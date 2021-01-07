package pl.kskowronski.data.reaports;

import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.xml.sax.InputSource;
import pl.kskowronski.data.service.egeria.ek.UserRepo;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.*;
import java.net.URL;
import java.util.*;

@Service
public class Pit11Service {

    @Autowired
    private UserRepo userRepo;

    public String exportPit11Report(String reportFormat, String workerID, String yearPit, String xmlPit11) throws FileNotFoundException, JRException {
        String path = "C:\\tmp\\";

        //load file and compile it
        ClassLoader cl = this.getClass().getClassLoader();
        URL url =  cl.getResource("pit11_26.jrxml");

        String absolutePath = url.getPath(); //+ "\\"

        if (!absolutePath.toUpperCase().substring(1,3).equals("C:")){ //todo better check system operation
            absolutePath = "/home/szeryf/kskowronski_projects/nap_emp_portal/pit11_pattern/pit11_26.jrxml";
            path = "/home/szeryf/kskowronski_projects/nap_emp_portal/pit11_pdf/";
        }
        //System.out.println(absolutePath);
        //File file = ResourceUtils.getFile("classpath:pit11_25.jrxml"); // only for windows
        File filePattern = ResourceUtils.getFile(absolutePath);
        JasperReport jasperReport = JasperCompileManager.compileReport(filePattern.getAbsolutePath());
        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listUsers);
        //Map<String, Object> parameters = new HashMap<>();
        //parameters.put("createdBy", "Java Techie");

        Map<String, Object> params = new HashMap<String, Object>();
        Document document = convertStringToXMLDocument(xmlPit11); //JRXmlUtils.parse(new InputSource(new StringReader(xmlPit11)), true); //JRLoader.getLocationInputStream("classpath:xmlFromDB.xml"));
        params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
        params.put("createdBy", "anfix poland");

        JRDesignStyle jrDesignStyle = new JRDesignStyle();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params);
        jrDesignStyle.setPdfEncoding("UTF-8");
        jasperPrint.addStyle(jrDesignStyle);
        jasperPrint.setLocaleCode("UTF-8");

        String pathPdfFile = path + "pit11_"+ yearPit + "_" + workerID + ".pdf";
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\pit11_"+ yearPit + "_" + workerID + ".html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPdfFile);
        }

        return pathPdfFile;
    }


    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            InputSource is = new InputSource(new StringReader(xmlString));
            //is.setEncoding("ISO-8859-2");
            Document doc = builder.parse(is);
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // If you want from file outside - klaudiusz
    /*

    import java.io.File;
    import javax.xml.parsers.DocumentBuilder;
    import javax.xml.parsers.DocumentBuilderFactory;
    import org.w3c.dom.Document;

    public class StringtoXMLExample
    {
        public static void main(String[] args)
        {
            final String xmlFilePath = "employees.xml";

            //Use method to convert XML string content to XML Document object
            Document doc = convertXMLFileToXMLDocument( xmlFilePath );

            //Verify XML document is build correctly
            System.out.println(doc.getFirstChild().getNodeName());
        }

        private static Document convertXMLFileToXMLDocument(String filePath)
        {
            //Parser that produces DOM object trees from XML content
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //API to obtain DOM Document instance
            DocumentBuilder builder = null;
            try
            {
                //Create DocumentBuilder with default configuration
                builder = factory.newDocumentBuilder();

                //Parse the content to Document object
                Document doc = builder.parse(new File(filePath));
                return doc;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

     */

}
