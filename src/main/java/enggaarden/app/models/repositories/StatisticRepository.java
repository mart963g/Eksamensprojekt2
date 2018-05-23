package enggaarden.app.models.repositories;

import enggaarden.app.models.interfaces.StatisticRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticRepository implements StatisticRepositoryInterface
{
    /*
    Fields
     */
    @Autowired
    private JdbcTemplate jdbc;

    // All statistics from the db
    @Override
    public SqlRowSet get()
    {
        String sql = "SELECT * FROM statistics;";

        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        return rowSet;
    }

    // Sum from db
    @Override
    public double getSum()
    {
        String sql = "SELECT ROUND(sum, 0) FROM sumOfAmount;";

        SqlRowSet rs = jdbc.queryForRowSet(sql);

        rs.next();

        return rs.getDouble(1);
    }
}