package ru.job4j.lite;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class StoreXML {
    private StoreSQL sql;

    public StoreXML(StoreSQL sql) {
        this.sql = sql;
    }

    public void save(File target) {
        sql.load();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StoreSQL.class, Entry.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(sql, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}