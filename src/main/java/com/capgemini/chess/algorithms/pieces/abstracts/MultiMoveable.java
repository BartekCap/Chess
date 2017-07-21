package com.capgemini.chess.algorithms.pieces.abstracts;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface MultiMoveable {
	
	default void validateMoveRuleAndClearPathForBishopRookAndQueen(int iteratorRestriction, Board board, Coordinate from, Coordinate to)
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
	
	default void validateIfSquaresAreNotNull(int signX, int signY, int iteratorRestriction, Board board,
			Coordinate from, Coordinate to) throws InvalidMoveException {
		for (int placeIterator = 1; placeIterator < iteratorRestriction; placeIterator++) {
			if (board.getPieceAt(
					new Coordinate(from.getX() + signX * placeIterator, from.getY() + signY * placeIterator)) != null) {
				throw new InvalidMoveException("There is piece on way!");
			}
		}
	}
	
	default boolean isDirectionN(int deltaX, int deltaY) {
		return deltaX > 0 && deltaY == 0;
	}

	default boolean isDirectionNE(int deltaX, int deltaY) {
		return deltaX > 0 && deltaY < 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}

	default boolean isDirectionE(int deltaX, int deltaY) {
		return deltaX == 0 && deltaY < 0;
	}

	default boolean isDirectionSE(int deltaX, int deltaY) {
		return deltaX < 0 && deltaY < 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}

	default boolean isDirectionS(int deltaX, int deltaY) {
		return deltaX < 0 && deltaY == 0;
	}

	default boolean isDirectionSW(int deltaX, int deltaY) {
		return deltaX < 0 && deltaY > 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}

	default boolean isDirectionW(int deltaX, int deltaY) {
		return deltaX == 0 && deltaY > 0;
	}

	default boolean isDirectionNW(int deltaX, int deltaY) {
		return deltaX > 0 && deltaY > 0 && Math.abs(deltaX) == Math.abs(deltaY);
	}
}
