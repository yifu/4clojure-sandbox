(ns four-clojure-sandbox.problem-115)

(defn- count-digits [n]
  (if (zero? n)
    1
    ((fn c [n]
       (if (zero? n)
         0
         (inc (c (quot n 10)))))
     n)))

(defn- seq-digits [n]
  (if (= n 0)
    []
    (conj (seq-digits (quot n 10)) (mod n 10))))

(seq-digits 0)
(seq-digits 1)
(seq-digits 11)
(seq-digits 121)
(seq-digits 126)
(seq-digits 88099)

(int (/ 5 2))
(quot 5 2)

(defn- balanced? [x]
  (let [s (seq-digits x)
        n (/ (count s) 2)]
    (= (apply + (take n s)) (apply + (take-last n s)))))

(balanced? 0)
(balanced? 1)
(balanced? 11)
(balanced? 121)
(balanced? 126)
(balanced? 0)
(balanced? 88099)
(balanced? 89098)
(balanced? 89089)

(def ^{:private true} __ balanced?)

;; Solution is :
(def __ (fn [x]
          (let [s ((fn f [n] (if (= n 0)
                            []
                            (conj (f (quot n 10)) (mod n 10)))) x)
                n (/ (count s) 2)]
            (= (apply + (take n s)) (apply + (take-last n s))))))

(and (= true (__ 11))
     (= true (__ 121))
     (= false (__ 123))
     (= true (__ 0))
     (= false (__ 88099))
     (= true (__ 89098))
     (= true (__ 89089))
     (= (take 20 (filter __ (range)))
        [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101]))
