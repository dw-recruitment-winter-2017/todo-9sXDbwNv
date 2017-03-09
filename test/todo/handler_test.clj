(ns todo.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [todo.handler :refer :all]))

(def test-todo {:description "This is a test TODO item"})
(def update-todo {:id 1 :description "This description has been updated" :completed false})

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "[]"))))
  
  (testing "about route"
    (let [response (app (mock/request :get "/about"))]
      (is (= (:status response) 200))
      (is (= (:body response) "About page"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest test-post
  (testing "create new todo"
    (let [response 
          (app 
            (mock/content-type
              (mock/body
                (mock/request :post "/new")
                (json/write-str test-todo))
              "application/json"))]
      (is (= (:status response) 200))))

  (testing "mark todo complete"
    (let [response 
          (app 
            (mock/content-type 
              (mock/body
                (mock/request :post "/update")
                (json/write-str update-todo))
              "application/json"))]
      (is (= (:status response) 200)))))

; (deftest test-db
;   (testing "add new todo to db"
;     (let [count-todo ()])))
