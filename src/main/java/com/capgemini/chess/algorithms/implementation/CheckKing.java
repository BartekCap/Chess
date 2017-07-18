package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.pieces.*;

public class CheckKing {

	private Board board;

	public CheckKing(Board board) {
		this.board = board;
	}

	public void checkIfKingIsInCheck(Color color) throws KingInCheckException {
		List<Coordinate> piecesThatAreDangerousForKing = findOtherPieceThenKing(color);
		Coordinate kingsCoordinate = findKing(color);
		for (Coordinate c : piecesThatAreDangerousForKing) {
			try {
				board.getPieceAt(c).checkIfMoveIsValid(null, c, kingsCoordinate);
			} catch (InvalidMoveException ex) {
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

	private List<Coordinate> findOtherPieceThenKing(Color color) {
		Coordinate pieceCoordinate;
		List<Coordinate> piecesCoordinates = new ArrayList<>();
		for (int rowBoard = 0; rowBoard < Board.SIZE; rowBoard++) {
			for (int columnBoard = 0; columnBoard < Board.SIZE; columnBoard++) {
				Piece actualPiece = board.getPieces()[rowBoard][columnBoard];
				if ((actualPiece instanceof Piece && !(actualPiece instanceof King))
						&& actualPiece.getColor() != color) {
					pieceCoordinate = new Coordinate(rowBoard, columnBoard);
					piecesCoordinates.add(pieceCoordinate);
				}
			}
		}
		return piecesCoordinates;
	}
}