package com.copetti.core;

public enum BoardState
{
	LIVE
	{

		@Override
		public String toString()
		{
			return "X";
		}
	},
	DEAD
	{

		@Override
		public String toString()
		{
			return "_";
		}
	};

	public static BoardState[][] getDeadBoard(int size)
	{
		return getBoard(size, DEAD);
	}

	public static BoardState[][] getLiveBoard(int size)
	{
		return getBoard(size, LIVE);
	}

	private static BoardState[][] getBoard(int size, BoardState bs)
	{
		BoardState[][] b = new BoardState[size][size];

		for( int i = 0; i < b.length; i++ )
			for( int j = 0; j < b.length; j++ )
				b[i][j] = bs;

		return b;
	}

	public static boolean isEqual(BoardState[][] bs1, BoardState[][] bs2)
	{
		if (bs1 == null || bs2 == null || bs1.length != bs2.length
				|| bs1[0].length != bs2.length) return false;

		for( int i = 0; i < bs1.length; i++ )
			for( int j = 0; j < bs2.length; j++ )
			{
				if (bs1[i][j] != bs2[i][j]) return false;
			}

		return true;
	}
}