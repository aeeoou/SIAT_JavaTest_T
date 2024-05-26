package com.my.hr.io;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;
import com.my.hr.domain.NoneException;
import com.my.hr.service.LaborerService;

// Presentation Layer 에서 LaborerIo 에는 노동자 업무에 직접 관련된 것은 io 에 구현
// Console은 유틸리티이기 때문에 Laborer 에 관련된 내용이 들어가면 안된다.

// IO 의 dependency 는 Service 다.
public class LaborerIo {
	private LaborerService laborerService;
	// 멤버변수
	private String menu;
	
	// 생성자
	public LaborerIo(LaborerService laborerService) {
		this.laborerService = laborerService;
		this.menu = Job.labels();
	}
	
	// IO 의 시작 역할을 하는 메서드
	public void play() {
		Job job = null;
		
		// 화면에 메뉴를 반복적으로 출력
		while((job = choose()) != Job.EXIT) {
			switch(job) {
			// ENUM 을 사용함으로써 코드의 가독성이 좋아졌다.
			case LIST -> listLaborers();
			case ADD -> addLaborers();
			case FIX -> fixLaborers();
			case DEL -> delLaborers();
			}
		}
	}
	
	private Job choose() {
		int choice = 0;
		boolean isGood = false;
		
		do {
			choice = Console.inNum(menu);	// 숫자값을 입력받는다.
			if(choice < 0 || choice > Job.length() - 1)		// 숫자값이 음수이거나 존재하지 않은 메뉴번호를 입력하면 거짓
				Console.err("메뉴 번호를 입력해주세요.");
			else isGood = true;
		} while(!isGood);
		
		return Job.valueOf(choice);
	}
	
	private void listLaborers() {
		List<Laborer> laborers = laborerService.getLaborers();
		
		System.out.println("ID 이름        입사일");
		System.out.println("******************");
		
		if(!laborers.isEmpty()) laborers.forEach(Console::info);
		else Console.info("등록된 노동자가 없습니다.");
	}
	
	private void addLaborer() {
		String laborerName = Console.inStr("노동자명을 입력하세요.", 5);
		
		if(!laborerName.equals("0")) {
			LocalDate hireDate = Console.inDate("입사일을 입력하세요.");
			laborerService.addLaborer(laborerName, hireDate);		// Service 에 데이터를 넘겨준다.
			Console.info("노동자를 추가했습니다.");
		} else Console.info("추가 취소합니다.");                         // 0 을 누르면 작업 취소
	}
	
	private void fixLaborer() {
		int laborerId = Console.inNum("노동자ID를 입력하세요.");
		
		if(laborerId != 0) {
			String laborerName = Console.inStr("노동자명을 입력하세요.", 5);
			LocalDate hireDate = Console.inDate("입사일을 입력하세요.");
			
			// Service 에 데이터를 넘겨준다.
			try {	
				laborerService.fixLaborer(new Laborer(laborerId, laborerName, hireDate));
				Console.info("노동자를 취소합니다.");
			} catch(NoneException e) {
				Console.err(e.getMessage());
			}
		} else Console.info("수정 취소합니다.");
	}
	
	private void delLaborer() {
		int laborerId = Console.inNum("노동자ID를 입력하세요.");
		
		if(laborerId != 0) {
			try {
				laborerService.delLaborer(laborerId);
				Console.info("노동자를 삭제했습니다.");
			} catch(NoneException e) {
				Console.err(e.getMessage());
			}
		} else Console.info("삭제를 취소합니다.");
	}
}
