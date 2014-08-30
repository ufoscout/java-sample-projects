package ufo.springreactor;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

@Configuration
@ComponentScan
public class SpringConfig {

    @Bean
    Environment env() {
        return new Environment();
    }

    @Bean
    Reactor createReactor(Environment env) {
        return Reactors.reactor()
                .env(env)
                .dispatcher(Environment.THREAD_POOL)
                .get();
    }

    @Bean
    Integer numberOfJokes() {
        return 10;
    }

    @Bean
    public CountDownLatch latch(Integer numberOfJokes) {
        return new CountDownLatch(numberOfJokes);
    }
}
