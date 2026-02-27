package Leonardo_Moscoa.Ejercicio_Prac.repository;

import Leonardo_Moscoa.Ejercicio_Prac.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
}
