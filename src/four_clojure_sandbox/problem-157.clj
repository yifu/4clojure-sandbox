(ns four-clojure-sandbox.core)

(def __ (fn [s] (map-indexed (fn [a b] [b a]) s)))

(def __ (fn [s] (map-indexed #([%2 %]) s)))

(__ [:a :b :c])

(and (= (__ [:a :b :c]) [[:a 0] [:b 1] [:c 2]])
     (= (__ [0 1 3]) '((0 0) (1 1) (3 2)))
     (= (__ [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]]))