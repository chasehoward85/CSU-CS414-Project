package com.tco.requests.game;

import java.util.Map;

import static java.util.Map.entry; 

public class TestBoardStates {
  public static final Map<String, String> INITIAL_BOARD = Map.ofEntries(
    entry("a1", "wR"),
    entry("b1", "wN"),
    entry("c1", "wB"),
    entry("d1", "wQ"),
    entry("e1", "wK"),
    entry("f1", "wB"),
    entry("g1", "wN"),
    entry("h1", "wR"),

    entry("a2", "wP"),
    entry("b2", "wP"),
    entry("c2", "wP"),
    entry("d2", "wP"),
    entry("e2", "wP"),
    entry("f2", "wP"),
    entry("g2", "wP"),
    entry("h2", "wP"),

    entry("a7", "bP"),
    entry("b7", "bP"),
    entry("c7", "bP"),
    entry("d7", "bP"),
    entry("e7", "bP"),
    entry("f7", "bP"),
    entry("g7", "bP"),
    entry("h7", "bP"),

    entry("a8", "bR"),
    entry("b8", "bN"),
    entry("c8", "bB"),
    entry("d8", "bQ"),
    entry("e8", "bK"),
    entry("f8", "bB"),
    entry("g8", "bN"),
    entry("h8", "bR")
  );
}
