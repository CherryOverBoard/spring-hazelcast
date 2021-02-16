package com.cherryoverboard.spring.hazelcast.embedded.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Todo {

    private UUID id;
    private String note;
    private Boolean done;

    public static Todo from(Todo from) {
        return new Todo(UUID.randomUUID(), from.note, from.done);
    }

    public Todo(
            @JsonProperty("id") UUID id,
            @JsonProperty("note") String note,
            @JsonProperty("done") Boolean done
    ) {
        this.id = id;
        this.note = note;
        this.done = done;
    }

    public UUID getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public Boolean getDone() {
        return done;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id +
                "\", \"note\": \"" + note + '\"' +
                ", \"done\": \"" + done +
                "\"}";
    }
}
