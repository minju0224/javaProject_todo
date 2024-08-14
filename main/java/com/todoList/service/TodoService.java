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

    public void searchTodoList(String title) {
        List<Todo> sTodo = todoRepository.selectTodo(title);
        if (sTodo == null || sTodo.isEmpty()) {
            System.out.println("입력하신 제목을 포함하는 일정이 없습니다.");
        } else {
            for (Todo todo : sTodo) {
                System.out.println(todo);
            }
        }
    }

    public Todo searchOneTodo(String title) {
        Todo todo = todoRepository.selectOneTodo(title);
        if(todo != null){
            todo.setNo(todo.getNo());
            todo.setTitle(todo.getTitle());
            todo.setContent(todo.getContent());
            todo.setStartDate(todo.getStartDate());
            todo.setEndDate(todo.getEndDate());
            todo.setStatus(todo.getStatus());
            todo.setStatus(todo.getStatus());

            return todo;
        }
        System.out.println("입력하신 제목의 일정이 없습니다.");
        return null;
    }

    public void updateTodo(Todo reform) {
        todoRepository.modifyTodo(reform);
    }
}
