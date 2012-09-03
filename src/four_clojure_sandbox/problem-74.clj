(ns four-clojure-sandbox.core)
(require 'clojure.string)

(def __ (fn [s]
          (clojure.string/replace
           (clojure.string/replace 
            (pr-str
             (keep 
              (fn [i]
                (if
                    (some
                     #(= i (* % % ))
                     (range 0 i))
                  i
                  nil))
              (map read-string (clojure.string/split s #","))))
            #" "  "," )
           #"[()]" "")))
 
(read-string "4,5,6,7,8,9")

(and (= (__ "4,5,6,7,8,9") "4,9")
     (= (__ "15,16,25,36,37") "16,25,36"))
