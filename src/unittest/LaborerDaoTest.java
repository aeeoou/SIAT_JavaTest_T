package unittest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.my.hr.dao.LaborerDao;
import com.my.hr.dao.LaborerDaoImpl;
import com.my.hr.domain.Laborer;
import com.my.hr.domain.NoneException;

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
		
		// update 테스트
		laborerDao.updateLaborer(new Laborer(1, "임화영", LocalDate.of(2024, 5, 26)));
		System.out.println(laborerDao.selectLaborers());
		
		// delete 테스트
		laborerDao.deleteLaborer(2);
		System.out.println(laborerDao.selectLaborers());
		// delete 에서 예외처리가 되는지 테스트하기
		
		// 1) update 의 예외처리 테스트
		try {
			// 예외가 발새하면 exception이 발생하도록 설계하였다. 
			laborerDao.updateLaborer(new Laborer(12, null, null)); // 등록되지 않는 정보를 기재해본다.
		// exception 타입은 NoneException 타입.
		} catch(NoneException e) {
			e.printStackTrace();
		}
		// 2) delete 의 예외처리 테스트
		try {
			laborerDao.deleteLaborer(12); // delete 는 ID 값만 입력하면 된다.
		} catch(NoneException e) {
			e.printStackTrace();
		}
		
		// DAO 유닛테스트 끝.
	}
}
