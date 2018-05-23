package enggaarden.app.models.repositories;

import enggaarden.app.models.Entities.Contribution;
import enggaarden.app.models.interfaces.ContributionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContributionRepository implements ContributionRepositoryInterface
{
    /*
    Fields
     */
    @Autowired
    private JdbcTemplate jdbc;

    /*
    Methods
     */

    // Contributions for contributions_overview.html
    @Override
    public SqlRowSet getContributions()
    {
        String sql = "SELECT * FROM contributions;";

        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        return rowSet;
    }

    // Constructs and returns a list of activity types from the db
    @Override
    public List<String> getActivities()
    {
        List<String> activityTypes = new ArrayList<>();
        String sql = "SELECT * FROM activityTypes;";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        while (rowSet.next())
        {
            activityTypes.add(rowSet.getString(1));
        }
        return activityTypes;
    }

    // Specific contribution for contribution_delete.html
    @Override
    public SqlRowSet getContribution(int id)
    {
        String sql = "SELECT * FROM contributions WHERE contributionId=" + id + ";";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        return rowSet;
    }

    // Posts a contribution to the db
    @Override
    public void postContribution(Contribution contribution)
    {
        String sql = "INSERT INTO contributions VALUES(" +
                "DEFAULT, '" +
                contribution.getActivity().toString() + "'," +
                contribution.getAmount() + ",'" +
                contribution.getSqlDate() + "');";
        jdbc.update(sql);
    }

    // Delete specified contribution from the db
    @Override
    public void deleteContribution(int id)
    {
        String sql = "DELETE FROM contributions WHERE contributionId = " + id + ";";

        jdbc.update(sql);
    }
}
