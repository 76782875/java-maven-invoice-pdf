package cl.aligare.entity;
import lombok.Data;

@Data
public class Invoice
{
    public String creationTime;
    public int documentType;
    public String currencyCode;
    public Executor executor;
    public AmountInfo amountInfo;
    public Customer customer;
    public ItemSale itemSale;
    public PaymentGroups paymentGroups;
}

