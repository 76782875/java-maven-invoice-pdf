package cl.aligare.entity;
import lombok.Data;

@Data
public class RootObject
{
    public TrxHdr trxHdr;
    public TrxIdentify trxIdentify;
    public TrxData trxData;
}