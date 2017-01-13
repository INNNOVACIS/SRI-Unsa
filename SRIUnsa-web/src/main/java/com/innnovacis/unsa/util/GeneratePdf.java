/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.awt.Color;
import java.awt.Paint;

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
import java.awt.BasicStroke;
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
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
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
    
    int DocentesInvestigando = 5;
    int DocentesNoInvestigando = 30;
    
    final BaseColor colorFondoTableClaro = new BaseColor(243, 224, 226);
    final BaseColor colorTextoTableClaro = new BaseColor(70, 70, 70);
    
    public GeneratePdf() {

    }
    private CategoryDataset getDataSetDocentesInvestigando() { 
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        result.addValue(DocentesInvestigando, "# Docentes investigando", "Docentes");
        result.addValue(DocentesNoInvestigando, "# Docentes no investigando", "Docentes");
        
        return result;
    }
    
    private CategoryDataset createDatasetFacultades() { 
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        result.addValue(20.3, "# Docentes investigando", "DEPARTAMENTO ACADEMICO DE CS. FISIOLOGICAS");
        result.addValue(27.2, "# Docentes investigando", "DEPARTAMENTO ACADEMICO DE CIRUGIA");
        result.addValue(25, "# Docentes investigando", "DEPARTAMENTO ACADEMICO DE MEDICINA");
        result.addValue(23, "# Docentes investigando", "DEPARTAMENTO ACADEMICO DE MICROBIOLOGIA Y PATOLOGIA");
        result.addValue(21, "# Docentes investigando", "DEPARTAMENTO ACADEMICO DE MORFOLOGIA HUMANA");
        result.addValue(25, "# Docentes no investigando", "DEPARTAMENTO ACADEMICO DE CS. FISIOLOGICAS");
        result.addValue(24, "# Docentes no investigando", "DEPARTAMENTO ACADEMICO DE CIRUGIA");
        result.addValue(14, "# Docentes no investigando", "DEPARTAMENTO ACADEMICO DE MEDICINA");
        result.addValue(16, "# Docentes no investigando", "DEPARTAMENTO ACADEMICO DE MICROBIOLOGIA Y PATOLOGIA");
        result.addValue(19, "# Docentes no investigando", "DEPARTAMENTO ACADEMICO DE MORFOLOGIA HUMANA");
        
        return result;
    }
    
    private JFreeChart createChart(final CategoryDataset dataset) {
        
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
        
        /*
        SubCategoryAxis domainAxis = new SubCategoryAxis("Product / Month");
        domainAxis.setCategoryMargin(0.05);
        domainAxis.addSubCategory("Product 1");
        domainAxis.addSubCategory("Product 2");
        domainAxis.addSubCategory("Product 3");*/

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        ((BarRenderer) plot.getRenderer())
                .setBarPainter(new StandardBarPainter());
        
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        //plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setBackgroundPaint( new Color(255,255,255,0) );
        plot.setBackgroundImageAlpha(0.0f);
        plot.setOutlineVisible(false);
        
        //CategoryItemRenderer renderer = new CustomRenderer();
        //plot.setRenderer(renderer);
        
        //GroupedStackedBarRenderer rendererA = (GroupedStackedBarRenderer) plot.getRenderer();//new GroupedStackedBarRenderer();
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
        rangeAxis.setRange(0.0, DocentesInvestigando + DocentesNoInvestigando + 1);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVisible(false);
        rangeAxis.setTickLabelsVisible(false);
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    private JFreeChart createChartFacultades(final CategoryDataset dataset) {
        
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
        
        /*
        SubCategoryAxis domainAxis = new SubCategoryAxis("Product / Month");
        domainAxis.setCategoryMargin(0.05);
        domainAxis.addSubCategory("Product 1");
        domainAxis.addSubCategory("Product 2");
        domainAxis.addSubCategory("Product 3");*/

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        //plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setBackgroundPaint( new Color(255,255,255,0) );
        plot.setBackgroundImageAlpha(0.0f);
        plot.setOutlineVisible(false);
        
        //CategoryItemRenderer renderer = new CustomRenderer();
        //plot.setRenderer(renderer);
        
        //GroupedStackedBarRenderer rendererA = (GroupedStackedBarRenderer) plot.getRenderer();//new GroupedStackedBarRenderer();
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
        rangeAxis.setRange(0.0, DocentesInvestigando + DocentesNoInvestigando + 1);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVisible(false);
        rangeAxis.setTickLabelsVisible(false);
        
        final CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();        
        domainAxis.setMaximumCategoryLabelLines(2);
        
        java.awt.Font font3 = new java.awt.Font("Dialog", java.awt.Font.PLAIN, 8); 
        plot.getDomainAxis().setLabelFont(font3);
        plot.getRangeAxis().setLabelFont(font3);
        
        return chart;
        
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
        Font ftPrincipal = new Font(FontFamily.HELVETICA, 14, Font.NORMAL,  GrayColor.GRAYWHITE);
        PdfPCell cellte = new PdfPCell((new Phrase( name, ftPrincipal)));
        cellte.setBackgroundColor(new BaseColor(146, 3, 11));
        cellte.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellte.setPadding(10);
        cellte.setBorderWidth(3);
        cellte.setBorderColor(GrayColor.WHITE);

        return cellte;
    }
    private PdfPCell createCellActividades(String name) {
        Font ft = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, colorTextoTableClaro);
        
        PdfPCell cellte = new PdfPCell((new Phrase( name,ft)));
        cellte.setBackgroundColor(colorFondoTableClaro);
        cellte.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellte.setPadding(10);
        cellte.setBorderWidth(3);
        cellte.setBorderColor(GrayColor.WHITE);

        return cellte;
    }
    
    
    public PdfPTable getTableInvestigaciones(){
        
        int nroColumnas = 4;
        
        PdfPTable table = new PdfPTable(nroColumnas);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        
        table.getDefaultCell().setBackgroundColor(new BaseColor(247, 247, 247));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Font ft = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(70, 70, 70));
        
        //Cabeceras
        PdfPCell celda0 = createCellTipoInvestigacion("Investigación Formativa");
        table.addCell(celda0);
        
        PdfPCell celda1 = createCellTipoInvestigacion("Asesoría de Tesis");
        table.addCell(celda1);
        
        PdfPCell celda2 = createCellTipoInvestigacion("Investigaciones Básicas y Aplicadas");
        table.addCell(celda2);
        
        PdfPCell celda3 = createCellTipoInvestigacion("Producción Intelectual");
        table.addCell(celda3);
        
        //Actividades
        PdfPCell celda0a = createCellActividades("8 Actividades");
        table.addCell(celda0a);
        
        PdfPCell celda1a = createCellActividades("0 Actividades");
        table.addCell(celda1a);
        
        PdfPCell celda2a = createCellActividades("0 Actividades");
        table.addCell(celda2a);
        
        PdfPCell celda3a = createCellActividades("0 Actividades");
        table.addCell(celda3a);
        
        return table;
    }
    
    public byte[] getArrayByteFrom2(int contador,
            String[] nombreColumnasCabeceras, String tituloPrincipal,
            ArrayList<ArrayList<String>> listaObjetosSend) throws IOException, DocumentException {
        
        LineSeparator line = new LineSeparator();
        line.setOffset(-2);
        
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
        
        Paragraph primerTitulo = new Paragraph("Estadísticas de docentes investigando");        
        primerTitulo.add(line);        
        documento.add(primerTitulo);
        
        int width = 700;
        int height = 75;            
        final CategoryDataset dataset = getDataSetDocentesInvestigando();
        JFreeChart chart = createChart(dataset);
        
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        documento.add(image);
            
        Paragraph segundoTitulo = new Paragraph("Estadísticas de Actividades por Tipo");
        segundoTitulo.add(line);        
        documento.add(segundoTitulo);
        documento.add( Chunk.NEWLINE );
        
        PdfPTable tableActividades = getTableInvestigaciones();
        documento.add(tableActividades);        

        documento.add( Chunk.NEWLINE );
        Paragraph tercerTitulo = new Paragraph("Estadísticas de docentes por Facultad");
        tercerTitulo.add(line);        
        documento.add(tercerTitulo);
        documento.newPage();
        
        height = 300;    
        final CategoryDataset datasetFacultades = createDatasetFacultades();
        JFreeChart chartFacultades = createChartFacultades(datasetFacultades);
        
        BufferedImage bufferedImageFacultadoes = chartFacultades.createBufferedImage(width, height);
        Image imageFacultades = Image.getInstance(writer, bufferedImageFacultadoes, 1.0f);
        documento.add(imageFacultades);
        
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
