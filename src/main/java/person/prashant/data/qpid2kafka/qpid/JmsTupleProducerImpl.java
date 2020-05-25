package person.prashant.data.qpid2kafka.qpid;

import org.apache.storm.jms.JmsTupleProducer;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JmsTupleProducerImpl implements JmsTupleProducer{
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsTupleProducerImpl.class);

    public static JmsTupleProducer build() {
        return new JmsTupleProducerImpl();
    }

    public Values toTuple(final Message message) throws JMSException {
        LOGGER.info("Received message - {}", message);
        return new Values(message.toString(), getPropertiesAsMap(message), message.getStringProperty("eventName"));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declareStream("default", new Fields("message", "properties", "eventName"));
    }

    private Map<String, String> getPropertiesAsMap(final Message message) throws JMSException {
        Map<String, String> properties = new HashMap<>();
        Enumeration props = message.getPropertyNames();
        while(props.hasMoreElements()){
            String key = props.nextElement().toString();
            properties.put(key, message.getStringProperty(key));
        }
        return properties;
    }
}
