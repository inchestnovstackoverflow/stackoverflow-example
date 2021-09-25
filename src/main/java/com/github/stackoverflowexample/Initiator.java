package com.github.stackoverflowexample;

import java.util.Map;

public interface Initiator {

    void registerInitiatorHandler(InitiatorHandler handler);

    void doInvoke(Map<String, Object> params);

}
