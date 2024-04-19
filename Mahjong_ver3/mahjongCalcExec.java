package Mahjong_ver3;

import java.util.Arrays;
import java.util.HashMap;

public class mahjongCalcExec {
	public static void main(String[] args) {
		mahjongCalc myMethod = new mahjongCalc();
		int user1 = myMethod.userScoreInput("user1の素点を入力してください。※数字のみ入れてください。　例：25000");
		int user2 = myMethod.userScoreInput("user2の素点を入力してください。※数字のみ入れてください。　例：25000");
		int user3 = myMethod.userScoreInput("user3の素点を入力してください。※数字のみ入れてください。　例：25000");
		int user4 = (100000 - (user1 + user2 + user3)); 
		int[] scores = {user1 , user2 , user3 , user4};
		HashMap<Integer,String> userValue = new HashMap<Integer,String>();
		userValue.put(user1, "user1");
		userValue.put(user2, "user2");
		userValue.put(user3, "user3");
		userValue.put(user4, "user4");
		Arrays.sort(scores);
		HashMap<String,Integer> userRank = new HashMap<String,Integer>();
		userRank.put(userValue.get(scores[0]),4);
		userRank.put(userValue.get(scores[1]),3);
		userRank.put(userValue.get(scores[2]),2);
		userRank.put(userValue.get(scores[3]),1);
		System.out.println("user1の素点は" + user1 + "で順位は" +userRank.get("user1") + "位です");
		System.out.println("user2の素点は" + user2 + "で順位は" +userRank.get("user2") + "位です");
		System.out.println("user3の素点は" + user3 + "で順位は" +userRank.get("user3") + "位です");
		System.out.println("user4の素点は" + user4 + "で順位は" +userRank.get("user4") + "位です");
		int oka = myMethod.userUmaOkaInput("対局終了時に返す点数を入力してください。メジャーなのは30000です。※数字のみ入れてください。　例：30000");
		System.out.println("素点からオカ分" + oka + "点だけ差し引きます");
		System.out.println("差し引いた分の点数は以下です。");
		System.out.println("user1：" + (user1 - oka ));
		System.out.println("user2：" + (user2 - oka ));
		System.out.println("user3：" + (user3 - oka ));
		System.out.println("user4：" + (user4 - oka ));
		System.out.println("1位に" + ((oka - 25000) * 4) + "点足します。");
		System.out.println("オカ適用後の数値は以下です。");
		System.out.println("1位:" + ((scores[3] - oka ) + ((oka - 25000) * 4)) + "点 :" + userValue.get(scores[3]));
		System.out.println("2位:" + (scores[2] - oka ) + "点 :" + userValue.get(scores[2]));
		System.out.println("3位：" + (scores[1] - oka ) + "点 :" + userValue.get(scores[1]));
		System.out.println("4位：" + (scores[0] - oka ) + "点 :" + userValue.get(scores[0]));
		int uma14 = myMethod.userUmaOkaInput("4位が1位に渡す点数を入力してください。※数字のみ入れてください。　例：30000");
		int uma23 = myMethod.userUmaOkaInput("3位が2位に渡す点数を入力してください。※数字のみ入れてください。　例：10000");
		System.out.println("ウマ適用後の数値は以下です。");
		System.out.println("1位:" + (((scores[3] - oka ) + ((oka - 25000) * 4)) + uma14 ) + "点 :" + userValue.get(scores[3]));
		System.out.println("2位:" + ((scores[2] - oka ) + uma23 ) + "点 :" + userValue.get(scores[2]));
		System.out.println("3位：" + ((scores[1] - oka ) - uma23 ) + "点 :" + userValue.get(scores[1]));
		System.out.println("4位：" + ((scores[0] - oka ) - uma14 ) + "点 :" + userValue.get(scores[0]));
		myMethod.closeScanner();
		
	}
}