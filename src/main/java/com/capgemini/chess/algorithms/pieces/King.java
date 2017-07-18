package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class King extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) {
		return null;
	}

	public King(Color color) {
		super(color);
	}
}
