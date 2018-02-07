package almeida.saul.com.pdfcreation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by Elite desk 700g1 on 11/01/2018.
 */

public class Metod {

    public boolean write (String name, String content, Context context){
        try {
            String path =   context.getFilesDir().getAbsolutePath();
            File file = new File(path+"/"+name+".pdf");
            if (!file.exists()){
                file.createNewFile();
            }


            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Document document = new Document();
            InputStream ims = context.getAssets().open("interactive-solutions-logo.png");
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setAbsolutePosition(700f,500f);
            image.scaleAbsolute(100f,50f);
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
            document.setPageSize(PageSize.A4.rotate());
            document.open();
            document.add(image);
            float[] dimensions = {1.5f,1.5f,1.5f,1.5f,1.5f,2.5f,1.5f,2.5f,2.5f,2.5f};
            document.add(new Paragraph("Prueba de salto de pagina"));
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Nombre del proyecto: _______________"));
            document.add(new Paragraph("Encargado: _____________"));
            document.add(new Paragraph("\n"));
            PdfPTable table = new PdfPTable(dimensions);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Fecha");
            table.addCell("Mes");
            table.addCell("Importe");
            table.addCell("Subtotal");
            table.addCell("IVA");
            table.addCell("Ingreo/Egreso");
            table.addCell("Tipo");
            table.addCell("Empleado");
            table.addCell("Descripción");
            table.addCell("Referencia");
            PdfPCell[] cells = table.getRow(0).getCells();

            for (int j=0;j<cells.length;j++){
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }


            Paragraph p = new Paragraph();
            p.add(table);
            document.add(p);
            document.close();
            return true;


        }catch (IOException e){
            e.printStackTrace();
            return false;
        }catch (DocumentException e){
            e.printStackTrace();
            return false;
        }
    }

    public void createTable (String name,Context context,Solicitudbean bean){

        List<EquiposModel> b = bean.getEquipos();

        try {
            String path = context.getFilesDir().getAbsolutePath();
            File file = new File(path + "/" + name + ".pdf");
            if (!file.exists()) {
                file.createNewFile();
            }

            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream(path+"/"+name+".pdf"));
            document.setMargins(25,55,55,35);
            document.setPageSize(PageSize.A4.rotate());
            document.open();
            /***********Logo empresa******************/
            InputStream ims = context.getAssets().open("interactive-solutions-logo.png");
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setAbsolutePosition(700f,500f);
            image.scaleAbsolute(100f,50f);
            document.add(image);
            /*********Salto de pagina**************/
            document.add(new Paragraph("\n"));
            /***********Titulo del documento*********/
            Font font = new Font();
            font.setStyle("bold");
            Paragraph paragraph = new Paragraph("SOLICITUD DE GASTO",font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            /*********Salto de pagina**************/
            document.add(new Paragraph("\n"));
            /***************Cuerpo del Documento *****/
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph("Solicita: "+ "Saul Almeida");
            p.add(new Chunk(glue));
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy",Locale.ENGLISH);
            String sCertDate = dateFormat.format(new Date());
            p.add("Fecha: "+sCertDate);
            document.add(p);
            Chunk glue1 = new Chunk(new VerticalPositionMark());
            Paragraph p1 = new Paragraph("Proyecto: "+ bean.getProyecto());
            p1.add(new Chunk(glue1));
            p1.add("Fecha de ejecución: "+bean.getFecha());
            document.add(p1);
            Chunk glue2 = new Chunk(new VerticalPositionMark());
            Paragraph p2 = new Paragraph("Atención: Olivia Cruz, Administración ");
            p2.add(new Chunk(glue2));
            p2.add("No. de orden: "+bean.getOrden());
            document.add(p2);
            document.add(new Paragraph("Tel: +52 (55) 12091506 ext. 104"));
            document.add(new Paragraph("Email: ocruz@interactivesolutions.mx"));
            document.add(new Paragraph("Estancia: "+bean.getEstancia()));
            document.add(new Paragraph("Personas: "+ bean.getPersonas()));
            document.add(new Paragraph("Por medio de este conducto, solicitamos para su compra, las siguientes partidas:",font));

            /****************Tabla de contenido*****/
            String[] headers  = new String[]{"Equipo","Cantidad","Precio","Importe","Notas"};
            PdfPTable  table = new PdfPTable(headers.length);
            table.setHeaderRows(1);
            table.setWidths(new int[]{4,1,1,1,2});
            table.setWidthPercentage(98);
            table.setSpacingBefore(15);
            table.setSplitLate(false);
            for (String columnheader :headers){
                PdfPCell headercell = new PdfPCell();
                headercell.addElement(new Phrase(columnheader, FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD,BaseColor.WHITE)));
                BaseColor color = WebColors.getRGBColor("#1d4484");
                headercell.setBackgroundColor(color);
                headercell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headercell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                //headercell.setBorderColor(BaseColor.LIGHT_GRAY);
                //headercell.setPadding(8);
                table.addCell(headercell);

            }

            for (int i=0;i< b.size();i++){
                table.addCell(b.get(i).getEquipo());
                table.addCell(b.get(i).getCantidad());
                table.addCell(b.get(i).getPrecio());
                table.addCell(String.valueOf(b.get(i).getImporte()));
                table.addCell(b.get(i).getEquipo());
            }

            document.add(table);
            /*******************Tabla total****************/
            PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidths(new int[]{1,1});
            totalTable.setWidthPercentage(98);
            totalTable.setSpacingBefore(15);
            totalTable.setSplitLate(false);
            
            document.add(totalTable);
            /*******************Actividades****************/

            document.add(new Paragraph("Actividades a realizar: "+bean.getActividades()));
            /***********************************************/
            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
