package enggaarden.app.models.interfaces;

import enggaarden.app.models.Entities.Member;
import enggaarden.app.models.Entities.SubLog;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Date;

public interface SubLogRepositoryInterface
{
    SqlRowSet getSubLogs();
    void post(Date date, Member member, String username);
    void clearLog();
}
