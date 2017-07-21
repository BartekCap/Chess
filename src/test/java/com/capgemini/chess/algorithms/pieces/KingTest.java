package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.abstracts.Piece;

public class KingTest {
	
	@Test
	public void shouldGiveCaptureMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(3, 5);
		board.setPieceAt(king, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		//when
		MoveType moveType = king.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.CAPTURE, moveType);
	}
	
	@Test
	public void shouldGiveAtackMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(2, 5);
		Coordinate to = new Coordinate(3, 6);
		board.setPieceAt(king, from);
		board.setPieceAt(new Pawn(Color.BLACK),new Coordinate(3, 5) );
		//when
		MoveType moveType = king.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.ATTACK, moveType);
	}
	
	@Test
	public void shouldGiveCastlingMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(4, 0);
		Coordinate to = new Coordinate(2, 0);
		board.setPieceAt(king, from);
		board.setPieceAt(new Rook(Color.WHITE),new Coordinate(0, 0) );
		//when
		MoveType moveType = king.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.CASTLING, moveType);
	}

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
		king.checkIfMoveIsValidForPiece(board,from , to);
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
	public void shouldThrowExceptionInCastlingWhenRookColorIsDifrentFromKingsColor() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(4, 0);
		Coordinate to = new Coordinate(2, 0);
		board.setPieceAt(king, from);
		board.setPieceAt(new Rook(Color.BLACK), new Coordinate(0, 0) );
		boolean isException;
		//when
		try{
		king.checkIfMoveIsValidForPiece(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldThrowExceptionInCastlingWhenRookMovedBefore() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(4, 0);
		Coordinate to = new Coordinate(2, 0);
		board.setPieceAt(king, from);
		board.setPieceAt(new Rook(Color.WHITE), new Coordinate(0, 0) );
		boolean isException;
		Move dummyMove = new Move(new Coordinate(0, 0), new Coordinate(0, 0), MoveType.ATTACK, new Rook(Color.WHITE));
		board.getMoveHistory().add(dummyMove);
		//when
		try{
		king.checkIfMoveIsValidForPiece(board, from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldNotThrowExceptionInCastlingWhenOtherRookMovedBefore() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(4, 0);
		Coordinate to = new Coordinate(6, 0);
		board.setPieceAt(king, from);
		board.setPieceAt(new Rook(Color.WHITE), new Coordinate(0, 0) );
		board.setPieceAt(new Rook(Color.WHITE), new Coordinate(7, 0) );
		boolean isException;
		Move dummyMove = new Move(new Coordinate(0, 0), new Coordinate(0, 0), MoveType.ATTACK, new Rook(Color.WHITE));
		board.getMoveHistory().add(dummyMove);
		//when
		try{
		king.checkIfMoveIsValidForPiece(board, from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertFalse(isException);
	}
	
	@Test
	public void shouldThrowExceptionWhenPieceIsBeetwenRookAndKing() {
		//given
		Board board = new Board();
		Piece king = new King(Color.WHITE);
		Coordinate from = new Coordinate(4, 0);
		Coordinate to = new Coordinate(2, 0);
		board.setPieceAt(king, from);
		board.setPieceAt(new Rook(Color.WHITE), new Coordinate(0, 0) );
		board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(1, 0) );
		boolean isException;
		//when
		try{
		king.checkIfMoveIsValidForPiece(board, from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}

}
