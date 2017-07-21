package com.capgemini.chess.algorithms.data.generated;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.pieces.Piece;

public class Board implements Cloneable {

	public static final int SIZE = 8;

	private Piece[][] pieces = new Piece[SIZE][SIZE];
	private List<Move> moveHistory = new ArrayList<>();
	private BoardState state;

	public Board() {
	}

	public Color calculateNextMoveColor() {
		if (this.getMoveHistory().size() % 2 == 0) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public BoardState getState() {
		return state;
	}

	public void setState(BoardState state) {
		this.state = state;
	}

	public void setPieceAt(Piece piece, Coordinate coordinate) {
		pieces[coordinate.getX()][coordinate.getY()] = piece;
	}

	public Piece getPieceAt(Coordinate coordinate) {
		return pieces[coordinate.getX()][coordinate.getY()];
	}

	@Override
	public Board clone() {
		Board clone;
		try {
			clone = (Board) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Board cloning go wrong!");
		}
		clone.pieces = this.pieces.clone();
		for (int i = 0; i < 8; i++) {
			clone.pieces[i] = this.pieces[i].clone();
		}
		return clone;
	}
}