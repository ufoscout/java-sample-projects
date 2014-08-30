package ufo.gae.core.service.validation;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;

import ufo.gae.core.GaeBaseTest;
import ufo.gae.core.domain.Song;
import ufo.gae.core.service.validation.ValidationResult;
import ufo.gae.core.service.validation.ValidationRule;
import ufo.gae.core.service.validation.ValidationService;
import ufo.gae.core.service.validation.ViolationCatalog;

public class ValidationServiceTest extends GaeBaseTest {

	@Resource
	private ValidationService validationService;

	@Test
	public void testBeanValidation() {
		Song song = new Song();
		song.setTitle("u");
		song.setYear(100);

		ValidationRule<Song> customValidationRule = new ValidationRule<Song>() {

			@Override
			public void validate(Song data, ViolationCatalog violationCatalog) {
				if ((data.getArtist() == null) || !data.getArtist().equals("Queen")) {
					violationCatalog.addViolation("bad_artist", "Artist is not Queen!");
				}
			}
		};

		ValidationResult<Song> validationResult = validationService.validator(song).addValidationRule(customValidationRule).validate();
		assertEquals( song , validationResult.getData() );
		Map<String, List<String>> errors = validationResult.getViolations();
		assertNotNull(errors);
		printErrors(errors);

		assertTrue( errors.containsKey("bad_artist") );
		assertTrue( errors.get("bad_artist").contains("Artist is not Queen!") );

		assertTrue( errors.containsKey("title") );
		assertTrue( errors.get("title").size() == 1 );
		assertTrue( errors.get("title").contains("minLenght3") );

		assertTrue( errors.containsKey("artist") );
		assertTrue( errors.get("artist").size() == 1 );
		assertTrue( errors.get("artist").contains("notNull") );

		assertTrue( errors.containsKey("year") );
		assertTrue( errors.get("year").size() == 1 );
		assertTrue( errors.get("year").contains("minSize1900") );
	}

	@Test
	public void testComplexBeanValidation() {
		BeanOne beanOne = new BeanOne();
		beanOne.beanOneList.add(new BeanOne());

		BeanOne beanOneValid = new BeanOne();
		beanOneValid.value = "";
		beanOne.beanOneList.add(beanOneValid);

		beanOne.beanOneList.add(new BeanOne());

		BeanOne beanTwoValid = new BeanOne();
		beanTwoValid.beanOneList.add(new BeanOne());
		beanTwoValid.value = "";
		beanOne.beanOneList.add(beanTwoValid);

		beanOne.innerBean = new BeanOne();
		beanOne.innerBean.beanOneList.add(new BeanOne());

		ValidationResult<BeanOne> validationResult = validationService.validator(beanOne).validate();
		assertEquals( beanOne,  validationResult.getData() );

		Map<String, List<String>> errors = validationResult.getViolations();
		printErrors(errors);
		assertTrue( errors.containsKey("value") );
		assertTrue( errors.containsKey("beanOneList[0].value") );
		assertTrue( errors.containsKey("beanOneList[2].value") );
		assertTrue( errors.containsKey("beanOneList[3].beanOneList[0].value") );
		assertTrue( errors.containsKey("innerBean.value") );
		assertTrue( errors.containsKey("innerBean.beanOneList[0].value") );
	}

	private void printErrors(Map<String, List<String>> errors) {
		System.out.println("-------------------------------------");
		for (Entry<String, List<String>> errorEntry :  errors.entrySet()) {
			String key = errorEntry.getKey();
			for (String message : errorEntry.getValue()) {
				System.out.println("Violation found -> key [" + key + "] - [" + message + "]");
			}
		}
		System.out.println("-------------------------------------");
	}

}
