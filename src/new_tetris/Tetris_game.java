package new_tetris;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class Tetris_game extends JFrame {
	Container c = getContentPane();
	
	Tetris_panel tetris = new Tetris_panel();
	JDialog retry_jd = new JDialog();
	TetrisThread thread;
	
	// ��� ����
	int blocks[][][][] = {
			{ { {0,0,0,0},{1,0,0,0},{1,1,1,0},{0,0,0,0} },{ {0,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0} },{ {1,1,1,0},{0,0,1,0},{0,0,0,0},{0,0,0,0} },{ {0,0,1,0},{0,0,1,0},{0,1,1,0},{0,0,0,0} } },
			{ { {0,0,0,0},{0,0,1,0},{1,1,1,0},{0,0,0,0} },{ {0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0} },{ {0,0,0,0},{1,1,1,0},{1,0,0,0},{0,0,0,0} },{ {0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0} } },
		    { { {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} } },
		    { { {0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0} },{ {0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0} },{ {0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0} },{ {0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0} } },
		    { { {0,0,0,0},{0,1,0,0},{1,1,1,0},{0,0,0,0} },{ {0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0} },{ {0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0} } },
		    { { {0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0} },{ {0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0} },{ {0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0} } },
		    { { {0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0} },{ {0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0} },{ {0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0} },{ {0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0} } } };
	
	// ��Ʈ���� �� ����
	int board[][] = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} };
	
	static int block_size = 20;	// �׷����� ���� ��ü ��� �׸��� ���� ��� ũ��
	int getX[] = new int[4], getY[] = new int[4];	// ��� ��ǥ ����
	boolean wall_limit = false;		// ���� ��� ���θ� �Ǵ��ϱ� ���� ����
	int score = 0, width = 100, height = 0;		// ����, ��� �̵��� ���� ���� �ʺ�� ����
	int deletedLines = 0;	// ������ �� ����
	long startTime = System.currentTimeMillis();		// ���� �ð�
	int random1 = 0, random2 = (int)(Math.random()*7);	// �������� ����� �������� �ϱ� ���� ����
	int rotation = 0;	// ��� ȸ���� �Ǵ��ϱ� ���� ����
	int end = 0;	// ���� ���� ���θ� �Ǵ��ϱ� ���� ����
	
	JLabel score_num = new JLabel();	// ���� ǥ��
	JLabel score_text = new JLabel(new ImageIcon(".\\static\\image\\score.png"));		// "����" ǥ��
	JButton retry = new JButton(new ImageIcon(".\\static\\image\\retryBtn_Image.png"));		// "�ٽ��ϱ�" ǥ��
	JButton gotomain = new JButton(new ImageIcon(".\\static\\image\\gotomainBtn_Image.png"));		// "ó������" ǥ��
	JButton exit = new JButton(new ImageIcon(".\\static\\image\\exitBtn_Image.png"));		// "������" ǥ��
	JLabel retry_label = new JLabel(new ImageIcon(".\\static\\image\\retry_image.jpg"));		// �絵�� ���̾�α� ��� �̹��� �����ϱ� ���� ���̺�
	
	Color color1 = new Color(121, 194, 218);	// �ϴû�
	Color color2 = new Color(158, 158, 158);	// ȸ��
	
	public Tetris_game() {
		setTitle(" �� TETRIS GAME ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		// ��Ʈ���� �г��� ���� ����
		tetris.setSize(700, 560);
		c.add(tetris);
		thread = new TetrisThread();
		
		new Game_music();	// ������� ����
		
		
		// ���� ���̺� ���� ����
		score_num.setFont(new Font("arial", Font.BOLD, 50));
		score_num.setForeground(Color.WHITE);
		
		tetris.add(score_text);
		tetris.add(score_num);
		tetris.add(gotomain);
		
		// �絵�� ���̾�α� â ���� ���� 
		retry_jd.add(retry_label);
		retry_jd.setTitle(" Game Over!!");
		retry_jd.setSize(400, 600);
		retry_label.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 18));
		
		// �ٽ��ϱ� ��ư ���� ����
		retry.setContentAreaFilled(false);
		retry.setBorderPainted(false);
		ImageIcon retry_rolloverIcon = new ImageIcon(".\\static\\image\\retryBtn_Image_mouse.png");
		retry.setRolloverIcon(retry_rolloverIcon);
		retry.setToolTipText("retry!!");
		ToolTipManager retryBtn_tool = ToolTipManager.sharedInstance();
		retryBtn_tool.setInitialDelay(500);
		
		// ó������ ��ư ���� ����
		gotomain.setContentAreaFilled(false);
		gotomain.setBorderPainted(false);
		ImageIcon gotomain_rolloverIcon = new ImageIcon(".\\static\\image\\gotomainBtn_Image_mouse.png");
		gotomain.setRolloverIcon(gotomain_rolloverIcon);
		gotomain.setToolTipText("go to main!!");
		ToolTipManager gotomainBtn_tool = ToolTipManager.sharedInstance();
		gotomainBtn_tool.setInitialDelay(500);
		
		// ������ ��ư ���� ����
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		ImageIcon exit_rolloverIcon = new ImageIcon(".\\static\\image\\exitBtn_Image_mouse.png");
		exit.setRolloverIcon(exit_rolloverIcon);
		exit.setToolTipText("exit!!");
		ToolTipManager exitBtn_tool = ToolTipManager.sharedInstance();
		exitBtn_tool.setInitialDelay(1000);
		
		// �ٽ��ϱ� ��ư�� �׼� �̺�Ʈ ó��
		retry.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				wall_limit = false;
				for(int y=0; y<19; y++) {
					for(int x=1; x<12; x++) {
						board[y][x] = 0;
					}
				}
				score = 0;
				width = 100;
				height = 0;
				retry_jd.setVisible(false);
			    
				tetris.add(score_text);
				tetris.add(score_num);
				tetris.add(gotomain);
				new Game_music();
			}
		});
		// ó������ ��ư�� �׼� �̺�Ʈ ó��
		gotomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Game_intro();
				retry_jd.setVisible(false);
				Game_music.clip.stop();
				thread.interrupt();
			}
		});
		// ������ ��ư�� �׼� �̺�Ʈ ó��
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				System.exit(0);
			}
		});
		
		// Ű���� ����Ű ��� ���� Ű �̺�Ʈ
		tetris.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_UP)			tetris.moveUp();
				else if (keyCode == KeyEvent.VK_DOWN)	tetris.moveDown();
				else if (keyCode == KeyEvent.VK_LEFT)	tetris.moveLeft();
				else if (keyCode == KeyEvent.VK_RIGHT)	tetris.moveRight();
			}
		});
		
		setSize(700, 700);
		setVisible(true);
		setContentPane(tetris);
		tetris.requestFocus(true);
		
		thread.start();
	}
	// ��Ʈ���� ���� �г�
	class Tetris_panel extends JPanel {
		private ImageIcon game_image = new ImageIcon(".\\static\\image\\game_Image.jpg");
		private Image img3 = game_image.getImage();
		
		public void paintComponent(Graphics g) {
			int down_count = 0, check_count = 0;
			tetris.requestFocus(true);
			super.paintComponent(g);
			g.drawImage(img3, 0, 0, getWidth(), getHeight(), this);
			
			score_text.setLocation(320, 100);
			score_num.setLocation(450, 200);
			score_num.setText(Integer.toString(score*100));
			gotomain.setLocation(360, 350);
			
			// ���� ������ ���� �޼ҵ� ȣ��
			landBlock(g);
			blockColor(g);
			convertToWall();
			removeBlock(down_count, check_count, g);
			checkGameOver();
			showNextBlock(g);
			
			if(end == 1) {
				random2 = (int)(Math.random()*7);
				end = 0;
			}
		}
		
		// �̹� ������ ����� ȸ�� ���� �޼ҵ�
		public void landBlock(Graphics g) {	
			g.setColor(color2);
			for(int y=0; y<19; y++) {
				for(int x=1; x<12; x++) {
					if(board[y][x] == 1) {
						g.fill3DRect(x*block_size+block_size, y*block_size+block_size*3, block_size, block_size, true);
					}
				}
			}
		}
		
		// �������� ��� �� ���� �޼ҵ�
		public void blockColor(Graphics g) {
			g.setColor(color1);
		}
		
		// �̹� ������ ����� �� ��ȯ �޼ҵ�
		public void convertToWall() {
			try {
				for(int i=0; i<4; i++) {
					if(board[getY[i]+1][getX[i]] == 1) {
						for (int j=0; j<4; j++) {
							board[getY[j]][getX[j]] = 1;
							width = 100;
							height = 0;
							end = 1;
							rotation = 0;
							random1 = random2;
						}
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		// �������� ��ƴ���� ���� ��� ���� ���� �޼ҵ�
		public void removeBlock(int down_count, int check_count, Graphics g) {
			for (int y=0; y<19; y++) {
				for (int x=1; x<12; x++) {
					if(board[y][x] == 1)	check_count++;
				}
				if(check_count == 11) {
					for (int i=y; i>1; i--) {
						for (int j=1; j<13; j++) {
							board[i][j] = 0;
							board[i][j] = board[i-1][j];
						}
					}
					score++;
					deletedLines++;
				}
				else {
					downBlock(down_count, g);
				}
				check_count = 0;
			}
		}
		
		// ���ӿ��� ���� üũ �޼ҵ�
		public void checkGameOver() {
		    boolean gameOverDetected = false; // ���� ���� ���θ� ��Ÿ���� ����

		    for (int x = 1; x < 12; x++) {
		        if (board[2][x] == 1) {
		            wall_limit = true;
		            gameOverDetected = true; // ���� ���� ��Ȳ ����

		        }
		    }

		    if (gameOverDetected) {
		    	// ���� ���� �ð� ��� �� ǥ��
	            long endTime = System.currentTimeMillis();
	            long elapsedTime = (endTime - startTime) / 1000;

	            // JPanel�� �����ϰ� �� ���̺��� �߰�
	            JPanel labelPanel = new JPanel();
	            labelPanel.setLayout(new FlowLayout()); // ���� ������ ���� FlowLayout ���
	            labelPanel.setBackground(Color.WHITE); // ���� ����

	            JLabel timeLabel = new JLabel(elapsedTime + " seconds, ");
	            timeLabel.setFont(new Font("���� ���", Font.BOLD, 25));
	            timeLabel.setForeground(Color.BLACK);

	            JLabel deletedLinesLabel = new JLabel(deletedLines + " Lines!");
	            deletedLinesLabel.setFont(new Font("���� ���", Font.BOLD, 25));
	            deletedLinesLabel.setForeground(Color.BLACK);

	            // JPanel�� �� ���̺� �߰�
	            labelPanel.add(timeLabel);
	            labelPanel.add(deletedLinesLabel);

		        retry_label.add(score_text);
		        retry_label.add(score_num);
		        retry_label.add(retry);
		        retry_label.add(gotomain);
		        retry_label.add(exit);
	            retry_label.add(labelPanel);
		        retry_jd.setVisible(true);
		        Game_music.clip.stop();
		    }
		}
		
		// ���� ���� ��� �̸����� �޼ҵ�
		public void showNextBlock(Graphics g) {
			for (int k=0; k<4; k++) {
				for (int l=0; l<4; l++) {
					if(blocks[random2][0][k][l] == 1) {
						g.fill3DRect(l*block_size+120, k*block_size, block_size, block_size, true);
					}
				}
			}
		}
		
		// ����� ȸ�� ���� ���� �Ǵ� & ȸ�� �޼ҵ�
		public void checkRotation() {
			int count = 0;
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					int rotation2 = (rotation%4) + 1;
					if (rotation2 == 4)	rotation2 = 0;
					if (blocks[random1][rotation2][i][j] == 1) {
						getX[count] = ((j*block_size)+width)/block_size;
						getY[count] = ((i*block_size)+height)/block_size;
						count++;
					}
				}
			}
			int check = 0;
			int empty = 0;
			int mistake = 0;
			
			// ���� ���� ��� ��� �Ǵ�
			if(board[getY[0]][getX[0]] == 1 || (random1==6 && board[getY[2]][getX[2]] == 1) || (random1 == 1 && board[getY[1]][getX[1]] == 1)) {
				check = 1;
				mistake++;
				if(random1 == 3) {
					for (int i=1; i<5; i++) {
						if(board[getY[0]][getX[0]+i] == 0)	empty++;
					}
					if(empty<4) check = 2;
				}
				else {
					for (int i=1; i<4; i++) {
						if(board[getY[0]][getX[0]+i] == 0)	empty++;
					}
					if(empty<3)	check = 2;
				}
			}
			// ������ ���� ��� ��� �Ǵ�
			else if(board[getY[2]][getX[2]] == 1) {
				mistake++;
				check = 2;
				for(int i=1; i<5; i++) {
					if(board[getY[2]][getX[2]-i] == 0)	empty++;
				}
				if(empty<4)	check = 4;
			}
			else if(board[getY[3]][getX[3]] == 1) {
				mistake++;
				check = 3;
				for(int i=0; i<5; i++) {
					if(board[getY[3]][getX[3]-i] == 0)	empty++;
				}
				if(empty<4)	check = 4;
			}
			
			// ��� ȸ��
			switch(check) {
			case 1: case 4:
				width += block_size;
				rotation++;
				rotation %= 4;
				break;
			case 2: case 3:
				width -= block_size;
				rotation++;
				rotation %= 4;
				break;
			default:
				rotation++;
				rotation %= 4;
			}
			
		}
		
		// ��� �������� �ϴ� �޼ҵ�
		public void downBlock(int down_count, Graphics g) {
			for (int k=0; k<4; k++) {
				for (int l=0; l<4; l++) {
					if(blocks[random1][rotation][k][l] == 1) {
						getX[down_count] = ((l*block_size)+width) / block_size;
						getY[down_count] = ((k*block_size)+height) / block_size;
						g.fill3DRect(getX[down_count]*block_size+block_size, getY[down_count]*block_size+block_size*3, block_size, block_size, true);
						down_count++;
					}
				}
			}
		}
		
		// ������ �� �浹 �Ǵ� �޼ҵ�
		public boolean controlRight() {
			for(int i=0; i<4; i++) {
				if(board[getY[i]][getX[i]+1] == 1)	return true;
			}
			return false;
		}
		// ���� �� �浹 �Ǵ� �޼ҵ�
		public boolean controlLeft() {
			for(int i=0; i<4; i++) {
				if(board[getY[i]][getX[i]-1] == 1)	return true;
			}
			return false;
		}
		
		// ��� �̵� �޼ҵ�
		void moveUp() {
			checkRotation();
			if (wall_limit == false)	repaint();
		}
		void moveDown() {
			if (wall_limit == false) {
				height += block_size;
				tetris.repaint();
			}
		}
		void moveLeft() {
			if (wall_limit == false && controlLeft() == false) {
				width -= block_size;
				tetris.repaint();
			}
		}
		void moveRight() {
			if (wall_limit == false && controlRight() == false) {
				width += block_size;
				tetris.repaint();
			}
		}
	}
	
	// ����� ���������� ���������� �ϴ� ������
	class TetrisThread extends Thread {
		Tetris_panel tetris = new Tetris_panel();
		public void run() {
			while(true) {
				try {
					sleep(300);		// 0.3�ʸ���
					tetris.moveDown();
				}
				catch(InterruptedException e){
					return;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		new Tetris_game();
	}
}