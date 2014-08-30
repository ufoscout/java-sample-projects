package ufo.springreactor;

import static reactor.event.selector.Selectors.$;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

import reactor.core.Reactor;

@Import(value=SpringConfig.class)
public class Application implements CommandLineRunner {

    @Autowired
    private Reactor reactor;

    @Autowired
    private Receiver receiver;

    @Autowired
    private Publisher publisher;

    @Override
    public void run(String... args) throws Exception {
        reactor.on($("jokes"), receiver);
        publisher.publishJokes();
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}
