(ns fourclojure-sandbox.problem-103)

(for [x #{4 5 6}] #{x})

(defn- k-comb [n s]
  (cond
   (= n 0) #{}
   (= n 1) (set (for [x s] #{x}))
   1  (reduce clojure.set/union (for [x s] (set (map conj (k-comb (dec n) (disj s x)) (repeat x)))))))

(= #{#{4} #{5} #{6}} (k-comb 1 #{4 5 6}))
(k-comb 2 #{0 1 2})

(disj #{1 2 3} 3)
(conj [#{0 1 } #{0 2}] #{1 2} #{1 3})

(def ^{:private true} __ k-comb)



(and (= (__ 1 #{4 5 6}) #{#{4} #{5} #{6}})
     (= (__ 10 #{4 5 6}) #{})
     (= (__ 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})
     (= (__ 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                              #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})
     (= (__ 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})
     (= (__ 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                    #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}))