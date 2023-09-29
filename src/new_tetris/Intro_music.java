package new_tetris;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

// 게임 시작화면의 배경 음악 설정 클래스
public class Intro_music extends JFrame {
	public static Clip clip;
	public Intro_music() {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(".\\static\\audio\\game_intro_BGM.wav"));
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	}
}
