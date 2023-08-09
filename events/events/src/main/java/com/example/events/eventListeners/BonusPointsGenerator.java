package com.example.events.eventListeners;

import com.example.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonusPointsGenerator {

    private Logger LOGGER = LoggerFactory.getLogger(BonusPointsGenerator.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent orderCreateEvent) {
        LOGGER.info("Order no {} has been created. I'm going to give bonus points to the client"
            , orderCreateEvent.getOrderId());

        //TODO = give bonus points to the client
    }
}
