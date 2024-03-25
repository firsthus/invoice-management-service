package com.oxygen.invoicemanagementservice.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.json.JsonSanitizer;
import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.enums.JsonOption;
import com.oxygen.invoicemanagementservice.common.exception.OxygenRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());



    public static <T> T deserializeFromJson(String payloadString, Class<T> klass) {
        try {
            return objectMapper.readValue(payloadString, klass);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot deserialize payload to " + klass.getSimpleName(), e);
        }
    }


    public static Map<String, String> convertJsonToMap(String jsonSource) throws JsonProcessingException {
        Map<String, String> result = new HashMap<>();

        if (StringUtils.isBlank(jsonSource)) {
            return result;
        }

        JsonNode jsonNode = objectMapper.readTree(jsonSource);
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            result.put(field.getKey(), field.getValue().asText());
        }

        return result;
    }


    public static String serializeToJson(Object o, JsonOption... options) {
        try {
            Set<JsonOption> jsonOptions = Arrays.stream(options).collect(Collectors.toSet());
            ObjectWriter objectWriter = jsonOptions.contains(JsonOption.PRETTY_PRINT) ? objectMapper.writerWithDefaultPrettyPrinter() : objectMapper.writer();
            return jsonOptions.contains(JsonOption.NOT_SANITIZED) ? objectWriter.writeValueAsString(o) : JsonSanitizer.sanitize(objectWriter.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            throw new OxygenRuntimeException(ResponseCode.UNPROCESSABLE_ENTITY, "Cannot serialize object to JSON", e);
        }
    }
}
