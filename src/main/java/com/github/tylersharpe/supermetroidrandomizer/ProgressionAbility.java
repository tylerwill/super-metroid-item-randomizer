package com.github.tylersharpe.supermetroidrandomizer;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public enum ProgressionAbility {
    VARIA,
    GRAVITY,

    MORPH,
    BOMBS,
    DESTROY_BOMB_BLOCKS,
    MISSILES,
    POWER_BOMBS,
    SUPER_MISSILES,
    SCREW_ATTACK,

    CHARGE_BEAM, // considered a progression ability since it can be essential for late game bosses which don't drop missiles / supers
    WAVE_BEAM,
    ICE_BEAM,
    PLASMA_BEAM,

    HIGH_JUMP,
    SPEED_BOOSTER,
    SPACE_JUMP,

    GRAPPLE;

    static Set<ProgressionAbility> allUnlockedWith(Collection<Item> items) {
        return items.stream()
                .flatMap(item -> item.abilitiesGranted.stream())
                .collect(toCollection(() -> EnumSet.noneOf(ProgressionAbility.class)));
    }
}
