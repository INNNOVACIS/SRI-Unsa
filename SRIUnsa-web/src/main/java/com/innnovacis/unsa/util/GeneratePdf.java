/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.awt.Color;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;

import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Innnovacis
 */
public class GeneratePdf {
        
    final Color colorFondoUnochart = new Color(205, 78, 81);
    final Color colorFondoDoschart = new Color(254, 216, 244);
    
    final BaseColor colorFondoTableClaro = new BaseColor(243, 224, 226);
    final BaseColor colorTextoTableClaro = new BaseColor(70, 70, 70);
    final Font fuenteTextoTableClaro = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, colorTextoTableClaro);
    final Font fuentePrincipal = new Font(FontFamily.HELVETICA, 14, Font.NORMAL,  GrayColor.GRAYWHITE);
    
    final LineSeparator line = new LineSeparator();
    
    final String TEXT_DOCENTES = "Docentes";
    
    public GeneratePdf() {
        line.setOffset(-2);
    }
    // Se supone que para este grafico solo deberian llegar dos datos,
    // uno para el numero de docentes investigando y otro para el nro de docentes
    // no investigando
    private CategoryDataset getDataSetDocentesInvestigando(int numDocentesActivos,
            String[] listNombreDocentesActivos,ArrayList<ArrayList<Integer>> listDocentesActivos) { 
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        result.addValue(listDocentesActivos.get(0).get(0), listNombreDocentesActivos[0], TEXT_DOCENTES);
        result.addValue(listDocentesActivos.get(0).get(1), listNombreDocentesActivos[1], TEXT_DOCENTES);
        
        return result;
    }
    
    private CategoryDataset createDatasetFacultades(int numFacultades,
            String[] listNombreFacultades, ArrayList<ArrayList<String>> listFacultades) { 
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        
        for(int i = 0 ; i < listFacultades.size();i++){
            result.addValue(Integer.parseInt(listFacultades.get(i).get(2)),
                    "# Docentes investigando", listFacultades.get(i).get(0));
            result.addValue(Integer.parseInt(listFacultades.get(i).get(3)),
                    "# Docentes no investigando", listFacultades.get(i).get(0));
        }
        
        return result;
    }
    
    private JFreeChart createChart(final CategoryDataset dataset,int numDocentesActivos,
            String[] listNombreDocentesActivos,ArrayList<ArrayList<Integer>> listDocentesActivos) {
        
        final JFreeChart chart = ChartFactory.createStackedBarChart(//createBarChart(
            "",         // chart title
            "",                 // domain axis label
            "",                // range axis label
            dataset,                    // data
            PlotOrientation.HORIZONTAL, // orientation
            true,                       // include legend
            true,
            false
        );
        
        chart.getLegend().setFrame(BlockBorder.NONE);

        final CategoryPlot plot = chart.getCategoryPlot();
        ((BarRenderer) plot.getRenderer())
                .setBarPainter(new StandardBarPainter());
        
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setBackgroundPaint( new Color(255,255,255,0) );
        plot.setBackgroundImageAlpha(0.0f);
        plot.setOutlineVisible(false);
        
        GroupedStackedBarRenderer rendererA = new GroupedStackedBarRenderer();
        rendererA.setBarPainter(new StandardBarPainter());
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup(listNombreDocentesActivos[0], "G1");
        map.mapKeyToGroup(listNombreDocentesActivos[1], "G1");
        rendererA.setSeriesToGroupMap(map);
        //rendererA.setDrawBarOutline(true);
        rendererA.setShadowVisible(false);
        rendererA.setSeriesPaint(1, colorFondoUnochart);
        rendererA.setSeriesPaint(2, colorFondoDoschart);
        
        //rendererA.setSeriesOutlinePaint(0, new Color(204, 121, 167));
        //rendererA.setSeriesOutlinePaint(1, new Color(213, 94, 0));
        
        rendererA.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        rendererA.setBaseItemLabelsVisible(true);

        plot.setRenderer(rendererA);
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, listDocentesActivos.get(0).get(0) +
                listDocentesActivos.get(0).get(1) + 1);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVisible(false);
        rangeAxis.setTickLabelsVisible(false);
        
        return chart;        
    }
    
    private JFreeChart createChartFacultades(final CategoryDataset dataset,int numFacultades,
            String[] listNombreFacultades, ArrayList<ArrayList<String>> listFacultades) {
        
        java.awt.Font font3 = new java.awt.Font("Dialog", java.awt.Font.PLAIN, 8); 
        final JFreeChart chart = ChartFactory.createStackedBarChart(//createBarChart(
            "",         // chart title
            "",                 // domain axis label
            "",                // range axis label
            dataset,                    // data
            PlotOrientation.HORIZONTAL, // orientation
            true,                       // include legend
            true,
            false
        );
        
        chart.getLegend().setFrame(BlockBorder.NONE);
        
        final CategoryPlot plot = chart.getCategoryPlot();
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        CategoryAxis axisCategory = plot.getDomainAxis();
        axisCategory.setLabelFont(font3);
        axisCategory.setTickLabelFont(font3);
         
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        //plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setBackgroundPaint( new Color(255,255,255,0) );
        plot.setBackgroundImageAlpha(0.0f);
        plot.setOutlineVisible(false);
        
        GroupedStackedBarRenderer rendererA = new GroupedStackedBarRenderer();
        rendererA.setBarPainter(new StandardBarPainter());
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup("# Docentes investigando", "G1");
        map.mapKeyToGroup("# Docentes no investigando", "G1");
        rendererA.setSeriesToGroupMap(map);
        //rendererA.setDrawBarOutline(true);
        rendererA.setShadowVisible(false);
        rendererA.setSeriesPaint(1, new Color(160, 219, 203));
        rendererA.setSeriesPaint(2, new Color(160, 202, 226));
        
        rendererA.setSeriesOutlinePaint(0, new Color(204, 121, 167));
        rendererA.setSeriesOutlinePaint(1, new Color(213, 94, 0));
        
        rendererA.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        rendererA.setBaseItemLabelsVisible(true);

        plot.setRenderer(rendererA);
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setRange(0.0, DocentesInvestigando + DocentesNoInvestigando + 1);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVisible(false);
        rangeAxis.setTickLabelsVisible(false);
                
        final CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();        
        domainAxis.setMaximumCategoryLabelLines(2);
        
        return chart;
    }
    
    private PdfPCell createCellMainHeader(String name) {        
        PdfPCell cell = new PdfPCell(new Phrase(name, fuentePrincipal));
        cell.setBackgroundColor(new BaseColor(146, 3, 11));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(10);
        cell.setPadding(10);
        cell.setBorderWidth(2);
        cell.setBorderColor(GrayColor.WHITE);
        return cell;
    }
    
    private PdfPCell createCellHeader(String name) {
        PdfPCell cellt = new PdfPCell(new Phrase(name, fuenteTextoTableClaro));
        cellt.setBackgroundColor(colorFondoTableClaro);
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
        cellte.setBackgroundColor(colorFondoTableClaro);
        cellte.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellte.setPadding(5);
        cellte.setBorderWidthTop(2);
        cellte.setBorderWidthBottom(0);        
        cellte.setBorderWidthLeft(0);
        cellte.setBorderWidthRight(0);
        cellte.setBorderColor(GrayColor.WHITE);

        return cellte;
    }
    
    private PdfPCell createCellTipoInvestigacion(String name) {
        PdfPCell cellte = new PdfPCell((new Phrase( name, fuentePrincipal)));
        cellte.setBackgroundColor(new BaseColor(146, 3, 11));
        cellte.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellte.setPadding(10);
        cellte.setBorderWidth(3);
        cellte.setBorderColor(GrayColor.WHITE);

        return cellte;
    }
    
    private PdfPCell createCellActividades(String name) {        
        PdfPCell cellte = new PdfPCell((new Phrase( name,fuenteTextoTableClaro)));
        cellte.setBackgroundColor(colorFondoTableClaro);
        cellte.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellte.setPadding(10);
        cellte.setBorderWidth(3);
        cellte.setBorderColor(GrayColor.WHITE);

        return cellte;
    }
    
    public PdfPTable getTableInvestigaciones(int numTiposActividades, 
            String[] listNombresTiposActividades, ArrayList<ArrayList<String>> listTiposActividades){
        
        PdfPTable table = new PdfPTable(listTiposActividades.size());
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        table.getDefaultCell().setBackgroundColor(new BaseColor(247, 247, 247));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                
        for(int i = 0; i < listTiposActividades.size() ;i++) {
            PdfPCell celda0 = createCellTipoInvestigacion(listTiposActividades.get(i).get(0));
            table.addCell(celda0);
        }
        for(int i = 0; i < listTiposActividades.size() ;i++) {
            PdfPCell celda0a = createCellActividades(listTiposActividades.get(i).get(1) + " Actividades");
            table.addCell(celda0a);
        }
        return table;
    }
    
    public byte[] getArrayByteFromPDFMultiplesTablas(String tituloPrincipal,int numUsuariosDocentes,
            String[] listColumnasUsuariosDocentes, ArrayList<ArrayList<String>> listUsuariosDocentes,
            int numTiposActividades, String[] listNombresTiposActividades, ArrayList<ArrayList<String>> listTiposActividades,
            int numFacultades, String[] listNombreFacultades, ArrayList<ArrayList<String>> listFacultades,
            int numDocentesActivos, String[] listNombreDocentesActivos,ArrayList<ArrayList<Integer>> listDocentesActivos
    ) throws IOException, DocumentException {
        
        int width = 700;
        int height = 75;  
        
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        Document documento = getDocumentNew();
        PdfWriter writer = PdfWriter.getInstance(documento, baosPDF);
        
        documento.open();
        
        Paragraph primerTitulo = new Paragraph("Estadísticas de docentes investigando");        
        primerTitulo.add(line);        
        documento.add(primerTitulo);
        
        final CategoryDataset dataset = getDataSetDocentesInvestigando(numDocentesActivos,
                listNombreDocentesActivos, listDocentesActivos);
        JFreeChart chart = createChart(dataset,numDocentesActivos,
                listNombreDocentesActivos, listDocentesActivos);
        
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        documento.add(image);
            
        Paragraph segundoTitulo = new Paragraph("Estadísticas de Actividades por Tipo");
        segundoTitulo.add(line);        
        documento.add(segundoTitulo);
        documento.add( Chunk.NEWLINE );
        
        PdfPTable tableActividades = getTableInvestigaciones(numTiposActividades,
                listNombresTiposActividades, listTiposActividades);
        documento.add(tableActividades);        
        
        documento.newPage();
        Paragraph tercerTitulo = new Paragraph("Estadísticas de docentes por Facultad");
        tercerTitulo.add(line);        
        documento.add(tercerTitulo);
        
        
        height = 450;    
        final CategoryDataset datasetFacultades = createDatasetFacultades(numFacultades,
                listNombreFacultades, listFacultades);
        JFreeChart chartFacultades = createChartFacultades(datasetFacultades,numFacultades,
                listNombreFacultades, listFacultades);
        
        BufferedImage bufferedImageFacultadoes = chartFacultades.createBufferedImage(width, height);
        Image imageFacultades = Image.getInstance(writer, bufferedImageFacultadoes, 1.0f);
        documento.add(imageFacultades);
        
        documento.newPage();
        
        Paragraph cuartoTitulo = new Paragraph("Profesores");
        cuartoTitulo.add(line);        
        documento.add(cuartoTitulo);
        documento.add( Chunk.NEWLINE );
        
        PdfPTable table = new PdfPTable(numUsuariosDocentes);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        // Creacion de la cabecera principal de la tabla
        table.addCell(createCellMainHeader(tituloPrincipal));
        
        // Cabeceras sub principales
        for(int i = 0 ; i < numUsuariosDocentes;i++) {
            table.addCell(createCellHeader(listColumnasUsuariosDocentes[i]));
        }

        table.setHeaderRows(2);
        table.getDefaultCell().setBackgroundColor(new BaseColor(247, 247, 247));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // Creamos los Items de la tabla
        for (int counter = 0; counter < listUsuariosDocentes.size(); counter++) {         
            ArrayList<String> arrayDatosObjeto = listUsuariosDocentes.get(counter);
            for (int j = 0; j < numUsuariosDocentes; j++) {
                PdfPCell celda = createCell();
                celda.addElement(new Phrase( arrayDatosObjeto.get(j), fuenteTextoTableClaro));
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
    
    public byte[] getArrayByteFrom(Map<String, Object> respuesta, int contador,
            String[] nombreColumnasCabeceras, String tituloPrincipal,
            ArrayList<ArrayList<String>> listaObjetosSend) throws IOException, DocumentException {

        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        Document documento = getDocumentNew();
        PdfWriter writer = PdfWriter.getInstance(documento, baosPDF);
        
        documento.open();
        
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
            ArrayList<String> arrayDatosObjeto = listaObjetosSend.get(counter);
            for (int j = 0; j < contador; j++) {
                PdfPCell celda = createCell();
                celda.addElement(new Phrase( arrayDatosObjeto.get(j), fuenteTextoTableClaro));
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
    
    private Document getDocumentNew(){
        Document documento = new Document(PageSize.A4.rotate());
        // Propiedades del documento
        documento.addAuthor("Innnóvacis");
        documento.addCreationDate();
        documento.addProducer();
        documento.addCreator("innnovacis.com");
        documento.addTitle("Innnóvacis");
        
        return documento;
    }
}
