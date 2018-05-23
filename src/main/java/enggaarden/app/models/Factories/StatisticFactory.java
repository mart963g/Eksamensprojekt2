package enggaarden.app.models.Factories;

import enggaarden.app.models.Entities.ActivityType;
import enggaarden.app.models.Entities.Statistic;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class StatisticFactory
{

    // List for statistic_overview.html
    public List<Statistic> statistics (SqlRowSet rowSet)
    {
        List<Statistic> statistics = new ArrayList<>();

        while (rowSet.next())
        {
            statistics.add(new Statistic(
                    ActivityType.valueOf(rowSet.getString(1)), (int)rowSet.getDouble(2),
                    rowSet.getInt(3), (int)rowSet.getDouble(4), (int)rowSet.getDouble(5)));
        }

        return statistics;
    }
}