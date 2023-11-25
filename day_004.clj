(ns day-004)

;;;; Project Euler #4 - Largest Palindrome Product

;;; A palindromic number reads the same both ways. The largest palindrome made from the
;;; product of two 2-digit numbers is 9009 = 91 x 99.
;;;
;;; Find the largest palindrome made from the product of two 3-digit numbers.

(defn palindrome?
  [num]
  (let [num-str (str num)]
    (= num-str (clojure.string/join (reverse num-str)))))

(def pairs (for [x (range 100 1000)
                 y (range 100 1000)]
             (* x y)))

(defn largest-palindrome
  []
  (loop [remaining-pairs (reverse pairs)
         max-palindrome 0]
    (if (empty? remaining-pairs)
      max-palindrome
      (let [current-product (first remaining-pairs)
            remaining (rest remaining-pairs)]
        (if (and (palindrome? current-product)
                 (> current-product max-palindrome))
          (recur remaining current-product)
          (recur remaining max-palindrome))))))

(println "Answer:" (largest-palindrome))

