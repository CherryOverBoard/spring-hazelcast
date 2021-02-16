package com.cherryoverboard.spring.hazelcast.serialization;

import com.cherryoverboard.spring.hazelcast.model.Todo;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;
import java.util.UUID;

public class TodoSerializer implements StreamSerializer<Todo> {

    @Override
    public void write(ObjectDataOutput objectDataOutput, Todo todo) throws IOException {
        objectDataOutput.writeUTF(todo.getId().toString());
        objectDataOutput.writeUTF(todo.getNote());
        objectDataOutput.writeBoolean(todo.getDone());
    }

    @Override
    public Todo read(ObjectDataInput objectDataInput) throws IOException {
        return new Todo(
                UUID.fromString(objectDataInput.readUTF()),
                objectDataInput.readUTF(),
                objectDataInput.readBoolean()
        );
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
