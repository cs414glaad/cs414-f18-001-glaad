package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;

public class Cannon extends Piece {

	public Cannon() {
		type = CANNON;
		capturableTypes = new ArrayList<String>(cannonCanCapture);
		isFlipped = false;
	}

	@Override
	public boolean isMoveValid(Cell fromCell, Cell toCell, Cell[][] cells) {
		if ((toCell.getPiece() instanceof NullPiece)
				|| toCell.getPiece().getIsFlipped() == true
				&& toCell.getPiece().getIsFlipped() == true) {

			return evaluateCorrectnessOfMove(fromCell, toCell, cells);

		} else {
			return false;
		}
	}

	private boolean evaluateCorrectnessOfMove(Cell fromCell, Cell toCell,
			Cell[][] cells) {
		int toX = toCell.getCoordinate().getX();
		int toY = toCell.getCoordinate().getY();
		int fromX = fromCell.getCoordinate().getX();
		int fromY = fromCell.getCoordinate().getY();
		// ensuring the move is not diagonal
		if (toX == fromX || toY == fromY) {
			return checkForPiecesBetween(toCell, cells, toX, toY, fromX, fromY);
		} else {
			return false;
		}
	}

	private boolean checkForPiecesBetween(Cell toCell, Cell[][] cells, int toX,
			int toY, int fromX, int fromY) {
		int lowIndex = -1;
		int highIndex = -1;
		if (toX != fromX) {
			if (toX < fromX) {
				lowIndex = toX;
				highIndex = fromX;

			} else {
				lowIndex = fromX;
				highIndex = toX;
			}
			if (pieceBetweenOnXAxis(lowIndex, highIndex, toY, cells)) {
				return canCapture(toCell.getPiece());
			}
		}
		if (toY != fromY) {
			if (toY < fromY) {
				lowIndex = toY;
				highIndex = fromY;
				
			} else {
				lowIndex = fromY;
				highIndex = toY;
			}
			
			if (pieceBetweenOnYAxis(fromY, toY, toX, cells)) {
				return canCapture(toCell.getPiece());
			}
		}
		return false;
	}

	public boolean pieceBetweenOnXAxis(int lowIndex, int highIndex, int row,
			Cell[][] cells) {
		boolean pieceBetween = false;
		for (int i = lowIndex + 1; i < highIndex; ++i) {
			// technically, x is y and y is x
			if ((cells[row][i].getPiece() instanceof NullPiece) == false) {
				// only allow them to jump one piece
				if (pieceBetween == true) {
					return false;
				} else {
					pieceBetween = true;
				}
			}
		}
		return pieceBetween;
	}

	public boolean pieceBetweenOnYAxis(int lowIndex, int highIndex, int col,
			Cell[][] cells) {
		boolean pieceBetween = false;
		for (int i = lowIndex + 1; i < highIndex; ++i) {
			if ((cells[i][col].getPiece() instanceof NullPiece) == false) {
				if (pieceBetween == true) {
					return false;
				} else {
					pieceBetween = true;
				}
			}
		}
		return pieceBetween;
	}
}
