(ns four-clojure-sandbox.core)

(defn __ [a]
  )

(map (fn [a] (if (associative? a)
               (if (= (empty a) [])
                 :vector
                 :map)
               :other)) [{} #{} [] ()])

(def __ (fn [a]
          (let [b (empty a)]
            (cond
             (and (= [] b) (associative? b)) :vector
             (= '() b) :list
             (= {} b) :map
             (= #{} b) :set
             ))))


((conj [] [:a 1]) :a)
(= '() (empty '(1 2)))
(= '() [])
(sorted? [])
(sorted? {})
(concat {1 2 } { 3 4})
(= (seq {1 2}) [1 2])
(= (seq  [:a 1, :b 2] ) [:a 1, :b 2])

(seq [])
(assoc {} 1 2)
(conj [] 1 2)
(conj {} 1 )
(assoc [] 1 343)
(assoc {} 1 343)

(and (= :map (__ {:a 1, :b 2}))
     (= :list (__ (range (rand-int 20))))
     (= :vector (__ [1 2 3 4 5 6]))
     (= :set (__ #{10 (rand-int 5)}))
     (= [:map :set :vector :list] (map __ [{} #{} [] ()])))
