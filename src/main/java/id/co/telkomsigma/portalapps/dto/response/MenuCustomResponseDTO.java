package id.co.telkomsigma.portalapps.dto.response;

public class MenuCustomResponseDTO {

    private static final long serialVersionUID = 8022228226583702709L;

    private Object an;
    private Object lps;
    private Object par;
    private Object log;
    private Object user;
    private Object bot;


    public MenuCustomResponseDTO() {
    }

    public MenuCustomResponseDTO(Object an, Object lps, Object par, Object log, Object user, Object bot) {
        this.an = an;
        this.lps = lps;
        this.par = par;
        this.log = log;
        this.user = user;
        this.bot = bot;
    }

    public Object getAn() {
        return an;
    }

    public void setAn(Object an) {
        this.an = an;
    }

    public Object getLps() {
        return lps;
    }

    public void setLps(Object lps) {
        this.lps = lps;
    }

    public Object getPar() {
        return par;
    }

    public void setPar(Object par) {
        this.par = par;
    }

    public Object getLog() {
        return log;
    }

    public void setLog(Object log) {
        this.log = log;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getBot() {
        return bot;
    }

    public void setBot(Object bot) {
        this.bot = bot;
    }
    
    
}

