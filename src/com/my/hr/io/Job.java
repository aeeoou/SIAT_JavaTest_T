package com.my.hr.io;

public enum Job {
	EXIT("종료"), LIST("목록"), ADD("추가"), FIX("수정"), DEL("삭제");
	
	// 필드
	// 각 상수에 대한 레이블(label)을 저장
	private String label;
	
	// 생성자
	// Job(String label) 생성자는 각 상수를 초기화할 때 사용되며, 레이블을 설정
	private Job(String label) {
		this.label = label;
	}
	
	// EXIT(0), LIST(1), ADD(2), FIX(3), DEL(4) 의 갯수를 얻어낼 수 있는 메서드 length 생성
	// values() 메서드를 사용하여 열거형의 모든 상수를 배열로 반환하고, 배열의 길이를 반환한다. (역할 = 열거형 상수의 개수를 반환한다.)
	public static int length() {
		return values().length;
	}
	
	// 자동으로 할당된 숫자를 메뉴 번호로 쓸 것이기 때문에 숫자 값을 각각의 코드로 변환
	// values() 메서드를 사용하여 열거형의 모든 상수를 배열로 반환하고, 배열의 인덱스를 사용하여 특정 상수를 반환한다. (역할 = 주어진 정수 값[ordinal]을 사용하여 해당 열거형 상수를 반환)
	public static Job valueOf(int ordinal) {
		return values()[ordinal];
	}
	
	// 메뉴를 얻어내는 메서드 (labels 메서드)
	// 모든 열거형 상수의 레이블을 가져와 문자열로 반환, EXIT 레이블은 마지막에 추가 (역할 = 열거형 상수의 레이블을 출력하기 위한 문자열 생성)
	// 
	public static String labels() {
		Job[] jobs = values();
		String labels = "";
		String last = "";
		
		for(Job job: jobs) {
			if(job.ordinal() == 0) last = job.ordinal() + "." + job.label;  // EXIT 메뉴를 맨 끝으로 보낸다.
			else labels += job.ordinal() + "." + job.label + ", ";
		}
		labels += last;
	
		return labels;
	}
}

/*
 * Job 이라는 열겨형(enum)을 정의하여, 다양한 작업(Job)에 대한 상수를 관리하고, 각 상수에 레이블(label)을 할당하는 기능 제공
 * 또한, 열거형의 기능을 확장하여 몇 가지 유틸리티 메서드를 포함하고 있다. 
 */
 
