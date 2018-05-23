package enggaarden.app.models.repositories;

import enggaarden.app.models.interfaces.EmailRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository implements EmailRepositoryInterface
{
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public String emailForPrimary()
    {
        String emails = "";
        String sql = "SELECT mail FROM members WHERE memberType = 'Primær'" +
                     " AND CHAR_LENGTH(mail) > 0 AND mail IS NOT NULL;";

        SqlRowSet rs = jdbc.queryForRowSet(sql);

        while (rs.next())
        {
            emails += rs.getString(1) + ";";
        }
        return emails;
    }

    @Override
    public String emailForSecondary()
    {
        String emails = "";
        String sql = "SELECT mail FROM members WHERE memberType = 'Sekundær'" +
                     " AND CHAR_LENGTH(mail) > 0 AND mail IS NOT NULL;";

        SqlRowSet rs = jdbc.queryForRowSet(sql);

        while (rs.next())
        {
            emails += rs.getString(1) + ";";
        }
        return emails;
    }

    @Override
    public String emailForExternal()
    {
        String emails = "";
        String sql = "SELECT mail FROM members WHERE memberType = 'Ekstern'" +
                     " AND CHAR_LENGTH(mail) > 0 AND mail IS NOT NULL;";

        SqlRowSet rs = jdbc.queryForRowSet(sql);

        while (rs.next())
        {
            emails += rs.getString(1) + ";";
        }
        return emails;
    }

    @Override
    public String emailForAll()
    {
        String emails = "";
        String sql = "SELECT mail FROM members WHERE " +
                     "CHAR_LENGTH(mail) > 0 AND mail IS NOT NULL;";

        SqlRowSet rs = jdbc.queryForRowSet(sql);

        while (rs.next())
        {
            emails += rs.getString(1) + ";";
        }
        return emails;
    }

    @Override
    public String emailForBoard()
    {
        String emails = "";
        String sql = "SELECT mail FROM members WHERE " +
                "CHAR_LENGTH(mail) > 0 AND mail IS NOT NULL " +
                "AND isBoard LIKE 'j%';";

        SqlRowSet rs = jdbc.queryForRowSet(sql);

        while (rs.next())
        {
            emails += rs.getString(1) + ";";
        }
        return emails;
    }
}
