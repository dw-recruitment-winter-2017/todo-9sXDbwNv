(ns todo.db-test
  (:require [clojure.test :refer :all]
            [todo.db :as t]))

(def test-todo {:id 5000 :description "This is a sample TODO item" :completed false})

(deftest test-db
  (testing "add new todo"
    (def insert-response (t/insert-todo test-todo))
    (is (= (get insert-response :description) "This is a sample TODO item")))

  ; There should only be one todo in the db when this test runs
  (testing "get all todos"
    (def todos (first (t/all-todos)))
    (is (= (get todos :id) 5000))
    (is (= (get todos :description) (get test-todo :description))))

  ; Note: Korma db returns 1 if the db transaction was successful
  (testing "mark todo as completed"
    (def todo-completed (t/complete-todo test-todo))
    (is (= todo-completed 1)))

  (testing "delete todo"
    (def deleted (t/delete-todo (get test-todo :id)))
    (is (= deleted 1))))
