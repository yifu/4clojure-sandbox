(ns fourclojure-sandbox.problem-121)


((fn [map] (let [{a :a b :b} map] [ a "|" b] )) {:b 8 :a 16})
((fn [map] (let [{:syms [a  b]} map] [ a "|" b] )) '{b 8 a 16})
((fn [map] (let [{:syms [a  b]} map] (eval '(/ a b) ) )) '{b 8 a 16})

(let [a 4 b 2] (eval '(/ a b)))

(((fn [form] (fn [a b] ~form)) '(/ 16 8)) 2 3)

(eval '(let [a 18 b 6] '(/ a b)))

((eval (fn [a b] '(/ 18 6))) 1 2)

(( `(fn [a b] ~'(/ 18 6)) 1 2) )


( ((fn [form] `(~@form)) '(prn "Hello world")))

(eval (list 'let '[ 'a 16 'b 8 '] '(/ a b)))

(cons '(let [a 16 b 8]) '('(/ a b)))

(list '1 '2  '(3 4))
(1 2 (3 4))

(list 'let '[ 'a 16 'b 8 '] '(/ a b) )
(list 'let '[ a 16 b 8 ] '(/ a b) )
(list 'let `[ ~@(list 'a 16 'b 8) ] '(/ a b) )
((fn [s]
   (let [keys (keys s)
         {:syms (keys s)} s]
     (list 'let `[ ~@(list 'a ,a 'b ,b) ] '(/ a b) ))) '{a 16 b 8})

((fn [s]
   (let [keys (keys s)
         {:syms (keys s)} s]
     (/ a b))) '{a 16 b 8})

(keys '{a 16 b 3})

((fn [b]
   (let [s (vec (keys b))
         {:syms [a b x y]} b]
     (/ a b))) '{a 16 b 8})

(((fn [body]
    (fn [bindings]
      (eval (list 'let `[{:syms [a b ]} '{a 16 b 8}] body))
      ))
  '(/ a b))
 '{a 16 b 8})


(eval (list 'let `[{:syms [a b ]} '{a 16 b 8}] '(/ a b)))

(let [s '{a 16 b 8}] (eval '(let [{:syms [a b]} ~s] [a b])))

(let [s '{a 16 b 8}] (eval (list 'let (vector (array-map :syms '[a b]) '(quote {a 16 b 8}) ) '[a b] )))
(let [s '{a 16 b 8}] (eval (list 'let (vector (array-map :syms '[a b]) '(quote s) ) '[a b] )))


(eval (list 'let (vector 'a 16 'b 8) '[a b]))
(let [{:syms [a b]} '{a 16, b 8}] [a b])
(array-map :syms [a b])

(vector 1 2 3)
(doc map )

;; HERE !!!!
(eval (let [s '{a 16 b 8}] (list 'let (vector (array-map :syms '[a b]) (list 'quote s)) '(/ a b))))

((fn [body] (eval (let [s '{a 16 b 8}] (list 'let (vector (array-map :syms '[a b]) (list 'quote s)) body)))) '(/ a b))

((fn [bindings]
   ((fn [body]
      (eval (let [s bindings] (list 'let (vector (array-map :syms '[a b x y]) (list 'quote s)) body))))
    '(/ a b)))
 '{a 16 b 8})

(defn- compute [body]
  (fn [bindings]
    (eval (let [s bindings] (list 'let (vector (array-map :syms '[a b x y]) (list 'quote s)) body)))))

((compute '(/ a b)) '{a 16 b 8} )

(def ^{:private true} __ compute)

;; Solution is :
(((fn [b] (fn [s] (eval (list 'let (vector (array-map :syms '[a b x y]) (list 'quote s)) b)))) '(/ a b)) '{a 16 b 8})

(and (= 2 ((__ '(/ a b))
           '{b 8 a 16}))
     (= 8 ((__ '(+ a b 2))
           '{a 2 b 4}))
     (= [6 0 -4]
        (map (__ '(* (+ 2 a)
                     (- 10 b)))
             '[{a 1 b 8}
               {b 5 a -2}
               {a 2 b 11}]))
     (= 1 ((__ '(/ (+ x 2)
                   (* 3 (+ y 1))))
           '{x 4 y 1})))