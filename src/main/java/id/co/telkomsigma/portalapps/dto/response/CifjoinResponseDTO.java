package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Cifjoin;

/**
 * @author satiya
 */
public class CifjoinResponseDTO {

    private String flagNasabah;
    private String tipeNasabah;
    private String noIdentitas;
    private String cif;
    private String cifJoin;
    private String tipeJoin;

    public CifjoinResponseDTO() {
    }

    public CifjoinResponseDTO(Cifjoin cifjoin) {
        flagNasabah = cifjoin.getFlagNasabah();
        tipeNasabah = cifjoin.getTipeNasabah();
        noIdentitas = cifjoin.getNoIdentitas();
        cif = cifjoin.getEmbedCifjoin().getCif();
        cifJoin = cifjoin.getEmbedCifjoin().getCifJoin();
        tipeJoin = cifjoin.getTipeJoin();
    }

    public String getFlagNasabah() {
        return flagNasabah;
    }

    public void setFlagNasabah(String flagNasabah) {
        this.flagNasabah = flagNasabah;
    }

    public String getTipeNasabah() {
        return tipeNasabah;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCifJoin() {
        return cifJoin;
    }

    public void setCifJoin(String cifJoin) {
        this.cifJoin = cifJoin;
    }

    public String getTipeJoin() {
        return tipeJoin;
    }

    public void setTipeJoin(String tipeJoin) {
        this.tipeJoin = tipeJoin;
    }
}
