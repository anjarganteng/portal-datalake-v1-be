package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.KodeAgunanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelmappingkodeagunan")
public class KodeAgunan {

    @Column(name = "no")
    private String no;

    @Id
    @Column(name = "kode_agunan_core")
    private String kodeAgunanCore;

    @Column(name = "kode_agunan_antasena")
    private String kodeAgunanAntasena;

    @Column(name = "kode_agunan_lps")
    private String kodeAgunanLps;

    @Column(name = "kode_agunan_slik")
    private String kodeAgunanSlik;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "nilai_persentase_coll_1")
    private BigDecimal nilaiPersentase;

    @Column(name = "jw_1")
    private BigDecimal jw1;

    @Column(name = "persen_1")
    private BigDecimal persen1;

    @Column(name = "jw_2")
    private BigDecimal jw2;

    @Column(name = "persen_2")
    private BigDecimal persen2;

    @Column(name = "jw_3")
    private BigDecimal jw3;

    @Column(name = "persen_3")
    private BigDecimal persen3;

    @Column(name = "jw_4")
    private BigDecimal jw4;

    @Column(name = "persen_4")
    private BigDecimal persen4;

    @Column(name = "jw_5")
    private BigDecimal jw5;

    @Column(name = "persen_5")
    private BigDecimal persen5;

    @Column(name = "jw_6")
    private BigDecimal jw6;

    @Column(name = "persen_6")
    private BigDecimal persen6;

    @Column(name = "jw_7")
    private BigDecimal jw7;

    @Column(name = "persen_7")
    private BigDecimal persen7;

    public KodeAgunan() {
    }

    public KodeAgunan(KodeAgunanRequestDTO kodeAgunanRequestDTO) {
        no = kodeAgunanRequestDTO.getNo();
        kodeAgunanCore = kodeAgunanRequestDTO.getKodeAgunanCore();
        kodeAgunanAntasena = kodeAgunanRequestDTO.getKodeAgunanAntasena();
        kodeAgunanLps = kodeAgunanRequestDTO.getKodeAgunanLps();
        kodeAgunanSlik = kodeAgunanRequestDTO.getKodeAgunanSlik();
        keterangan = kodeAgunanRequestDTO.getKeterangan();
        nilaiPersentase = kodeAgunanRequestDTO.getNilaiPersentase();
        jw1 = kodeAgunanRequestDTO.getJw1();
        persen1 = kodeAgunanRequestDTO.getPersen1();
        jw2 = kodeAgunanRequestDTO.getJw2();
        persen2 = kodeAgunanRequestDTO.getPersen2();
        jw3 = kodeAgunanRequestDTO.getJw3();
        persen3 = kodeAgunanRequestDTO.getPersen3();
        jw4 = kodeAgunanRequestDTO.getJw4();
        persen4 = kodeAgunanRequestDTO.getPersen4();
        jw5 = kodeAgunanRequestDTO.getJw5();
        persen5 = kodeAgunanRequestDTO.getPersen5();
        jw6 = kodeAgunanRequestDTO.getJw6();
        persen6 = kodeAgunanRequestDTO.getPersen6();
        jw7 = kodeAgunanRequestDTO.getJw7();
        persen7 = kodeAgunanRequestDTO.getPersen7();
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


