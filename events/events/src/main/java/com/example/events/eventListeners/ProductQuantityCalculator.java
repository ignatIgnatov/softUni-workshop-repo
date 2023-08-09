package com.example.events.eventListeners;

import com.example.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQuantityCalculator {

    private Logger LOGGER = LoggerFactory.getLogger(ProductQuantityCalculator.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent orderCreateEvent) {
        LOGGER.info("Order no {} has been created. I'm going to calculate the current product quantities."
                , orderCreateEvent.getOrderId());

        //TODO = see what products had been ordered and calculate subtract product quantities.
    }
}
