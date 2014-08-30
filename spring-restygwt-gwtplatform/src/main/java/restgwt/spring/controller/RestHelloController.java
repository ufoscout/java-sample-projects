package restgwt.spring.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import restgwt.client.restygwt.UserData;
import restgwt.client.restygwt.UserElaboratedData;
import restgwt.shared.RestURLPath;

@Controller
@RequestMapping(value = RestURLPath.HELLO_CONTROLLER)
public class RestHelloController {

	@RequestMapping(value = RestURLPath.HELLO_CONTROLLER_LOAD_INFO, method = RequestMethod.POST)
	public @ResponseBody UserElaboratedData handleRequest(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserData userData) throws ServletException, IOException {

		UserElaboratedData confirmation = new UserElaboratedData();
		int age = Calendar.getInstance().get( Calendar.YEAR ) - userData.year;
		confirmation.name = userData.name;
		confirmation.age = age;
		confirmation.ready_time = System.currentTimeMillis() + (1000 * 60 * 30);

		return confirmation;
	}
}
