package cl.aligare.entity;
import lombok.Data;

@Data
public class AmountInfo
{
    public int amount;
    public int shippingAmount;
    public int shippingTax;
    public int taxAmount;
    public int discountAmount;
    public int total;
}

