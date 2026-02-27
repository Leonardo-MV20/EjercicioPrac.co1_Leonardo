package Leonardo_Moscoa.Ejercicio_Prac.repository;

import Leonardo_Moscoa.Ejercicio_Prac.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
