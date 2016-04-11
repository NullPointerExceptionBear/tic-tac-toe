import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class yurp
{
	public static void main(String[] arg)
	{
		JFrame f = new JFrame("Tic Tac Toe");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.getContentPane().add(new bPanel());
		f.setSize(500,400);
		f.setVisible(true);
	}
}

class Player
{
	private int score;
	private String symbol;
	Player()
	{
		score = 0;
		symbol = "";
	}
	
	public void setSymbol(String c)
	{
		symbol = c;
	}
	public String getSymbol()
	{
		return symbol;
	}
	public void setScore(int s)
	{
		score = s;
	}
	public int getScore()
	{
		return score;
	}
}

class bPanel extends JPanel implements ActionListener
{
	public bPanel()
	{
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, new mPanel());
		add(BorderLayout.WEST, new wPanel());
	}
	
	public void actionPerformed(ActionEvent a)
	{
		
	}
}

class wPanel extends JPanel implements ActionListener
{
	private MyThread m;
	private JButton newGame, again;
	public static boolean oneW, twoW, tie;
	public wPanel()
	{
		setBackground(Color.YELLOW);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(this);
		add(newGame);
		
		again = new JButton("Next");
		again.addActionListener(this);
		add(again);
		
		oneW = false;
		twoW = false;
		tie = false;
		
		m = new MyThread();
		m.start();
	}
	
	public void paintComponent(Graphics g)
	{
		String dspScr;
		dspScr = Integer.toString(mPanel.plyr1.getScore());
		super.paintComponent(g);
		g.drawString("Player 1 Score: " + dspScr , 50, 50);
		dspScr = Integer.toString(mPanel.plyr2.getScore());
		g.drawString("Player 2 Score: " + dspScr , 50, 70);
		if(tie == true)
		{
			g.drawString("Tie Game", 50, 200);
			repaint();
		}
		else if(oneW == true)
		{
			g.drawString("Player 1 Wins!", 50, 200);
			repaint();
		}
		else if(twoW == true)
		{
			g.drawString("Player 2 Wins!", 50, 200);
			repaint();
		}
	}
	
	class MyThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				repaint();
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{}
			}
		}
	}
	
	public void Reset()
	{
		mPanel.ClrField();
		mPanel.plyr1.setScore(0);
		mPanel.plyr2.setScore(0);
		repaint();
	}
	
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == newGame)
		{
			Reset();
		}
		if(a.getSource() == again)
		{
			mPanel.ClrField();
			repaint();
		}
	}
}

class mPanel extends JPanel implements ActionListener
{
	private static JButton[] field;
	public static int turnCt;
	
	public static Player plyr1, plyr2;
	private static boolean[] bxChk;
	
	public mPanel()
	{
		setLayout(new GridLayout(3,3));
		turnCt = 0;
		plyr1 = new Player();
		plyr2 = new Player();
		plyr1.setSymbol("X");
		plyr2.setSymbol("O");
		field = new JButton[9];
		bxChk = new boolean[9];
		
		for(int index = 0; index < 9; index++)
		{
			field[index] = new JButton();
			field[index].addActionListener(this);
			add(field[index]);
			bxChk[index] = false;
		}
		
	}
	
	public static void ClrField()
	{
		for(int index = 0; index < 9; index++)
		{
			field[index].setText("");
			field[index].setBackground(null);
			bxChk[index] = false;
			turnCt = 0;
		}
	}
	
	public void LockAll()
	{
		for(int index = 0; index < 9; index++)
		{
			bxChk[index] = true;
		}
	}
	
	public void CheckWinner()
	{
		String w1 = "X";
		String w2 = "O";
		wPanel.oneW = false;
		wPanel.twoW = false;
		wPanel.tie = false;
		
		//player 1 horz check
		if(w1.equals(field[0].getText()) && w1.equals(field[1].getText()) && w1.equals(field[2].getText()))
		{
			LockAll();
			field[0].setBackground(Color.GREEN);
			field[1].setBackground(Color.GREEN);
			field[2].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		else if(w1.equals(field[3].getText()) && w1.equals(field[4].getText()) && w1.equals(field[5].getText()))
		{
			LockAll();
			field[3].setBackground(Color.GREEN);
			field[4].setBackground(Color.GREEN);
			field[5].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		else if(w1.equals(field[6].getText()) && w1.equals(field[7].getText()) && w1.equals(field[8].getText()))
		{
			LockAll();
			field[6].setBackground(Color.GREEN);
			field[7].setBackground(Color.GREEN);
			field[8].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		//player 1 vert check
		else if(w1.equals(field[0].getText()) && w1.equals(field[3].getText()) && w1.equals(field[6].getText()))
		{
			LockAll();
			field[0].setBackground(Color.GREEN);
			field[3].setBackground(Color.GREEN);
			field[6].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		else if(w1.equals(field[1].getText()) && w1.equals(field[4].getText()) && w1.equals(field[7].getText()))
		{
			LockAll();
			field[1].setBackground(Color.GREEN);
			field[4].setBackground(Color.GREEN);
			field[7].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		else if(w1.equals(field[2].getText()) && w1.equals(field[5].getText()) && w1.equals(field[8].getText()))
		{
			LockAll();
			field[2].setBackground(Color.GREEN);
			field[5].setBackground(Color.GREEN);
			field[9].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		//player 1 diag check
		else if(w1.equals(field[0].getText()) && w1.equals(field[4].getText()) && w1.equals(field[8].getText()))
		{
			LockAll();
			field[0].setBackground(Color.GREEN);
			field[4].setBackground(Color.GREEN);
			field[8].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		else if(w1.equals(field[2].getText()) && w1.equals(field[4].getText()) && w1.equals(field[6].getText()))
		{
			LockAll();
			field[2].setBackground(Color.GREEN);
			field[4].setBackground(Color.GREEN);
			field[6].setBackground(Color.GREEN);
			System.out.println("Player 1 Wins!");
			plyr1.setScore(plyr1.getScore() + 1);
			wPanel.oneW = true;
		}
		//player 2 horz check
		else if(w2.equals(field[0].getText()) && w2.equals(field[1].getText()) && w2.equals(field[2].getText()))
		{
			LockAll();
			field[0].setBackground(Color.CYAN);
			field[1].setBackground(Color.CYAN);
			field[2].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		else if(w2.equals(field[3].getText()) && w2.equals(field[4].getText()) && w2.equals(field[5].getText()))
		{
			LockAll();
			field[3].setBackground(Color.CYAN);
			field[4].setBackground(Color.CYAN);
			field[5].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		else if(w2.equals(field[6].getText()) && w2.equals(field[7].getText()) && w2.equals(field[8].getText()))
		{
			LockAll();
			field[6].setBackground(Color.CYAN);
			field[7].setBackground(Color.CYAN);
			field[8].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		//player 2 vert check
		else if(w2.equals(field[0].getText()) && w2.equals(field[3].getText()) && w2.equals(field[6].getText()))
		{
			LockAll();
			field[0].setBackground(Color.CYAN);
			field[3].setBackground(Color.CYAN);
			field[6].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		else if(w2.equals(field[1].getText()) && w2.equals(field[4].getText()) && w2.equals(field[7].getText()))
		{
			LockAll();
			field[1].setBackground(Color.CYAN);
			field[4].setBackground(Color.CYAN);
			field[7].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		else if(w2.equals(field[2].getText()) && w2.equals(field[5].getText()) && w2.equals(field[8].getText()))
		{
			LockAll();
			field[2].setBackground(Color.CYAN);
			field[5].setBackground(Color.CYAN);
			field[8].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		//player 2 diag check
		else if(w2.equals(field[0].getText()) && w2.equals(field[4].getText()) && w2.equals(field[8].getText()))
		{
			LockAll();
			field[0].setBackground(Color.CYAN);
			field[4].setBackground(Color.CYAN);
			field[8].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
		else if(w2.equals(field[2].getText()) && w2.equals(field[4].getText()) && w2.equals(field[6].getText()))
		{
			LockAll();
			field[2].setBackground(Color.CYAN);
			field[4].setBackground(Color.CYAN);
			field[6].setBackground(Color.CYAN);
			System.out.println("Player 2 Wins!");
			plyr2.setScore(plyr2.getScore() + 1);
			wPanel.twoW = true;
		}
	}
	
	public void actionPerformed(ActionEvent a)
	{
		for(int chk = 0; chk < 9; chk++)
		{
			if(a.getSource() == field[chk])
			{
				if((turnCt % 2) == 0)
				{
					if(bxChk[chk] == false)
					{
						field[chk].setText(plyr1.getSymbol());
						bxChk[chk] = true;
						CheckWinner();
						turnCt++;
					}
					else
					{
						System.out.println("Spot Taken");
					}
				}
				else
				{
					if(bxChk[chk] == false)
					{
						field[chk].setText(plyr2.getSymbol());
						bxChk[chk] = true;
						CheckWinner();
						turnCt++;
					}
					else
					{
						System.out.println("Spot Taken");
					}
				}
				field[chk].setFont(new Font("Monospace", Font.PLAIN, 40));
				
				if(turnCt == 9 && wPanel.oneW == false && wPanel.twoW == false)
				{
					System.out.println("That's Game");
					wPanel.tie = true;
				}
			}
		}
	}
}