package com.github.tylersharpe.supermetroidrandomizer;

import java.util.EnumSet;
import java.util.Set;

import static com.github.tylersharpe.supermetroidrandomizer.AccessPredicate.*;
import static com.github.tylersharpe.supermetroidrandomizer.Item.Type.MAJOR;
import static com.github.tylersharpe.supermetroidrandomizer.Item.Type.MINOR;
import static com.github.tylersharpe.supermetroidrandomizer.Item.VARIA_SUIT;
import static com.github.tylersharpe.supermetroidrandomizer.ItemLocation.Type.*;
import static com.github.tylersharpe.supermetroidrandomizer.ProgressionAbility.*;
import static com.github.tylersharpe.supermetroidrandomizer.Strat.*;

public enum ItemLocation {

    /****************************** Blue Brinstar ******************************/
    MORPH_BALL(
        "Morph Ball",
        MAJOR,
        NORMAL,
        0x786de,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        ALWAYS,
        ALWAYS
    ),

    ALPHA_MISSILES(
        "Missiles (alpha missiles)",
        MINOR,
        CHOZO,
        0x78802,
        (a, s) -> a.contains(MORPH)
    ),

    BLUE_BRINSTAR_ENERGY_TANK(
        "Energy Tank (blue Brinstar)",
        MAJOR,
        HIDDEN,
        0x7879e,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        (a, s) -> a.contains(MISSILES) && (
            // vanilla
            (a.contains(HIGH_JUMP)) ||

            // IBJ
            (s.contains(INFINITE_BOMB_JUMP) && a.contains(MORPH) && a.contains(ProgressionAbility.BOMBS))
        ),
        ALWAYS
    ),

    BLUE_BRINSTAR_FAR_RIGHT_MISSILES(
        "Missiles (blue Brinstar, far right)",
        MINOR,
        NORMAL,
        0x78798,
        (a, s) -> a.contains(MISSILES) && a.contains(MORPH)
    ),

    BILLIE_MAYS_MISSILES_1(
        "Missiles (Billie Mays 1)",
        MINOR,
        NORMAL,
        0x78836,
        (a, s) -> a.contains(MISSILES) &&
            a.contains(ProgressionAbility.POWER_BOMBS) &&
            a.contains(ProgressionAbility.SPEED_BOOSTER)
    ),

    BILLIE_MAYS_MISSILES_2(
        "Missiles (Billie Mays 2)",
        MINOR,
        HIDDEN,
        0x7883c,
        BILLIE_MAYS_MISSILES_1.canAccess
    ),

    BLUE_BRINSTAR_POWER_BOMBS(
        "Power Bombs (blue Brinstar)",
        MINOR,
        NORMAL,
        0x7874c,
        (a, s) -> a.contains(ProgressionAbility.POWER_BOMBS)
    ),

    /****************************** West Crateria ******************************/
    OLD_MOTHER_BRAIN(
        "Missiles (old mother brain)",
        MINOR,
        NORMAL,
        0x783ee,
        (a, s) -> a.contains(DESTROY_BOMB_BLOCKS)
    ),

    CRATERIA_CLIMB_SUPERS(
        "Super Missiles (Crateria climb)",
        MINOR,
        NORMAL,
        0x78478,
        (a, s) -> a.contains(ProgressionAbility.POWER_BOMBS) &&
            a.contains(ProgressionAbility.SPEED_BOOSTER) &&
            (a.contains(ProgressionAbility.ICE_BEAM) || s.contains(SHORT_CHARGE)
        )
    ),

    BOMBS(
        "Bombs",
        MAJOR,
        CHOZO,
        0x78404,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        (a, s) -> a.contains(MORPH) && a.contains(MISSILES),
        ALWAYS
    ),

    CRATERIA_PARLOR_MISSILES(
        "Missiles (Crateria parlor)",
        MINOR,
        NORMAL,
        0x78486,
        (a, s) -> a.contains(MORPH) && (
            a.contains(ProgressionAbility.BOMBS) || a.contains(ProgressionAbility.POWER_BOMBS)
        )
    ),

    LANDING_SITE_POWER_BOMBS(
        "Power Bombs (near ship)",
        MINOR,
        NORMAL,
        0x781cc,
        (a, s) ->
            a.contains(ProgressionAbility.POWER_BOMBS) && (
                a.contains(ProgressionAbility.SPEED_BOOSTER) ||
                a.contains(ProgressionAbility.SPACE_JUMP) ||
                (a.contains(ProgressionAbility.BOMBS) && s.contains(INFINITE_BOMB_JUMP))
            )
        ),

    GAUNTLET_ENERGY_TANK(
        "Energy Tank (gauntlet)",
        MAJOR,
        NORMAL,
        0x78264,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        (a, s) -> a.contains(DESTROY_BOMB_BLOCKS),
        ALWAYS
    ),

    GAUNTLET_MISSILES_LEFT(
        "Missiles (gauntlet left)",
        MINOR,
        NORMAL,
        0x7846a,
        GAUNTLET_ENERGY_TANK.canAccess.and((a, s) -> a.contains(MORPH) && (
            a.contains(ProgressionAbility.BOMBS) || a.contains(ProgressionAbility.POWER_BOMBS))
        )
    ),

    GAUNTLET_MISSILES_RIGHT(
        "Missiles (gauntlet right)",
        MINOR,
        NORMAL,
        0x7846a,
        GAUNTLET_MISSILES_LEFT.canAccess
    ),

    TERMINATOR(
        "Energy Tank (terminator)",
        MAJOR,
        NORMAL,
        0x78432,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        (a, s) -> a.contains(DESTROY_BOMB_BLOCKS) || s.contains(SHORT_CHARGE),
        ALWAYS
    ),

    /****************************** East Crateria ******************************/
    MOAT_MISSILES(
        "Missiles (Crateria moat)",
        MINOR,
        NORMAL,
        0x78248,
        MOAT
    ),

    OCEAN_LOWER_MISSILES(
        "Missiles (outside Wrecked Ship bottom)",
        MINOR,
        NORMAL,
        0x781e8,
        WRECKED_SHIP
    ),

    OCEAN_MID_MISSILES(
        "Missiles (outside Wrecked Ship middle)",
        MINOR,
        NORMAL,
        0x781f4,
        WRECKED_SHIP
    ),

    OCEAN_TOP_MISSILES(
        "Missiles (outside Wrecked Ship top)",
        MINOR,
        HIDDEN,
        0x781ee,
        WRECKED_SHIP
    ),

    /****************************** Green / pink Brinstar ******************************/
    EARLY_SUPERS_RUNWAY_MISSILES(
        "Missiles (early supers, beneath runway)",
        MINOR,
        NORMAL,
        0x78518,
        BRINSTAR_PARLOR.and((a, s) -> a.contains(MISSILES))
    ),

    EARLY_SUPERS_SUPER_MISSILES(
        "Super Missiles (early supers)",
        MINOR,
        NORMAL,
        0x7851e,
        BRINSTAR_PARLOR.and((a, s) ->
            a.contains(MISSILES) && (
                a.contains(ProgressionAbility.SPEED_BOOSTER) || s.contains(MOCKBALL)
            )
        )
    ),

    GREEN_BRINSTAR_RESERVE(
        "Reserve Tank (Brinstar)",
        MAJOR,
        CHOZO,
        0x7852c,
        EARLY_SUPERS_SUPER_MISSILES.canAccess
    ),

    EARLY_SUPERS_SECRET_MISSILES_1(
        "Missiles (behind green Brinstar reserve)",
        MINOR,
        NORMAL,
        0x78538,
        EARLY_SUPERS_SUPER_MISSILES.canAccess
    ),

    EARLY_SUPERS_SECRET_MISSILES_2(
        "Missiles (behind green Brinstar reserve, secret)",
        MINOR,
        HIDDEN,
        0x78532,
        EARLY_SUPERS_SUPER_MISSILES.canAccess.and((a, s) -> a.contains(MORPH) && a.contains(DESTROY_BOMB_BLOCKS))
    ),

    ETECOONS_ENERGY_TANK(
        "Energy Tank (etecoons)",
        MAJOR,
        NORMAL,
        0x787c2,
        ETECOONS
    ),

    ETECOONS_SUPER_MISSILES(
        "Super Missiles (etecoons)",
        MINOR,
        NORMAL,
        0x787d0,
        ETECOONS
    ),

    ETECOONS_POWER_BOMBS(
        "Power Bombs (etecoons)",
        MINOR,
        CHOZO,
        0x784ac,
        ETECOONS
    ),

    BIG_PINK_GRAPPLE_MISSILES(
        "Missiles (big pink, grapple)",
        MINOR,
        NORMAL,
        0x78608,
        BIG_PINK
    ),

    WAVE_GATE(
        "Energy Tank (wave gate)",
        MAJOR,
        NORMAL,
        0x78824,
        BIG_PINK.and((a, s) ->
            a.contains(ProgressionAbility.POWER_BOMBS) && (
                s.contains(WAVE_GATE_GLITCH) || a.contains(ProgressionAbility.WAVE_BEAM)
            )
        )
    ),

    ALPHA_SUPERS(
        "Super Missiles (spore spawn reward)",
        MINOR,
        CHOZO,
        0x784e4,
        BIG_PINK
    ),

    MISSION_IMPOSSIBLE_POWER_BOMBS(
        "Power Bombs (big pink, behind grapple missiles)",
        MINOR,
        NORMAL,
        0x7865c,
        BIG_PINK_GRAPPLE_MISSILES.canAccess.and((a, s) -> a.contains(ProgressionAbility.POWER_BOMBS))
    ),

    CHARGE_MISSILES(
        "Missiles (near charge beam)",
        MINOR,
        NORMAL,
        0x7860e,
        BIG_PINK
    ),

    CHARGE_BEAM(
        "Charge Beam",
        MAJOR,
        CHOZO,
        0x78614,
        BIG_PINK.and((a, s) -> a.contains(MORPH) && a.contains(DESTROY_BOMB_BLOCKS))
    ),

    WATERWAY(
        "Energy Tank (waterway)",
        MAJOR,
        NORMAL,
        0x787fa,
        CHARGE_BEAM.canAccess.and((a, s) ->
            a.contains(ProgressionAbility.POWER_BOMBS) && (
                s.contains(SHORT_CHARGE) || (a.contains(GRAVITY) && a.contains(ProgressionAbility.SPEED_BOOSTER))
            )
        )
    ),

    GREEN_HILLS_MISSILES(
        "Missiles (green hills pipe)",
        MINOR,
        NORMAL,
        0x78676,
        GREEN_HILLS
    ),

    /****************************** Red Brinstar ******************************/
    XRAY(
        "X-Ray",
        MAJOR,
        CHOZO,
        0x78876,
        UPPER_RED_TOWER.and((a, s) ->
            a.contains(ProgressionAbility.POWER_BOMBS) && (
                a.contains(ProgressionAbility.GRAPPLE) || s.contains(XRAY_DAMAGE_BOOST)
            )
        )
    ),

    ALPHA_POWER_BOMBS(
        "Power Bombs (alpha)",
        MINOR,
        CHOZO,
        0x7890e,
        Set.of(),
        UPPER_RED_TOWER,
        (a, s) -> a.contains(ProgressionAbility.POWER_BOMBS)
    ),

    ALPHA_POWER_BOMBS_MISSILES(
        "Missiles (behind alpha power bombs)",
        MINOR,
        NORMAL,
        0x78914,
        ALPHA_POWER_BOMBS.canAccess.and((a, s) -> a.contains(ProgressionAbility.POWER_BOMBS))
    ),

    BIG_HOPPER_POWER_BOMBS(
        "Power Bombs (big hopper room, near alpha power bombs)",
        MINOR,
        NORMAL,
        0x788ca,
        ALPHA_POWER_BOMBS.canAccess.and((a, s) -> a.contains(ProgressionAbility.POWER_BOMBS))
    ),

    SPAZER(
        "Spazer",
        MAJOR,
        CHOZO,
        0x7896e,
        LOWER_RED_TOWER
    ),

    KRAID_MISSILES(
        "Missiles (Kraid's lair)",
        MINOR,
        HIDDEN,
        0x789ec,
        LOWER_RED_TOWER.and((a, s) -> a.contains(ProgressionAbility.POWER_BOMBS))
    ),

    KRAID_WAREHOUSE(
        "Energy Tank (Kraid's warehouse)",
        MAJOR,
        HIDDEN,
        0x7899c,
        LOWER_RED_TOWER
    ),

    KRAID_REWARD(
        "Varia Suit",
        MAJOR,
        CHOZO,
        0x78aca,
        LOWER_RED_TOWER
    ),

    /****************************** Upper Norfair ******************************/
    HI_JUMP_BOOTS(
        "Hi-Jump Boots",
        MAJOR,
        CHOZO,
        0x78bac,
        LOWER_RED_TOWER
    ),

    HI_JUMP_MISSILES(
        "Missiles (Hi-Jump)",
        MINOR,
        NORMAL,
        0x78be6,
        LOWER_RED_TOWER
    ),

    HI_JUMP_ENERGY_TANK(
        "Energy Tank (Hi-Jump, changed to minor item)",
        MINOR,
        NORMAL,
        0x78bec,
        LOWER_RED_TOWER
    ),

    BUSINESS_CENTER_GREEN_GATE_MISSILES(
        "Missiles (Norfair business center, behind green gate)",
        MINOR,
        NORMAL,
        0x78bc0,
        LOWER_RED_TOWER.and((a, s) ->
            // Vanilla access
            (HEATED_UPPER_NORFAIR.test(a, s) && (a.contains(ProgressionAbility.GRAPPLE) || a.contains(ProgressionAbility.SPACE_JUMP))) ||

            // Access with green gate glitch
            (a.contains(ProgressionAbility.POWER_BOMBS) && s.contains(GREEN_GATE_GLITCH))
        )
    ),

    ICE_BEAM(
        "Ice Beam",
        MAJOR,
        CHOZO,
        0x78b24,
        HEATED_UPPER_NORFAIR.and((a, s) -> a.contains(ProgressionAbility.SPEED_BOOSTER) || s.contains(MOCKBALL))
    ),

    CROC_SPEEDWAY_MISSILES(
        "Missiles (Croc's speedway)",
        MINOR,
        HIDDEN,
        0x78b46,
        LOWER_RED_TOWER.and((a, s) ->
            a.contains(ProgressionAbility.POWER_BOMBS) && (
                a.contains(ProgressionAbility.SPEED_BOOSTER) || s.contains(MOCKBALL)
            )
        )
    ),

    CROC_ENERGY_TANK(
        "Energy Tank (Crocomire)",
        MAJOR,
        NORMAL,
        0x78ba4,
        HEATED_UPPER_NORFAIR
    ),

    CROC_POWER_BOMBS(
        "Power Bombs (Crocomire)",
        MINOR,
        NORMAL,
        0x78c04,
        HEATED_UPPER_NORFAIR.and((a, s) ->
            a.contains(ProgressionAbility.GRAPPLE) || (
                a.contains(ProgressionAbility.SPEED_BOOSTER) && a.contains(HIGH_JUMP)
            )
        )
    ),

    GRAPPLE_RISING_ACID_MISSILES(
        "Missiles (below Crocomire, rising acid room)",
        MINOR,
        NORMAL,
        0x78c14,
        HEATED_UPPER_NORFAIR
    ),

    GRAPPLE_RAMP_ROOM_MISSILES(
        "Missiles (large room before grapple)",
        MINOR,
        NORMAL,
        0x78c2a,
        HEATED_UPPER_NORFAIR.and((a, s) ->
            s.contains(INFINITE_BOMB_JUMP) || (
                a.contains(ProgressionAbility.POWER_BOMBS) && a.contains(ProgressionAbility.SPEED_BOOSTER)
            )
        )
    ),

    GRAPPLE(
        "Grapple Beam",
        MAJOR,
        CHOZO,
        0x78c36,
        HEATED_UPPER_NORFAIR.and((a, s) ->
            // Vanilla access
            (a.contains(ProgressionAbility.POWER_BOMBS) && a.contains(ProgressionAbility.SPEED_BOOSTER)) ||

            // Entering from behind
            (s.contains(GREEN_GATE_GLITCH))
        )
    ),

    CATHEDRAL_MISSILES(
        "Missiles (cathedral)",
        MINOR,
        HIDDEN,
        0x78ae4,
        HEATED_UPPER_NORFAIR
    ),

    BUBBLE_MOUNTAIN_RESERVE(
        "Reserve Tank (bubble mountain)",
        MAJOR,
        CHOZO,
        0x78c3e,
        HEATED_UPPER_NORFAIR.and((a, s) ->
            a.contains(ProgressionAbility.GRAPPLE) || s.contains(INFINITE_BOMB_JUMP) || a.contains(ProgressionAbility.ICE_BEAM)
        )
    ),

    BUBBLE_MOUNTAIN_RESERVE_MISSILES(
        "Missiles (bubble mountain reserve, behind green door)",
        MINOR,
        NORMAL,
        0x78c52,
        BUBBLE_MOUNTAIN_RESERVE.canAccess
    ),

    BUBBLE_MOUNTAIN_RESERVE_HIDDEN_MISSILES(
        "Missiles (bubble mountain reserve, hidden in pedestal)",
        MINOR,
        HIDDEN,
        0x78c44,
        BUBBLE_MOUNTAIN_RESERVE.canAccess
    ),

    BUBBLE_MOUNTAIN_MISSILES(
        "Missiles (bubble mountain main area)",
        MINOR,
        NORMAL,
        0x78c66,
        HEATED_UPPER_NORFAIR.and((a, s) -> a.contains(DESTROY_BOMB_BLOCKS))
    ),

    SPEED_BOOSTER(
        "Speed Booster",
        MAJOR,
        CHOZO,
        0x78c82,
        HEATED_UPPER_NORFAIR
    ),

    SPEED_BOOSTER_MISSILES(
        "Missiles (before speed booster)",
        MINOR,
        HIDDEN,
        0x78c74,
        HEATED_UPPER_NORFAIR
    ),

    WAVE_MISSILES(
        "Missiles (before wave beam)",
        MINOR,
        NORMAL,
        0x78cbc,
        HEATED_UPPER_NORFAIR
    ),

    WAVE_BEAM(
        "Wave Beam",
        MAJOR,
        CHOZO,
        0x78cca,
        HEATED_UPPER_NORFAIR
    ),

    /****************************** Lower Norfair ******************************/
    GT_MISSILES(
        "Missiles (GT)",
        MINOR,
        NORMAL,
        0x78e6e,
        LOWER_NORFAIR
    ),

    GT_SUPERS(
        "Super Missiles",
        MINOR,
        HIDDEN,
        0x78e74,
        LOWER_NORFAIR
    ),

    GT_REWARD(
        "Screw Attack",
        MAJOR,
        CHOZO,
        0x79110,
        Set.of(),
        LOWER_NORFAIR,
        (a, s) ->
            // Vanilla exit
            a.contains(ProgressionAbility.SPACE_JUMP) ||

            // INFINITE_BOMB_JUMP through the ceiling
            s.contains(INFINITE_BOMB_JUMP) ||

            // Exit using shine spark
            a.contains(ProgressionAbility.SPEED_BOOSTER) ||

            // Exit via spring ball glitch
            s.contains(SPRING_BALL_DOUBLE_JUMP) ||

            // Exit via high jump from energy refill entrance
            (a.contains(HIGH_JUMP) && a.contains(ProgressionAbility.SPEED_BOOSTER))
    ),

    MICKEY_MOUSE_MISSILES(
        "Missiles (lower Norfair, mickey mouse room)",
        MINOR,
        NORMAL,
        0x78f30,
        AMPHITHEATER
    ),

    POWER_BOMBS_OF_SHAME(
        "Power Bombs (power bombs of shame)",
        MINOR,
        NORMAL,
        0x790c0,
        AMPHITHEATER
    ),

    RIDLEY_REWARD(
        "Energy Tank (Ridley)",
        MAJOR,
        HIDDEN,
        0x79108,
        AMPHITHEATER
    ),

    FIRE_FLEAS(
        "Energy Tank (fire fleas)",
        MAJOR,
        NORMAL,
        0x79184,
        AMPHITHEATER
    ),

    RIDLEY_ESCAPE_POWER_BOMBS(
        "Power Bombs (Ridley escape)",
        MINOR,
        NORMAL,
        0x78fd2,
        AMPHITHEATER
    ),

    RIDLEY_ESCAPE_MISSILES_1(
        "Missiles (Ridley escape, above fire fleas)",
        MINOR,
        NORMAL,
        0x78fca,
        AMPHITHEATER
    ),

    RIDLEY_ESCAPE_MISSILES_2(
        "Missiles (Ridley escape, below wave beam)",
        MINOR,
        NORMAL,
        0x79100,
        AMPHITHEATER
    ),

    /****************************** Green Maridia ******************************/
    MARIDIA_SHINE_SPARK_MISSILES(
        "Missiles (green Maridia shine spark)",
        MINOR,
        NORMAL,
        0x7c437,
        GREEN_MARIDIA_ENTRY.and((a, s) -> a.contains(GRAVITY) && a.contains(ProgressionAbility.SPEED_BOOSTER))
    ),

    MARIDIA_ALCOVE_SUPER_MISSILES(
        "Super Missiles (green Maridia alcove)",
        MINOR,
        NORMAL,
        0x7c43d,
        GREEN_MARIDIA_FULL
    ),

    MAMA_TURTLE_MISSILES(
        "Missiles (mama turtle)",
        MINOR,
        HIDDEN,
        0x7c483,
        GREEN_MARIDIA_FULL
    ),

    MAMA_TURTLE_ENERGY_TANK(
        "Energy Tank (mama turtle)",
        MAJOR,
        NORMAL,
        0x7c47d,
        GREEN_MARIDIA_FULL.and((a, s) ->
            a.contains(ProgressionAbility.GRAPPLE) || a.contains(ProgressionAbility.SPACE_JUMP) || s.contains(INFINITE_BOMB_JUMP)
        )
    ),

    SPRING_BALL(
        "Spring Ball",
        MAJOR,
        CHOZO,
        0x7c6e5,
        GREEN_MARIDIA_FULL.and((a, s) ->
            s.contains(ICE_CLIP) ||
            (a.contains(GRAVITY) && a.contains(ProgressionAbility.GRAPPLE) && (
                a.contains(ProgressionAbility.SPACE_JUMP) ||
                a.contains(HIGH_JUMP) ||
                s.contains(SPRING_BALL_DOUBLE_JUMP))
            )
        )
    ),

    LEFT_SAND_PIT_RESERVE(
        "Reserve Tank (left Maridia sand pit)",
        MAJOR,
        CHOZO,
        0x7c5e3,
        GREEN_MARIDIA_FULL.and((a, s) -> a.contains(GRAVITY))
    ),

    LEFT_SAND_PIT_MISSILES(
        "Missiles (left Maridia sand pit)",
        MINOR,
        NORMAL,
        0x7c5dd,
        LEFT_SAND_PIT_RESERVE.canAccess
    ),

    RIGHT_SAND_PIT_MISSILES(
        "Missiles (right Maridia sand pit)",
        MINOR,
        NORMAL,
        0x7c5eb,
        LEFT_SAND_PIT_RESERVE.canAccess
    ),

    RIGHT_SAND_PIT_POWER_BOMBS(
        "Power Bombs (right Maridia sand pit)",
        MINOR,
        NORMAL,
        0x7c5f1,
        RIGHT_SAND_PIT_MISSILES.canAccess
    ),

    /****************************** Yellow Maridia ******************************/
    PLASMA_ACCESS_MISSILES(
        "Missiles (yellow Maridia, plasma beam access, false wall)",
        MINOR,
        NORMAL,
        0x7c533,
        GREEN_MARIDIA_FULL
    ),

    YELLOW_MARIDIA_PIT_MISSILES(
        "Missiles (yellow Maridia pit)",
        MINOR,
        NORMAL,
        0x7c4b5,
        GREEN_MARIDIA_FULL
    ),

    YELLOW_MARIDIA_PIT_SUPERS(
        "Super Missiles (yellow Maridia pit)",
        MINOR,
        NORMAL,
        0x7c4af,
        GREEN_MARIDIA_FULL
    ),

    PLASMA_BEAM(
        "Plasma Beam",
        MAJOR,
        CHOZO,
        0x7c559,
        Set.of(),
        BOTWOON,
        BOTWOON.and((a, s) ->
            // Allows to get to the exit
            (a.contains(ProgressionAbility.SPACE_JUMP) || s.contains(INFINITE_BOMB_JUMP) || s.contains(SHORT_CHARGE)) &&

            // Allows to kill enemies to open the door
            (a.contains(ProgressionAbility.PLASMA_BEAM) || a.contains(ProgressionAbility.SCREW_ATTACK))
        )
    ),

    /****************************** Maridia lab ******************************/
    AQUEDUCT_MISSILES(
        "Missiles (aqueduct)",
        MINOR,
        NORMAL,
        0x7c603,
        GREEN_MARIDIA_FULL.and((a, s) -> a.contains(GRAVITY) && a.contains(ProgressionAbility.SPEED_BOOSTER))
    ),

    AQUEDUCT_SUPER_MISSILES(
        "Super Missiles (aqueduct)",
        MINOR,
        NORMAL,
        0x7c609,
        AQUEDUCT_MISSILES.canAccess
    ),

    BOTWOON_ENERGY_TANK(
        "Energy Tank (Botwoon)",
        MAJOR,
        NORMAL,
        0x7c755,
        Set.of(),
        BOTWOON,
        BOTWOON.and((a, s) -> a.contains(GRAVITY))
    ),

    DRAYGON_MINOR(
        "Missiles (before Draygon)",
        MINOR,
        HIDDEN,
        0x7c74d,
        BOTWOON
    ),

    SPACE_JUMP(
        "Space Jump",
        MAJOR,
        CHOZO,
        0x7c7a7,
        Set.of(),
        BOTWOON,
        (a, s) ->
            s.contains(GRAPPLE_GRAVITY_LAUNCH) ||
            s.contains(GRAVITY_JUMP) ||
            (a.contains(GRAVITY) && a.contains(ProgressionAbility.SPACE_JUMP)) ||
            (a.contains(GRAVITY) && s.contains(INFINITE_BOMB_JUMP)) ||
            (a.contains(GRAVITY) && a.contains(ProgressionAbility.SPEED_BOOSTER) && a.contains(HIGH_JUMP))
    ),

    /****************************** Wrecked Ship ******************************/
    WRECKED_SHIP_ENERGY_TANK(
        "Energy Tank (Wrecked Ship)",
        MAJOR,
        NORMAL,
        0x7c337,
        SPIKY_DEATH_ROOM
    ),

    WRECKED_SHIP_RIGHT_SUPER(
        "Super Missiles (Wrecked Ship lower right, extra major)",
        MAJOR,
        NORMAL,
        0x7c365,
        WRECKED_SHIP
    ),

    LOWER_WRECKED_SHIP_MISSILES(
        "Missiles (Wrecked Ship lower)",
        MINOR,
        NORMAL,
        0x7c265,
        WRECKED_SHIP
    ),

    WRECKED_SHIP_LEFT_SUPERS(
        "Super Missiles (Wrecked Ship, lower left)",
        MINOR,
        NORMAL,
        0x7c357,
        WRECKED_SHIP
    ),

    WRECKED_SHIP_ATTIC_MISSILES(
        "Missiles (Wrecked Ship attic)",
        MINOR,
        NORMAL,
        0x7c319,
        WRECKED_SHIP
    ),

    GRAVITY_SUIT(
        "Gravity Suit",
        MAJOR,
        CHOZO,
        0x7c36d,
        WRECKED_SHIP
    ),

    BOWLING_MISSILES(
        "Missiles (bowling)",
        MINOR,
        NORMAL,
        0x7c2ef,
        WRECKED_SHIP
    ),

    WRECKED_SHIP_RESERVE(
        "Reserve Tank (Wrecked Ship)",
        MAJOR,
        CHOZO,
        0x7c2e9,
        WRECKED_SHIP.and((a, s) -> a.contains(ProgressionAbility.SPEED_BOOSTER))
    );

    enum Type { NORMAL, HIDDEN, CHOZO }

    final String name;
    final Item.Type itemType;
    final ItemLocation.Type locationType;
    final Set<Item> itemBlacklist;
    final AccessPredicate canAccess;
    final AccessPredicate canExit;
    final int hexAddress;

    ItemLocation(String name, Item.Type itemType, ItemLocation.Type locationType, int hexAddress, AccessPredicate canAccess) {
        this(name, itemType, locationType, hexAddress, Set.of(), canAccess, ALWAYS);
    }

    ItemLocation(String name, Item.Type itemType, ItemLocation.Type locationType, int hexAddress, Set<Item> itemBlacklist, AccessPredicate canAccess, AccessPredicate canExit) {
        this.name = name;
        this.itemType = itemType;
        this.locationType = locationType;
        this.hexAddress = hexAddress;
        this.canAccess = canAccess;
        this.canExit = canExit;
        this.itemBlacklist = EnumSet.copyOf(itemBlacklist);
    }

}
