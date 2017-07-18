package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;

public class King extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Coordinate from, Coordinate to) {
		return null;
	}

	public King() {
		super();
	}

	public King(Color color) {
		super(color);
	}
	
	@Override
	public boolean equals(Object obj) {
		King outObj = (King) obj;
		if(this.getClass()==outObj.getClass() && this.getColor().equals(outObj.getColor())){
			return true;
		}
		else{
			return false;
		}
	}
}
