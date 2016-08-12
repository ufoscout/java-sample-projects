package test.socket;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SocketApplication.class)
public abstract class SocketApplicationTests {

	protected int getFreePort(int minPortNumber) {
		int port = minPortNumber-1;
		boolean found = false;
		while (!found) {
			port++;
			found = isPortFree(port);
		}
		return port;
	}

	protected int getNotFreePort(int minPortNumber) {
		int port = minPortNumber-1;
		boolean found = false;
		while (!found) {
			port++;
			found = !isPortFree(port);
		}
		return port;
	}

    protected boolean isPortFree(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
