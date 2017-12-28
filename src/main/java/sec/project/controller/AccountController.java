/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

/**
 *
 * @author veerakoskinen
 */
public class AccountController {
    
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultMapping(Authentication authentication) {
        System.out.println("AccountController defaultMapping");
        Account currentAccount = getCurrentAccount(authentication);
        if (currentAccount == null) {
            return "redirect:/login";
        }
        else {
            return "redirect:/form";
        }
        
    }

    @RequestMapping("/login")
    public String passwordForm() {
        System.out.println("passwordForm");
        return "login";
    }
 
    public Account getCurrentAccount(Authentication authentication) {
        return accountRepository.findByUsername(authentication.getName());
    }
}
