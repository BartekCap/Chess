package com.capgemini.chess.algorithms.pieces;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Pawn extends Piece {
	int deltaX;
	int deltaY;

	public Pawn(Color color) {
		super(color);
	}

	// DO REFACTORINGU
	@Override
	public MoveType checkIfMoveIsValidForPiece(Board board, Coordinate from, Coordinate to)
			throws InvalidMoveException {
		initialPawnValidation(from, to);
		if (isEnPassant(board, from, to)) {
			return MoveType.EN_PASSANT;
		}
		return getMoveTypeByValidateAttackAndCapture(board, from, to);
	}

	private MoveType getMoveTypeByValidateAttackAndCapture(Board board, Coordinate from, Coordinate to)
			throws InvalidMoveException {
		if (deltaX == 0 && Math.abs(deltaY) == 2) {
			validateDoubleMove(board, from, to);
			return getMoveType(board, from, to);
		} else if (deltaX == 0 && Math.abs(deltaY) == 1 && board.getPieceAt(to) == null) {
			return MoveType.ATTACK;
		} else if (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 1 && board.getPieceAt(to) != null
				&& board.getPieceAt(to).getColor() != color) {
			return MoveType.CAPTURE;
		} else {
			throw new InvalidMoveException();
		}
	}

	private void initialPawnValidation(Coordinate from, Coordinate to) throws InvalidMoveException {
		setDeltaXAndDeltaY(from, to);
		validateThatPieceCanMoveThatDirection(from, to);
		validateDistanceForPawn(from, to);
	}

	private void setDeltaXAndDeltaY(Coordinate from, Coordinate to) {
		deltaX = from.getX() - to.getX();
		deltaY = from.getY() - to.getY();
	}

	private void validateDistanceForPawn(Coordinate from, Coordinate to) throws InvalidMoveException {
		if (!((Math.abs(deltaY) == 2 && deltaX == 0) || Math.abs(deltaY) == 1)) {
			throw new InvalidMoveException("Pawn cant move more than 2 square");
		}
	}

	private void validateDoubleMove(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		int avarageX = (from.getX() + to.getX()) / 2;
		int avarageY = (from.getY() + to.getY()) / 2;
		// TODO do zrobienia
		if (board.getPieceAt(new Coordinate(avarageX, avarageY)) != null || !(from.getY() == 1 || from.getY() == 6))
			throw new InvalidMoveException("Between your piece and destination is another piece!");
	}

	private boolean isEnPassant(Board board, Coordinate from, Coordinate to) {
		if (board.getMoveHistory().size() < 1) {
			return false;
		}
		if (checkIfIsPosibleMove(board, from, to)) {
			return checkLastMoveThatIsDoubleMoveAndIsPawn(board);
		}
		return false;
	}

	private boolean checkLastMoveThatIsDoubleMoveAndIsPawn(Board board) {
		Move lastMove = board.getMoveHistory().get(board.getMoveHistory().size() - 1);
		int lastMoveDeltaY = lastMove.getFrom().getY() - lastMove.getTo().getY();
		if (lastMove.getMovedPiece().equals(new Pawn(getOppositColor())) && Math.abs(lastMoveDeltaY) == 2) {
			return true;
		}
		return false;
	}

	private Color getOppositColor() {
		if (color == Color.BLACK) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	private boolean checkIfIsPosibleMove(Board board, Coordinate from, Coordinate to) {
		if (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 1 && board.getPieceAt(to) == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void validateThatPieceCanMoveThatDirection(Coordinate from, Coordinate to) throws InvalidMoveException {
		if (color.equals(Color.BLACK)) {
			if (!(isDirectionW(deltaX, deltaY) || isDirectionSW(deltaX, deltaY) || isDirectionNW(deltaX, deltaY))) {
				throw new InvalidMoveException("Pawn cant move there. It is against rules for this piece");
			}
		} else {
			if (!(isDirectionNE(deltaX, deltaY) || isDirectionE(deltaX, deltaY) || isDirectionSE(deltaX, deltaY))) {
				throw new InvalidMoveException("Pawn cant move there. It is against rules for this piece");
			}
		}
	}
}
