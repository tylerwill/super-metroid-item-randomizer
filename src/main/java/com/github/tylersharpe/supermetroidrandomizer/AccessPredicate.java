package com.github.tylersharpe.supermetroidrandomizer;

import java.util.Collection;
import java.util.Set;

import static com.github.tylersharpe.supermetroidrandomizer.Strat.*;

interface AccessPredicate {

    AccessPredicate ALWAYS = (i, s) -> true;

    AccessPredicate DESTROY_BOMB_BLOCKS = (i, s) ->
            (i.contains(Item.MORPH_BALL) && (i.contains(Item.BOMBS) || i.contains(Item.POWER_BOMBS))) ||
            i.contains(Item.SCREW_ATTACK);

    AccessPredicate BRINSTAR_PARLOR = DESTROY_BOMB_BLOCKS.or((i, s) -> s.contains(SHORT_CHARGE));

    AccessPredicate BRINSTAR_PINK_MOUNTAIN = BRINSTAR_PARLOR.and((i, s) -> i.contains(Item.MISSILES));

    AccessPredicate GREEN_HILLS =
        // vanilla
        BRINSTAR_PINK_MOUNTAIN.and((i, s) -> i.contains(Item.SUPER_MISSILES))

        // meme route
        .or((i, s) -> i.contains(Item.POWER_BOMBS));

    AccessPredicate ETECOONS = BRINSTAR_PARLOR.and((i, s) -> i.contains(Item.POWER_BOMBS));

    AccessPredicate UPPER_RED_TOWER =
        // vanilla
        GREEN_HILLS.and((i, s) -> i.contains(Item.SUPER_MISSILES))

        // access from upper crateria
        .or((i, s) -> i.contains(Item.POWER_BOMBS) && i.contains(Item.SUPER_MISSILES));

    AccessPredicate LOWER_RED_TOWER = UPPER_RED_TOWER.and(DESTROY_BOMB_BLOCKS);

    AccessPredicate HEATED_UPPER_NORFAIR = LOWER_RED_TOWER.and((i, s) -> i.contains(Item.VARIA_SUIT) || s.contains(HELL_RUNS));

    AccessPredicate LOWER_NORFAIR = HEATED_UPPER_NORFAIR.and((i, s) ->
        i.contains(Item.VARIA_SUIT) && i.contains(Item.POWER_BOMBS) && (
            // vanilla access
            (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPACE_JUMP)) ||

            // gravity jump
            s.contains(GRAVITY_JUMP) ||

            // lava dives
            (s.contains(LAVA_DIVE_NO_HIGH_JUMP) || s.contains(LAVA_DIVE_WITH_HIGH_JUMP))
        ));

    AccessPredicate AMPHITHEATER = LOWER_NORFAIR.and(DESTROY_BOMB_BLOCKS).and((i, s) ->
        // vanilla
        i.contains(Item.SPACE_JUMP) ||

        // spring ball jump
        s.contains(SPRING_BALL_DOUBLE_JUMP) ||

        // wall jumps
        i.contains(Item.HIGH_JUMP_BOOTS)
    );

    AccessPredicate GREEN_MARIDIA_ENTRY = LOWER_RED_TOWER.and((i, s) ->
        i.contains(Item.POWER_BOMBS) && (i.contains(Item.HIGH_JUMP_BOOTS) || i.contains(Item.GRAVITY_SUIT))
    );

    AccessPredicate GREEN_MARIDIA_FULL = GREEN_MARIDIA_ENTRY.and((i, s) ->
        // vanilla
        (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.HIGH_JUMP_BOOTS) && i.contains(Item.GRAPPLE_BEAM)) ||

        // non-grapple exploration
        (i.contains(Item.GRAVITY_SUIT) && (i.contains(Item.SPACE_JUMP) || (i.contains(Item.SPEED_BOOSTER) && i.contains(Item.HIGH_JUMP_BOOTS)))) ||

        // suitless
        s.contains(SUITLESS_MARIDIA)
    );

    AccessPredicate BOTWOON = GREEN_MARIDIA_FULL.and((i, s) ->
        // vanilla
        (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPEED_BOOSTER) && i.contains(Item.GRAPPLE_BEAM)) ||

        // suitless ice clip
        (s.contains(ICE_CLIP) && s.contains(GRAPPLE_GRAVITY_LAUNCH)) ||

        // normal ice clip
        (s.contains(ICE_CLIP) && i.contains(Item.GRAVITY_SUIT))
    );

    AccessPredicate MOAT = (i, s) -> i.contains(Item.POWER_BOMBS) && i.contains(Item.SUPER_MISSILES);

    AccessPredicate WRECKED_SHIP = MOAT.and((i, s) ->
        i.contains(Item.GRAPPLE_BEAM) || s.contains(CONTINUOUS_WALL_JUMP) || s.contains(HORIZONTAL_BOMB_JUMP)
    );

    AccessPredicate SPIKY_DEATH_ROOM = WRECKED_SHIP.and((i, s) ->
        i.contains(Item.GRAVITY_SUIT) || // Vanilla access
        i.contains(Item.HIGH_JUMP_BOOTS) || // Access from running start into water + wall jump
        s.contains(SPIKY_DEATH_POWER_BOMB_ENTRANCE)
    );
    
    /**
     * Determines if a given location can be accessed (or exited from) with the given abilities and strats
     */
    boolean test(Collection<Item> items, Set<Strat> strats);

    default AccessPredicate and(AccessPredicate other) {
        return (items, strats) -> test(items, strats) && other.test(items, strats);
    }

    default AccessPredicate or(AccessPredicate other) {
        return (items, strats) -> test(items, strats) || other.test(items, strats);
    }

}
