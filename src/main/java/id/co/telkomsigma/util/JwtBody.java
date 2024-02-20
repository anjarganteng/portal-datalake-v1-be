package id.co.telkomsigma.util;

import id.co.telkomsigma.portalapps.model.ApplicationUsers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author satiya
 */
public class JwtBody implements Serializable {

    private static final long serialVersionUID = 6424858902806855651L;

    private String fullname;
    private String email;
    private String phone;

    public JwtBody() {
        super();
    }

    public JwtBody(String fullname, String email, String phone) {
        super();
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public JwtBody(ApplicationUsers applicationUsers) {
        super();
        fullname = applicationUsers.getFullname();
        email = applicationUsers.getEmail();
        phone = applicationUsers.getPhone();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("fullname", fullname);
        maps.put("email", email);
        maps.put("phone", phone);

        return maps;
    }
}
