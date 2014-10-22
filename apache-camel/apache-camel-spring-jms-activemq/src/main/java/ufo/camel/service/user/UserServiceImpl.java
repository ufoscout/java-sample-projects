package ufo.camel.service.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("USER_SERVICE")
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final List<User> users = new ArrayList<>();

	@Override
	public void saveUser(User user) {
		logger.info("Saving user [{}]", user.username);
		users.add(user);
	}

	@Override
	public User getUser(String username) {
		logger.info("Searching user [{}]", username);
		for (User user : users) {
			if (user.username.equals(username)) {
				logger.info("user [{}] found", username);
				return user;
			}
		}
		logger.info("user [{}] NOT found", username);
		return null;
	}

}
