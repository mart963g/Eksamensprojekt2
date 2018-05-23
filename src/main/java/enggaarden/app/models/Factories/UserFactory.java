package enggaarden.app.models.Factories;

import enggaarden.app.models.Entities.User;
import enggaarden.app.models.Entities.UserType;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class UserFactory
{
    // List for user_overview.html
    public List<User> getUsers(SqlRowSet rowSet)
    {
        ArrayList<User> users = new ArrayList<>();

        while(rowSet.next())
        {
            users.add(new User(rowSet.getString(1),rowSet.getString(2),UserType.valueOf(rowSet.getString(3))));
        }

        return users;
    }

    // Returns a single user
    public User getUser(SqlRowSet rowSet)
    {
        rowSet.next();
        User value = new User(rowSet.getString(1),rowSet.getString(2),UserType.valueOf(rowSet.getString(3)));
        return value;
    }

    // Constructs and returns a new user
    public User createUser()
    {
        return new User();
    }
}
