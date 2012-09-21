(ns fourclojure-sandbox.problem-112)


(require '[clojure.zip :as zip])
(def data '[[a * b] + [c * d]])
(def dz (zip/vector-zip data))
(zip/path (zip/next (zip/next (zip/next dz))))
(zip/path (zip/next dz))

(loop [z dz]
  (if (zip/end? z)
    (prn "end")
    (do
      (prn (zip/node z) "|" (zip/path (zip/node z)))
      (recur (zip/next z)))))


(loop [loc dz]
  (if (zip/end? loc)
    (zip/root loc)
    (recur
     (zip/next
      (if (= (zip/node loc) '*)
        (zip/replace loc '/)
        loc)))))

(def  __
  (fn [a s]
    (loop [n a z (zip/vector-zip s)]
     (do
       (prn n "|" (number? (zip/node z)) "|" (zip/node z))
       (cond
        (zip/end? z) (zip/root z)
        (neg? n) (zip/root (zip/remove z))
        1 (recur         
           (if (number? (zip/node z))
             (- n (zip/node z))
             n)
           (zip/next
            (if (or (neg? n) (and (number? (zip/node z)) (neg? (- n (zip/node z)))))
              (zip/remove z)
              z))))))))


(and (=  (__ 10 [1 2 [3 [4 5] 6] 7])
         '(1 2 (3 (4))))
     (=  (__ 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
         '(1 2 (3 (4 (5 (6 (7)))))))
     (=  (__ 9 (range))
         '(0 1 2 3))
     (=  (__ 1 [[[[[1]]]]])
         '(((((1))))))
     (=  (__ 0 [1 2 [3 [4 5] 6] 7])
         '())
     (=  (__ 0 [0 0 [0 [0]]])
         '(0 0 (0 (0))))
     (=  (__ 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
         '(-10 (1 (2 3 (4))))))