package com.todoList.repository;

import com.todoList.aggregate.StateType;
import com.todoList.aggregate.Todo;
import com.todoList.stream.MyObjectOutputStream;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TodoRepository {
    private final ArrayList<Todo> todoList = new ArrayList<>();
    private static final String FILE_PATH = "src/main/java/com/todoList/db.todoDb.dat";
    public TodoRepository() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            ArrayList<Todo> todos = new ArrayList<>();
            todos.add(new Todo(0,"제목", "내용", LocalDate.now(), LocalDate.now(), StateType.완료, 5));
            saveTodos(file,todos);
        }
        loadTodos(file);
    }



    private void saveTodos(File file, ArrayList<Todo> todos) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for(Todo todo: todos) {
                oos.writeObject(todo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTodos(File file) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while(true){
                todoList.add((Todo)ois.readObject());
            }
        } catch (EOFException e){
            System.out.println("일정 목록을 모두 로딩하였습니다.");
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastNumber() {
        Todo lastTodo = todoList.get(todoList.size()-1);
        return lastTodo.getNo();
    }

    public int insertTodo(Todo addTodo) {
        int result = 0;
        try(MyObjectOutputStream moos = new MyObjectOutputStream(new FileOutputStream(FILE_PATH,true))){
            moos.writeObject(addTodo);
            todoList.add(addTodo);
            result = 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ArrayList<Todo> selectAllTodo() {
        return todoList;
    }
}
