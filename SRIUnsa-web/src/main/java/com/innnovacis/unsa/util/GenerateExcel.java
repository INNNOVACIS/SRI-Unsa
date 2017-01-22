/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Innnovacis
 */
public class GenerateExcel {

    public GenerateExcel() {
    }
    
    private Cell getCell(XSSFRow row, int num, String text,
            CellStyle estilo) {
        Cell celda = row.createCell(num);        
        celda.setCellStyle(estilo);
        celda.setCellValue(text);
        
        return celda;
    }
    private CellStyle getEstiloDarkRedCell(XSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);  
        font.setBold(true);
        CellStyle estilo = workbook.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        estilo.setFillPattern(CellStyle.SOLID_FOREGROUND);            
        estilo.setFont(font);
        
        return estilo;        
    }
    private CellStyle getEstiloWhiteCell(XSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setColor(HSSFColor.DARK_RED.index);  
        font.setBold(true);
                
        CellStyle estilo = workbook.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        estilo.setFillPattern(CellStyle.SOLID_FOREGROUND);            
        estilo.setFont(font);
        
        return estilo;        
    }
    
    public byte[] getArrayByteFrom2(Map<String, Object> respuesta, int contador,
            String[] nombreColumnasCabeceras, String tituloPrincipal,
            ArrayList<ArrayList<String>> listaObjetosSend,
            SRIDocenteActivosInactivos docenteActivosInactivos,
            int numTiposActividades, String[] listNombresTiposActividades, 
            ArrayList<ArrayList<String>> listTiposActividades,int numFacultades, 
            String[] listNombreFacultades, ArrayList<ArrayList<String>> listFacultades
            ) throws FileNotFoundException, IOException {
        
        int numCeldas = 10;
        // Crear el libro excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Crear la hoja
        XSSFSheet spreadsheet = workbook.createSheet(tituloPrincipal);
        // Crear una fila
        XSSFRow row;
        int rowid = 0;
        int ceroCelda = 0;
        // Creando la primera linea de las estadisticas por docente
        row = spreadsheet.createRow(rowid++);
        CellStyle estiloTitulo = getEstiloDarkRedCell(workbook);
        Cell celdaTituloUno = getCell(row,ceroCelda, "Estadísticas de docentes investigando",
                estiloTitulo);
        spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, numCeldas-1));
        
        rowid++; // Para saltar una fila
        row = spreadsheet.createRow(rowid++);
        CellStyle estiloTexto = getEstiloWhiteCell(workbook);
        getCell(row,ceroCelda, "Docentes investigando: " + 
                docenteActivosInactivos.getNActivos(), estiloTexto);
        row = spreadsheet.createRow(rowid++);
        getCell(row,ceroCelda, "Docentes no investigando: " + 
                docenteActivosInactivos.getNInactivos(), estiloTexto);
        
        //row = spreadsheet.createRow(rowid++);
        row = spreadsheet.createRow(rowid++);
        int totalActivosInactivos = docenteActivosInactivos.getNActivos() + docenteActivosInactivos.getNInactivos();
        int totalActivos = (docenteActivosInactivos.getNActivos() * numCeldas ) / totalActivosInactivos;
        if(totalActivos <= 0)totalActivos++;
        int totalInactivos = totalActivosInactivos - numCeldas;
        
        for(int i = 0 ; i < numCeldas; i++) {
            String cantidad= "";
            if(i == 0) cantidad = String.valueOf(docenteActivosInactivos.getNActivos());
            if(i == totalActivos) cantidad = String.valueOf(docenteActivosInactivos.getNInactivos());
            Cell celdaActivos = getCell(row,i, cantidad,
                estiloTitulo);
        }
        
        // Creando la primera linea de las estadisticas por docente    
        rowid++; // Para saltar una fila
        row = spreadsheet.createRow(rowid++);
        Cell celdaTituloDos = getCell(row,ceroCelda, "Estadísticas de Actividades por Tipo",
                estiloTitulo);
        spreadsheet.addMergedRegion(new CellRangeAddress(rowid-1, rowid-1, 0, numCeldas-1));
        
        rowid++; // Para saltar una fila
        for(int i = 0 ; i < listTiposActividades.size(); i++) {
            row = spreadsheet.createRow(rowid++);
            getCell(row,0, listTiposActividades.get(i).get(0) + " " +
                    listTiposActividades.get(i).get(1), estiloTexto);
        }
                
        // Creando la primera linea de las estadisticas por docente
        rowid++; // Para saltar una fila
        row = spreadsheet.createRow(rowid++);
        Cell celdaTituloTres = getCell(row,ceroCelda, "Estadísticas de docentes por Facultad",
                estiloTitulo);
        spreadsheet.addMergedRegion(new CellRangeAddress(rowid-1,rowid-1, 0, numCeldas-1));
        
        rowid++; // Para saltar una fila
        row = spreadsheet.createRow(rowid++);
        for(int i = 0 ; i < listNombreFacultades.length; i++) {
            getCell(row,i, listNombreFacultades[i], estiloTexto);
        }
        for(int i = 0 ; i < listFacultades.size(); i++){
            row = spreadsheet.createRow(rowid++);
            for(int j = 0 ; j < listFacultades.get(i).size(); j++) {
                getCell(row,j,listFacultades.get(i).get(j),estiloTexto);
            }
        }
        
        // Creando la primera linea de las estadisticas por docente
        rowid++; // Para saltar una fila
        row = spreadsheet.createRow(rowid++);
        Cell celdaTituloCuatro = getCell(row,ceroCelda, "Profesores",
                estiloTitulo);
        spreadsheet.addMergedRegion(new CellRangeAddress(rowid-1, rowid-1, 0, numCeldas-1));
        
        
        ceroCelda = 0;
        row = spreadsheet.createRow(rowid++);
        Cell celdablank = row.createCell(ceroCelda);
        
        // Creando la cabecera de los titulos 
        row = spreadsheet.createRow(rowid++);
        ceroCelda = 0;
        for (String obj : nombreColumnasCabeceras) {
            Cell cell = row.createCell(ceroCelda);
            
            CellStyle estilo = workbook.createCellStyle();
            estilo.setWrapText(true);
            estilo.setVerticalAlignment(CellStyle.VERTICAL_TOP);
            estilo.setAlignment(CellStyle.ALIGN_CENTER);
            estilo.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            estilo.setFillPattern(CellStyle.SOLID_FOREGROUND); 
                
            int cwidth = spreadsheet.getColumnWidth(ceroCelda);
            spreadsheet.autoSizeColumn(ceroCelda);
            cwidth += 2100;
            spreadsheet.setColumnWidth(ceroCelda, cwidth);
            
            Font font = workbook.createFont();
            font.setColor(HSSFColor.WHITE.index);              
            estilo.setFont(font);
                
            cell.setCellStyle(estilo);
            cell.setCellValue((String) obj);
            
            ceroCelda++;
        }
        ceroCelda = 0;
        for (ArrayList<String> list : listaObjetosSend) {
            row = spreadsheet.createRow(rowid++);
            int cellid = 0;
            for (String obj : list) {
                Cell cell = row.createCell(cellid++);
                
                CellStyle borderStyle = workbook.createCellStyle();
                borderStyle.setWrapText(true);
                borderStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
                borderStyle.setAlignment(CellStyle.ALIGN_LEFT);        
		borderStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                borderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); 
                Font font = workbook.createFont();
                font.setColor(HSSFColor.BLACK.index);              
                borderStyle.setFont(font);
                cell.setCellStyle(borderStyle);
            
                cell.setCellValue((String) obj);
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        byte[] bytes = bos.toByteArray();

        bos.close();

        return bytes;
    }
    
    public byte[] getArrayByteFrom(Map<String, Object> respuesta, int contador,
            String[] nombreColumnasCabeceras, String tituloPrincipal,
            ArrayList<ArrayList<String>> listaObjetosSend) throws FileNotFoundException, IOException {
        
        // Crear el libro excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Crear la hoja
        XSSFSheet spreadsheet = workbook.createSheet(tituloPrincipal);
        // Crear una fila
        XSSFRow row;
        int rowid = 0;
        int ceroCelda = 0;
        // Creando la primera linea de las estadisticas por docente
        row = spreadsheet.createRow(rowid++);
        CellStyle estiloTituloUno = getEstiloDarkRedCell(workbook);
        Cell celdaTituloUno = getCell(row,ceroCelda, "Estadísticas de docentes investigando",
                estiloTituloUno);
        spreadsheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
        
        // Creando la cabecera de los titulos 
        row = spreadsheet.createRow(rowid++);
        ceroCelda = 0;
        for (String obj : nombreColumnasCabeceras) {
            Cell cell = row.createCell(ceroCelda);
            
            CellStyle estilo = workbook.createCellStyle();
            estilo.setWrapText(true);
            estilo.setVerticalAlignment(CellStyle.VERTICAL_TOP);
            estilo.setAlignment(CellStyle.ALIGN_CENTER);
            estilo.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            estilo.setFillPattern(CellStyle.SOLID_FOREGROUND); 
                
            int cwidth = spreadsheet.getColumnWidth(ceroCelda);
            spreadsheet.autoSizeColumn(ceroCelda);
            cwidth += 2100;
            spreadsheet.setColumnWidth(ceroCelda, cwidth);
            
            Font font = workbook.createFont();
            font.setColor(HSSFColor.WHITE.index);              
            estilo.setFont(font);
                
            cell.setCellStyle(estilo);
            cell.setCellValue((String) obj);
            
            ceroCelda++;
        }
        ceroCelda = 0;
        for (ArrayList<String> list : listaObjetosSend) {
            row = spreadsheet.createRow(rowid++);
            int cellid = 0;
            for (String obj : list) {
                Cell cell = row.createCell(cellid++);
                
                CellStyle borderStyle = workbook.createCellStyle();
                borderStyle.setWrapText(true);
                borderStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
                borderStyle.setAlignment(CellStyle.ALIGN_LEFT);        
		borderStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                borderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); 
                Font font = workbook.createFont();
                font.setColor(HSSFColor.BLACK.index);              
                borderStyle.setFont(font);
                cell.setCellStyle(borderStyle);
            
                cell.setCellValue((String) obj);
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        byte[] bytes = bos.toByteArray();

        bos.close();

        return bytes;
    }
}
