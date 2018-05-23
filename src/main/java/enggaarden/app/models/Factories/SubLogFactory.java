package enggaarden.app.models.Factories;

import enggaarden.app.models.Entities.SubLog;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class SubLogFactory
{

    // List with all subLogs for subLog_overview.html
    public List<SubLog> getSubLogs(SqlRowSet rowSet)
    {
        List<SubLog> subLogs = new ArrayList<>();

        while (rowSet.next())
        {
            subLogs.add(new SubLog(rowSet.getString(1), rowSet.getString(2),
                    rowSet.getDate(4), rowSet.getString(3)));
        }

        return subLogs;
    }
}
