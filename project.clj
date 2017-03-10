(defproject todo "0.1.0-SNAPSHOT"
  :description "A simple TODO tracking application"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.21"]
                 [log4j/log4j "1.2.17" 
                    :exclusions [javax.mail/mail
                                 javax.jms/jms
                                 com.sun.jmdk/jmxtools
                                 com.sun.jmx/jmxri]]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [korma "0.4.3"]
                 [environ "1.1.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring/ring-mock "0.3.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-environ "1.1.0"]]
  :ring {:handler todo.handler/app
         :nrepl {:start? true
                 :port 8080}}
  :profiles {:dev {:env {:database-name "dw_todo_dev"}}
             :test {:env {:database-name "dw_todo_test"}}})
