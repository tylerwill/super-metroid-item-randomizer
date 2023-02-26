package com.github.tylersharpe.supermetroidrandomizer;

import java.util.Set;

import static com.github.tylersharpe.supermetroidrandomizer.ProgressionAbility.*;
import static com.github.tylersharpe.supermetroidrandomizer.Strat.*;

interface AccessPredicate {

    AccessPredicate ZEBES_AWAKE = (a, s) -> a.contains(MORPH);

    AccessPredicate BRINSTAR_PARLOR = ZEBES_AWAKE.and((a, s) ->
            a.contains(DESTROY_BOMB_BLOCKS) || s.contains(SHORT_CHARGE));

    AccessPredicate BIG_PINK = BRINSTAR_PARLOR.and((a, s) -> a.contains(OPEN_RED_DOORS));

    AccessPredicate GREEN_HILLS =
            BIG_PINK.and((a, s) -> a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)) // Vanilla access
                    .or(ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))); // Meme route

    AccessPredicate ETECOONS = BRINSTAR_PARLOR.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS));

    AccessPredicate UPPER_RED_TOWER =
            GREEN_HILLS.and((a, s) -> a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)) // Vanilla access
                    .or((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)); // Access from upper crateria

    AccessPredicate LOWER_RED_TOWER = UPPER_RED_TOWER.and((a, s) -> a.contains(DESTROY_BOMB_BLOCKS));

    AccessPredicate HEATED_UPPER_NORFAIR = LOWER_RED_TOWER.and((a, s) -> a.contains(VARIA) || s.contains(HELL_RUNS));

    AccessPredicate LOWER_NORFAIR = HEATED_UPPER_NORFAIR.and((a, s) ->
            a.contains(VARIA) && a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (
                    (a.contains(GRAVITY) && a.contains(SPACE_JUMP)) || // Vanilla access
                            s.contains(GRAVITY_JUMP) || // Gravity jump
                            (s.contains(HI_JUMPLESS_LAVA_DIVE) || s.contains(HI_JUMP_LAVA_DIVE)) // Lava dives
            ));

    AccessPredicate AMPHITHEATER = LOWER_NORFAIR.and((a, s) ->
            a.contains(DESTROY_BOMB_BLOCKS) && ( // Need to be able to clear barriers throughout the room
                    a.contains(SPACE_JUMP) || // Vanilla access
                            s.contains(SPRING_BALL_DOUBLE_JUMP) || // Spring ball jump
                            a.contains(HIGH_JUMP) // Using wall jumps
            )
    );

    AccessPredicate GREEN_MARIDIA_ENTRY = LOWER_RED_TOWER.and((a, s) ->
            a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (a.contains(HIGH_JUMP) || a.contains(GRAVITY))
    );

    AccessPredicate GREEN_MARIDIA_FULL = GREEN_MARIDIA_ENTRY.and((a, s) ->
            (a.contains(GRAVITY) && a.contains(HIGH_JUMP) && a.contains(GRAPPLE)) || // Vanilla
                    (a.contains(GRAVITY) && (a.contains(SPACE_JUMP) || (a.contains(SPEED_BOOSTER) && a.contains(HIGH_JUMP)))) || // Non grapple exploration
                    s.contains(SUITLESS_MARIDIA) // Suitless
    );

    AccessPredicate BOTWOON = GREEN_MARIDIA_FULL.and((a, s) ->
            (a.contains(GRAVITY) && a.contains(SPEED_BOOSTER) && a.contains(GRAPPLE)) || // Vanilla access
                    (s.contains(ICE_CLIP) && s.contains(GRAPPLE_GRAVITY_LAUNCH)) || // Suitless ice clip
                    (s.contains(ICE_CLIP) && a.contains(GRAVITY)) // Ice clip if lacking speed booster
    );

    AccessPredicate MOAT = ZEBES_AWAKE.and((a, s) ->
            a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)
    );

    AccessPredicate WRECKED_SHIP = MOAT.and((a, s) ->
            a.contains(GRAPPLE) || s.contains(CONTINUOUS_WALL_JUMP) || s.contains(HORIZONTAL_BOMB_JUMP)
    );

    AccessPredicate SPIKY_DEATH_ROOM = WRECKED_SHIP.and((a, s) ->
            a.contains(GRAVITY) || // Vanilla access
                    a.contains(HIGH_JUMP) || // Access from running start into water + wall jump
                    s.contains(SPIKY_DEATH_POWER_BOMB_ENTRANCE)
    );
    
    /**
     * Determines if a given location can be accessed (or exited from) with the given abilities and strats
     */
    boolean test(Set<ProgressionAbility> abilities, Set<Strat> strats);

    default AccessPredicate and(AccessPredicate other) {
        return (abilities, strats) -> test(abilities, strats) && other.test(abilities, strats);
    }

    default AccessPredicate or(AccessPredicate other) {
        return (abilities, strats) -> test(abilities, strats) || other.test(abilities, strats);
    }

}
