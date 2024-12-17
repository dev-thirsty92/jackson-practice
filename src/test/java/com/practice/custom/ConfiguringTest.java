package com.practice.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ConfiguringTest {

    private static ObjectMapper mapper;
    
    @BeforeAll
    static void beforeAll() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    @DisplayName("객체에서 정의하지 않은 새로운 필드를 역직렬화할 시에 에외가 발생한다.")
    @Disabled("mapper.configure를 주석 후 테스트 실행해주세요.")
    void testUnrecognizedPropertyException() throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"name\", \"unknownType\":\"unknown\"}";
        assertThrows(UnrecognizedPropertyException.class
                , () -> mapper.readValue(json, User.class)
        );
    }

    @Test
    @DisplayName("객체에서 정의하지 않은 새로운 필드를 역직렬화할 시에 에외가 발생하지않는다..")
    void testUnknownProperties() throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"name\", \"unknownType\":\"unknown\"}";
        User user = mapper.readValue(json, User.class);

        assertAll(()->{
            assertEquals(1, user.getId());
            assertEquals("name", user.getName());
        });
    }

}
