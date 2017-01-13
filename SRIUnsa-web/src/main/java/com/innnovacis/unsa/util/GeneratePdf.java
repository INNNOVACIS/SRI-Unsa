/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.html.Tags;

import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Innnovacis
 */
public class GeneratePdf {

    public GeneratePdf() {

    }
        
    private PdfPCell createCellMainHeader(String name) {
        Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
        
        PdfPCell cell = new PdfPCell(new Phrase(name, f));
        cell.setBackgroundColor(new BaseColor(146, 3, 11));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(10);
        cell.setPadding(10);
        cell.setBorderWidth(2);
        cell.setBorderColor(GrayColor.WHITE);
        return cell;
    }
    
    private PdfPCell createCellHeader(String name) {
        Font ft = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, new BaseColor(70, 70, 70));
        PdfPCell cellt = new PdfPCell(new Phrase(name, ft));
        cellt.setBackgroundColor(new BaseColor(243, 224, 226));
        cellt.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellt.setPadding(8);
        cellt.setBorderWidthTop(2);
        cellt.setBorderWidthBottom(0);
        cellt.setBorderWidthLeft(0);
        cellt.setBorderWidthRight(0);
        cellt.setBorderColor(GrayColor.WHITE);

        return cellt;
    }

    private PdfPCell createCell() {
        PdfPCell cellte = new PdfPCell();
        cellte.setBackgroundColor(new BaseColor(243, 224, 226));
        cellte.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellte.setPadding(5);
        cellte.setBorderWidthTop(2);
        cellte.setBorderWidthBottom(0);        
        cellte.setBorderWidthLeft(0);
        cellte.setBorderWidthRight(0);
        cellte.setBorderColor(GrayColor.WHITE);

        return cellte;
    }
    public byte[] getArrayByteFrom(Map<String, Object> respuesta) throws IOException, DocumentException {
        return new byte[1];
    }
    public byte[] getArrayByteFrom(Map<String, Object> respuesta, int contador,
            String[] nombreColumnasCabeceras, String tituloPrincipal,
            ArrayList<ArrayList<String>> listaObjetosSend) throws IOException, DocumentException {

        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        Document documento = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(documento, baosPDF);

        // Propiedades del documento
        documento.addAuthor("Innnóvacis");
        documento.addCreationDate();
        documento.addProducer();
        documento.addCreator("innnovacis.com");
        documento.addTitle("Innnóvacis");
        
        documento.open();

        documento.add(new Paragraph("Universidad Nacional de San Agustín"));
        documento.add(new Paragraph("Arequipa - Arequipa - Perú"));
        documento.add(new Paragraph("--"));
        documento.add(new Paragraph("--"));
        float[] columnWidths = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        PdfPTable table = new PdfPTable(contador);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        // Creacion de la cabecera principal de la tabla
        table.addCell(createCellMainHeader(tituloPrincipal));
        
        // Cabeceras sub principales
        for(int i = 0 ; i < contador;i++)
        {
            table.addCell(createCellHeader(nombreColumnasCabeceras[i]));
        }

        table.setHeaderRows(2);
        table.getDefaultCell().setBackgroundColor(new BaseColor(247, 247, 247));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // Creamos los Items de la tabla
        for (int counter = 0; counter < listaObjetosSend.size(); counter++) {

            Font ft = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(70, 70, 70));
            
            ArrayList<String> arrayDatosObjeto = listaObjetosSend.get(counter);
            for (int j = 0; j < contador; j++) {
                PdfPCell celda = createCell();
                celda.addElement(new Phrase( arrayDatosObjeto.get(j), ft));
                table.addCell(celda);
            }
        }
        documento.add(table);

        documento.close();
        int longitudEnBytes = baosPDF.toByteArray().length;
        byte[] rptaBytes = new byte[longitudEnBytes];
        rptaBytes = baosPDF.toByteArray();
        return rptaBytes;
    }
}
