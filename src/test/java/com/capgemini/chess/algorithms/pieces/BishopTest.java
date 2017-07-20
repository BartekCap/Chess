package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class BishopTest {

	@Test
	public void shouldGiveCaptureMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece bishop = new Bishop(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(1, 6);
		board.setPieceAt(bishop, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		//when
		MoveType moveType = bishop.checkIfMoveIsValid(board,from , to);
		//then
		assertEquals(MoveType.CAPTURE, moveType);
	}
	
	@Test
	public void shouldGiveAttackMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece bishop = new Bishop(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(1, 6);
		board.setPieceAt(bishop, from);
		//when
		MoveType moveType = bishop.checkIfMoveIsValid(board,from , to);
		//then
		assertEquals(MoveType.ATTACK, moveType);
	}
	
	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRuleles(){
		//given
		Board board = new Board();
		Piece bishop = new Bishop(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(7, 2);
		board.setPieceAt(bishop, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		boolean isException;
		String expectedMessage="Invalid move! Bishop cant move there. It is against rules for this piece";
		String message = "";
		//when
		try{
		bishop.checkIfMoveIsValid(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
			message = e.getMessage();
		}
		//then
		assertTrue(isException);
		assertEquals(expectedMessage, message);
	}

}
