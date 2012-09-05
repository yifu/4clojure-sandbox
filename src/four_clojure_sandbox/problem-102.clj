(ns four-clojure-sandbox.core)


(clojure.string/replace "multi-word-key" #"-([a-z])" "X")

(clojure.string/replace "multi-word-key" #"-[a-z]" #(clojure.string/upper-case (str %)))


(clojure.string/replace "multi-word-key" #"-[a-z]" #(let [r (str (char (- (int (first %)) 32 )))]
                                                      (prn r) r))


(str (char ( -  (int (first "word")) 32)))

(+ 32 (int \W))

(clojure.string/replace "multi-word-key" #"(-)([a-z])" #(str (clojure.string/upper-case %2)))
(clojure.string/replace "multi-word-key" #"-[a-z]" #(str (+ %2 32)))
(clojure.string/replace "multi-word-key" #"(-)([a-z])" $2)
(clojure.string/replace "multi-word-key" #"-([a-z])" #(str %))


( -  (int \a) (int \A))

(+ 1 2)



;; Solution is:
(def __ (fn [s] (clojure.string/replace s #"-[a-z]" #(clojure.string/upper-case (subs % 1 2)))))

(and (= (__ "something") "something")
     (= (__ "multi-word-key") "multiWordKey")
     (= (__ "leaveMeAlone") "leaveMeAlone"))
