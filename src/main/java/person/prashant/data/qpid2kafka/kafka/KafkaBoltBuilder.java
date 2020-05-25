package person.prashant.data.qpid2kafka.kafka;

import org.apache.storm.kafka.bolt.KafkaBolt;

import java.io.IOException;
import java.util.Properties;

public class KafkaBoltBuilder {

    public static final KafkaBolt build(){
        Properties properties = new Properties();
        try {
            properties.load(KafkaBoltBuilder.class.getClassLoader().getResourceAsStream("kafka.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new KafkaBolt()
                .withTupleToKafkaMapper(new TupleToKafkaMapperImpl())
                .withTopicSelector("test")
                .withProducerProperties(properties);
    }
}
