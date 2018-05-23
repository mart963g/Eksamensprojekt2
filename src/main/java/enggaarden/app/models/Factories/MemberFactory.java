package enggaarden.app.models.Factories;

import enggaarden.app.models.Entities.Member;
import enggaarden.app.models.Entities.MemberType;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.util.ArrayList;
import java.util.List;

public class MemberFactory
{
    // List for members_overview.html
    public List<Member> getMembers(SqlRowSet rowSet)
    {
        ArrayList<Member> members = new ArrayList<>();

        while (rowSet.next())
        {
            members.add(new Member(rowSet.getInt(1), rowSet.getString(2), rowSet.getString(3),
                    rowSet.getString(4), rowSet.getInt(5), rowSet.getDate(6),
                    rowSet.getDate(9), MemberType.valueOf(rowSet.getString(7)), rowSet.getString(8)));
        }
        return members;
    }

    // Full member, for member_details.html
    public Member getMember(SqlRowSet rowSet)
    {
        rowSet.next();

        return new Member(rowSet.getInt(1), rowSet.getString(2), rowSet.getString(3),
                rowSet.getString(4), rowSet.getInt(5), rowSet.getDate(6),
                rowSet.getString(9), rowSet.getInt(10), rowSet.getString(11),
                rowSet.getDate(12),
                MemberType.valueOf(rowSet.getString(7)), rowSet.getString(8));
    }

    // Create an empty member
    public Member createMember()
    {
        return new Member();
    }

    // Create a list with board options for create and edit.html
    public List<String> getBoardOptions()
    {
        List<String> boardOptions = new ArrayList<>();
        boardOptions.add("Nej");
        boardOptions.add("Ja");
        return boardOptions;
    }
}
