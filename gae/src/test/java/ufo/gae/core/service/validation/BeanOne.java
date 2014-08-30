package ufo.gae.core.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class BeanOne {

	@NotNull
	String value;

	@Valid
	List<BeanOne> beanOneList = new ArrayList<BeanOne>();

	@Valid
	BeanOne innerBean;

}
