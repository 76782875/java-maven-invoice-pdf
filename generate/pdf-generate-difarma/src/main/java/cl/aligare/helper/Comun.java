package cl.aligare.helper;

import static cl.aligare.helper.Constant.CENTER;
import static cl.aligare.helper.Constant.DEFAUL_SIZE_FONT_TITLE_BLOD;
import static cl.aligare.helper.Constant.DEFAUL_SIZE_SPACING_BEWTTEN_TITTLE_AND_LINE;
import static cl.aligare.helper.Constant.LEFT;
import static cl.aligare.helper.Constant.RIGHT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class Comun {

    /* -------------------------- for control exeption -------------------------- */

    public static PdfFont getFont(String p) {
        PdfFont f;
        try {
            f = PdfFontFactory.createFont(p);
            return f;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Table generateTable(float[] columnWidths, float WidthPercent) {
        Table table = new Table(columnWidths);
        table.setWidthPercent(WidthPercent);

        return table;
    }

    public static Cell generateTittleForTable(String tittle, boolean withLine, Integer tableColumn) {
        Cell cell = new Cell(1, tableColumn);
        cell.add(tittle).setFont(getFont(FontConstants.COURIER_BOLD)).setFontSize(DEFAUL_SIZE_FONT_TITLE_BLOD)
                .setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER);
        if (withLine) {
            cell.setBorderBottom(new DashedBorder(Color.BLACK, 1))
                    .setPaddingBottom(DEFAUL_SIZE_SPACING_BEWTTEN_TITTLE_AND_LINE);
        }
        return cell;
    }

    public static Cell getCellDefault(int i, int j, String numberInvoice, int k) {
        Cell cell = new Cell(i, j).add(numberInvoice).setBackgroundColor(Color.WHITE).setFontSize(9).setBorder(null)
                .setPaddingBottom(-2f).setWidth(80);

        switch (k) {
            case 0:
                cell.setTextAlignment(TextAlignment.CENTER);
                break;
            case 1:
                cell.setTextAlignment(TextAlignment.RIGHT);
                break;
            case 2:
                cell.setTextAlignment(TextAlignment.LEFT);
                break;
            default:
                break;
        }

        return cell;
    }

    public static Cell getCellDefaultStyleForColSimple(int firstP, int secP, String text, int position) {
        Cell cell = new Cell(firstP, secP).add(text).setBackgroundColor(Color.WHITE).setFontSize(9).setBorder(null)
                .setPaddingBottom(-2f).setWidth(200);

        switch (position) {
            case 0:
                cell.setTextAlignment(TextAlignment.CENTER);
                break;
            case 1:
                cell.setTextAlignment(TextAlignment.RIGHT);
                break;
            case 2:
                cell.setTextAlignment(TextAlignment.LEFT);
                break;
            default:
                break;
        }

        return cell;
    }

    public static List<Cell> generateRowsSubTittleForTable(List<String> itemsTittle) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int i = 0; i < (itemsTittle.size()); i++) {
            if (i != (itemsTittle.size() - 1)) {
                cells.add(getCellSubTittleDefault(1, 1, itemsTittle.get(i), RIGHT));
            } else {
                cells.add(getCellSubTittleDefault(1, 4, itemsTittle.get(i), LEFT));
            }
        }

        return cells;
    }

    public static List<Cell> generateRowsSubTittleForTable(List<String> itemsTittle, int size) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (i != (itemsTittle.size() - 1)) {
                if (i != 1) {
                    cells.add(getCellSubTittleDefault(1, 2, itemsTittle.get(i), RIGHT));
                } else {
                    cells.add(getCellSubTittleDefault(1, 1, itemsTittle.get(i), CENTER));
                }
            } else {
                cells.add(getCellSubTittleDefault(1, 0, itemsTittle.get(i), LEFT));
            }
        }

        return cells;
    }

    public static Cell getCellSubTittleDefault(int i, int j, String text, int k) {
        Cell cell = new Cell(i, j).add(text).setBackgroundColor(Color.WHITE).setFontSize(9).setWidth(80)
                .setBorderBottom(new DashedBorder(Color.BLACK, 1)).setPaddingBottom(7).setBorder(null);

        switch (k) {
            case 0:
                cell.setTextAlignment(TextAlignment.CENTER);
                break;
            case 1:
                cell.setTextAlignment(TextAlignment.RIGHT);
                break;
            case 2:
                cell.setTextAlignment(TextAlignment.LEFT);
                break;
            default:
                break;
        }

        return cell;
    }

    public static String givenList_shouldReturnARandomElement() {
        List<String> givenList = Arrays.asList("Acetaminophen", "Adderall", "Alprazolam", "Amitriptyline", "Amlodipine",
                "Amoxicillin", "Ativan", "Atorvastatin", "Azithromycin", "Ciprofloxacin", "Citalopram", "Clindamycin",
                "Clonazepam", "Codeine", "Cyclobenzaprine", "Cymbalta", "Doxycycline", "Gabapentin",
                "Hydrochlorothiazide", "Ibuprofen", "Lexapro", "Lisinopril", "Loratadine", "Lorazepam", "Losartan",
                "Lyrica", "Meloxicam", "Metformin", "Metoprolol", "Naproxen", "Omeprazole", "Oxycodone", "Pantoprazole",
                "Prednisone", "Tramadol", "Trazodone", "Viagra", "Wellbutrin", "Xanax", "Zoloft");

        Random rand = new Random();
        String randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }
}