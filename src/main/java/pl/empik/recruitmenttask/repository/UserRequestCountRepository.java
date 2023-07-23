package pl.empik.recruitmenttask.repository;

import org.springframework.data.repository.CrudRepository;
import pl.empik.recruitmenttask.model.UserRequestCount;

public interface UserRequestCountRepository extends CrudRepository<UserRequestCount, String> {

}
