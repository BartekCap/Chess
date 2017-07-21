package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.abstracts.MultiMoveable;
import com.capgemini.chess.algorithms.pieces.abstracts.Piece;

public class Bishop extends Piece implements MultiMoveable {

	public Bishop(Color color) {
		super(color);
	}
	
	@Override
	public MoveType checkIfMoveIsValidForPiece(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		validateThatPieceCanMoveThatDirection(from, to);
		int iterationRestriction = Math.abs(from.getX()-to.getX());
		validateMoveRuleAndClearPathForBishopRookAndQueen(iterationRestriction, board, from, to);
		return getMoveType(board, from, to);
	}

	@Override
	protected void validateThatPieceCanMoveThatDirection(Coordinate from, Coordinate to) throws InvalidMoveException {
		int deltaX = from.getX() - to.getX();
		int deltaY = from.getY() - to.getY();
		if(!(isDirectionNE(deltaX, deltaY) || isDirectionNW(deltaX, deltaY) || isDirectionSE(deltaX, deltaY) || isDirectionSW(deltaX, deltaY))){
			throw new InvalidMoveException("Bishop cant move there. It is against rules for this piece");
		}
	}
}