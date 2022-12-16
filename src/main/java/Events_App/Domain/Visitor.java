package Events_App.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "visitors")
public class Visitor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;
    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    private List<Participation> participation = new ArrayList<>();

    public Visitor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public Visitor(String name, String surname, List<Participation> participation) {
        this.name = name;
        this.surname = surname;
        this.participation = participation;
    }

    public Visitor() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor employee = (Visitor) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(participation, employee.participation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, participation);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }

    public List<Participation> getParticipation() {
        return participation;
    }

    public void setParticipation(List<Participation> participation) {
        this.participation = participation;
    }
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"surname\":\"" + surname + '\"' +
                '}';
    }
}
