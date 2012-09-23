
(defn- tramp [f]
  (fn t [a & r]
    (if (ifn? (f a))
      (apply t (tramp (f a)) r)
      (f a))))

(((((fn [a]
      (fn [b]
        (fn [c]
          (fn [d]
            (+ a b c d))))) 1  ) 2) 3) 4 )

(defn- decurry [f]
  (fn [& a] (reduce #(% %2) f a)))


(def __ decurry)



(and (= 10 ((__ (fn [a]
                  (fn [b]
                    (fn [c]
                      (fn [d]
                        (+ a b c d))))))

            1 2 3 4))
     (= 24 ((__ (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (* a b c d))))))
       1 2 3 4))

     (= 25 ((__ (fn [a]
             (fn [b]
               (* a b))))
       5 5)))