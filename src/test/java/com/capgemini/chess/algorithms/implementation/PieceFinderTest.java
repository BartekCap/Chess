package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.*;


public class PieceFinderTest {

	@Test
	public void shoulGiveSizeOfListAndCoordinateOfKings() throws InvalidMoveException {
		//given
		Board board = new Board();
		board.setPieceAt(new Queen(Color.WHITE), new Coordinate(0, 0));
		board.setPieceAt(new Rook(Color.WHITE), new Coordinate(1, 0));
		board.setPieceAt(new Pawn(Color.WHITE), new Coordinate(2, 0));
		board.setPieceAt(new Pawn(Color.WHITE), new Coordinate(3, 0));
		board.setPieceAt(new Pawn(Color.WHITE), new Coordinate(4, 0));
		Coordinate whiteKingCoords = new Coordinate(7, 0);
		board.setPieceAt(new King(Color.WHITE), whiteKingCoords);
		
		board.setPieceAt(new Bishop(Color.BLACK), new Coordinate(0, 1));
		board.setPieceAt(new Rook(Color.BLACK), new Coordinate(0, 2));
		board.setPieceAt(new Bishop(Color.BLACK), new Coordinate(0, 3));
		board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(0, 4));
		Coordinate blackKingCoords = new Coordinate(5, 0);
		board.setPieceAt(new King(Color.BLACK), blackKingCoords);
		
		PieceFinder pieceFinder = new PieceFinder(board);
		//when
		List<Coordinate> blackPieces = pieceFinder.findPieces(Color.BLACK);
		List<Coordinate> whitePieces = pieceFinder.findPieces(Color.WHITE);
		//then
		assertEquals(5, blackPieces.size());
		assertEquals(6, whitePieces.size());
		assertEquals(new Coordinate(7, 0), pieceFinder.findKing(Color.WHITE));
		assertEquals(new Coordinate(5, 0), pieceFinder.findKing(Color.BLACK));
	}
}