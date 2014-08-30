package ufo.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ufo.test.config.UfoAopContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=UfoAopContext.class)
public abstract class BaseTest {

}
