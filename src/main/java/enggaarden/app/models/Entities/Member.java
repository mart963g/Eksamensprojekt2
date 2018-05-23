package enggaarden.app.models.Entities;

import org.springframework.format.annotation.DateTimeFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member
{
    /*
    Fields
     */
    private int memberId;
    private String firstName;
    private String lastName;
    private String mail;
    private int phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    public Address address;
    public Subscription subscription;
    private MemberType memberType;
    private String isBoard;

    /*
    Constructors
     */
    public Member()
    {
        this.address = new Address();
        this.subscription = new Subscription();
    }

    // Full Member for member_details.html
    public Member(int memberId, String firstName,
                  String lastName, String mail,
                  int phoneNumber, Date creationDate,
                  String street, int zipCode, String city,
                  Date payDay, MemberType memberType, String isBoard)
    {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.creationDate = creationDate;
        this.address = new Address(street,zipCode,city);
        this.subscription = new Subscription(payDay);
        this.memberType = memberType;
        this.isBoard = isBoard;
    }

    // Standalone member for post method
    public Member(int memberId, String firstName,
                  String lastName, String mail,
                  int phoneNumber, Date creationDate,
                  MemberType memberType, String isBoard)
    {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.creationDate = creationDate;
        this.memberType = memberType;
        this.isBoard = isBoard;

    }

    // Member for members_overview.html
    public Member(int memberId, String firstName,
                  String lastName, String mail,
                  int phoneNumber, Date creationDate,
                  Date payDay, MemberType memberType, String isBoard)
    {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.creationDate = creationDate;
        this.subscription = new Subscription(payDay);
        this.memberType = memberType;
        this.isBoard = isBoard;
    }


    /*
    Getters
     */
    public int getMemberId()
    {
        return memberId;
    }
    public String getLastName()
    {
        return lastName;
    }
    public int getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getMail()
    {
        return mail;
    }
    public Date getCreationDate()
    {
        return creationDate;
    }
    public Address getAddress()
    {
        return address;
    }
    public Subscription getSubscription()
    {
        return subscription;
    }
    public MemberType getMemberType()
    {
        return memberType;
    }
    public String getIsBoard()
    {
        return this.isBoard;
    }
    public String getSqlDate()
    {
        DateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd");
        return correctFormat.format(this.creationDate);
    }

    /*
    Setters
     */
    public void setMemberId(int memberId)
    {
        this.memberId = memberId;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }
    public void setAddress(Address address)
    {
        this.address = address;
    }
    public void setSubscription(Subscription subscription)
    {
        this.subscription = subscription;
    }
    public void setMemberType(MemberType memberType)
    {
        this.memberType = memberType;
    }
    public void setIsBoard(String board)
    {
        this.isBoard = board;
    }
}
