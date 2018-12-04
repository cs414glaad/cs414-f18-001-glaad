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
	public boolean isMoveValid(Cell toCell, Cell fromCell, Cell[][] cells) {
		//making sure that validity is checked with opposing pieces only
		if (!arePiecesDifferentColors(toCell, fromCell)) {
			return false;
		}

		if(toCell.getPiece() instanceof NullPiece || toCell.getPiece().getIsFlipped() == true) {
			return decideBetweenMoveOrCapture(toCell, fromCell, cells);
		}
		else {
			return false;
		}
	}


	private boolean decideBetweenMoveOrCapture(Cell toCell, Cell fromCell, Cell[][] cells){
		System.out.println("entering decideBetweenMoveOrCapture"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing
		//if we are a cannon and were trying to capture a piece, call the method that checks cannon can capture
		if(!(toCell.getPiece().type.equals("Null piece"))) {
			return isValidCapture(fromCell, toCell, cells);
		}

		//if cannon is just trying to move, then use generic move test that all pieces abide by
		return ensureMoveIsNotDiagonalOrTooFar(toCell, fromCell);
	}


	private boolean isValidCapture(Cell fromCell, Cell toCell, Cell[][] cells) {
		System.out.println("entering isValidCapture"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing
			return evaluateCorrectnessOfMove(fromCell, toCell, cells);

	}

	private boolean evaluateCorrectnessOfMove(Cell fromCell, Cell toCell,
			Cell[][] cells) {
		System.out.println("entering evaluateCorrectnessOfMOve"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing
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
		System.out.println("entering checkForPieceBetween"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing
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

	private boolean pieceBetweenOnXAxis(int lowIndex, int highIndex, int row,
			Cell[][] cells) {
		System.out.println("pieceBetweenOnXaxis"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing
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

	private boolean pieceBetweenOnYAxis(int lowIndex, int highIndex, int col,
			Cell[][] cells) {
		System.out.println("pieceBetweenOnYaxis"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!testing

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
