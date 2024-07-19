(ns day-007
  (:require [clojure.test :refer [deftest is testing]]))

;;;; Project Euler #3 - Largest Prime Factor

;;; By listing the first six prime numbers: 2, 3, 5 , 7, 11, and 13, we can see that the 6th prime is 13.
;;;
;;; What is the 10,001st prime number?

;;; Need a lazy seq of prime numbers

(declare prime? nth-prime primes)

(deftest test-primes
  (testing "..."
    (is (= (take 7 primes) (list 2 3 5 7 11 13 17)))))

(defn prime? [n]
  (cond
    (<= n 1) false
    (=  n 2) true
    :else (and (not (even? n))
               ;; Check for divisibility by all integers less than or equal to sqrt(n)
               (not-any? (fn [x]
                           (zero? (mod n x)))
                         (range 3 (-> n Math/sqrt Math/ceil inc) 2))))) ; [3, n)

(def primes (filter prime? (iterate inc 2))) ; Probable primes lazy seq
(defn nth-prime [n] ; Adjust since nth is zero-based
  (if (<= n 0)
    (throw (IllegalArgumentException.))
    (nth primes (dec n))))

(comment
  (let [n 10001]
    (println (nth-prime n)))
  )
