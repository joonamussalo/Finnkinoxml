package com.example.finnkinoxml;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static com.example.finnkinoxml.Elokuvateatterit.teatterilista;

public class MainActivity extends AppCompatActivity {
    Context context=null;
    Spinner spinner;
    EditText tekstiInput=null;
    Button button;
    EditText aloitusinput=null;
    EditText lopetusinput=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        spinner = (Spinner) findViewById(R.id.spinner3);
        tekstiInput = (EditText) findViewById(R.id.tekstiInput);

        lopetusinput = (EditText) findViewById(R.id.lopetusinput);




    }


    public void readXML (View v) {
        try {


        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        String  urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
        Document doc = builder.parse(urlString);
        doc.getDocumentElement().normalize();
        System.out.println("Root element" +doc.getDocumentElement().getNodeName());
        Elokuvateatterit.getArray();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i=0;i< nList.getLength();i++) {
                Node node = nList.item(i);

                if (node.getNodeType()==node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id =element.getElementsByTagName("ID").item(0).getTextContent();
                    String name =element.getElementsByTagName("Name").item(0).getTextContent();
                    Elokuvateatterit.addTeatteri(id,name);
                }
            }
            ArrayAdapter<Teatterit> adapter = new ArrayAdapter<Teatterit>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, teatterilista);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);





        } catch (IOException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void readMovies(View v) {

        try {
            int aloitus =Integer.parseInt(aloitusinput.getText().toString());
            int lopetus =Integer.parseInt(lopetusinput.getText().toString());
            String s = tekstiInput.getText().toString();
            int ID = spinner.getSelectedItemPosition();
            String tunnus = teatterilista.get(ID).getID();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/Schedule/?area=" + tunnus + "&dt=" + s;
            System.out.println(url);


            Document document = documentBuilder.parse(url);
            document.getDocumentElement().normalize();
            System.out.println("Root element" + document.getDocumentElement().getNodeName());

            NodeList elokuvat = document.getDocumentElement().getElementsByTagName("Show");
            ArrayList<String> elokuvalista = new ArrayList<>();


            for (int i = 0; i < elokuvat.getLength(); i++) {
                Node node = elokuvat.item(i);

                if (node.getNodeType() == node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String aika = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    String nimi = element.getElementsByTagName("Title").item(0).getTextContent();
                    int aloitusaika = Integer.parseInt(aika.substring(11,12)+aika.substring(14,15));
                    elokuvalista.add(aika.substring(11)+" "+nimi);


                }

            }

            Intent intent= new Intent(this, Activity2.class);
            intent.putExtra("elokuvat",elokuvalista);
            startActivity(intent);



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

}
