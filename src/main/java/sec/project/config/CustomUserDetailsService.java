package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // private Map<String, String> accountDetails;
    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        // this data would typically be retrieved from a database
       // this.accountDetails = new TreeMap<>();
       //this.accountDetails.put("ted", "$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        
        Account account1 = new Account();  
        // username and password is "ted"
        account1.setUsername("ted");
        account1.setPassword("$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        accountRepository.save(account1);
        // ---
        
        Account account2 = new Account();
        // username "jee" and password is "ted"
        account2.setUsername("jee");
        account2.setPassword("$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        accountRepository.save(account2);
        // ---
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // if (!this.accountDetails.containsKey(username)) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
