(ns day-014)

(defn collatz-seq
  [n]
  (loop [num n ; Value of n on each iteration
         seq (list n)] ; The sequence to build up
    (cond
      (= num 1) seq ; Assume all Collatz sequences finish at 1
      :else
      (let [new-num (if (even? num)
                      (/ num 2) ; Even
                      (+ (* 3 num) 1))] ; Odd
        (recur new-num (conj seq new-num))))))

;;; Resulting sequences are not nearly as long as they should be?

(comment ; Test case works
  (assert (= (count (collatz-seq 13)) 10)))

;;; Currently incorrect
(println "Answer:"
         (apply max (map #(count (collatz-seq %))
                         (range 1 1000000))))
