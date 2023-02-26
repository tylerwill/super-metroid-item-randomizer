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

        Difficulty difficulty = Difficulty.valueOf(parsedArgs.namedArgs.getOrDefault("diff", Difficulty.NOVICE.name()));
        Set<Strat> allowedStrats = Strat.getAllowedStrats(difficulty);

        boolean debug = parsedArgs.switches.contains("debug");
        Map<ItemLocation, Item> seed = Seed.createSeed(allowedStrats, debug);
        seed.forEach((location, item) -> System.out.println(location.name + " -> " + item));
        System.out.println();

        if (!parsedArgs.ordinalArgs.isEmpty()) {
            Path romFile = Paths.get(parsedArgs.ordinalArgs.get(0));
            applySeed(seed, romFile);
        }
    }

    private static void applySeed(Map<ItemLocation, Item> seed, Path romFile) throws IOException {
        byte[] bytes = Files.readAllBytes(romFile);

        seed.forEach((ItemLocation location, Item item) -> {
            int locationAddress = location.hexAddress;
            int itemHexValue = item.getHexValue(location.locationType);

            bytes[locationAddress] = (byte) ((itemHexValue >> 8) & 0xff);
            bytes[locationAddress + 1] = (byte) (itemHexValue & 0xff);
        });

        String modifiedFileName = Util.getFilenameWithoutExtension(romFile) + ".randomized." + Util.getExtension(romFile);
        Path saveTarget = romFile.getParent().resolve(modifiedFileName);
        System.out.println("Applying randomizer seed to [" + romFile + "] with output file [" + saveTarget + "]");
        Files.write(saveTarget, bytes);
    }

}
