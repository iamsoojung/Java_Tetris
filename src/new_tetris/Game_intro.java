package new_tetris;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Game_intro extends JFrame {
	private IntroPanel intro = new IntroPanel();
	private RulePanel rule = new RulePanel();
	public Game_intro() {
		setTitle(" ★ TETRIS GAME ★");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		new Intro_music();
		
		// Game start 버튼 & Game rule 버튼 생성
		JButton startBtn = new JButton(new ImageIcon(".\\static\\image\\startBtn_Image.png"));
		JButton ruleBtn = new JButton(new ImageIcon(".\\static\\image\\ruleBtn_Image.png"));
		
		// Game start 버튼의 세부 설정
		startBtn.setLocation(80, 220);
		startBtn.setSize(140, 84);
		startBtn.setContentAreaFilled(false);
		startBtn.setBorderPainted(false);
		ImageIcon startBtn_rolloverIcon = new ImageIcon(".\\static\\image\\startBtn_Image_mouse.png");
		startBtn.setRolloverIcon(startBtn_rolloverIcon);	// 마우스 커서 갖다 댈때, 버튼을 다른 이미지로 설정
		startBtn.setToolTipText("게임 시작!");		 // 버튼 툴팁 생성
		ToolTipManager startBtn_tool = ToolTipManager.sharedInstance();
		startBtn_tool.setInitialDelay(500);
		
		// Game rule 버튼의 세부 설정
		ruleBtn.setLocation(460, 220);
		ruleBtn.setSize(140, 84);
		ruleBtn.setContentAreaFilled(false);
		ruleBtn.setBorderPainted(false);
		ruleBtn.setBorderPainted(false);
		ImageIcon ruleBtn_rolloverIcon = new ImageIcon(".\\static\\image\\ruleBtn_Image_mouse.png");
		ruleBtn.setRolloverIcon(ruleBtn_rolloverIcon);
		ruleBtn.setToolTipText("게임 규칙!");
		ToolTipManager ruleBtn_tool = ToolTipManager.sharedInstance();
		ruleBtn_tool.setInitialDelay(500);
		
		intro.add(startBtn);
		intro.add(ruleBtn);
		
		// 게임 설명 다이얼로그
		JDialog JD = new JDialog();
		JD.setTitle(" ☆ HOW TO TETRIS ☆");
	    JD.setSize(600, 720);
	    JD.setLayout(null);
	    JD.setContentPane(rule);
		
	    // Game start 버튼 & Game rule 버튼의 액션 이벤트 처리
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
	// 게임 시작 화면 이미지 설정을 위한 패널
	class IntroPanel extends JPanel {
		private ImageIcon intro_image = new ImageIcon(".\\static\\image\\intro_Image.jpg");
		private Image img1 = intro_image.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
		}
	}
	// 게임 룰 설명 화면 이미지 설정을 위한 패널
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