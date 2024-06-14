package io.jk.api.redis;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.List;
import java.util.jar.JarException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String , Object> redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("레디스에 key, value 저장에 성공한다.")
    void test1() {
        //given
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        final String key = "keykey";
        final String value ="hello, Redis";

        //when
        valueOperations.set(key, value);

        //then
        Object redisValue = valueOperations.get(key);
        assertThat(redisValue).isEqualTo(value);
    }


    /**redis의 hash 자료구조는 관계형 데이터베이스의 테이블과 유사하다.
     * key는 마치 table 이름과 같으며, field는 column명과 같고 value는 테이블의 tuple과 비
     */
    @Test
    @DisplayName("redis Hash에 set, get과 JSON 저장 조회에 성공한다.")
    void test2() throws JSONException {
        //given
        final String key = "hashKey";
        final String field1 = "field1";
        final String field2 = "field2";
        final String field3 = "JsonField";
        final String value1 = "value1";
        final String value2 = "value2";
        final JSONObject jsonObject = new JSONObject(
                "{" +
                        "\"name\":\"Jhon\"," +
                        "\"age\":\"10\"" +
                    "}"
        );
        String jsons = jsonObject.toString();

        //when
        redisTemplate.opsForHash().put(key,field1,value1);
        redisTemplate.opsForHash().put(key,field2,value2);
        redisTemplate.opsForHash().put(key,field3,jsons);


        //then
        String value = (String) redisTemplate.opsForHash().get(key, field3);
        assertThat(value).isEqualTo(jsons);

    }
}
