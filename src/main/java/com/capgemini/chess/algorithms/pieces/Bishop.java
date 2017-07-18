package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}
	
	@Override
	public MoveType checkIfMoveIsValid(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		checkIfCanMoveThatWay(from, to);
		chcekIfThereIsPieceOnWay(board, from, to);
		return super.checkDestinationPlace(board, from, to);
	}

	//TODO Czy da się to zrefaktorowac? - jakos podzielić na mniejsze - chodzi o to ze znaki przy iteratorze muszą się zmieniać!
	private void chcekIfThereIsPieceOnWay(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		int row = from.getX()-to.getX();
		int column = from.getY()-to.getY();
		
		if(row>0 && column>0){
			for(int placeIterator=1; placeIterator<Math.abs(row);placeIterator++){
				if(board.getPieceAt(new Coordinate(from.getX()-placeIterator, from.getY()-placeIterator))!=null){
					throw new InvalidMoveException("There is piece on way!");
				}
			}
		}
		else if (row>0 && column<0)
		{
			for(int placeIterator=1; placeIterator<Math.abs(row);placeIterator++){
				if(board.getPieceAt(new Coordinate(from.getX()-placeIterator, from.getY()+placeIterator))!=null){
					throw new InvalidMoveException("There is piece on way!");
				}
			}
		}
		else if(row<0 && column>0){
			for(int placeIterator=1; placeIterator<Math.abs(row);placeIterator++){
				if(board.getPieceAt(new Coordinate(from.getX()+placeIterator, from.getY()-placeIterator))!=null){
					throw new InvalidMoveException("There is piece on way!");
				}
			}
		}
		else if(row<0 && column<0){
			for(int placeIterator=1; placeIterator<Math.abs(row);placeIterator++){
				if(board.getPieceAt(new Coordinate(from.getX()+placeIterator, from.getY()+placeIterator))!=null){
					throw new InvalidMoveException("There is piece on way!!");
				}
			}
		}
	}

	private void checkIfCanMoveThatWay(Coordinate from, Coordinate to) throws InvalidMoveException {
		if(!(Math.abs(from.getX()-to.getX())==Math.abs(from.getY()-to.getY()))){
			throw new InvalidMoveException("Bishop cant move that way");
		}
	}
}
