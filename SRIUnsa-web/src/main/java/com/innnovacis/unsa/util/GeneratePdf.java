/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
/**
 *
 * @author Diego
 */
public class GeneratePdf {

    public GeneratePdf() {
    }

    public void generarPdfEjemplo() throws FileNotFoundException, DocumentException {
        FileOutputStream archivo = new FileOutputStream("F:\\hola.pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Hola Mundo!"));
        documento.add(new Paragraph("Este es un ejemplo para probar como funciona la generacion de "
                + " Pdfs con iText y Java"));
        documento.close();
    }
}
