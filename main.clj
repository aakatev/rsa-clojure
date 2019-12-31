(println "RSA Algorithm")
(println "1. Parameters")
(def p 3)
(def q 11)
(println "p =" p "\nq =" q)
(def n (* p q))
(println "n =" n)
(def phi (* (- p 1)(- q 1)))
(println "phi =" phi)

(defn gcd 
  [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))


(defn gen-e 
  [x]
  (if (= (gcd x phi) 1)
    x
    (recur (+ x 1))))

;; TODO fix e generator
;; (def e (gen-e (+ 3 (rand-int (- phi 1)))))
(def e 3)
(println "e =" e)

(defn mult-inv?
  [d e phi]
  (cond
    (= (mod (* d e) phi) 1) true
    :else false))

(defn gen-d 
  [x]
  (if (mult-inv? x e phi)
    x
    (recur (+ x 1))))

(def d (gen-d 0))
(println "d =" d)

(defn expt 
  [x n]
  (if (zero? n) 1
    (* x (expt x (- n 1)))))

(defn enc 
  [x]
  (mod (expt x e) n)
)

(defn dec 
  [y]
  (mod (expt y d) n)
)

(defn Test []
   (loop [x 0]
    (when (< x n)
      (println (str "Encrypting " x " results into " (enc x)))
      (println (str "Decrypting " (enc x) " results into " (dec (enc x))))
      (recur (+ x 1)))))
      
(println "2. Test")
(Test)