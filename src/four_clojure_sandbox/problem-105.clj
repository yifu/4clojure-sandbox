(ns four-clojure-sandbox.problem-105)

(filter keyword? [:a 1 2 3 :b :c 4])
(split-with keyword? [:a 1 2 3 :b :c 4])
(split-with number? [1 2 3 :b :c 4])
(split-with number? [:c 4])
(let [[k & rest] [:a 1 2 3 :b :c 4]]
  (prn k "|" rest)

(defn- identify-k-v [s]
  (if (empty? s)
    {}
    (let [[k & rest] s
          [n r] (split-with number? rest)]
      (assoc (identify-k-v r) k n))))

(identify-k-v [])
(identify-k-v [:a 1 2 3 :b :c 4])

(def ^{:private true} __
  identify-k-v)

;; Solution is:
(def ^{:private true} __
  (fn f[s]
    (if (empty? s)
      {}
      (let [[k & r] s
            [v n] (split-with number? r)]
        (assoc (f n) k v)))))

((fn f[[k & r]]
   (if r
     (prn "ok")
     (prn k "|" r "|" "empty"))) [])

(and (= {} (__ []))
     (= {:a [1]} (__ [:a 1]))
     (= {:a [1], :b [2]} (__ [:a 1, :b 2]))
     (= {:a [1 2 3], :b [], :c [4]} (__ [:a 1 2 3 :b :c 4])))