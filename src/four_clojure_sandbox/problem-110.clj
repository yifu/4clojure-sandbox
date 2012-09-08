(ns four-clojure-sandbox.problem-110)

(defn- pronounce [s]
  (reduce (fn [s k] (conj s (count k) (first k))) [] (partition-by identity s)))

(defn- pronounce [s]
  (reduce (fn [s [k & _ :as m]] (conj s (count m) k)) [] (partition-by identity s)))

(defn- pronounce [s]
  (flatten (map #(vector (count %) (first %)) (partition-by identity s))))

(defn- pronounce [s]
   (for [[k v] (map frequencies  (partition-by identity s))] (prn k v)))

(flatten {1 2} {2 3})
(pronounce [1])
(pronounce [1 2 1 1])
(pronounce [1 1 1 4 4])

(defn- pronounce* [s] (iterate pronounce (pronounce s)))

(take 5 (pronounce* [1]))
(take 10 (pronounce* [1]))
(def ^{:private true} __ pronounce*)

;;Solution is"
(def __
  (fn [s]
    (let [f (fn [s] (reduce (fn [s k] (conj s (count k) (first k))) [] (partition-by identity s)))]
      (iterate f (f s)))))

(and (= [[1 1] [2 1] [1 2 1 1]] (take 3 (__ [1])))
     (= [3 1 2 4] (first (__ [1 1 1 4 4])))
     (= [1 1 1 3 2 1 3 2 1 1] (nth (__ [1]) 6))
     (= 338 (count (nth (__ [3 2]) 15))))

