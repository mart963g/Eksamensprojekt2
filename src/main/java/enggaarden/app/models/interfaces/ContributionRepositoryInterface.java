package enggaarden.app.models.interfaces;

import enggaarden.app.models.Entities.Contribution;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface ContributionRepositoryInterface
{
    SqlRowSet getContributions();
    SqlRowSet getContribution(int id);
    void postContribution(Contribution contribution);
    void deleteContribution(int id);
    List<String> getActivities();
}