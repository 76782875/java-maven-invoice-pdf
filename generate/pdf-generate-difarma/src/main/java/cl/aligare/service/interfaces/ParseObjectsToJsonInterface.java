package cl.aligare.service.interfaces;


import cl.aligare.entity.AllObjectsInvoice;

public interface ParseObjectsToJsonInterface {
   public AllObjectsInvoice getObjectsInvoice(String jsonObjects);
}