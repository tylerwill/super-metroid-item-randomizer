package com.github.tylersharpe.supermetroidrandomizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws IOException {
    ParsedArgs parsedArgs = ParsedArgs.parse(args);

    if (parsedArgs.ordinalArgs.size() != 1) {
      throw new IllegalArgumentException("A single argument is required for the ROM file to patch");
    }
    Path romFile = Paths.get(parsedArgs.ordinalArgs.get(0));

    Difficulty difficulty = Difficulty.valueOf(parsedArgs.namedArgs.getOrDefault("diff", Difficulty.NOVICE.name()));
    Set<Strat> allowedStrats = Strat.getAllowedStrats(difficulty);

    boolean seedDebug = parsedArgs.switches.contains("debug");
    Map<ItemLocation, Item> seed = Seed.createSeed(allowedStrats, seedDebug);
    seed.forEach((location, item) -> System.out.println(location.name + " -> " + item));
    System.out.println();

    applySeed(seed, romFile);
  }

  private static void applySeed(Map<ItemLocation, Item> seed, Path romFile) throws IOException {
    byte[] bytes = Files.readAllBytes(romFile);

    seed.forEach((ItemLocation location, Item item) -> {
      int locationAddress = location.address;
      int itemCode = item.getCode(location.locationType);

      bytes[locationAddress] = (byte)((itemCode >> 8) & 0xff);
      bytes[locationAddress + 1] = (byte)(itemCode & 0xff);
    });

    String modifiedFileName = Util.getFilenameWithoutExtension(romFile) + ".randomized." + Util.getExtension(romFile);
    Path saveTarget = romFile.getParent().resolve(modifiedFileName);
    System.out.println("Applying randomizer seed to [" + romFile + "] with output file [" + saveTarget + "]");
    Files.write(saveTarget, bytes);
  }

}