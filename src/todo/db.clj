(ns todo.db
  (:require [clojure.java.jdbc :as sql]
            [korma.core :refer :all :rename {update sql-update}]
            [korma.db :as db]
            [clojure.tools.logging :as log]
            [environ.core :refer [env]]))

(def db-name (env :database-url))

(db/defdb pgdb (db/postgres {:db db-name
                     :user "dw_todo"}))

(defentity dw_todo
  (table :dw_todo)
  (entity-fields :id :description :completed))

(defn all-todos []
  (select dw_todo))

(defn insert-todo [todo]
  (insert dw_todo
        (values todo)))

; TODO: make this a generic update by key, value 
(defn complete-todo [todo]
  (sql-update dw_todo
    (set-fields {:completed true})
    (where {:id (get todo :id)})))

; TODO: return errors to the route handler and
; display on front end
(defn delete-todo [todo-id]
  (log/info "In delete-todo, id is " todo-id)
  (try
    (delete dw_todo
      (where {:id todo-id}))
  (catch Exception e (log/error (str "Error saving data to db: " (.getMessage e))))))
