package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}
	
	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		validateThatPieceCanMoveThatDirection(from, to);
		int iterationRestriction = Math.abs(from.getX()-to.getX());
		validateMoveRuleAndClearPath(iterationRestriction, board, from, to);
		return getMoveType(board, from, to);
	}

	@Override
	protected void validateThatPieceCanMoveThatDirection(Coordinate from, Coordinate to) throws InvalidMoveException {
		int deltaX = from.getX() - to.getX();
		int deltaY = from.getY() - to.getY();
		if(!(isDirectionNE(deltaX, deltaY) || isDirectionNW(deltaX, deltaY) || isDirectionSE(deltaX, deltaY) || isDirectionSW(deltaX, deltaY))){
			throw new InvalidMoveException("Piece cant move there. It is against rules for this piece");
		}
	}


	
}
