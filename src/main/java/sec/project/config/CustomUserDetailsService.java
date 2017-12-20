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
        Account account = new Account();
        
        // username and password is "ted"
        account.setUsername("ted");
        account.setPassword("$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        accountRepository.save(account);
        // ---
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // if (!this.accountDetails.containsKey(username)) {
        if (this.accountRepository.findByUsername(username) == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                this.accountRepository.findByUsername(username).getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
