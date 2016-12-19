//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.topic;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.siemens.bt.ra.mq.common.ChannelCreator;

public class TopicReceiver {

  private static final String EXCHANGE_NAME = "topic_exchange";

  private final Channel channel;

  private String topicKey = null;

  public TopicReceiver() throws IOException {
    this.channel = ChannelCreator.create();
  }

  public TopicReceiver withTopicSubscribed(String bindingKey) {
    this.topicKey = bindingKey;
    return this;
  }

  public void receive() throws IOException {
    assert topicKey != null;
    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    String queueName = channel.queueDeclare().getQueue();
    channel.queueBind(queueName, EXCHANGE_NAME, topicKey);

    System.out.println(" [*] Waiting for topic: " + topicKey);

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
      }
    };
    channel.basicConsume(queueName, true, consumer);
  }

  public static void main(String[] args) throws IOException {
    // String topic = "#";
    // String topic = "zomato.#";
    // String topic = "foodpanda.#";
    // String topic = "*.order.*";
    String topic = "*.payment.*";

    TopicReceiver receiver = new TopicReceiver().withTopicSubscribed(topic);
    receiver.receive();
  }

}
