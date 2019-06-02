package com.github.tylersharpe.supermetroidrandomizer;

import java.util.*;
import java.util.stream.Stream;

import static com.github.tylersharpe.supermetroidrandomizer.Difficulty.*;
import static com.github.tylersharpe.supermetroidrandomizer.Item.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public enum Strat {
  MOCKBALL(NOVICE, EnumSet.of(MORPH_BALL)),

  IBJ(INTERMEDIATE, EnumSet.of(BOMBS)),
  SPEED_BALL(INTERMEDIATE, EnumSet.of(MORPH_BALL, SPEED_BOOSTER)),
  GRAVITY_JUMP(INTERMEDIATE, EnumSet.of(GRAVITY_SUIT)),
  SHORT_CHARGE(INTERMEDIATE, EnumSet.of(SPEED_BOOSTER)),
  SPRING_BALL_DOUBLE_JUMP(INTERMEDIATE, EnumSet.of(SPRING_BALL)),
  GREEN_GATE_GLITCH(INTERMEDIATE, EnumSet.of(SUPER_MISSILES)),
  WAVE_GATE_GLITCH(INTERMEDIATE, EnumSet.of(SUPER_MISSILES)),
  HI_JUMP_LAVA_DIVE(INTERMEDIATE, EnumSet.of(HIGH_JUMP_BOOTS)),
  ICE_CLIP(INTERMEDIATE, EnumSet.of(ICE_BEAM)),
  HELL_RUNS(INTERMEDIATE),
  GRAPPLE_GRAVITY_LAUNCH(INTERMEDIATE, EnumSet.of(GRAPPLE_BEAM)),
  SPIKY_DEATH_POWER_BOMB_ENTRANCE(INTERMEDIATE, EnumSet.of(POWER_BOMBS)),
  HBJ(INTERMEDIATE, EnumSet.of(BOMBS)),
  XRAY_DAMAGE_BOOST(INTERMEDIATE),

  CWJ(EXPERT),
  SUITLESS_MARIDIA(EXPERT, EnumSet.of(HIGH_JUMP_BOOTS, ICE_BEAM, SUPER_MISSILES)),
  HI_JUMPLESS_LAVA_DIVE(EXPERT)
  ;

  Difficulty minDifficulty;
  Set<Item> requiredItems;

  Strat(Difficulty minDifficulty) {
    this(minDifficulty, Set.of());
  }

  Strat(Difficulty minDifficulty, Set<Item> requiredItems) {
    this.minDifficulty = minDifficulty;
    this.requiredItems = requiredItems;
  }

  static Set<Strat> getAllowedStrats(Difficulty difficulty) {
    return Stream.of(values())
            .filter(strat -> strat.minDifficulty.ordinal() <= difficulty.ordinal())
            .collect(toCollection(() -> EnumSet.noneOf(Strat.class)));
  }

  static Set<Strat> allPerformableWith(Set<Strat> allPossibleStrats, Collection<Item> withItems) {
    Map<Item, Long> itemCounts = withItems.stream().collect(groupingBy(identity(), counting()));

    return allPossibleStrats.stream()
            .filter(strat -> strat.canPerformWith(itemCounts))
            .collect(toCollection(() -> EnumSet.noneOf(Strat.class)));
  }

  private boolean canPerformWith(Map<Item, Long> itemCounts) {
    if (this == HELL_RUNS) {
      return itemCounts.getOrDefault(ENERGY_TANK, 0L) >= 4;
    } else {
      return itemCounts.keySet().containsAll(requiredItems);
    }
  }

}
