(ns problem-016)

(defn digits
  "Convert a number to a seq of its digits."
  [n]
  (map #(Character/digit % 10) (str n)))

(defn big-pow
  "Raise base to the power of power. Arguments are converted to bigint's."
  [base power]
  (reduce *
          (take (bigint power) ; Create lazy-seq that just returns the base over & over
                (repeatedly #(identity (bigint base))))))

(defn sum-digits
  "Sum the digits in the number n."
  [n]
  (reduce + (digits n)))

(def solve (comp sum-digits (partial big-pow 2))) ; Same base used for example & solution

(comment ; Example case:
  (assert (= (solve 15) 26)))

(println "Answer:" (solve 1000))

