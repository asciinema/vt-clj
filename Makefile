test-main:
	cat test/commands.jsonl | node main.js

.PHONY: test-main
