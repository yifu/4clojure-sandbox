(ns four-clojure-sandbox.core.problem-104)

(defn- roman-nat [n]
  (str
   ({1 "M" 2 "MM" 3 "MMM" 4 "MMMM" 5 "D" 6 "DC" 7 "DCC" 8 "DCCC" 9 "CM"} (mod (quot n 1000) 10))
   ({1 "C" 2 "CC" 3 "CCC" 4 "CD" 5 "D" 6 "DC" 7 "DCC" 8 "DCCC" 9 "CM"} (mod (quot n 100) 10))
   ({1 "X" 2 "XX" 3 "XXX" 4 "XL" 5 "L" 6 "LX" 7 "LXX" 8 "LXXX" 9 "XC"} (mod (quot n 10) 10))
   ({1 "I" 2 "II" 3 "III" 4 "IV" 5 "V" 6 "VI" 7 "VII" 8 "VIII" 9 "IX"} (mod n 10))))

(roman-nat 1)

(def __ (fn [n]
  (str
   ({1 "M" 2 "MM" 3 "MMM" 4 "MMMM" 5 "D" 6 "DC" 7 "DCC" 8 "DCCC" 9 "CM"} (mod (quot n 1000) 10))
   ({1 "C" 2 "CC" 3 "CCC" 4 "CD" 5 "D" 6 "DC" 7 "DCC" 8 "DCCC" 9 "CM"} (mod (quot n 100) 10))
   ({1 "X" 2 "XX" 3 "XXX" 4 "XL" 5 "L" 6 "LX" 7 "LXX" 8 "LXXX" 9 "XC"} (mod (quot n 10) 10))
   ({1 "I" 2 "II" 3 "III" 4 "IV" 5 "V" 6 "VI" 7 "VII" 8 "VIII" 9 "IX"} (mod n 10)))))

(and (= "I" (__ 1))
     (= "XXX" (__ 30))
     (= "IV" (__ 4))
     (= "CXL" (__ 140))
     (= "DCCCXXVII" (__ 827))
     (= "MMMCMXCIX" (__ 3999))
     (= "XLVIII" (__ 48)))