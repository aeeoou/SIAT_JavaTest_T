package com.my.hr;

import java.util.ArrayList;
import java.util.List;

import com.my.hr.dao.LaborerDao;
import com.my.hr.dao.LaborerDaoImpl;
import com.my.hr.domain.Laborer;
import com.my.hr.io.Console;
import com.my.hr.io.LaborerIo;
import com.my.hr.service.LaborerService;
import com.my.hr.service.LaborerServiceImpl;

public class Main {
	public static void main(String[] args) {
		List<Laborer> laborers = new ArrayList<>();
		
		// 관계 맺어주기
		LaborerDao laborerDao = new LaborerDaoImpl(laborers);
		LaborerService laborerService = new LaborerServiceImpl(laborerDao);
		LaborerIo laborerIo = new LaborerIo(laborerService);
		
		laborerIo.play();
		Console.info("끝");
	}
}
