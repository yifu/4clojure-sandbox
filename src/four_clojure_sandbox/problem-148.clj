(ns fourclojure-sandbox.problem-148)

(defn- big-div [n a b]
  (-
   (+
    (reduce + (take-while #(< % n) (iterate #(+ a %) a)))
    (reduce + (take-while #(< % n) (iterate #(+ b %) b))))
   (reduce + (take-while #(< % n) (iterate #(+ (* a b) %) (* a b))))))


(big-div 10 3 5)
(big-div 1000 3 5)

(def ^{:private true} __ big-div)

(and (= 0 (__ 3 17 11))
     (= 23 (__ 10 3 5))
     (= 233168 (__ 1000 3 5))
     (= "2333333316666668" (str (__ 100000000 3 5)))
     (= "110389610389889610389610"
        (str (__ (* 10000 10000 10000) 7 11)))
     (= "1277732511922987429116"
        (str (__ (* 10000 10000 10000) 757 809)))
     (= "4530161696788274281"
        (str (__ (* 10000 10000 1000) 1597 3571))))