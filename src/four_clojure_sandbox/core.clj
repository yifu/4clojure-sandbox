(ns four-clojure-sandbox.core)

;; Pascal's Trapezoid. #147.
(next-pascal [3 1 2])
(next-pascal [1])
(next-pascal [1 1])
(next-pascal [1 2 1])
(next-pascal [3 3 1])

(def mytest (fn [[  :as m]]
              (prn (comment a "|" rest) "|" m)))
           

(def next-pascal (fn next-pascal [[a & rest]]
                   (if (nil? rest)
                     (list a)
                     (cons (bigint (+ a (first rest))) (next-pascal rest)))))

(take 5 (iterate #(cons (first %) (next-pascal %)) [1]))

(def __ (fn [r] (iterate #(cons (first %) (next-pascal %)) r)))

(take 100 (__ [2 4 2]))

(def __ (fn [r]
          (let [next-pascal (fn next-pascal [[a & rest]]
                              (if (nil? rest)
                                (list a)
                                (cons (bigint (+ a (first rest))) (next-pascal rest))))]
            (iterate #(cons (first %) (next-pascal %)) r))))
                
(and (= (second (__ [2 3 2])) [2 5 5 2])
     (= (take 5 (__ [1])) [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])
     (= (take 2 (__ [3 1 2])) [[3 1 2] [3 4 3 2]])
     (= (take 100 (__ [2 4 2])) (rest (take 101 (__ [2 2])))))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
