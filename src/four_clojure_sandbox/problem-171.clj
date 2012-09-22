
(defn- merge-ints [[ [a b :as m] [c d :as n] & r :as s]]
  (if (and m n)
    (if (> c (+ 1 b))
      (cons m (merge-ints (cons n r)))
      (merge-ints (cons [a d] r)))
    s))

(merge-ints [ [1 1] [1 2] [3 4] [4 4] [4 4] [6 9]])
(merge-ints  [ [1 2] [2 3] [8 9] [9 10]])

(defn __ [s]
  (let [m (fn e [[ [a b :as m] [c d :as n] & r :as s]]
            (if (and m n)
              (if (> c (+ 1 b))
                (cons m (e (cons n r)))
                (e (cons [a d] r)))
              s))]
    (m
     (map #(vec %&) (sort s) (sort s)))))

(map-indexed #()  (sort [10 9 8 1 2 3]))
(split-at 3 (sort [10 9 8  2 3 4]) )


(and (= (__ [1 2 3]) [[1 3]])
     (= (__ [10 9 8 1 2 3]) [[1 3] [8 10]])
     (= (__ [1 1 1 1 1 1 1]) [[1 1]])
     (= (__ []) [])
     (= (__ [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
       [[1 4] [6 6] [9 11] [13 17] [19 19]]))