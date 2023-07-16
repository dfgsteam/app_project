# app_project

## Gruppenmitglieder

- Kirill Pokhilenko
- Ramon Cemil Kimyon
- Viktor Tevosyan
- Florian Will
- Julius Hunold

## Projektnamen

Hofbauern

## Notwendige Schritte zum Übersetzen ihres Programms

ant

## Erklärung zur Verwendung ihrer grafischen Ein- und Ausgabe

Maus zum andrücken von Karten
-> Wenn groß werden, dann kann man klicken
-> Bei den Stapel kann man mit Scrollen in den Stapel schauen

Leertaste, um nach UI Schritt, nächsten zu machen.

## Ausgabe ihres Programms wenn es mit der --help Flagge


usage: java -jar Bauernhof-Preset-indev_juhu.jar [-c <FILE>] [-con <HOST>]
       [-d <DELAY>] [-g] [-h] [-l] [-ll <LEVEL>] [-load <FILE>] [-p
       <PORT>] [-pc <COLOR ...>] [-pn <NAME ...>] [-pt <TYPE ...>] [-t
       <ROUNDS>] [-tw] [-v <VOLUME>]

=========================================================================
### Hofbauern <1.2.1>
  
#### Authors: Kirill Pokhilenko, Ramon Cemil Kimyon, Viktor Tevosyan, Florian Will, Julius Hunold
=========================================================================


| Command          | Input     | Explanation                                                      |
| ---------------- | --------- | ---------------------------------------------------------------- |
| -c,--config      | <FILE>    | The file from which the game configuration should be read.       |
| -con,--connect   | <HOST>    | Connect as a client to the host.                                 |
| -d,--delay       | <DELAY>   | Delay in milliseconds after a computerplayer has made his move.  |
| -g,--gui         |           | Show the GUI, even if no HUMAN player exists.                    |
| -c,--config   | <FILE>   | The file from which the game configuration should be read.       |

           
           
           
           
Options:

 -h,--help                        Print this help message and some extra
                                  information about this program.
 -l,--launcher                    Launch the launcher. (LAUNCHER)
 -ll,--loglevel <LEVEL>           The maximum log level. [ERRORS,
                                  WARNINGS, INFO, DEBUG]
 -load,--loadsave <FILE>          Load a save game. (SAVEGAMES)
 -p,--port <PORT>                 The port to be used when either hosting
                                  the game as a server or conntecting to a
                                  server as a client.
 -pc,--playerColors <COLOR ...>   The color(s) of the player(s). [RED,
                                  BLUE, ...]
 -pn,--playerNames <NAME ...>     The name(s) of the player(s).
 -pt,--playerTypes <TYPE ...>     The type(s) of the player(s). [HUMAN,
                                  RANDOM_AI, REMOTE, SIMPLE_AI,
                                  ADVANCED_AI]
 -t,--tournament <ROUNDS>         Play a tournament. (TOURNAMENTS)
 -tw,--tournamentwait             Wait for a user interaction before
                                  starting the next game in a tournament.
                                  (TOURNAMENTS)
 -v,--volume <VOLUME>             Volume of the soundeffects. (0-100)
                                  (SOUNDEFFECTS)

Preset: v1.2.1 (Thu Jul 06 03:48:56 CEST 2023)
SAG: v2.1.0 (Mon Jul 03 07:30:41 CEST 2023)
Implemented optional features:
  - ADVANCED_AI
  - LAUNCHER
  - SAVEGAMES
  - SCREENSHOTS
  - SIMPLE_AI
  - SOUNDEFFECTS
  - TOURNAMENTS
