/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacionGenerada")
@RequestScoped
public class ActividadInvestigacionGeneradaRestService {

    @Inject
    private Logger log;

    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;

    @POST
    @Path("/actividades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesGeneradas(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradas(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesGeneradas - Success : {0}", entidad.toString());
        } catch (Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesGeneradas - Error : {0}", ex.getMessage());
        }

        return builder.build();
    }

    
    @POST
    @Path("/GetActividadesGeneradasHomeDocente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesGeneradasHomeDocente(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradasHomeDocente(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesGeneradasHomeDocente - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesGeneradasHomeDocente - Error : {0}", ex.getMessage());
        }

        return builder.build();
    }

    //@Produces("application/pdf")
    @POST
    @Path("/descargarPdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarPdf(SRIPaginacion entidad) {

        try {
            ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();

            //File outFile = new File("\\try\\frombytes.pdf");
            Document documento = new Document();
            //PdfWriter.getInstance(documento, new FileOutputStream("\\try\\test.pdf"));
            PdfWriter writer = PdfWriter.getInstance(documento, baosPDF);
            documento.addAuthor("innnovacis");
            documento.addCreationDate();
            documento.addProducer();
            documento.addCreator("innnovacis.com");
            documento.addTitle("innnovacis");
            documento.setPageSize(PageSize.A4);
            documento.open();

            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(200);
            table.setWidths(new int[]{1, 10});
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cell;
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.addElement(new Paragraph("Label"));
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            List list = new List(List.UNORDERED);
            list.add(new ListItem(new Chunk("Value 1")));
            list.add(new ListItem(new Chunk("Value 2")));
            list.add(new ListItem(new Chunk("Value 3")));
            cell.addElement(list);
            table.addCell(cell);
            documento.add(table);
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            documento.add(new Paragraph("Prueba esta e suna prueba para generar PDFS. Es un nuevo caso"));
            

            documento.close();
            int longitud = baosPDF.toByteArray().length;
            byte[] blobAsBytes = new byte[longitud];
            blobAsBytes = baosPDF.toByteArray();
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documento13.pdf")
                    .build();

        } catch (DocumentException ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.pdf")
                .build();
    }
}
