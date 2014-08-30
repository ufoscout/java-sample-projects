package ufo.test.aop;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UfoAspect {
	
	@Resource
	private Actions actions;

	private Logger logger = LoggerFactory.getLogger(getClass());

//	@Around("execution(* *(..)) && @annotation(annotation)")
	@Around("@annotation(ufoAnnotation)")
	public Object retry(ProceedingJoinPoint pjp, UfoAnnotation ufoAnnotation) throws Throwable {
		Object returnValue = null;

		logger.info("Before execution");
		logger.info("Annotation value [{}]", ufoAnnotation.value());
		
		logger.info("pjp.getArgs() [{}]", pjp.getArgs());
		logger.info("pjp.getTarget() [{}]", pjp.getTarget());
		logger.info("pjp.getThis() [{}]", pjp.getThis());
		logger.info("pjp.getKind() [{}]", pjp.getKind());
		logger.info("pjp.getSignature() [{}]", pjp.getSignature());
		logger.info("pjp.getSourceLocation() [{}]", pjp.getSourceLocation());
		logger.info("pjp.getStaticPart() [{}]", pjp.getStaticPart());
		
		actions.calls.add(pjp.getSignature().getName());
		
		logger.info("Start execution");
		returnValue = pjp.proceed();
		
		logger.info("After execution");
		
		if (ufoAnnotation.noReturn()) {
			return null;
		}
		
		return returnValue;
	}
}
