package com.github.stackoverflowexample;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InvokeByInitiatorAnnotationBeanPostProcessor implements BeanPostProcessor {

    private List<InitiatorHandler> handlers = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object obj = BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(obj.getClass());
        for (Method method : methods) {
            InvokeByInitiator invokeByInitiator = method.getAnnotation(InvokeByInitiator.class);

            if (invokeByInitiator == null) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }

            Class<?> parameterType = parameterTypes[0];

            if (Map.class.isAssignableFrom(parameterType)) {
                handlers.add(params -> {
                    ReflectionUtils.makeAccessible(method);
                    ReflectionUtils.invokeMethod(method, obj, params);
                });
            }
        }

        return obj;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object obj = BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);

        if (obj instanceof Initiator) {
            handlers.forEach(((Initiator) obj)::registerInitiatorHandler);
        }

        return obj;
    }

}
