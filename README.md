# BustABobble
An open-source adaptation of a classic Japanese puzzle game for Android.

Copyright (c) 2016 Andrew Cleland

alcleland@gmail.com


# About the game
BustABobble is an open-source Android game project based on the game PuzzleBobble (Bust-A-Move in the United States) developed by the Taito Corporation. In the game, the player fires colored balls, called bobbles, into the game frame. Connect three or more bobbles of the same color, and they "pop", causing all bobbles below them to fall off the screen. Clear all the bobbles on the screen and you advance to the next level.

# Copyright and License
BustABobble is freely avaliable under the standard MIT License. See the COPYING for details. The underlying engine is taken primarily from example code written in "Sams Teach Yourself Android Game Programming in 24 Hours" by Jonathan S. Harbour. Used with permission from the author.

Note to Bart for grading: files written in package april.bustabobble are written by me (except ReboundBehavior.java), and package game.engine is mostly written by J. Harbour (except Vec2.java, and some minor changes here and there). Copyright messages in each file should indicate who has was primarily responsible for that file..

# Source code available on GitHub
https://github.com/acleland/BustABobble

# Build Instructions
* As a prerequisite, I suggest obtaining a copy of Android Studio. 
* Download the source code from GitHub to a directory of your choice, 
* Use Android Studio to import a project from that directory. 
* Building the project in Android Studio will require installing Android SDK Build-Tools 24. Android Studio should provide you a link for doing that.
* Press the green Run button or use Run->'Run app' from the drop down menu. It will prompt you to select device you want to run on. You may use either a conncected Android device or an emulator.
* The program runs at a very slow frame rate on emulators provided by Android Studio. I suggest connecting an Android device for best results.

# Project Status and Documentation
The game is currently far from complete. Here is a brief summary of its current status.
* When you open the app, a checkerboard screen appears, with a black line segment at the bottom representing a cannon.
* The bobble placed over the cannon tells you the color that is already loaded and will be fired next. Another bobble below and left of the cannon shows the next color waiting to be loaded into the cannon.
* Touch and move finger to aim the cannon.
* Cannon fires on release of finger.
* Bobbles ricochet off sides of the game frame and stick to the ceiling.
* Bobbles "snap" to each other in an organized grid.

The next stage of development is to complete the logic and data structure required to detect color matches and drop the correct bobbles from the screen. Once this is done, I will create levels and scoring. 

# Demo Video
A short video demonstrating the game status as of August 10, 2016:
https://youtu.be/b2j3L30qUNY
