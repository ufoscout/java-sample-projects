package ufo.test.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Actions {

	public final List<String> calls = new ArrayList<>();
	
}
