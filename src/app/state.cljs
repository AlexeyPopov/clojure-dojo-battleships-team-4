(ns app.state
  (:require [reagent.core :refer [atom]]))

(defonce app-state (atom {:battlefield (vec (repeat 10 (vec (repeat 10 :free))))
                          :op-battlefield (vec (repeat 10 (vec (repeat 10 :free))))
                          :op-name ""
                          :name ""
                          :game 1}))
