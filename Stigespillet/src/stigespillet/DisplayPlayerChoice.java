package stigespillet;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.*;

public class DisplayPlayerChoice {

	private JFrame frame;
	
	private String title;
	private int width, height;
	private JTextField player1 = new JTextField("p1");
	private JTextField player2 = new JTextField("p2");
	private JTextField player3 = new JTextField("p3");
	private JTextField player4 = new JTextField("p4");
	private JLabel layb1 = new JLabel("Player 1: ");
	private JLabel layb2 = new JLabel("Player 2: ");
	private JLabel layb3 = new JLabel("Player 3: ");
	private JLabel layb4 = new JLabel("Player 4: ");
	private JLabel sm1 = new JLabel("Choose a smiley: ");
	private JLabel sm2 = new JLabel("Choose a smiley: ");
	private JLabel sm3 = new JLabel("Choose a smiley: ");
	private JLabel sm4 = new JLabel("Choose a smiley: ");
	private JTextField sml1 = new JTextField("s1");
	private JTextField sml2 = new JTextField("s2");
	private JTextField sml3 = new JTextField("s3");
	private JTextField sml4 = new JTextField("s4");
	private JLabel smile1 = new JLabel("1." + new ImageIcon(Assets.like));
	private JLabel smile2 = new JLabel("2." + new ImageIcon(Assets.heart));
	private JLabel smile3 = new JLabel("3." + new ImageIcon(Assets.laugh));
	private JLabel smile4 = new JLabel("4." + new ImageIcon(Assets.smile));
	private JLabel smile5 = new JLabel("5." + new ImageIcon(Assets.oops));
	private JLabel smile6 = new JLabel("6." + new ImageIcon(Assets.tear));
	private JLabel smile7 = new JLabel("7." + new ImageIcon(Assets.angry));
	private JLabel smile8 = new JLabel("8." + new ImageIcon(Assets.grin));
	private JLabel smile9 = new JLabel("9." + new ImageIcon(Assets.wink));
	private JLabel smile10 = new JLabel("10." + new ImageIcon(Assets.loveeyes));
	private JButton ready = new JButton("Ready");
	
	
	
	public DisplayPlayerChoice(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		
		
		
		createDisplay();
	}
	
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setBounds(10,10,1200,625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(6,5));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
		frame.add(player1);
		frame.add(player2);
		frame.add(player3);
		frame.add(player4);
	}
	
	public void setVisibility(boolean bla){
		frame.setVisible(bla);
	}
	
	public JFrame getFrame(){ 
		return frame;
	}
}