package com.copetti.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBoxMenuItem;


@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

	public MainWindow()
	{
		setSize(new Dimension(500, 500));
		setTitle("Conway's Game Of Life");
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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setHorizontalAlignment(SwingConstants.TRAILING);
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Quit without saving");
		mntmNewMenuItem.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Game Options");
		menuBar.add(mnNewMenu_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Squared Grid");
		chckbxmntmNewCheckItem.setSelected(true);
		mnNewMenu_1.add(chckbxmntmNewCheckItem);
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
