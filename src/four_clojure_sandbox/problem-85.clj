(ns four-clojure-sandbox.problem-85)

(comment (defn- add-elt [s e]
  (clojure.set/union
   (map conj s (repeat e))
   s
   #{#{e}})))
;   (set (map #(conj % e) s))))
;   (set (for [x s] (conj x e)))))

;; Improved add-elt function is:
;;(defn- add-elt [s e]
(map conj #{#{3} #{4} #{5}} (repeat 1))
(apply conj #{#{1 3} #{1 4} #{1 5}} #{3 4 5})
(apply conj #{#{} #{1 2} #{1} #{2}} [#{3} #{1 2 3} #{1 3} #{2 3}]) ;; HERE
(conj [#{1 3} #{1 4} #{1 5}] #{3 4 5})

(defn- add-elt [s e]
  (apply conj
         s
         (map
          conj
          s
          (repeat e))))
   
(add-elt #{#{} #{2} #{3} #{4}} 1)
(add-elt #{#{}} 1)


(defn- power-set [s]
   (reduce #(add-elt % %2) #{#{}} s))

(power-set #{1 :a})
(power-set #{})
(power-set #{1 2 3})
(count (power-set (into #{} (range 10))))

(def ^{ :private true } __
  power-set)

;; Solution is:
(def ^{:private true}  __
  (fn [s]
    (conj
     (reduce
      #(clojure.set/union (map conj % (repeat %2)) % #{#{%2}})
      #{}
      s)
     #{})))

;; Second improvement step:
(def ^{:private true} __
  (fn [s] (reduce
           #(apply conj % (map conj % (repeat %2)))
           #{#{}}
           s)))
;; Since 4clojure performs a string/replace, even better is :
reduce #(apply conj % (map conj % (repeat %2))) #{#{}}


(and (= (__ #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})
     (= (__ #{}) #{#{}})
     (= (__ #{1 2 3})
        #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
     (= (count (__ (into #{} (range 10)))) 1024))
