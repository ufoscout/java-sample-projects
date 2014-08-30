/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufo.jetty9;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ufo
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Jetty9BaseTest {

    private final static DecimalFormat TIME_FORMAT = new DecimalFormat("####,###.###", new DecimalFormatSymbols(Locale.US));

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Rule
    public TestName name = new TestName();

    private Date testStartDate;

    @Before
    public void setUpBeforeTest() {
        testStartDate = new Date();
        logger.info("===================================================================");
        logger.info("BEGIN TEST " + name.getMethodName());
        logger.info("===================================================================");
    }

    @After
    public void tearDownAfterTest() {
        long executionTime = new Date().getTime() - testStartDate.getTime();
        logger.info("===================================================================");
        logger.info("END TEST " + name.getMethodName());
        logger.info("execution time: " + TIME_FORMAT.format(executionTime) + " ms");
        logger.info("===================================================================");
    }

    public Logger getLogger() {
        return logger;
    }
}
