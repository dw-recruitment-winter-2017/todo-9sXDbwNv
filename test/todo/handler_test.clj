(ns todo.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [todo.handler :refer :all]
            [todo.db :as t]))

(def test-todo {:id 5000 :description "This is a sample TODO item" :completed false})
; Function to delete all data from the table to ensure
; test data is cleaned up

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "[\"You have no todos!\"]"))))
  
  (testing "about route"
    (let [response (app (mock/request :get "/about"))]
      (is (= (:status response) 200))
      (is (= (:body response) "About page"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "test route"
    (let [response
          (app
            (mock/request :get "/test/1"))]
          (is (= (:status response) 200))
          (is (= (:body response) "The user id is 1")))))

(deftest test-post
  (testing "create new todo"
    (let [response 
          (app 
            (mock/content-type
              (mock/body
                (mock/request :post "/todo")
                (json/write-str test-todo))
              "application/json"))]
      (is (= (:status response) 200))))

  (testing "mark todo complete"
    (let [response 
          (app 
            (mock/content-type 
              (mock/body
                (mock/request :post "/update")
                (json/write-str (update test-todo :completed not))) ;flips boolean false to true and updates the db
              "application/json"))]
      (is (= (:status response) 200))
      (is (= (:body response) 1))))

  (testing "mark todo incomplete"
    (let [response 
          (app 
            (mock/content-type 
              (mock/body
                (mock/request :post "/update")
                (json/write-str (update test-todo :completed not))) ;flips boolean false to true and updates the db
              "application/json"))]
      (is (= (:status response) 200))
      (is (= (:body response) 1))))

  (testing "delete todo"
    (def delete-url (str "/todo/" (get test-todo :id)))
    (let [response 
          (app 
            (mock/request :delete delete-url))]
      (is (= (:status response) 200))
      (is (= (:body response) "deleted")))))
