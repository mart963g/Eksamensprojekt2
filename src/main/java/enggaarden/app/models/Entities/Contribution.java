package enggaarden.app.models.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contribution
{
    /*
    Fields
     */
    private int contributionId;
    private ActivityType activity;
    private double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;

    /*
    Constructors
     */
    public Contribution() {}

    public Contribution(int contributionId, ActivityType activity, double amount, Date paymentDate)
    {
        this.contributionId = contributionId;
        this.activity = activity;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    /*
    Getters
    */
    public ActivityType getActivity()
    {
        return activity;
    }
    public double getAmount()
    {
        return amount;
    }
    public Date getPaymentDate()
    {
        return paymentDate;
    }
    public String getSqlDate()
    {
        DateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd");
        return correctFormat.format(this.paymentDate);
    }
    public int getContributionId()
    {
        return contributionId;
    }

    /*
    Setters
    */
    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }
    public void setActivity(ActivityType activity)
    {
        this.activity = activity;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    public void setContributionId(int contributionId)
    {
        this.contributionId = contributionId;
    }
}