/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.lucene4.config;

import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import ufo.lucene4.Lucene4BaseTest;
import ufo.lucene4.service.LuceneService;

/**
 *
 * @author ufo
 */
public class SpringConfigTest extends Lucene4BaseTest {
   
    @Resource
    private LuceneService luceneService;
    @Value("${lucene.basefolder}")
    private String stringFromProperties;
    
     @Test
     public void testContextStart() throws InterruptedException {
         assertNotNull(luceneService);
         assertEquals("./target", stringFromProperties);
     }
     
}
