(ns solo-counter.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom 0))
(def button-styles
  {:border-radius "4px"
   :border "none"
   :margin "5px"
   :width "40px"
   :height "40px"
   :text-align "center"
   :font-size "20px"})
(defn counter []
  [:center
   {:style {:font-family "Helvetica"}}
   [:h1
    {:style {:color "palevioletred"}}
    "Simple Counter"]
      [:p {:style {:font-size 42}} @app-state]
        [:button
         {:style button-styles
          :on-click
         (fn [e] (swap! app-state inc))} "+"]
        [:button
         {:style button-styles
          :on-click
          (fn [e] (swap! app-state dec))} "-"]
        (prn @app-state)])

(reagent/render-component [counter]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
