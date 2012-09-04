(ns four-clojure-sandbox.core)

(take 5 (reductions + (range)))
(reductions conj [1] [2 3 4])

((fn [[a b]] [(+ a (first b)) (rest b)] ) [(+ 0) (rest [1 2 3 4 5 6])])

(def myreductions-aux (fn m
  ([o r] (m o (first r) (rest r)))
  ([o x r] (cons x (if (empty? r)
                      r
                      (lazy-seq (m o (o x (first r)) (rest r))))))))


(myreductions-aux + 0 [1 2 3 4])
(take 4 (myreductions-aux conj [1] [2 3 4]))

(= (take 5 (myreductions-aux + (range))) [0 1 3 6 10])
(= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])

(def __ myreductions-aux)

(and (= (take 5 (__ + (range))) [0 1 3 6 10])
     (= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
     (= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))