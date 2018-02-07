package twitter.classification.queuereader.reader;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

public class QueueReader {

  private static final Logger logger = LoggerFactory.getLogger(QueueReader.class);

  private Channel channel;

  private Consumer consumer;

  public QueueReader(Channel channel, Consumer consumer) {

    this.channel = channel;
    this.consumer = consumer;
  }

  public void run() {

    try {
      channel.basicConsume("tweets", true, consumer);
    } catch (IOException e) {
      logger.error("Issue consuming the queue", e);
    }
  }
}
