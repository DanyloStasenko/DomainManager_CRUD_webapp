package com.springapp.mvc.model;

import javax.persistence.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Entity
@Table(name = "domains")
public class Domain
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "domain_title")
    private String domainTitle;

    @Column(name = "domain_status")
    private String domainStatus;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDomainTitle()
    {
        return domainTitle;
    }

    public void setDomainTitle(String domainTitle)
    {
        this.domainTitle = domainTitle;
    }

    public String getDomainStatus()
    {
        return domainStatus;
    }

    public void setDomainStatus(String domainStatus)
    {
        this.domainStatus = domainStatus;
    }

    // checking URL with SafeBrowsing API
    public void checkStatus() throws Exception
    {
        String CLIENT = "DS";
        String KEY = "AIzaSyBe5JVoM-jSx3bFGLTpF1Uy5mhjhNHhmq4";
        String APPVER = "1.5.2";
        String PVER = "3.1";
        String URL = getDomainTitle();

        String httpQuery = "https://sb-ssl.google.com/safebrowsing/api/lookup?" + "client="+CLIENT
                                                                                  + "&key="+KEY
                                                                                  + "&appver="+APPVER
                                                                                  + "&pver="+PVER
                                                                                  + "&url="+URL;

        URL url = new URL(httpQuery);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        switch (responseCode)
        {
            case 200: setDomainStatus("Danger!");
                      break;
            case 204: checkUrl(); // 204 is generated when 'OK', and 'NotFound'. Let's get real UrlStatus
                      break;
            case 400: setDomainStatus("Not Found");
                      break;
            case 503: setDomainStatus("Service Unavailable");
                      break;
        }
    }

    // one more check, if 204
    public void checkUrl() throws Exception
    {
        String tempUrl = this.getDomainTitle();
        String responseMessage = "Not Found";

        // auto add "http://", if not specified
        if(!tempUrl.startsWith("http", 0))
        {
            tempUrl = "http://" + tempUrl;
        }
        try
        {
            URL url = new URL(tempUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseMessage = connection.getResponseMessage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.setDomainStatus(responseMessage);
    }

    @Override
    public String toString()
    {
        return "Domain{"+
                "id"+id+
                ", domainTitle='" + domainTitle + '\'' +
                ", domainStatus='" + domainStatus + '\'' +
                '}';
    }
}