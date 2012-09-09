(ns four-clojure-sandbox.problem-144)

(take 10 (reductions #(%2 %) 3.14 (cycle [int double])))

(defn- oscil [n & f]
  (reductions #(%2 %) n (cycle f)))

(def ^{:private true} __ oscil)

;; Solution is:
(def __ (fn [n & f]
          (reductions #(%2 %) n (cycle f))))

(and (= (take 3 (__ 3.14 int double)) [3.14 3 3.0])
     (= (take 5 (__ 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7])
     (= (take 12 (__ 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3]))
