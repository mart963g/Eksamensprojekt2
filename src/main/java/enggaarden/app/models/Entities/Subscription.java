package enggaarden.app.models.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Subscription {

    /*
    Fields
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDay;
    private String isPaid;


    /*
    Constructors
     */
    public Subscription()
    {
    }
    public Subscription(Date payDay)
    {
        this.payDay = payDay;

        if (this.payDay == null) this.isPaid = "❌";
        else this.isPaid = "✔️";
    }


    /*
    Getters
     */
    public Date getPayDay()
    {
        return this.payDay;
    }
    public String getSqlDate()
    {
        if (this.payDay == null)
        {
            return null;
        }
        else
        {
            DateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd");
            return correctFormat.format(this.payDay);
        }
    }
    public String getIsPaid()
    {
        return isPaid;
    }

    /*
    Setters
     */
    public void setPayDay(Date payDay)
    {
        this.payDay = payDay;
    }
    public void setIsPaid(String isPaid)
    {
        this.isPaid = isPaid;
    }
}
