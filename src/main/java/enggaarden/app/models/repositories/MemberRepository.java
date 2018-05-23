package enggaarden.app.models.repositories;

import enggaarden.app.models.Entities.Member;
import enggaarden.app.models.interfaces.MemberRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository implements MemberRepositoryInterface
{
    /*
    Fields
     */
    @Autowired
    private JdbcTemplate jdbc;

    /*
    Methods
     */

    // Members for members_overview.html
    @Override
    public SqlRowSet getMember()
    {
        String sql = "SELECT * FROM partialMember ORDER BY memberId DESC;";

        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        return rowSet;
    }

    // Full member for member_details.html
    @Override
    public SqlRowSet getMember(int id)
    {
        String sql = "SELECT * FROM Members m " +
                "JOIN addresses a USING(memberId)" +
                "JOIN subscriptions s USING(memberId)" +
                " WHERE MemberId = " + id + ";";

        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        return rowSet;
    }

    // Returns all member types from the db
    @Override
    public List<String> getMemberTypes()
    {
        List<String> memberTypes = new ArrayList<>();
        String sql = "SELECT * FROM memberTypes;";
        SqlRowSet rowSet = jdbc.queryForRowSet(sql);

        while(rowSet.next())
        {
            memberTypes.add(rowSet.getString(1));
        }
        return memberTypes;
    }

    // Posting a full member to the db
    @Override
    public void postMember(Member member)
    {
        String count = "SELECT MAX(memberId) FROM Members";
        SqlRowSet rowSet = jdbc.queryForRowSet(count);
        rowSet.next();
        int i = rowSet.getInt(1);

        String sqlMem = "INSERT INTO members VALUES" +
                "(" + (i+1) + ", '" +
                member.getFirstName() + "', '" +
                member.getLastName() + "', '" +
                member.getMail() + "', " +
                member.getPhoneNumber() + ", '" +
                member.getSqlDate() + "', '" +
                member.getMemberType().toString() + "', '" +
                member.getIsBoard() + "');";

        jdbc.update(sqlMem);


        String sqlAdd = "INSERT INTO addresses VALUES(" +
                (i+1) + ", '" +
                member.getAddress().getStreet() + "', " +
                member.getAddress().getZipCode() + ", '" +
                member.getAddress().getCity() + "');";

        jdbc.update(sqlAdd);

        if (member.getSubscription().getSqlDate() == null)
        {
            String sqlSub = "INSERT INTO subscriptions VALUES(" +
                    (i+1) + ", " +
                    member.getSubscription().getSqlDate() + ")";

            jdbc.update(sqlSub);
        }
        else
        {
            String sqlSub = "INSERT INTO subscriptions VALUES(" +
                    (i+1) + ", '" +
                    member.getSubscription().getSqlDate() + "');";

            jdbc.update(sqlSub);
        }
    }

    // Posting an updated member to the db
    @Override
    public void updateMember(Member member)
    {
       String sqlMem = "UPDATE members " +
                "SET firstName = '" + member.getFirstName() + "', " +
                "lastName = '" + member.getLastName() + "', " +
                "mail = '" + member.getMail() + "', " +
                "phoneNumber = " + member.getPhoneNumber() + ", " +
                "creationDate = '" + member.getSqlDate() + "', " +
                "memberType = '" + member.getMemberType().toString() + "', " +
                "isBoard = '" + member.getIsBoard() + "' " +
                "WHERE memberId = " + member.getMemberId() + ";";

        jdbc.update(sqlMem);

        String sqlAdd = "UPDATE addresses " +
                "SET streetName = '" + member.getAddress().getStreet()  + "', " +
                "zipCode = " + member.getAddress().getZipCode()  + ", " +
                "city = '" + member.getAddress().getCity()  + "' " +
                "WHERE memberId = " + member.getMemberId() + ";";
        jdbc.update(sqlAdd);

        // Different syntax for null value dates
        if (member.getSubscription().getSqlDate() == null)
        {
            String sqlSub = "UPDATE subscriptions " +
                    "SET payDate = " + member.getSubscription().getSqlDate() +
                    " WHERE memberId = " + member.getMemberId() + ";";

            jdbc.update(sqlSub);
        }
        else
        {
            String sqlSub = "UPDATE subscriptions " +
                    "SET payDate = '" + member.getSubscription().getSqlDate() + "'" +
                    "WHERE memberId = " + member.getMemberId() + ";";

            jdbc.update(sqlSub);
        }
    }

    // Delete a member from the db
    @Override
    public void deleteMember(int id)
    {
        String sql = "DELETE FROM members WHERE memberId = " + id + ";";

        jdbc.update(sql);
    }

    // Reset all subscriptions to null
    @Override
    public void resetSubscriptions()
    {
        String sql = "UPDATE subscriptions " +
                "SET payDate = NULL " +
                "WHERE payDate IS NOT NULL;";
        jdbc.update(sql);
    }
}
