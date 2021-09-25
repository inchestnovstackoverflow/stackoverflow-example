package com.github.stackoverflowexample;

import java.util.Map;

public interface Initiator {

    void registerEventHandler(InitiatorHandler handler);

    void doInvoke(Map<String, Object> params);

}
