
(defn- create-paths [i [row & rows]]
  (if row
    (+ (row i) 
       (let [d #(create-paths % rows)]
         (min
          (d i)
          (d (inc i)))))
    0))

(create-paths 0 '([1]
                  [2 4]
                    [5 1 4]
                      [2 3 4 5]))


(defn- tri-min-path [s]
  (create-paths 0 s))

(tri-min-path '([1]
                  [2 4]
                    [5 1 4]
                      [2 3 4 5]) )

(tri-min-path '([3]
      [2 4]
        [1 9 3]
          [9 9 2 4]
            [4 6 6 7 8]
              [5 7 3 5 1 4]))

(and (= 7 (__ '([1]
                  [2 4]
                    [5 1 4]
                      [2 3 4 5]))) ; 1->2->1->3

     (= 20 (__ '([3]
                   [2 4]
                     [1 9 3]
                       [9 9 2 4]
                         [4 6 6 7 8]
                           [5 7 3 5 1 4]))) ; 3->4->3->2->7->1
     )