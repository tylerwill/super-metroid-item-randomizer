# sm-randomizer
Randomizes the location of items in super metroid, respecting progression requirements and allowed strats.

The randomizer assumes the player is at least capable of wall jumping and shine sparking. Additional strats / glitches can be enabled via difficulty options. There are currently 3 difficulties available: 'novice', 'intermediate' and 'expert'. These affect the number of strats the randomizer will consider enabled. For instance, a difficult strat such as high jump-less lava dive would only be enabled within the 'expert' difficulty.

## Running
The default build can be run with the following command:

```gradlew jar && java -jar build/libs/sm-randomizer.jar```

This will output a JSON object in which the keys are item locations and the values are the items located at those locations.


Difficulty can be passed as follows. If not given, it will default to 'novice':

```java -Ddiff=intermediate -jar sm-randomizer.jar```

