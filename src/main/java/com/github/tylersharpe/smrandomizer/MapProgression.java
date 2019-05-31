package com.github.tylersharpe.smrandomizer;

import static com.github.tylersharpe.smrandomizer.ProgressionAbility.*;
import static com.github.tylersharpe.smrandomizer.Strat.*;
import static com.github.tylersharpe.smrandomizer.Strat.HI_JUMP_LAVA_DIVE;

/**
 * Provides access logic for common 'checkpoints' maps throughout the game, allowing to reuse access logic for certain regions
 */
class MapProgression {

  static final ProgressionRule ZEBES_AWAKE = (a, s) -> a.contains(MORPH);

  static final ProgressionRule BRINSTAR_PARLOR = ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BRITTLE_WALLS) || s.contains(SHORT_CHARGE));

  static final ProgressionRule BIG_PINK = BRINSTAR_PARLOR.and((a, s) ->
          a.contains(OPEN_MISSILE_DOORS) && (a.contains(CLEAR_BRITTLE_WALLS) || a.contains(SPEED_BOOSTER))
  );

  static final ProgressionRule GREEN_HILLS =
          BIG_PINK.and((a, s) -> a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)) // Vanilla access
          .or(ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))); // Meme route

  static final ProgressionRule ETECOONS = BRINSTAR_PARLOR.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS));

  static final ProgressionRule RED_TOWER =
          GREEN_HILLS.and((a, s) -> a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)) // Vanilla access
          .or((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)); // Access from upper crateria

  static final ProgressionRule BRINSTAR_WATERWAY = RED_TOWER.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS));

  static final ProgressionRule HEATED_UPPER_NORFAIR = BRINSTAR_WATERWAY.and((a, s) -> a.contains(PREVENT_HEAT_DAMAGE) || s.contains(HELL_RUNS));

  static final ProgressionRule LOWER_NORFAIR = HEATED_UPPER_NORFAIR.and((a, s) ->
          a.contains(PREVENT_HEAT_DAMAGE) && a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (
                  (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(SPACE_JUMP)) || // Vanilla access
                  (a.contains(FREE_LIQUID_MOVEMENT) && s.contains(GRAVITY_JUMP)) || // Gravity jump
                  (s.contains(HI_JUMPLESS_LAVA_DIVE) || s.contains(HI_JUMP_LAVA_DIVE)) // Lava dives
          ));

  static final ProgressionRule AMPHITHEATER = LOWER_NORFAIR.and((a, s) ->
          a.contains(CLEAR_BRITTLE_WALLS) && ( // Need to be able to clear barriers throughout the room
              a.contains(SPACE_JUMP) || // Vanilla access
              s.contains(SPRING_BALL_DOUBLE_JUMP) || // Spring ball jump
              a.contains(HIGH_JUMP) // Using wall jumps
          )
  );

  static final ProgressionRule GREEN_MARIDIA_ENTRY = BRINSTAR_WATERWAY.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (a.contains(HIGH_JUMP) || a.contains(FREE_LIQUID_MOVEMENT))
  );

  static final ProgressionRule GREEN_MARIDIA_FULL = GREEN_MARIDIA_ENTRY.and((a, s) ->
          (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(HIGH_JUMP) && a.contains(GRAPPLE)) || // Vanilla
          (a.contains(FREE_LIQUID_MOVEMENT) && (a.contains(SPACE_JUMP) || (a.contains(SPEED_BOOSTER) && a.contains(HIGH_JUMP)))) || // Non grapple exploration
          s.contains(SUITLESS_MARIDIA) // Suitless
  );

  static final ProgressionRule BOTWOON = GREEN_MARIDIA_FULL.and((a, s) ->
          (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(SPEED_BOOSTER) && a.contains(GRAPPLE)) || // Vanilla access
          (s.contains(ICE_CLIP) && s.contains(GRAPPLE_GRAVITY_LAUNCH)) || // Suitless ice clip
          (s.contains(ICE_CLIP) && a.contains(FREE_LIQUID_MOVEMENT)) // Ice clip if lacking speed booster
  );

  static final ProgressionRule MOAT = ZEBES_AWAKE.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(CLEAR_SUPER_MISSILE_OBSTRUCTIONS)
  );

  static final ProgressionRule WRECKED_SHIP = MOAT.and((a, s) ->
          a.contains(GRAPPLE) || s.contains(CWJ) || s.contains(HBJ)
  );

  static final ProgressionRule SPIKY_DEATH_ROOM = WRECKED_SHIP.and((a, s) ->
          a.contains(FREE_LIQUID_MOVEMENT) || // Vanilla access
          a.contains(HIGH_JUMP) || // Access from running start into water + wall jump
          s.contains(SPIKY_DEATH_POWER_BOMB_ENTRANCE)
  );
}
