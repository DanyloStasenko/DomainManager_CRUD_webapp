package com.springapp.mvc.dao;

import com.springapp.mvc.model.Domain;
import java.util.List;

public interface DomainDao
{
    public void addDomain(Domain domain);
    public void updateDomain(Domain domain);
    public void removeDomain(int id);
    public Domain getDomainById(int id);
    public List<Domain> listDomains();
}
