package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;

public class WhitePawn extends Pawn {

	@Override
	public MoveType checkIfMoveIsValid(Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		WhitePawn outObj = (WhitePawn) obj;
		if(this.getClass()==outObj.getClass()){
			return true;
		}
		else{
			return false;
		}
	}
}
