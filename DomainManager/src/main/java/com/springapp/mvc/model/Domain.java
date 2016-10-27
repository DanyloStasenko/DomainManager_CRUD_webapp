package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    // checking URL, using GoogleSafeBrowsing API
    public void checkStatus() throws Exception
    {
        // 'templateUrl' consists from parameters: client, appver, key, pver, and URL
        String templateUrl = "https://sb-ssl.google.com/safebrowsing/api/lookup?client=DS&key=AIzaSyBe5JVoM-jSx3bFGLTpF1Uy5mhjhNHhmq4&appver=1.5.2&pver=3.1&url=";
        String readyUrl = templateUrl+this.getDomainTitle();
        URL url = new URL(readyUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        switch (responseCode)
        {
            case 200: setDomainStatus("Danger!");
                      break;
            case 204: checkUrl(); // 204 responseCode is genereted by GoogleApi in 2 cases: 'OK', and 'NotFound', so I added one more function to check real UrlStatus
                      break;
            case 400: setDomainStatus("Not Found");
                      break;
            case 503: setDomainStatus("Service Unavailable");
                      break;
        }
    }

    public void checkUrl() throws Exception
    {
        String tempUrl = getDomainTitle();

        if (!tempUrl.contains("http"))
        {
            tempUrl = "http://"+tempUrl;
            System.out.println(tempUrl);
        }

        String responseMessage = "Not Found";
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