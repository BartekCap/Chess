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

public class CheckKingTest {

	@Test
	public void shouldThrowInvalidMoveException() {
		//given
		Board board = new Board();
		board.setPieceAt(new King(Color.BLACK), new Coordinate(0, 7));
		board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(1, 6));
		board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(2, 5));
		boolean exceptionThrown=false;
		CheckKing checkKing = new CheckKing(board);
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
	public void shouldNotThrowInvalidMoveException() {
		//given
		Board board = new Board();
		board.setPieceAt(new King(Color.BLACK), new Coordinate(0, 7));
		board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(1, 6));
		board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(2, 5));
		boolean exceptionThrown=false;
		CheckKing checkKing = new CheckKing(board);
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
