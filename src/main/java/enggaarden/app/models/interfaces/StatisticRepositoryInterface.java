package enggaarden.app.models.interfaces;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface StatisticRepositoryInterface
{
    SqlRowSet get();
    double getSum();
}