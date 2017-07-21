package com.capgemini.chess.algorithms.pieces.abstracts;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public abstract class Piece {

	protected Color color;

	public Piece(Color color) {
		this.color = color;
	}

	public abstract MoveType checkIfMoveIsValidForPiece(Board board, Coordinate from, Coordinate to)
			throws InvalidMoveException;

	protected abstract void validateThatPieceCanMoveThatDirection(Coordinate from, Coordinate to)
			throws InvalidMoveException;

	protected MoveType getMoveType(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		Color color = board.getPieceAt(from).getColor();
		if (board.getPieceAt(to) == null) {
			return MoveType.ATTACK;
		} else if (board.getPieceAt(to).getColor() != color) {
			return MoveType.CAPTURE;
		} else {
			throw new InvalidMoveException("You cant capture your piece!");
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		return true;
	}
}