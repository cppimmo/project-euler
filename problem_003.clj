(ns day-003)

;;;; Project Euler #3 - Largest Prime Factor

;;; The prime factors of 13195 are 5, 7, 13, and 29.
;;;
;;; What is the largest prime factor of the number 600851475143?

;;; Need a lazy seq of prime numbers
;;; Need evenly-divisible? predicate

(defn evenly-divisible?
  [n div-by]
  (zero? (mod n div-by)))

(defn sieve
  ([numbers]
   (letfn [(remove-multiples [n nums]
             (filter #(or (not= 0 (mod % n)) (= % n)) nums))]
     (if (empty? numbers)
       '()
       (cons (first numbers)
             (lazy-seq
              (sieve (remove-multiples (first numbers) (rest numbers))))))))
  ([]
   (sieve (iterate inc 2))))

(def prime-seq (sieve))

(comment
  (let [num 600851475143]
    (apply max (filter #(evenly-divisible? num %)
                       (take-while #(<= % num) prime-seq)))))

