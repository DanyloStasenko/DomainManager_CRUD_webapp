package com.springapp.mvc.service;

import com.springapp.mvc.dao.DomainDao;
import com.springapp.mvc.model.Domain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DomainServiceImpl implements DomainService
{
    private DomainDao domainDao;

    public void setDomainDao(DomainDao domainDao)
    {
        this.domainDao = domainDao;
    }

    @Override
    @Transactional
    public void addDomain(Domain domain)
    {
        this.domainDao.addDomain(domain);
    }

    @Override
    @Transactional
    public void updateDomain(Domain domain)
    {
        this.domainDao.updateDomain(domain);
    }

    @Override
    @Transactional
    public void removeDomain(int id)
    {
        this.domainDao.removeDomain(id);
    }

    @Override
    @Transactional
    public Domain getDomainById(int id)
    {
        return this.domainDao.getDomainById(id);
    }

    @Override
    @Transactional
    public List<Domain> listDomains()
    {
        return this.domainDao.listDomains();
    }

    @Override
    public void checkAndUpdateDomain(int id)
    {
        try
        {
            getDomainById(id).checkStatus();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}