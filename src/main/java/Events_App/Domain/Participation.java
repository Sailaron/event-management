package Events_App.Domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "participation")
public class Participation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @Fetch(FetchMode.JOIN)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    @Fetch(FetchMode.JOIN)
    private Visitor visitor;

    private String status; // No, Null, Yes

    public Participation(Visitor visitor, Event event) {
        this.visitor = visitor;
        this.event = event;
        this.status = "Null";
    }

    public Participation(Visitor visitor, Event event, String status) {
        this.visitor = visitor;
        this.event = event;
        this.status = status;
    }

    public Participation() {
        this.status = "Null";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participation participation = (Participation) o;
        return Objects.equals(id, participation.id) &&
                Objects.equals(event, participation.event) &&
                Objects.equals(visitor, participation.visitor) &&
                Objects.equals(status, participation.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, visitor, status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"visitor\":\"" + visitor.getId() + '\"' +
                ", \"event\":\"" + event.getId() + '\"' +
                ", \"status\":\"" + status + '\"' +
                '}';
    }

}
