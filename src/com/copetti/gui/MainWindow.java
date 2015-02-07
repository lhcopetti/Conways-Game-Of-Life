package com.copetti.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

	private Grid pnl_Board;

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

		pnl_Board = new Grid(15);
		pnl_Board.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyReleased(KeyEvent e)
			{
				keyPressedEvent(e);
			}
		});
		pnl_Board.setBorder(new TitledBorder(null, "Conway's Board",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				SystemColor.textHighlight));
		pnl_Board.setLayout(null);
		pnl_Board.setFocusable(true);
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

		JMenuItem mntmChangeGridSize = new JMenuItem("Change grid Size");
		mntmChangeGridSize.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				changeGridSizeOptionSelected(e);
			}
		});

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Square Grid?");
		mntmNewMenuItem_1.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				squareGridOptionSelected(e);
			}

		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mnNewMenu_1.add(mntmChangeGridSize);
		setVisible(true);
	}

	private void keyPressedEvent(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_SPACE:
			getPnl_Board().keyboardSpacePressed();
			break;
		case KeyEvent.VK_ENTER:
			getPnl_Board().keyboardEnterPressed();
			break;
		case KeyEvent.VK_ESCAPE:
			dispose();
			break;
		}
	}

	private void squareGridOptionSelected(ActionEvent e)
	{
		int height = getHeight();
		int width = getWidth();

		int greater = Math.max(height, width);

		setSize(greater, greater);

	}

	private void changeGridSizeOptionSelected(ActionEvent e)
	{
		// Create a JOptionPane to prompt the user for grid size
		String sNewGridSize = JOptionPane.showInputDialog(MainWindow.this,
				"Please choose a new grid size (5-50).");

		try
		{
			int newGridSize = Integer.parseInt(sNewGridSize);
			if (newGridSize >= 5 && newGridSize <= 50)
				getPnl_Board().changeGridSize(newGridSize);
			else
				throw new InputMismatchException();

		}
		catch (InputMismatchException ex)
		{
			JOptionPane
					.showMessageDialog(
							MainWindow.this,
							"Invalid value. The input must be a number between 5 and 50.",
							"Error", JOptionPane.ERROR_MESSAGE);
		}

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

	public Grid getPnl_Board()
	{
		return pnl_Board;
	}
}
