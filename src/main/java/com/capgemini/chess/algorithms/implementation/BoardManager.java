package com.capgemini.chess.algorithms.implementation;

import java.util.Arrays;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.implementation.service.MoveValidator;
import com.capgemini.chess.algorithms.implementation.service.KingInCheckValidator;
import com.capgemini.chess.algorithms.implementation.service.PieceFinder;
import com.capgemini.chess.algorithms.pieces.*;

public class BoardManager {

	private Board board = new Board();

	public BoardManager() {
		initBoard();
	}

	public BoardManager(List<Move> moves) {
		initBoard();
		for (Move move : moves) {
			addMove(move);
		}
	}

	public BoardManager(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return this.board;
	}

	public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		Move move = validateMove(from, to);
		addMove(move);
		return move;
	}

	public BoardState updateBoardState() {
		Color nextMoveColor = board.calculateNextMoveColor();
		boolean isKingInCheck = isKingInCheck(nextMoveColor);
		boolean isAnyMoveValid = isAnyMoveValid(nextMoveColor);
		BoardState boardState;
		if (isKingInCheck) {
			if (isAnyMoveValid) {
				boardState = BoardState.CHECK;
			} else {
				boardState = BoardState.CHECK_MATE;
			}
		} else {
			if (isAnyMoveValid) {
				boardState = BoardState.REGULAR;
			} else {
				boardState = BoardState.STALE_MATE;
			}
		}
		this.board.setState(boardState);
		return boardState;
	}

	public boolean checkThreefoldRepetitionRule() {
		int lastNonAttackMoveIndex = findLastNonAttackMoveIndex();
		List<Move> omittedMoves = this.board.getMoveHistory().subList(0, lastNonAttackMoveIndex);
		BoardManager simulatedBoardManager = new BoardManager(omittedMoves);
		int counter = 0;
		for (int i = lastNonAttackMoveIndex; i < this.board.getMoveHistory().size(); i++) {
			Move moveToAdd = this.board.getMoveHistory().get(i);
			simulatedBoardManager.addMove(moveToAdd);
			boolean areBoardsEqual = Arrays.deepEquals(this.board.getPieces(),
					simulatedBoardManager.getBoard().getPieces());
			if (areBoardsEqual) {
				counter++;
			}
		}
		return counter >= 2;
	}

	public boolean checkFiftyMoveRule() {
		if (this.board.getMoveHistory().size() < 100) {
			return false;
		}
		for (int i = this.board.getMoveHistory().size() - 1; i >= this.board.getMoveHistory().size() - 100; i--) {
			Move currentMove = this.board.getMoveHistory().get(i);
			if (currentMove.getType() != MoveType.ATTACK || currentMove.getMovedPiece().getClass() == Pawn.class) {
				return false;
			}
		}
		return true;
	}

	private void initBoard() {
		this.board.setPieceAt(new Rook(Color.BLACK), new Coordinate(0, 7));
		this.board.setPieceAt(new Knight(Color.BLACK), new Coordinate(1, 7));
		this.board.setPieceAt(new Bishop(Color.BLACK), new Coordinate(2, 7));
		this.board.setPieceAt(new Queen(Color.BLACK), new Coordinate(3, 7));
		this.board.setPieceAt(new King(Color.BLACK), new Coordinate(4, 7));
		this.board.setPieceAt(new Bishop(Color.BLACK), new Coordinate(5, 7));
		this.board.setPieceAt(new Knight(Color.BLACK), new Coordinate(6, 7));
		this.board.setPieceAt(new Rook(Color.BLACK), new Coordinate(7, 7));
		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(new Pawn(Color.BLACK), new Coordinate(x, 6));
		}
		this.board.setPieceAt(new Rook(Color.WHITE), new Coordinate(0, 0));
		this.board.setPieceAt(new Knight(Color.WHITE), new Coordinate(1, 0));
		this.board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(2, 0));
		this.board.setPieceAt(new Queen(Color.WHITE), new Coordinate(3, 0));
		this.board.setPieceAt(new King(Color.WHITE), new Coordinate(4, 0));
		this.board.setPieceAt(new Bishop(Color.WHITE), new Coordinate(5, 0));
		this.board.setPieceAt(new Knight(Color.WHITE), new Coordinate(6, 0));
		this.board.setPieceAt(new Rook(Color.WHITE), new Coordinate(7, 0));
		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(new Pawn(Color.WHITE), new Coordinate(x, 1));
		}
	}

	private void addMove(Move move) {
		addRegularMove(move);
		if (move.getType() == MoveType.CASTLING) {
			addCastling(move);
		} else if (move.getType() == MoveType.EN_PASSANT) {
			addEnPassant(move);
		}
		this.board.getMoveHistory().add(move);
	}

	private void addRegularMove(Move move) {
		Piece movedPiece = this.board.getPieceAt(move.getFrom());
		this.board.setPieceAt(null, move.getFrom());
		this.board.setPieceAt(movedPiece, move.getTo());
		performPromotion(move, movedPiece);
	}

	private void performPromotion(Move move, Piece movedPiece) {
		if (movedPiece.equals(new Pawn(Color.WHITE)) && move.getTo().getY() == (Board.SIZE - 1)) {
			this.board.setPieceAt(new Queen(Color.WHITE), move.getTo());
		}
		if (movedPiece.equals(new Pawn(Color.BLACK)) && move.getTo().getY() == 0) {
			this.board.setPieceAt(new Queen(Color.BLACK), move.getTo());
		}
	}

	private void addCastling(Move move) {
		if (move.getFrom().getX() > move.getTo().getX()) {
			Piece rook = this.board.getPieceAt(new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() + 1, move.getTo().getY()));
		} else {
			Piece rook = this.board.getPieceAt(new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() - 1, move.getTo().getY()));
		}
	}

	private void addEnPassant(Move move) {
		Move lastMove = this.board.getMoveHistory().get(this.board.getMoveHistory().size() - 1);
		this.board.setPieceAt(null, lastMove.getTo());
	}

	private Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException, KingInCheckException {
		Move validatedMove = new MoveValidator(board).validateMove(from, to);
		return validatedMove;
	}

	private boolean isKingInCheck(Color kingColor) {
		boolean isKingInCheck;
		try {
			new KingInCheckValidator(board).validateIfKingIsInCheck(kingColor);
			isKingInCheck = false;
		} catch (KingInCheckException e) {
			isKingInCheck = true;
		} catch (InvalidMoveException e) {
			isKingInCheck = false;
		}
		return isKingInCheck;
	}

	private boolean isAnyMoveValid(Color nextMoveColor) {
		List<Coordinate> actualColorPiecesCoordinates = new PieceFinder(board).findPieces(nextMoveColor);
		for (Coordinate iteratorCoordinate : actualColorPiecesCoordinates) {
			for (int iteratorRow = 0; iteratorRow < Board.SIZE; iteratorRow++) {
				for (int iteratorColumn = 0; iteratorColumn < Board.SIZE; iteratorColumn++) {
					if (new MoveValidator(board).isMoveValid(iteratorCoordinate,
							new Coordinate(iteratorRow, iteratorColumn))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private int findLastNonAttackMoveIndex() {
		int counter = 0;
		int lastNonAttackMoveIndex = 0;
		for (Move move : this.board.getMoveHistory()) {
			if (move.getType() != MoveType.ATTACK) {
				lastNonAttackMoveIndex = counter;
			}
			counter++;
		}
		return lastNonAttackMoveIndex;
	}
}