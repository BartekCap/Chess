package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KnightTest {

	@Test
	public void shouldGiveCaptureMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece knight = new Knight(Color.WHITE);
		Coordinate from = new Coordinate(3, 6);
		Coordinate to = new Coordinate(1, 7);
		board.setPieceAt(knight, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		//when
		MoveType moveType = knight.checkIfMoveIsValid(board,from , to);
		//then
		assertEquals(MoveType.CAPTURE, moveType);
	}
	
	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRules() {
		//given
		Board board = new Board();
		Piece knight = new Knight(Color.WHITE);
		Coordinate from = new Coordinate(4, 3);
		Coordinate to = new Coordinate(4, 4);
		board.setPieceAt(knight, from);
		boolean isException;
		//when
		try{
		knight.checkIfMoveIsValid(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldThrowExceptionWhenInDestinationIsBishopTheSameColor() {
		//given
		Board board = new Board();
		Piece knight = new Knight(Color.WHITE);
		Coordinate from = new Coordinate(4, 3);
		Coordinate to = new Coordinate(3, 5);
		board.setPieceAt(knight, from);
		board.setPieceAt(new Bishop(Color.WHITE), to);
		boolean isException;
		//when
		try{
		knight.checkIfMoveIsValid(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}

}
