# app_project

## Version 1.0 (30.06.2023)

Changes:

    - First Structure of the Adanced_AI
    - First Implementations of: MoveNodeClass, MoveTreeClass
    - First Idea of Multithreading usage on that tree

Conflicts:

    - Need of gameboardstate.clone()-Method for calculating another movesfor the advanced_AI
    - Problematic usage of the boolean drawpile Parameter on the doMove()-Method
    -> Cemil

## Version 1.1 (30.06.2023)

Changes:

    - First Implementations of ThreadInterface and Thread (not ready)

Conflicts:

    - not fixed

## Version 1.2 (30.06.2023)

Changes:

    - Bug-Fixes of Random_AI
    - First Idea of Multithreading: 1 THread is busy with right side of the tree, 1 Thread is busy with left side of the tree

Conflicts:
 
    - not fixed

## Version 1.3 (30.06.2023)

Changes:

    - First Version for Threads Interface (for MoveTree calculations)
    - First implementation of Thread Class (not ready to use)

Conflicts:

    - not fixed

## Version 1.4 (02.07.2023)

Changes:

    - First Implementations of ThreadClasses (Right and Left) for Tree calculations
    - Changes in MoveTree Class (now there are 2 different acual_nodes)

Conflicts:

    - not fixed

## Version 1.5 (02.07.2023)

Changes:

    - Blueprints for the Thread Classes, its abstract classes  + interface are done
    - Aim is clear

Conflicts:

    - not fixed

## Version 1.6 (02.07.2023)

Changes: 

    - Written methods for calculating next moves for the Threads = first Version (without run()- method) how the THreads will actually work (modified: LeftThread RightThread)
    - Thoughts about the run() methods for each Thread

Conflicts:

    - not fixed

## Version 1.7 (03.07.2023)

Changes:

    - The case of the "first" Node solved
    - Left and Right Thread are determined
    - Sets of Cards (player) fixed to ArrayList (easier to wok)

Conflicts:

    - clone() - Method for GameBoard still needed