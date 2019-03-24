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
            :on-change #(swap! app-state assoc :name (-> % .-target .-value) ) } ]
  [:Button {:type "Button"} "START" ] ] )


(defn bf-click [e]
  (println e)
  (let [cell (.-target e) 
        ds (.-dataset cell)
        r  (js/parseInt (aget ds "row"))
        c  (js/parseInt (aget ds "col"))
        ]
    (js/console.log r c)
    0 (swap! app-state assoc-in [:battlefield r c] :ship ) 
  ))

(defn op-bf-click [e]
  (println e)
  (let [cell (.-target e) 
        ds (.-dataset cell)
        r  (js/parseInt (aget ds "row"))
        c  (js/parseInt (aget ds "col"))
        ]
    (js/console.log r c)
  
    (swap! app-state assoc-in [:op-battlefield r c] :ship )
  ))



(defn bf-render []
  (let [field (:battlefield @app-state)]
  [:div.bf
  (doall 
  (for [i (range (count field))
        :let [row (nth field i)]]
    [:div.bf__row
      (for [j (range (count row))
            :let [cell (nth row j)
                  attr {:data-row i
                        :data-col j
                        :on-click bf-click} ] ] 
        (case cell 
          :free [:div.bf__row__cell.bf__row__cell--free attr]
          :ship [:div.bf__row__cell.bf__row__cell--ship attr]
          :ship-dmg [:div.bf__row__cell.bf__row__cell--ship-dmg attr]
          :shot [:div.bf__row__cell.bf__row__cell--shot attr]
          [:div.bf__row__cell--else ]))]))]))


(defn op-bf-render []
  (let [field (:op-battlefield @app-state)]
  [:div.bf
  (doall 
  (for [i (range (count field))
        :let [row (nth field i)]]
    [:div.bf__row
      (for [j (range (count row))
            :let [cell (nth row j)
                  attr {:data-row i
                        :data-col j
                        :on-click op-bf-click} ] ] 
        (case cell 
          :free [:div.bf__row__cell.bf__row__cell--free attr]
          :ship [:div.bf__row__cell.bf__row__cell--ship attr]
          :ship-dmg [:div.bf__row__cell.bf__row__cell--ship-dmg attr]
          :shot [:div.bf__row__cell.bf__row__cell--shot attr]
          [:div.bf__row__cell--else ]))]))]))

(defn debug []
  [:pre (with-out-str (cljs.pprint/pprint @app-state))])



(defn app []
  [:div.app
   [:div.app__header [header]]
   [:div.app__fields
    [:div.app__bf  [bf-render "My ships"]]
    [:div.app__bf  [op-bf-render "Opponent"]]]
   [:div.app__debug  [debug]] ])



  

