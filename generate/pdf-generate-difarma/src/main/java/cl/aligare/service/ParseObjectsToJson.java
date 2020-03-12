package cl.aligare.service;

import java.util.logging.Logger;

import com.google.gson.Gson;

import cl.aligare.entity.AllObjectsInvoice;
import cl.aligare.service.interfaces.ParseObjectsToJsonInterface;


public class ParseObjectsToJson implements ParseObjectsToJsonInterface {

    private static final Logger LOGGER = Logger.getLogger(ParseObjectsToJson.class.getName());
    @Override
    public AllObjectsInvoice getObjectsInvoice(String jsonObjects) {
        LOGGER.info("[ParseObjectsToJson.AllObjectsInvoice] -> parseando Json...");
        Gson g = new Gson();
        AllObjectsInvoice a = new AllObjectsInvoice();
        a = g.fromJson(jsonObjects, AllObjectsInvoice.class);
        return a;
    }

}