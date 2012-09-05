(ns four-clojure-sandbox.core)

(defn merge-two-sets-with [op s1 s2]
  (reduce
   (fn [s [k v]] (assoc s k (if-let [w (get s k)] (op w v) v)))
   s1
   s2))

(* nil 4)

(defn merge-sets-with [op s1 & rest]
  (reduce #(merge-two-sets-with op % %2) s1 rest))

(def __ merge-sets-with)

;; Solution is:
(def __ (fn [o s & r]
          (reduce
           #(reduce
             (fn [s [k v]]
               (assoc
                   s k
                 (if-let [w (get s k)] (o w v) v)))
             % %2)
           s r)))


;; Solution improvement.
(def __ (fn [o s & r]
          (reduce
           #(reduce
             (fn [s [k v]]
               (assoc
                   s k
                 (if (s k) (o (s k) v) v)))
             % %2)
           s r)))


(and
  (= (__ * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5}) {:a 4, :b 6, :c 20})
  (= (__ - {1 10, 2 20} {1 3, 2 10, 3 15}) {1 7, 2 10, 3 15})
  (= (__ concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]}) {:a [3 4 5], :b [6 7], :c [8 9]}))
