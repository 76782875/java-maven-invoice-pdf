package cl.aligare.service.interfaces;

import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;


public interface InvoiceInterface {
    public Image getImageForPdfFromBase64(String base64);
    public Paragraph generateHeader();
    public Table generateBody();

}