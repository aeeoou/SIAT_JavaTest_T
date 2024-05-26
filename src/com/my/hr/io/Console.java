package com.my.hr.io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public interface Console {
	Scanner sc = new Scanner(System.in);
	
	// 알림 출력
	static void info(Object obj) {	// 파라미터 값으로 Object를 받음으로써 어떤 객체든 받아서 출력
		System.out.println(obj);
	}
	
	// Console은 유틸리티이기 때문에 Laborer 에 관련된 내용이 들어가면 안된다.
	
	// 에러메세지 출력
	static void err(String msg) {
		System.out.println("ERROR] " + msg);
	}
	
	// 입력 안내 메세지 출력
	// 외부에서 호출하지 않고 내부에서만 사용
	private static void inMsg(String msg) {
		System.out.println(msg + "\n> ");
	}
	
	// 문자 입력받는 메서드
	static String inStr(String msg, int len) {	// 두번째 파라미터(len)는 문자의 길이 수
		String input = "";                      // 입력 값을 저장할 변수
		boolean isGood = false;                 // 플래그
		
		// isGood 값이 true 가 나오면 멈춘다. (참된 데이터가 입력되었으면 do-while문을 멈춘다.)
		do {
			Console.inMsg(msg);		          	// 입력 안내 메세지를 띄운다.
			input = sc.nextLine();				// 한 줄을 입력 받는다.
			isGood = input.matches("[a-zA-Z가-힣0-9]{1," + len + "}"); // 입력 받은 것이 문자인지 확인한다. (글자가 한글자 이상 len 글자 이하)
			// 플래그 값이 false 면 에러 메세지 출력
			if(!isGood) Console.err(len + "자 이하의 문자가 아닙니다.");
		}while(!isGood);
		
		return input;							// 입력 값을 리턴한다.
	}
	
	// 숫자 입력받는 메서드
	static int inNum(String msg) {
		String input = "";
		boolean isGood = false;
		
		do {
			Console.inMsg(msg);		          	// 입력 안내 메세지를 띄운다.
			input = sc.nextLine();				// 한 줄을 입력 받는다.
			isGood = input.matches("[0-9]+"); // 입력 받은 것이 숫자인지 확인한다. (글자가 한글자 이상 len 글자 이하)
			if(!isGood) Console.err("0 이상의 정수가 아닙니다.");	// 입력데이터가 거짓이면 에러메세지 출력
		}while(!isGood);
		
		// 입력이 완료된 값을 숫자로 바꿔서 리턴 (왜냐 값을 문자로 받고 있어서..)
		return Integer.parseInt(input);
	}
	
	// 날짜 입력받는 메서드
	static LocalDate inDate(String msg) {
		String input = "";
		LocalDate date = null;
		
		do {
			Console.inMsg(msg);
			input = sc.nextLine().trim();	// trim() 문자열의 앞과 뒤에 있는 공백을 모두 제거
			
			if(input.length() > 0) {
				try {
					// 날짜 타입으로 바꾸는 시도
					date = LocalDate.parse(input, DateTimeFormatter.ISO_DATE);	// 날짜 형식에 맞지 않으면 parse가 실패한다.
				// exception(예외)가 발생하면 데이터 변수가 안채워지고 null 값이 된다. (null 값이 되면 거짓이다.)
				} catch(DateTimeParseException e) {}
			}
			// 에러메세지 출력
			if(date == null) Console.err("YYYY-MM-DD 형식의 실제 날짜가 아닙니다.");
		}while(date == null);
		
		// 확인된 날짜를 반환
		return date;
	}
}
