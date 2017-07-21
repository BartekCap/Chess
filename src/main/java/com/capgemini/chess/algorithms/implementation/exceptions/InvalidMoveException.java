package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = -3078327974919142439L;

	public InvalidMoveException() {
		super("Invalid move!");
	}
	
	public InvalidMoveException(String message) {
		super("Invalid move! " + message);
	}
}
