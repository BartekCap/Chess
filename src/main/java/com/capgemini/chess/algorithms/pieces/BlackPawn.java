package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;

public class BlackPawn extends Pawn {

	@Override
	public MoveType checkIfMoveIsValid(Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		BlackPawn outObj = (BlackPawn) obj;
		if(this.getClass()==outObj.getClass() && this.getColor().equals(outObj.getColor())){
			return true;
		}
		else{
			return false;
		}
	}
	
	

}
