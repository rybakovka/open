package mapping;

import java.util.Objects;

public class Person {

    public String id;
    public String createdAt;
    public String name;
    public String job;

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
