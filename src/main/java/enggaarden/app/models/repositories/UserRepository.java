package enggaarden.app.models.repositories;

import enggaarden.app.models.Entities.User;
import enggaarden.app.models.interfaces.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface
{
    @Autowired
    private JdbcTemplate jdbc;

    /*
    Methods
     */

    // Password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // Posts a new user to the db
    @Override
    public void postUser(User user)
    {
        String password = passwordEncoder().encode(user.getUserPassword());

        String sql = "INSERT INTO users VALUES('" +
                user.getUsername() + "', '" +
                password + "', '" +
                user.getUserType().toString() + "');";
        jdbc.update(sql);

    }

    // Returns a list with all user types from the db
    @Override
    public List<String> getUserTypes()
    {
        List<String> userTypes = new ArrayList<>();
        String sql = "SELECT * FROM userTypes ORDER BY userType DESC;";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        while (rowSet.next())
        {
            userTypes.add(rowSet.getString(1));
        }
        return userTypes;
    }

    // Returns a rowset with all users from the db
    @Override
    public SqlRowSet get()
    {
        String sql = "SELECT * FROM users WHERE username != 'Enggården';";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);
        return rowSet;
    }

    // Returns a rowset with a single user from the db
    @Override
    public SqlRowSet get(String username)
    {
        String sql = "SELECT * FROM users WHERE username='" + username + "';";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);
        return rowSet;
    }

    // Deletes the specified user from the db
    @Override
    public void delete(String username)
    {
        String sql = "DELETE FROM users WHERE username = '" + username + "';";
        jdbc.update(sql);
    }
}
