(ns four-clojure-sandbox.core)

(comment (def __ (fn c [f & r]
                   (if (empty? r)
                     #(apply f %&)
                     #(f (apply (apply c r) %&))))))

(comment def __ (fn [& f]
                  (let [[h & r] (reverse f)]
                    (fn [y & z]
                      (reduce
                       #(%2 %)
                       (apply h y z)
                       r)))))


(def __ (fn [& f]
          (let [[h & r] (reverse f)]
            (fn [& z]
              (reduce
               #(%2 %)
               (apply h z)
               r)))))

(and (= [3 2 1] ((__ rest reverse) [1 2 3 4]))
     (= 5 ((__ (partial + 3) second) [1 2 3 4]))
     (= true ((__ zero? #(mod % 8) +) 3 5 7 9))
     (= "HELLO" ((__ #(.toUpperCase %) #(apply str %) take) 5 "hello world")))