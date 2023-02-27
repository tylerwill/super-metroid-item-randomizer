package com.github.tylersharpe.supermetroidrandomizer;

import java.util.EnumSet;
import java.util.Set;

import static com.github.tylersharpe.supermetroidrandomizer.Item.Type.MAJOR;
import static com.github.tylersharpe.supermetroidrandomizer.Item.Type.MINOR;
import static com.github.tylersharpe.supermetroidrandomizer.Item.VARIA_SUIT;
import static com.github.tylersharpe.supermetroidrandomizer.ItemLocation.Type.*;
import static com.github.tylersharpe.supermetroidrandomizer.PlayerState.*;
import static com.github.tylersharpe.supermetroidrandomizer.Strat.*;

public enum ItemLocation {

    /****************************** Blue Brinstar ******************************/
    MORPH_BALL(
        "Morph Ball",
        MAJOR,
        NORMAL,
        0x786de,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        ANY,
        ANY
    ),

    ALPHA_MISSILES(
        "Missiles (alpha missiles)",
        MINOR,
        CHOZO,
        0x78802,
        (i, s) -> i.contains(Item.MORPH_BALL)
    ),

    BLUE_BRINSTAR_ENERGY_TANK(
        "Energy Tank (blue Brinstar)",
        MAJOR,
        HIDDEN,
        0x7879e,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        CAN_OPEN_RED_DOORS.and((i, s) ->
            i.contains(Item.MISSILES) && (
                // vanilla
                i.contains(Item.HIGH_JUMP_BOOTS) ||

                // IBJ
                s.contains(INFINITE_BOMB_JUMP)
            )
        ),
        ANY
    ),

    BLUE_BRINSTAR_FAR_RIGHT_MISSILES(
        "Missiles (blue Brinstar, far right)",
        MINOR,
        NORMAL,
        0x78798,
        (i, s) -> i.contains(Item.MISSILES) && i.contains(Item.MORPH_BALL)
    ),

    BILLIE_MAYS_MISSILES_1(
        "Missiles (Billie Mays 1)",
        MINOR,
        NORMAL,
        0x78836,
        (i, s) -> i.contains(Item.MISSILES)
            && i.contains(Item.MORPH_BALL)
            && i.contains(Item.POWER_BOMBS)
            && i.contains(Item.SPEED_BOOSTER)
    ),

    BILLIE_MAYS_MISSILES_2(
        "Missiles (Billie Mays 2)",
        MINOR,
        HIDDEN,
        0x7883c,
        BILLIE_MAYS_MISSILES_1.requiredAccessState
    ),

    BLUE_BRINSTAR_POWER_BOMBS(
        "Power Bombs (blue Brinstar)",
        MINOR,
        NORMAL,
        0x7874c,
        (i, s) -> i.contains(Item.MORPH_BALL) && i.contains(Item.POWER_BOMBS)
    ),

    /****************************** West Crateria ******************************/
    OLD_MOTHER_BRAIN(
        "Missiles (old mother brain)",
        MINOR,
        NORMAL,
        0x783ee,
        PlayerState.CAN_LAY_ANY_BOMB_TYPE
    ),

    CRATERIA_CLIMB_SUPERS(
        "Super Missiles (Crateria climb)",
        MINOR,
        NORMAL,
        0x78478,
        (i, s) -> i.contains(Item.POWER_BOMBS)
            && i.contains(Item.MORPH_BALL)
            && i.contains(Item.SPEED_BOOSTER)
            && (i.contains(Item.ICE_BEAM) || s.contains(SHORT_CHARGE))
    ),

    BOMBS(
        "Bombs",
        MAJOR,
        CHOZO,
        0x78404,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        (i, s) -> i.contains(Item.MORPH_BALL) && i.contains(Item.MISSILES),
        ANY
    ),

    CRATERIA_PARLOR_MISSILES(
        "Missiles (Crateria parlor)",
        MINOR,
        NORMAL,
        0x78486,
        PlayerState.CAN_LAY_ANY_BOMB_TYPE
    ),

    LANDING_SITE_POWER_BOMBS(
        "Power Bombs (near ship)",
        MINOR,
        NORMAL,
        0x781cc,
        (i, s) ->
            i.contains(Item.POWER_BOMBS) && (
                i.contains(Item.SPEED_BOOSTER) ||
                i.contains(Item.SPACE_JUMP) ||
                s.contains(INFINITE_BOMB_JUMP)
            )
        ),

    GAUNTLET_ENERGY_TANK(
        "Energy Tank (gauntlet)",
        MAJOR,
        NORMAL,
        0x78264,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        PlayerState.CAN_DESTROY_BOMB_BLOCKS,
        ANY
    ),

    GAUNTLET_MISSILES_LEFT(
        "Missiles (gauntlet left)",
        MINOR,
        NORMAL,
        0x7846a,
        GAUNTLET_ENERGY_TANK.requiredAccessState.and(PlayerState.CAN_LAY_ANY_BOMB_TYPE)
    ),

    GAUNTLET_MISSILES_RIGHT(
        "Missiles (gauntlet right)",
        MINOR,
        NORMAL,
        0x7846a,
        GAUNTLET_MISSILES_LEFT.requiredAccessState
    ),

    TERMINATOR(
        "Energy Tank (terminator)",
        MAJOR,
        NORMAL,
        0x78432,
        Set.of(VARIA_SUIT, Item.GRAVITY_SUIT),
        PlayerState.CAN_DESTROY_BOMB_BLOCKS.or((i, s) -> s.contains(SHORT_CHARGE)),
        ANY
    ),

    /****************************** East Crateria ******************************/
    MOAT_MISSILES(
        "Missiles (Crateria moat)",
        MINOR,
        NORMAL,
        0x78248,
        PlayerState.CAN_ACCESS_CRATERIA_MOAT
    ),

    OCEAN_LOWER_MISSILES(
        "Missiles (outside Wrecked Ship bottom)",
        MINOR,
        NORMAL,
        0x781e8,
        CAN_ACCESS_WEST_WRECKED_SHIP_ENTRANCE.and(CAN_LAY_ANY_BOMB_TYPE)
    ),

    OCEAN_MID_MISSILES(
        "Missiles (outside Wrecked Ship middle)",
        MINOR,
        NORMAL,
        0x781f4,
        CAN_ACCESS_WEST_WRECKED_SHIP_ENTRANCE.and((i, s) -> i.contains(Item.SUPER_MISSILES))
    ),

    OCEAN_TOP_MISSILES(
        "Missiles (outside Wrecked Ship top)",
        MINOR,
        HIDDEN,
        0x781ee,
        CAN_ACCESS_WEST_WRECKED_SHIP_ENTRANCE
    ),

    /****************************** Green / pink Brinstar ******************************/
    EARLY_SUPERS_RUNWAY_MISSILES(
        "Missiles (early supers, beneath runway)",
        MINOR,
        NORMAL,
        0x78518,
        Set.of(),
        CAN_DESTROY_BOMB_BLOCKS.and(CAN_OPEN_RED_DOORS),
        CAN_LAY_ANY_BOMB_TYPE
    ),

    EARLY_SUPERS_SUPER_MISSILES(
        "Super Missiles (early supers)",
        MINOR,
        NORMAL,
        0x7851e,
        EARLY_SUPERS_RUNWAY_MISSILES.requiredAccessState.and((i, s) ->
            // vanilla
            i.contains(Item.SPEED_BOOSTER) ||

            // mockball from parlor running start
            s.contains(MOCKBALL)
        )
    ),

    BRINSTAR_PARLOR_RESERVE(
        "Reserve Tank (Brinstar parlor)",
        MAJOR,
        CHOZO,
        0x7852c,
        EARLY_SUPERS_SUPER_MISSILES.requiredAccessState
    ),

    EARLY_SUPERS_SECRET_MISSILES_1(
        "Missiles (behind green Brinstar reserve)",
        MINOR,
        NORMAL,
        0x78538,
        EARLY_SUPERS_SUPER_MISSILES.requiredAccessState.and((i, s) -> i.contains(Item.MORPH_BALL))
    ),

    EARLY_SUPERS_SECRET_MISSILES_2(
        "Missiles (behind green Brinstar reserve, secret)",
        MINOR,
        HIDDEN,
        0x78532,
        EARLY_SUPERS_SECRET_MISSILES_1.requiredAccessState.and(CAN_LAY_ANY_BOMB_TYPE)
    ),

    ETECOONS_ENERGY_TANK(
        "Energy Tank (etecoons)",
        MAJOR,
        NORMAL,
        0x787c2,
        CAN_DESTROY_BOMB_BLOCKS.and((i, s) -> i.contains(Item.MORPH_BALL) && i.contains(Item.POWER_BOMBS))
    ),

    ETECOONS_SUPER_MISSILES(
        "Super Missiles (etecoons)",
        MINOR,
        NORMAL,
        0x787d0,
        ETECOONS_ENERGY_TANK.requiredAccessState
    ),

    ETECOONS_POWER_BOMBS(
        "Power Bombs (etecoons)",
        MINOR,
        CHOZO,
        0x784ac,
        ETECOONS_ENERGY_TANK.requiredAccessState
    ),

    BIG_PINK_GRAPPLE_MISSILES(
        "Missiles (big pink, grapple)",
        MINOR,
        NORMAL,
        0x78608,
        CAN_ACCESS_BRINSTAR_BIG_PINK
    ),

    BRINSTAR_WAVE_GATE_ENERGY_TANK(
        "Energy Tank (wave gate)",
        MAJOR,
        NORMAL,
        0x78824,
        CAN_ACCESS_BRINSTAR_BIG_PINK
            .and(CAN_LAY_POWER_BOMBS)
            .and((i, s) -> i.contains(Item.WAVE_BEAM) || s.contains(WAVE_GATE_GLITCH))
    ),

    ALPHA_SUPERS(
        "Super Missiles (spore spawn reward)",
        MINOR,
        CHOZO,
        0x784e4,
        Set.of(),
        CAN_ACCESS_BRINSTAR_BIG_PINK,
        PlayerState.CAN_LAY_ANY_BOMB_TYPE.and((i, s) -> i.contains(Item.SUPER_MISSILES))
    ),

    MISSION_IMPOSSIBLE_POWER_BOMBS(
        "Power Bombs (big pink, behind grapple missiles)",
        MINOR,
        NORMAL,
        0x7865c,
        BIG_PINK_GRAPPLE_MISSILES.requiredAccessState.and(CAN_LAY_POWER_BOMBS)
    ),

    CHARGE_MISSILES(
        "Missiles (near charge beam)",
        MINOR,
        NORMAL,
        0x7860e,
        CAN_ACCESS_BRINSTAR_BIG_PINK
    ),

    CHARGE_BEAM(
        "Charge Beam",
        MAJOR,
        CHOZO,
        0x78614,
        CAN_ACCESS_BRINSTAR_BIG_PINK.and(CAN_LAY_ANY_BOMB_TYPE)
    ),

    WATERWAY(
        "Energy Tank (waterway)",
        MAJOR,
        NORMAL,
        0x787fa,
        CHARGE_BEAM.requiredAccessState
            .and(CAN_LAY_POWER_BOMBS)
            .and((i, s) -> s.contains(SHORT_CHARGE) || (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPEED_BOOSTER)))
    ),

    GREEN_HILLS_MISSILES(
        "Missiles (green hills pipe)",
        MINOR,
        NORMAL,
        0x78676,
        CAN_ACCESS_GREEN_HILLS
    ),

    /****************************** Red Brinstar ******************************/
    XRAY(
        "X-Ray",
        MAJOR,
        CHOZO,
        0x78876,
        CAN_ACCESS_RED_BRINSTAR
            .and(CAN_LAY_POWER_BOMBS)
            .and((i, s) -> i.contains(Item.GRAPPLE_BEAM) || s.contains(XRAY_DAMAGE_BOOST))
    ),

    ALPHA_POWER_BOMBS(
        "Power Bombs (alpha)",
        MINOR,
        CHOZO,
        0x7890e,
        Set.of(),
        CAN_ACCESS_RED_BRINSTAR,
        CAN_LAY_POWER_BOMBS
    ),

    ALPHA_POWER_BOMBS_MISSILES(
        "Missiles (behind alpha power bombs)",
        MINOR,
        NORMAL,
        0x78914,
        ALPHA_POWER_BOMBS.requiredAccessState.and(CAN_LAY_POWER_BOMBS)
    ),

    BIG_HOPPER_POWER_BOMBS(
        "Power Bombs (big hopper room, near alpha power bombs)",
        MINOR,
        NORMAL,
        0x788ca,
        ALPHA_POWER_BOMBS.requiredAccessState.and(CAN_LAY_POWER_BOMBS)
    ),

    SPAZER(
        "Spazer",
        MAJOR,
        CHOZO,
        0x7896e,
        CAN_ACCESS_RED_BRINSTAR.and(CAN_LAY_ANY_BOMB_TYPE)
    ),

    KRAID_MISSILES(
        "Missiles (Kraid's lair)",
        MINOR,
        HIDDEN,
        0x789ec,
        CAN_ACCESS_RED_BRINSTAR.and(CAN_LAY_POWER_BOMBS)
    ),

    KRAID_WAREHOUSE(
        "Energy Tank (Kraid's warehouse)",
        MAJOR,
        HIDDEN,
        0x7899c,
        CAN_ACCESS_RED_BRINSTAR
    ),

    KRAID_REWARD(
        "Varia Suit",
        MAJOR,
        CHOZO,
        0x78aca,
        CAN_ACCESS_RED_BRINSTAR
    ),

    // TODO audit remainder
    /****************************** Upper Norfair ******************************/
    HI_JUMP_BOOTS(
        "Hi-Jump Boots",
        MAJOR,
        CHOZO,
        0x78bac,
        CAN_ACCESS_RED_BRINSTAR
    ),

    HI_JUMP_MISSILES(
        "Missiles (Hi-Jump)",
        MINOR,
        NORMAL,
        0x78be6,
        CAN_ACCESS_RED_BRINSTAR
    ),

    HI_JUMP_ENERGY_TANK(
        "Energy Tank (Hi-Jump, changed to minor item)",
        MINOR,
        NORMAL,
        0x78bec,
        CAN_ACCESS_RED_BRINSTAR
    ),

    BUSINESS_CENTER_GREEN_GATE_MISSILES(
        "Missiles (Norfair business center, behind green gate)",
        MINOR,
        NORMAL,
        0x78bc0,
        CAN_ACCESS_RED_BRINSTAR.and((i, s) ->
            // Vanilla access
            (HEATED_UPPER_NORFAIR.matches(i, s) && (i.contains(Item.GRAPPLE_BEAM) || i.contains(Item.SPACE_JUMP))) ||

            // Access with green gate glitch
            (i.contains(Item.POWER_BOMBS) && s.contains(GREEN_GATE_GLITCH))
        )
    ),

    ICE_BEAM(
        "Ice Beam",
        MAJOR,
        CHOZO,
        0x78b24,
        HEATED_UPPER_NORFAIR.and((i, s) -> i.contains(Item.SPEED_BOOSTER) || s.contains(MOCKBALL))
    ),

    CROC_SPEEDWAY_MISSILES(
        "Missiles (Croc's speedway)",
        MINOR,
        HIDDEN,
        0x78b46,
        CAN_ACCESS_RED_BRINSTAR.and((i, s) ->
            i.contains(Item.POWER_BOMBS) && (
                i.contains(Item.SPEED_BOOSTER) || s.contains(MOCKBALL)
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
        HEATED_UPPER_NORFAIR.and((i, s) ->
            i.contains(Item.GRAPPLE_BEAM) || (
                i.contains(Item.SPEED_BOOSTER) && i.contains(Item.HIGH_JUMP_BOOTS)
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
        HEATED_UPPER_NORFAIR.and((i, s) ->
            s.contains(INFINITE_BOMB_JUMP) || (
                i.contains(Item.POWER_BOMBS) && i.contains(Item.SPEED_BOOSTER)
            )
        )
    ),

    GRAPPLE(
        "Grapple Beam",
        MAJOR,
        CHOZO,
        0x78c36,
        HEATED_UPPER_NORFAIR.and((i, s) ->
            // Vanilla access
            (i.contains(Item.POWER_BOMBS) && i.contains(Item.SPEED_BOOSTER)) ||

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
        HEATED_UPPER_NORFAIR.and((i, s) ->
            i.contains(Item.GRAPPLE_BEAM) || s.contains(INFINITE_BOMB_JUMP) || i.contains(Item.ICE_BEAM)
        )
    ),

    BUBBLE_MOUNTAIN_RESERVE_MISSILES(
        "Missiles (bubble mountain reserve, behind green door)",
        MINOR,
        NORMAL,
        0x78c52,
        BUBBLE_MOUNTAIN_RESERVE.requiredAccessState
    ),

    BUBBLE_MOUNTAIN_RESERVE_HIDDEN_MISSILES(
        "Missiles (bubble mountain reserve, hidden in pedestal)",
        MINOR,
        HIDDEN,
        0x78c44,
        BUBBLE_MOUNTAIN_RESERVE.requiredAccessState
    ),

    BUBBLE_MOUNTAIN_MISSILES(
        "Missiles (bubble mountain main area)",
        MINOR,
        NORMAL,
        0x78c66,
        HEATED_UPPER_NORFAIR
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
        (i, s) ->
            // Vanilla exit
            i.contains(Item.SPACE_JUMP) ||

            // INFINITE_BOMB_JUMP through the ceiling
            s.contains(INFINITE_BOMB_JUMP) ||

            // Exit using shine spark
            i.contains(Item.SPEED_BOOSTER) ||

            // Exit via spring ball glitch
            s.contains(SPRING_BALL_DOUBLE_JUMP) ||

            // Exit via high jump from energy refill entrance
            (i.contains(Item.HIGH_JUMP_BOOTS) && i.contains(Item.SPEED_BOOSTER))
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
        GREEN_MARIDIA_ENTRY.and((i, s) -> i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPEED_BOOSTER))
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
        GREEN_MARIDIA_FULL.and((i, s) ->
            i.contains(Item.GRAPPLE_BEAM) || i.contains(Item.SPACE_JUMP) || s.contains(INFINITE_BOMB_JUMP)
        )
    ),

    SPRING_BALL(
        "Spring Ball",
        MAJOR,
        CHOZO,
        0x7c6e5,
        GREEN_MARIDIA_FULL.and((i, s) ->
            s.contains(ICE_CLIP) ||
            (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.GRAPPLE_BEAM) && (
                i.contains(Item.SPACE_JUMP) ||
                i.contains(Item.HIGH_JUMP_BOOTS) ||
                s.contains(SPRING_BALL_DOUBLE_JUMP))
            )
        )
    ),

    LEFT_SAND_PIT_RESERVE(
        "Reserve Tank (left Maridia sand pit)",
        MAJOR,
        CHOZO,
        0x7c5e3,
        GREEN_MARIDIA_FULL.and((i, s) -> i.contains(Item.GRAVITY_SUIT))
    ),

    LEFT_SAND_PIT_MISSILES(
        "Missiles (left Maridia sand pit)",
        MINOR,
        NORMAL,
        0x7c5dd,
        LEFT_SAND_PIT_RESERVE.requiredAccessState
    ),

    RIGHT_SAND_PIT_MISSILES(
        "Missiles (right Maridia sand pit)",
        MINOR,
        NORMAL,
        0x7c5eb,
        LEFT_SAND_PIT_RESERVE.requiredAccessState
    ),

    RIGHT_SAND_PIT_POWER_BOMBS(
        "Power Bombs (right Maridia sand pit)",
        MINOR,
        NORMAL,
        0x7c5f1,
        RIGHT_SAND_PIT_MISSILES.requiredAccessState
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
        BOTWOON.and((i, s) ->
            // Allows to get to the exit
            (i.contains(Item.SPACE_JUMP) || s.contains(INFINITE_BOMB_JUMP) || s.contains(SHORT_CHARGE)) &&

            // Allows to kill enemies to open the door
            (i.contains(Item.PLASMA_BEAM) || i.contains(Item.SCREW_ATTACK))
        )
    ),

    /****************************** Maridia lab ******************************/
    AQUEDUCT_MISSILES(
        "Missiles (aqueduct)",
        MINOR,
        NORMAL,
        0x7c603,
        GREEN_MARIDIA_FULL.and((i, s) -> i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPEED_BOOSTER))
    ),

    AQUEDUCT_SUPER_MISSILES(
        "Super Missiles (aqueduct)",
        MINOR,
        NORMAL,
        0x7c609,
        AQUEDUCT_MISSILES.requiredAccessState
    ),

    BOTWOON_ENERGY_TANK(
        "Energy Tank (Botwoon)",
        MAJOR,
        NORMAL,
        0x7c755,
        Set.of(),
        BOTWOON,
        BOTWOON.and((i, s) -> i.contains(Item.GRAVITY_SUIT))
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
        (i, s) ->
            s.contains(GRAPPLE_GRAVITY_LAUNCH) ||
            s.contains(GRAVITY_JUMP) ||
            (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPACE_JUMP)) ||
            (i.contains(Item.GRAVITY_SUIT) && s.contains(INFINITE_BOMB_JUMP)) ||
            (i.contains(Item.GRAVITY_SUIT) && i.contains(Item.SPEED_BOOSTER) && i.contains(Item.HIGH_JUMP_BOOTS))
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
        WRECKED_SHIP.and((i, s) -> i.contains(Item.SPEED_BOOSTER))
    );

    enum Type { NORMAL, HIDDEN, CHOZO }

    final String name;
    final Item.Type itemType;
    final ItemLocation.Type locationType;
    final Set<Item> itemBlacklist;
    final PlayerState requiredAccessState;
    final PlayerState requiredExitState;
    final int hexAddress;

    ItemLocation(String name, Item.Type itemType, ItemLocation.Type locationType, int hexAddress, PlayerState requiredAccessState) {
        this(name, itemType, locationType, hexAddress, Set.of(), requiredAccessState, ANY);
    }

    ItemLocation(String name, Item.Type itemType, ItemLocation.Type locationType, int hexAddress, Set<Item> itemBlacklist, PlayerState requiredAccessState, PlayerState requiredExitState) {
        this.name = name;
        this.itemType = itemType;
        this.locationType = locationType;
        this.hexAddress = hexAddress;
        this.requiredAccessState = requiredAccessState;
        this.requiredExitState = requiredExitState;
        this.itemBlacklist = EnumSet.copyOf(itemBlacklist);
    }

}
