package enggaarden.app.models.interfaces;

import enggaarden.app.models.Entities.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface UserRepositoryInterface
{
    void postUser(User user);
    SqlRowSet get();
    SqlRowSet get(String username);
    void delete(String username);
    List<String> getUserTypes();
}
