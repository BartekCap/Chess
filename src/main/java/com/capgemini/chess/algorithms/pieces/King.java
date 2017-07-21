package com.capgemini.chess.algorithms.pieces;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.service.KingInCheckValidator;
import com.capgemini.chess.algorithms.pieces.abstracts.MultiMoveable;
import com.capgemini.chess.algorithms.pieces.abstracts.Piece;

public class King extends Piece implements MultiMoveable {

	private static final int CASTLING_ITERATOR_N = 4;
	private static final int CASTLING_ITERATOR_S = 3;
	private static final int ROOK_X_COORDINATES_N = 0;
	private static final int ROOK_X_COORDINATES_S = 7;
	private int deltaX;
	private int deltaY;

	@Override
	public MoveType checkIfMoveIsValidForPiece(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		setDeltaXAndDeltaY(from, to);
		if (isCastling(board, from, to)) {
			return MoveType.CASTLING;
		}
		validateThatPieceCanMoveThatDirection(from, to);
		return getMoveType(board, from, to);
	}

	@Override
	protected void validateThatPieceCanMoveThatDirection(Coordinate from, Coordinate to) throws InvalidMoveException {
		if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) {
			throw new InvalidMoveException("King cant move there. It is against rules for this piece");
		}
	}

	private boolean isCastling(Board board, Coordinate from, Coordinate to) {
		try {
			castlingInitialValidation();
			castlingDeepValidation(board, from, to);
			return true;
		} catch (InvalidMoveException e) {
			return false;
		}
	}

	private void castlingInitialValidation() throws InvalidMoveException {
		if (!(Math.abs(deltaX) == 2 && deltaY == 0)) {
			throw new InvalidMoveException();
		}
	}

	private void castlingDeepValidation(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		Coordinate rookCoordinates;
		List<Coordinate> coordinatesThatCantBeInCapture = new ArrayList<>();
		if (deltaX > 0) {
			validateIfSquaresAreNotNull(-1, 0, CASTLING_ITERATOR_N, board, from, to);
			rookCoordinates = new Coordinate(ROOK_X_COORDINATES_N, from.getY());
			coordinatesThatCantBeInCapture = createCoordinateToValidateSquares(-1, from, to);

		} else {
			validateIfSquaresAreNotNull(1, 0, CASTLING_ITERATOR_S, board, from, to);
			rookCoordinates = new Coordinate(ROOK_X_COORDINATES_S, from.getY());
			coordinatesThatCantBeInCapture = createCoordinateToValidateSquares(1, from, to);
		}
		validateRookExist(board, rookCoordinates);
		validateColor(board, from, rookCoordinates);
		validateRookAndKingHistory(board, rookCoordinates);
		validateThatKingAndSquaresCanByInDanger(board, coordinatesThatCantBeInCapture);
	}

	private void validateRookExist(Board board, Coordinate rookCoordinates) throws InvalidMoveException {
		if(board.getPieceAt(rookCoordinates)==null || !board.getPieceAt(rookCoordinates).equals(new Rook(color))){
			throw new InvalidMoveException("There is no rook to castling");
		}
	}

	private void validateColor(Board board, Coordinate from, Coordinate rookCoordinates) throws InvalidMoveException {
		if(board.getPieceAt(from).getColor()!=board.getPieceAt(rookCoordinates).getColor()){
			throw new InvalidMoveException("You cant do castling with opponent!");
		}
	}

	private void validateRookAndKingHistory(Board board, Coordinate rookCoordinates) throws InvalidMoveException {
		for (Move move : board.getMoveHistory()) {
			if (move.getMovedPiece().equals(this) || move.getFrom().equals(rookCoordinates)) {
				throw new InvalidMoveException("You cant do castling because King or Rook moved before");
			}
		}
	}

	private List<Coordinate> createCoordinateToValidateSquares(int sign, Coordinate from, Coordinate to) {
		List<Coordinate> coordinates = new ArrayList<>();
		coordinates.add(from);
		coordinates.add(to);
		coordinates.add(new Coordinate(from.getX() + sign * 1, from.getY()));
		return coordinates;
	}

	private void validateThatKingAndSquaresCanByInDanger(Board board, List<Coordinate> coordinates)
			throws InvalidMoveException {
		KingInCheckValidator validateKing = new KingInCheckValidator(board);
		validateKing.validateKingsWayInCastlingThatCantBeInCapture(board.getPieceAt(coordinates.get(0)).getColor(), coordinates);
	}

	public King(Color color) {
		super(color);
	}

	private void setDeltaXAndDeltaY(Coordinate from, Coordinate to) {
		deltaX = from.getX() - to.getX();
		deltaY = from.getY() - to.getY();
	}
}