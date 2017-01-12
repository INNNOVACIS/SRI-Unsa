/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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
        // Creando la cabecera principal
        row = spreadsheet.createRow(rowid++);
        int ceroCelda = 0;
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
