package com.github.tylersharpe.supermetroidrandomizer;

import static com.github.tylersharpe.supermetroidrandomizer.ProgressionAbility.*;
import static com.github.tylersharpe.supermetroidrandomizer.Strat.*;
import static com.github.tylersharpe.supermetroidrandomizer.Strat.HI_JUMP_LAVA_DIVE;

/**
 * Provides access logic for common 'checkpoints' maps throughout the game, allowing to reuse access logic for certain regions
 */
class CommonProgressionRules {

  static final ProgressionRule ZEBES_AWAKE = (a, s) -> a.contains(MORPH);

  static final ProgressionRule BRINSTAR_PARLOR = ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BRITTLE_WALLS) || s.contains(SHORT_CHARGE));

  static final ProgressionRule BIG_PINK = BRINSTAR_PARLOR.and((a, s) -> a.contains(OPEN_RED_DOORS));

  static final ProgressionRule GREEN_HILLS =
          BIG_PINK.and((a, s) -> a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)) // Vanilla access
          .or(ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))); // Meme route

  static final ProgressionRule ETECOONS = BRINSTAR_PARLOR.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS));

  static final ProgressionRule UPPER_RED_TOWER =
          GREEN_HILLS.and((a, s) -> a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)) // Vanilla access
          .or((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)); // Access from upper crateria

  static final ProgressionRule LOWER_RED_TOWER = UPPER_RED_TOWER.and((a, s) -> a.contains(CLEAR_BOMB_PASSAGES));

  static final ProgressionRule HEATED_UPPER_NORFAIR = LOWER_RED_TOWER.and((a, s) -> a.contains(VARIA) || s.contains(HELL_RUNS));

  static final ProgressionRule LOWER_NORFAIR = HEATED_UPPER_NORFAIR.and((a, s) ->
          a.contains(VARIA) && a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (
                  (a.contains(GRAVITY) && a.contains(SPACE_JUMP)) || // Vanilla access
                  s.contains(GRAVITY_JUMP) || // Gravity jump
                  (s.contains(HI_JUMPLESS_LAVA_DIVE) || s.contains(HI_JUMP_LAVA_DIVE)) // Lava dives
          ));

  static final ProgressionRule AMPHITHEATER = LOWER_NORFAIR.and((a, s) ->
          a.contains(CLEAR_BRITTLE_WALLS) && ( // Need to be able to clear barriers throughout the room
              a.contains(SPACE_JUMP) || // Vanilla access
              s.contains(SPRING_BALL_DOUBLE_JUMP) || // Spring ball jump
              a.contains(HIGH_JUMP) // Using wall jumps
          )
  );

  static final ProgressionRule GREEN_MARIDIA_ENTRY = LOWER_RED_TOWER.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (a.contains(HIGH_JUMP) || a.contains(GRAVITY))
  );

  static final ProgressionRule GREEN_MARIDIA_FULL = GREEN_MARIDIA_ENTRY.and((a, s) ->
          (a.contains(GRAVITY) && a.contains(HIGH_JUMP) && a.contains(GRAPPLE)) || // Vanilla
          (a.contains(GRAVITY) && (a.contains(SPACE_JUMP) || (a.contains(SPEED_BOOSTER) && a.contains(HIGH_JUMP)))) || // Non grapple exploration
          s.contains(SUITLESS_MARIDIA) // Suitless
  );

  static final ProgressionRule BOTWOON = GREEN_MARIDIA_FULL.and((a, s) ->
          (a.contains(GRAVITY) && a.contains(SPEED_BOOSTER) && a.contains(GRAPPLE)) || // Vanilla access
          (s.contains(ICE_CLIP) && s.contains(GRAPPLE_GRAVITY_LAUNCH)) || // Suitless ice clip
          (s.contains(ICE_CLIP) && a.contains(GRAVITY)) // Ice clip if lacking speed booster
  );

  static final ProgressionRule MOAT = ZEBES_AWAKE.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)
  );

  static final ProgressionRule WRECKED_SHIP = MOAT.and((a, s) ->
          a.contains(GRAPPLE) || s.contains(CWJ) || s.contains(HBJ)
  );

  static final ProgressionRule SPIKY_DEATH_ROOM = WRECKED_SHIP.and((a, s) ->
          a.contains(GRAVITY) || // Vanilla access
          a.contains(HIGH_JUMP) || // Access from running start into water + wall jump
          s.contains(SPIKY_DEATH_POWER_BOMB_ENTRANCE)
  );
}
