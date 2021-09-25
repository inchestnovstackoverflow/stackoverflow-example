package com.github.stackoverflowexample;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InitiatorImpl implements Initiator {

    private List<InitiatorHandler> handlers = new ArrayList<>();

    @Override
    public void registerEventHandler(InitiatorHandler handler) {
        handlers.add(handler);
    }

    @Override
    public void doInvoke(Map<String, Object> params) {
         handlers.forEach(h -> h.handle(params));
    }

}
