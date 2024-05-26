package com.my.hr.domain;

// Java 에서 Exception(예외)는 예외 상황을 처리하기 위한 매커니즘 
// exception 의 타입은 2가지를 쓸 수 있다. (1)exception (2) RuntimeException 
// 1) Exception(CheckException)
public class NoneException extends RuntimeException {
	public NoneException(String msg) {
		super(msg);
	}
}
