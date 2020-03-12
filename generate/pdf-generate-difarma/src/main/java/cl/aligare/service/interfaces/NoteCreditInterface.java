package cl.aligare.service.interfaces;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;




public interface NoteCreditInterface {
    
    public Paragraph generateHeader();
    public Table generateBody();
    
}