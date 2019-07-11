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
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
 
/**
 *
 * @author PabloP
 */
public class GenerarPDF {
    
    public final String DEST;
    public final cliente cliente;
    public static final String NEWLINE = "\n";
    public final PdfFont regular;
    public final PdfFont bold;
    String ruta=Paths.get(System.getProperty("user.dir")).getParent().getParent().toString();

 
    public GenerarPDF(cliente c) throws IOException {
      this.cliente = c;
      this.DEST = ruta+"/GuardianWeb/GuardianWeb/web/pdfventa/"+"Compra_"+c.getNombre()+c.getApellido()+".pdf";
      this.regular = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
      this.bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        
    }
    public void createPdf() throws IOException {
        
       // List<producto> listaProd= c.getCompra().getDetalles().getListaProducto();
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdf);
        
        String imageFile = ruta+"/GuardianWeb/GuardianWeb/web/img/logoguardian.jpg"; 
        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data); 
        
       

        String output = 
        ZonedDateTime.now ( ZoneId.of ( "Europe/Madrid" ) )
        .format( 
        DateTimeFormatter.ofLocalizedDate ( FormatStyle.FULL )
        .withLocale ( new Locale ( "es" , "ES" ) ) 
        );
        
        document.add(
            new Paragraph()
            .   setTextAlignment(TextAlignment.RIGHT)
                .setMultipliedLeading(1)
                .add(new Text(String.format("%s %s\n", "", "Fecha de Compra"))
                        .setFont(bold).setFontSize(14))
                .add(new Text(output)));
        
        
        Table tableimg = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 23),
                new UnitValue(UnitValue.PERCENT, 77)})
                .setWidth(UnitValue.createPercentValue(100));
        tableimg.addCell(img);
            
        
        
         Paragraph p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("El Guardian Estetica Canina").setFont(bold)).add(NEWLINE)
                .add(new Text("Compra realizada por: ").setFont(bold)).add(NEWLINE)
                .add("Nombre y Apellido: "+cliente.getNombre()+cliente.getApellido()).add(NEWLINE)
                .add("Cedula: "+cliente.getCedula()).add(NEWLINE)
                .add("Telefono: "+cliente.getTel_cel()).add(NEWLINE)
                .add("Direccion: "+cliente.getDireccion()).add(NEWLINE)
                .add("Email: "+cliente.getCorreo()).add(NEWLINE);
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
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nro")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Producto")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Cantidad")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Precio Unitario")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("SubTotal"))
            };
            for (Cell hfCell : headerFooter) {
               
                    table.addHeaderCell(hfCell);
                
            }
        //}
    List<detalleVenta> Productosavender= (List<detalleVenta>) cliente.getCompra().getDetalles();
    List<producto> prodsventa = new ArrayList<>();
               
            Iterator it1 = Productosavender.iterator();
            while (it1.hasNext()){
                detalleVenta dv=(detalleVenta) it1.next();
                producto pr=dv.getProducto();
                pr.setCantidad(dv.getCantidad());
                prodsventa.add(pr);
                
            }
   
            
            
         Iterator it = prodsventa.iterator();
         int counter = 1;
         float total=0;
            while (it.hasNext()){
            producto pr=(producto) it.next();
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(counter))));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(pr.getNombre())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(pr.getCantidad()))));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("$"+String.valueOf((pr.getPrecio())))));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("$"+String.valueOf((pr.getPrecio()*pr.getCantidad())))));
            counter++;
            total=total+(pr.getPrecio()*pr.getCantidad());
                
            }
            
        
        Paragraph p1 = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("Total a pagar: $"+total).setFont(bold)).add(NEWLINE);
               
        Cell total1 = new Cell()
                .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                .add(p1);
//        
        table.addCell(new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).add(new Paragraph("")));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).add(new Paragraph("")));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).add(new Paragraph("")));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).add(new Paragraph("")));
        table.addCell(total1);
        
        
         Paragraph p2 = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("Gracias por su preferencia!!!").setFont(bold)).add(NEWLINE)
                .add(new Text("Direccion: Zorrilla de San Martín 1851, Ciudad De Paysandú, Paysandu, Uruguay").setFont(bold)).add(NEWLINE)
                .add(new Text("Email: elguardianesteticacanina@gmail.com").setFont(bold)).add(NEWLINE)
                .add(new Text("Celular: 091 815 175").setFont(bold)).add(NEWLINE);
               
        Cell cellguard = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(p2);
        
        document.add(new Paragraph("").add(NEWLINE));
    
        
        document.add(table); 
        document.add(cellguard);
    
        document.close();
    }
    
  
  
   
}

