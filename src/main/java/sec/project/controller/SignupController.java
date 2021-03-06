package sec.project.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {
    
    @Autowired 
    private AccountRepository accountRepository;

    @Autowired
    private SignupRepository signupRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping("*")
    public String defaultMapping(Authentication authentication) {
        System.out.println("SignUpController defaultMapping ");
        Account currentAccount = getCurrentAccount(authentication);
        System.out.println("Current account: " + currentAccount.getUsername());
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitFormAndListParticipants(Authentication authentication, @RequestParam String firstname, @RequestParam String familyname, @RequestParam String address, Model model) {
        
        // saving new signups --------
        Account account = accountRepository.findByUsername(authentication.getName());
        System.out.println("Account: " + account.getUsername());
        model.addAttribute("currentUsername", account.getUsername());
        signupRepository.save(new Signup(firstname, familyname, address, account.getUsername()));
        System.out.println("repositoriossa alkioita: " + signupRepository.findAll().size());
        // ---------------------------
        
        
        // listing participants ------
        ArrayList<Signup> participants = (ArrayList) signupRepository.findAll();
        System.out.println("Listassa osallistujia: " + participants.size());
        model.addAttribute("participantList", participants);
        // ---------------------------
        
        
        return "done";
    }
    
    
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String changePassword(Authentication authentication, @RequestParam String password) {
        Account account = accountRepository.findByUsername(authentication.getName());
        if (account == null) {
            return "redirect:/login";
        }
        
        account.setPassword(encoder.encode(password));
        accountRepository.save(account);

        return "thanks";
    }
    
    public Account getCurrentAccount(Authentication authentication) {
        return accountRepository.findByUsername(authentication.getName());
    }
}
