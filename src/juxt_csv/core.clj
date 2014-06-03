(ns juxt-csv.core
  (:require [clojure.java.io :as io])
  (:require [clojure.data.csv :as csv])
  (:use [clojure.algo.generic.functor :only (fmap)])
  (:gen-class))

(defn read-data
  "Reads a csv from the specified file"
  [filename]
  (with-open [in-file (io/reader filename)] (doall (csv/read-csv in-file))))

(defn filter-for-additions
  "Filters out records that aren't additions to the sales record"
  [data]
  (filter #(= (last %) "A") data))

(defn group-data
  "Groups data into a hashmap keyed by town"
  [data]
  (group-by #(nth % 11) data))

(defn total-spend
  "Totals sales for an an area or group of data"
  [areadata]
  (apply + (map #(read-string (second %)) areadata)))

(defn all-totals
  "Returns a hashmap of sales totals by town/city from a coll of sales"
  [data]
  (fmap total-spend (group-data (filter-for-additions data))))

(defn highest-sales
  "Reads a csv and returns the highest 20 sales totals by town"
  [n data]
  (take n (reverse (sort-by second (all-totals data)))))

(defn run
  [in-filename]
  (with-open [out-file (io/writer "out.csv")]
    (csv/write-csv out-file (highest-sales 20 (read-data in-filename)))))

(defn -main
  [& args]
  (if args
    (run (first args))
    (println "Expected an input filename.")))
