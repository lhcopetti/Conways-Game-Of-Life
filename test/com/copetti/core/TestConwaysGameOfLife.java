package com.copetti.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class TestConwaysGameOfLife
{

	ConwaysGameOfLife cg_boardState_1;
	ConwaysGameOfLife cg_boardState_2;

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		/*-
		 * First configuration of Board: 
		 * | A | D | D |  
		 * | D | A | D | 
		 * | D | D | A |  
		 */
		BoardState[][] bState_1 = BoardState.getDeadBoard(3);
		bState_1[0][0] = BoardState.LIVE;
		bState_1[1][1] = BoardState.LIVE;
		bState_1[2][2] = BoardState.LIVE;
		cg_boardState_1 = new ConwaysGameOfLife(bState_1);

		/*-
		 * Second configuration of Board: 
		 * | A | A | A | 
		 * | A | D | A | 
		 * | A | A | A |
		 */
		BoardState[][] bState_2 = BoardState.getAliveBoard(3);
		bState_2[1][1] = BoardState.DEAD;
		cg_boardState_2 = new ConwaysGameOfLife(bState_2);
	}

	@Test
	public void testGetNumberOfNeighbours()
	{
		assertTrue(cg_boardState_1.getNumberOfNeighbours(0, 0) == 1);
		assertTrue(cg_boardState_1.getNumberOfNeighbours(1, 1) == 2);
		assertTrue(cg_boardState_1.getNumberOfNeighbours(2, 2) == 1);
		assertTrue(cg_boardState_1.getNumberOfNeighbours(1, 2) == 2);

		assertTrue(cg_boardState_2.getNumberOfNeighbours(0, 0) == 2);
		assertTrue(cg_boardState_2.getNumberOfNeighbours(1, 1) == 8);
		assertTrue(cg_boardState_2.getNumberOfNeighbours(2, 2) == 2);
		assertTrue(cg_boardState_2.getNumberOfNeighbours(1, 2) == 4);
	}

	@Test
	public void testIsInsideBoard()
	{
		// No need to test for both cg_boardstate's since they are of the same
		// size.

		// Inside Test Cases
		assertTrue(cg_boardState_1.isInsideBoard(0, 0));
		assertTrue(cg_boardState_1.isInsideBoard(1, 1));
		assertTrue(cg_boardState_1.isInsideBoard(2, 2));

		// Outside Test Cases
		assertFalse(cg_boardState_1.isInsideBoard(3, 3));
		assertFalse(cg_boardState_1.isInsideBoard(2, 3));
		assertFalse(cg_boardState_1.isInsideBoard(3, 2));

		assertFalse(cg_boardState_1.isInsideBoard(-3, -3));
		assertFalse(cg_boardState_1.isInsideBoard(2, -3));
		assertFalse(cg_boardState_1.isInsideBoard(3, -2));

		assertFalse(cg_boardState_1.isInsideBoard(1, -1));
		assertFalse(cg_boardState_1.isInsideBoard(-2, 2));
	}

	@Test
	public void testGetNextState()
	{
		/*-
		 * After two rounds of the conway's Game of Life 
		 * algorythm the board (1) should be equals to:
		 * 
		 * | A | D | D |     | D | D | D |    | D | D | D |
		 * | D | A | D |  -> | D | A | D | -> | D | D | D |
		 * | D | D | A |     | D | D | D |    | D | D | D |
		 */

		// Stage 1
		BoardState[][] bs_1_stage1 = BoardState.getDeadBoard(3);
		bs_1_stage1[1][1] = BoardState.LIVE;

		// Stage 2
		BoardState[][] bs_1_stage2 = BoardState.getDeadBoard(3);

		cg_boardState_1.getNextState();
		assertTrue(BoardState.isEqual(cg_boardState_1.getBoard(), bs_1_stage1));

		cg_boardState_1.getNextState();
		assertTrue(BoardState.isEqual(cg_boardState_1.getBoard(), bs_1_stage2));

		/*-
		 * After two rounds of the conway's Game of Life 
		 * algorythm the board (2) should be equals to:
		 * 
		 * | A | A | A |     | A | D | A |    | D | D | D |
		 * | A | D | A |  -> | D | D | D | -> | D | D | D |
		 * | A | A | A |     | A | D | A |    | D | D | D |
		 */

		// Stage 1
		BoardState[][] bs_2_stage1 = BoardState.getDeadBoard(3);
		bs_2_stage1[0][0] = BoardState.LIVE;
		bs_2_stage1[0][2] = BoardState.LIVE;
		bs_2_stage1[2][0] = BoardState.LIVE;
		bs_2_stage1[2][2] = BoardState.LIVE;

		// Stage 2
		BoardState[][] bs_2_stage2 = BoardState.getDeadBoard(3);

		cg_boardState_2.getNextState();
		assertTrue(BoardState.isEqual(cg_boardState_2.getBoard(), bs_2_stage1));

		cg_boardState_2.getNextState();
		assertTrue(BoardState.isEqual(cg_boardState_2.getBoard(), bs_2_stage2));

	}

}
