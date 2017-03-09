(ns todo.db
  (:require [clojure.java.jdbc :as sql])
  (:use korma.db)
  (:use korma.core))

(defdb db (postgres {:db "dw_todo"
                     :user "dw_todo"}))

(defentity dw_todo
  (table :dw_todo)
  (entity-fields :description :completed))

(defn all-todos []
  (select dw_todo))

(defn insert-todo [todo]
  (insert dw_todo
        (values todo)))

(defn update-todo [todo]
  (update dw_todo
    (set-fields (dissoc dw_todo :id))
    (where {:id (dw_todo :id)})))
