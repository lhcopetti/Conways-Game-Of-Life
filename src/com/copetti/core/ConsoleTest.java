package com.copetti.core;

import com.copetti.core.ConwaysGameOfLife.BoardState;


public class ConsoleTest
{

	public static void main(String[] args)
	{
		int boardSize = 8;
		BoardState[][] myBoard = new BoardState[boardSize][boardSize];

		for( int i = 0; i < myBoard.length; i++ )
		{
			for( int j = 0; j < myBoard.length; j++ )
			{
				boolean addLiveCell = false;

				if (i == 2 && j > 1 && j < 5) addLiveCell = true;
				if (i == 3 && j > 0 && j < 4) addLiveCell = true;

				/* Blinker
				if ( i == 1 &&  j >= 0 && j <= 2)
					addLiveCell = true;
				*/
				
				if (addLiveCell)
					myBoard[i][j] = BoardState.LIVE;
				else
					myBoard[i][j] = BoardState.DEAD;
			}
		}

		ConwaysGameOfLife cg = new ConwaysGameOfLife(myBoard);
		int numberOfTurns = 4;
		for( int i = 1; i <= numberOfTurns; i++ )
		{
			System.out.println("Turn: [" + i + "]");
			

			
			System.out.println(cg);
			cg.getNextState();
		}

	}
}
