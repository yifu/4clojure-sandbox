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

(def __
  (fn [n s]
    (loop [n n z (zip/vector-zip s)]
      (if (or (zip/end? z) (neg? n))
        (zip/path z)
        (recur (if (number? (zip/node z)) (- n (zip/node z)) n ) (zip/next z))))))

(defn get-n [s]
  (if (empty? s)
    0
    (if (number? (first s))
      (+ (first s) (get-n (rest s)))
      (+ (get-n (first s)) (get-n (rest s))))))

(get-n [1 2 3])

(def __
  (fn f [n s]
    (if (empty? s)
      '()
      (if (number? (first s))
        (if (<= 0 (- n (first s)))
          (cons (first s) (f (- n (first s)) (rest s)))
          '())
        (cons (f n (first s)) (f (- n (get-n (first s))) (rest s)))))))

(defn get-n [s]
  (if (number? s)
    s
    (if-let [[a & r] s]
      (+ (get-n a) (get-n r))
      0)))

(get-n 3)
(get-n [1 2 3])
(get-n [[1 [ 2 ]] 3])

(def __
  (fn f [n [a & r]]
    (let [g (fn g [s] (if (number? s) s (if-let [[a & r] s] (+ (g a) (g r)) 0)))]
     (if a
       (if (number? a)
         (if (neg? (- n a)) '() (cons a (f (- n a) r)))
         (cons (f n a) (f (- n (g a)) r)))
       '()))))

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