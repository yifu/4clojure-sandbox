(ns four-clojure-sandbox.problem-114)

(defn- lazy-while
  ([n f s] (lazy-while n f s 1))
  ([n f s i] (lazy-seq (if (and (f (first s)) (= i n))
                         nil
                         (cons (first s) (lazy-while n f (rest s) (if (f (first s)) (inc i) i)))))))

(lazy-while 4 #(= 2 (mod % 3))
            [2 3 5 7 11 13 17 19 23])

(def ^{:private true} __ lazy-while)

;; Solution is:

(def __ (fn w
          [n f [a & r]] (if (and (f a) (= n 1))
                            nil
                            (cons a (lazy-seq (w
                                               (if (f a) (dec n) n)
                                               f
                                               r))))))

;; Trying another (shorter) solution.

(def __ (fn [n f s]
          (first
           (reduce
            (fn [[[a & r :as m] n :as e] v]
              (do
                (prn m "|" e "|" v)
                (if (< n 1)
                  e
                 (if (f v)
                   (if (> n 1)
                     [(conj m v) (dec n)]
                     [m (dec n)])
                   (if (>= n 1)
                     [(conj m v) n]
                     e)))))
           [[] n]
           s))))
  
(__ 4 #(= 2 (mod % 3))
    [2 3 5 7 11 13 17 19 23])


(and (= [2 3 5 7 11 13]
        (__ 4 #(= 2 (mod % 3))
            [2 3 5 7 11 13 17 19 23]))
     (= ["this" "is" "a" "sentence"]
        (__ 3 #(some #{\i} %)
            ["this" "is" "a" "sentence" "i" "wrote"]))
     (= ["this" "is"]
        (__ 1 #{"a"}
            ["this" "is" "a" "sentence" "i" "wrote"])))
     
     