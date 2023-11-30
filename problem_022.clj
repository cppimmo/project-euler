(ns problem-022
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

;;;; Project Euler #22 - Names Scores

;;; Using names.txt, a 46K text file containing over five-thousand first names, begin by
;;; sorting it into alphabetical order. Then working out the alphabetical value for each name,
;;; multiply this value by its alphabetical position in the list to obtain a name score.
;;;
;;; For example, when the list is sorted into alphabetical order, COLIN, which is worth
;;; 3+15+12+9+14=53, is the 938th name in the list. So, COLIN would obtain a score of
;;; 938 x 53 = 49714.
;;;
;;; What is the total of all the name scores in the file?

(defn score-name
  "The name score is the product of the sum of the alphabetic character positions in name
  and the position, pos, of the name in the sorted seq."
  [name pos]
  (assert (every? #(Character/isAlphabetic (int %)) name)) ; String must be alphabetic
  (* pos
     (reduce + (map #(inc (- (int %) (int \a)))
                    (str/lower-case name)))))

(defn read-names
  "Return an seq of each name from the filepath: f."
  [f]
  (with-open [reader (io/reader f)]
    (->> (line-seq reader)
         ;; Note: this is probably replacing the whole file's string at once
         (mapcat #(str/split % #","))
         (map #(str/replace % #"\"" "")))))

(def filename "0022_names.txt")

(defn solve
  []
  (reduce (fn [acc [index name]]   ; Destructure the index & name pair
            (+ acc (score-name name (inc index)))) ; Accumulate the score sum
          0 ; Include 0-based index with each name for scoring purposes
          (map-indexed vector (sort (read-names filename)))))

(comment ; Test cases
  (let [colin (nth (sort (read-names filename)) 937)]
    (assert (and (= colin "COLIN")
                 (= (score-name colin 938) 49714)))))

(println "Answer:" (solve))

