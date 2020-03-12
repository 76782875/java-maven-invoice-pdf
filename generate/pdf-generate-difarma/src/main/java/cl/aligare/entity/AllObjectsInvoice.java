package cl.aligare.entity;

import lombok.Data;
@Data
public class AllObjectsInvoice{

    public TrxIdentify trxIdentify;
    public TrxHdr trxHdr;
    public TrxData trxData;
}