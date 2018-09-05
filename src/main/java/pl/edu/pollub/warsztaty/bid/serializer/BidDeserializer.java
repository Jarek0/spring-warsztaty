package pl.edu.pollub.warsztaty.bid.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;

import java.io.IOException;
import java.math.BigDecimal;

public class BidDeserializer extends StdDeserializer<BidEntity> {

    protected BidDeserializer() {
        super(BidEntity.class);
    }

    @Override
    public BidEntity deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        BigDecimal amount = (BigDecimal) node.get("amount").numberValue();

        return new BidEntity(amount);
    }
}
