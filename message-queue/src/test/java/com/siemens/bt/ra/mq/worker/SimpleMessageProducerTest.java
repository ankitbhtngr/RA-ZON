//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.worker;

import java.io.IOException;

import org.junit.Test;

import com.siemens.bt.ra.mq.api.MessageProducer;

public class SimpleMessageProducerTest {

  private final String topic = "simple_task_queue";

  @Test
  public void createAndSendTask() throws IOException {
    MessageProducer creator = new RabbitMessageProducer();
    creator.produce(topic, "Task1.");
  }

  @Test
  public void createAndSendMultipleTasks() throws IOException {
    MessageProducer creator = new RabbitMessageProducer();
    creator.produce(topic, "Task2......");
    creator.produce(topic, "Task3.");
    creator.produce(topic, "Task4...");
    creator.produce(topic, "Task5...");
  }
}
