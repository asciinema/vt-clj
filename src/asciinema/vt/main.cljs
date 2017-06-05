(ns asciinema.vt.main
  (:refer-clojure :exclude [js->clj])
  (:require [asciinema.cljs.patch :refer [js->clj]]
            [asciinema.vt :as vt]
            [asciinema.vt.screen :as screen]
            [cljs.nodejs :as nodejs]
            [clojure.string :as str]))

(nodejs/enable-util-print!)

(def liner (nodejs/require "./liner"))

(def vt (atom (vt/make-vt 80 24)))

(defn to-json [obj]
  (-> obj clj->js JSON.stringify))

(defn print-result [data]
  (println (to-json {:result data})))

(defmulti process-command :cmd)

(defmethod process-command "new" [msg]
  (reset! vt (vt/make-vt (:width msg) (:height msg))))

(defmethod process-command "feed-str" [msg]
  (swap! vt vt/feed-str (:str msg)))

(defmethod process-command "dump-str" [msg]
  (print-result (vt/dump @vt)))

(defmethod process-command "dump-screen" [msg]
  (let [screen {:lines (-> @vt :screen screen/lines)
                :cursor (-> @vt :screen screen/cursor)}]
    (print-result screen)))

(defn process-line [line]
  (let [line (-> line str str/trim)]
    (when (not= line "")
      (let [payload (-> line
                        str
                        JSON.parse
                        (js->clj :keywordize-keys true))]
        (process-command payload)))))

(defn -main [& args]
  (let [stdin (.-stdin nodejs/process)]
    (.pipe stdin liner)
    (.on liner "readable" (fn []
                            (when-let [line (.read liner)]
                              (process-line line)
                              (recur))))))

(set! *main-cli-fn* -main)
