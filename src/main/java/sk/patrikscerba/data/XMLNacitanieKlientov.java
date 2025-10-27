package sk.patrikscerba.data;

import org.w3c.dom.*;
import sk.patrikscerba.model.Klient;

import javax.xml.parsers.*;
import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Táto trieda načítava klientov zo súboru XML
public class XMLNacitanieKlientov {

    // Formát dátumu podľa tvojho štýlu (dd.MM.yyyy)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Metóda na načítanie klientov zo súboru XML
    public List<Klient> nacitajKlientov() {
        List<Klient> klienti = new ArrayList<>();
        Path xmlCesta = Path.of(System.getProperty("user.dir"), "data", "klienti.xml");
        File xmlSubor = xmlCesta.toFile();

        if (!xmlSubor.exists() || xmlSubor.length() == 0) {
            return klienti;
        }
        // Parsovanie XML súboru kvôli načítaniu klientov
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlSubor);
            document.getDocumentElement().normalize();

            // Získanie všetkých elementov "klient" z dokumentu
            NodeList nodeList = document.getElementsByTagName("klient");

            // Prechádzanie každého elementu klient vytváranie objektov Klient
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // Kontrola, či je uzol element
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Načítanie údajov klienta z XML elementu
                    String krstneMeno = getText(element, "krstneMeno");
                    String priezvisko = getText(element, "priezvisko");
                    String email = getText(element, "email");
                    String telefonneCislo = getText(element, "telefonneCislo");
                    String datumText = getText(element, "datumNarodenia");

                    int vek = 0;
                    try {
                        vek = Integer.parseInt(element.getAttribute("vek"));
                    } catch (NumberFormatException ignored) {
                            vek = 0;
                    }
                    LocalDate datumNarodenia;
                    try {
                        datumNarodenia = LocalDate.parse(datumText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    } catch (Exception e) {
                        datumNarodenia = LocalDate.now();
                    }
                    // Vytvorenie objektu Klient a pridanie do zoznamu
                    Klient klient = new Klient(krstneMeno, priezvisko, vek, email, telefonneCislo, datumNarodenia);
                    klienti.add(klient);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Chyba pri načítaní klientov: " + e.getMessage());
        }

        return klienti;
    }
    // Pomocná metóda, ktorá načíta textový obsah elementu
    private String getText(Element element, String tag) {
        NodeList list = element.getElementsByTagName(tag);
        if (list.getLength() > 0) {
            Node node = list.item(0);
            return node.getTextContent().trim();
        }
        return "";
    }
}
