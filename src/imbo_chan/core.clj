(ns imbo-chan.core
  (:require [toucan.db :as db]
            [toucan.models :as models]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.api.sweet :refer [routes]]
            [imbo-chan.topic :refer [topic-routes]]
            [imbo-chan.settings :refer [db-spec]])
  (:gen-class))

(def app (apply routes topic-routes))

(defn -main [& args]
  (db/set-default-db-connection! db-spec)
  (models/set-root-namespace! 'imbo-chan.models)
  (run-jetty app {:port 3000}))
