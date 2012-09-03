(ns four-clojure-sandbox.core)

(defn is-a-prime [p]
  (every? true?
        (map #(not= 0 (mod p %)) (range 2 p))))

(defn is-a-prime [p]
  (reduce
   #(and
     %
     (not= 0 (mod p %2)))
   true
   (range 2 p)))

(def __ (fn nprime [n]
          (take n (filter
                   is-a-prime
                   (drop 2 (range))))))

;; Solution is:
(def __ (fn [n] (take n (filter (fn [p] (every? true? (map #(not= 0 (mod p %)) (range 2 p)))) (drop 2 (range))))))

(def __ (fn [n] (take n (remove nil? (for [p (drop 2 (range))] (if (every? true? (for [i (range 2 p)] (not= 0 (mod p i)))) p ))))))
                       
(remove nil? [nil nil nil 3 4 5 nil])

(and (= (__ 2) [2 3])
     (= (__ 5) [2 3 5 7 11])
     (= (last (__ 100)) 541))
