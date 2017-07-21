package com.capgemini.chess.algorithms.implementation.service;

import static org.junit.Assert.*;
import org.junit.Test;
import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.King;
import com.capgemini.chess.algorithms.pieces.Pawn;
import com.capgemini.chess.algorithms.pieces.abstracts.Piece;

public class MoveValidatorTest {

	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRules() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(7, 3);
		board.setPieceAt(king, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		boolean isException;
		String expectedMessage="Invalid move! King cant move there. It is against rules for this piece";
		String message = "";
		//when
		try{
		new MoveValidator(board).validateMove(from, to);
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
	public void shouldNotThrowExceptionWhenDestinationGoesWithRules() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(7, 2);
		board.setPieceAt(king, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		boolean isException;
		//when
		try{
		new MoveValidator(board).validateMove(from, to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertFalse(isException);
	}

	@Test
	public void shouldThrowExceptionWhenFromEqualsTrue() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = from;
		board.setPieceAt(king, from);
		boolean isException;
		//when
		try{
		new MoveValidator(board).validateMove(from, to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldThrowExceptionWhenFromHadNoPiece() {
		//given
		Board board = new Board();
		boolean isException;
		//when
		try{
		new MoveValidator(board).validateMove(new Coordinate(6, 1), new Coordinate(5, 1));
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
}
