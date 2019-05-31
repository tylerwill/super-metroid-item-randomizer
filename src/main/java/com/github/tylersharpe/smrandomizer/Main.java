package com.github.tylersharpe.smrandomizer;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.tylersharpe.smrandomizer.Item.Type;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Main {

  public static void main(String[] args) {
    Difficulty difficulty = Difficulty.valueOf(System.getProperty("diff", Difficulty.NOVICE.name()).toUpperCase());
    Set<Strat> allowedStrats = Strat.getAllowedStrats(difficulty);

    Map<ItemLocation, Item> seed = createSeed(allowedStrats);

    String jsonBody = seed.entrySet()
            .stream()
            .map(e ->"\"" + e.getKey().name().toLowerCase() + "\": \"" + e.getValue().name().toLowerCase() + "\"")
            .collect(joining(",\n  "));

    System.out.println("{\n  " + jsonBody + "\n}");
  }

  private static Map<ItemLocation, Item> createSeed(Set<Strat> allowedStrats)  {
    Map<ItemLocation, Item> seededItems = new LinkedHashMap<>(); // Linked hash map preserves order items were seeded in - nice when printing out results
    Consumer<Supplier<String>> debug = "true".equals(System.getProperty("seed.debug")) ? str -> System.out.println(str.get()) : str -> {};

    List<Item> remainingItemsToSeed = Stream.of(Item.values())
            .flatMap(item -> IntStream.range(0, item.numAvailable).mapToObj(i -> item))
            .collect(toList());

    Set<Strat> initialStrats = Strat.allPerformableWith(allowedStrats, Set.of());
    List<ItemLocation> seedableLocations = Stream.of(ItemLocation.values())
            .filter(location -> location.canAccess.test(List.of(), initialStrats) && location.canExit.test(List.of(), initialStrats))
            .collect(toList());

    while (remainingItemsToSeed.size() > 0) {

      Map<ItemLocation, List<Item>> seedableItemsByLocation = new EnumMap<>(ItemLocation.class);
      for (ItemLocation seedableLocation : seedableLocations) {
        List<Item> itemsCanSeedHere = remainingItemsToSeed.stream()
                .filter(item -> item.type == seedableLocation.type)
                .filter(item -> !seedableLocation.blacklist.contains(item))
                .filter(item -> {
                    Collection<Item> potentialItems = Util.concat(seededItems.values(), item);
                    Set<ProgressionAbility> potentialAbilities = ProgressionAbility.allUnlockedWith(potentialItems);
                    Set<Strat> potentialStrats = Strat.allPerformableWith(allowedStrats, potentialItems);

                    return seedableLocation.canExit.test(potentialAbilities, potentialStrats);
                }).collect(toList());

        if (itemsCanSeedHere.size() > 0) {
          seedableItemsByLocation.put(seedableLocation, itemsCanSeedHere);
        }
      }

      Set<Item> uniqueSeedableItems = seedableItemsByLocation.values().stream().flatMap(Collection::stream).collect(toSet());

      Map<Item, List<ItemLocation>> progressionByItem = new EnumMap<>(Item.class);
      for (Item seedableItem : uniqueSeedableItems) {
          Collection<Item> potentialItems = Util.concat(seededItems.values(), seedableItem);
          Set<ProgressionAbility> potentialAbilities = ProgressionAbility.allUnlockedWith(potentialItems);
          Set<Strat> potentialStrats = Strat.allPerformableWith(allowedStrats, potentialItems);

          List<ItemLocation> locationsOpened = Stream.of(ItemLocation.values())
                  .filter(location -> !seededItems.containsKey(location) && !seedableLocations.contains(location)) // Remove locations already open
                  .filter(location -> location.canAccess.test(potentialAbilities, potentialStrats)) // New locations we could access with this item
                  .collect(toList());

          if (locationsOpened.size() > 0) {
            progressionByItem.put(seedableItem, locationsOpened);
          }
      }

      Map<ItemLocation, Set<ItemLocation>> progressionByLocation = new EnumMap<>(ItemLocation.class);
      for (var location_items : seedableItemsByLocation.entrySet()) {
        ItemLocation seedLocation = location_items.getKey();

        Set<ItemLocation> locationsCanOpen = location_items.getValue()
                .stream()
                .filter(progressionByItem::containsKey)
                .flatMap(item -> progressionByItem.get(item).stream())
                .collect(toSet());

        if (!locationsCanOpen.isEmpty()) {
          progressionByLocation.put(seedLocation, locationsCanOpen);
        }
      }

      debug.accept(() -> "Current items: " + seededItems.values().stream().collect(groupingBy(identity(), counting())));
      debug.accept(() -> "");

      debug.accept(() -> "Seedable items:");
      seedableItemsByLocation.forEach((loc, items) -> debug.accept(() -> loc + " -> " + new HashSet<>(items)));
      debug.accept(() -> "");

      debug.accept(() -> "Available progression by item:");
      progressionByItem.forEach((item, locations) -> debug.accept(() -> item + " -> " + locations));
      debug.accept(() -> "");

      debug.accept(() -> "Available progression by location:");
      progressionByLocation.forEach((loc, opened) -> debug.accept(() -> loc + " -> " + opened));
      debug.accept(() -> "");

      ItemLocation locationToSeed;
      List<Item> itemsCanSeed;
      if (progressionByLocation.size() == 1) {
        locationToSeed = progressionByLocation.keySet().iterator().next();
        debug.accept(() -> "Forced to seed progression item at " + locationToSeed + " since it is the only location offering progression");

        Stream<Item> progressionItems = seedableItemsByLocation.get(locationToSeed).stream().filter(progressionByItem::containsKey);

        if (seededItems.values().containsAll(Item.MINORS)) {
          debug.accept(() -> "All minors have been seeded, progression item must unlock a major location");
          progressionItems = progressionItems.filter(item -> progressionByItem.get(item).stream().anyMatch(location -> location.type == Type.MAJOR));
        }

        itemsCanSeed = progressionItems.collect(toList());
      } else {
        locationToSeed = Util.sample(seedableLocations);
        itemsCanSeed = seedableItemsByLocation.get(locationToSeed);
      }

      Item itemToSeed = Util.sample(itemsCanSeed);
      seededItems.put(locationToSeed, itemToSeed);
      debug.accept(() -> "Seeded " + itemToSeed + " at " + locationToSeed + (progressionByItem.containsKey(itemToSeed) ? ", opening: " + progressionByItem.get(itemToSeed) : ""));
      remainingItemsToSeed.remove(itemToSeed);
      seedableLocations.remove(locationToSeed);

      if (progressionByItem.containsKey(itemToSeed)) {
        var locationsOpened = progressionByItem.get(itemToSeed);
        seedableLocations.addAll(locationsOpened);
      }

      debug.accept(() -> "\n" + "-".repeat(200) + "\n");
    }

    return seededItems;
  }

}