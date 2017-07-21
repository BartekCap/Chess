package com.capgemini.chess.algorithms.implementation.service;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class MoveValidator {

	private Board board;

	public MoveValidator(Board board) {
		super();
		this.board = board;
	}

	public Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException, KingInCheckException {
		new MoveValidator(board).initialValidation(from, to);
		MoveType moveType = board.getPieceAt(from).checkIfMoveIsValidForPiece(board, from, to);
		new KingInCheckValidator(board).validateKingAfterMove(from, to);
		return new Move(from, to, moveType, board.getPieceAt(from));
	}

	public boolean isMoveValid(Coordinate from, Coordinate to) {
		try {
			validateMove(from, to);
			return true;
		} catch (InvalidMoveException e) {
			return false;
		}
	}

	private void initialValidation(Coordinate from, Coordinate to) throws InvalidMoveException {
		validateIfCoordinateIsOutOfBoard(from);
		validateIfCoordinateIsOutOfBoard(to);
		validateIfPieceIsNotNull(from);
		validateTurn(from);
		validateIfFromEqualsTo(from, to);
	}

	private void validateIfFromEqualsTo(Coordinate from, Coordinate to) throws InvalidMoveException {
		if (from.equals(to)) {
			throw new InvalidMoveException("You cant move to the same place!");
		}
	}

	private void validateTurn(Coordinate from) throws InvalidMoveException {
		if (board.getPieceAt(from).getColor() != board.calculateNextMoveColor()) {
			throw new InvalidMoveException("Its not your turn");
		}
	}

	private void validateIfPieceIsNotNull(Coordinate from) throws InvalidMoveException {
		if (board.getPieceAt(from) == null) {
			throw new InvalidMoveException("You cant move without pawn");
		}
	}

	private void validateIfCoordinateIsOutOfBoard(Coordinate coordinate) throws InvalidMoveException {
		if (coordinate.getX() < 0 || coordinate.getX() >= Board.SIZE || coordinate.getY() < 0
				|| coordinate.getY() >= Board.SIZE) {
			throw new InvalidMoveException("Your coordinate is out of board!");
		}
	}
}
