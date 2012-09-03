(ns four-clojure-sandbox.core)

(def __ (fn [n l]
          (for [i (range (quot (count l) n))]
            (take n (drop (* i n) l)))))

;; Solution is:
#(for [i (range (quot (count %2) %))]
            (take % (drop (* i %) %2)))

(and (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
     (= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
     (= (__ 3 (range 8)) '((0 1 2) (3 4 5))))
