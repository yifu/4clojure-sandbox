(ns four-clojure-sandbox.core)

(defn l-shift [[a & rest]] `(~@rest ~a))

(defn rot-x [x l]
  (last (take (inc (mod x (count l))) (iterate l-shift l))))

(defn rot-n [x l]
  (let [n (mod x (count l))]
        (reee (nthrest l n) (take n l))))

(defn rot-n [x l]
  (let [n (mod x (count l))]
       `( ~@(nthrest l n) ~@(take n l))))

(defn rot-n [x l]
  ( (fn [n] `( ~@(nthrest l n) ~@(take n l)) ) (mod x (count l))))

(defn rot-n [x l]
  #( `( (list ~@(nthrest l %) ~@(take % l))) ) (mod x (count l)))


(rot-n  2 '(:a :b :c :d :e :f :g :h))

(def seq-appends
  #(`(~@ % ~@ %1)))

(defn reee [l r]
  #(`(~@l ~@r)))

(seq-appends '(1 2) '(3 4 5 6))

(take 2 '(1 2 3 4 5 6))
(nthrest '(1 2 3 4 5 6) 2)

(def __ rot-x)

;; Solution
(def __ 
  #( (fn [n] `( ~@(nthrest %2 n) ~@(take n %2)) ) (mod % (count %2))))


(and (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
     (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
     (= (__ 3 [1 2 3 4 5]) '(4 5 1 2 3))
     (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
     (= (__ 1 '(:a :b :c)) '(:b :c :a))
     (= (__ -4 '(:a :b :c)) '(:c :a :b)))
