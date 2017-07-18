package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class Knight extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		return null;
	}

	public Knight(Color color) {
		super(color);
	}

}
