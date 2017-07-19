package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class RookTest {

	@Test
	public void shouldGiveCaptureMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece rook = new Rook(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(5, 5);
		board.setPieceAt(rook, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		board.setPieceAt(new King(Color.BLACK), new Coordinate(7, 7));
		//when
		MoveType moveType = rook.checkIfMoveIsValid(board,from , to);
		//then
		assertEquals(MoveType.CAPTURE, moveType);
	}
	
	@Test
	public void shouldGiveAttackMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece rook = new Rook(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(7, 5);
		board.setPieceAt(rook, from);
		board.setPieceAt(new King(Color.BLACK), new Coordinate(7, 7));
		//when
		MoveType moveType = rook.checkIfMoveIsValid(board,from , to);
		//then
		assertEquals(MoveType.ATTACK, moveType);
	}
	
	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRules() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece rook = new Rook(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(5, 1);
		board.setPieceAt(rook, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		board.setPieceAt(new King(Color.BLACK), new Coordinate(7, 7));
		boolean isException;
		String expectedMessage="Invalid move! Piece cant move there. It is against rules for this piece";
		String message = "";
		//when
		try{
		rook.checkIfMoveIsValid(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
			message = e.getMessage();
		}
		//then
		assertTrue(isException);
		assertEquals(expectedMessage, message);
	}
	
	@Test
	public void shouldThrowExceptionWhenSomethingIsOnWay() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece rook = new Rook(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(6, 5);
		board.setPieceAt(rook, from);
		board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(5, 5) );
		board.setPieceAt(new King(Color.BLACK), new Coordinate(7, 7));
		boolean isException;
		String expectedMessage="Invalid move! There is piece on way!";
		String message = "";
		//when
		try{
		rook.checkIfMoveIsValid(board,from , to);
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
