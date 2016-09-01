package org.example.morphia.mongodb.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.mongodb.morphia.converters.DateConverter;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.MappingException;

/**
 * Converter for LocalDateTime.
 *
 * @author adam.bialas
 *
 */
public class LocalDateTimeConverter extends DateConverter {

    public LocalDateTimeConverter() {
        super(LocalDateTime.class);
    }

    @Override
    public Object decode(final Class<?> targetClass, final Object val, final MappedField optionalExtraInfo)
            throws MappingException {
        final Date date = (Date) super.decode(targetClass, val, optionalExtraInfo);
        final Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    @Override
    public Object encode(final Object value, final MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }
        return new Date(((LocalDateTime) value).toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}