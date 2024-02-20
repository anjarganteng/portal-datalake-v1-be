package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = -3192496030731184007L;

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
