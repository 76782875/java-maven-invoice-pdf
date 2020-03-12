package cl.aligare.entity;
import lombok.Data;

@Data
public class TrxHdr
{
    public String version;
    public Context context;
    public String trxClientExecDate;
}

