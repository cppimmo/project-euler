(ns day-020)

;;;; Project Euler #20 - Factorial Digit Sum

;;; n! means n x (n - 1) x ... x 3 x 2 x 1.
;;;
;;; For example, 10! = 10 x 9 x ... x 3 x 2 x 1 = 3628800,
;;; and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
;;;
;;; Find the sum of the digits in the number 100!.

(defn factorial
  ([n] ; Linear iterative version
   (loop [product (bigint 1) ; Use bitint's to prevent long overflow
          counter (bigint 1)]
     ;;(println product counter n)
     (if (> counter n)
       product
       (recur (* counter product)
              (inc counter)))))
  ([n garbo-arg]                        ; Linear recursive version
   (if (= n 0)
     1
     (* n (factorial (dec n))))))

(defn number->digits ; https://stackoverflow.com/a/29942388
  [n]
  (->> n
       str
       (map (comp read-string str))))

(defn sum-factorial-digits
  [n]
  (reduce + (number->digits (factorial n))))

(comment
  (assert (= (sum-factorial-digits 10) 27)))

(println "Answer:"
         (sum-factorial-digits 100))

