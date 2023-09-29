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
	
	// 블록 생성
	int blocks[][][][] = {
			{ { {0,0,0,0},{1,0,0,0},{1,1,1,0},{0,0,0,0} },{ {0,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0} },{ {1,1,1,0},{0,0,1,0},{0,0,0,0},{0,0,0,0} },{ {0,0,1,0},{0,0,1,0},{0,1,1,0},{0,0,0,0} } },
			{ { {0,0,0,0},{0,0,1,0},{1,1,1,0},{0,0,0,0} },{ {0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0} },{ {0,0,0,0},{1,1,1,0},{1,0,0,0},{0,0,0,0} },{ {0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0} } },
		    { { {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{1,1,0,0},{0,0,0,0} } },
		    { { {0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0} },{ {0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0} },{ {0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0} },{ {0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0} } },
		    { { {0,0,0,0},{0,1,0,0},{1,1,1,0},{0,0,0,0} },{ {0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0} },{ {0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0} } },
		    { { {0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0} },{ {0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0} },{ {0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0} },{ {0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0} } },
		    { { {0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0} },{ {0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0} },{ {0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0} },{ {0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0} } } };
	
	// 테트리스 판 생성
	int board[][] = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} };
	
	static int block_size = 20;	// 그래픽을 통해 입체 블록 그리기 위한 블록 크기
	int getX[] = new int[4], getY[] = new int[4];	// 블록 좌표 저장
	boolean wall_limit = false;		// 벽에 닿는 여부를 판단하기 위한 변수
	int score = 0, width = 100, height = 0;		// 점수, 블록 이동을 위한 보드 너비와 높이
	int deletedLines = 0;	// 삭제된 줄 개수
	long startTime = System.currentTimeMillis();		// 시작 시간
	int random1 = 0, random2 = (int)(Math.random()*7);	// 랜덤으로 블록이 나오도록 하기 위한 변수
	int rotation = 0;	// 블록 회전을 판단하기 위한 변수
	int end = 0;	// 게임 종료 여부를 판단하기 위한 변수
	
	JLabel score_num = new JLabel();	// 점수 표시
	JLabel score_text = new JLabel(new ImageIcon(".\\static\\image\\score.png"));		// "점수" 표시
	JButton retry = new JButton(new ImageIcon(".\\static\\image\\retryBtn_Image.png"));		// "다시하기" 표시
	JButton gotomain = new JButton(new ImageIcon(".\\static\\image\\gotomainBtn_Image.png"));		// "처음으로" 표시
	JButton exit = new JButton(new ImageIcon(".\\static\\image\\exitBtn_Image.png"));		// "끝내기" 표시
	JLabel retry_label = new JLabel(new ImageIcon(".\\static\\image\\retry_image.jpg"));		// 재도전 다이얼로그 배경 이미지 설정하기 위한 레이블
	
	Color color1 = new Color(121, 194, 218);	// 하늘색
	Color color2 = new Color(158, 158, 158);	// 회색
	
	public Tetris_game() {
		setTitle(" ★ TETRIS GAME ★");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		// 테트리스 패널의 세부 설정
		tetris.setSize(700, 560);
		c.add(tetris);
		thread = new TetrisThread();
		
		new Game_music();	// 배경음악 시작
		
		
		// 점수 레이블 세부 설정
		score_num.setFont(new Font("arial", Font.BOLD, 50));
		score_num.setForeground(Color.WHITE);
		
		tetris.add(score_text);
		tetris.add(score_num);
		tetris.add(gotomain);
		
		// 재도전 다이얼로그 창 세부 설정 
		retry_jd.add(retry_label);
		retry_jd.setTitle(" Game Over!!");
		retry_jd.setSize(400, 600);
		retry_label.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 18));
		
		// 다시하기 버튼 세부 설정
		retry.setContentAreaFilled(false);
		retry.setBorderPainted(false);
		ImageIcon retry_rolloverIcon = new ImageIcon(".\\static\\image\\retryBtn_Image_mouse.png");
		retry.setRolloverIcon(retry_rolloverIcon);
		retry.setToolTipText("retry!!");
		ToolTipManager retryBtn_tool = ToolTipManager.sharedInstance();
		retryBtn_tool.setInitialDelay(500);
		
		// 처음으로 버튼 세부 설정
		gotomain.setContentAreaFilled(false);
		gotomain.setBorderPainted(false);
		ImageIcon gotomain_rolloverIcon = new ImageIcon(".\\static\\image\\gotomainBtn_Image_mouse.png");
		gotomain.setRolloverIcon(gotomain_rolloverIcon);
		gotomain.setToolTipText("go to main!!");
		ToolTipManager gotomainBtn_tool = ToolTipManager.sharedInstance();
		gotomainBtn_tool.setInitialDelay(500);
		
		// 끝내기 버튼 세부 설정
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		ImageIcon exit_rolloverIcon = new ImageIcon(".\\static\\image\\exitBtn_Image_mouse.png");
		exit.setRolloverIcon(exit_rolloverIcon);
		exit.setToolTipText("exit!!");
		ToolTipManager exitBtn_tool = ToolTipManager.sharedInstance();
		exitBtn_tool.setInitialDelay(1000);
		
		// 다시하기 버튼의 액션 이벤트 처리
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
		// 처음으로 버튼의 액션 이벤트 처리
		gotomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Game_intro();
				retry_jd.setVisible(false);
				Game_music.clip.stop();
				thread.interrupt();
			}
		});
		// 끝내기 버튼의 액션 이벤트 처리
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				System.exit(0);
			}
		});
		
		// 키보드 방향키 제어를 위한 키 이벤트
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
	// 테트리스 게임 패널
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
			
			// 게임 실행을 위한 메소드 호출
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
		
		// 이미 내려온 블록의 회색 착지 메소드
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
		
		// 내려오는 블록 색 지정 메소드
		public void blockColor(Graphics g) {
			g.setColor(color1);
		}
		
		// 이미 내려온 블록의 벽 변환 메소드
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
		
		// 수평으로 빈틈없이 쌓인 블록 행의 제거 메소드
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
		
		// 게임오버 여부 체크 메소드
		public void checkGameOver() {
		    boolean gameOverDetected = false; // 게임 오버 여부를 나타내는 변수

		    for (int x = 1; x < 12; x++) {
		        if (board[2][x] == 1) {
		            wall_limit = true;
		            gameOverDetected = true; // 게임 오버 상황 감지

		        }
		    }

		    if (gameOverDetected) {
		    	// 게임 종료 시간 계산 및 표시
	            long endTime = System.currentTimeMillis();
	            long elapsedTime = (endTime - startTime) / 1000;

	            // JPanel을 생성하고 두 레이블을 추가
	            JPanel labelPanel = new JPanel();
	            labelPanel.setLayout(new FlowLayout()); // 가로 정렬을 위해 FlowLayout 사용
	            labelPanel.setBackground(Color.WHITE); // 배경색 설정

	            JLabel timeLabel = new JLabel(elapsedTime + " seconds, ");
	            timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	            timeLabel.setForeground(Color.BLACK);

	            JLabel deletedLinesLabel = new JLabel(deletedLines + " Lines!");
	            deletedLinesLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	            deletedLinesLabel.setForeground(Color.BLACK);

	            // JPanel에 두 레이블 추가
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
		
		// 다음 나올 블록 미리보기 메소드
		public void showNextBlock(Graphics g) {
			for (int k=0; k<4; k++) {
				for (int l=0; l<4; l++) {
					if(blocks[random2][0][k][l] == 1) {
						g.fill3DRect(l*block_size+120, k*block_size, block_size, block_size, true);
					}
				}
			}
		}
		
		// 블록의 회전 가능 여부 판단 & 회전 메소드
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
			
			// 왼쪽 벽에 닿는 경우 판단
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
			// 오른쪽 벽에 닿는 경우 판단
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
			
			// 블록 회전
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
		
		// 블록 내려오게 하는 메소드
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
		
		// 오른쪽 벽 충돌 판단 메소드
		public boolean controlRight() {
			for(int i=0; i<4; i++) {
				if(board[getY[i]][getX[i]+1] == 1)	return true;
			}
			return false;
		}
		// 왼쪽 벽 충돌 판단 메소드
		public boolean controlLeft() {
			for(int i=0; i<4; i++) {
				if(board[getY[i]][getX[i]-1] == 1)	return true;
			}
			return false;
		}
		
		// 블록 이동 메소드
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
	
	// 블록이 유동적으로 떨어지도록 하는 스레드
	class TetrisThread extends Thread {
		Tetris_panel tetris = new Tetris_panel();
		public void run() {
			while(true) {
				try {
					sleep(300);		// 0.3초마다
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