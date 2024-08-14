package com.todoList.run;

import com.todoList.aggregate.Todo;
import com.todoList.service.TodoService;

import java.time.LocalDate;
import java.util.Scanner;

public class Application {

    private static final TodoService todoService = new TodoService();

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("===== 일정 관리 프로그램 =====");
            System.out.println("1. 모든 일정 조회");
            System.out.println("2. 일정 검색");
            System.out.println("3. 일정 추가");
            System.out.println("4. 일정 수정");
            System.out.println("5. 일정 삭제");
            System.out.println("6. 일정 상태 관리");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 선택 : ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: todoService.selectAllTodo(chooseType());; break;
                case 2: todoService.searchTodoList(chooseTitle()); break;
                case 3: todoService.insertTodo(addTodo()); break;
                case 4:
                    Todo selectedTodo = todoService.searchOneTodo(chooseTitle());
                    if (selectedTodo == null) { continue; }
                    todoService.updateTodo(reform(selectedTodo));
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다."); return;
                default:
                    System.out.println("올바른 값을 입력해주세요.");
            }
        }
    }

    private static String chooseTitle() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 일정 제목을 입력하십시오. : ");
        String title = sc.nextLine();
        return title;
    }

    private static int chooseType(){
        Scanner sc = new Scanner(System.in);
        System.out.print("일정 목록 조회 조건을 선택하십시오. (0 = 조건 없음, 1 = 마감일 순, 2 = 중요도 순) : ");
        int choice = sc.nextInt();
        return choice;
    }

    private static Todo addTodo(){
        Todo newTodo = null;
        Scanner sc = new Scanner(System.in);

        System.out.print("일정 제목을 입력하십시오. : ");
        String title = sc.nextLine();

        System.out.print("일정 내용을 입력하십시오. : ");
        String content = sc.nextLine();

        System.out.print("일정 시작 날짜를 입력하십시오.(yyyy-MM-dd 양식으로 작성) : ");
        String startStr = sc.nextLine();
        LocalDate startDate = LocalDate.parse(startStr);

        System.out.print("일정 시작 날짜를 입력하십시오.(yyyy-MM-dd 양식으로 작성) : ");
        String endStr = sc.nextLine();
        LocalDate endDate = LocalDate.parse(endStr);

        System.out.print("중요도를 입력하시오.(1 ~ 5 사이의 자연수값으로 작성) : ");
        int importance = sc.nextInt();

        newTodo = new Todo(title,content,startDate,endDate,importance);

        return newTodo;
    }
    private static Todo reform(Todo selected){
        Todo todo = selected;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("==== 일정 " +todo.getTitle()+ "수정 ====");
            System.out.println("1. 제목 수정");
            System.out.println("2. 내용 수정");
            System.out.println("3. 시작일 수정");
            System.out.println("4. 마감일 수정");
            System.out.println("5. 중요도 수정");
            System.out.println("9. 수정 종료");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("수정할 제목을 입력하십시오. : ");
                    todo.setTitle(sc.nextLine());
                    break;
                case 2:
                    System.out.print("수정할 내용을 입력하십시오. : ");
                    todo.setContent(sc.nextLine());
                    break;
                case 3:
                    System.out.print("수정할 시작일을 입력하십시오. : ");
                    todo.setStartDate(LocalDate.parse(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("수정할 마감일을 입력하십시오. : ");
                    todo.setEndDate(LocalDate.parse(sc.nextLine()));
                    break;
                case 5:
                    System.out.print("수정할 중요도을 입력하십시오. : ");
                    todo.setImportance(sc.nextInt());
                    break;
                case 9:
                    System.out.println("메인메뉴로 돌아갑니다.");
                    return selected;
                default:
                    System.out.println("올바른 값을 입력해 주세요.");
            }
        }
    }
}
