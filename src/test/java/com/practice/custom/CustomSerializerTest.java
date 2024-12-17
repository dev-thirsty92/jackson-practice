package com.practice.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class CustomSerializerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("자바 객체의 표준직렬화")
    @Disabled("Item 클래스의 @JsonSerialize를 주석처리 후 테스트를 실행할 것")
    void standardSerializationTest() throws JsonProcessingException {
        Item item = new Item(1, "item", new User(2, "user"));
        String serialized = mapper.writeValueAsString(item);

        log.info("표준직렬화: {}", serialized);
        Item deserialized = mapper.readValue(serialized, Item.class);

        assertAll(()->{
            assertEquals(1, deserialized.id) ;
            assertEquals("item",deserialized.name) ;
            assertEquals(2, deserialized.getOwner().id);
            assertEquals("user", deserialized.getOwner().name);
        });
    }


    @Test
    @DisplayName("자바 객체의 커스텀 직렬화")
    void customSerializationTest() throws JsonProcessingException {
        Item item = new Item(1, "item", new User(2, "user"));

        SimpleModule module = new SimpleModule();
        module.addSerializer(Item.class, new CustomItemSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(item);
        log.info("커스텀직렬화: {}", serialized);

        Map<String, Object> deserialized = mapper.readValue(serialized, java.util.Map.class);
        assertAll(()->{
            assertEquals(1, deserialized.get("id"));
            assertEquals("item", deserialized.get("itemName"));
            assertEquals( 2, deserialized.get("ownerId"));
        });

    }


    @Test
    @DisplayName("커스텀 직렬화기를 클래스에 직접 등록하여 활용")
    void customSerializerOntheClassTest() throws JsonProcessingException {
        Item item = new Item(1, "item", new User(2, "user"));
        String serialized = mapper.writeValueAsString(item);
        log.info("커스텀직렬화: {}", serialized);

        Map<String, Object> deserialized = mapper.readValue(serialized, java.util.Map.class);
        assertAll(()->{
            assertEquals(1, deserialized.get("id"));
            assertEquals("item", deserialized.get("itemName"));
            assertEquals( 2, deserialized.get("ownerId"));
        });

    }






}
