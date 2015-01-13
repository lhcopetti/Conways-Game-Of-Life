package com.copetti.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
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
		{ 504, 183, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 509, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Grid pnl_Board = new Grid(5);
		pnl_Board.setBorder(new TitledBorder(null, "Conway's Board", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		pnl_Board.setLayout(null);
		GridBagConstraints gbc_pnl_Board = new GridBagConstraints();
		gbc_pnl_Board.fill = GridBagConstraints.BOTH;
		gbc_pnl_Board.insets = new Insets(0, 0, 0, 5);
		gbc_pnl_Board.gridx = 0;
		gbc_pnl_Board.gridy = 0;
		getContentPane().add(pnl_Board, gbc_pnl_Board);

		JPanel pnl_Options = new JPanel();
		pnl_Options.setLayout(null);
		GridBagConstraints gbc_pnl_Options = new GridBagConstraints();
		gbc_pnl_Options.fill = GridBagConstraints.BOTH;
		gbc_pnl_Options.gridx = 1;
		gbc_pnl_Options.gridy = 0;
		getContentPane().add(pnl_Options, gbc_pnl_Options);

		JButton btn_StartGame = new JButton("START GAME!!!");
		btn_StartGame.setBounds(10, 449, 165, 49);
		pnl_Options.add(btn_StartGame);

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
