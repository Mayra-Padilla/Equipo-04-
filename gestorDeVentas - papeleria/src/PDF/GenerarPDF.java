package PDF;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Desktop;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Adrian
 */
public class GenerarPDF {


    public void createPDF(String r, JTable vPrevia,String fInicial,String fFinal) throws IOException {
        // Aquí introduciremos el código pa ra crear el PDF.
        String logo = "src/Img/papeleria (1).jpg";
        PdfWriter writer = new PdfWriter(r);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf,PageSize.A4);
        Image img = new Image(ImageDataFactory.create(logo));
        img.scaleToFit(150, 150);
        
        Paragraph p = new Paragraph().add(img);

        p.setTextAlignment(TextAlignment.CENTER);
        p.add("\n");
        p.add("REPORTE\n").setFontSize(18);
        Paragraph pa2 = new Paragraph();
        pa2.add("Fecha inicial: "+fInicial+"\n");
        pa2.add("Fecha Final: "+fFinal);

        Table table = new Table(new float[]{4,4,4,4,4,4});
        llenarTabla(vPrevia, table);
        
        doc.add(p);
        doc.add(pa2);
        doc.add(table);
        doc.close();

        try {
            File path = new File(r);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void llenarTabla(JTable vPrevia,Table tb){
        //Generacion de tabla para reporte
        TableModel modelo = vPrevia.getModel();
        int col = modelo.getColumnCount();
        int fila = modelo.getRowCount();
        for (int h = 0; h < col; h++) {
            tb.addHeaderCell(new Cell().add(new Paragraph(modelo.getColumnName(h))));
        }
        //cuerpo de la tabla
        for (int f = 0; f < fila; f++) {
            for (int c = 0; c < col; c++) {
                tb.addCell(new Cell().add(new Paragraph(
                        String.valueOf(modelo.getValueAt(f, c)))));
            }
        }
    }
}
