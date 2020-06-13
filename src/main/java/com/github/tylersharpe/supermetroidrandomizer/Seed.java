package com.github.tylersharpe.supermetroidrandomizer;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

final class Seed {

  private Seed() {}

  static Map<ItemLocation, Item> createSeed(Set<Strat> allowedStrats, boolean debug)  {
    Map<ItemLocation, Item> seededItems = new LinkedHashMap<>(); // Linked hash map preserves order items were seeded in - nice when printing out results
    Consumer<Supplier<String>> debugOut = debug ? str -> System.out.println(str.get()) : str -> {};

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
                .filter(item -> item.type == seedableLocation.itemType)
                .filter(item -> !seedableLocation.itemBlacklist.contains(item))
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

      Map<Item, Set<ItemLocation>> progressionByItem = new EnumMap<>(Item.class);
      for (Item seedableItem : uniqueSeedableItems) {
        Collection<Item> potentialItems = Util.concat(seededItems.values(), seedableItem);
        Set<ProgressionAbility> potentialAbilities = ProgressionAbility.allUnlockedWith(potentialItems);
        Set<Strat> potentialStrats = Strat.allPerformableWith(allowedStrats, potentialItems);

        Set<ItemLocation> locationsOpened = Stream.of(ItemLocation.values())
                .filter(location -> !seededItems.containsKey(location) && !seedableLocations.contains(location)) // Remove locations already open
                .filter(location -> location.canAccess.test(potentialAbilities, potentialStrats)) // New locations we could access with this item
                .collect(toSet());

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

      debugOut.accept(() -> "Current items: " + seededItems.values().stream().collect(groupingBy(identity(), counting())));
      debugOut.accept(() -> "");

      debugOut.accept(() -> "Seedable items:");
      seedableItemsByLocation.forEach((location, items) -> debugOut.accept(() -> location + " -> " + new HashSet<>(items)));
      debugOut.accept(() -> "");

      debugOut.accept(() -> "Available progression by item:");
      progressionByItem.forEach((item, locations) -> debugOut.accept(() -> item + " -> " + locations));
      debugOut.accept(() -> "");

      debugOut.accept(() -> "Available progression by location:");
      progressionByLocation.forEach((location, opened) -> debugOut.accept(() -> location + " -> " + opened));
      debugOut.accept(() -> "");

      ItemLocation locationToSeed;
      List<Item> itemsCanSeed;
      if (progressionByLocation.size() == 1) {
        locationToSeed = progressionByLocation.keySet().iterator().next();
        debugOut.accept(() -> "Forced to seed progression item at " + locationToSeed + " since it is the only location offering progression");

        Stream<Item> progressionItems = seedableItemsByLocation.get(locationToSeed).stream().filter(progressionByItem::containsKey);

        if (seededItems.values().containsAll(Item.MINORS)) {
          debugOut.accept(() -> "All minors have been seeded, progression item must unlock a major location");
          progressionItems = progressionItems.filter(progressionItem ->
              progressionByItem.get(progressionItem).stream().anyMatch(location -> location.itemType == Item.Type.MAJOR)
          );
        }

        itemsCanSeed = progressionItems.collect(toList());
      } else {
        locationToSeed = Util.sample(seedableLocations);
        itemsCanSeed = seedableItemsByLocation.get(locationToSeed);
      }

      Item itemToSeed = Util.sample(itemsCanSeed);
      seededItems.put(locationToSeed, itemToSeed);
      debugOut.accept(() -> "Seeded " + itemToSeed + " at " + locationToSeed + (progressionByItem.containsKey(itemToSeed) ? ", opening: " + progressionByItem.get(itemToSeed) : ""));
      remainingItemsToSeed.remove(itemToSeed);
      seedableLocations.remove(locationToSeed);

      if (progressionByItem.containsKey(itemToSeed)) {
        var locationsOpened = progressionByItem.get(itemToSeed);
        seedableLocations.addAll(locationsOpened);
      }

      debugOut.accept(() -> "\n" + "-".repeat(200) + "\n");
    }

    return seededItems;
  }

}
