(ns four-clojure-sandbox.problem-93)

(defn- depth [s]
  (if (not (coll? s))
    0
    (inc (apply max (map depth s)))))

(depth [1 2 3])
(depth [[1 2] [2 3] [3 4]])
(depth [1 [4 5 6 [4 [5]]] [2 [ 3]]])

(defn- flatten-1 [s]
  (if (= 1 (depth s))
    [s]
    (reduce #(apply conj % (flatten-1 %2)) [] s)))

(flatten-1 [1 2 3])
(flatten-1 [[1 2] [2 3] [3 4]])
(flatten-1 [[[1 2] [2 3] [3 4]]])

(def ^{:private true} __ flatten-1)

;; Solution is:
(def __ (fn f [s]
          (let [d (fn d [s] (if (not (coll? s)) 0 (inc (apply max (map d s)))))]
            (if (= 1 (d s))
              [s]
              (reduce #(apply conj % (f %2)) [] s)))))


(and (= (__ [["Do"] ["Nothing"]])
        [["Do"] ["Nothing"]])
     (= (__ [[[[:a :b]]] [[:c :d]] [:e :f]])
        [[:a :b] [:c :d] [:e :f]])
     (= (__ '((1 2)((3 4)((((5 6)))))))
        '((1 2)(3 4)(5 6))))
