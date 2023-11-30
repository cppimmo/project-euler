(ns problem-009
  (:require [clojure.math.combinatorics :as combo]))

;;;; Project Euler #9 - Special Pythagorean Triplet

;;; A Pythagorean triplet is a set of three natural numbers, a < b < b, for which,
;;;
;;; a^2 + b^2 = c^2
;;;
;;; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2
;;;
;;; There exists one Pythagorean  triplet for which a + b + c = 1000.
;;; Find the product abc.

(comment
  ;; This solution isn't ideal. See:
  (combo/count-permutations (range 1 (inc 25))))

(defn first-in-combos [pred coll]
  (first
   (filter pred
           (for [combos (apply map list (repeat (count coll) coll))]
             combos))))

(defn valid-triplet?
  [& coll]
  (< coll))

(comment
  (let [product 25]
    (first-in-combos (fn [combo]
                       (and (apply valid-triplet? combo)
                            (= (reduce + combo) product)))
                     (range 1 (inc product)))))

(defn square [n] (* n n))

(defn right-tri?
  [a b product]
  (let [c (- product a b)]
    (= (+ (square a) (square b)) (square c))))

(let [product 25]
  (for [a (range 1 (inc product))
        b (range a (- (inc product) a))
        :when (right-tri? a b product)]
    [a b product]))

