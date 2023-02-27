package com.github.tylersharpe.supermetroidrandomizer;

import java.util.Collection;
import java.util.Set;

import static com.github.tylersharpe.supermetroidrandomizer.Strat.*;

/**
 * Predicate which tests whether a given collection of items & strats matches a given 'player state'.
 */
interface PlayerState {

    PlayerState ANY = (i, s) -> true;

    PlayerState CAN_LAY_ANY_BOMB_TYPE = (i, s) ->
        i.contains(Item.MORPH_BALL) && (i.contains(Item.BOMBS) || i.contains(Item.POWER_BOMBS));

    PlayerState CAN_DESTROY_BOMB_BLOCKS = CAN_LAY_ANY_BOMB_TYPE.or((i, s) -> i.contains(Item.SCREW_ATTACK));

    PlayerState CAN_OPEN_RED_DOORS = (i, s) -> i.contains(Item.MISSILES) || i.contains(Item.SUPER_MISSILES);

    PlayerState CAN_LAY_POWER_BOMBS = (i, s) -> i.contains(Item.MORPH_BALL) && i.contains(Item.POWER_BOMBS);

    PlayerState CAN_ACCESS_BRINSTAR_PARLOR = CAN_DESTROY_BOMB_BLOCKS.or((i, s) -> s.contains(SHORT_CHARGE));

    PlayerState CAN_ACCESS_BRINSTAR_BIG_PINK = CAN_ACCESS_BRINSTAR_PARLOR
        .and(CAN_OPEN_RED_DOORS)
        .and(CAN_LAY_ANY_BOMB_TYPE.or(CAN_DESTROY_BOMB_BLOCKS));

    PlayerState CAN_ACCESS_GREEN_HILLS =
        // vanilla
        CAN_ACCESS_BRINSTAR_BIG_PINK.and((i, s) -> i.contains(Item.MORPH_BALL) && i.contains(Item.SUPER_MISSILES))

        // meme route
        .or(CAN_LAY_POWER_BOMBS);

    PlayerState CAN_ACCESS_RED_BRINSTAR =
        // vanilla
        CAN_ACCESS_GREEN_HILLS.and((i, s) -> i.contains(Item.SUPER_MISSILES))

        // access from upper crateria
        .or(CAN_LAY_POWER_BOMBS.and((i, s) -> i.contains(Item.SUPER_MISSILES)));

    PlayerState HEATED_UPPER_NORFAIR = CAN_ACCESS_RED_BRINSTAR.and((i, s) -> i.contains(Item.VARIA_SUIT) || s.contains(HELL_RUNS));

    PlayerState LOWER_NORFAIR = HEATED_UPPER_NORFAIR.and((i, s) ->
        i.contains(Item.VARIA_SUIT) && i.contains(Item.POWER_BOMBS) && (
            // vanilla access
            (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPACE_JUMP)) ||

            // gravity jump
            s.contains(GRAVITY_JUMP) ||

            // lava dives
            (s.contains(LAVA_DIVE_NO_HIGH_JUMP) || s.contains(LAVA_DIVE_WITH_HIGH_JUMP))
        ));

    PlayerState AMPHITHEATER = LOWER_NORFAIR.and(CAN_DESTROY_BOMB_BLOCKS).and((i, s) ->
        // vanilla
        i.contains(Item.SPACE_JUMP) ||

        // spring ball jump
        s.contains(SPRING_BALL_DOUBLE_JUMP) ||

        // wall jumps
        i.contains(Item.HIGH_JUMP_BOOTS)
    );

    PlayerState GREEN_MARIDIA_ENTRY = CAN_ACCESS_RED_BRINSTAR.and((i, s) ->
        i.contains(Item.POWER_BOMBS) && (i.contains(Item.HIGH_JUMP_BOOTS) || i.contains(Item.GRAVITY_SUIT))
    );

    PlayerState GREEN_MARIDIA_FULL = GREEN_MARIDIA_ENTRY.and((i, s) ->
        // vanilla
        (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.HIGH_JUMP_BOOTS) && i.contains(Item.GRAPPLE_BEAM)) ||

        // non-grapple exploration
        (i.contains(Item.GRAVITY_SUIT) && (i.contains(Item.SPACE_JUMP) || (i.contains(Item.SPEED_BOOSTER) && i.contains(Item.HIGH_JUMP_BOOTS)))) ||

        // suitless
        s.contains(SUITLESS_MARIDIA)
    );

    PlayerState BOTWOON = GREEN_MARIDIA_FULL.and((i, s) ->
        // vanilla
        (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPEED_BOOSTER) && i.contains(Item.GRAPPLE_BEAM)) ||

        // suitless ice clip
        (s.contains(ICE_CLIP) && s.contains(GRAPPLE_GRAVITY_LAUNCH)) ||

        // normal ice clip
        (s.contains(ICE_CLIP) && i.contains(Item.GRAVITY_SUIT))
    );

    PlayerState CAN_ACCESS_CRATERIA_MOAT = PlayerState.CAN_LAY_POWER_BOMBS.and((i, s) -> i.contains(Item.SUPER_MISSILES));
    PlayerState CAN_ACCESS_WEST_WRECKED_SHIP_ENTRANCE = CAN_ACCESS_CRATERIA_MOAT.and((i, s) ->
        // vanilla
        i.contains(Item.GRAPPLE_BEAM) ||

        // wall jump off missile pillar
        s.contains(CONTINUOUS_WALL_JUMP) ||

        // jump to missiles & bomb jump over
        (i.contains(Item.BOMBS) && s.contains(HORIZONTAL_BOMB_JUMP)));

    PlayerState WRECKED_SHIP = CAN_ACCESS_CRATERIA_MOAT.and((i, s) ->
        i.contains(Item.GRAPPLE_BEAM) || s.contains(CONTINUOUS_WALL_JUMP) || s.contains(HORIZONTAL_BOMB_JUMP)
    );

    PlayerState SPIKY_DEATH_ROOM = WRECKED_SHIP.and((i, s) ->
        i.contains(Item.GRAVITY_SUIT) || // Vanilla access
        i.contains(Item.HIGH_JUMP_BOOTS) || // Access from running start into water + wall jump
        s.contains(SPIKY_DEATH_POWER_BOMB_ENTRANCE)
    );

    // TODO add 3rd parameter which defines bosses killed
    boolean matches(Collection<Item> items, Set<Strat> strats);

    default PlayerState and(PlayerState other) {
        return (items, strats) -> matches(items, strats) && other.matches(items, strats);
    }

    default PlayerState or(PlayerState other) {
        return (items, strats) -> matches(items, strats) || other.matches(items, strats);
    }

}
