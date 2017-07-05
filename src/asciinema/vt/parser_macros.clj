(ns asciinema.vt.parser-macros
  (:require [clojure.string :as str]))

(defn event-seq [event]
  (if (keyword? event)
    (let [[low high] (str/split (name event) #"-")
          low (Long/decode low)
          high (Long/decode high)]
      (range low (inc high)))
    [event]))

(defmacro events [& items]
  `(set '~(mapcat event-seq items)))
