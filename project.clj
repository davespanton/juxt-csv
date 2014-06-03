(defproject juxt-csv "0.1.0-SNAPSHOT"
  :description "JUXT technical"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.csv "0.1.2"]
                 [org.clojure/algo.generic "0.1.2"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]]}}
  :main juxt-csv.core)
