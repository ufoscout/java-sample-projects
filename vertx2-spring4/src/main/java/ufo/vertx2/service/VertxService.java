/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.vertx2.service;

import org.vertx.java.core.eventbus.EventBus;

/**
 *
 * @author ufo
 */
public interface VertxService {

	EventBus eventBus();

}
