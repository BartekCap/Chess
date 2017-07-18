package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.pieces.*;

public class CheckKing {

	private Board board;

	public CheckKing(Board board) {
		this.board = board;
	}

	public Coordinate findKing(Color color) {
		Coordinate kingsCoordinate;
		for (int rowBoard = 0; rowBoard < Board.SIZE; rowBoard++) {
			for (int columnBoard = 0; columnBoard < Board.SIZE; columnBoard++) {
				if (board.getPieces()[rowBoard][columnBoard] instanceof King
						&& board.getPieces()[rowBoard][columnBoard].getColor() == color) {
					kingsCoordinate = new Coordinate(rowBoard, columnBoard);
					return kingsCoordinate;
				}
			}
		}
		//TODO Co tutaj zwrocic czy moze lepiej rzucic wyjatkiem?
		return null;
	}
	
	public List<Coordinate> findQueenBishopAndRook(Color color) {
		Coordinate pieceCoordinate;
		List<Coordinate> piecesCoordinates = new ArrayList<>();
		for (int rowBoard = 0; rowBoard < Board.SIZE; rowBoard++) {
			for (int columnBoard = 0; columnBoard < Board.SIZE; columnBoard++) {
				Piece actualPiece = board.getPieces()[rowBoard][columnBoard];
				if ((actualPiece instanceof Queen ||
						actualPiece instanceof Bishop ||
						actualPiece instanceof Rook)
						&& actualPiece.getColor() == color) {
					pieceCoordinate = new Coordinate(rowBoard, columnBoard);
					piecesCoordinates.add(pieceCoordinate);
				}
			}
		}
		return piecesCoordinates;
	}

	public void checkIfKingIsInCheck(Color color) throws InvalidMoveException{
		List<Coordinate> piecesThatAreDangerousForKing = findQueenBishopAndRook(color);
		Coordinate kingsCoordinate = findKing(color);
		for(Coordinate c : piecesThatAreDangerousForKing){
			//TODO jestli checkIfMoveIsValid wyrzuca wyjatek dla nie mozliwych ruchow to moze tak zostac jesli nie to bedzie trzeba obsluzyc to ponizej
			board.getPieceAt(c).checkIfMoveIsValid(c, kingsCoordinate);
			}	
	}
}