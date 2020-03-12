package cl.aligare.entity;

import lombok.Data;

@Data
public class Address
{
    public String street;
    public String number;
    public String appartmentNumber;
    public String detail;
    public String country;
    public String state;
    public String city;
    public String municipality;
    public int postalcode;
}

