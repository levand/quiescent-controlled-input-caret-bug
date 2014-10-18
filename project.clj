(defproject streaker "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2234"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [quiescent/quiescent "0.1.4"]
                 [org.clojars.franks42/cljs-uuid-utils "0.1.3"]
                 [cljs-cursor "0.1.0-SNAPSHOT"]
                 [com.cemerick/piggieback "0.1.3"]
                 [weasel "0.2.0"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles {:dev {:source-paths ["src" "dev"]}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src" "dev"]
                        :compiler {:output-to "out/main.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true}}]})
