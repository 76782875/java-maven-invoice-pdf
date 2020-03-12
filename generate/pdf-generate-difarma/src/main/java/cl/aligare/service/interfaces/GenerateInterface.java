package cl.aligare.service.interfaces;

import java.io.IOException;


import cl.aligare.entity.AllObjectsInvoice;

public interface GenerateInterface {
   public byte[] generate(AllObjectsInvoice allObjectsInvoice) throws IOException;
}
