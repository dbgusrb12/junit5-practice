package com.hg.junit5practice.junit5;

public class Study {

    private StudyStatus status;

    private int limit;

    private String name;

    public Study() {
    }

    public Study(StudyStatus status) {
        this.status = status;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야합니다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야합니다.");
        }
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
            "status=" + status +
            ", limit=" + limit +
            ", name='" + name + '\'' +
            '}';
    }
}

