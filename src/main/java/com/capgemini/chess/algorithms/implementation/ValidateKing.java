package com.capgemini.chess.algorithms.implementation;

import java.util.List;
import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.pieces.*;

public class ValidateKing {

	private Board board;

	public ValidateKing(Board board) {
		this.board = board;
	}

	public void validateCastling(Color actualColor, List<Coordinate> coordinates) throws InvalidMoveException {
		List<Coordinate> opponentPieces = new PieceFinder(board).findOpponentPieces(actualColor);
		for (Coordinate coordinate : coordinates) {
			for (Coordinate coord : opponentPieces) {
				try {
					board.getPieceAt(coord).checkIfMoveIsValid(board, coord, coordinate);
				} catch (InvalidMoveException ex) {
					throw new InvalidMoveException("You cant do castling because squeres on Kings way can by captured");
				}
			}
		}
	}
	//TODO tutaj moze byc problem
	
	public void validateIfKingIsInCheck(Color color) throws InvalidMoveException, KingInCheckException {
		List<Coordinate> opponentPieces = new PieceFinder(board).findOpponentPieces(color);
		Coordinate kingsCoordinate = findKing(color);
		boolean kingInCheck;
		for (Coordinate coord : opponentPieces) {
			try {
				board.getPieceAt(coord).checkIfMoveIsValid(board, coord, kingsCoordinate);
				kingInCheck=true;
			} catch (InvalidMoveException ex) {
				kingInCheck=false;
			}
			if(kingInCheck){
				throw new KingInCheckException();
			}
		}
	}

	private Coordinate findKing(Color color) {
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
		// TODO Co tutaj zwrocic czy moze lepiej rzucic wyjatkiem?
		return null;
	}

//	public List<Coordinate> findOpponentPieces(Color color) {
//		Coordinate pieceCoordinate;
//		List<Coordinate> piecesCoordinates = new ArrayList<>();
//		for (int rowBoard = 0; rowBoard < Board.SIZE; rowBoard++) {
//			for (int columnBoard = 0; columnBoard < Board.SIZE; columnBoard++) {
//
//				// TODO ssss
//				Piece actualPiece = board.getPieces()[rowBoard][columnBoard];
//				if (actualPiece != null && actualPiece.getColor() != color) {
//					pieceCoordinate = new Coordinate(rowBoard, columnBoard);
//					piecesCoordinates.add(pieceCoordinate);
//				}
//			}
//		}
//		return piecesCoordinates;
//	}
}