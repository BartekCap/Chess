package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.abstracts.Piece;

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
		//when
		MoveType moveType = rook.checkIfMoveIsValidForPiece(board,from , to);
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
		//when
		MoveType moveType = rook.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.ATTACK, moveType);
	}
	
	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRules(){
		//given
		Board board = new Board();
		Piece rook = new Rook(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(5, 1);
		board.setPieceAt(rook, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		boolean isException;
		String expectedMessage="Invalid move! Rook cant move there. It is against rules for this piece";
		String message = "";
		//when
		try{
		rook.checkIfMoveIsValidForPiece(board,from , to);
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
	public void shouldThrowExceptionWhenSomethingIsOnWay(){
		//given
		Board board = new Board();
		Piece rook = new Rook(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(6, 5);
		board.setPieceAt(rook, from);
		board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(5, 5) );
		boolean isException;
		String expectedMessage="Invalid move! There is piece on way!";
		String message = "";
		//when
		try{
		rook.checkIfMoveIsValidForPiece(board,from , to);
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
