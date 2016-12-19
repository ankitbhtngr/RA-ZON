//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.topic;

import java.io.IOException;

public class TopicReceiverTest {

  private void createZomatoReceiver() throws IOException {
    TopicReceiver receiver = new TopicReceiver().withTopicSubscribed("zomato.#");
    receiver.receive();
  }

  public void createFoodpandaReceiver() throws IOException {
    TopicReceiver receiver = new TopicReceiver().withTopicSubscribed("foodpanda.#");
    receiver.receive();
  }

  public void createOrderReceiver() throws IOException {
    TopicReceiver receiver = new TopicReceiver().withTopicSubscribed("*.order.*");
    receiver.receive();
  }

  public void createPaymentReceiver() throws IOException {
    TopicReceiver receiver = new TopicReceiver().withTopicSubscribed("*.payment.*");
    receiver.receive();
  }

}
