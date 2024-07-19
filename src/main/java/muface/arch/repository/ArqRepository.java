package muface.arch.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArqRepository<T, ID> extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID> {


}


