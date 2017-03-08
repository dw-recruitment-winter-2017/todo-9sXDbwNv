(ns todo.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [todo.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))
  
  (testing "about route"
    (let [response (app (mock/request :get "/about"))]
      (is (= (:status response) 200))
      (is (= (:body response) "About page"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest test-todo
  (testing "can add new todo")
  (testing "can mark todo as completed")
  (testing "can unmark todo as completed")
  (testing "can delete existing todos"))
