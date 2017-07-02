(ns solo-counter.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom 0))
(defonce js-time (atom (js/Date.)))
(defonce time-interval (js/setInterval
                         ;Reset time to current time every second
                        (fn [] (reset! js-time (js/Date.)) 1000)))

(def button-styles
  {:border-radius "4px"
   :border "none"
   :margin "5px"
   :width "40px"
   :height "40px"
   :text-align "center"
   :font-size "20px"})

(def reset
  {:width "80px"
   :border-radius "4px"
   :border "none"
   :height "40px"
   :text-align "center"
   :font-size "20px"
   })

(def clock-styles
  {:width "300px"
   :height "150px"
   :border "solid 2px black"
   :border-radius "3px"
   :box-shadow "0 1px 1px rgba(0, 0, 0, 0.5)"
   :padding "3px"
   :font-size "50px"
   })

(defn counter []
  [:center
   {:style {:font-family "Helvetica"}}
   [:h1
    {:style
     {:color "palevioletred"}}
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
        ;(prn @app-state)
        [:p [:button
             {:style reset
              :on-click
             (fn [e] (reset! app-state 0))}
             "Reset"
             ]]])

(defn clock []
  (let [time-str (-> @js-time .toTimeString (clojure.string/split " ") first)]
    [:center {:style {:font-family "Helvetica"}}
     [:h1  {:style {:color "palevioletred"}} "Random Clock"]
     [:div
      {:style clock-styles}
      "Time Now is:"
      [:p {:style {:color "palevioletred" :margin "2px"}} 
       time-str]] ]))

(defn container []
  [:center [counter]
           [clock]])

(reagent/render-component [container]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
