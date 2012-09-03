(ns four-clojure-sandbox.core)

(def __ (fn [& l]
          #(for [f l] (apply f %&))))

(and (= [21 6 1] ((__ + max min) 2 3 5 1 6 4))
     (= ["HELLO" 5] ((__ #(.toUpperCase %) count) "hello"))
     (= [2 6 4] ((__ :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))
