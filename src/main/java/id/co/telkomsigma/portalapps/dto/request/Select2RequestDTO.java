package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.util.Map;

/**
 * @author satiya
 */
public class Select2RequestDTO implements Serializable {

    private static final long serialVersionUID = 3732554889222172206L;

    private int page;

    private Select2RequestSearchDTO search;

    private Map<String, Object> query;

    public Select2RequestDTO() {
        super();
    }

    public Select2RequestDTO(int page, Select2RequestSearchDTO search, Map<String, Object> query) {
        super();
        this.page = page;
        this.search = search;
        this.query = query;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Select2RequestSearchDTO getSearch() {
        return search;
    }

    public void setSearch(Select2RequestSearchDTO search) {
        this.search = search;
    }

    public Map<String, Object> getQuery() {
        return query;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }
}
