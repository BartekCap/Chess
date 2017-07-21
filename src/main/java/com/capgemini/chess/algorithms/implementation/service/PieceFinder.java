package com.capgemini.chess.algorithms.implementation.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.pieces.King;
import com.capgemini.chess.algorithms.pieces.abstracts.Piece;

public class PieceFinder {
	Board board;

	public PieceFinder(Board board) {
		this.board = board;
	}

	public Coordinate findKing(Color color) {
		Coordinate kingsCoordinate;
		for (int rowBoard = 0; rowBoard < Board.SIZE; rowBoard++) {
			for (int columnBoard = 0; columnBoard < Board.SIZE; columnBoard++) {
				if (board.getPieces()[rowBoard][columnBoard] != null
						&& board.getPieces()[rowBoard][columnBoard].equals(new King(color))) {
					kingsCoordinate = new Coordinate(rowBoard, columnBoard);
					return kingsCoordinate;
				}
			}
		}
		return null;
	}

	public List<Coordinate> findOpponentPieces(Color color) {
		return findPieces(getOppositColor(color));
	}

	public List<Coordinate> findPieces(Color color) {
		Coordinate pieceCoordinate;
		List<Coordinate> piecesCoordinates = new ArrayList<>();
		for (int rowBoard = 0; rowBoard < Board.SIZE; rowBoard++) {
			for (int columnBoard = 0; columnBoard < Board.SIZE; columnBoard++) {
				Piece actualPiece = board.getPieces()[rowBoard][columnBoard];
				if (actualPiece != null && actualPiece.getColor() == color) {
					pieceCoordinate = new Coordinate(rowBoard, columnBoard);
					piecesCoordinates.add(pieceCoordinate);
				}
			}
		}
		return piecesCoordinates;
	}

	private Color getOppositColor(Color color) {
		if (Color.BLACK == color) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}
}