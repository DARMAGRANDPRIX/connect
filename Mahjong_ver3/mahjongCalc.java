package Mahjong_ver3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class mahjongCalc {
	Scanner userinput = new Scanner(System.in);
	int input = 0;
	public int userScoreInput (String text) {
		boolean value = false;
		while(!value) {
			try {
				System.out.println(text);
				input = userinput.nextInt();
				value = true;
			} catch (InputMismatchException e) {
				System.out.println("無効な入力です。もう一度入力してください");
				userinput.next();
			}
		}
		return input;
	}
	public int userUmaOkaInput (String text) {
		System.out.println(text);
		return userinput.nextInt();
	}
	public void closeScanner() {
		userinput.close();
	}
}
	
