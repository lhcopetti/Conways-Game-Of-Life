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

import com.copetti.core.ConwaysGameOfLife.BoardState;


public class Grid extends JPanel
{

	private int gridCellCount;
	private int gridCellSize;

	public Grid(int gridCellCount)
	{
		super();
		this.gridCellCount = gridCellCount;

		registerListeners();
	}

	private void registerListeners()
	{
		// MOUSE CLICKED EVENT
		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				mouseClickEventHandler(e);
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

		gridCellSize = Math.min(cellSizeX, cellSizeY);
	}

	private void mouseClickEventHandler(MouseEvent e)
	{
		Point p = absoluteToGridCoordinates(e.getX(), e.getY());

		mouseClickedAt(p.x, p.y);
	}

	private Point absoluteToGridCoordinates(int x, int y)
	{
		int i = x / gridCellSize;
		int j = y / gridCellSize;

		if (i >= gridCellCount || j >= gridCellCount)
			return new Point(-1, -1);
		else
			return new Point(i, j);
	}

	private void mouseClickedAt(int x, int y)
	{
		System.out.println("Mouse clicked at: X: [" + x + "] and Y: [" + y
				+ "].");
	}

	public void setGridSize(int gridCellcount)
	{
		if (gridCellcount > 0)
			this.gridCellCount = gridCellcount;
		else
			this.gridCellCount = 5; // Default 5x5 Grid.
	}

	public int getGridCellSize()
	{
		Dimension ds = this.getSize();

		int xAxis = ds.width;
		int yAxis = ds.height;

		return Math.min(xAxis / gridCellCount, yAxis / gridCellCount);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Update gridCellSize
		Dimension panelSize = getSize();
		int xAxis = panelSize.width;
		int yAxis = panelSize.height;

		gridCellSize = Math.min(xAxis / gridCellCount, yAxis / gridCellCount);

		drawRect(g, 0, 0, Color.BLACK);
		drawRect(g, 1, 1, Color.BLACK);
		drawRect(g, 2, 2, Color.BLUE);

	}

	private void drawRect(Graphics g, int i, int j, Color color)
	{
		Color lastColor = g.getColor();

		g.setColor(color);
		g.fillRect(i * gridCellSize, j * gridCellSize, gridCellSize,
				gridCellSize);

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
