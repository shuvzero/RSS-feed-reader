package com.example.rssfeedreader.rss;


import android.util.Log;
import com.example.rssfeedreader.rss.RssItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RssXmlParser extends DefaultHandler {

    private List<RssItem> rssItems;
    private RssItem rssItem;
    private StringBuilder text;

    public RssXmlParser() {
        rssItems = new ArrayList<RssItem>();
        text = new StringBuilder();
    }

    public List<RssItem> getItems(String filter) {
        filterItems(filter);
        return rssItems;
    }

    private void filterItems(String filter) {
        filter = filter.toLowerCase();
        if(filter != null && filter.length() > 0) {
            Iterator<RssItem> iterator = rssItems.iterator();
            while (iterator.hasNext()) {
                RssItem item = iterator.next();
                if (!item.getTitle().toLowerCase().contains(filter) &&
                        !item.getShortDescription().toLowerCase().contains(filter))
                    iterator.remove();
            }
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("item")) {
            rssItem = new RssItem();
        } else if(qName.equals("enclosure") && rssItem != null) {
            String imagePath = attributes.getValue("url");
            rssItem.setImagePath(imagePath);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName == null)
            Log.e("parser", "qname is null");
        if (qName.equals("item")) {
            rssItems.add(rssItem);
            rssItem = null;
        } else if(qName.equals("title") && rssItem != null) {
            rssItem.setTitle(getText());
        } else if(qName.equals("pubDate") && rssItem != null) {
            rssItem.setPubDate(getText());
        } else if(qName.equals("description") && rssItem != null) {
            rssItem.setDescription(getText());
        }
        text = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    public String getText() {
        return text.toString().trim();
    }

}