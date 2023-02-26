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
    OPEN_RED_DOORS,
    DESTROY_POWER_BOMB_OBSTRUCTIONS,
    DESTROY_SUPER_MISSILE_OBSTRUCTIONS,

    CHARGE_BEAM, // considered a progression ability since it can be essential for late game bosses which don't drop missiles / supers
    WAVE_BEAM,
    ICE_BEAM,

    PIERCING_DAMAGE, // ability to kill armored space pirates (gold / pink)

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
