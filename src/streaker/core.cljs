(ns streaker.core
  (:require [quiescent :as q :include-macros true]
            [quiescent.dom :as d]
            [cljs-cursor :refer [build-cursor refine set value]]))


(q/defcomponent App [cursor]
  (d/div {:className "App"}
         (d/pre {} (.stringify js/JSON (clj->js (value cursor)) nil 2))
         (let [a (-> cursor
                     (refine :app)
                     (refine :a))]
           (d/input {:value (value a)
                     :onChange #(set a (-> % (.-value) (.-target)))}))))

(def initial-state {:app
                    {:a "foo"}})

(let [render-pending? (atom false)
      state (atom initial-state)]

  (defn request-render []
    (when (compare-and-set! render-pending? false true)
      (.setTimeout js/window
                   (fn []
                     (q/render (App (build-cursor state request-render))
                               (.getElementById js/document "root"))
                     (reset! render-pending? false))
                   0)))


  (defn ^:export main []
    (enable-console-print!)
    (request-render)))

(main)
