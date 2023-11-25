(ns day-006)

;;;; Project Euler #6 - Smallest Multiple

;;; The sum of the squares of the first ten natural numbers is,
;;;
;;; 1^2 + 2^2 + ... + 10^2 = 385.
;;;
;;; The square of the sum of the first ten natural numbers is,
;;;
;;; (1 + 2  + ... + 10)^2 = 55^2 = 3025.
;;;
;;; Hence the difference between the sum of the squares of the first ten natural
;;; numbers and the square of the sum is 3025 - 385 = 2640.
;;;
;;; Find the difference between the sum of the squares of the first one hundred natural
;;; numbers and the square of the sum.

(defn sum-of-squares
  "Sum the squares of the natural numbers between 1 and cnt (inclusive)."
  [cnt]
  (int (reduce (fn [acc n]
                 (+ acc (* n n)))
               (range 1 (inc cnt)))))

(defn square-of-sum
  "Square the sum of the natural numbers between 1 and cnt (inclusive)."
  [cnt]
  (let [sum (reduce + (range 1 (inc cnt)))]
    (* sum sum)))

(println "Answer:"
         (let [natural-number 100] ; Calculate the difference
           (- (square-of-sum natural-number)
              (sum-of-squares natural-number))))

