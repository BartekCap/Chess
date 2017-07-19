package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class QueenTest {

	@Test
	public void shouldGiveCaptureMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece queen = new Queen(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(5, 5);
		board.setPieceAt(queen, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		board.setPieceAt(new King(Color.BLACK), new Coordinate(7, 7));
		//when
		MoveType moveType = queen.checkIfMoveIsValid(board,from , to);
		//then
		assertEquals(MoveType.CAPTURE, moveType);
	}

	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRules() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece queen = new Queen(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(1, 3);
		board.setPieceAt(queen, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		board.setPieceAt(new King(Color.BLACK), new Coordinate(7, 7));
		boolean isException;
		String expectedMessage="Invalid move! Piece cant move there. It is against rules for this piece";
		String message = "";
		//when
		try{
		queen.checkIfMoveIsValid(board,from , to);
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
