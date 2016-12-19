//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.topic;

import java.io.IOException;

import org.junit.Test;

public class TopicPublisherTest {

  @Test
  public void publishMultipleTopics() throws IOException {
    TopicPublisher publisher = new TopicPublisher();
    publisher.publish("zomato.order.rec", "Order received from Zomato");
    publisher.publish("foodpanda.payment.rec", "Payment received from Foodpanda");
    publisher.publish("zomato.order.deliver", "Order delivered for Zomato");
    publisher.publish("zomato.order.cancel", "Order cancelled from Zomato");
    publisher.publish("foodpand.payment.cancel", "Order received from Zomato");
    publisher.close();
  }
}
