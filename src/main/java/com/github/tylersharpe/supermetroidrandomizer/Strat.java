package com.github.tylersharpe.supermetroidrandomizer;

import java.util.*;

import static com.github.tylersharpe.supermetroidrandomizer.Item.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

enum Strat {
    MOCKBALL(MORPH_BALL),
    INFINITE_BOMB_JUMP(MORPH_BALL, BOMBS),
    SPEED_BALL(MORPH_BALL, SPEED_BOOSTER),
    GRAVITY_JUMP(GRAVITY_SUIT),
    SHORT_CHARGE(SPEED_BOOSTER),
    SPRING_BALL_DOUBLE_JUMP(SPRING_BALL),
    GREEN_GATE_GLITCH(SUPER_MISSILES),
    WAVE_GATE_GLITCH(SUPER_MISSILES),
    LAVA_DIVE_WITH_HIGH_JUMP(HIGH_JUMP_BOOTS),
    ICE_CLIP(ICE_BEAM),
    HELL_RUNS,
    GRAPPLE_GRAVITY_LAUNCH(GRAPPLE_BEAM),
    SPIKY_DEATH_POWER_BOMB_ENTRANCE(POWER_BOMBS),
    HORIZONTAL_BOMB_JUMP(BOMBS),
    XRAY_DAMAGE_BOOST,
    CONTINUOUS_WALL_JUMP,
    SUITLESS_MARIDIA(HIGH_JUMP_BOOTS, ICE_BEAM, SUPER_MISSILES),
    LAVA_DIVE_NO_HIGH_JUMP;

    final Set<Item> requiredItems;

    Strat(Item... requiredItems) {
        this.requiredItems = EnumSet.copyOf(List.of(requiredItems));
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
