(ns four-clojure-sandbox.core)

(def __ #(apply merge-with + (for [x %] (assoc {} x 1))))
(def __ #(apply merge-with + (for [x %] {x 1})))

(and (= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
     (= (__ [:b :a :b :a :b]) {:a 2, :b 3})
     (= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))