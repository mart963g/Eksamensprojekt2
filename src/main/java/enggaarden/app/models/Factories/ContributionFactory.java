package enggaarden.app.models.Factories;

import enggaarden.app.models.Entities.ActivityType;
import enggaarden.app.models.Entities.Contribution;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class ContributionFactory
{
    /*
    Methods
     */

    // Returns a list of contributions for contributions_overview.html
    public List<Contribution> getContributions(SqlRowSet rowSet)
    {
        ArrayList<Contribution> contributions = new ArrayList<>();
        while(rowSet.next())
        {
            contributions.add(new Contribution(rowSet.getInt(1),
                    ActivityType.valueOf(rowSet.getString(2)),
                    rowSet.getDouble(3),rowSet.getDate(4)));
        }
        return contributions;
    }

    // Returns a single contribution
    public Contribution getContribution(SqlRowSet rowSet)
    {
        rowSet.next();

        return new Contribution(rowSet.getInt(1),
                ActivityType.valueOf(rowSet.getString(2)),
                rowSet.getDouble(3),rowSet.getDate(4));
    }

    // Constructs an empty contribution
    public Contribution createContribution()
    {
        return new Contribution();
    }
}
