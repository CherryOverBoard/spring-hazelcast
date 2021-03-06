package com.cherryoverboard.spring.hazelcast.controller;

import com.cherryoverboard.spring.hazelcast.Constants;
import com.cherryoverboard.spring.hazelcast.model.Todo;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cache")
public class CacheController {

    private HazelcastInstance hazelcastInstance;

    @Autowired
    public CacheController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping
    public UUID addTodo(@RequestBody Todo request) {
        Todo todo = Todo.from(request);
        IMap<UUID, Todo> todos = hazelcastInstance.getMap(Constants.TODOS);
        todos.putIfAbsent(todo.getId(), todo);
        return todo.getId();
    }

    @GetMapping("{id}")
    public Optional<Todo> getTodo(@PathVariable("id") UUID id) {
        IMap<UUID, Todo> todos = hazelcastInstance.getMap(Constants.TODOS);
        return Optional.ofNullable(todos.get(id));
    }
}
