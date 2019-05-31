package com.github.tylersharpe.smrandomizer;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public enum Item {
  VARIA_SUIT(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.PREVENT_HEAT_DAMAGE)),
  GRAVITY_SUIT(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.FREE_LIQUID_MOVEMENT)),

  MORPH_BALL(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.MORPH)),
  BOMBS(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.CLEAR_BRITTLE_WALLS, ProgressionAbility.CLEAR_BOMB_OBSTRUCTIONS)),
  SPRING_BALL(Type.MAJOR, 1),
  SCREW_ATTACK(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.CLEAR_BRITTLE_WALLS, ProgressionAbility.PIERCING_DAMAGE)),

  HIGH_JUMP_BOOTS(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.HIGH_JUMP)),
  SPEED_BOOSTER(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.SPEED_BOOSTER)),
  SPACE_JUMP(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.SPACE_JUMP)),

  CHARGE_BEAM(Type.MAJOR, 1),
  WAVE_BEAM(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.WAVE_BEAM)),
  ICE_BEAM(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.ICE_BEAM)),
  SPAZER_BEAM(Type.MAJOR, 1),
  PLASMA_BEAM(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.PIERCING_DAMAGE)),

  ENERGY_TANK(Type.MAJOR, 14),
  RESERVE_TANK(Type.MAJOR, 4),

  MISSILES(Type.MINOR, 46, EnumSet.of(ProgressionAbility.OPEN_MISSILE_DOORS)),
  SUPER_MISSILES(Type.MINOR, 10, EnumSet.of(ProgressionAbility.OPEN_MISSILE_DOORS, ProgressionAbility.CLEAR_SUPER_MISSILE_OBSTRUCTIONS)),
  POWER_BOMBS(Type.MINOR, 10, EnumSet.of(ProgressionAbility.CLEAR_BOMB_OBSTRUCTIONS, ProgressionAbility.CLEAR_BRITTLE_WALLS, ProgressionAbility.CLEAR_POWER_BOMB_OBSTRUCTIONS)),
  GRAPPLE_BEAM(Type.MAJOR, 1, EnumSet.of(ProgressionAbility.GRAPPLE)),
  XRAY(Type.MAJOR, 1);

  enum Type { MAJOR, MINOR }

  static final Set<Item> MAJORS = Stream.of(values()).filter(i -> i.type == Type.MAJOR).collect(toCollection(() -> EnumSet.noneOf(Item.class)));
  static final Set<Item> MINORS = Stream.of(values()).filter(i -> i.type == Type.MINOR).collect(toCollection(() -> EnumSet.noneOf(Item.class)));

  Type type;
  Set<ProgressionAbility> abilities;
  int numAvailable;

  Item(Type type, int numAvailable) {
    this(type, numAvailable, Set.of());
  }

  Item(Type type, int numAvailable, Set<ProgressionAbility> abilities) {
    this.type = type;
    this.numAvailable = numAvailable;
    this.abilities = abilities;
  }

}
