(ns day-007)

;;;; Project Euler #3 - Largest Prime Factor

;;; By listing the first six prime numbers: 2, 3, 5 , 7, 11, and 13, we can see that the 6th prime is 13.
;;;
;;; What is the 10,001st prime number?

;;; Need a lazy seq of prime numbers

(defn prime? [n]
  (if (or (= n 2) (= n 3))
    true
    (and (pos? n)
         (not (even? n))
         ;; Check for divisibility by all integers less than or equal to sqrt(n)
         (not-any? (fn [x]
                     (zero? (mod n x)))
                   (range 3 (-> n Math/sqrt int inc) 2)))))

(def primes (filter prime? (iterate inc 2))) ; Probable primes lazy-sequence
(defn nth-prime [n] ; Adjust since nth is zero-based
  (nth primes (dec n)))

(comment
  (let [n 10001]
    (println (nth-prime n)))
  )
