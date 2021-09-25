package com.github.stackoverflowexample;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
class Invoker {

    @InvokeByInitiator
    public void invoke(Map<String, Object> params) {
        String s = "";
    }

}
