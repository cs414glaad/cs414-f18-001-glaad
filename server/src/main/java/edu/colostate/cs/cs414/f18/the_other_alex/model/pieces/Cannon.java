package edu.colostate.cs.cs414.f18.the_other_alex.model.pieces;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Cell;
import edu.colostate.cs.cs414.f18.the_other_alex.model.NullPiece;
import edu.colostate.cs.cs414.f18.the_other_alex.model.PieceColor;

import java.util.ArrayList;

public class Cannon extends Piece {

	public Cannon(PieceColor color) {
		super(color);
		type = CANNON;
		capturableTypes = new ArrayList<String>(cannonCanCapture);
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
		int toCol = toCell.getCoordinate().getCol();
		int toRow = toCell.getCoordinate().getRow();
		int fromCol = fromCell.getCoordinate().getCol();
		int fromRow = fromCell.getCoordinate().getRow();
		// ensuring the move is not diagonal
		if (toCol == fromCol || toRow == fromRow) {
			return checkForPiecesBetween(toCell, cells, toCol, toRow, fromCol, fromRow);
		} else {
			return false;
		}
	}

	private boolean checkForPiecesBetween(Cell toCell, Cell[][] cells, int toCol,
			int toRow, int fromCol, int fromRow) {
		int lowIndex = -1;
		int highIndex = -1;
		if (toCol != fromCol) {
			if (toCol < fromCol) {
				lowIndex = toCol;
				highIndex = fromCol;

			} else {
				lowIndex = fromCol;
				highIndex = toCol;
			}
			if (pieceBetweenOnXAxis(lowIndex, highIndex, toRow, cells)) {
				return canCapture(toCell.getPiece());
			}
		}
		if (toRow != fromRow) {
			if (toRow < fromRow) {
				lowIndex = toRow;
				highIndex = fromRow;
				
			} else {
				lowIndex = fromRow;
				highIndex = toRow;
			}
			
			if (pieceBetweenOnYAxis(fromRow, toRow, toCol, cells)) {
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
