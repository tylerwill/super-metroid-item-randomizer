package com.github.tylersharpe.smrandomizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

final class Util {

  private static final Random RNG = new Random();

  private Util() {}

  static <T> Collection<T> concat(Collection<T> base, T additional) {
    var newCollection = new ArrayList<>(base);
    newCollection.add(additional);
    return newCollection;
  }

  static <T> T sample(Collection<T> collection) {
    List<T> list = collection instanceof List ? (List<T>) collection : new ArrayList<>(collection);
    return list.get(RNG.nextInt(list.size()));
  }

}
