(ns four-clojure-sandbox.core)

(defn digits [n]
  (if (zero? n)
    '(0)
    (cons (mod n 10) (digits (quot n 10)))))

(defn happiness-formula-1-step [n]
  (apply + (map #(* % %) (digits n))))

(defn hapiness-aux [n s]
  (if (= n 1)
    true
    (if (s n)
      false
      (recur (happiness-formula-1-step n) (conj s n)))))

(defn hapiness [n] (hapiness-aux n #{}))

(def __ hapiness)

;; Solution is:
(def __ (fn [n]
          (let [d (fn d [n] (if (zero? n) [0] (conj (d (quot n 10)) (mod n 10))))
                f (fn [n] (apply + (map #(* % %) (d n))))
                h (fn [n s] (if (= n 1)
                              true
                              (if (s n)
                                false
                                (recur (f n) (conj s n)))))]
            (h n #{}))))

;; Improved solution:
;(def d (fn d [n] (if (zero? n) [0] (conj (d (quot n 10)) (mod n 10)))))
(defn d [n] (for [c (str n)] (- (int c) 48)))
(defn f [n] (apply + (map #(* % %) (d n))))

(defn f [n] (reduce #(+ % (* (- (int %2) 48) (- (int %2) 48))) 0 (str n)))

(def h (fn [n s]
         (let [a (= 1 n) b (s n)]
           (if a
             a
             (if b
               (not b)
               (h (f n) (conj s n)))))))

(defn hapiness [n] (h n #{}))
(hapiness 7)
(hapiness 2)


;; Improved solution:
(def __ (fn [n]
          (let [f (fn [n]  (reduce #(+ % (* (- (int %2) 48) (- (int %2) 48))) 0 (str n)))
                h (fn h [n s]
                    (let [a (= 1 n) b (s n)]
                      (if a
                        a
                        (if b
                          (not b)
                          (h (f n) (conj s n))))))]
            (h n #{}))))

(and (= (__ 7) true)
     (= (__ 986543210) true)
     (= (__ 2) false)
     (= (__ 3) false))