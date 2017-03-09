(ns todo.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [todo.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"description\":\"Example Todo item\",\"completed\":false}"))))
  
  (testing "about route"
    (let [response (app (mock/request :get "/about"))]
      (is (= (:status response) 200))
      (is (= (:body response) "About page"))))

  (testing "create new todo"
    (let [response (app (mock/request :post "/new"))]
      (is (= (:status response) 200))))

  (testing "mark todo complete"
    (let [response (app (mock/request :post "/update"))]
      (is (= (:status response) 200))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
