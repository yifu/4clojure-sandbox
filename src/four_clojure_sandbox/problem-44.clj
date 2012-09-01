(ns four-clojure-sandbox.core)

(defn myfun [l]
  (prn ~@l))

(defn r-shift [l]
  (if (empty? l)
    l
    (cons (last l) (butlast l) )))

(defn l-shift [[a & rest]] `(~@rest ~a))

(l-shift '(1 2 3))

(defn rot-x [x l]
   (if (> x 0)
     (flatten (take-last 1 (take (inc x) (iterate l-shift l))))
     (reverse (flatten (take-last 1 (take (inc (- x)) (iterate l-shift (reverse l))))))))

(def __ rot-x)

(__ -2 [1 2 3 4 5 6 7 8 9 ])
(reverse (flatten (take-last 1 (take (inc (- -2)) (iterate l-shift (reverse '(1 2 3 4 5 6)))))))


(and (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
     (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
     (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
     (= (__ 1 '(:a :b :c)) '(:b :c :a))
     (= (__ -4 '(:a :b :c)) '(:c :a :b)))
