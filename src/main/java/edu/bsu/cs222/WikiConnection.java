package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiConnection {
    public InputStream pullRevisionData(URL wikiPage) {
        if (wikiPage == null) {
            return null;
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) wikiPage.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (bteger@bsu.edu)");
            return connection.getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public URL generateHTTPRequest(String search) throws MalformedURLException {
        if (search.isEmpty()) {
            return null;
        }

        String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8);
        return new URL("https://en.wikipedia.org/w/api.php?" +
                "action=query&format=json&" +
                "prop=revisions&" + "titles=" + encodedSearch + "&" +
                "rvprop=timestamp|user&rvlimit=20&redirects");
    }
}