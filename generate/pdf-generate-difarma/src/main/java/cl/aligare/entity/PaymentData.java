package cl.aligare.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class PaymentData
{
    public int connectionType;
    public int paymentType;
    public String currencyCode;
    public AmountInfo amountInfo ;
    public String uniqueNumberTrx;
    public String authorizationCode;
    public String cardNumber;
    public int bin;
    @JsonProperty("IdHolder")
    public String idHolder;
    public String commerceCode;
}

