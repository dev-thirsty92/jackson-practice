package com.practice.date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DateHandlingTest {

    private static ObjectMapper mapper;

    @BeforeAll
    static void beforeAll() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    @DisplayName("LocalDateTime을 제어해봅니다..")
    void testLocaldateTimeHandling() throws JsonProcessingException {
        Request request = new Request(
                1,
                LocalDateTime.of(2024, 12, 17, 14, 00, 00)
        );

        String serialized = mapper.writeValueAsString(request);
        log.info("LocalDateTime 직렬화: {}", serialized);

        Request deserialized = mapper.readValue(serialized, Request.class);

        assertAll(()->{
            assertEquals(1, deserialized.getRequestId());
            assertEquals(LocalDateTime.of(2024, 12, 17, 14, 00, 00)
                    , deserialized.getRequestDate());
        });

    }
}
