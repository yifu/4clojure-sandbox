(ns four-clojure-sandbox.core)

(def __ (fn [s]
          (do
            (use 'clojure.string)
            (sort
             #(compare (lower-case %) (lower-case %2))
             (re-seq #"\w+" s)))))

;; Solution is:

 (and (= (__  "Have a nice day.")
         ["a" "day" "Have" "nice"])
      (= (__  "Clojure is a fun language!")
         ["a" "Clojure" "fun" "is" "language"])
      (= (__  "Fools fall for foolish follies.")
        ["fall" "follies" "foolish" "Fools" "for"]))