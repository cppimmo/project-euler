(ns day-005)

;;;; Project Euler #5 - Smallest Multiple

;;; 2520 is the smallest number that can be divided by each of the numbers from 1 to
;;; 10 without any remainder.
;;;
;;; What is the smallest positive number that is evenly divisible by all of the
;;; numbers from 1 to 20?

(defn gcd
  ""
  [a b]
  (if (zero? b)
    a
    (gcd b (rem a b))))

(defn lcm
  "Return least common multiple by using the greatest common divisor."
  [a b]
  (/ (Math/abs (* a b)) (gcd a b)))

(defn evenly-divisible?
  [n div-by]
  (zero? (mod n div-by)))

(defn smallest-multiple
  [nmin nmax]
  (letfn [(smallest? [n]
            (every? #(evenly-divisible? n %) (range nmin (inc nmax))))]
    (->> (rest (range))
         (filter smallest?)
         first)
    ))

