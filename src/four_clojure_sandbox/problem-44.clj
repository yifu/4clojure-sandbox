(ns four-clojure-sandbox.core)

(defn l-shift [[a & rest]] `(~@rest ~a))

(defn rot-x [x l]
  (last (take (inc (mod x (count l))) (iterate l-shift l))))

(def __ rot-x)

(and (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
     (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
     (= (__ 3 [1 2 3 4 5]) '(4 5 1 2 3))
     (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
     (= (__ 1 '(:a :b :c)) '(:b :c :a))
     (= (__ -4 '(:a :b :c)) '(:c :a :b)))
