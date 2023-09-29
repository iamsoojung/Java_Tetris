package new_tetris;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Game_intro extends JFrame {
	private IntroPanel intro = new IntroPanel();
	private RulePanel rule = new RulePanel();
	public Game_intro() {
		setTitle(" �� TETRIS GAME ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		new Intro_music();
		
		// Game start ��ư & Game rule ��ư ����
		JButton startBtn = new JButton(new ImageIcon(".\\static\\image\\startBtn_Image.png"));
		JButton ruleBtn = new JButton(new ImageIcon(".\\static\\image\\ruleBtn_Image.png"));
		
		// Game start ��ư�� ���� ����
		startBtn.setLocation(80, 220);
		startBtn.setSize(140, 84);
		startBtn.setContentAreaFilled(false);
		startBtn.setBorderPainted(false);
		ImageIcon startBtn_rolloverIcon = new ImageIcon(".\\static\\image\\startBtn_Image_mouse.png");
		startBtn.setRolloverIcon(startBtn_rolloverIcon);	// ���콺 Ŀ�� ���� �, ��ư�� �ٸ� �̹����� ����
		startBtn.setToolTipText("���� ����!");		 // ��ư ���� ����
		ToolTipManager startBtn_tool = ToolTipManager.sharedInstance();
		startBtn_tool.setInitialDelay(500);
		
		// Game rule ��ư�� ���� ����
		ruleBtn.setLocation(460, 220);
		ruleBtn.setSize(140, 84);
		ruleBtn.setContentAreaFilled(false);
		ruleBtn.setBorderPainted(false);
		ruleBtn.setBorderPainted(false);
		ImageIcon ruleBtn_rolloverIcon = new ImageIcon(".\\static\\image\\ruleBtn_Image_mouse.png");
		ruleBtn.setRolloverIcon(ruleBtn_rolloverIcon);
		ruleBtn.setToolTipText("���� ��Ģ!");
		ToolTipManager ruleBtn_tool = ToolTipManager.sharedInstance();
		ruleBtn_tool.setInitialDelay(500);
		
		intro.add(startBtn);
		intro.add(ruleBtn);
		
		// ���� ���� ���̾�α�
		JDialog JD = new JDialog();
		JD.setTitle(" �� HOW TO TETRIS ��");
	    JD.setSize(600, 720);
	    JD.setLayout(null);
	    JD.setContentPane(rule);
		
	    // Game start ��ư & Game rule ��ư�� �׼� �̺�Ʈ ó��
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tetris_game();
				setVisible(false);
				Intro_music.clip.stop();
			}
		});
		ruleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JD.setVisible(true);
			}
		});
		
		setContentPane(intro);
		intro.setLayout(null);
		setSize(700, 560);
		setVisible(true);
	}
	// ���� ���� ȭ�� �̹��� ������ ���� �г�
	class IntroPanel extends JPanel {
		private ImageIcon intro_image = new ImageIcon(".\\static\\image\\intro_Image.jpg");
		private Image img1 = intro_image.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
		}
	}
	// ���� �� ���� ȭ�� �̹��� ������ ���� �г�
	class RulePanel extends JPanel {
		private ImageIcon rule_image = new ImageIcon(".\\static\\image\\rule_Image.jpg");
		private Image img2 = rule_image.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);
		}
	}
	public static void main(String[] args) {
		new Game_intro();
	}
}