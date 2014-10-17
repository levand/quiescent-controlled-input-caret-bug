(ns streaker.dev
  (:require [weasel.repl :as ws-repl]))

(def ^:private ws-url "ws://localhost:9001")

(ws-repl/connect ws-url :verbose true)
