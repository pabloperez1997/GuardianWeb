/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.IOException;
 
/**
 *
 * @author PabloP
 */
public class GenerarPDF {
    
    public static final String DEST = "C:/Users/PabloP/Desktop/hola.pdf";
    public static final String NEWLINE = "\n";
    public final PdfFont regular;
    public final PdfFont bold;

 
    public GenerarPDF() throws IOException {
       
  this.regular = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        this.bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        
    }
    public void createPdf(cliente c) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdf);
        
        String imageFile = "C:/Users/PabloP/Desktop/logoguardian.jpg"; 
        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data); 
        
        
        document.add(
            new Paragraph()
            .   setTextAlignment(TextAlignment.RIGHT)
                .setMultipliedLeading(1)
                .add(new Text(String.format("%s %s\n", "Pablo", "Perez"))
                        .setFont(bold).setFontSize(14))
                .add(new Text("26/06/2019")));
        
        
         Table tableimg = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 28),
                new UnitValue(UnitValue.PERCENT, 72)})
                .setWidth(UnitValue.createPercentValue(100));
        tableimg.addCell(img);
        
        
         Paragraph p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("Gracias por su preferencia!!!").setFont(bold)).add(NEWLINE)
                .add(new Text("Compra realizada por: ").setFont(bold)).add(NEWLINE)
                .add("Nombre y Apellido: "+c.getNombre()+c.getApellido()).add(NEWLINE)
                .add("Cedula: "+c.getCedula()).add(NEWLINE)
                .add("Telfono: "+c.getTel_cel()).add(NEWLINE)
                .add("Direccion: "+c.getDireccion()).add(NEWLINE)
                .add("Email: "+c.getCorreo()).add(NEWLINE);
        Cell celldir = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(p);
        
        tableimg.addCell(celldir);
        
        document.add(tableimg);
        
        
         Table tabledir = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setWidth(UnitValue.createPercentValue(100));
         

        
        document.add(tabledir);
        
        
 float[] columnWidths = {1, 4, 1, 4, 4};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).setWidth(UnitValue.createPercentValue(100));
        PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Cell cell = new Cell(1, 5)
                .add(new Paragraph("Detalles de su compra en El Guardian"))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(cell);
      //  for (int i = 0; i < 3; i++) {
            Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("#")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Producto")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Cantidad")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Precio Unitario")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Total"))
            };
            for (Cell hfCell : headerFooter) {
               
                    table.addHeaderCell(hfCell);
                
            }
        //}
        for (int counter = 1; counter < 9; counter++) {
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(counter))));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("key " + counter)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + counter)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + counter)));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + counter)));

        }
        
        
        
        
       
        document.add(table);
        document.close();
    }
    
  
  
   
}

