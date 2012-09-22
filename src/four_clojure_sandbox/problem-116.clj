(ns fourclojure-sandboc.core.problem-116)

(defn prime-p [n]
  (if (> n 1)  
    (not-any? zero? (for [x (range 2 n)] (mod n x)))))

(prime-p 0)
(prime-p 2)
(if (prime-p -1)
  "yes" "no")


(for [x (range 15)]
  (prn x "|" (if (prime-p x) "prime" "nor a prime" )))

(prime-p 2)

(for [x (range 1 6)] [x (mod 6 x)])

(for [x (range 1 20)] [x (prime-p x)])


(defn balanced-prime? [n]
  (if (prime-p n)
    (loop [x 1]
      (let [a (- n x)
            b (+ n x)
            p-a (prime-p a)
            p-b (prime-p b)]
        (comment (if (and p-a p-b)
                   true
                   (if (not (or p-a p-b))
                     (recur (inc x))
                     false)))
        (or (and p-a p-b) (and (not p-a) (not p-b) (recur (inc x))))))
    false))

;; Solution is:
(def ^{:private true} __ (fn [n]
                           (let [p (fn [n] (if (> n 1) (not-any? zero? (for [x (range 2 n)] (mod n x)))))]
                               (if (p n)
                                 (loop [x 1]
                                   (let [c (p (- n x))
                                         d (p (+ n x))]
                                     (or (and c d) (and (not c) (not d) (recur (inc x))))))
                                 false))))

(def ^{:private true} __ (fn [n]
                           (let [p (fn [n] (if (> n 1) (not-any? zero? (for [x (range 2 n)] (mod n x)))))]
                               (if (p n)
                                 ((fn [x]
                                    (let [c (p (- n x))
                                          d (p (+ n x))]
                                      (or (and c d) (and (not c) (not d) (recur (inc x)))))) 1)
                                 false))))


(= nil false)
(prime-p 1)
(prime-p 3)
(balanced-prime? 1)
(balanced-prime? 2)
(balanced-prime? 563)
(take 20 (filter balanced-prime? (range)))

(def ^{:private true} __ balanced-prime?)

(and (= false (__ 4))
     (= true (__ 563))
     (= 1103 (nth (filter __ (range)) 15)))


;; Second solution from scratch to avoid the timeout issue.

(defn- prime? [n]
  (and
   (> n 1)  
   (not-any? zero? (for [x (range 2 n)] (mod n x)))))

(defn is-a-prime [p]
  (and (> p 1)
       (every? true?
               (map #(not= 0 (mod p %)) (range 2 p)))))

(for [i (range 0 15)] [ i (prime? i)])
(is-a-prime 0)

(defn- a-prime [n o]
  (if (pos? n)
    (if (prime? (o n)) (o n) (recur (o n) o))
    n))

(defn- n-prime [n] (a-prime n inc))
(defn- p-prime [n] (a-prime n dec))

(n-prime 13)
(p-prime 13)
(p-prime 1)


(defn- balanced-prime? [n]
  (and
   (prime? n)
   (= n (/ (+ (n-prime n) (p-prime n)) 2))))

(def __ balanced-prime?)

(balanced-prime? 564)
(for [i (range 0 15)] [i (balanced-prime? i)])

(defn __ [i]
  (let [p (fn [p] (and (> p 1)
                   (every? true?
                           (map #(not= 0 (mod p %)) (range 2 p)))))
        a (fn [n o]
            (if (pos? n)
              (if (p (o n)) (o n) (recur (o n) o))
              n))
        n (fn [n] (a n inc))
        r (fn [n] (a n dec))]
    (and
     (p i)
     (= i (/ (+ (n i) (r i)) 2)))))


(and (= false (__ 4))
     (= true (__ 563))
     (= 1103 (nth (take 20 (filter __ (range))) 15)))
