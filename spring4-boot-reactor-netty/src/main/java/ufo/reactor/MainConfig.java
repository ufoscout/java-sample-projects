package ufo.reactor;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.function.Consumer;
import reactor.io.encoding.StandardCodecs;
import reactor.net.NetChannel;
import reactor.net.NetServer;
import reactor.net.netty.tcp.NettyTcpServer;
import reactor.net.tcp.spec.TcpServerSpec;
import reactor.spring.context.config.EnableReactor;
/**
 * Simple Spring Boot app to start a Reactor+Netty-based REST API server
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableReactor
public class MainConfig {

	@Bean
	public Reactor reactor(Environment env) {
		Reactor reactor = Reactors.reactor(env, Environment.THREAD_POOL);

		// Register our thumbnailer on the Reactor
		// reactor.receive($("thumbnail"), new BufferedImageThumbnailer(250));

		return reactor;
	}

	@Bean
	public NetServer<String, String> restApi(Environment env, Reactor reactor, CountDownLatch closeLatch) throws InterruptedException {

		NetServer<String, String> server = new TcpServerSpec<String, String>(NettyTcpServer.class)
				.env(env)
				.dispatcher(Environment.RING_BUFFER)
				.listen(8088)
				 .codec(StandardCodecs.STRING_CODEC)
				.consume((NetChannel<String, String> ch) -> {
					String msg = "Hello World";
					ch.send(msg).onSuccess(new Consumer<Void>() {

                        @Override
                        public void accept(Void data) {

							System.out.println("sent:" + msg);
                        }

                    }).onError(new Consumer<Throwable>() {

                        @Override
                        public void accept(Throwable t) {
                            t.printStackTrace();
                        }
                    });
					// filter requests by URI


						/*
						Stream<String> in = ch.in();
						// shutdown this demo app
						in.filter((FullHttpRequest req) -> "/shutdown".equals(req.getUri())).consume(req -> closeLatch.countDown());

						in.filter((FullHttpRequest req) -> "/hello".equals(req.getUri())).consume((FullHttpRequest req) -> {
							DefaultFullHttpResponse resp = new DefaultFullHttpResponse(HTTP_1_1, OK);
							    resp.content().writeBytes("Hello World".getBytes());
							    resp.headers().set(CONTENT_TYPE, "text/plain");
							    resp.headers().set(CONTENT_LENGTH, resp.content().readableBytes());
							ch.send(resp);
						});
						*/
					}).get();

		server.start().await();

		return server;
	}

	@Bean
	public CountDownLatch closeLatch() {
		return new CountDownLatch(1);
	}

	public static void main(String... args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(MainConfig.class, args);

		// Reactor's TCP servers are non-blocking so we have to do something to
		// keep from exiting the main thread
		CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
		closeLatch.await();
	}

}
