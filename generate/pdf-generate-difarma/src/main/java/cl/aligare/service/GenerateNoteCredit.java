package cl.aligare.service;

import static cl.aligare.helper.Comun.generateRowsSubTittleForTable;
import static cl.aligare.helper.Comun.generateTittleForTable;
import static cl.aligare.helper.Comun.getCellDefault;
import static cl.aligare.helper.Comun.getCellDefaultStyleForColSimple;
import static cl.aligare.helper.Comun.getFont;
import static cl.aligare.helper.Comun.givenList_shouldReturnARandomElement;
import static cl.aligare.helper.Constant.CENTER;
import static cl.aligare.helper.Constant.DEFAULT_HEIGHT_NC;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_B;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_L;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_R;
import static cl.aligare.helper.Constant.DEFAULT_MARGIN_T;
import static cl.aligare.helper.Constant.DEFAULT_WITH_NC;
import static cl.aligare.helper.Constant.DEFAUL_SIZE_FONT_TITLE_BLOD;
import static cl.aligare.helper.Constant.LEFT;
import static cl.aligare.helper.Constant.PADDING_TOP_SIGNATURE;
import static cl.aligare.helper.Constant.RIGHT;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import cl.aligare.entity.AllObjectsInvoice;
import cl.aligare.helper.Comun;
import cl.aligare.service.interfaces.GenerateInterface;
import cl.aligare.service.interfaces.NoteCreditInterface;

public class GenerateNoteCredit implements GenerateInterface, NoteCreditInterface {
    private static final Logger LOGGER = Logger.getLogger(GenerateInvoice.class.getName());
    Random a = new Random();
    AllObjectsInvoice allObjectsInvoice;

    @Override
    public byte[] generate(AllObjectsInvoice allObjectsInvoice) throws IOException {
        if (null == allObjectsInvoice) {
            throw new IllegalArgumentException("AllObjectsInvoice.class is null, pleace check out over json object");
        }
        
        this.allObjectsInvoice = allObjectsInvoice;
        LOGGER.info("generate Note Credit in memory");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter aWriter = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(aWriter);

        Table table = generateBody();

        Paragraph header = generateHeader();

        float hieght = (table.getNumberOfRows() * 16.50000f) + DEFAULT_HEIGHT_NC;
        Document document = new Document(pdf, new PageSize(DEFAULT_WITH_NC, hieght));
        document.setMargins(DEFAULT_MARGIN_T, DEFAULT_MARGIN_R, DEFAULT_MARGIN_B, DEFAULT_MARGIN_L);
        document.add(header);
        document.add(table);
        document.close();

        OutputStream os = aWriter.getOutputStream();
        baos.writeTo(os);
        return baos.toByteArray();
    }

    private List<Cell> generateBodyForDetailAnulation() {

        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // DESCP.
            cells.add(getCellDefault(1, 2, givenList_shouldReturnARandomElement().toUpperCase(), RIGHT));
            // CANT
            cells.add(getCellDefault(1, 1, a.nextInt(10) + "", CENTER));
            // VALOR
            cells.add(getCellDefault(0, 1, "$ " + a.nextInt(58851), LEFT));
        }

        for (Cell cell : generateBodyForDetailAnulationFooter()) {
            cells.add(cell);
        }

        return cells;
    }

    @Override
    public Paragraph generateHeader() {
        Paragraph header = new Paragraph();
        header.addStyle(new Style().setTextAlignment(TextAlignment.CENTER));
        header.setFixedLeading(14); // this manager spacing bewteen lines
        header.setFontSize(10);
        header.setPaddingBottom(12f);

        // explame: A N U L A C I Ó N
        header.add(new Text("ANULACIÓN \n").setFont(getFont(FontConstants.COURIER)).setCharacterSpacing(4f));
        // explame: NORMAL
        header.add(new Text("NORMAL \n ").setFontSize(DEFAUL_SIZE_FONT_TITLE_BLOD)
                .setFont(getFont(FontConstants.COURIER_BOLD)));
        // explame: DROGUERIAS Y FARMACIAS CRUZ VERDE SAS
        header.add(new Text("DROGUERIAS Y FARMACIAS CRUZ VERDE SAS \n").setFont(getFont(FontConstants.COURIER)));
        // explame: NIT 800.149.695-1
        header.add(new Text("NIT 800.149.695-1 \n").setFont(getFont(FontConstants.COURIER)));
        // explame: Calle 97 N 13 - 14 Bogota
        header.add(new Text("Calle 97 N 13 - 14 Bogota \n").setFont(getFont(FontConstants.COURIER)));
        // explame: Régimen Común
        header.add(new Text("Régimen Común \n").setFont(getFont(FontConstants.COURIER)));
        // explame: ACTIVIDAD ECONÓMICA ICA 52311
        header.add(new Text("ACTIVIDAD ECONÓMICA ICA 52311 \n").setFont(getFont(FontConstants.COURIER)));
        // explame: Somos Grandes Contribuyentes Según
        header.add(new Text("Somos Grandes Contribuyentes Según \n").setFont(getFont(FontConstants.COURIER)));
        // explame: Resolución Nro. 11944 Dic 24 de 2004
        header.add(new Text("Resolución Nro. 11944 Dic 24 de 2004 \n").setFont(getFont(FontConstants.COURIER)));
        // explame: Agentes Retenedores de IVA e ICA
        header.add(new Text("Agentes Retenedores de IVA e ICA \n").setFont(getFont(FontConstants.COURIER)));

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

    private List<Cell> generateFristTable(Integer tableColumn) {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(generateTittleForTable("DATOS VENTA ORIGINAL \n", true, tableColumn));
        for (Cell cellOriginalSaleData : generateBodyForOriginalSaleData()) {
            cells.add(cellOriginalSaleData);
        }
        cells.add(generateTittleForTable("DATOS ANULACIÓN \n", true, tableColumn).setPaddingTop(10f));
        for (Cell cellOriginalSaleData : generateBodyForAnulationData()) {
            cells.add(cellOriginalSaleData);
        }

        cells.add(generateTittleForTable("DETALLE PAGOS VENTA ORIGINAL \n", false, tableColumn).setPaddingTop(10f));
        List<String> listSellOriginal = Arrays.asList("FORMA", "BANCO", "Nro.", "VALOR");

        for (Cell subTittle : generateRowsSubTittleForTable(listSellOriginal)) {
            cells.add(subTittle);
        }
        for (Cell cellOriginalSaleData : generateBodyForOriginalPaymentDetails()) {
            cells.add(cellOriginalSaleData);
        }

        cells.add(generateTittleForTable("DETALLE ANULACIÓN \n", false, tableColumn).setPaddingTop(10f));
        List<String> listAlulation = Arrays.asList("DESCRIPCIÓN", "CANT", "VALOR");

        for (Cell subTittle : generateRowsSubTittleForTable(listAlulation, 3)) {

            cells.add(subTittle);
        }
        for (Cell data : generateBodyForDetailAnulation()) {

            cells.add(data);
        }
        for (Cell signature : generateSignature()) {
            cells.add(signature);
        }

        return cells;
    }

    private List<Cell> generateBodyForOriginalSaleData() {
        ArrayList<Cell> cells = new ArrayList<>();
        // FACTURA Nro.
        cells.add(getCellDefault(1, 2, "FACTURA Nro.", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "A172 128120", RIGHT));
        // SECUENCIA
        cells.add(getCellDefault(1, 2, "SECUENCIA", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "128120", RIGHT));
        // Nro. TICKET
        cells.add(getCellDefault(1, 2, "Nro. TICKET", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "88777", RIGHT));
        // FECHA Y HORA
        cells.add(getCellDefault(1, 2, "FECHA Y HORA", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "24-02-2020 12:06", RIGHT));
        // CAJERO
        cells.add(getCellDefault(1, 2, "CAJERO", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "101.611.XXX-X", RIGHT));
        // SUCURSAL
        cells.add(getCellDefault(1, 2, "SUCURSAL", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "172", RIGHT));
        return cells;
    }

    private List<Cell> generateBodyForAnulationData() {
        ArrayList<Cell> cells = new ArrayList<>();
        // CLIENTE
        cells.add(getCellDefault(1, 2, "CLIENTE", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "DANIELA CAROLINA PACHECO PACHECO", RIGHT));
        // IDENTIFICACIÓN
        cells.add(getCellDefault(1, 2, "IDENTIFICACIÓN", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "1045685497", RIGHT));
        // DIRECCIÓN
        cells.add(getCellDefault(1, 2, "DIRECCIÓN", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "Carrera-7g 07", RIGHT));
        // MUNICIPIO
        cells.add(getCellDefault(1, 2, "MUNICIPIO", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "METROPOLITANO", RIGHT));
        // CIUDAD
        cells.add(getCellDefault(1, 2, "CIUDAD", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "SANTIAGO", RIGHT));
        // REGIÓN
        cells.add(getCellDefault(1, 2, "REGIÓN", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "ARICA XI", RIGHT));
        // SUPERVISOR
        cells.add(getCellDefault(1, 2, "SUPERVISOR", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "1364", RIGHT));
        // Nro. TICKET
        cells.add(getCellDefault(1, 2, "Nro. TICKET", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "88777", RIGHT));
        // CAJERO
        cells.add(getCellDefault(1, 2, "CAJERO", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "3181", RIGHT));
        // FECHA Y HORA
        cells.add(getCellDefault(1, 2, "FECHA Y HORA", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "24-02-2020 12:06", RIGHT));
        // SUCURSAL
        cells.add(getCellDefault(1, 2, "SUCURSAL", RIGHT));
        cells.add(getCellDefaultStyleForColSimple(1, 4, "172", RIGHT));
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

    private List<Cell> generateBodyForDetailAnulationFooter() {
        ArrayList<Cell> cells = new ArrayList<>();

        // Subtotal.
        cells.add(getCellDefault(1, 3, "Subtotal", RIGHT).setPaddingTop(10));
        cells.add(getCellDefault(1, 3, "$ " + a.nextInt(58851), LEFT).setPaddingTop(10));
        // Efectivo
        cells.add(getCellDefault(1, 3, "Efectivo", RIGHT).setPaddingTop(-1));
        cells.add(getCellDefault(1, 3, "$ " + a.nextInt(58851), LEFT).setPaddingTop(-1));
        // Total
        cells.add(getCellDefault(1, 3, "TOTAL", RIGHT).setFont(getFont(FontConstants.COURIER_BOLD)).setPaddingTop(-1));
        cells.add(getCellDefault(1, 3, "$ " + a.nextInt(58851), LEFT).setFont(getFont(FontConstants.COURIER_BOLD))
                .setPaddingTop(-1));
        return cells;
    }

    private List<Cell> generateSignature() {
        ArrayList<Cell> cells = new ArrayList<>();

        cells.add(getCellDefault(1, 1, "FIRMA", RIGHT).setPaddingTop(PADDING_TOP_SIGNATURE).setPaddingLeft(30));
        cells.add(getCellDefault(1, 3, ":", RIGHT).setPaddingTop(PADDING_TOP_SIGNATURE)
                .setBorderBottom(new DashedBorder(Color.BLACK, 1)));

        cells.add(getCellDefault(1, 1, "ID CLIENTE", RIGHT).setPaddingTop(2).setPaddingLeft(30));
        cells.add(getCellDefault(1, 3, ":", RIGHT).setPaddingTop(2).setBorderBottom(new DashedBorder(Color.BLACK, 1)));
        return cells;
    }

}
