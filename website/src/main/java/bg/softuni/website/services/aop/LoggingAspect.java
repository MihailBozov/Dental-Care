package bg.softuni.website.services.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {
    
    private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Pointcut("execution(* bg.softuni.website.services.*.*(..))")
    public void everyServiceMethod() {}
    
    @Before("everyServiceMethod()")
    public void beforeServiceMethodExecution(JoinPoint joinPoint) {
        LOGGER.info("Entering the method {} in {} class at {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                LocalDateTime.now());
    }
    
    @After("everyServiceMethod()")
    public void afterServiceMethodExecution(JoinPoint joinPoint) {
        LOGGER.info("Exiting the method {} in {} class at {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                LocalDateTime.now());
    }
    
    @AfterReturning(value = "everyServiceMethod()", returning = "result")
    public void afterReturningServiceMethodExecution(JoinPoint joinPoint, Object result) {
        LOGGER.info("After the execution of method {} in {} class at {} returned {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                LocalDateTime.now(),
                result);
    }
    
    @AfterThrowing(value = "everyServiceMethod()", throwing = "exception")
    public void afterThrowingExceptionInServiceMethodExecution(JoinPoint joinPoint, Exception exception) {
        LOGGER.info("{} occurred in method {} in {} class at {} with message {}",
                exception.getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                LocalDateTime.now(),
                exception.getMessage());
    }
}
