package enggaarden.app.models.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubLog
{
    /*
    fields
     */
    private String memberName;
    private String edit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date editDate;
    private int memberId;
    private String username;

    /*
    Constructors
     */
    public SubLog() {}
    public SubLog(String edit, Date editDate, int memberId, String username)
    {
        this.edit = edit;
        this.editDate = editDate;
        this.memberId = memberId;
        this.username = username;
    }
    public SubLog(String memberName, String edit, Date editDate, String username)
    {
        this.memberName = memberName;
        this.edit = edit;
        this.editDate = editDate;
        this.username = username;
    }

    /*
    getters
     */
    public String getEdit()
    {
        return this.edit;
    }
    public Date getEditDate()
    {
        return this.editDate;
    }
    public int getMemberId()
    {
        return this.memberId;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getMemberName()
    {
        return memberName;
    }
    public String getSqlDate()
    {
        if (this.editDate == null)
        {
            return null;
        }
        else
        {
            DateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd");
            return correctFormat.format(this.editDate);
        }
    }

    /*
    setters
     */
    public void setEdit(String edit)
    {
        this.edit = edit;
    }
    public void setEditDate(Date editDate)
    {
        this.editDate = editDate;
    }
    public void setMemberId(int memberId)
    {
        this.memberId = memberId;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }
}
