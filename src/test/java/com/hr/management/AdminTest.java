/*
 * import static org.assertj.core.api.Assertions.assertThat;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.autoconfigure.domain.EntityScan; import
 * org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
 * import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 * 
 * import com.hr.management.entity.Admin; import
 * com.hr.management.repository.AdminRepository;
 * 
 * @DataJpaTest
 * 
 * @EntityScan(basePackages = "com.hr.management.entity")
 * 
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 * public class AdminTest {
 * 
 * private final AdminRepository adminRepo;
 * 
 * @Autowired public AdminTest(AdminRepository adminRepo) { this.adminRepo =
 * adminRepo; }
 * 
 * @Test public void checkIfEmailExists() { // Your test logic here String email
 * = "shaheda@gmail.com";
 * 
 * Admin admin = new Admin("Jaminla", email); adminRepo.save(admin);
 * 
 * boolean expected = adminRepo.isEmailExists(email);
 * assertThat(expected).isTrue(); } }
 */