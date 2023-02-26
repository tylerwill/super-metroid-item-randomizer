package com.github.tylersharpe.supermetroidrandomizer;

import java.util.*;

class ParsedArgs {

    List<String> ordinalArgs = new ArrayList<>();
    Set<String> switches = new HashSet<>();
    Map<String, String> namedArgs = new HashMap<>();

    static ParsedArgs parse(String[] commands) {
        ParsedArgs args = new ParsedArgs();

        for (int i = 0; i < commands.length; i++) {
            String currentCommand = commands[i];

            if (currentCommand.startsWith("--")) {
                if (i < commands.length - 1) {
                    String nextCommand = commands[++i];

                    if (nextCommand.startsWith("--")) {
                        args.switches.add(currentCommand.substring(2));
                        i--;
                    } else {
                        args.namedArgs.put(currentCommand.substring(2), nextCommand);
                    }
                } else {
                    args.switches.add(currentCommand.substring(2));
                }
            } else {
                args.ordinalArgs.add(currentCommand);
            }
        }

        return args;
    }

}
