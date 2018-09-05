package pl.edu.pollub.warsztaty.bid.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;

import java.io.IOException;

public class BidSerializer extends StdSerializer<BidDto> {

    protected BidSerializer() {
        super(BidDto.class);
    }

    @Override
    public void serialize(BidDto value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        {
            jgen.writeNumberField("id", value.getId());
            jgen.writeNumberField("amount", value.getAmount());
        }
        jgen.writeEndObject();
    }
}
