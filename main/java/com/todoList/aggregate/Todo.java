package com.todoList.aggregate;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Todo implements Serializable {
    private int no; // 고유번호
    private String title; // 제목
    private String content; // 내용
    private Date startDate; // 시작날짜
    private Date endDate; // 마감날짜
    private StateType status; // 진행 상태
    private int importance; // 중요도

    @Override
    public String toString() {
        return "Todo{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", importance=" + importance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return no == todo.no && importance == todo.importance && Objects.equals(title, todo.title) && Objects.equals(content, todo.content) && Objects.equals(startDate, todo.startDate) && Objects.equals(endDate, todo.endDate) && status == todo.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, title, content, startDate, endDate, status, importance);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public StateType getStatus() {
        return status;
    }

    public void setStatus(StateType status) {
        this.status = status;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
