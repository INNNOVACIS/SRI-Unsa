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
import org.apache.poi.ss.usermodel.Cell;
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
        //Create row object
        XSSFRow row;
        int rowid = 0;
        for (ArrayList<String> list : listaObjetosSend) {
            row = spreadsheet.createRow(rowid++);
            int cellid = 0;
            for (String obj : list) {
                Cell cell = row.createCell(cellid++);
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
