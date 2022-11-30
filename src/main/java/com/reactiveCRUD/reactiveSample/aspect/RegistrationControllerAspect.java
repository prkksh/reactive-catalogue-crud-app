package com.reactiveCRUD.reactiveSample.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RegistrationControllerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationControllerAspect.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcut() {}

    @Before("pointcut()")
    public void logMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Map<String, Object> parameters = getParameters(joinPoint);

        try {
            LOGGER.info("==> path(s): {}, method(s): {}, arguments: {} ",
                    signature.getDeclaringTypeName(), signature.getName(), objectMapper.writeValueAsString(parameters));
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting", e);
        }
    }

    @AfterReturning(pointcut = "pointcut()", returning = "entity")
    public void logMethodAfter(JoinPoint joinPoint, Mono entity) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        try {
            LOGGER.info("<== path(s): {}, method(s): {}, retuning: {}",
                    signature.getDeclaringTypeName(), signature.getName(), objectMapper.writeValueAsString(entity));
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting", e);
        }
    }


    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        HashMap<String, Object> map = new HashMap<>();

        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }
}
