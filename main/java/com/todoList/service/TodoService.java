package com.todoList.service;

import com.todoList.aggregate.Todo;
import com.todoList.repository.TodoRepository;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TodoService {

    private final TodoRepository todoRepository = new TodoRepository();


    public void insertTodo(Todo addTodo) {
        int lastTodoNo = todoRepository.selectLastNumber();
        addTodo.setNo(lastTodoNo+1);

        int result = todoRepository.insertTodo(addTodo);
        if(result == 1) {
            System.out.println("일정 생성이 완료되었습니다.");
        }
    }

    public void selectAllTodo(int type) {
        ArrayList<Todo> allTodoList = todoRepository.selectAllTodo();
        List<Todo> endList = null;
        switch (type){
            case 0: endList = allTodoList; break;
            case 1:
                endList = allTodoList.stream()
                        .sorted(Comparator.comparing(Todo::getEndDate).reversed())
                        .collect(Collectors.toList());
                break;
            case 2:
                endList = allTodoList.stream()
                        .sorted(Comparator.comparing(Todo::getImportance).reversed())
                        .collect(Collectors.toList());
                break;
        }

        for(Todo todo : endList) {
            System.out.println(todo);
        }
    }
}
