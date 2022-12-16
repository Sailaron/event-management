package Events_App.Domain;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ParticipationRepository extends CrudRepository<Participation, Integer> {
    Participation findByEventAndVisitor(Event event, Visitor visitor);
}
