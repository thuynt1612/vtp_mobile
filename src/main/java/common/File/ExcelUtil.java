package common.File;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtil {
//    private ExcelUtil() {
//    }
//
//    public static Map<String, FieldInformation> getMapExcel(Class<?> dataClass) {
//        Map<String, FieldInformation> map = new HashMap<>();
//        Field[] fields = dataClass.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
//            Excel column = fields[i].getAnnotation(Excel.class);
//            FieldInformation fieldInfo = new FieldInformation();
//            fieldInfo.setNameField(fields[i].getName());
//            fieldInfo.setTypeOfField(fields[i].getType());
//            fieldInfo.setTitle(column.title());
//            map.put(String.valueOf(i), fieldInfo);
//        }
//        return map;
//    }
//
//    @Getter
//    @Setter
//    public static class FieldInformation {
//        private Class<?> typeOfField;
//        private String nameField;
//        private String title;
//    }
//
//    public static void exportToExcel(String filePath, Class<?> dataClass, List<?> listData) throws IOException {
//        Map<String, FieldInformation> map = getMapExcel(dataClass);
//        try (XSSFWorkbook wb = new XSSFWorkbook();) {
//            XSSFSheet sheet = wb.createSheet("Sheet1");
//            CellStyle headerStyle = wb.createCellStyle();
//            headerStyle.setAlignment(HorizontalAlignment.CENTER);
//            headerStyle.setWrapText(true);
//            CellStyle cellStyle = wb.createCellStyle();
//            cellStyle.setAlignment(HorizontalAlignment.CENTER);
//            Row rowHeaderColumn = sheet.createRow(0);
//            Map<Integer, Integer> maxWidth = new HashMap<>();
//            int index = 0;
//            for (int i = 0; i < map.size(); i++) {
//                String cellHeaderName = map.get(String.valueOf(i)).title;
//                Cell rowCell = rowHeaderColumn.createCell(index);
//                rowCell.setCellStyle(headerStyle);
//                rowCell.setCellValue(cellHeaderName);
//                maxWidth.put(index, rowCell.getStringCellValue().getBytes().length * 256 + 200);
//                index++;
//            }
//            for (int i = 0; i < map.keySet().size(); i++) {
//                sheet.setColumnWidth(i, maxWidth.get(i));
//            }
//            for (int i = 0; i < listData.size(); i++) {
//                Row row = sheet.createRow(i + 1);
//                row.setHeight((short) (25 * 18));
//                for (int j = 0; j < map.size(); j++) {
//                    FieldInformation fieldInfo = map.get(String.valueOf(j));
//                    Object value = null;
//                    try {
//                        Method method = listData.get(j).getClass().getMethod("get" + toUpperCaseFirstOne(fieldInfo.getNameField()));
//                        value = method.invoke(listData.get(j));
//                        if (fieldInfo.getTypeOfField() == Date.class) {
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                            value = sdf.format(value);
//                        }
//                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                        value = "";
//                    }
//                    Cell rowCell = row.createCell(j);
//                    rowCell.setCellStyle(cellStyle);
//                    rowCell.setCellValue(value.toString());
//                }
//            }
//            try {
//                FileOutputStream out = new FileOutputStream(filePath);
//                wb.write(out);
//                out.flush();
//                out.close();
//            } catch (FileNotFoundException e) {
//                throw new FileNotFoundException("Xuất thất bại!" + e);
//            } catch (IOException e) {
//                throw new IOException("Xuất thất bại!" + e);
//            }
//        }
//    }
//
//    public static String toUpperCaseFirstOne(String str) {
//        return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
//    }
//
//
//    public static List<?> getListData(String pathFile, String sheetName, Class<?> dataClass) {
//        try {
//            Map<String, FieldInformation> map = getMapExcel(dataClass);
//            List<Object> list = new ArrayList();
//            Workbook wb = null;
//            String fileType = pathFile.substring(pathFile.lastIndexOf("."));
//            InputStream is = new FileInputStream(pathFile);
//            if (fileType.equals("xls")) {
//                wb = new HSSFWorkbook(is);
//            } else if (fileType.equals("xlsx")) {
//                wb = new XSSFWorkbook(is);
//            }
//            int rowNum_x = -1;
//            Map<String, Integer> cellmap = new HashMap<String, Integer>(20);
//            Sheet sheet = wb.getSheet(sheetName);
//            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
//                Row row = sheet.createRow(rowNum);
//                if (row == null) continue;
//                boolean flag = false;
//                for (int i = 0; i < row.getLastCellNum(); i++) {
//                    if (row.getCell(i) != null && !row.getCell(i).toString().trim().equals("")) flag = true;
//                }
//                if (!flag) continue;
//                if (rowNum_x == -1) {
//                    for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
//                        Cell cell = row.getCell(cellNum);
//                        if (cell == null) continue;
//                        String tempCellValue = cell.getStringCellValue();
//                        tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
//                        tempCellValue = tempCellValue.trim();
//                        for (String key : map.keySet()) {
//                            if (StringUtils.isNoneBlank(tempCellValue) && map.get(key).getTitle().equals(tempCellValue)) {
//                                rowNum_x = rowNum;
//                                cellmap.put(map.get(key).getNameField(), cellNum);
//                            }
//                        }
//                    }
//                } else {
//                    Object obj =
//                    for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
//
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static Object getValue(Cell cell, Class<?> fieldType) throws ParseException {
//        Object val = null;
//        if (cell.getCellType() == CellType.BOOLEAN) {
//            val = cell.getBooleanCellValue();
//        } else if (cell.getCellType() == CellType.NUMERIC) {
//            if (DateUtil.isCellDateFormatted(cell)) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    if (fieldType == String.class) {
//                        val = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
//                    } else {
//                        val = dateConvertFormat(sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())));
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (fieldType == String.class) {
//                    val = cell.getStringCellValue();
//                } else if (fieldType == BigDecimal.class) {
//                    val = new BigDecimal(cell.getNumericCellValue());
//                } else if (fieldType == double.class || fieldType == Double.class) {
//                    val = (double) cell.getNumericCellValue();
//                } else if (fieldType == long.class || fieldType == Long.class) {
//                    val = (long) cell.getNumericCellValue();
//                } else if (fieldType == int.class || fieldType == Integer.class) {
//                    val = (int) cell.getNumericCellValue();
//                } else if (fieldType == float.class || fieldType == Float.class) {
//                    val = (float) cell.getNumericCellValue();
//                } else if (fieldType == Short.class || fieldType == short.class) {
//                    val = (short) cell.getNumericCellValue();
//                } else {
//                    val = cell.getNumericCellValue();
//                }
//            }
//        } else if (cell.getCellType() == CellType.STRING) {
//            String cellValue = cell.getStringCellValue();
//            if (fieldType == BigDecimal.class) {
//                val = new BigDecimal(cellValue);
//            } else if (fieldType == Double.class || fieldType == double.class) {
//                val = Double.parseDouble(cellValue);
//            } else if (fieldType == Float.class || fieldType == float.class) {
//                val = Float.parseFloat(cellValue);
//            } else if (fieldType == Integer.class || fieldType == int.class) {
//                val = Integer.parseInt(cellValue);
//            } else if (fieldType == Short.class || fieldType == short.class) {
//                val = Short.parseShort(cellValue);
//            } else if (fieldType == Date.class) {
//                val = dateConvertFormat(cellValue);
//            } else {
//                val = cell;
//            }
//        }
//        return val;
//    }
//
//    public static Date dateConvertFormat(String dateStr) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse(dateStr);
//        return date;
//    }
//
//    public static void setValueField(Object obj, String field, Object value, Class<?> fieldType, int row, int column) throws Exception {
//        try {
//            Method method = obj.getClass().getMethod("set" + toUpperCaseFirstOne(field), fieldType);
//            method.invoke(obj, value);
//        } catch (Exception e) {
//            throw new Exception("Lỗi ở row = " + row + " column = " + column + " tại field = " + field);
//        }
//    }
}
