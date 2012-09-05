(ns four-clojure-sandbox.problem-75)

(defn divisor-list [n]
  (for [x (range 1 (inc n)) :when (zero? (mod n x))] x))

(= #{1} (clojure.set/intersection (set (divisor-list 5))
(set (divisor-list 6))))
(divisor-list 24)
(divisor-list 10)
(divisor-list 5)
(range 1 5)

(defn coprime? [a b]
  (= #{1} (clojure.set/intersection (set (divisor-list a)) (set (divisor-list b)))))

(coprime? 5 6)
(coprime? 3 6)

(defn euler-totient [n]
  (if (= n 1)
    1
    (count (for [i (range 1 n) :when (coprime? i n)]
             i))))
  
(euler-totient 10)
(count (euler-totient 40))
(count (euler-totient 99))

(set (for [i (range 10) :when (odd? i)] i))

(let [n 10]
  (keep #(if (zero? (mod n %)) % nil) (range 1 (inc n))))

;    (let [divisor (fn [n] (set (for [x (range 1 (inc n)) :when (zero?
                                        ;    (mod n x))] x)))
;; Solution is:
(def ^{ :private true } __
  (fn [n]
    (let [divisor (fn [n] (set (keep #(if (zero? (mod n %)) % nil) (range 1 (inc n)))))
          coprime? (fn [a b] (= #{1} (clojure.set/intersection (divisor a) (divisor b))))]
      (if
          (= n 1)
        1
        (count (for [i (range 1 n) :when (coprime? i n)] i))))))

(and (= (__ 1) 1)
     (= (__ 10) (count '(1 3 7 9)) 4)
     (= (__ 40) 16)
     (= (__ 99) 60))