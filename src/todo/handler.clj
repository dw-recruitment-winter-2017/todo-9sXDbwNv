(ns todo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as middleware]
            [clojure.tools.logging :as log]))

(def test-data {:description "Example Todo item" :completed false})

(defroutes app-routes
  (GET "/" []
    {:status 200
     :body test-data})
  
  (GET "/about" [] "About page")

  ; (POST "/new" req
  ;   {:status 200})

  ; (POST "/update" req
  ;   {:status 200})

  (route/not-found {:message "Not Found"}))


(def app
  (-> (wrap-defaults app-routes api-defaults)

   (middleware/wrap-json-body {:keywords? true})
   (middleware/wrap-json-response)))
