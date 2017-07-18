package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Rook extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		checkIfCanMoveThatWay(from, to);
		chcekIfThereIsPieceOnWay(board, from, to);		
		return getReturnMoveTypeForRook(board, from, to);
	}

	private void chcekIfThereIsPieceOnWay(Board board, Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		
	}

	private void checkIfCanMoveThatWay(Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		
	}

	private MoveType getReturnMoveTypeForRook(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		MoveType moveType = super.checkDestinationPlace(board, from, to);
		if(isCastling()){
			moveType = MoveType.CASTLING;
		}
		return moveType;
	}
	
	private boolean isCastling() {
		// TODO Auto-generated method stub
		//TODO Czy castling moze wykonac rook czy king? czy moze obydwoje?!
		return false;
	}

	@Override
	protected MoveType checkDestinationPlace(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return super.checkDestinationPlace(board, from, to);
	}



	public Rook(Color color) {
		super(color);
	}	
}
