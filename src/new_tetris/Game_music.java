package new_tetris;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

// ���� ����ȭ�� ��� ���� ���� Ŭ����
public class Game_music extends JFrame {
	public static Clip clip;
	public Game_music() {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(".\\static\\audio\\tetris_game_BGM.wav"));
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	}
}
