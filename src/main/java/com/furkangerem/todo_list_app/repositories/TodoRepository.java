package com.furkangerem.todo_list_app.repositories;

import com.furkangerem.todo_list_app.entities.Todo;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends BaseRepository<Todo> {
}
