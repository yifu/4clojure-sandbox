
(defn- tricky-cg [trump-suit]
  (fn [lead & rest :as tricks]
    (first (sort-by (juxt :suit :rank) tricks))))


(sort-by (juxt :suit :rank) [ {:suit :club :rank 4} {:suit :club :rank 9}])
(filter #(#{:heart :club}  (:suit %))
        (sort-by (juxt :suit  :rank) [{:suit :heart :rank 2}
                                      {:suit :spade :rank 10}
                                      {:suit :heart :rank 4}
                                      {:suit :heart :rank 3}
                                      {:suit :spade :rank 3}
                                      {:suit :club :rank 1}]))

(let [lead {:suit :heart :rank 2}
      trump {:suit nil :rank 0 }
      tricks  [{:suit :heart :rank 2}
               {:suit :spade :rank 10}
               {:suit :heart :rank 4}
               {:suit :heart :rank 3}
               {:suit :spade :rank 3}
               {:suit :club :rank 1}]]
 (let [w (fn [lead] (reduce #(if (= (:suit %) (:suit %2))
                               (if (< (:rank %) (:rank %2)) %2 %)
                               %) lead tricks))]
   (let [t (w trump) u (w lead)]
     (if (= 0 (:rank t))
       u t))))

(defn- tricky-cg [s]
  (fn [[ l & _ :as t]]
    (let [w (fn [l] (reduce #(if (= (:suit %) (:suit %2))
                                  (if (< (:rank %) (:rank %2))
                                    %2
                                    %)
                                  %) l t))
          t (w {:suit s :rank 0})]
      (if (= 0 (:rank t)) (w l) t))))



(defn- tricky-cg [s]
  (let [a :suit b :rank]
    (fn [[ l & _ :as t]]
      (let [w (fn [l] (reduce #(if (= (a %) (a %2))
                                 (if (< (b %) (b %2))
                                   %2
                                   %)
                                 %) l t))
            t (w {a s b 0})]
        (if (= 0 (b t)) (w l) t)))))


(def __ tricky-cg)

(and
 (let [notrump (__ nil)]
   (and

    (= {:suit :club :rank 9}  (notrump [{:suit :club :rank 4}
                                        {:suit :club :rank 9}]))
    
    (= {:suit :spade :rank 2} (notrump [{:suit :spade :rank 2}
                                        {:suit :club :rank 10}]))))

 (= {:suit :club :rank 10} ((__ :club) [{:suit :spade :rank 2}
                                        {:suit :club :rank 10}]))

 (= {:suit :heart :rank 8}
    ((__ :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                  {:suit :diamond :rank 10} {:suit :heart :rank 4}]))

 )