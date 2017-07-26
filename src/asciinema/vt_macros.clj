(ns asciinema.vt-macros)

(defmacro update! [a key f & args]
  `(assoc! ~a ~key (~f (~key ~a) ~@args)))
