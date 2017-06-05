(ns asciinema.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [asciinema.vt-test]
            [asciinema.vt.parser-test]))

(enable-console-print!)

(doo-tests
  'asciinema.vt-test
  'asciinema.vt.parser-test)
