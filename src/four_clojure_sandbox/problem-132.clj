(ns four-clojure-sandbox.problem-132)

;; First attempt:
(defn- insert [c v s]
  (reduce (fn [s n] (if (c (last s) n) (conj s v n) (conj s n))) [(first s)] (rest s)))

(insert  < :less [1 6 7 4 3])

;;Second attempt, coping with empty seq and ... integer overflow?
(defn- insert [c v [a b & r :as m]]
  (if a
    (if b
;;      (comment (if (c a b)
;;                 `(~a ~v ~@(insert c v (rest m)))
;;                 `(~a ~@(insert c v (rest m)))))

;;      (concat [a] (if (c a b) [v] nil) (insert c v (rest m)))

      (concat [a] (if (c a b) [v] nil) (insert c v `(~b ~@r)))
      [a])
    []))

;; Nth reduction is code size:
(comment (defn- insert [c v [a b & r :as m]]
  (if a
    `(~a ~@(if b (if (c a b) [v] nil)) ~@(insert c v `(~b ~@r)))
    [])))

;; Pfiouu..
(defn- insert [c v [a b & r]]
  (if a
    `(~a ~@(if b `(~@(if (c a b) [v] nil) ~@(insert c v `(~b ~@r))))) ;; (if (c a b) [v] nil)) ~@(insert c v `(~b ~@r)))
    []))

(insert  < :less [1 6 7 4 3])

(let [a 1 b 3 c 3]  
`(~[a] ~@(if (= b 2) [333] nil) ~c))

(def ^{:private true} __ insert)

;; Solution is:
(def ^{:private true} __
  (fn i [c v [a b & r]]
    (if a
      `(~a ~@(if b `(~@(if (c a b) [v] nil) ~@(i c v `(~b ~@r)))))
      [])))

;; Integer overflow on fib nbr...

(defn- insert [c v [a b & r]]
  (if a
    `(~a ~@(if b `(~@(if (c a b) [v] nil) ~@(lazy-seq (insert c v `(~b ~@r)))))) ;; (if (c a b) [v] nil)) ~@(insert c v `(~b ~@r)))
    []))

(insert  < :less [1 6 7 4 3])

(def ^{:private true} __
  (fn i [c v [a b & r]]
    (if a
      `(~a ~@(if b `(~@(if (c a b) [v] nil) ~@(lazy-seq (i c v `(~b ~@r))))))
      [])))


(and (= '(1 :less 6 :less 7 4 3) (__ < :less [1 6 7 4 3]))
     (= '(2) (__ > :more [2]))
     (= [0 1 :x 2 :x 3 :x 4]  (__ #(and (pos? %) (< % %2)) :x (range 5)))
     (empty? (__ > :more ()))
     (= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
   (take 12 (->> [0 1]
                 (iterate (fn [[a b]] [b (+ a b)]))
                 (map first) ; fibonacci numbers
                 (__ (fn [a b] ; both even or both odd
                       (= (mod a 2) (mod b 2)))
                     :same)))))