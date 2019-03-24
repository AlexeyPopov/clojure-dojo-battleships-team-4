(ns app.views
  (:require [app.state :refer [app-state]]
            [app.events :refer [increment decrement]]))

(defn header
  []
  [:div
  [:h1 "Battleships"]
   [:input {:name "name"
            :placeholder "Enter player name" 
            :value (get @app-state :name)
            :on-change #(swap! app-state assoc :name (-> % .-target .-value) ) } ]])

(defn bf-render []
  [:div.bf
  (doall 
  (for [row (:battlefield @app-state)]
    [:div.bf__row
      (for [cell row]
        (case cell
          :free [:div.bf__row__cell.bf__row__cell--free]
          :ship [:div.bf__row__cell.bf__row__cell--ship]
          :ship-dmg [:div.bf__row__cell.bf__row__cell--ship-dmg]
          :shot [:div.bf__row__cell.bf__row__cell--shot]
          [:div.bf__row__cell--else]))]))])

(defn debug []
  [:pre (with-out-str (cljs.pprint/pprint @app-state))])



(defn app []
  [:div.app
   [:div.app__header [header]]
   [:div.app__bf     [bf-render]]
   [:div.app__debug  [debug]] ])
