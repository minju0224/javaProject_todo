package com.todoList.run;

import com.todoList.aggregate.StateType;
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
                case 3: todoService.insertTodo(addTodo()); break;
                case 5: todoService.deleteTodo(chooseNum()); break;
                case 6: chooseUpdate(); break;
                case 9:
                    System.out.println("프로그램을 종료합니다."); return;
            }
        }
    }

    private static int chooseNum() {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제하고자 하는 번호를 입력하세요.");
        return sc.nextInt();
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
    private static void chooseUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("상태를 변경하고 싶은 일정 번호를 입력하세요: ");
        int num = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기!
        /* nextInt(), nextDouble(), nextLong() 등의 메서드는 숫자 데이터를 읽은 후,
         줄 바꿈 문자를 포함하지 않음. 개행 문자(\n)는 여전히 버퍼에 남게 되는 것을 방지 */

        System.out.print("새로운 상태를 입력하세요 (진행전, 진행후, 완료): ");
        String newStatus = sc.nextLine();

        try {
            StateType status = StateType.valueOf(newStatus);
            todoService.updateStatus(num, status);
        } catch (IllegalArgumentException e) {
            System.out.println("올바르지 않은 상태입니다. 진행전, 진행후, 완료 중에서 선택하세요.");
        }
    }
}

