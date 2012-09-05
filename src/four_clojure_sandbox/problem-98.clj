(ns four-clojure-sandbox.problem-98)

(= (set (map #(set (val %)) (group-by #(* % %) #{-2 -1 0 1 2}))) #{#{0} #{1 -1} #{2 -2}})

;;(map (fn [[_ v]] #{v})  (group-by #(* % %) #{-2 -1 0 1 2}))
;;#{[1 2 3]}
;;(set [1 2 3])
;;(clojure.set/union #{} #{1 2 3})

(def ^{ :private true }  __
  (fn [f d]
    (set (map #(set (val %)) (group-by f d)))))

(and (= (__ #(* % %) #{-2 -1 0 1 2}) #{#{0} #{1 -1} #{2 -2}})
     (= (__ #(rem % 3) #{0 1 2 3 4 5 }) #{#{0 3} #{1 4} #{2 5}})
     (= (__ identity #{0 1 2 3 4}) #{#{0} #{1} #{2} #{3} #{4}})
     (= (__ (constantly true) #{0 1 2 3 4}) #{#{0 1 2 3 4}}))
