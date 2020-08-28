package com.mapping;

import java.util.Objects;

/**
 * Модель данных
 * @author Константин Рыбаков
 */
public class Person {

    private String id;
    private String createdAt;
    private String name;
    private String job;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                job.equals(person.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, job);
    }
}
