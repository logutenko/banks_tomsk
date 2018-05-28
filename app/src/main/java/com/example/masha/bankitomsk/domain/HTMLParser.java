package com.example.masha.bankitomsk.domain;

import android.content.Context;
import android.widget.Toast;

import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.data.Currency;
import com.example.masha.bankitomsk.data.Office;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by masha on 20.03.2018.
 */

public class HTMLParser {

    private URL url;

    public HTMLParser(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            //Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
        }

    }


    private String getSource() {
        HttpURLConnection urlConnection;
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);

            inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));

            String line;
            try {

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {

                //Toast.makeText(getActivity(), "IOException", Toast.LENGTH_LONG).show();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {

                    //Toast.makeText(getActivity(), "IOException", Toast.LENGTH_LONG).show();
                }
            }


            urlConnection.disconnect();

        } catch (MalformedURLException e) {

            //Toast.makeText(getActivity(), "MalformedURLException", Toast.LENGTH_LONG).show();
        } catch (IOException e) {

            //Toast.makeText(getActivity(), "IOException", Toast.LENGTH_LONG).show();

        }
        return sb.toString();
    }


    public void setURL(String url) {

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            //Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
        }

    }


    public List<Bank> parseBanks() {
        List<Bank> banksList;
        banksList = new LinkedList<>();
        String source = getSource();
        String regexTr = "<tr\\s+class=\"curbody\"[^>]*>.*?</tr>";
        String regexTd = "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>";
        String regexA = "<a\\s*href=\"(.*?)\"[^>]*>([^<]*).*</a>";
        Pattern pattern = Pattern.compile(regexTr, Pattern.DOTALL);
        //find pattern <tr class=curbody ...> ... </tr>
        Matcher matcher = pattern.matcher(source);
        Matcher matcherJ;
        String temp;

        while (matcher.find()) {

            pattern = Pattern.compile(regexTd, Pattern.DOTALL);
            //find pattern <td> ... </td>
            matcherJ = pattern.matcher(matcher.group());

            if (matcherJ.find()) {

                Bank bank = new Bank();
                Currency usd = new Currency("USD", matcherJ.group(2), matcherJ.group(3));
                Currency eur = new Currency("EUR", matcherJ.group(4), matcherJ.group(5));
                bank.addCurrency(usd);
                bank.addCurrency(eur);


                pattern = Pattern.compile(regexA, Pattern.DOTALL);
                //find pattern <a> ... </a>
                temp = matcherJ.group(1);
                matcherJ = pattern.matcher(temp);
                if (matcherJ.find()) {
                    String link = matcherJ.group(1);
                    if (link.contains("http"))
                        bank.setDetailsUrl(link);
                    else bank.setDetailsUrl("http://banki.tomsk.ru" + link);

                    bank.setName(matcherJ.group(2));
                }
                banksList.add(bank);
            }

        }

        return banksList;
    }


    public Bank parseBankDetails() {
        Bank bank = new Bank();
        String source = getSource();

        String regex = "Полное наименование.*?<td[^>]*>(.*?)</td>.*?Адрес сайта.*?<a[^>]*>(.*?)</a>";
        String regexAdress = "<b>Адрес:</b>\\s*(.*?)</td>.*?Телефон:.*?>\\s*(.*?)<";
        String regexCurrency = "<tr.+?td>(\\w+)</td>.+?nobr><img.+?>([\\d.]+).+?<nobr><img.+?>([\\d.]+)";

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        //find pattern <table class="obzor-table"> ... </tr>
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {

            bank.setName(matcher.group(1));
            bank.setWebSite(matcher.group(2));
        }
        pattern = Pattern.compile(regexAdress, Pattern.DOTALL);
        matcher = pattern.matcher(source);
        while (matcher.find()) {
            Office office = new Office(matcher.group(1), matcher.group(2));
            bank.addOffice(office);
        }
        pattern = Pattern.compile(regexCurrency, Pattern.DOTALL);
        matcher = pattern.matcher(source);
        if (matcher.find()) {
            Currency currency = new Currency(matcher.group(1),matcher.group(2), matcher.group(3));
            bank.addCurrency(currency);
        }
        return bank;
    }

}
