package com.morris.metaj.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ObjectMapperHelper {
    private static final Logger logger = LoggerFactory.getLogger(ObjectMapperHelper.class);

    /**
     * Maps and converts json string to object of unknown type.
     *
     * @param s {@link String} json string
     * @param c {@link Class} given POJO class for object being mapped from json
     *
     * @return T
     *
     * @throws JsonProcessingException exception processing json mapping
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertJsonStringToObject(String s, Class<?> c) throws JsonProcessingException {
        Object obj = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(s);
        try {
           obj = objectMapper.treeToValue(node, c);
       } catch (IOException e) {
            logger.error("error mapping json string to object: {}", c);
            e.printStackTrace();
        }
        return (T) obj;
    }
}
