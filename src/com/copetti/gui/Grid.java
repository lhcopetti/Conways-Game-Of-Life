package com.copetti.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.copetti.core.BoardState;
import com.copetti.core.ConwaysGameOfLife;


@SuppressWarnings("serial")
public class Grid extends JPanel
{

	private ConwaysGameOfLife cg;
	private Dimension gridCellSize;
	private int gridCellCount;

	public Grid(int gridCellCount)
	{
		super();
		this.gridCellCount = gridCellCount;
		cg = new ConwaysGameOfLife(BoardState.getAliveBoard(gridCellCount));
		this.gridCellSize = getGridCellSize();

		registerListeners();
	}

	private void registerListeners()
	{
		// MOUSE CLICKED EVENT
		this.addMouseListener(new MouseAdapter()
		{

			private int[] pressedAt = null;
			private int[] releasedAt = null;

			@Override
			public void mousePressed(MouseEvent e)
			{
				pressedAt = getGridCoordinates(e.getX(), e.getY());
			}

			public void mouseReleased(MouseEvent e)
			{
				releasedAt = getGridCoordinates(e.getX(), e.getY());

				if (pressedAt[0] == releasedAt[0]
						&& pressedAt[1] == releasedAt[1])
					mouseClickedAt(pressedAt[0], releasedAt[1]);
			}
		});

		// RESIZE EVENT
		this.addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent e)
			{
				resizeEventHandler(e);
			}
		});
	}

	public void resizeEventHandler(ComponentEvent e)
	{
		System.out.println("Resize event...");

		int cellSizeX = this.getWidth() / gridCellCount;
		int cellSizeY = this.getHeight() / gridCellCount;

		gridCellSize = new Dimension(cellSizeX, cellSizeY);
	}

	private int[] getGridCoordinates(int x, int y)
	{
		int i = (int) (x / gridCellSize.getWidth());
		int j = (int) (y / gridCellSize.getHeight());

		// Not used anymore. The cells are squared.
		// if (i >= gridCellCount || j >= gridCellCount)
		// return new Point(-1, -1);
		// else
		return new int[]
		{ i, j };
	}

	private void mouseClickedAt(int x, int y)
	{
		System.out.println("Mouse clicked at: X: [" + x + "] and Y: [" + y
				+ "].");

		// Invert the state of the cell that has been clicked.
		cg.negateOnBoard(x, y);

		// Force the repaint.
		repaint();
	}

	public void setGridSize(int gridCellcount)
	{
		if (gridCellcount > 0)
			this.gridCellCount = gridCellcount;
		else
			this.gridCellCount = 5; // Default 5x5 Grid.
	}

	public Dimension getGridCellSize()
	{
		Dimension ds = this.getSize();

		int xAxis = ds.width;
		int yAxis = ds.height;

		return new Dimension(xAxis / gridCellCount, yAxis / gridCellCount);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		drawConwaysGrid(g);

		// Draw grids last so it appears on top.
		drawGridLines(g, Color.BLUE);

	}

	private void drawConwaysGrid(Graphics g)
	{
		BoardState[][] bs = cg.getBoard();

		for( int i = 0; i < bs.length; i++ )
			for( int j = 0; j < bs.length; j++ )
				drawRect(g, i, j, getColor(bs[i][j]));

	}

	private void drawGridLines(Graphics g, Color c)
	{
		Color LastColor = g.getColor();
		g.setColor(c);

		for( int i = 0; i < gridCellCount; i++ )
			for( int j = 0; j < gridCellCount; j++ )
			{
				g.drawRect(i * gridCellSize.width, j * gridCellSize.height,
						gridCellSize.width, gridCellSize.height);
			}

		g.setColor(LastColor);
	}

	private void drawRect(Graphics g, int i, int j, Color color)
	{
		Color lastColor = g.getColor();

		g.setColor(color);
		g.fillRect(i * gridCellSize.width, j * gridCellSize.height,
				gridCellSize.width, gridCellSize.height);

		// Restore graphics object.
		g.setColor(lastColor);

	}

	public Color getColor(BoardState bs)
	{
		if (bs == BoardState.DEAD)
			return Color.BLACK;
		else
			return Color.WHITE;
	}

}
