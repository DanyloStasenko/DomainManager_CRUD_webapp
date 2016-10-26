package com.springapp.mvc.controller;

import com.springapp.mvc.model.Domain;
import com.springapp.mvc.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DomainController
{
    private DomainService domainService;

    @Autowired(required = true)
    @Qualifier(value = "domainService")
    public void setDomainService(DomainService domainService)
    {
        this.domainService = domainService;
    }

    @RequestMapping(value = "domains", method = RequestMethod.GET)
    public String listDomains(Model model)
    {
        model.addAttribute("domain", new Domain());
        model.addAttribute("listDomains", this.domainService.listDomains());

        return "domains";
    }

    @RequestMapping(value = "/domains/add", method = RequestMethod.POST)
    public String addDomain(@ModelAttribute("domain") Domain domain)
    {
        if(domain.getId() == 0)
        {
            this.domainService.addDomain(domain);
        }
        else
        {
            this.domainService.updateDomain(domain);
        }

        return "redirect:/domains";
    }

    @RequestMapping("/remove/{id}")
    public String removeDomain(@PathVariable("id") int id)
    {
        this.domainService.removeDomain(id);

        return "redirect:/domains";
    }

    @RequestMapping("edit/{id}")
    public String editDomain(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("domain", this.domainService.getDomainById(id));
        model.addAttribute("listDomains", this.domainService.listDomains());

        return "domains";
    }

    @RequestMapping("domaindata/{id}")
    public String domainData(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("domain", this.domainService.getDomainById(id));

        return "domaindata";
    }

    // added this
    @RequestMapping("/check/{id}")
    public String checkStatus(@PathVariable("id") int id)
    {
        Domain domain = domainService.getDomainById(id);
        try
        {
            domain.checkStatus();
            domainService.updateDomain(domain);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //System.out.println("domain status checked");
        //this.domainService.checkAndUpdateDomain(id);
        return "redirect:/domains";
    }
}
