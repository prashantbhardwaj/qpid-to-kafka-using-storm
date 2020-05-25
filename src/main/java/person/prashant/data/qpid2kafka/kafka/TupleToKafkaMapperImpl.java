package person.prashant.data.qpid2kafka.kafka;

import org.apache.storm.kafka.bolt.mapper.TupleToKafkaMapper;
import org.apache.storm.tuple.Tuple;

public class TupleToKafkaMapperImpl implements TupleToKafkaMapper<String, String> {

    @Override
    public String getKeyFromTuple(Tuple tuple) {
        return tuple.getStringByField("eventName");
    }

    @Override
    public String getMessageFromTuple(Tuple tuple) {
        return tuple.getStringByField("message");
    }
}
