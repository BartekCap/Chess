package com.capgemini.chess.algorithms.implementation.service;

import java.util.List;
import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class KingInCheckValidator {

	private Board board;

	public KingInCheckValidator(Board board) {
		this.board = board;
	}

	public void validateKingsWayInCastlingThatCantBeInCapture(Color actualColor, List<Coordinate> coordinates)
			throws InvalidMoveException {
		List<Coordinate> opponentPiecesCoordinates = new PieceFinder(board).findOpponentPieces(actualColor);
		for (Coordinate coordinate : coordinates) {
			for (Coordinate coord : opponentPiecesCoordinates) {
				try {
					board.getPieceAt(coord).checkIfMoveIsValidForPiece(board, coord, coordinate);
				} catch (InvalidMoveException ex) {
					throw new InvalidMoveException("You cant do castling because squeres on Kings way can by captured");
				}
			}
		}
	}

	public void validateIfKingIsInCheck(Color actualPieceColor) throws InvalidMoveException, KingInCheckException {
		PieceFinder pieceFinder = new PieceFinder(board);
		List<Coordinate> opponentPiecesCoordinates = pieceFinder.findOpponentPieces(actualPieceColor);
		Coordinate kingsCoordinate = pieceFinder.findKing(actualPieceColor);
		boolean kingInCheck;
		for (Coordinate coord : opponentPiecesCoordinates) {
			try {
				board.getPieceAt(coord).checkIfMoveIsValidForPiece(board, coord, kingsCoordinate);
				kingInCheck = true;
			} catch (InvalidMoveException ex) {
				kingInCheck = false;
			}
			if (kingInCheck) {
				throw new KingInCheckException();

			}
		}
	}

	public void validateKingAfterMove(Coordinate from, Coordinate to)
			throws KingInCheckException, InvalidMoveException {
		Board boardAfterMove = createTemproraryBoard(board, from, to);
		new KingInCheckValidator(boardAfterMove).validateIfKingIsInCheck(board.getPieceAt(from).getColor());
	}

	private Board createTemproraryBoard(Board board, Coordinate from, Coordinate to) {
		Board temproraryBoard = board.clone();
		temproraryBoard.setPieceAt(temproraryBoard.getPieceAt(from), to);
		temproraryBoard.setPieceAt(null, from);
		return temproraryBoard;
	}
}