package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class King extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) {
		
		if(isCastling())
		{
			return MoveType.CASTLING;
		}
		//TODO reszte wykonac jak w innych pionkach
		
		return null;
	}

	private boolean isCastling() {
		// TODO Auto-generated method stub
		return false;
	}

	public King(Color color) {
		super(color);
	}
}
