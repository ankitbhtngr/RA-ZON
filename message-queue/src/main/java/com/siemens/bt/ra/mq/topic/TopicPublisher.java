//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.topic;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.siemens.bt.ra.mq.common.ChannelCreator;

public class TopicPublisher {

  private static final String EXCHANGE_NAME = "topic_exchange";

  private final Channel channel;

  public TopicPublisher() throws IOException {
    this.channel = ChannelCreator.create();
    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
  }

  public void publish(String routingKey, String message) throws IOException {
    channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
    System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
  }

  public void close() throws IOException {
    channel.close();
    channel.getConnection().close();
  }
}
