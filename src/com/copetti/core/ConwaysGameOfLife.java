package com.copetti.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class ConwaysGameOfLife
{

	private BoardState[][] board;

	public int getNumberOfNeighbours(int i, int j)
	{
		int liveNeighbours = 0;

		for( int x = i - 1; x <= i + 1; x++ )
		{
			for( int y = j - 1; y <= j + 1; y++ )
			{
				if (!(x == i && y == j) && isInsideBoard(x, y)
						&& board[x][y] == BoardState.LIVE) liveNeighbours += 1;
			}
		}

		return liveNeighbours;
	}

	public int getBoardSize()
	{
		return board.length;
	}

	public boolean isInsideBoard(int i, int j)
	{
		return i >= 0 && j >= 0 && i < getBoardSize() && j < getBoardSize();
	}

	public ConwaysGameOfLife(BoardState[][] board)
	{
		if (board == null || board.length == 0 || !isSquared(board))
			throw new InputMismatchException(
					"There must be a valid Matrix for the game to work.");

		this.board = board;
	}

	private boolean isSquared(BoardState[][] matrix)
	{
		return matrix.length == matrix[0].length;
	}

	public BoardState[][] getBoard()
	{
		return board;
	}

	/**
	 * Returns true if the operation was successfull.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean negateOnBoard(int i, int j)
	{
		if (isInsideBoard(i, j))
		{
			board[i][j] = negate(board[i][j]);
			return true;
		}
		return false;
	}

	private BoardState negate(BoardState bs)
	{
		if (bs == BoardState.LIVE) return BoardState.DEAD;
		return BoardState.LIVE;
	}

	public void getNextState()
	{
		List<Point> flipState = new ArrayList<Point>();

		for( int i = 0; i < getBoardSize(); i++ )
		{
			for( int j = 0; j < getBoardSize(); j++ )
			{
				int neighbours = getNumberOfNeighbours(i, j);
				boolean addToList = false;

				if (board[i][j] == BoardState.LIVE)
				{
					// Any live cell with fewer than two live neighbours dies,
					// as if caused by under-population.
					if (neighbours < 2)
						addToList = true;
					// Any live cell with two or three live neighbours lives on
					// to the next generation.
					else
						if (neighbours == 2 || neighbours == 3)
							;
						else
							if (neighbours > 3) addToList = true;

				}
				else
				{
					// Any dead cell with exactly three live neighbours becomes
					// a live cell, as if by reproduction.
					if (neighbours == 3) addToList = true;
				}

				if (addToList) flipState.add(new Point(i, j));

			}
		}

		for( Point p : flipState )
			board[p.x][p.y] = negate(board[p.x][p.y]);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for( int i = 0; i < getBoardSize(); i++ )
			sb.append(i + " ");
		sb.append("\n");

		for( int i = 0; i < board.length; i++ )
		{
			sb.append(i + " ");
			for( int j = 0; j < board.length; j++ )
			{
				BoardState bs = board[i][j];
				sb.append(bs.toString() + " ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
