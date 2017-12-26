package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Signup extends AbstractPersistable<Long> {

    private String firstname;
    private String familyname;
    private String address;
    private String account;

    public Signup() {
        super();
    }

    public Signup(String firstname, String familyname, String address, String account) {
        this();
        this.firstname = firstname;
        this.familyname = familyname;
        this.address = address;
        this.account = account;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String a) {
        this.account = a;
    }
    
    
    public String toString() {
        return firstname + " " + familyname + ", sähköposti: " + address;
    }

}
