package pl.kskowronski.data.reaports;


import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xml.sax.InputSource;
import pl.kskowronski.data.service.egeria.ek.UserRepo;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


import java.io.*;
import java.util.*;

@Service
public class Pit11Service {

    @Autowired
    private UserRepo userRepo;

    public String exportPit11Report(String reportFormat, String workerID, String yearPit, String xmlPit11) throws FileNotFoundException, JRException {
        String path = "C:\\tmp\\";

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:pit11_25.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listUsers);
        //Map<String, Object> parameters = new HashMap<>();
        //parameters.put("createdBy", "Java Techie");

        Map<String, Object> params = new HashMap<String, Object>();
        Document document = convertStringToXMLDocument(xmlPit11); //JRXmlUtils.parse(new InputSource(new StringReader(xmlPit11)), true); //JRLoader.getLocationInputStream("classpath:xmlFromDB.xml"));
        params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
        params.put("createdBy", "anfix poland");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\pit11_"+ yearPit + "_" + workerID + ".pdf");
        }

        return "report generated in path : " + path;
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
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
