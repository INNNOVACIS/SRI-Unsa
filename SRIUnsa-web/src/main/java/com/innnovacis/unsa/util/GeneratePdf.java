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

        int nroColumnas = 10;

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
        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        // Creacion de la cabecera principal de la tabla
        table.addCell(createCellMainHeader("Actividades de Investigación Generadas"));

        String[] nombreColumnas = {"Facultad", "Departamento", "Semestre", "Nombre del proyecto",
            "Tipo", "Fecha creación", "Fecha última revisión", "Última revisión", "Pendiente", "Fondo"};

        // Cabeceras sub principales
        table.addCell(createCellHeader(nombreColumnas[0]));
        table.addCell(createCellHeader(nombreColumnas[1]));
        table.addCell(createCellHeader(nombreColumnas[2]));
        table.addCell(createCellHeader(nombreColumnas[3]));
        table.addCell(createCellHeader(nombreColumnas[4]));
        table.addCell(createCellHeader(nombreColumnas[5]));
        table.addCell(createCellHeader(nombreColumnas[6]));
        table.addCell(createCellHeader(nombreColumnas[7]));
        table.addCell(createCellHeader(nombreColumnas[8]));
        table.addCell(createCellHeader(nombreColumnas[9]));

        table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(247, 247, 247));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // Creamos los Items de la tabla
        ArrayList<SRIActividadGeneralPaginacion> listaObjetos
                = (ArrayList<SRIActividadGeneralPaginacion>) respuesta.get("lista");

        for (int counter = 0; counter < listaObjetos.size(); counter++) {

            Font ft3 = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(70, 70, 70));

            ArrayList<PdfPCell> cells = new ArrayList<PdfPCell>();
            for (int j = 0; j < nroColumnas; j++) {
                cells.add(createCell());
            }

            cells.get(0).addElement(new Phrase(listaObjetos.get(counter).getFacultad() == null ? ""
                    : listaObjetos.get(counter).getFacultad(), ft3));
            cells.get(1).addElement(new Phrase(listaObjetos.get(counter).getDepartamento() == null ? ""
                    : listaObjetos.get(counter).getDepartamento(), ft3));
            cells.get(2).addElement(new Phrase(listaObjetos.get(counter).getSemestre() == null ? ""
                    : listaObjetos.get(counter).getSemestre(), ft3));
            cells.get(3).addElement(new Phrase(listaObjetos.get(counter).getNombreactividad() == null ? ""
                    : listaObjetos.get(counter).getNombreactividad(), ft3));
            cells.get(4).addElement(new Phrase(listaObjetos.get(counter).getTipoactividad() == null ? ""
                    : listaObjetos.get(counter).getTipoactividad(), ft3));
            cells.get(5).addElement(new Phrase(listaObjetos.get(counter).getFechacreacion() == null ? ""
                    : listaObjetos.get(counter).getFechacreacion(), ft3));
            cells.get(6).addElement(new Phrase(listaObjetos.get(counter).getUltimafecha() == null ? ""
                    : listaObjetos.get(counter).getUltimafecha(), ft3));
            cells.get(7).addElement(new Phrase(listaObjetos.get(counter).getUltimoaprobador() == null ? ""
                    : listaObjetos.get(counter).getUltimoaprobador(), ft3));
            cells.get(8).addElement(new Phrase(listaObjetos.get(counter).getPendiente() == null ? ""
                    : listaObjetos.get(counter).getPendiente(), ft3));
            cells.get(9).addElement(new Phrase(listaObjetos.get(counter).getFondoconcursable() == null ? ""
                    : listaObjetos.get(counter).getFondoconcursable(), ft3));

            for (int j = 0; j < nroColumnas; j++) {
                table.addCell(cells.get(j));
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
