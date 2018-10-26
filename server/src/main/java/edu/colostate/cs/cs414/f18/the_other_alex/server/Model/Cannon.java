package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

import java.util.ArrayList;

public class Cannon extends Piece {

	public Cannon() {
		type = CANNON;
		capturableTypes = new ArrayList<String>(cannonCanCapture);
		isFlipped = false;
	}

	@Override
	public boolean isMoveValid(Cell fromCell, Cell toCell, Cell[][] cells)
	{
		if ((toCell.getPiece() instanceof NullPiece) || toCell.getPiece().getIsFlipped() == true && toCell.getPiece().getIsFlipped() == true)
		{
		
		int toX = toCell.getCoordinate().getX();
		int toY = toCell.getCoordinate().getY();
		int fromX = fromCell.getCoordinate().getX();
		int fromY = fromCell.getCoordinate().getY();
		//ensuring the move is not diagonal
			if (toX == fromX || toY == fromY)
			{
				if (toX < fromX)
				{
					if (pieceBetweenOnXAxis(toX, fromX, toY, cells))
					{
						return canCapture(toCell.getPiece());
					}
				}
				else if (toX > fromX)
				{
					if (pieceBetweenOnXAxis(fromX, toX, toY, cells))
				 	{
					 	return canCapture(toCell.getPiece());
				 	}
			
				}
				else if (toY < fromY)
				{
					if (pieceBetweenOnYAxis(toY, fromY, toX, cells))
				 	{
						return canCapture(toCell.getPiece());
				 	}
				}
				else if (toY > fromY)
				{
					if (pieceBetweenOnYAxis(fromY, toY, toX, cells))
				 	{
						return canCapture(toCell.getPiece());
				 	}
				}
				else
				{// cannot move to where your piece already is
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		return false;
	}
	
	public boolean pieceBetweenOnXAxis(int lowIndex, int highIndex, int row, Cell[][] cells)
	{
		boolean pieceBetween = false;
		for (int i = lowIndex + 1; i < highIndex; ++i)
		{
			//technically, x is y and y is x
			if ((cells[row][i].getPiece() instanceof NullPiece) == false)
			{
				// only allow them to jump one piece
				if (pieceBetween == true)
				{
					return false;
				}
				else
				{
					pieceBetween = true;
				}
			}
		}
		return pieceBetween;
	}
	
	public boolean pieceBetweenOnYAxis(int lowIndex, int highIndex, int col, Cell[][] cells)
	{
		boolean pieceBetween = false;
		for (int i = lowIndex + 1; i < highIndex; ++i)
		{
			if ((cells[i][col].getPiece() instanceof NullPiece) == false)
			{
				if (pieceBetween == true)
				{
					return false;
				}
				else
				{
					pieceBetween = true;
				}
			}
		}
		return pieceBetween;
	}
}
