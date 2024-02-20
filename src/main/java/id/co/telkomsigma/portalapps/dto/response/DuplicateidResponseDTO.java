package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Duplicateid;

/**
 * @author satiya
 */
public class DuplicateidResponseDTO {

    private static final long serialVersionUID = 8602246974818837182L;

    private String cif;
    private String noIdentitas;
    private String namaLengkap;
    private String sumber;


    public DuplicateidResponseDTO() {
    }

    public DuplicateidResponseDTO(Duplicateid duplicateid) {
        cif = duplicateid.getCif();
        noIdentitas = duplicateid.getNoIdentitas();
        namaLengkap = duplicateid.getNamaLengkap();
        sumber = duplicateid.getSumber();
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }
}
