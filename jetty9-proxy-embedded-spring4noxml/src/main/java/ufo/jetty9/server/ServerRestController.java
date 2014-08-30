package ufo.jetty9.server;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("welcome")
public class ServerRestController {

	@RequestMapping(value = "sayHelloWorld", method = RequestMethod.GET)
	public @ResponseBody Callable<String> sayHelloWorld() {
		Callable<String> deferredResult = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "Hello World!";
			}
		};
		return deferredResult;
	}


}
