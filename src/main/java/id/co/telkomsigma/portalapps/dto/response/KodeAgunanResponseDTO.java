package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.KodeAgunan;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class KodeAgunanResponseDTO {

    private static final long serialVersionUID = -5368629484279277550L;

    private String no;
    private String kodeAgunanCore;
    private String kodeAgunanAntasena;
    private String kodeAgunanLps;
    private String kodeAgunanSlik;
    private String keterangan;
    private BigDecimal nilaiPersentase;
    private BigDecimal jw1;
    private BigDecimal persen1;
    private BigDecimal jw2;
    private BigDecimal persen2;
    private BigDecimal jw3;
    private BigDecimal persen3;
    private BigDecimal jw4;
    private BigDecimal persen4;
    private BigDecimal jw5;
    private BigDecimal persen5;
    private BigDecimal jw6;
    private BigDecimal persen6;
    private BigDecimal jw7;
    private BigDecimal persen7;

    public KodeAgunanResponseDTO() {

    }

    public KodeAgunanResponseDTO(KodeAgunan kodeAgunan) {
        no = kodeAgunan.getNo();
        kodeAgunanCore = kodeAgunan.getKodeAgunanCore();
        kodeAgunanAntasena = kodeAgunan.getKodeAgunanAntasena();
        kodeAgunanLps = kodeAgunan.getKodeAgunanLps();
        kodeAgunanSlik = kodeAgunan.getKodeAgunanSlik();
        keterangan = kodeAgunan.getKeterangan();
        nilaiPersentase = kodeAgunan.getNilaiPersentase();
        jw1 = kodeAgunan.getJw1();
        persen1 = kodeAgunan.getPersen1();
        jw2 = kodeAgunan.getJw2();
        persen2 = kodeAgunan.getPersen2();
        jw3 = kodeAgunan.getJw3();
        persen3 = kodeAgunan.getPersen3();
        jw4 = kodeAgunan.getJw4();
        persen4 = kodeAgunan.getPersen4();
        jw5 = kodeAgunan.getJw5();
        persen5 = kodeAgunan.getPersen5();
        jw6 = kodeAgunan.getJw6();
        persen6 = kodeAgunan.getPersen6();
        jw7 = kodeAgunan.getJw7();
        persen7 = kodeAgunan.getPersen7();

    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getKodeAgunanCore() {
        return kodeAgunanCore;
    }

    public void setKodeAgunanCore(String kodeAgunanCore) {
        this.kodeAgunanCore = kodeAgunanCore;
    }

    public String getKodeAgunanAntasena() {
        return kodeAgunanAntasena;
    }

    public void setKodeAgunanAntasena(String kodeAgunanAntasena) {
        this.kodeAgunanAntasena = kodeAgunanAntasena;
    }

    public String getKodeAgunanLps() {
        return kodeAgunanLps;
    }

    public void setKodeAgunanLps(String kodeAgunanLps) {
        this.kodeAgunanLps = kodeAgunanLps;
    }

    public String getKodeAgunanSlik() {
        return kodeAgunanSlik;
    }

    public void setKodeAgunanSlik(String kodeAgunanSlik) {
        this.kodeAgunanSlik = kodeAgunanSlik;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigDecimal getNilaiPersentase() {
        return nilaiPersentase;
    }

    public void setNilaiPersentase(BigDecimal nilaiPersentase) {
        this.nilaiPersentase = nilaiPersentase;
    }

    public BigDecimal getJw1() {
        return jw1;
    }

    public void setJw1(BigDecimal jw1) {
        this.jw1 = jw1;
    }

    public BigDecimal getPersen1() {
        return persen1;
    }

    public void setPersen1(BigDecimal persen1) {
        this.persen1 = persen1;
    }

    public BigDecimal getJw2() {
        return jw2;
    }

    public void setJw2(BigDecimal jw2) {
        this.jw2 = jw2;
    }

    public BigDecimal getPersen2() {
        return persen2;
    }

    public void setPersen2(BigDecimal persen2) {
        this.persen2 = persen2;
    }

    public BigDecimal getJw3() {
        return jw3;
    }

    public void setJw3(BigDecimal jw3) {
        this.jw3 = jw3;
    }

    public BigDecimal getPersen3() {
        return persen3;
    }

    public void setPersen3(BigDecimal persen3) {
        this.persen3 = persen3;
    }

    public BigDecimal getJw4() {
        return jw4;
    }

    public void setJw4(BigDecimal jw4) {
        this.jw4 = jw4;
    }

    public BigDecimal getPersen4() {
        return persen4;
    }

    public void setPersen4(BigDecimal persen4) {
        this.persen4 = persen4;
    }

    public BigDecimal getJw5() {
        return jw5;
    }

    public void setJw5(BigDecimal jw5) {
        this.jw5 = jw5;
    }

    public BigDecimal getPersen5() {
        return persen5;
    }

    public void setPersen5(BigDecimal persen5) {
        this.persen5 = persen5;
    }

    public BigDecimal getJw6() {
        return jw6;
    }

    public void setJw6(BigDecimal jw6) {
        this.jw6 = jw6;
    }

    public BigDecimal getPersen6() {
        return persen6;
    }

    public void setPersen6(BigDecimal persen6) {
        this.persen6 = persen6;
    }

    public BigDecimal getJw7() {
        return jw7;
    }

    public void setJw7(BigDecimal jw7) {
        this.jw7 = jw7;
    }

    public BigDecimal getPersen7() {
        return persen7;
    }

    public void setPersen7(BigDecimal persen7) {
        this.persen7 = persen7;
    }
}


