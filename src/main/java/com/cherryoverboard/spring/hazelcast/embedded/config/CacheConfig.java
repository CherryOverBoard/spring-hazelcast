package com.cherryoverboard.spring.hazelcast.embedded.config;

import com.cherryoverboard.spring.hazelcast.embedded.model.Todo;
import com.cherryoverboard.spring.hazelcast.embedded.serialization.TodoSerializer;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.cherryoverboard.spring.hazelcast.embedded.Constants.TODOS;

@Configuration
public class CacheConfig {

    @Value("${cache.max-ttl}")
    private int maxTTL;
    @Value("${cache.max-idle}")
    private int maxIdle;

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(createConfig());
    }

    private Config createConfig() {
        MapConfig mapConfig = new MapConfig(TODOS);
        mapConfig.setTimeToLiveSeconds(maxTTL);
        mapConfig.setMaxIdleSeconds(maxIdle);

        Config config = new Config();
        config.addMapConfig(mapConfig);
        config.getSerializationConfig()
                .addSerializerConfig(todoSerializerConfig());

        return config;
    }

    private SerializerConfig todoSerializerConfig() {
        SerializerConfig sc = new SerializerConfig();
        sc.setImplementation(new TodoSerializer()).setTypeClass(Todo.class);
        return sc;
    }

}
