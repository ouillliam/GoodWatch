package goodwatch.app.director;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    
    ArrayList<Director> findAll();
    
    Optional<Director> findByDirectorID(Long id);
}
