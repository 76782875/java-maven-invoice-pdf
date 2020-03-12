package cl.aligare.entity;
import java.util.List;
import lombok.Data;

@Data
public class ItemSale
{
    public int itemsQuantity;
    public int unitsQuantity;
    public List<Item> item;
}

