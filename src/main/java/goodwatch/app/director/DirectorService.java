package goodwatch.app.director;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    
    @Autowired
    DirectorRepository directorRepository;

    public ArrayList<Director> findAllDirector(){
        return directorRepository.findAllByOrderByNameAsc();
    }

    public Optional<Director> getDirectorById(long id){
        return directorRepository.findByDirectorID(id);
    }

    public void saveDirector(Director director){
        directorRepository.save(director);
    }

    public void deleteDirector(long directorID){
        directorRepository.deleteById(directorID);
    }
}
