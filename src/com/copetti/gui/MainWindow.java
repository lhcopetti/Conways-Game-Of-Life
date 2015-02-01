package com.copetti.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

	public MainWindow()
	{
		setTitle("Conway's Game Of Life");
		setSize(730, 559);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 713, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 509, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Grid pnl_Board = new Grid(5);
		pnl_Board.setBorder(new TitledBorder(null, "Conway's Board",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				SystemColor.textHighlight));
		pnl_Board.setLayout(null);
		GridBagConstraints gbc_pnl_Board = new GridBagConstraints();
		gbc_pnl_Board.fill = GridBagConstraints.BOTH;
		gbc_pnl_Board.gridx = 0;
		gbc_pnl_Board.gridy = 0;
		getContentPane().add(pnl_Board, gbc_pnl_Board);

		setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				new MainWindow();
			}
		});
	}
}
