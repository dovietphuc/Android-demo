package com.example.bai2de3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import org.xml.sax.Parser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream inputStream = getAssets().open("data.xml");
            List<Post> data = parse(inputStream);
            for (Post datum : data) {
                Log.d("PhucDVb", "onCreate: " + datum.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    class Post {
        String name;
        String content;

        public Post(String name, String content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readString(parser);
        } finally {
            in.close();
        }
    }

    public List readString(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Post> listPosts = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, null, "resources");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("string")) {
                listPosts.add(readPost(parser));
            } else {
                skip(parser);
            }
        }

        return listPosts;
    }

    public Post readPost(XmlPullParser parser) throws XmlPullParserException, IOException {
        String name = "";
        String content = "";

        parser.require(XmlPullParser.START_TAG, null, "string");
        name = parser.getAttributeValue(null, "name");
        if (parser.next() == XmlPullParser.TEXT) {
            content = parser.getText();
        }
        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, null, "string");

        return new Post(name, content);
    }


    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


}