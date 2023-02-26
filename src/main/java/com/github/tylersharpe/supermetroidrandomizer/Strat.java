package com.github.tylersharpe.supermetroidrandomizer;

import java.util.*;
import java.util.stream.Stream;

import static com.github.tylersharpe.supermetroidrandomizer.Difficulty.*;
import static com.github.tylersharpe.supermetroidrandomizer.Item.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

enum Strat {
    MOCKBALL(NOVICE, MORPH_BALL),
    INFINITE_BOMB_JUMP(NOVICE, BOMBS),

    SPEED_BALL(INTERMEDIATE, MORPH_BALL, SPEED_BOOSTER),
    GRAVITY_JUMP(INTERMEDIATE, GRAVITY_SUIT),
    SHORT_CHARGE(INTERMEDIATE, SPEED_BOOSTER),
    SPRING_BALL_DOUBLE_JUMP(INTERMEDIATE, SPRING_BALL),
    GREEN_GATE_GLITCH(INTERMEDIATE, SUPER_MISSILES),
    WAVE_GATE_GLITCH(INTERMEDIATE, SUPER_MISSILES),
    LAVA_DIVE_WITH_HIGH_JUMP(INTERMEDIATE, HIGH_JUMP_BOOTS),
    ICE_CLIP(INTERMEDIATE, ICE_BEAM),
    HELL_RUNS(INTERMEDIATE),
    GRAPPLE_GRAVITY_LAUNCH(INTERMEDIATE, GRAPPLE_BEAM),
    SPIKY_DEATH_POWER_BOMB_ENTRANCE(INTERMEDIATE, POWER_BOMBS),
    HORIZONTAL_BOMB_JUMP(INTERMEDIATE, BOMBS),
    XRAY_DAMAGE_BOOST(INTERMEDIATE),

    CONTINUOUS_WALL_JUMP(EXPERT),
    SUITLESS_MARIDIA(EXPERT, HIGH_JUMP_BOOTS, ICE_BEAM, SUPER_MISSILES),
    LAVA_DIVE_NO_HIGH_JUMP(EXPERT);

    final Difficulty minDifficulty;
    final Set<Item> requiredItems;

    Strat(Difficulty minDifficulty, Item... requiredItems) {
        this.minDifficulty = minDifficulty;
        this.requiredItems = EnumSet.copyOf(List.of(requiredItems));
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
