package com.todoList.run;

import com.todoList.aggregate.Todo;
import com.todoList.service.TodoService;

import java.time.LocalDate;
import java.util.Date;
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
                case 3: todoService.insertTodo(addTodo()); break;
                case 9:
                    System.out.println("프로그램을 종료합니다."); return;
            }
        }
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
}
