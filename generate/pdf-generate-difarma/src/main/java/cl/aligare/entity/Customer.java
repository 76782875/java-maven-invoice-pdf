package cl.aligare.entity;
import lombok.Data;

@Data
public class Customer
{
    public String customerId;
    public int foreignFlag;
    public String firstName;
    public String middleName;
    public String lastName;
    public String email;
    public int emailFlag;
    public String phoneNumber1;
    public String phoneNumber2;
    public int isGuest;
    public String gender;
    public String birthdate;
    public Address address;
}

