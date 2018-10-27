package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Piece {
	protected static final String GENERAL = "General";
	protected static final String ADVISOR = "Advisor";
	protected static final String ELEPHANT = "Elephant";
	protected static final String CHARIOT = "Chariot";
	protected static final String HORSE = "Horse";
	protected static final String CANNON = "Cannon";
	protected static final String SOLDIER = "Soldier";
	// may be used to help with movement, might decide it's a bad idea
	protected static final String NULL = "Null piece";

	protected static final List<String> generalCanCapture = Arrays.asList(
			GENERAL, ADVISOR, ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, NULL);
	protected static final List<String> advisorCanCapture = Arrays.asList(
			ADVISOR, ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, NULL);
	protected static final List<String> elephantCanCapture = Arrays.asList(
			ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, NULL);
	protected static final List<String> chariotCanCapture = Arrays.asList(
			CHARIOT, HORSE, CANNON, SOLDIER, NULL);
	protected static final List<String> horseCanCapture = Arrays.asList(HORSE,
			CANNON, SOLDIER, NULL);
	protected static final List<String> soldierCanCapture = Arrays.asList(
			GENERAL, SOLDIER, NULL);
	protected static final List<String> cannonCanCapture = Arrays.asList(
			ADVISOR, ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, NULL);

	protected ArrayList<String> capturableTypes;
	protected boolean isFlipped;
	protected String type;
	protected PieceColor color;

	public Piece(PieceColor color){
		isFlipped = false;
		this.color = color;
	}
	public Piece(){
		isFlipped = false;
		this.color = PieceColor.NONE;
	}

	public boolean canCapture(Piece piece) {
		if (capturableTypes.contains(piece.getType())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMoveValid(Cell toCell, Cell fromCell, Cell[][] cells) {
		if ((toCell.getPiece() instanceof NullPiece)
				|| toCell.getPiece().getIsFlipped() == true
				&& toCell.getPiece().getIsFlipped() == true) {
			
			return ensureMoveIsNotDiagonalOrTooFar(toCell, fromCell);

		}

		else {
			return false;
		}
	}

	private boolean ensureMoveIsNotDiagonalOrTooFar(Cell toCell, Cell fromCell) {
		int toCol = toCell.getCoordinate().getCol();
		int toRow = toCell.getCoordinate().getRow();
		int fromCol = fromCell.getCoordinate().getCol();
		int fromRow = fromCell.getCoordinate().getRow();
		// ensuring the move is not diagonal
		if (toCol == fromCol || toRow == fromRow) {
			// ensuring the cells are adjacent
			if (Math.abs(toCol - fromCol) == 1) {
				return canCapture(toCell.getPiece());
			} else if (Math.abs(toRow - fromRow) == 1) {
				return canCapture(toCell.getPiece());
			} else {
				return false;
			}
		}
		return false;
	}

	public String getType() {
		return type;
	}

	public void flipPiece() {
		isFlipped = true;
		// may need to notify observer now
	}

	public boolean getIsFlipped() {
		return isFlipped;
	}

	public PieceColor getColor() {return color;}
}
