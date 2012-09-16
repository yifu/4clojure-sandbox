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

(def myvar balanced-prime? )

(load-string "(defn prime-p [n]
  (if (> n 1)  
    (not-any? zero? (for [x (range 2 n)] (mod n x)))))
")

(for   [ x  (read-string "(defn prime-p [n]
  (if (> n 1)  
    (not-any? zero? (for [x (range 2 n)] (mod n x)))))
")] (prn x))

(read-string "#()")


(prn (read-string "(defn balanced-prime? [n]
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
"))


(defn shorten-list [l]
  (apply str  (for [x l] (shorten-it x))))


(defn shorten-it [elt]
  (let [core? #{'defn 'if 'for 'let 'loop 'and 'or 'recur}]
   (cond
    (list? elt) (str "(" (shorten-list elt) ")")
    (vector? elt) (str "[" (shorten-list elt) "]")
    (core? elt) (str elt " ")
    (clojure.test/function? elt) (str elt " ")
    (keyword? elt) (str "kwd " elt " ")
    (symbol? elt) (str (first (name elt)) " ")
    :else (str elt " "))))

(load-string (shorten-it '
   (defn prime-p [nbr]
     (if (> nbr 1)  
       (not-any? zero? (for [elt (range 2 nbr)] (mod nbr elt)))))
   ))

(shorten-it (read-string "(defn balanced-prime? [n]
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
"))

