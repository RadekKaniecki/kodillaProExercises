package com.kodilla.eventsexercise.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BasicCalculatorListener implements ApplicationListener<BasicCalculatorUsedEvent> {

    private Logger logger = LoggerFactory.getLogger(BasicCalculatorListener.class);

    @Override
    public void onApplicationEvent(BasicCalculatorUsedEvent event) {
        logger.info(event.getOperator() + " operator was called with parameters: a = " + event.getA() + ", b = " + event.getB());
    }
}
