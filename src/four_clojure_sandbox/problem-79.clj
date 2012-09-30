 
(defn- c [i [r & s]]
  (if r
    (+ (r i) 
       (min
        (c i s)
        (c (inc i) s)))
    0))

(defn- f [[[a] & b]]
  (+ a (if b (min (f (map rest b))
                  (f b)) 0)))

((fn [[a b]] b) [ 0 1 2])

(def __ f)

(c 0 '([1]
         [2 4]
           [5 1 4]
             [2 3 4 5]))


(defn- tri-min-path [s]
  (c 0 s))

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

;; Another solution:

(defn- l-r [n]
  (if (= n 1)
    [[0] [1]]
    (for [e [0 1] p (l-r (- n 1))]
      (cons e p))))

(fn f
  ([t] (f 0 t))
  ([i [r & t]]
    (+ (r i)
       (if t
         (min (f i t) (f (inc i) t))
         0))))

(pprint (l-r 4))

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