package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;

public class Knight extends Piece {

	@Override
	public MoveType checkIfMoveIsValid(Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		return null;
	}

	public Knight() {
		super();
	}

	public Knight(Color color) {
		super(color);
	}
	
	@Override
	public boolean equals(Object obj) {
		Knight outObj = (Knight) obj;
		if(this.getClass()==outObj.getClass() && this.getColor().equals(outObj.getColor())){
			return true;
		}
		else{
			return false;
		}
	}

}
