(ns four-clojure-sandbox.core)

(group-by identity [1 2 1 3 1 2 4])
(keys (group-by identity '([2 4] [1 2] [1 3] [1 3])))
(def __ #(keys (group-by identity %)))
(group-by identity (range 50))

(doc reduce)
(set [1 2 1 3 1 2 4])
( get [1 2 3 4 ] 1 )
(frequencies [1 2 1 3 1 2 4])

(.indexOf [] 2)
(#(if (nil? (.indexOf % %2)) (conj % %2)) [] 3)
(if (= -1 (.indexOf [3] 3)) (conj [3] 3) [2])
([5 5 55 5] 55)
(reduce #(if (= -1 (.indexOf % %2)) (conj % %2) %) [] [1 2 1 3 1 2 4])
(reduce (fn [l x] (if (some #(= x %) l) l (conj l x))) [] [1 2 1 3 1 2 4])

(def __ (fn [l] (reduce (fn [l x] (if (some #(= x %) l) l (conj l x))) [] l)))

;; solution is reduce (fn [l x] (if (some #(= x %) l) l (conj l x)))
;;[] 

(keep #(= % 2) [nil nil 2 2 ])


(and (= (__ [1 2 1 3 1 2 4]) [1 2 3 4])
     (= (__ [:a :a :b :b :c :c]) [:a :b :c])
     (= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
     (= (__ (range 50)) (range 50)))
