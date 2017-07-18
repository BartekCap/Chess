package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public abstract class Piece {
	
	private Color color;
	

	public Piece(){
	}
	
	public Piece(Color color) {
		this.color = color;
	}

	public abstract MoveType checkIfMoveIsValid (Board board, Coordinate from, Coordinate to) throws InvalidMoveException;

	protected MoveType checkDestinationPlace(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		if(board.getPieceAt(to)!=null){
			if(board.getPieceAt(from).getColor()==board.getPieceAt(to).getColor()){
				throw new InvalidMoveException("In destination place is your piece!");
			}
			else{
				return MoveType.CAPTURE;
			}
		}
		else {
			return MoveType.ATTACK;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		return true;
	}
 
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
