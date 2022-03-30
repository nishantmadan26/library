package com.prisma.library.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvUtils {
    private static final CsvMapper mapper = new CsvMapper();

    private CsvUtils() {
    }

    public static <T> List<T> read(Class<T> tClass, MultipartFile file) throws IOException {
        final List<T> result = new ArrayList<>();
        CsvSchema schema = mapper.schemaFor(tClass).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(tClass).with(schema);
        final MappingIterator<T> records = reader.readValues(file.getInputStream());
        while (records.hasNextValue()) {
            final T one = records.nextValue();
            result.add(one);
        }
        return result;
    }

}
