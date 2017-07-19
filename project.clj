(defproject vt "0.1.0-SNAPSHOT"
  :description "asciinema virtual terminal"
  :url "https://github.com/asciinema/vt"
  :license {:name "Apache 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.671"]
                 [org.clojure/test.check "0.9.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [cljsjs/nodejs-externs "1.0.4-1"]
                 [prismatic/schema "1.1.6"]]

  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-doo "0.1.7"]]

  :min-lein-version "2.5.3"

  :clean-targets ^{:protect false} ["resources/js" "target" "out" "main.js"]

  :source-paths ["src"]

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]
                                  [environ "1.0.1"]]
                   :plugins [[refactor-nrepl "1.1.0"]]}
             :repl {:plugins [[cider/cider-nrepl "0.10.0"]]}}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :cljsbuild {:builds {:test {:source-paths ["src" "test"]
                              :compiler {:output-to "out/test.js"
                                         :source-map true
                                         :foreign-libs [{:file "codepoint-polyfill.js"
                                                         :provides ["asciinema.vt.codepoint-polyfill"]}]
                                         :optimizations :none
                                         :pretty-print false
                                         :main "asciinema.test-runner"}}
                       :main {:source-paths ["src"]
                              :compiler {:output-to "main.js"
                                         :foreign-libs [{:file "codepoint-polyfill.js"
                                                         :provides ["asciinema.vt.codepoint-polyfill"]}]
                                         :optimizations :advanced
                                         :pretty-print false
                                         :elide-asserts true
                                         :target :nodejs
                                         :main "asciinema.vt.main"}}}})
