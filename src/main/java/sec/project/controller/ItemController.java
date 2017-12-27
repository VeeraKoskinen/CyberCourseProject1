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

//@Autowired
//    private ItemRepository itemRepository;
//
//    @PostConstruct
//    public void init() {
//        // add some content to the product repository
//        itemRepository.save(new Item("Fanta", 2.5));
//        itemRepository.save(new Item("Cola", 2.5));
//        itemRepository.save(new Item("Beer", 4.0));
//        itemRepository.save(new Item("Water", 0.0));
//        itemRepository.save(new Item("Vegetarian menu", 28.0));
//        itemRepository.save(new Item("Meatlovers menu", 35.0));
//        itemRepository.save(new Item("Fish menu", 32.5));
//    }
//
//    @RequestMapping(value = "/items", method = RequestMethod.GET)
//    public String list(Model model) {
//        model.addAttribute("items", itemRepository.findAll());
//        System.out.println("items in itemRepository: " + itemRepository.findAll().size());
//        return "items";
//    }