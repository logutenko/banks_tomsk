package com.example.parsehtml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ParseHTMLTask htmlParser = new ParseHTMLTask();
        htmlParser.execute("http://banki.tomsk.ru/pages/41/");
        //ListView lvMain =  findViewById(R.id.lvMain);
        ArrayList<Map<String, String>> result = new  ArrayList<Map<String, String>>();
        try {

            result = htmlParser.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }





    }
    class ParseHTMLTask extends AsyncTask<String, Void, ArrayList<Map<String, String>>>{

        @Override
        protected ArrayList<Map<String, String>> doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            StringBuilder sb = new StringBuilder();
            ArrayList<Map<String, String>> banksList;
            banksList = new ArrayList<Map<String, String>>();
            try {
                URL url =  new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);

                inputStream = urlConnection.getInputStream();
                // html страница в кодировке windows-1251, поэтому парсер так же настраивается
                // чтобы узнать в какой кодировке текст, можно сохранить html и открыть его в редакторе
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));

                String line = null;
                try {

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                            //sb.append(System.getProperty("line.separator"));
                        }

                } catch (IOException e) {
                    Log.d("MyLogs", "Error!");
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.d("MyLogs", "Error!");
                        e.printStackTrace();
                    }
                }


                urlConnection.disconnect();



            } catch (MalformedURLException e) {
                Log.d("MyLogs", "Error!");
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("MyLogs", "Error!");
                e.printStackTrace();

            }
            String regexTr = "<tr\\s+class=\"curbody\"[^>]*>.*?</tr>";
            Pattern patternTr = Pattern.compile(regexTr, Pattern.DOTALL);
            Matcher mainMatcher = patternTr.matcher(sb.toString());
            StringBuffer buffer = new StringBuffer();


            while (mainMatcher.find()) {
                String regexTd = "<td[^>]*>(.*?)</td>\\s*" +
                        "<td[^>]*>(.*?)</td>\\s*" +
                        "<td[^>]*>(.*?)</td>\\s*" +
                        "<td[^>]*>(.*?)</td>\\s*" +
                        "<td[^>]*>(.*?)</td>";
                Pattern patternTd = Pattern.compile(regexTd, Pattern.DOTALL);
                Matcher matcher = patternTd.matcher(mainMatcher.group());

                Map<String,String> m = new HashMap<String, String>();
                if (matcher.find()){
                    m.put("name", matcher.group(1));
                    m.put("buyDollar", matcher.group(2));
                    m.put("sellDollar", matcher.group(3));
                    m.put("buyEuro", matcher.group(4));
                    m.put("sellEuro", matcher.group(5));
                }
                banksList.add(m);


               /* while(matcher.find()) {

                    buffer.append(matcher.group(1));

                }*/
                //buffer.append(matcher.group());
                // buffer = "-" -> "-text-" -> "-text-text-" -> "-text-text-text-"
            }

            return banksList;
        }

        protected void onPostExecute(ArrayList<Map<String, String>> result) {
            super.onPostExecute(result);
            //tvInfo.setText(result);
           //Log.d("MyLogs", "End. Result = " + result);

        }
    }
}
