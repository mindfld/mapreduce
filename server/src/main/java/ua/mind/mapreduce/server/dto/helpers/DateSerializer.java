package ua.mind.mapreduce.server.dto.helpers;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mind on 16.09.14.
 * Date serializer for correct Date representation for JSON Respond. Didn't worked. Yet don't know why
 */
public class DateSerializer extends JsonSerializer<Date> {
    public static final String DATE_FORMAT = ("dd/MM/yyyy");

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws
            IOException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String formattedDate = formatter.format(value);
        gen.writeString(formattedDate);

    }
}