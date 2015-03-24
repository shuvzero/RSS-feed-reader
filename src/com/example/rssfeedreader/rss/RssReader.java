package com.example.rssfeedreader.rss;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class RssReader {

    private String url;
    private String filter;
    private final String HTTP = "http://";

    public RssReader(String url, String filter) {
        if(!url.startsWith(HTTP))
            url = HTTP + url;
        this.url = url;
        this.filter = filter;
    }

    public List<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RssXmlParser handler = new RssXmlParser();
        saxParser.parse(url, handler);
        return handler.getItems(filter);
    }

}