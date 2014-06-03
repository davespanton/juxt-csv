(ns juxt-csv.core-test
  (:use [midje.sweet])
  (:require [juxt-csv.core :refer :all]))

(def towns ["BECKENHAM" "GOOLE" "LEICESTER" "BARNARD CASTLE" "PETERLEE" "LOUGHTON" "PETERBOROUGH" "COLEFORD" "WEDNESBURY" "FELIXSTOWE"])

(def towns-repeating (flatten (repeat towns)))

(defn generate-record
  [sale-val record-status town-or-city]
  [nil (str sale-val) nil nil nil nil nil nil nil nil nil town-or-city nil nil record-status])

(def fixed-price-records (map (partial generate-record 250000 "A") (take 20 towns-repeating)))

(def random-price-records (map (partial generate-record (rand-int 500000) "A") towns))

(def deleted-records (map (partial generate-record 250000 "D") towns))


(fact "results contain totals of sales by town"
      (highest-sales 10 fixed-price-records) => (has every? #(= (second %) 500000)))

(fact "results are sorted by sales value, descending"
      (let [result (highest-sales 10 random-price-records)] result => (sort-by second result)))

(fact "results are grouped by town"
      (count (highest-sales 20 fixed-price-records)) => (count towns))

(fact "results are limited to top n"
      (let [limit 5] (count (highest-sales limit random-price-records)) => limit))

(fact "filters out deletion and change records"
      (let [records (conj fixed-price-records deleted-records)]
        (highest-sales 20 records) => (has every? #(= (second %) 500000))))
