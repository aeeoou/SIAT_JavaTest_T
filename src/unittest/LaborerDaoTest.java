package unittest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.my.hr.dao.LaborerDao;
import com.my.hr.dao.LaborerDaoImpl;
import com.my.hr.domain.Laborer;

// dao 유닛테스트 하기
public class LaborerDaoTest {
	public static void main(String[] args) {
		// DB를 준비한다.(가상의)
		List<Laborer> laborers = new ArrayList<>();
		laborers.add(new Laborer(11, "고현정", LocalDate.now()));
		// 테스트 대상인 DAO를 준비한다.
		LaborerDao laborerDao = new LaborerDaoImpl(laborers);
		
		System.out.println(laborerDao.selectLaborers());
		
		// insert 테스트
		laborerDao.insertLaborer("김남길", LocalDate.now());
		System.out.println(laborerDao.selectLaborers());
		// insert 테스트2
		laborerDao.insertLaborer("박해일", LocalDate.now());
		System.out.println(laborerDao.selectLaborers());
		
		//update 테스트
		laborerDao.updateLaborer(new Laborer(1, "임화영", LocalDate.of(2024, 5, 26)));
		System.out.println(laborerDao.selectLaborers());
	}
}
