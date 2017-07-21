package com.capgemini.chess.algorithms.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PawnTest {

	@Test
	public void shouldGiveCaptureMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(5, 5);
		board.setPieceAt(pawn, from);
		board.setPieceAt(new Pawn(Color.BLACK),to );
		//when
		MoveType moveType = pawn.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.CAPTURE, moveType);
	}
	
	@Test
	public void shouldGiveAttackMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 5);
		board.setPieceAt(pawn, from);
		//when
		MoveType moveType = pawn.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.ATTACK, moveType);
	}
	
	@Test
	public void shouldGiveEnPassantMoveType() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(6, 1);
		Coordinate to = new Coordinate(6, 3);
		board.getMoveHistory().add(new Move(from, to, MoveType.ATTACK, pawn));
		board.setPieceAt(pawn, to);
		Piece capturingPiece = new Pawn(Color.BLACK);
		board.setPieceAt(capturingPiece, new Coordinate(5, 3));
		//when
		MoveType moveType = capturingPiece.checkIfMoveIsValidForPiece(board, new Coordinate(5, 3) , new Coordinate(6, 2));
		//then
		assertEquals(MoveType.EN_PASSANT, moveType);
	}
	
	@Test
	public void shouldGiveAttackMoveTypeWhenDoubleMove() throws InvalidMoveException {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(7, 3);
		board.setPieceAt(pawn, from);
		//when
		MoveType moveType = pawn.checkIfMoveIsValidForPiece(board,from , to);
		//then
		assertEquals(MoveType.ATTACK, moveType);
	}
	
	@Test
	public void shouldThrowExceptionWhenDestinationIsAgainstRules() {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(7, 4);
		board.setPieceAt(pawn, from);
		boolean isException;
		//when
		try{
		pawn.checkIfMoveIsValidForPiece(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldThrowExceptionWhenPieceIsOnWayInDoubleMove() {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(7, 3);
		board.setPieceAt(pawn, from);
		board.setPieceAt(new Pawn(Color.WHITE), new Coordinate(7, 2));
		boolean isException;
		//when
		try{
		pawn.checkIfMoveIsValidForPiece(board,from , to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldThrowExceptionWhenGivenDestinationForCaptureButThereIsNotAPiece() {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(7, 1);
		Coordinate to = new Coordinate(6, 2);
		board.setPieceAt(pawn, from);
		boolean isException;
		//when
		try{
		pawn.checkIfMoveIsValidForPiece(board, from, to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
	
	@Test
	public void shouldThrowExceptionWhenPawnGoBack() {
		//given
		Board board = new Board();
		Piece pawn = new Pawn(Color.WHITE);
		Coordinate from = new Coordinate(6, 4);
		Coordinate to = new Coordinate(6, 3);
		board.setPieceAt(pawn, from);
		boolean isException;
		//when
		try{
		pawn.checkIfMoveIsValidForPiece(board, from, to);
		isException=false;
		} catch (InvalidMoveException e){
			isException = true;
		}
		//then
		assertTrue(isException);
	}
}
