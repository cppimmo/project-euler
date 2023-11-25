(ns day-017)

;;;; Project Euler #17 - Sum Square Difference

;;; If the numbers 1 to 5 are written out in words: one, two, three, four, five, then
;;; there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
;;;
;;; If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
;;; how many letters would be used?
;;;
;;; NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
;;; contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use
;;; of "and" when writing out numbers is in compliance with British usage.

(def single-digits
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"})

(def double-digits
  {10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   ;; No more special cases
   2 "twenty"
   3 "thirty"
   4 "forty"
   5 "fifty"
   6 "sixty"
   7 "seventy"
   8 "eighty"
   9 "ninety"})

(defn number->digits
  [n]
  (map #(Character/digit % 10) (str n)))

(defn two-digit-number->string
  [n]
  (let [digits (number->digits n)]
    (if (< n 20)
      (get double-digits n)
      (str (get double-digits (first digits))
           (when (not (zero? (second digits)))
             (str "-" (get single-digits (second digits))))))))

;;; TODO: Messy, needs cleanup.

(defn number->string
  [n]
  (let [digits (number->digits n)
        digit-count (count (str n))]
    (cond
      (= digit-count 1) (get single-digits n)
      (= digit-count 2) (two-digit-number->string n)
      ;; 
      (= digit-count 3) (str (get single-digits (first digits))
                             " hundred"
                             (when (not (zero? (mod n 100)))
                               (str " and "
                                    (if (< (mod n 100) 10)
                                      (get single-digits (mod n 100))
                                      (two-digit-number->string (mod n 100))))))
      (= digit-count 4) (str (get single-digits (first digits)) " thousand")
      :else (assert false "Digit count not supported."))))

(println
 "Answer: "
 (reduce (fn [acc elem]
           (+ acc (count (clojure.string/replace elem #"[ \-]" ""))))
         0
         (map number->string
              (range 1 (inc 1000)))))

;;(with-open [wtr (clojure.java.io/writer "coll.txt")]
;;  (binding [*out* wtr]
;;    (doseq [subseq (partition 10 (map number->string
;;                               (range 1 (inc 1000))))]
;;      (println subseq))))

