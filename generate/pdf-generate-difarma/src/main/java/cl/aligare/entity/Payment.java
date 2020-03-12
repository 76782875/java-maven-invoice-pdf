package cl.aligare.entity;
import lombok.Data;

@Data
public class Payment
{
    public int state;
    public PaymentData paymentData;
}

