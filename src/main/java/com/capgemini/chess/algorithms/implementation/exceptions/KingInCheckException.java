package com.capgemini.chess.algorithms.implementation.exceptions;

public class KingInCheckException extends InvalidMoveException {

	private static final long serialVersionUID = -7109029342454067452L;
	
	public KingInCheckException() {
		super("King must not be checked!");
	}

}
