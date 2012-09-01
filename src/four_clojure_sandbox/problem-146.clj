(ns four-clojure-sandbox.core)

(for [y {:p 1, :q 2}]
  (prn (second y) "|" (first y) ))

(defn myfun [m]
  (for [x m
        y (second x)]
      (prn (first x) "|" y)))
(myfun '{a {p 1, q 2}})

(def __ (fn tree-walk [m]
          (into {}
                (for [x m
                      y (second x)]
                  [[(first x) (first y)] (second y)]))))

(__ '{a {p 1, q 2}
      b {m 3, n 4}})


(and (= (__ '{a {p 1, q 2}
         b {m 3, n 4}})
   '{[a p] 1, [a q] 2
     [b m] 3, [b n] 4})
     
     (= (__ '{[1] {a b c d}
         [2] {q r s t u v w x}})
   '{[[1] a] b, [[1] c] d,
     [[2] q] r, [[2] s] t,
     [[2] u] v, [[2] w] x})
     
     (= (__ '{m {1 [a b c] 3 nil}})
   '{[m 1] [a b c], [m 3] nil}))