package ufo.spring.integration.service;

import java.util.UUID;

import org.springframework.stereotype.Controller;

@Controller
public class NodeNameServiceImpl implements NodeNameService {

	private String name = UUID.randomUUID().toString();

	NodeNameServiceImpl() {
		System.out.println("Started with name " + name );
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
