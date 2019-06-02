package com.github.tylersharpe.supermetroidrandomizer;

import java.util.Collection;

/**
 * Provides progression logic for whether a given map can be accessed or exited from.
 *
 * The reason this interface is used for exit logic as well as entrance logic is
 * to implement exit logic for locations which can soft-lock the user if they do not
 * have certain abilities after obtaining the item. An example is the vanilla screw
 * attack location: the player can soft-lock after getting this item if they do not have
 * the available items to climb out of the room
 */
interface ProgressionRule {

  boolean test(Collection<ProgressionAbility> abilities, Collection<Strat> strats);

  default ProgressionRule and(ProgressionRule other) {
    return (abilities, strats) -> test(abilities, strats) && other.test(abilities, strats);
  }

  default ProgressionRule or(ProgressionRule other) {
    return (abilities, strats) -> test(abilities, strats) || other.test(abilities, strats);
  }

}