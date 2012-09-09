(ns four-clojure-sandbox.problem-108)

(defn- search-min [& colls]
  (if (apply = (for [c colls] (first c)))
    (ffirst colls)
    (let [ [[m & rest ] & colls] (sort-by first colls)]
      (apply search-min rest colls))))

(search-min [1 2 3])
(search-min [1 2 3] [1 4 5])
(search-min [1 2 3 4 5 6 7] [0.5 3/2 4 19])
(search-min (range) (range 0 100 7/6) [2 3 5 7 11 13])

(def ^{:private true} __ search-min)

(def __ #((fn f[[[a & r] & c]]
            (if (apply = a (map first c))
              a
              (f (sort-by first (conj c r)))))
          (sort-by first %&)))

(and (= 3 (__ [3 4 5]))
     (= 4 (__ [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
     (= 7 (__ (range) (range 0 100 7/6) [2 3 5 7 11 13]))
     (= 64 (__ (map #(* % % %) (range)) ;; perfect cubes
               (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
               (iterate inc 20)))) ;; at least as large as 20
