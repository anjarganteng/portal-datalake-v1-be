package id.co.telkomsigma.portalapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabeldatanasabah")
public class DataNasabah {

    @Id
    @Column(name = "no_cif_per_nasabah")
    private String noCif;

    @Column(name = "nama_nasabah")
    private String namaNasabah;

    @Column(name = "no_npwp")
    private String noNpwp;

    @Column(name = "jenis_id")
    private String jenisId;

    @Column(name = "no_id")
    private String noId;

    @Column(name = "nama_ibu")
    private String namaIbu;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tgl_lahir")
    private Date tglLahir;

    @Column(name = "no_siup")
    private String noSiup;

    @Column(name = "nama_kuasa")
    private String namaKuasa;

    @Column(name = "jenis_id_kuasa")
    private String jenisIdKuasa;

    @Column(name = "no_id_kuasa")
    private String noIdKuasa;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "kab_kota")
    private String kabKota;

    @Column(name = "kewarganegaraan")
    private String kewarnegaraan;

    @Column(name = "no_telp")
    private String noTelp;

    @Column(name = "flag_fraud")
    private String flagFraud;

    @Column(name = "hub_dgn_bank")
    private String hubDgnBank;

    @Column(name = "gol_nasabah")
    private String golNasabah;

    @Column(name = "kategori_usaha")
    private String kategoriUsaha;


}
