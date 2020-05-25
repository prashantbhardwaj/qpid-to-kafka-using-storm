package person.prashant.data.qpid2kafka.qpid;

import org.apache.storm.jms.spout.JmsSpout;

public class JMSSpoutBuilder {

    public static JmsSpout build(){
        JmsSpout jmsSpout = new JmsSpout();
        jmsSpout.setDistributed(false);
        jmsSpout.setJmsAcknowledgeMode(2); // 2 = clientAckHandler
        jmsSpout.setJmsTupleProducer(JmsTupleProducerImpl.build());
        jmsSpout.setJmsProvider(JmsProviderImpl.build());
        return jmsSpout;
    }
}
