//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.worker;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.siemens.bt.ra.mq.api.MessageProducer;
import com.siemens.bt.ra.mq.common.ChannelCreator;

public class RabbitMessageProducer implements MessageProducer {

  private static String TASK_QUEUE_NAME = "default_queue";

  @Override
  public void produce(String topic, String message) throws IOException {
    TASK_QUEUE_NAME = topic;
    Channel channel = createChannel(topic);
    publish(message, channel);
    closeChannel(channel);
  }

  private static void closeChannel(Channel channel) throws IOException {
    channel.close();
    channel.getConnection().close();
  }

  private static void publish(String message, Channel channel) throws IOException {
    channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
  }

  private static Channel createChannel(String topic) throws IOException {
    TASK_QUEUE_NAME = topic;
    Channel channel = ChannelCreator.create();
    channel.queueDeclare(TASK_QUEUE_NAME, true);
    return channel;
  }
}
