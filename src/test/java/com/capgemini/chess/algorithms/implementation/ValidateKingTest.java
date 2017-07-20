package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.Bishop;
import com.capgemini.chess.algorithms.pieces.King;
import com.capgemini.chess.algorithms.pieces.Pawn;
import com.capgemini.chess.algorithms.pieces.Rook;

public class ValidateKingTest {

	@Test
	public void shouldThrowInvalidMoveExceptionWhenKingIsInCheck() {
		//given
		Board board = new Board();
		board.setPieceAt(new King(Color.BLACK), new Coordinate(0, 7));
		board.setPieceAt(new Pawn(Color.WHITE), new Coordinate(1, 6));
		board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(2, 5));
		boolean exceptionThrown=false;
		ValidateKing checkKing = new ValidateKing(board);
		//when
		try{
			checkKing.validateIfKingIsInCheck(Color.BLACK);
		} catch(InvalidMoveException ex){
			exceptionThrown = true;
		}
		//then
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldNotThrowInvalidMoveExceptionWhenKingIsNotInCheck() {
		//given
		Board board = new Board();
		board.setPieceAt(new King(Color.BLACK), new Coordinate(0, 7));
		board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(1, 6));
		board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(2, 5));
		boolean exceptionThrown=false;
		ValidateKing checkKing = new ValidateKing(board);
		//when
		try{
			checkKing.validateIfKingIsInCheck(Color.BLACK);
		} catch(InvalidMoveException ex){
			exceptionThrown = true;
		}
		//then
		assertFalse(exceptionThrown);
	}
	
	
	@Test
	public void shouldNotThrowInvalidMoveExceptionWhenKingIsNotOnCheckByTwoOpponents() {
		//given
		Board board = new Board();
		board.setPieceAt(new King(Color.BLACK), new Coordinate(0, 7));
		board.setPieceAt(new Rook(Color.WHITE), new Coordinate(2, 7));
		board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(2, 5));
		boolean exceptionThrown=false;
		ValidateKing checkKing = new ValidateKing(board);
		//when
		try{
			checkKing.validateIfKingIsInCheck(Color.BLACK);
		} catch(InvalidMoveException ex){
			exceptionThrown = true;
		}
		//then
		assertTrue(exceptionThrown);
	}
	
	
	

}
