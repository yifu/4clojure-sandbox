(ns fourclojure-sandbox.problem-148)

(defn- big-div [n a b]
  (-
   (+
    (reduce + (take-while #(< % n) (iterate #(+ a %) a)))
    (reduce + (take-while #(< % n) (iterate #(+ b %) b))))
   (reduce + (take-while #(< % n) (iterate #(+ (* a b) %) (* a b))))))


(defn- big-div [n a b]
  (reduce + (take )))

(defn- big-div [n a b]
  (reduce +
          (for [x (range n)]
            (if (or (zero? (mod x a)) (zero? (mod x b)))
              x
              0))))

(time (big-div 10 3 5))
(time (big-div 1000 3 5))

(reduce + (take-while #(< % 100000000) (iterate #(+ % 3) 3)))1666666683333333
(reduce + (take-while #(< % 100000000) (iterate #(+ % 5) 5)))999999950000000
(reduce + (take-while #(< % 100000000) (iterate #(+ % 15) 15)))333333316666665
(+  999999950000000 1666666683333333 -333333316666665)

(defn- sum-n-first [n]  (/ (* n (inc n)) 2))

(for [n (range 10)]
  [n (sum-n-first n)])

(= 1666666683333333 (* 3 (sum-n-first (dec (quot 100000000 3)))))
(= 999999950000000 (* 5 (sum-n-first (dec (quot 100000000 5)))))
(= 333333316666665 (* 15 (sum-n-first (dec (quot 100000000 15)))))

(+
 (* 3 (sum-n-first  (quot (dec 100000000) 3)))
 (* 5 (sum-n-first  (quot (dec 100000000) 5)))
 (- (* 15 (sum-n-first (quot (dec 100000000) 15)))))

(defn- sum-n-based [n max] (* n (sum-n-first (quot (dec max) n))))

(defn- big-div [n a b]
  (- (+ (sum-n-based a n) (sum-n-based b n)) (sum-n-based (* a b) n)))

(big-div 3 17 11)
(big-div 10 3 5)
(big-div 1000 3 5)
(big-div (* 10000 10000 1000) 1597 3571)

(def ^{:private true} __ big-div)

;; Solution is:
(def ^{:private true} __
  (fn [n a b]
    (let [s (fn [n]  (/ (* n (inc n)) 2))
          f (fn [n max] (* n (s (quot (dec max) n))))]
     (- (+ (f a n) (f b n)) (f (* a b) n))))
  )

(* (* 10000 10000 10000) (/ (* (quot (dec (* 10000 10000 10000)) 809) (inc (quot (dec (* 10000 10000 10000)) 809))) 2))

(def ^{:private true} __
  (fn [n a b]
    (let [t (fn [n x]
              (let [d (dec n)
                    c (* d d)]
                (/ (+ c (* x d) ) (* 2 x))))]
      (- (+ (t n a) (t n b)) (t n (* a b)))))
  )
;; resolve[a * sum i, from 1 to (n -1)/a ]

(def ^{:private true} __
  (fn [n a b]
    (let [t (fn [n a] (/ (* (dec n) (+ a n -1) ) (* 2 a)))]
      (+ (t n a) (t n b) (- (t n (* a b))))))
  )

(quot 241 187)
(defn- suum [n a] (/ (- a n -1) a))

(defn- sum [n a] (/ (* (dec n) (+ a n -1)) (+ a a)))

(defn- sum [n a] (let [t (bigint (quot (dec n) a))] (/ (* a t (inc t))  2)))

(defn- big-div [n a b] (+ (sum n a) (sum n b) (- (sum n (* b a)))))

;; Solution is :

(def ^{:private true} __
  (fn [n a b]
    (let [s (fn [n a] (let [t (bigint (quot (dec n) a))] (/ (* a t (inc t))  2)))]
      (+ (s n a) (s n b) (- (s n (* b a))))))
  )

(suum 10 3)

(and (= 0 (__ 3 17 11))
     (= 23 (__ 10 3 5))
     (= 233168 (__ 1000 3 5))
     (= "2333333316666668" (str (__ 100000000 3 5)))
     (= "110389610389889610389610"
        (str (__ (* 10000 10000 10000) 7 11)))
     (= "1277732511922987429116"
        (str (__ (* 10000 10000 10000) 757 809)))
     (= "4530161696788274281"
        (str (__ (* 10000 10000 1000) 1597 3571))))