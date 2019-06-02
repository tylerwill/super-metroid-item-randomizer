# Super Metroid Item Randomizer
![Super Metroid Cartridge Artwork](https://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/super_nintendo_5/H2x1_SNES_SuperMetroid_image1600w.jpg)

## Overview
This program randomizes the locations of items in the Super Nintendo game Super Metroid. It has built-in logic to ensure the locations chosen for each item will not result in the player getting stuck or 'soft-locked'.

The randomizer assumes the player is at least capable of wall jumping and shine sparking. Additional strats / glitches can be enabled via difficulty options.

The program requires java 12 to build and run.

## Building

Run the following from the root of the project:

```(linux) ./gradlew jar```

```(windows) gradlew jar```

After running this command, a JAR file will be created at ```build/libs/sm-randomizer.jar``` relative to the root of the project.

## Running
The randomizer is run as follows:

```java -jar path/to/sm-randomizer.jar path/to/rom/to/patch [--debug] [--diff expert|intermediate|novice]```
