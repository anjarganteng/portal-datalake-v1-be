package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedIrrevocableLc;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_irrevocable_lc_bot")
public class IrrevocableLc {
    
    @EmbeddedId
    protected EmbedIrrevocableLc embedIrrevocableLc;
    
    @Column(name = "aset_classifitcation_reason")
    private String asetClassifitcationReason;
    
    @Column(name = "economic_sector")
    private String economicSector;

    public IrrevocableLc() {
    }

    public IrrevocableLc(EmbedIrrevocableLc embedIrrevocableLc) {
        this.embedIrrevocableLc = embedIrrevocableLc;
    }

    public IrrevocableLc(String customerNo, String loanNo) {
        this.embedIrrevocableLc = new EmbedIrrevocableLc(customerNo, loanNo);
    }

    public EmbedIrrevocableLc getEmbedIrrevocableLc() {
        return embedIrrevocableLc;
    }

    public void setEmbedIrrevocableLc(EmbedIrrevocableLc embedIrrevocableLc) {
        this.embedIrrevocableLc = embedIrrevocableLc;
    }

    public String getAsetClassifitcationReason() {
        return asetClassifitcationReason;
    }

    public void setAsetClassifitcationReason(String asetClassifitcationReason) {
        this.asetClassifitcationReason = asetClassifitcationReason;
    }

    public String getEconomicSector() {
        return economicSector;
    }

    public void setEconomicSector(String economicSector) {
        this.economicSector = economicSector;
    }
    
}
