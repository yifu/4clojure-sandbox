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