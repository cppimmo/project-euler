(ns day-001)

;;;; Project Euler #1 - Multiples of 3 or 5

;;; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get
;;; 3, 5, 6, and 9. The sum of these multiples is 23.
;;;
;;; Find the sum of all the multiples of 3 or 5 below 1000.

(defn multiple-of?
  "True if n is multiples of multiple-num, false otherwise."
  [n multiple-num]
  (zero? (mod n multiple-num)))

(defn multiples-of-3-or-5
  [max-num]
  (let [num-list (range max-num)]
    (reduce +
            (filter #(or (multiple-of? % 3)
                         (multiple-of? % 5))
                    num-list))))

(println "Answer:" (multiples-of-3-or-5 1000))

