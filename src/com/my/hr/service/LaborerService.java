package com.my.hr.service;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;

// Service 는 presentaion Layer (IO) 의 Dependency 다.
public interface LaborerService {
	// 이 프로그램은 업무랄게 딱히 없어서, 메서드 이름이라도 DAO 와 달리해본다.
	// DAO 는 용어를 Oracle 에 가깝게 선택
	// Service 에서는 용어를 업무에 가깝게 선택
	List<Laborer> getLaborers();                             // select -> get 으로 (그냥..변경하는데는 큰 의미 없음)
	void addLaborer(String laborerName, LocalDate hireDate); // insert -> add
	void fixLaborer(Laborer laborer);						 // update -> fix
	void delLaborer(int laborerId);                          // delete -> del 
}
