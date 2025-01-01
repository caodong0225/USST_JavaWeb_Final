package usst.web.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jyzxc
 * @since 2025-1-1
 */
@Service
public class RocketMQProducerService {
    private DefaultMQProducer producer;

    @Autowired
    public RocketMQProducerService(DefaultMQProducer producer) throws MQClientException {
        this.producer = producer;
        producer.start();
    }

    public void sendMessage(String topic, String messageContent) throws Exception {
        Message message = new Message(topic, messageContent.getBytes());
        producer.send(message);
    }
}
