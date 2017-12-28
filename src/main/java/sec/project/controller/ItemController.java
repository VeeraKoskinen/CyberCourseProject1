/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Item;
import sec.project.repository.ItemRepository;

/**
 *
 * @author veerakoskinen
 */
@Controller
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "items";
    } 
}

