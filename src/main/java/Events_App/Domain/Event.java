package Events_App.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String date;

    private String time;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Participation> participation = new ArrayList<>();

    public Event(String name, String date) {
        this.date = date;
        this.name = name;
        this.time = "";
    }
    public Event(String name, String date, String time) {
        this.date = date;
        this.name = name;
        this.time = time;
    }
    public Event(String name, String date, String time, List<Participation> participation) {
        this.date = date;
        this.name = name;
        this.time = time;
        this.participation = participation;
    }

    public Event() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time) &&
                Objects.equals(participation, event.participation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, participation);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Participation> getParticipation() {
        return participation;
    }

    public void setParticipation(List<Participation> participation) {
        this.participation = participation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"date\":\"" + date + '\"' +
                ", \"time\":\"" + time + '\"' +
                ", \"participation\":" + participation + "" +
                '}';
    }
}
