package rs.ac.bg.fon.banksystem.model.auth;

import java.util.Date;

public class AuthenticationResponse {
    private  String jwtToken;
    private Date expirationDate;
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


}
