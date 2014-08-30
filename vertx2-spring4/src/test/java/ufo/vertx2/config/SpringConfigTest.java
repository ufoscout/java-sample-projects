/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.vertx2.config;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import ufo.vertx2.VertxBaseTest;
import ufo.vertx2.service.VertxService;

/**
 *
 * @author ufo
 */
public class SpringConfigTest extends VertxBaseTest {
   
    @Resource
    private VertxService luceneService;
    @Value("${ufo.test.properties}")
    private String stringFromProperties;
    
     @Test
     public void testContextStart() throws InterruptedException {
         assertNotNull(luceneService);
         assertEquals("valueFromPropertiesFile", stringFromProperties);
     }
     
}
