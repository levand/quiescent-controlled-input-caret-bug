(ns streaker.core
  (:require [quiescent :as q :include-macros true]
            [quiescent.dom :as d]))


(defn App [state-atom refresh-fn]
  (d/div {:className "App"}
         (d/pre {} (.stringify js/JSON (clj->js @state-atom) nil 2))
         (d/input {:value (get-in @state-atom [:app :a])
                   :onChange (fn [e]
                               (let [val (->> e (.-target) (.-value))]
                                 (reset! state-atom
                                         (assoc-in @state-atom [:app :a] val))
                                 (refresh-fn)))})))


(def initial-state {:app {:a "foo"}})


(let [render-pending? (atom false)
      state (atom initial-state)]
  (defn request-render []
    (when (compare-and-set! render-pending? false true)
      (.setTimeout js/window
                   (fn []
                     (q/render (App state request-render)
                               (.getElementById js/document "root"))
                     (reset! render-pending? false))
                   0))))


(defn ^:export main []
  (enable-console-print!)
  (request-render))

(main)
