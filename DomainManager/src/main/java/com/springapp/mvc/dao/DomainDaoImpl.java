package com.springapp.mvc.dao;

import com.springapp.mvc.model.Domain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DomainDaoImpl implements DomainDao
{
    private static final Logger logger = LoggerFactory.getLogger(DomainDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDomain(Domain domain)
    {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(domain);
        logger.info("Domain successfully saved" + domain);
    }

    @Override
    public void updateDomain(Domain domain)
    {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(domain);
        logger.info("Domain updated" + domain);
    }

    @Override
    public void removeDomain(int id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        Domain domain = (Domain) session.load(Domain.class, new Integer(id));
        if (domain!=null)
        {
            session.delete(domain);
        }
        logger.info("Domain deleted" + domain);
    }

    @Override
    public Domain getDomainById(int id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        Domain domain = (Domain) session.load(Domain.class, new Integer(id));
        logger.info("Domain loaded" + domain);

        return domain;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Domain> listDomains()
    {
        Session session = this.sessionFactory.getCurrentSession();
        List<Domain> domainList = session.createQuery("from Domain").list();

        for(Domain domain : domainList)
        {
            logger.info("Domain list: " + domain);
        }

        return domainList;
    }
}