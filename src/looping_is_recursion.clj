(ns looping-is-recursion)

(defn power [base exp]
  (let [helper (fn [acc base exp] 
	(cond (== base 0) 0
	      (== exp 0) acc
	      (== exp 1) (* acc base)
	      :else (recur (* acc base) base (dec exp))))]
	(helper 1 base exp)
 ))

(defn last-element [a-seq]
  (let [helper (fn [n a-seq] 
	(cond (== n 0) nil
	      (== n 1) (first a-seq)
	      :else (recur (dec n) (rest a-seq))))]
  (helper (count a-seq) a-seq)))

(defn seq= [seq1 seq2]
  (let [helper (fn [s1 s2]
	(cond	(empty? s1) true
	     	(not (== (first s1) (first s2))) false
	        :else (recur (rest s1) (rest s2))))] 
  (if  (not (== (count seq1) (count seq2))) false
       (helper seq1 seq2))))

(defn find-first-index [pred a-seq]
  (loop [i 0
	my-seq a-seq]
	(cond  (empty? my-seq) nil
	       (pred (first my-seq)) i
		:else (recur (inc i) (rest my-seq)))))

(defn avg [a-seq]
  (loop [num 0 
	sum 0
	my-seq a-seq]
	(if (empty? my-seq) (/ sum num)
	     (recur (inc num) (+ sum (first my-seq)) (rest my-seq)))))

(defn parity [a-seq]
  (loop [my-set #{} 
	 my-seq a-seq]
   (cond (empty? my-seq) my-set
	 (contains? my-set (first my-seq)) (recur (disj my-set (first my-seq)) (rest my-seq))
	:else (recur (conj my-set (first my-seq)) (rest my-seq)))))

(defn fast-fibo [n]
  (loop [m 0
	x 0
	y 0]
	(cond (== n m) y
	      (== m 0) (recur 1 0 1)  
	      :else (recur (inc m) y (+ x y)))))

(defn cut-at-repetition [a-seq]
 (loop [my-seq a-seq
	my-set #{}
	my-vec []]
	(cond (empty? my-seq) my-vec
	      (contains? my-set (first my-seq)) my-vec
	      :else (recur (rest my-seq) (conj my-set (first my-seq)) (conj my-vec (first my-seq)))

)))

