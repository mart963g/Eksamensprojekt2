package enggaarden.app.models.repositories;

import enggaarden.app.models.Entities.Member;
import enggaarden.app.models.Entities.SubLog;
import enggaarden.app.models.interfaces.SubLogRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class SubLogRepository implements SubLogRepositoryInterface
{
    /*
    Fields
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Returns all subLogs from the db for subLogs_overview.html
    @Override
    public SqlRowSet getSubLogs()
    {
        String sql = "SELECT * FROM logView ORDER BY subLogId DESC;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        return rowSet;
    }

    // Post a new log to the db. !!Has bugs!!
    @Override
    public void post(Date date, Member member, String username)
    {
        DateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd");
        SubLog subLog = new SubLog();

        //  check for change
        if (!date.equals(member.getSubscription().getPayDay()))
        {
            if (member.getSubscription().getPayDay() == null)
            {
                subLog.setEdit("Status sat til ikke betalt");
            }
            else
            {
                subLog.setEdit("Status sat til betalt");
            }

            String sql = "INSERT INTO subLogs " +
                    "VALUES (DEFAULT, '" + subLog.getEdit() + "', '" + correctFormat.format(new Date()) +
                    "', " + member.getMemberId() +
                    ", '" + username + "');";

            jdbcTemplate.update(sql);
        }
    }

    // Clears all subLog entries in the db
    @Override
    public void clearLog()
    {
        String sql = "DELETE FROM subLogs;";

        jdbcTemplate.update(sql);
    }
}
