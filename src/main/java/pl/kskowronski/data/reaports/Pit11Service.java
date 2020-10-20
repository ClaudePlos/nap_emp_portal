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
import pl.kskowronski.data.entity.Person;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.PersonRepository;
import pl.kskowronski.data.service.egeria.ek.UserRepo;
import net.sf.jasperreports.engine.util.JRLoader;
import org.w3c.dom.Document;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@Service
public class Pit11Service {

    @Autowired
    private UserRepo userRepo;


    public String exportPit11Report(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\tmp\\";
        List<User> listUsers = new ArrayList<>();
        Optional<User> employees = userRepo.findByPassword("80080503091");
        listUsers.add(employees.get());
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:pit11_25.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listUsers);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");

        Map<String, Object> params = new HashMap<String, Object>();
        Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream("C:/Users/k.skowronski/Documents/GitHub/nap_emp_portal/target/classes/xmlFromDB.xml"));
        params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        }

        return "report generated in path : " + path;
    }

}
