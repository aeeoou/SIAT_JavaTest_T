package com.my.hr.dao;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.my.hr.domain.Laborer;
import com.my.hr.domain.NoneException;

public class LaborerDaoImpl implements LaborerDao {
	// DAO는 DB로써 리스트를 받게 된다. (현재는 DB가 리스트지만, 나중에는 hr 또는 mybatis 앱을 만듬)
	private List<Laborer> laborers;
	// 노동자 ID를 만든다.
	private int laborerIdSeq;
	
	// 생성자
	public LaborerDaoImpl(List<Laborer> laborers) {
		this.laborers = laborers;
		this.laborerIdSeq = 1;
	}
	
	@Override
	public List<Laborer> selectLaborers() {
		return laborers;
	}
	
	// 인터페이스에는 없는 메서드 (외부에서 호출은 안하지만, 이 클래스 내부에서만 쓴다.)
	//인자로 int laborerId 를 받아, 이 laborerId 와 일치하는 Laborer 객체를 찾는다.
	private Laborer selectLaborer(int laborerId) {
		// laborers 리스트를 스트림으로 변환하여 laborerId 와 일치하는 Laborer 객체를 필터링한 후, 리스트로 수집
		List<Laborer> list = laborers.stream()                       // 스트림 생성 (stream) = 컬렉션의 요소들을 처리하는 일련의 연산 (중간연산과 최종연산으로 구성) 
				.filter(laborer -> laborer.laborerId() == laborerId) // 필터 적용 (filter)  = 중간연산으로, 조건을 만족하는 요소들만을 포함하는 새로운 스트림 생성
				.collect(Collectors.toList());						 // 결과 수집 (collect) = 최종연산으로, 스트림의 요소들을 수집하여 결과를 생성
		
		// Laborer 객체를 저장할 변수를 초기화
		Laborer laborer = null;
		// 리스트가 비어 있지 않은 경우에 첫 번째 요소를 laborer 변수에 할당한다. (리스트가 비어 있으면 null 반환)
		if(!list.isEmpty()) laborer = list.get(0);
		
		return laborer;
		
		// laborerId를 와 일치하는 Laborer 객체를 laborers 리스트에서 찾아 반환하는 메서드
		// 일치하는 Laborer 객체가 없는 경우 null 을 반환
		// steam API를 사용하여 리스트를 필터링하고, 결과 리스트에서 첫 번째 요소를 선택
		// 예외가 발생하지 않도록 리스트가 비어 있지 않은지 확인하는 논리를 포함한다.
	}
	
	@Override
	public void insertLaborer(String laborerName, LocalDate hireDate) {
		// add 메서드는 List 인터페이스에 정의된 메서드로, 리스트 끝에 요소를 포함시키는 역할을 한다. (오소가 리스트에 추가되면 리스트의 크기가 1 증가)
		laborers.add(new Laborer(laborerIdSeq++, laborerName, hireDate));
	}
	
	@Override
	public void updateLaborer(Laborer laborer) {
		// 기존에 있던 laborer를 삭제하고
		this.deleteLaborer(laborer.laborerId());
		// 수정할 내용을 담고있는 laborer를 수정
		laborers.add(laborer);
		// 정렬시키기 (리스트를 삭제하고 넣으면 정렬이 어지러워지니 노동자ID 순서로 정렬 기준을 세운다.)
		laborers.sort(Comparator.comparing(Laborer::laborerId));
	}
	
	@Override
	public void deleteLaborer(int laborerId) {
		// 노동자를 삭제하려먼 노동자 정보가 있어야 삭제가 가능하다. 그러니 조회부터 먼저 한다.
		Laborer laborer = selectLaborer(laborerId);
		// 노동자가 있으면, DB(laborers)에서 삭제한다.
		if(laborer != null) laborers.remove(laborer);
		// 만약 노동자가 없으면, 만들었던 NonException을 발생시킨다.
		else throw new NoneException("해당 노동자가 없습니다.");
	}
}
