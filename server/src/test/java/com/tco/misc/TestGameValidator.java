package com.tco.misc;

import com.tco.requests.game.Game;
import com.tco.requests.game.Move;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameValidator {
  private GameValidator validator;

  @BeforeEach
  public void beforeEach() {
    validator = new GameValidator();
  }

  @Test
  @DisplayName("GameValidator should instantiate")
  public void testGameValidatorInstatiates() {
    assertNotNull(validator);
  }

  // @Test
  // @DisplayName("GameValidator should make a move")
  // public void testGameValidatorMove() {
  //   Game testGame = new Game(-1, "Jones", "Jimothy");
  //   Move testMove = new Move("a2", "a4", "wP");
  //   assertEquals(testMove.getPiece(), testGame.getBoard().get(testMove.getSourceSquare()));
  //   assertNull(testGame.getBoard().get(testMove.getTargetSquare()));

  //   boolean valid = validator.validateAndMove(testGame, testMove, "Jones");

  //   assertTrue(valid);
  //   assertNull(testGame.getBoard().get(testMove.getSourceSquare()));
  //   assertEquals(testMove.getPiece(), testGame.getBoard().get(testMove.getTargetSquare()));
  // }
}
