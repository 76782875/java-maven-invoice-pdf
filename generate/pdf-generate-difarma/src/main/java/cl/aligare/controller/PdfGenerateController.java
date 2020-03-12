
package cl.aligare.controller;

import java.io.IOException;

import java.util.logging.Logger;

import cl.aligare.entity.AllObjectsInvoice;
import cl.aligare.service.ParseObjectsToJson;
import cl.aligare.service.GenerateInvoice;
import cl.aligare.service.GenerateNoteCredit;

public class PdfGenerateController {

    GenerateInvoice generateInvoiceInstance = new GenerateInvoice();
    GenerateNoteCredit generateNoteCreditInstance = new GenerateNoteCredit();
    ParseObjectsToJson parseObjectsToJsonInstance = new ParseObjectsToJson();
    private static final Logger LOGGER = Logger.getLogger(PdfGenerateController.class.getName());

    public byte[] generateInvoice(String jsonobject) throws IOException {

        AllObjectsInvoice allObjectsInvoice = parseObjectsToJsonInstance.getObjectsInvoice(jsonobject);
        LOGGER.info("[PdfGenerateController.generateInvoice]Iniciando parseo...");
        if (null != allObjectsInvoice) {
            return generateInvoiceInstance.generate(allObjectsInvoice);
        }
        return generateInvoiceInstance.generate(allObjectsInvoice);
    }

    public byte[] generateCreditNote(String jsonobject) throws IOException {
        AllObjectsInvoice allObjectsInvoice = parseObjectsToJsonInstance.getObjectsInvoice(jsonobject);
        LOGGER.info("[PdfGenerateController.generateCreditNote]Iniciando parseo...");
        if (null != allObjectsInvoice) {
            return generateNoteCreditInstance.generate(allObjectsInvoice);
        }
        return generateNoteCreditInstance.generate(allObjectsInvoice);
    }

}
