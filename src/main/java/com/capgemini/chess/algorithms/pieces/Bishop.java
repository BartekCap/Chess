package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		checkIfBishopCanMoveThatWay(from, to);
		chcekIfThereIsPieceOnWay(board, from, to);
		return null;
	}

	private void chcekIfThereIsPieceOnWay(Board board, Coordinate from, Coordinate to) {
	}

	private void checkIfBishopCanMoveThatWay(Coordinate from, Coordinate to) throws InvalidMoveException {
		if(!(Math.abs(from.getX()-to.getX())==Math.abs(from.getY()-to.getY()))){
			throw new InvalidMoveException("Bishop cant move that way");
		}
	}

	public Bishop(Color color) {
		super(color);
	}
}
