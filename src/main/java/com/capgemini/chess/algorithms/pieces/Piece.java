package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public abstract class Piece {
	private Color color;
	
	public abstract MoveType checkIfMoveIsValid (Coordinate from, Coordinate to) throws InvalidMoveException;

	public Piece(Color color) {
		this.color = color;
	}
	
	public Piece() {
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
 
	
	
}
