package com.ar.lighthouse.member.function;

import java.util.Random;

public class ranNumber {
	//랜덤 문자 생성 메소드
	public String randomNumber() {
		Random random = new Random();
		int createNum = 0; // 1자리 난수
		String ranNum = "";// 1자리 난수 String
		int letter = 6;		//자릿수
		String resultNum = ""; //결과
		
		for(int i=0; i<letter; i++) {
			createNum = random.nextInt(9);
			ranNum = Integer.toString(createNum);
			resultNum += ranNum;
		}
		
		return resultNum;
	}
}
