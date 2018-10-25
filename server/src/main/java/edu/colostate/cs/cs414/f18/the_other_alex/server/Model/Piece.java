package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

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
	protected static final List<String> horseCanCapture = Arrays.asList(
			HORSE, CANNON, SOLDIER, NULL);
	protected static final List<String> soldierCanCapture = Arrays.asList(
			GENERAL, SOLDIER, NULL);
	protected static final List<String> cannonCanCapture = Arrays.asList(
			ADVISOR, ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, NULL);

	protected ArrayList<String> capturableTypes;
	protected boolean isFlipped;
	protected String type;

	public boolean canCapture(Piece piece) 
	{
		if (capturableTypes.contains(piece.getType())) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

	public boolean isMoveValid(Cell toCell, Cell fromCell, Cell[][] cells) 
	{
		int toX = toCell.getCoordinate().getX();
		int toY = toCell.getCoordinate().getY();
		int fromX = fromCell.getCoordinate().getX();
		int fromY = fromCell.getCoordinate().getY();
		//ensuring the move is not diagonal
		if (toX == fromX || toY == fromY)
		{
			//ensuring the cells are adjacent
			if (Math.abs(toX - fromX) == 1)
			{
				return canCapture(toCell.getPiece());
			}
			else if (Math.abs(toY - fromY) == 1)
			{
				return canCapture(toCell.getPiece());
			}
			else
			{
				return false;
			}
			
		}
		return false;
	}

	public String getType() 
	{
		return type;
	}
}
