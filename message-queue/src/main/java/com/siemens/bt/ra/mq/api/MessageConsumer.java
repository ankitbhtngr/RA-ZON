//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.api;

import java.io.IOException;

public interface MessageConsumer {

  public void consume(String topic) throws IOException;

}
