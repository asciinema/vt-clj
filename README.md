# asciinema virtual terminal

[![Build Status](https://travis-ci.org/asciinema/vt.svg?branch=master)](https://travis-ci.org/asciinema/vt)

This repository contains the source code of virtual terminal emulator used
by [asciinema-player](https://github.com/asciinema/asciinema-player).

The emulator is based on
[Paul Williams' parser for ANSI-compatible video terminals](http://vt100.net/emu/dec_ansi_parser).
It covers only the output (display) part of the emulation as only this is needed
by asciinema-player. Handling of escape sequences is fully compatible
with most modern terminal emulators like xterm, Gnome Terminal, iTerm, mosh etc.

## License

Copyright &copy; 2015-2017 Marcin Kulik.

All code is licensed under the Apache License, Version 2.0. See LICENSE file for details.
