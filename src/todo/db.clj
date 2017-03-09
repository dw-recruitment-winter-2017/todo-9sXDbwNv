(ns todo.db
  (:require [clojure.java.jdbc :as sql]
            [korma.core :refer :all :rename {update sql-update}]
            [korma.db :as db]))

(db/defdb pgdb (db/postgres {:db "dw_todo"
                     :user "dw_todo"}))

(defentity dw_todo
  (table :dw_todo)
  (entity-fields :id :description :completed))

(defn all-todos []
  (select dw_todo))

(defn insert-todo [todo]
  (insert dw_todo
        (values todo)))

(defn complete-todo [todo]
  (sql-update dw_todo
    (set-fields {:completed true})
    (where {:id (get todo :id)})))

(defn delete-todo [todo-id]
  (delete dw_todo
    (where {:id todo-id})))

(defn delete-all []
  (delete dw_todo))

(defn select-one []
  (select dw_todo
    (limit 1)))
