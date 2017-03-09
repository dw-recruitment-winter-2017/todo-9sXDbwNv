(ns todo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
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
     :body todos})
  
  (GET "/about" [] "About page")

  ; maybe make these one endpoint and have logic in
  ; db code to insert or update depending on if todo
  ; exists in the db or not
  (POST "/new" request
    (def insert-response (t/insert-todo (get-in request [:body])))
    {:status 200
     :body insert-response})

  (POST "/update" request
    (def update-response (t/complete-todo (get-in request [:body])))
    {:status 200
     :body update-response})

  ; need to generate a dynamic route for this
  (DELETE "/todo/:id" [id]
    (def delete-response (t/delete-todo id))
    {:status 200
     :body delete-response})

  (route/not-found {:message "Not Found"}))


(def app
  (-> (wrap-defaults app-routes api-defaults)

   (middleware/wrap-json-body {:keywords? true})
   (middleware/wrap-json-response)))
