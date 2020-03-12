package cl.aligare;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cl.aligare.controller.PdfGenerateController;
import cl.aligare.helper.Constant;

public class Main {

    public static void main(final String[] args) throws IOException {

        PdfGenerateController a = new PdfGenerateController();
        String json = Constant.JSON;

        File invoice = new File("invoice.pdf");
        File noteCredit = new File("noteCredit.pdf");
        FileOutputStream fosInvoice = new FileOutputStream(invoice);
        FileOutputStream fosNoteCredit = new FileOutputStream(noteCredit);

        fosInvoice.write(a.generateInvoice(json));
        fosNoteCredit.write(a.generateCreditNote(json));
        fosInvoice.flush();
        fosNoteCredit.flush();
        fosInvoice.close();
        fosNoteCredit.close();
    }

}