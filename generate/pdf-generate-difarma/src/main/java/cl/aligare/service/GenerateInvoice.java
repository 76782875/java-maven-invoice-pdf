package cl.aligare.service;

import static cl.aligare.helper.Constant.DEFAULT_HEIGHT;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_B;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_L;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_R;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_T;
import static cl.aligare.helper.Constant.DEFAULT_WITH;

import static cl.aligare.helper.Constant.IMG_LOGO_BASE64;
import static cl.aligare.helper.Constant.LEFT;
import static cl.aligare.helper.Constant.RIGHT;
import static com.itextpdf.io.font.FontConstants.COURIER;
import static com.itextpdf.io.font.FontConstants.COURIER_BOLD;
import static com.itextpdf.layout.property.TextAlignment.CENTER;
import static cl.aligare.helper.Comun.generateRowsSubTittleForTable;

import static cl.aligare.helper.Comun.generateTittleForTable;
import static cl.aligare.helper.Comun.getCellDefault;
import static cl.aligare.helper.Comun.getCellDefaultStyleForColSimple;
import static cl.aligare.helper.Comun.getFont;
import static cl.aligare.helper.Comun.givenList_shouldReturnARandomElement;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;



import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

import cl.aligare.entity.AllObjectsInvoice;
import cl.aligare.helper.Comun;
import cl.aligare.helper.Constant;
import cl.aligare.service.interfaces.GenerateInterface;
import cl.aligare.service.interfaces.InvoiceInterface;

public class GenerateInvoice implements GenerateInterface, InvoiceInterface {
    private static final Logger LOGGER = Logger.getLogger(GenerateInvoice.class.getName());
    Random a = new Random();
    AllObjectsInvoice allObjectsInvoice;

    @Override
    public byte[] generate(AllObjectsInvoice allObjectsInvoice) throws IOException {
        if (null == allObjectsInvoice) {
            throw new IllegalArgumentException("AllObjectsInvoice.class is null, pleace check out over json object");
        }
        this.allObjectsInvoice = allObjectsInvoice;

        LOGGER.info("generate invoice in memory");
     
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter aWriter = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(aWriter);
  

        Paragraph header = generateHeader();
        Table table = generateBody();
        Image image = getImageForPdfFromBase64(IMG_LOGO_BASE64);
    
        // .setBorder(new DashedBorder(Color.BLACK, 1))
        float hieght = (table.getNumberOfRows() * 17.5900f) + DEFAULT_HEIGHT;
        Document document = new Document(pdf, new PageSize(DEFAULT_WITH, hieght));
        document.setMargins(DEFAULT_MARGIN_T, DEFAULT_MARGIN_R, DEFAULT_MARGIN_B, DEFAULT_MARGIN_L);
        document.add(image);
        document.add(header);
        document.add(table);
        document.close();

        OutputStream os = aWriter.getOutputStream();
        baos.writeTo(os);
        return baos.toByteArray();
    }

    @Override
    public Image getImageForPdfFromBase64(String base64) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64.getBytes());
        ImageData imageData = ImageDataFactory.create(decodedBytes);
        return new Image(imageData);
    }

    @Override
    public Paragraph generateHeader() {
        Paragraph header = new Paragraph();
        header.addStyle(new Style().setTextAlignment(CENTER));
        header.setFixedLeading(14);
        header.setFontSize(10);
        header.setPaddingBottom(12f);

        header.add(new Text("" + allObjectsInvoice.getTrxHdr().getContext().getIdCompany() + " 24 HORAS\n ")
                .setFont(getFont(COURIER_BOLD)));
        header.add("TEL: 4865000 " + allObjectsInvoice.getTrxHdr().getContext().getIdCountry() + " \n  ")
                .setFont(getFont(COURIER_BOLD));
        header.add(new Text(" 53432 345354 45 NACIONAL \n").setFont(getFont(COURIER_BOLD)));
        header.add(" \n");
        header.add(new Text("DROGERIA " + allObjectsInvoice.getTrxHdr().getContext().getIdStore() + " SAS \n ")
                .setFont(getFont(COURIER)));
        header.add(new Text("NIT: 70.458.215-6 \n  ").setFont(getFont(COURIER)));
        header.add(new Text(" Regimen Común \n").setFont(getFont(COURIER)));
        header.add(new Text(" Somos Grandes Contribuyentes Según \n").setFont(getFont(COURIER)));
        header.add(new Text(" Resolución N° 0122345 Dic 14 de 2018\n").setFont(getFont(COURIER)));
        header.add(" \n");
        header.add(new Text("Calle 97 N° 13 ~ 14 Bogota \n").setFont(getFont(COURIER)));
        header.add(new Text("PBX: 4924860 \n").setFont(getFont(COURIER)));
        header.add(new Text(" Agentes Retenedores de IVA e ICA\n").setFont(getFont(COURIER)));
        return header;
    }

    @Override
    public Table generateBody() {
        float[] columnWidths = { 1, 2, 3, 4 };
        Table table = Comun.generateTable(columnWidths, 100f);
        for (Cell f : generateFristTable(table.getNumberOfColumns())) {
            table.addCell(f);
        }
        return table;
    }

    private List<Cell> generateFristTable(int tableColumn) {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(generateTittleForTable("DATOS VENTA ORIGINAL \n", true, tableColumn).setFont(getFont(COURIER))
                .setFontSize(10));
        for (Cell cell : generateBodyForDocumentEquivalent()) {
            cells.add(cell);
        }
        cells.add(generateTittleForTable("DATOS VENTA ORIGINAL \n", false, tableColumn).setPaddingTop(15f));
        List<String> listSellOriginal = Arrays.asList("DESCRIPCIÓN", "CANT", "VALOR");
        for (Cell subTittle : generateRowsSubTittleForTable(listSellOriginal, 3)) {
            cells.add(subTittle);
        }
        List<Cell> listDetailBuy = generateBodyForDetailBuy();
        for (int i = 0; i < listDetailBuy.size(); i++) {
            if (i == 0 || i == 1 || i == 2) {
                cells.add(listDetailBuy.get(i).setPaddingTop(10f));
            } else {
                cells.add(listDetailBuy.get(i));
            }
        }
        cells.add(generateTittleForTable("DETALLE DE IMPUESTO \n", false, tableColumn).setPaddingTop(15f));
        List<String> listDetailTax = Arrays.asList("DETALLE", "V/R COMPRA", "BASE/IMP.", "IMP");
        for (Cell subTittle : generateRowsSubTittleForTable(listDetailTax)) {
            cells.add(subTittle);
        }
        List<Cell> listDetailTx = generateBodyForDetails();
        for (int i = 0; i < listDetailTx.size(); i++) {
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                cells.add(listDetailTx.get(i).setPaddingTop(10f));
            } else {
                cells.add(listDetailTx.get(i));
            }
        }
        cells.add(generateTittleForTable("DETALLES DE PAGO \n", false, tableColumn).setPaddingTop(15f));
        List<String> listDetailPay = Arrays.asList("FORMA", "BANCO", "Nro.", "VALOR");
        for (Cell subTittle : generateRowsSubTittleForTable(listDetailPay)) {
            cells.add(subTittle);
        }
        List<Cell> detailPay = generateBodyForOriginalPaymentDetails();

        for (int i = 0; i < detailPay.size(); i++) {
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                cells.add(detailPay.get(i).setPaddingTop(10f));
            } else {
                cells.add(detailPay.get(i));
            }
        }

        return cells;
    }

    private List<Cell> generateBodyForOriginalPaymentDetails() {
        ArrayList<Cell> cells = new ArrayList<>();
        // FORMA
        cells.add(getCellDefault(4, 1, "Efectivo", RIGHT));
        // BANCO
        cells.add(getCellDefault(4, 1, "", RIGHT));
        // Nro.
        cells.add(getCellDefault(4, 1, "", RIGHT));
        // VALOR
        cells.add(getCellDefault(4, 1, "$ " + a.nextInt(35881), LEFT));
        return cells;

    }

    private List<Cell> generateBodyForDetails() {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(getCellDefault(4, 1, "IVA 19%", RIGHT));
        cells.add(getCellDefault(4, 1, "" + a.nextInt(35881), RIGHT));
        cells.add(getCellDefault(4, 1, "" + a.nextInt(35881), RIGHT));
        cells.add(getCellDefault(4, 1, "" + a.nextInt(35881), LEFT));
        return cells;

    }

    private List<Cell> generateBodyForDetailBuy() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            cells.add(getCellDefault(1, 2, givenList_shouldReturnARandomElement().toUpperCase(), RIGHT)
                    .setWidthPercent(60));
            cells.add(getCellDefault(1, 1, a.nextInt(10) + "   *IVA 19 %", Constant.CENTER).setWidthPercent(25));
            cells.add(getCellDefault(0, 1, "" + a.nextInt(58851), LEFT).setWidthPercent(15));
        }
        for (Cell cell : generateBodyForDetailBuyFooter()) {
            cells.add(cell);
        }
        return cells;
    }

    private List<Cell> generateBodyForDetailBuyFooter() {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(getCellDefault(1, 3, "***Descuento Convenio", RIGHT));
        cells.add(getCellDefault(1, 3, "" + a.nextInt(58851), LEFT));
        cells.add(getCellDefault(1, 3, "TOTAL AHORRO", RIGHT).setPaddingTop(10));
        cells.add(getCellDefault(1, 3, "" + a.nextInt(58851), LEFT).setPaddingTop(10));
        cells.add(getCellDefault(1, 3, "TOTAL SIN REDONDEO", RIGHT).setPaddingTop(-1));
        cells.add(getCellDefault(1, 3, "" + a.nextInt(58851), LEFT).setPaddingTop(-1));
        cells.add(getCellDefault(1, 3, "REDONDEO", RIGHT).setPaddingTop(-1));
        cells.add(getCellDefault(1, 3, "" + a.nextInt(58851), LEFT).setPaddingTop(-1));
        cells.add(getCellDefault(1, 3, "TOTAL FACTURA", RIGHT).setFont(getFont(FontConstants.COURIER_BOLD))
                .setPaddingTop(1));
        cells.add(getCellDefault(1, 3, "" + a.nextInt(58851), LEFT).setFont(getFont(FontConstants.COURIER_BOLD))
                .setPaddingTop(1));
        return cells;
    }

    private List<Cell> generateBodyForDocumentEquivalent() {
        ArrayList<Cell> cells = new ArrayList<>();
        // FACTURA Nro.
        cells.add(getCellDefault(1, 2, "FACTURA Nro.", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, allObjectsInvoice.getTrxIdentify().getIdOrder(), RIGHT));
        // SECUENCIA
        cells.add(getCellDefault(1, 2, "SECUENCIA", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "128120", RIGHT));
        // Nro. TICKET
        cells.add(getCellDefault(1, 2, "NRO. TICKET", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "88777", RIGHT));
        // FECHA Y HORA
        cells.add(getCellDefault(1, 2, "FECHA Y HORA", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "24-02-2020 12:06", RIGHT));
        // CAJERO
        cells.add(getCellDefault(1, 2, "CAJERO", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "LORENA DEL CARMEN SOTO", RIGHT));
        // CIUDAD
        cells.add(getCellDefault(1, 2, "CIUDAD", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "SANTIAGO", RIGHT));
        // Caja Nro.
        cells.add(getCellDefault(1, 2, "Caja Nro.", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "4", RIGHT));

        // IDENTIFICACIÓN
        cells.add(getCellDefault(1, 2, "IDENTIFICACIÓN", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "18.145.096-8", RIGHT));
        // CONVENIO
        cells.add(getCellDefault(1, 2, "CONVENIO", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "FUNDACION CRUZ VERDE", RIGHT));
        // PLAN
        cells.add(getCellDefault(1, 2, "PLAN", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "258251", RIGHT));
        // DIRECCIÓN
        cells.add(getCellDefault(1, 2, "DIRECCIÓN", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "Alcántara 44", RIGHT));
        // TELÉFONO
        cells.add(getCellDefault(1, 2, "TELÉFONO", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "227423780", RIGHT));
        // CELULAR
        cells.add(getCellDefault(1, 2, "CELULAR", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "941584795", RIGHT));

        return cells;
    }

 

}
