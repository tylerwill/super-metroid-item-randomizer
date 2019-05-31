package com.github.tylersharpe.smrandomizer;

import java.util.EnumSet;
import java.util.Set;

import static com.github.tylersharpe.smrandomizer.Item.*;
import static com.github.tylersharpe.smrandomizer.Item.Type.MAJOR;
import static com.github.tylersharpe.smrandomizer.Item.Type.MINOR;
import static com.github.tylersharpe.smrandomizer.MapProgression.*;
import static com.github.tylersharpe.smrandomizer.ProgressionAbility.*;
import static com.github.tylersharpe.smrandomizer.ProgressionAbility.SPACE_JUMP;
import static com.github.tylersharpe.smrandomizer.Strat.*;

public enum ItemLocation {

  // Blue brinstar
  MORPH_BALL(MAJOR, EnumSet.of(VARIA_SUIT, GRAVITY_SUIT)),
  ALPHA_MISSILES(MINOR, (a, s) -> a.contains(MORPH)),
  BLUE_BRINSTAR_ETANK(MAJOR, EnumSet.of(VARIA_SUIT, GRAVITY_SUIT)),
  BLUE_BRINSTAR_FAR_RIGHT_MISSILES(MINOR, (a, s) -> a.contains(MORPH)),
  BILLIE_MAYS_1(MINOR, (a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS)),
  BILLIE_MAYS_2(MINOR, BILLIE_MAYS_1.canAccess),
  BLUE_BRINSTAR_POWER_BOMBS(MINOR, (a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS)),

  // West crateria
  OLD_MOTHER_BRAIN(MINOR, ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  CRATERIA_CLIMB_SUPERS(MINOR, ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(ProgressionAbility.SPEED_BOOSTER))),
  BT(MAJOR, EnumSet.of(VARIA_SUIT, GRAVITY_SUIT), ZEBES_AWAKE.and((a, s) -> a.contains(OPEN_MISSILE_DOORS))),
  TOOTH_HURTY_MISSILES(MINOR, ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  LANDING_SITE_POWER_BOMBS(MINOR, ZEBES_AWAKE.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) &&
          (a.contains(ProgressionAbility.SPEED_BOOSTER) || a.contains(SPACE_JUMP) || a.contains(CLEAR_BOMB_OBSTRUCTIONS))
  )),
  GAUNTLET(MAJOR, EnumSet.of(VARIA_SUIT, GRAVITY_SUIT), ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BRITTLE_WALLS))),
  GAUNTLET_MISSILES_1(MINOR, ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  GAUNTLET_MISSILES_2(MINOR, ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  TERMINATOR(MAJOR, EnumSet.of(VARIA_SUIT, GRAVITY_SUIT), ZEBES_AWAKE.and((a, s) -> a.contains(CLEAR_BRITTLE_WALLS) || s.contains(SHORT_CHARGE))),

  // East crateria
  MOAT_MISSILES(MINOR, MOAT),
  OCEAN_LOWER_MISSILES(MINOR, WRECKED_SHIP),
  OCEAN_MID_MISSILES(MINOR, WRECKED_SHIP),
  OCEAN_TOP_MISSILES(MINOR, WRECKED_SHIP),

  // Green / pink brinstar
  EARLY_SUPERS_FLOOR_MISSILES(MINOR, BRINSTAR_PARLOR.and((a, s) -> a.contains(OPEN_MISSILE_DOORS))),
  EARLY_SUPERS_SUPER_MISSILES(MINOR, BRINSTAR_PARLOR.and((a, s) -> a.contains(OPEN_MISSILE_DOORS) && (a.contains(ProgressionAbility.SPEED_BOOSTER) || s.contains(MOCKBALL)))),
  EARLY_SUPERS_MAJOR(MAJOR, EARLY_SUPERS_SUPER_MISSILES.canAccess),
  EARLY_SUPERS_SECRET_MINOR_1(MINOR, EARLY_SUPERS_SUPER_MISSILES.canAccess),
  EARLY_SUPERS_SECRET_MINOR_2(MINOR, EARLY_SUPERS_SUPER_MISSILES.canAccess.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  ETECOONS_MAJOR(MAJOR, ETECOONS),
  ETECOONS_TUNNEL_MINOR(MINOR, ETECOONS),
  ETECOONS_CLIMB_MINOR(MINOR, ETECOONS),
  BIG_PINK_GRAPPLE_MINOR(MINOR, BIG_PINK),
  WAVE_GATE(MAJOR, BIG_PINK.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) &&
          (s.contains(WAVE_GATE_GLITCH) || a.contains(ProgressionAbility.WAVE_BEAM))
  )),
  ALPHA_SUPERS(MINOR, BIG_PINK),
  BIG_PINK_HIDDEN_HOPPERS(MINOR, BIG_PINK_GRAPPLE_MINOR.canAccess.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))),
  CHARGE_MINOR(MINOR, BIG_PINK),
  CHARGE_BEAM(MAJOR, BIG_PINK.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  WATERWAY(MAJOR, CHARGE_BEAM.canAccess.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) &&
          (s.contains(SHORT_CHARGE) || (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(ProgressionAbility.SPEED_BOOSTER)))
  )),
  GREEN_HILLS_MISSILES(MINOR, GREEN_HILLS),

  // Red Brinstar
  XRAY(MAJOR, RED_TOWER.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && (a.contains(ProgressionAbility.GRAPPLE) || s.contains(XRAY_DAMAGE_BOOST)))),
  ALPHA_POWER_BOMBS(MINOR, RED_TOWER),
  ALPHA_POWER_BOMBS_MISSILES(MINOR, ALPHA_POWER_BOMBS.canAccess.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))),
  BIG_HOPPER_POWER_BOMBS(MINOR, ALPHA_POWER_BOMBS.canAccess.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))),
  SPAZER(MAJOR, BRINSTAR_WATERWAY),
  KRAID_MISSILES(MINOR, BRINSTAR_WATERWAY.and((a, s) -> a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS))),
  KRAID_ETANK(MAJOR, BRINSTAR_WATERWAY),
  KRAID_REWARD(MAJOR, BRINSTAR_WATERWAY),

  // Upper Norfair
  HIGH_JUMP_MAJOR(MAJOR, BRINSTAR_WATERWAY),
  HIGH_JUMP_MINOR_1(MINOR, BRINSTAR_WATERWAY),
  HIGH_JUMP_MINOR_2(MINOR, BRINSTAR_WATERWAY),
  PARLOR_GREEN_GATE_MISSILES(MINOR, BRINSTAR_WATERWAY.and((a, s) ->
          (HEATED_UPPER_NORFAIR.test(a, s) && (a.contains(ProgressionAbility.GRAPPLE) || a.contains(SPACE_JUMP))) || // Vanilla access
          (a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && s.contains(GREEN_GATE_GLITCH)) // Access with green gate glitch
  )),
  ICE_BEAM(MAJOR, HEATED_UPPER_NORFAIR.and((a, s) -> a.contains(ProgressionAbility.SPEED_BOOSTER) || s.contains(MOCKBALL))),
  CROC_SPEEDWAY_MISSILES(MINOR, BRINSTAR_WATERWAY.and((a, s) ->
          a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) &&
         (a.contains(ProgressionAbility.SPEED_BOOSTER) || s.contains(MOCKBALL))
  )),
  CATHEDRAL(MINOR, HEATED_UPPER_NORFAIR),
  BUBBLE_MOUNTAIN_RESERVE(MAJOR, HEATED_UPPER_NORFAIR.and((a, s) ->
          a.contains(ProgressionAbility.GRAPPLE) || s.contains(IBJ) || a.contains(ProgressionAbility.ICE_BEAM)
  )),
  BUBBLE_MOUNTAIN_RESERVE_MINOR_1(MINOR, BUBBLE_MOUNTAIN_RESERVE.canAccess),
  BUBBLE_MOUNTAIN_RESERVE_MINOR_2(MINOR, BUBBLE_MOUNTAIN_RESERVE.canAccess),
  BUBBLE_MOUNTAIN_MISSILES(MINOR, HEATED_UPPER_NORFAIR.and((a, s) -> a.contains(CLEAR_BOMB_OBSTRUCTIONS))),
  SPEED_BOOSTER(MAJOR, HEATED_UPPER_NORFAIR),
  SPEED_BOOSTER_MISSILES(MINOR, HEATED_UPPER_NORFAIR),
  WAVE_MINOR(MINOR, HEATED_UPPER_NORFAIR),
  WAVE_BEAM(MAJOR, HEATED_UPPER_NORFAIR),
  CROC_ETANK(MAJOR, HEATED_UPPER_NORFAIR),
  CROC_POWER_BOMBS(MINOR, HEATED_UPPER_NORFAIR.and((a, s) ->
          a.contains(ProgressionAbility.GRAPPLE) ||
          (a.contains(ProgressionAbility.SPEED_BOOSTER) && a.contains(HIGH_JUMP))
  )),
  GRAPPLE_RISING_ACID_MISSILES(MINOR, HEATED_UPPER_NORFAIR),
  GRAPPLE_RAMP_ROOM_MISSILES(MINOR, HEATED_UPPER_NORFAIR.and((a, s) ->
          s.contains(IBJ) || (a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(ProgressionAbility.SPEED_BOOSTER))
  )),
  GRAPPLE(MAJOR, HEATED_UPPER_NORFAIR.and((a, s) ->
          (a.contains(CLEAR_POWER_BOMB_OBSTRUCTIONS) && a.contains(ProgressionAbility.SPEED_BOOSTER)) || // Vanilla access
          (s.contains(GREEN_GATE_GLITCH)) // If entering from behind
  )),

  // Lower Norfair
  GT_MISSILES(MINOR, LOWER_NORFAIR),
  GT_SUPERS(MINOR, LOWER_NORFAIR),
  GT_REWARD(MAJOR, Set.of(), LOWER_NORFAIR, (a, s) ->
          a.contains(SPACE_JUMP) || // Vanilla exit
          s.contains(IBJ) || // IBJ through the ceiling
          a.contains(ProgressionAbility.SPEED_BOOSTER) || // Exit using shine spark charge from GT's room
          s.contains(SPRING_BALL_DOUBLE_JUMP) || // Exit via spring ball glitch
          (a.contains(HIGH_JUMP) && a.contains(ProgressionAbility.SPEED_BOOSTER)) // Exit via high jump from energy refill entrance
  ),
  WORST_ROOM_IN_THE_GAME_MISSILES(MINOR, AMPHITHEATER),
  POWER_BOMBS_OF_SHAME(MINOR, AMPHITHEATER),
  RIDLEY_REWARD(MAJOR, AMPHITHEATER),
  FIRE_FLEES(MAJOR, AMPHITHEATER),
  RIDLEY_ESCAPE_POWER_BOMBS(MINOR, AMPHITHEATER),
  RIDLEY_ESCAPE_MISSILES_1(MINOR, AMPHITHEATER),
  RIDLEY_ESCAPE_MISSILES_2(MINOR, AMPHITHEATER),

  // Green maridia
  MARIDIA_SHINE_SPARK_MISSILES(MINOR, GREEN_MARIDIA_ENTRY.and((a, s) -> a.contains(FREE_LIQUID_MOVEMENT) && a.contains(ProgressionAbility.SPEED_BOOSTER))),
  MARIDIA_ALCOVE_SUPER_MISSILES(MINOR, GREEN_MARIDIA_FULL),
  MAMA_TURTLE_MINOR(MINOR, GREEN_MARIDIA_FULL),
  MAMA_TURTLE_MAJOR(MAJOR, GREEN_MARIDIA_FULL.and((a, s) ->
          a.contains(ProgressionAbility.GRAPPLE) || a.contains(SPACE_JUMP) || s.contains(IBJ)
  )),
  SHAKTOOL(MAJOR, GREEN_MARIDIA_FULL.and((a, s) ->
          (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(ProgressionAbility.GRAPPLE) && (a.contains(SPACE_JUMP) || a.contains(HIGH_JUMP) || s.contains(SPRING_BALL_DOUBLE_JUMP))) ||
          s.contains(ICE_CLIP)
  )),
  LEFT_SAND_PIT_MAJOR(MAJOR, GREEN_MARIDIA_FULL.and((a, s) -> a.contains(FREE_LIQUID_MOVEMENT))),
  LEFT_SAND_PIT_MINOR(MINOR, LEFT_SAND_PIT_MAJOR.canAccess),
  RIGHT_SAND_PIT_MINOR_1(MINOR, LEFT_SAND_PIT_MAJOR.canAccess),
  RIGHT_SAND_PIT_MINOR_2(MINOR, LEFT_SAND_PIT_MAJOR.canAccess),

  // Yellow maridia
  PLASMA_ACCESS_MISSILES(MINOR, GREEN_MARIDIA_FULL),
  HIDDEN_PIT_MISSILES(MINOR, GREEN_MARIDIA_FULL),
  HIDDEN_PIT_SUPERS(MINOR, GREEN_MARIDIA_FULL),
  PLASMA_BEAM(MAJOR, Set.of(), BOTWOON, BOTWOON.and((a, s) ->
         (a.contains(SPACE_JUMP) || s.contains(IBJ) || s.contains(SHORT_CHARGE)) && // Allows to get to the exit
         a.contains(PIERCING_DAMAGE) // Allows to kill enemies to open the door
  )),

  // Maridia lab
  MARIDIA_LAB_GATEWAY_MINOR_1(MINOR, GREEN_MARIDIA_FULL.and((a, s) -> a.contains(FREE_LIQUID_MOVEMENT) && a.contains(ProgressionAbility.SPEED_BOOSTER))),
  MARIDIA_LAB_GATEWAY_MINOR_2(MINOR, MARIDIA_LAB_GATEWAY_MINOR_1.canAccess),
  BOTWOON_REWARD(MAJOR, BOTWOON),
  DRAYGON_MINOR(MINOR, BOTWOON),
  DRAYGON_REWARD(MAJOR, Set.of(), BOTWOON, BOTWOON.and((a, s) ->
          s.contains(GRAPPLE_GRAVITY_LAUNCH) ||
          s.contains(GRAVITY_JUMP) ||
          (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(SPACE_JUMP)) ||
          (a.contains(FREE_LIQUID_MOVEMENT) && s.contains(IBJ)) ||
          (a.contains(FREE_LIQUID_MOVEMENT) && a.contains(ProgressionAbility.SPEED_BOOSTER) && a.contains(HIGH_JUMP))
  )),

  // Wrecked ship
  WRECKED_SHIP_ETANK(MAJOR, SPIKY_DEATH_ROOM),
  WRECKED_SHIP_EXTRA_MAJOR(MAJOR, WRECKED_SHIP),
  LOWER_WRECKED_SHIP_MISSILES(MINOR, WRECKED_SHIP),
  LOWER_WRECKED_SHIP_SUPERS(MINOR, WRECKED_SHIP),
  WRECKED_SHIP_ATTIC_MINOR(MINOR, WRECKED_SHIP),
  BOWLING_MAJOR(MAJOR, WRECKED_SHIP),
  BOWLING_MINOR(MINOR, WRECKED_SHIP),
  WRECKED_SHIP_RESERVE(MAJOR, WRECKED_SHIP.and((a, s) -> a.contains(ProgressionAbility.SPEED_BOOSTER)))
  ;

  Item.Type type;
  Set<Item> blacklist;
  ProgressionRule canAccess;
  ProgressionRule canExit;

  ItemLocation(Item.Type type, ProgressionRule canAccess) {
    this(type, Set.of(), canAccess, (a, s) -> true);
  }

  ItemLocation(Item.Type type, Set<Item> blacklist) {
    this(type, blacklist, (a, s) -> true, (a, s) -> true);
  }

  ItemLocation(Item.Type type, Set<Item> blacklist, ProgressionRule canAccess) {
    this(type, blacklist, canAccess, (a, s) -> true);
  }

  ItemLocation(Item.Type type, Set<Item> blacklist, ProgressionRule canAccess, ProgressionRule canExit) {
    this.type = type;
    this.canAccess = canAccess;
    this.canExit = canExit;
    this.blacklist = blacklist;
  }

}