# BustABobble
An open-source adaptation of a classic Japanese puzzle game for Android.

Copyright (c) 2016 Andrew Cleland

alcleland@gmail.com


# About the game
BustABobble is an open-source Android game project based on the game PuzzleBobble (Bust-A-Move in the United States) developed by the Taito Corporation. In the game, the player fires colored balls, called bobbles, into the game frame. Connect three or more bobbles of the same color, and they "pop", causing all bobbles below them to fall off the screen. Clear all the bobbles on the screen and you advance to the next level.

# Copyright and License
BustABobble is freely avaliable under the standard MIT License. See the COPYING for details. The underlying engine is taken primarily from example code written in "Sams Teach Yourself Android Game Programming in 24 Hours" by Jonathan S. Harbour. Used with permission from the author. 

# Source code available on GitHub
https://github.com/acleland/BustABobble

# Build Instructions
The project is organized as an Android Studio project. After downloading the source code to a directory of your choice, use Android Studio to import a new project from that directory. Use Android Studio tools to build BustABobble onto a connected Android device or an emulator.

# Project Status and Documentation
The game is currently far from complete. Here is a brief summary of its current status.
* When you open the app, a checkerboard screen appears, with a black line segment at the bottom representing a cannon.
* Touch and move finger to aim the cannon.
* Cannon fires on release of finger.
* Bobbles ricochet off sides of the game frame and stick to the ceiling.
* Bobbles "snap" to each other in an organized grid.

The next stage of development is to complete the logic and data structure required to detect color matches and drop the correct bobbles from the screen. Once this is done, I will create levels and scoring. 

# Demo Video
A short video demonstrating the game status as of August 10, 2016:
https://youtu.be/b2j3L30qUNY
