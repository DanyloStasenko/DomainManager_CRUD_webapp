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

    // checking URL, using GoogleSafeBrowsing API
    public void checkStatus() throws Exception
    {
        // 'httpQueryTemplate' consists from parameters: client, appver, key, pver, and URL
        String httpQueryTemplate = "https://sb-ssl.google.com/safebrowsing/api/lookup?client=DS&key=AIzaSyBe5JVoM-jSx3bFGLTpF1Uy5mhjhNHhmq4&appver=1.5.2&pver=3.1&url=";
        String httpQuery = httpQueryTemplate+this.getDomainTitle();
        URL url = new URL(httpQuery);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) this.setDomainStatus("Danger!");
        if (responseCode == 400) this.setDomainStatus("Bad Request");
        if (responseCode == 503) this.setDomainStatus("Service Unavailable");
        else this.setDomainStatus("Safe");
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