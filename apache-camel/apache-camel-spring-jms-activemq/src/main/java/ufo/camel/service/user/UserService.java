package ufo.camel.service.user;

public interface UserService {

	void saveUser(User user);

	User getUser(String username);

}
