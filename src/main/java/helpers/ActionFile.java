package helpers;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class ActionFile {
        public static Map<String, String> getDataPropertiesFile(String pathProp) {
            Properties prop = new Properties();
            Map<String, String> map = new HashMap<>();
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(pathProp);
                prop.load(inputStream);
                for (String key : prop.stringPropertyNames()) {
                    String value = prop.getProperty(key);
                    map.put(key, value);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Some issue finding or loading file....!!! " + e.getMessage());
            }
            return map;
        }

        public static String readTxtFile(String path) {

            String text = "";
            try {
                File file = new File(path);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    //process the line
                    text = text + myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
            return text;
        }

        public static String replaceDataByObjectXmlFile(String path, Object obj) throws IOException {
            File inputXML = new File(path);
            BufferedReader br = null;
            String newString = "";
            StringBuilder strTotale = new StringBuilder();
            String strTotaleFinal = "";
            FileReader reader = new FileReader(inputXML);
            br = new BufferedReader(reader);
            try {
                while ((newString = br.readLine()) != null) {
                    strTotale.append(newString + "\n");
                }
                strTotaleFinal = strTotale.toString();
                Class<?> aclass = obj.getClass();
                Field[] fields = aclass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    strTotaleFinal = strTotaleFinal.replace("{" + fields[i].getName() + "}", "" + fields[i].get(obj) + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    br.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
            return strTotaleFinal;
        }

        public static String replaceDataByObject(Object obj, String doc) {
            try {
                Class<?> aclass = obj.getClass();
                Field[] fields = aclass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    doc = doc.replace("{" + fields[i].getName() + "}", "" + fields[i].get(obj) + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }

        public static String readFileToString(String path) {
            String text = "";
            try {
                File file = new File(path);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    //process the line
                    text += myReader.nextLine();
                    text += "\n";
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return text;
        }

//    public static Document convertStringToDocument(String strXml) {
//        try {
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            documentBuilderFactory.setNamespaceAware(true);
//            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(strXml));
//            return builder.parse(is);
//        } catch (ParserConfigurationException | IOException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
