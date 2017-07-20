package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public abstract class Piece {

	protected Color color;

	public Piece() {
	}

	public Piece(Color color) {
		this.color = color;
	}

	public abstract MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to)
			throws InvalidMoveException;

	protected void validateMoveRuleAndClearPath(int iteratorRestriction, Board board, Coordinate from, Coordinate to)
			throws InvalidMoveException {
		int deltaX = from.getX() - to.getX();
		int deltaY = from.getY() - to.getY();

		if (isDirectionN(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(-1, 0, iteratorRestriction, board, from, to);
		} else if (isDirectionNE(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(-1, 1, iteratorRestriction, board, from, to);
		} else if (isDirectionE(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(0, 1, iteratorRestriction, board, from, to);
		} else if (isDirectionSE(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(1, 1, iteratorRestriction, board, from, to);
		} else if (isDirectionS(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(1, 0, iteratorRestriction, board, from, to);
		} else if (isDirectionSW(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(1, -1, iteratorRestriction, board, from, to);
		} else if (isDirectionW(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(0, -1, iteratorRestriction, board, from, to);
		} else if (isDirectionNW(deltaX, deltaY)) {
			validateIfSquaresAreNotNull(-1, -1, iteratorRestriction, board, from, to);
		}
	}

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

	protected void validateIfSquaresAreNotNull(int signX, int signY, int iteratorRestriction, Board board,
			Coordinate from, Coordinate to) throws InvalidMoveException {
		for (int placeIterator = 1; placeIterator < iteratorRestriction; placeIterator++) {
			if (board.getPieceAt(
					new Coordinate(from.getX() + signX * placeIterator, from.getY() + signY * placeIterator)) != null) {
				throw new InvalidMoveException("There is piece on way!");
			}
		}
	}

	protected boolean isDirectionN(int deltaX, int deltaY) {
		return deltaX > 0 && deltaY == 0;
	}

	protected boolean isDirectionNE(int deltaX, int deltaY) {
		return deltaX > 0 && deltaY < 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}

	protected boolean isDirectionE(int deltaX, int deltaY) {
		return deltaX == 0 && deltaY < 0;
	}

	protected boolean isDirectionSE(int deltaX, int deltaY) {
		return deltaX < 0 && deltaY < 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}

	protected boolean isDirectionS(int deltaX, int deltaY) {
		return deltaX < 0 && deltaY == 0;
	}

	protected boolean isDirectionSW(int deltaX, int deltaY) {
		return deltaX < 0 && deltaY > 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}

	protected boolean isDirectionW(int deltaX, int deltaY) {
		return deltaX == 0 && deltaY > 0;
	}

	protected boolean isDirectionNW(int deltaX, int deltaY) {
		return deltaX > 0 && deltaY > 0 && Math.abs(deltaX) == Math.abs(deltaY);
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
