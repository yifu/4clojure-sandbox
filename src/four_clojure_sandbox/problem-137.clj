(ns four-clojure-sandbox.problem-110)

(defn- convert-to-base-aux [n b]
  (if (= n 0)
    []
    (conj (convert-to-base-aux (quot n b) b) (mod n b))))

(defn- convert-to-base [n b]
  (if (= n 0)
    [0]
    (convert-to-base-aux n b)))

(convert-to-base  9 2)

(def ^{:private true} __ convert-to-base)

;; Solution is:
(def __ (fn [n b]
          (if (= n 0)
            [0]
            ((fn f[n b] (if (= n 0) [] (conj (f (quot n b) b) (mod n b)))) n b))))

(and (= [1 2 3 4 5 0 1] (__ 1234501 10))
     (= [0] (__ 0 11))
     (= [1 0 0 1] (__ 9 2))
     (= [1 0] (let [n (rand-int 100000)](__ n n)))
     (= [16 18 5 24 15 1] (__ Integer/MAX_VALUE 42)))