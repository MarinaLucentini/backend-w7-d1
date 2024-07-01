package marinalucentini.backend_w7_d1.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
Optional<Employee> findByUsername (String username);
Optional<Employee> findByEmail(String email);
//boolean existByEmail(String email);

}
