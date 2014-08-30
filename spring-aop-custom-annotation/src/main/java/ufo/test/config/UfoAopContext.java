package ufo.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import ufo.BasePackageScan;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackageClasses = {BasePackageScan.class})
//@PropertySource("classpath:application.properties")
public class UfoAopContext {

}
