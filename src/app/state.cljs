(ns app.state
  (:require [reagent.core :refer [atom]]))

(defonce app-state (atom {:battlefield (vec (repeat 10 (vec (repeat 10 :free))))
                          :name ""
                          :game 1}))
