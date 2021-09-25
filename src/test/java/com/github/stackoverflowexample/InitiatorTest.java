package com.github.stackoverflowexample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InitiatorConfiguration.class)
class InitiatorTest {

    @Autowired
    private Initiator initiator;

    @MockBean
    private Invoker invoker;

    @Test
    void doInvokeTest() {
        HashMap<String, Object> params = new HashMap<>();
        initiator.doInvoke(params);
        Mockito.verify(invoker).invoke(params);
    }

}