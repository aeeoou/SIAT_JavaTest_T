package com.my.hr.dao;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;

// Persitence Layer (dao) = 데이터를 다루는 객체들을 모아둔 영역
// dao 는 service layer 의 dependecy
public interface LaborerDao {
	// dao 객체는 oracle과 대화한다. (oracle 에 가깝게 코딩한다.)
	List<Laborer> selectLaborers();
	void insertLaborer(String laborerName, LocalDate hireDate);
	void updateLaborer(Laborer laborer);
	void deleteLaborer(int laborerId);
}