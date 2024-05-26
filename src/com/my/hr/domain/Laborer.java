package com.my.hr.domain;

import java.time.LocalDate;

// 레코드 클래스는 옆에 필드를 나열해야한다.
public record Laborer(int laborerId, String laborerName, LocalDate hireDate) {
	@Override
	// 노동자 목록을 출력할 때 사용한다.
	public String toString() {
		return String.format("%2d %-5s %s", laborerId, laborerName, hireDate);
	}
}
