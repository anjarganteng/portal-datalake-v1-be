package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.GolPihakLawan;

/**
 * @author satiya
 */
public class GolPihakLawanResponseDTO {

    private static final long serialVersionUID = -6975601466326169124L;

    private String golonganPihakLawan;
    private String golDebiturKreditur;

    public GolPihakLawanResponseDTO() {
    }

    public GolPihakLawanResponseDTO(GolPihakLawan golPihakLawan) {
        golonganPihakLawan = golPihakLawan.getGolonganPihakLawan();
        golDebiturKreditur = golPihakLawan.getGolDebiturKreditur();
    }

    public String getGolonganPihakLawan() {
        return golonganPihakLawan;
    }

    public void setGolonganPihakLawan(String golonganPihakLawan) {
        this.golonganPihakLawan = golonganPihakLawan;
    }

    public String getGolDebiturKreditur() {
        return golDebiturKreditur;
    }

    public void setGolDebiturKreditur(String golDebiturKreditur) {
        this.golDebiturKreditur = golDebiturKreditur;
    }
}
