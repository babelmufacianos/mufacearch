package muface.arch.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArqRepository<T, ID> extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID> {


}



