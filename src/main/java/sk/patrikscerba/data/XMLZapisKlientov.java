package sk.patrikscerba.data;

import org.w3c.dom.*;
import sk.patrikscerba.model.Klient;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.*;



// Trieda na zápis klientov do súboru klienti.xml
public class XMLZapisKlientov {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Metóda na uloženie klienta do XML súboru
    public void ulozKlienta(Klient novyKlient) {
        try {
            //Príprava priečinka data
            Path dataAdresar = Path.of(System.getProperty("user.dir"), "data");
            if (Files.notExists(dataAdresar)) {
                Files.createDirectories(dataAdresar);
            }
            //príprava cesty k XML súboru
            Path xmlCesta = dataAdresar.resolve("klienti.xml");
            File xmlSubor = xmlCesta.toFile();

            //Načítanie všetkých existujúcich klientov
            List<Klient> klienti = new ArrayList<>();
            if (xmlSubor.exists() && xmlSubor.length() > 0) {
                klienti.addAll(new XMLNacitanieKlientov().nacitajKlientov());
            }

            // Pridanie nového klienta
            klienti.add(novyKlient);

            // Vytvorenie nového XML dokumentu
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("klienti");
            document.appendChild(root);

            //Zapíšeme všetkých klientov a ich údajov do dokumentu
            for (Klient k : klienti) {
                Element klient = document.createElement("klient");

                Attr vekAttr = document.createAttribute("vek");
                vekAttr.setValue(String.valueOf(k.getVek()));
                klient.setAttributeNode(vekAttr);

                Element meno = document.createElement("krstneMeno");
                meno.appendChild(document.createTextNode(k.getKrstneMeno()));
                klient.appendChild(meno);

                Element priezvisko = document.createElement("priezvisko");
                priezvisko.appendChild(document.createTextNode(k.getPriezvisko()));
                klient.appendChild(priezvisko);

                Element email = document.createElement("email");
                email.appendChild(document.createTextNode(k.getEmail()));
                klient.appendChild(email);

                Element telefon = document.createElement("telefonneCislo");
                telefon.appendChild(document.createTextNode(k.getTelefonneCislo()));
                klient.appendChild(telefon);

                Element datum = document.createElement("datumNarodenia");
                String datumText = (k.getDatumNarodenia() != null) ? k.getDatumNarodenia().format(FORMATTER) : "";
                datum.appendChild(document.createTextNode(datumText));
                klient.appendChild(datum);

                root.appendChild(klient);
            }

            //Formátovanie a zápis do súboru
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            //Zápis do súboru
            transformer.transform(new DOMSource(document), new StreamResult(xmlSubor));

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Chyba pri zápise XML: " + e.getMessage());
        }
    }

    // Metóda na vymazanie klienta podľa mena a priezviska
    //po odstránení klienta sa celý zoznam prepíše na novo do XML súboru
    public void vymazatKlienta(Klient vymazatKlienta) {
        try {
            Path xmlCesta = Path.of(System.getProperty("user.dir"), "data", "klienti.xml");
            File xmlSubor = xmlCesta.toFile();

            if (!xmlSubor.exists() || xmlSubor.length() == 0) {
                return;
            }
            // Načítame všetkých klientov
            XMLNacitanieKlientov xmlNacitanieKlientov = new XMLNacitanieKlientov();
            List<Klient> klienti = xmlNacitanieKlientov.nacitajKlientov();

            // Odstránime klienta s rovnakým menom a priezviskom
            klienti.removeIf(k ->
                    k.getKrstneMeno().equalsIgnoreCase(vymazatKlienta.getKrstneMeno()) &&
                            k.getPriezvisko().equalsIgnoreCase(vymazatKlienta.getPriezvisko())
            );

            // Zapíšeme aktualizovaný zoznam späť do XML
            ulozVsetkychKlientov(klienti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Pomocná metóda na uloženie všetkých klientov do XML
    private void ulozVsetkychKlientov(List<Klient> klienti) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = document.createElement("klienti");
        document.appendChild(root);

        for (Klient k : klienti) {
            Element klient = document.createElement("klient");
            Attr vekAttr = document.createAttribute("vek");
            vekAttr.setValue(String.valueOf(k.getVek()));
            klient.setAttributeNode(vekAttr);

            Element meno = document.createElement("krstneMeno");
            meno.appendChild(document.createTextNode(k.getKrstneMeno()));
            klient.appendChild(meno);

            Element priezvisko = document.createElement("priezvisko");
            priezvisko.appendChild(document.createTextNode(k.getPriezvisko()));
            klient.appendChild(priezvisko);

            Element email = document.createElement("email");
            email.appendChild(document.createTextNode(k.getEmail()));
            klient.appendChild(email);

            Element telefon = document.createElement("telefonneCislo");
            telefon.appendChild(document.createTextNode(k.getTelefonneCislo()));
            klient.appendChild(telefon);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            Element datumNarodenia = document.createElement("datumNarodenia");
            String datumText = (k.getDatumNarodenia() != null) ? k.getDatumNarodenia().format(formatter) : "";
            datumNarodenia.appendChild(document.createTextNode(datumText));

            klient.appendChild(datumNarodenia);

            root.appendChild(klient);
        }
        // Formátovanie a zápis do súboru pomocou Transformeru
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(document),
                new StreamResult(Path.of(System.getProperty("user.dir"), "data", "klienti.xml").toFile()));
    }

    //Metóda na aktualizáciu klienta v XML súbore
    //Vyhlľada klienta podľa pôvodného mena a priezviska a prepíše, nahradí jeho údaje novými a následne uloží celý zoznam späť do XML
    public void aktualizujKlienta(Klient aktualizovanyKlient, String povodneMeno, String povodnePriezvisko) {
        try {
            Path suborPath = Path.of(System.getProperty("user.dir"), "data", "klienti.xml");
            File subor = suborPath.toFile();

            if (!subor.exists() || subor.length() == 0) {
                return;
            }

            // Načítame všetkých klientov
            XMLNacitanieKlientov xmlNacitanieKlientov = new XMLNacitanieKlientov();
            List<Klient> klienti = xmlNacitanieKlientov.nacitajKlientov();

            boolean aktualizovany = false;

            // Prejdeme všetkých klientov a hľadáme zhodu podľa pôvodných údajov
            for (int i = 0; i < klienti.size(); i++) {
                Klient k = klienti.get(i);

                // Hľadáme podľa pôvodného mena a priezviska
                if (k.getKrstneMeno().trim().equalsIgnoreCase(povodneMeno.trim()) &&
                        k.getPriezvisko().trim().equalsIgnoreCase(povodnePriezvisko.trim())) {

                    // Prepíšeme celý objekt
                    klienti.set(i, aktualizovanyKlient);
                    aktualizovany = true;
                    break;
                }
            }

            // Ak sa klient nenašiel, môžeme ho pridať ako nový (voliteľné)
            if (!aktualizovany) {
                klienti.add(aktualizovanyKlient);
            }

            // Zapíšeme aktualizovaný zoznam späť do XML
            ulozVsetkychKlientov(klienti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
