(ns todo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.coercions :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as middleware]
            [clojure.tools.logging :as log]
            [todo.db :as t]))

(def test-data {:description "Example Todo item" :completed false})

(defn save-todo [request]
  (try
    (log/info (get-in request [:body]))
    (catch Exception e (log/error (str "Error saving data to db: " (.getMessage e))))))

(defroutes app-routes
  (GET "/" []
    (def todos (t/all-todos))
    {:status 200
     :body (if (empty? todos) ["You have no todos!"] todos )})
  
  (GET "/about" [] "About page")

  (GET "/test/:id" [id]
    (log/info "In test route")
    {:status 200
      :body (str "The user id is " id)})

  ; TODO: consider make these one endpoint and have logic in
  ; db code to insert or update depending on if todo already
  ; exists in the db
  (POST "/todo" request
    (def insert-response (t/insert-todo (get-in request [:body])))
    {:status 200
     :body insert-response})

  (POST "/update" request
    (def update-response (t/complete-todo (get-in request [:body])))
    {:status 200
     :body update-response})

  (DELETE "/todo/:id" [id :<< as-int]
    ; (log/info (str "In DELETE route, id is " id "and type is " (type id))))
    (def delete-response (t/delete-todo id))
    (log/info delete-response)
    {:status 200
     :body "deleted"})

  (route/not-found {:message "Not Found"}))


(def app
  (-> (wrap-defaults app-routes api-defaults)

   (middleware/wrap-json-body {:keywords? true})
   (middleware/wrap-json-response)))
