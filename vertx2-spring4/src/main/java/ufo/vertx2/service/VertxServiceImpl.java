/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.vertx2.service;

import org.springframework.stereotype.Service;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.eventbus.EventBus;

/**
 *
 * @author ufo
 */
@Service
public class VertxServiceImpl implements VertxService {

	private final Vertx vertx = VertxFactory.newVertx();

	@Override
	public EventBus eventBus() {
		return vertx.eventBus();
	}

}
