package com.example.parsingxmlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> lista = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        try {
            parserXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void parserXML() throws IOException, XmlPullParserException {

        //Instanzio il parser XML
        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = pullParserFactory.newPullParser();

        InputStream inputStream = getAssets().open("data.xml");
        xmlPullParser.setInput(inputStream, null);
        xmlPullParser.setFeature(xmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        processParsing(xmlPullParser);

    }

    private void processParsing(XmlPullParser parserXml) throws IOException, XmlPullParserException
    {
        ArrayList<Person> peopleList = new ArrayList<>();
        int eventType = parserXml.getEventType();
        Person currentPerson = null;
        String tagName;

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if (eventType == XmlPullParser.START_TAG){
                tagName = parserXml.getName();

                if (tagName.equals("person")) {
                    currentPerson = new Person();
                    peopleList.add(currentPerson);
                } else if (currentPerson != null) {
                    if (tagName.equals("age")) {
                        currentPerson.setAge(parserXml.nextText());
                    } else if (tagName.equals("name")) {
                        currentPerson.setName(parserXml.nextText());
                    }
                }
            }
            eventType = parserXml.next();
        }
        processPerson(peopleList);
    }

    private void processPerson(ArrayList<Person> peopleList) {

        for (Person person : peopleList) {
            String personNameAndAge = person.getName() + " " + person.getAge();
            lista.add(personNameAndAge);
        }

        adapter.notifyDataSetChanged();
    }
}
