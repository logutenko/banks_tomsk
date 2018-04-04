package com.example.masha.bankitomsk.domain;

import com.example.masha.bankitomsk.data.Bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by masha on 20.03.2018.
 */

public class HTMLParser {

    private URL url;


    public void setURL (String url){

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

    }

    public List<Bank> parseBanks (){
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        List<Bank> banksList;
        banksList = new LinkedList<>();
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);

            inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));

            String line = null;
            try {

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {

                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }


            urlConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

        String regexTr = "<tr\\s+class=\"curbody\"[^>]*>.*?</tr>";
        String regexTd = "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>\\s*" +
                "<td[^>]*>(.*?)</td>";
        String regexA =  "<a\\s*href=\"(.*?)\"[^>]*>([^<]*).*</a>";
        Pattern pattern = Pattern.compile(regexTr, Pattern.DOTALL);
        //find pattern <tr class=curbody ...> ... </tr>
        Matcher matcher = pattern.matcher(sb.toString());
        Matcher matcherJ;
        String temp;
        Map <String, String> rateToBuy, rateToSell ;


        while (matcher.find()) {

            pattern = Pattern.compile(regexTd, Pattern.DOTALL);
            //find pattern <td> ... </td>
            matcherJ = pattern.matcher(matcher.group());

            if (matcherJ.find()){

                Bank bank = new Bank();
                rateToBuy = new HashMap<String, String>();
                rateToSell = new HashMap<String, String>();
                rateToBuy.put("USD", matcherJ.group(2));
                rateToBuy.put("EUR", matcherJ.group(4));
                rateToSell.put("USD", matcherJ.group(3));
                rateToSell.put("EUR", matcherJ.group(5));

                bank.setRateToBuy(rateToBuy);
                bank.setRateToSell(rateToSell);

                pattern  = Pattern.compile(regexA, Pattern.DOTALL);
                //find pattern <a> ... </a>
                temp = matcherJ.group(1);
                matcherJ = pattern.matcher(temp);
                if (matcherJ.find()){
                    bank.setLink(matcherJ.group(1));
                    bank.setName(matcherJ.group(2));
                }
                banksList.add(bank);
            }

        }

        return banksList;
    }


    public Bank parseBankInfo (){
        Bank bank = new Bank();
        return bank;
    }

}
