package cl.aligare.entity;
import java.util.List;
import lombok.Data;

@Data
public class Item
{
    public int itemId;
    public int sku;
    public String itemSaleName;
    public int isGift;
    public String giftMessage;
    public int departmentId;
    public String departmentName;
    public int typeSKU;
    public Cashier cashier;
    public List<Point> points;
    public List<Discount> discounts;
    public  AmountInfo amountInfo;
    public List<Tax> taxes;
    public Attributes attributes;
}

