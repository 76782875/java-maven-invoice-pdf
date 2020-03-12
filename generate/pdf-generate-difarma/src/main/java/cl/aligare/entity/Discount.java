package cl.aligare.entity;
import lombok.Data;

@Data
public class Discount
{
    public String promotionId;
    public int usedPoints;
    public int amount;
    public int loyaltyDiscount;
}

