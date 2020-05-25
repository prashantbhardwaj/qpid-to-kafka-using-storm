package person.prashant.data.qpid2kafka.qpid;

import org.apache.qpid.client.AMQConnectionFactory;
import org.apache.qpid.client.AMQQueue;
import org.apache.storm.jms.JmsProvider;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import java.util.Properties;

public class JmsProviderImpl implements JmsProvider {
    transient private Context context;
    private final Properties properties;

    public static JmsProviderImpl build() {
        return new JmsProviderImpl();
    }

    private JmsProviderImpl() throws RuntimeException {
        try {
            properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("qpid.properties"));
        } catch (Exception exp){
            throw new RuntimeException(exp);
        }
    }

    public ConnectionFactory connectionFactory() throws Exception {
        return new AMQConnectionFactory(properties.getProperty("connectionfactory.qpidConnectionfactory"));
    }

    public Destination destination() throws Exception {
        return new AMQQueue(properties.getProperty("destination.queueName"));
    }
}
