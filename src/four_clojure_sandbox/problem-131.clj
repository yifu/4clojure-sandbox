
(defn- sums [s]
  (set (for [x s y s] (+ x y))))

(defn- equi-sum [& r]
  (do
    (prn r)
    (not (empty?
          (apply clojure.set/intersection
                 (for [s r] (do (prn s (sums s)) (sums s))))))))

(defn- equi-sum [& r]
  (every?
   true?
   (for [x r y r] (not (empty? (clojure.set/intersection (sums x) (sums y)))))))

(equi-sum #{-1 1 99}
          #{-2 2 888}
          #{-3 3 7777})

(equi-sum #{1}
          #{2}
          #{3}
          #{4})

(def __ equi-sum)

(and (= true  (__ #{-1 1 99}
                  #{-2 2 888}
                  #{-3 3 7777})) ; ex. all sets have a subset which sums to zero

     (= false (__ #{1}
             #{2}
             #{3}
             #{4}))

     (= true  (__ #{1}))

     (= false (__ #{1 -3 51 9}
             #{0}
             #{9 2 81 33}))

     (= true  (__ #{1 3 5}
             #{9 11 4}
             #{-3 12 3}
             #{-3 4 -2 10}))

     (= false (__ #{-1 -2 -3 -4 -5 -6}
                  #{1 2 3 4 5 6 7 8 9}))

     (= true  (__ #{1 3 5 7}
                  #{2 4 6 8}))

     (= true  (__ #{-1 3 -5 7 -9 11 -13 15}
             #{1 -3 5 -7 9 -11 13 -15}
             #{1 -1 2 -2 4 -4 8 -8}))

     (= true  (__ #{-10 9 -8 7 -6 5 -4 3 -2 1}
             #{10 -9 8 -7 6 -5 4 -3 2 -1}))

     )