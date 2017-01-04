/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Diego
 */
public class GeneratePdf extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("application/pdf");
        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph("Hello World"));
            document.add(new Paragraph(new Date().toString()));
            // step 5
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
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
    /**
     * A serial version uid
     */
    private static final long serialVersionUID = 4262544639420765610L;
    
}
