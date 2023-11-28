(ns problem-029)

(defn powers
  "Generate sequence of a raised to the powers in [b-min, b-max]."
  ([a [b-min b-max]] ; Inclusive range
   (map #(bigint (Math/pow a %)) (range b-min (inc b-max)))))

(defn powers-seq
  [min max] ; Same range for a & b terms
  ;; apply concat to squash the result list into to a single seq
  (apply concat (map #(powers % [min max])
                     (range min (inc max)))))

(defn count-terms
  "Count terms in seq of power combinations."
  [min max]
  (count (distinct (powers-seq min max))))

(comment
  (assert (= (count-terms 2 5) 15)))

(println "Answer:" (count-terms 2 100))

