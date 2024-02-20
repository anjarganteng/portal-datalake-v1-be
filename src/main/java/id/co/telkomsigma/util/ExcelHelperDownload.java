package id.co.telkomsigma.util;

import id.co.telkomsigma.portalapps.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author satiya
 */
public class ExcelHelperDownload {

    static String[] ASET_ANTAR_KANTOR_HEADERs = {"KODE COA", "DESKRIPSI COA", "USAHA KANTOR", "STATUS KANTOR", "NEGARA KANTOR", "JENIS ASET", "SUKU BUNGA RP", "SUKU BUNGA VL"};
    static String[] ASET_LAINNYA_HEADERs = {"KODE COA", "KETERANGAN JENIS LAINNYA", "JENIS ASET", "GOLONGAN DEBITUR", "HUBUNGAN DEBITUR", "PEND BKN PEND"};
    static String[] ASET_TETAP_INVENTARIS_HEADERs = {"NOMOR ASET", "JENIS ASET", "TGL PEROLEHAN", "JENIS VALUTA", "SUMBER PEROLEHAN", "METODE UKUR", "STATUS ASET", "JML BLN LALU", "JML DEBET", "JML KREDIT", "JML LAINNYA", "JML BLN LAPORAN", "AKUMULASI SUSUT"};
    static String[] ASET_TIDAK_BERWUJUD_HEADERs = {"JENIS ASET", "TGL PEROLEHAN", "TGL MULAI", "TGL JTH TEMPO", "JENIS VALUTA", "METODE UKUR", "JML BLN LALU", "JML DEBET", "JML KREDIT", "JML LAINNYA", "JML BLN LAPORAN", "AKUMULASI AMORTISASI"};
    static String[] ATM_HEADERs = {"ID", "JENIS", "STATUS", "LOKASI", "USAHA", "KETERANGAN"};
    static String[] CABANG_PELAPOR_HEADERs = {"STATUS DATA", "KODE CABANG", "KODE CABANG UTAMA", "KODE COA", "NAMA CABANG", "ALIAS CABANG", "ALIAS WILAYAH", "KODE CAB BI"};
    static String[] KODE_TRANSAKSI_LAINNYA_HEADERs = {"KODE TRANSAKSI"};
    static String[] LIABILITAS_ANTAR_KANTOR_HEADERs = {"KODE COA", "DESKRIPSI COA", "USAHA KANTOR", "STATUS KANTOR", "NEGARA KANTOR", "JENIS LIABILITAS", "PEND BKN PEND", "SUKU BUNGA RP", "SUKU BUNGA VL"};
    static String[] LIABILITAS_LAINNYA_HEADERs = {"KODE COA", "KETERANGAN JENIS LAINNYA", "JENIS LIABILITAS", "GOLONGAN KREDITUR", "HUBUNGAN KREDITUR", "NEGARA KREDITUR", "PEND BKN PEND"};
    static String[] MDR_HEADERs = {"KODE MERCHANT", "RATE"};
    static String[] REFERENSI_ANTASENA_HEADERs = {"NO", "TABEL REFERENSI", "SANDI REFERENSI", "SANDI EXISTING", "LABEL"};
    static String[] REKENING_SLIK_HEADERs = {"NO REK BARU", "NO REK LAMA"};
    static String[] SETORAN_JAMINAN_HEADERs = {"KODE COA", "DESKRIPSI COA", "TUJUAN SETORAN JAMINAN", "GOLONGAN PEMILIK", "HUB DGN PELAPOR", "PEND BKN PEND"};
    static String[] KURS_HEADERs = {"MATA UANG", "TANGGAL", "KURS JUAL", "KURS BELI", "JENIS"};
    static String[] GOL_INDIVIDU_HEADERs = {"TIPE GOL PEMILIK"};
    static String[] CIF_DIKECUALIKAN_HEADERs = {"NO CIF", "FLAG", "KETERANGAN", "FlAG DN LN"};
    static String[] PIHAK_TERKAIT_HEADERs = {"SANDI ANTASENA", "DESKRIPSI SANDI", "KODE GL", "DESKRIPSI GL", "MATA UANG"};
    static String[] HAPUS_BUKU_HEADERs = {"SANDI ANTASENA", "DESKRIPSI SANDI", "RUPIAH", "VALAS"};
    static String[] CIF_NCD_HEADERs = {"NO DEPOSITO", "NO CIF"};
    static String[] RATE_SBK_HEADERs = {"JENIS PENGGUNAAN", "JENIS VALUTA", "RATE FLAT", "RATE EFEKTIF"};
    static String[] RATE_SBS_HEADERs = {"JENIS INSTRUMEN", "JENIS VALUTA", "JANGKA WAKTU", "RATE MIN", "RATE MAX"};
    static String[] PBI_HEADERs = {"KODE COA", "DESKRIPSI COA", "DALAM LUAR NEGERI", "JENIS", "SANDI JENIS", "BRANCH", "WILAYAH", "MATA UANG", "HUB DGN BANK", "SANDI BANK", "NO REKENING", "MULAI", "JATUH TEMPO", "KLASIFIKASI ASET", "KUALITAS ASET", "SUKU BUNGA"};
    static String[] PBL_HEADERs = {"KODE COA", "DESKRIPSI COA", "DALAM LUAR NEGERI", "JENIS", "SANDI JENIS", "BRANCH", "WILAYAH", "MATA UANG", "HUB DGN BANK", "SANDI BANK", "NO REKENING", "MULAI", "JATUH TEMPO", "KLASIFIKASI ASET", "KUALITAS ASET", "PD", "LGD", "SUKU BUNGA", "JENIS SUKU BUNGA"};
    static String[] KAS_VALAS_HEADERs = {"KODE TRANSAKSI", "DESKRIPSI", "FLAG DEBET", "FLAG KREDIT"};
    static String[] KODE_AGUNAN_HEADERs = {"NO", "KODE AGUNAN CORE", "KODE AGUNAN ANTASENA", "KODE AGUNAN LPS", "KODE AGUNAN SLIK", "KETERANGAN", "NILAI PERSENTASE", "JW.1", "PERSEN.1", "JW.2", "PERSEN.2", "JW.3", "PERSEN.3", "JW.4", "PERSEN.4", "JW.5", "PERSEN.5", "JW.6", "PERSEN.6", "JW.7", "PERSEN.7"};
    static String[] SLIK_AGUNAN_LAMA_HEADERs = {"NO REK LAMA", "NO AGUNAN LAMA"};
    static String[] SLIK_AGUNAN_BARU_HEADERs = {"NO VALID", "NO AGUNAN BARU"};
    static String[] NO_AGUNAN_HEADERs = {"NO AGUNAN BARU", "NO AGUNAN LAMA"};
    static String[] BUNGA_LPS_HEADERs = {"TANGGAL MULAI", "TANGGAL JT", "JENIS VALUTA", "SUKU BUNGA"};
    static String[] PPA_HEADERs = {"KODE COA", "DESKRIPSI COA", "JENIS", "SANDI JENIS", "CABANG", "WILAYAH", "MATA UANG", "TANGGAL MULAI", "TANGGAL JTH TEMPO", "METODE UKUR", "STATUS ASET", "KUALITAS ASET", "NOMOR ASET", "HARGA PEROLEHAN", "AKUMULASI SUSUT", "NILAI BUKU"};
    static String[] PTS_HEADERs = {"SANDI ANTASENA", "DESKRIPSI SANDI", "KODE GL", "DESKRIPSI GL", "MATA UANG"};
    static String[] PENGECUALIANKODEAGUNAN_HEADERs = {"KODE AGUNAN CORE"};
    static String[] GOL_PIHAK_LAWAN_HEADERs = {"GOLONGAN PIHAK LAWAN", "GOL DEBITUR KREDITUR"};
    static String[] COA_HEADERs = {"COA INDUK", "COA DETAIL"};
    static String[] PIHAK_LAWAN_HEADERs = {"KD CABANG", "ID PIHAK LAWAN", "JENIS INDENTITAS", "NOMOR_ID", "JENIS KELAMIN", "NAMA LENGKAP", "NPWP", "KODE WN", "KODE NEGARA", "JENIS KEG USAHA", "HUB BANK", "GOL_DEB", "KD LEMBAGA", "KD RATING", "TGL RATING", "TAHUN", "KOTA", "ID GROUP"};
    static String[] PEMEGANGKUASA_HEADERs = {"NO CIF", "JENIS ID", "NOMOR ID"};
    static String[] FRAUDNASABAH_HEADERs = {"NO CIF"};
    static String[] NASABAHJOIN_HEADERs = {"CIF", "CIF JOIN", "FLAG NASABAH", "FLAG JOIN", "TIPE CIF", "NM LNKP NSB", "NO NPWP", "JNS IDENTITAS", "NO IDENTITAS", "NM IBU KDG", "TMP LAHIR", "TGL LAHIR", "NO SIUP", "NM LNKP PEMEGANG KUASA", "JNS IDENTITAS PEMEGANG KUASA", "NO IDENTITAS PEMEGANG KUASA", "ALAMAT", "KAB KOTA", "KEWARGANEGARAAN", "NO TELP", "FLAG FRAUD", "HUB DGN BANK", "GOL NSB", "KATEGORI USAHA"};
    static String[] CIFJOINQQ_HEADERs = {"CIF QQ", "NAMA LENGKAP"};
    static String[] MASTER_CIF_HEADERs = {"CIF", "NM LNKP NSB", "TIPE NASABAH", "NO NPWP", "JNS IDENTITAS", "NO IDENTITAS", "NM IBU KDG", "TMP LAHIR", "TGL LAHIR", "NO SIUP", "NM LNKP PEMEGANG KUASA", "JNS IDENTITAS PEMEGANG KUASA", "NO IDENTITAS PEMEGANG KUASA", "ALAMAT", "KAB KOTA", "KEWARGANEGARAAN", "NO TELP", "FLAG FRAUD", "HUB DGN BANK", "GOL NSB", "KATEGORI USAHA"};
    static String[] CIF_JOIN_HEADERs = {"FLAG NASABAH", "TIPE NASABAH", "NO IDENTITAS", "CIF", "CIF JOIN", "TIPE JOIN"};
    static String[] VALIDATION_LOG_HEADERs = {"ROW", "COLUMN NAME, DESCRIPTION"};
    static String[] DUPLICATE_CIF_HEADERs = {"CIF", "NO IDENTITAS", "NAMA LENGKAP NASABAH", "SUMBER"};
    static String[] CASHBACK_HEADERs = {"NO REK", "JENIS SIMPANAN", "PERSENTASE CASHBACK"};
    static String[] PARAM_ARUS_KASs = {"SANDI REFERENSI", "LABEL"};
    static String[] ARUS_KASs = {"UNIQUE ID", "TANGGAL PROYEKSI", "SANDI REFERENSI", "JENIS ARUS KAS", "JENIS VALUTA", "NOMINAL PROYEKSI"};
    static String[] ARUS_KAS_KBUs = {"UNIQUE ID", "TANGGAL PROYEKSI", "SANDI REFERENSI", "JENIS ARUS KAS", "JENIS VALUTA", "NOMINAL PROYEKSI"};
    static String[] SUMMARY_ADJUSTMENT_HEADERs = {"ADJUSTMENT NO", "COA", "DESCRIPTION", "DEBIT CREDIT", "NOTE", "FLAG"};
    static String[] ADJUSTMENT_THB_HEADERs = {"ADJUSTMENT NO", "COA", "DESCRIPTION", "DEBIT CREDIT", "NOTE"};
    static String[] COA_BOT_HEADERs = {"GL CODE", "GL NAME", "CURRENCY", "GL CODE KBANK", "ACCOUNT NAME", "PRODUCT CODE", "REPORTING", "BOT REPORT"};
    static String[] REVERSE_REPO_SUPPORT_HEADERs = {"UNDERLYING NO", "PUBLICATION DATE", "DUE DATE", "INTEREST RATE", "COUPON TYPE", "INTEREST PAYMENT FREQUENCY", "TYPE", "AMOUNT", "ROUNDING", "SECOND LEG", "CADANGAN PENDAPATAN", "OUTSTANDING"};
    static String[] WESEL_HEADERs = {"NO", "REFERENCE", "SKBDN TYPE", "PURPOSE", "ISSUED DATE", "EXPIRED DATE", "CURRENCY", "DOCUMENTS AMOUNT", "APPLICANT CUSTOMER NO"};
    static String[] KPMM_HEADERs = {"KODE FORM", "KODE KOMPONEN", "NAMA KOMPONEN", "JUMLAH"};
    static String[] BILYET_HEADERs = {"CHECK NO", "CHECK TYPE", "PURPOSE", "ISSUED DATE", "EXPIRED DATE", "CURRENCY", "DOCUMENTS AMOUNT", "APPLICANT CUSTOMER NO"};
    static String[] LOAN_HEADERs = {"CUSTOMER NO", "LOAN NO", "MOVEMENT TYPE", "OPERATION PROGRESS", "ASSET CLASS REASON", "ASSET CLASS REASON UNUSED", "TDR TYPE", "TDR METHOD TYPE", "LOAN TYPE", "REVOLVING", "COLLATERAL DESCRIPTION"};
    static String[] IRREVOCABLE_LC_HEADERs = {"CUSTOMER NO", "ACCOUNT NO", "ASSET CLASSIFICATION REASON", "ECONOMIC SECTOR"};
    static String[] BANK_GUARANTEE_HEADERs = {"CUSTOMER NO", "ACCOUNT NO", "ASSET CLASSIFICATION REASON", "ECONOMIC SECTOR"};
    static String[] SECURITIES_HEADERs = {"UNIQUE ID", "FINAL AMOUNT", "BUY AMOUNT", "MARKET TO MARKET"};
    static String[] INTERCOMPANY_KBANK_HEADERs = {"ACCOUNT TYPE", "ACCOUNT NO", "BRANCH CODE", "OGL ACCOUNT NO", "OGL AMOUNT", "BANK STATEMENT AMOUNT", "REMAIN PERIOD"};
    static String[] INTERCOMPANY_GROUP_HEADERs = {"ACCOUNT TYPE", "ACCOUNT NO", "BRANCH CODE", "OGL ACCOUNT NO", "OGL AMOUNT", "BANK STATEMENT AMOUNT", "REMAIN PERIOD", "INTERCOMPANY CODE"};
    static String[] MAP_LOAN_TYPE_HEADERs = {"LOAN TYPE", "BMI PRODUCT", "KBANK PRODUCT"};
    static String[] INVESTMENT_NET_HEADERs = {"DESCRIPTION", "REVALUATION SURPLUS", "REVALUATION DEFICIT", "EXPECTED CREDIT LOSS", "HEDGE INVESTMENT", "DIVIDEND INCOME"};
    static String[] SHORT_TERM_EMPLOYEE_BENEFITS_HEADERs = {"Short-term employee benefits", "Jan-Mar", "Apr-Jun", "Jul-Sep", "Oct-Dec"};
    static String[] POST_EMPLOYMENT_BENEFITS_EMPLOYEE_ID_HEADERs = {"Post-employment benefits (Retirement Benefits)", "31 March", "30 June", "30 September", "31 December"};
    static String[] POST_EMPLOYMENT_BENEFITS_HEADERs = {"Post-employment benefits (Retirement Benefits)", "Jan-Mar", "Apr-Jun", "Jul-Sep", "Oct-Dec"};
    static String[] OTHER_BENEFITS_PAID_HEADERs = {"Other benenfits paid to key management except for short-term benefits", "Jan-Mar", "Apr-Jun", "Jul-Sep", "Oct-Dec"};
    static String[] FUNDING_CONCENTRATION_INTERBANK_HEADERs = {"CIF NO"};
    static String[] CIF_TO_FI_HEADERs = {"CIF", "FLAG"};
    static String[] DEFERRED_TAX_HEADERs = {"CODE", "GROUP CODE", "DESCRIPTION", "DEFERRED TAX ASSET", "DEFERRED TAX LIABILITIES"};
    
    static String SHEET_ASET_ANTAR_KANTOR = "Aset Antar Kantor";
    static String SHEET_ASET_LAINNYA = "Aset Lainnya";
    static String SHEET_ATI = "ATI";
    static String SHEET_ATB = "ATB";
    static String SHEET_ATM = "ATM";
    static String SHEET_CABANG_PELAPOR = "Cabang Pelapor";
    static String SHEET_KODE_TRANSAKSI_LAINNYA = "Transaksi Lainnya";
    static String SHEET_LIABILITAS_ANTAR_KANTOR = "Liabilitas Antar Kantor";
    static String SHEET_LIABILITAS_LAINNYA = "Liabilitas Lainnya";
    static String SHEET_MDR = "MDR";
    static String SHEET_REFERENSI_ANTASENA = "Referensi Antasena";
    static String SHEET_REKENING_SLIK = "Mapping Rek Baru Lama";
    static String SHEET_SETORAN_JAMINAN = "Setoran Jaminan";
    static String SHEET_KURS = "Kurs";
    static String SHEET_GOl_INDIVIDU = "Gol Individu";
    static String SHEET_CIF_DIKECULIKAN = "Cif Dikecualikan";
    static String SHEET_PIHAK_TERKAIT = "PTS";
    static String SHEET_HAPUS_BUKU = "Hapus Buku";
    static String SHEET_CIF_NCD = "Cif Ncd";
    static String SHEET_RATE_SBK = "Sbk";
    static String SHEET_RATE_SBS = "SbS";
    static String SHEET_PBI = "Pbi";
    static String SHEET_PBL = "Pbl";
    static String SHEET_KAS_VALAS = "Kas Valas";
    static String SHEET_KODE_AGUNAN = "Kode Agunan";
    static String SHEET_SLIK_AGUNAN_LAMA = "Mapping Fasilitas Agunan";
    static String SHEET_SLIK_AGUNAN_BARU = "Slik Agunan Baru";
    static String SHEET_NO_AGUNAN = "Mapping Agunan Baru Lama";
    static String SHEET_BUNGA_LPS = "Bunga LPS";
    static String SHEET_PPA = "Ppa";
    static String SHEET_PTS = "Pts";
    static String SHEET_PENGECUALIANKODEAGUNAN = "Pengecualian Kode Agunan";
    static String SHEET_GOL_PIHAK_LAWAN = "Gol Pihak Lawan";
    static String SHEET_COA = "Coa";
    static String SHEET_PIHAK_LAWAN = "Pihak Lawan";
    static String SHEET_PEMEGANGKUASA = "Pemegangkuasa";
    static String SHEET_FRAUDNASABAH = "Fraudnasabah";
    static String SHEET_NASABAHJOIN = "Nasabahjoin";
    static String SHEET_CIFQQJOIN = "Cif Join QQ";
    static String SHEET_MASTER_CIF = "Master Cif";
    static String SHEET_CIF_JOIN = "Cif Join";
    static String SHEET_VALIDATION_LOG = "Validation Log";
    static String SHEET_DUPLICATE_CIF = "Duplicate CIF";
    static String SHEET_CASHBACK = "Cashback";
    static String SHEET_PARAM_ARUS_KAS = "Param Arus Kas";
    static String SHEET_ARUS_KAS = "Arus Kas Rpp";
    static String SHEET_ARUS_KAS_KBU = "Arus Kas Kbu";
    static String SHEET_SUMMARY_ADJUSTMENT = "Summary Adjustment";
    static String SHEET_ADJUSTMENT_THB = "Adjustment THB";
    static String SHEET_COA_BOT = "COA";
    static String SHEET_REVERSE_REPO_SUPPORT = "Reverse Repo Support";
    static String SHEET_WESEL = "Wesel";
    static String SHEET_KPMM = "KPMM";
    static String SHEET_BILYET = "Bilyet";
    static String SHEET_LOAN = "Loan";
    static String SHEET_IRREVOCABLE_LC = "Irrevocable Lc";
    static String SHEET_BANK_GUARANTEE = "Bank Guarantee";
    static String SHEET_SECURITIES = "Securities";
    static String SHEET_INTERCOMPANY_KBANK = "Intercompany Transaction Kbank";
    static String SHEET_INTERCOMPANY_GROUP = "Intercompany Transaction Group";
    static String SHEET_MAP_LOAN_TYPE = "Map Loan Type";
    static String SHEET_INVESTMENT_NET = "Investment Net";
    static String SHEET_SHORT_TERM_EMPLOYEE_BENEFITS = "Short Term Employee Benefits";
    static String SHEET_POST_EMPLOYMENT_BENEFITS_EMPLOYEE_ID = "Post Employment Benefits";
    static String SHEET_POST_EMPLOYMENT_BENEFITS = "Post Employment Benefits";
    static String SHEET_OTHER_BENEFITS_PAID = "Other Benefits Paid";
    static String SHEET_FUNDING_CONCENTRATION_INTERBANK = "Funding Concentration Interbank";
    static String SHEET_CIF_TO_FI = "CIF to FI";
    static String SHEET_DEFERRED_TAX = "Deferred Tax";

    public static ByteArrayInputStream arusKasToExcel(List<ArusKas> arusKass) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ARUS_KAS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ARUS_KASs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ARUS_KASs[col]);
            }

            // create Date Cell
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (ArusKas arusKas : arusKass) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(arusKas.getUniqueId());

                Cell cell = row.createCell(1);
                cell.setCellValue(arusKas.getTanggalProyeksi());
                cell.setCellStyle(cellStyle);

                row.createCell(2).setCellValue(arusKas.getSandiReferensi());
                row.createCell(3).setCellValue(arusKas.getJenisArusKas());
                row.createCell(4).setCellValue(arusKas.getJenisValuta());
                row.createCell(5).setCellValue(arusKas.getNominalProyeksi());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream arusKasKbuToExcel(List<ArusKasKbu> arusKasKbus) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ARUS_KAS_KBU);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ARUS_KAS_KBUs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ARUS_KAS_KBUs[col]);
            }

            // create Date Cell
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (ArusKasKbu arusKasKbu : arusKasKbus) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(arusKasKbu.getUniqueId());

                Cell cell = row.createCell(1);
                cell.setCellValue(arusKasKbu.getTanggalProyeksi());
                cell.setCellStyle(cellStyle);

                row.createCell(2).setCellValue(arusKasKbu.getSandiReferensi());
                row.createCell(3).setCellValue(arusKasKbu.getJenisArusKas());
                row.createCell(4).setCellValue(arusKasKbu.getJenisValuta());
                row.createCell(5).setCellValue(arusKasKbu.getNominalProyeksi());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream paramArusKasToExcel(List<ParamArusKas> paramArusKas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PARAM_ARUS_KAS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PARAM_ARUS_KASs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PARAM_ARUS_KASs[col]);
            }

            int rowIdx = 1;
            for (ParamArusKas paramArusKas1 : paramArusKas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(paramArusKas1.getSandiReferensi());
                row.createCell(1).setCellValue(paramArusKas1.getLabel());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream asetAntarKantorToExcel(List<AsetAntarKantor> asetAntarKantors) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ASET_ANTAR_KANTOR);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ASET_ANTAR_KANTOR_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ASET_ANTAR_KANTOR_HEADERs[col]);
            }

            int rowIdx = 1;
            for (AsetAntarKantor asetAntarKantor : asetAntarKantors) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(asetAntarKantor.getKodeCoa());
                row.createCell(1).setCellValue(asetAntarKantor.getDeskripsiCoa());
                row.createCell(2).setCellValue(asetAntarKantor.getUsahaKantor());
                row.createCell(3).setCellValue(asetAntarKantor.getStatusKantor());
                row.createCell(4).setCellValue(asetAntarKantor.getNegaraKantor());
                row.createCell(5).setCellValue(asetAntarKantor.getJenisAset());
                row.createCell(6).setCellValue(asetAntarKantor.getSukuBungaRp().doubleValue());
                row.createCell(7).setCellValue(asetAntarKantor.getSukuBungaVl().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream asetLainnyaToExcel(List<AsetLainnya> asetLainnyas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ASET_LAINNYA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ASET_LAINNYA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ASET_LAINNYA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (AsetLainnya asetLainnya : asetLainnyas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(asetLainnya.getKodeCoa());
                row.createCell(1).setCellValue(asetLainnya.getKeteranganJenisLainnya());
                row.createCell(2).setCellValue(asetLainnya.getJenisAset());
                row.createCell(3).setCellValue(asetLainnya.getGolonganDebitur());
                row.createCell(4).setCellValue(asetLainnya.getHubunganDebitur());
                row.createCell(5).setCellValue(asetLainnya.getPendBknPend());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream atmToExcel(List<Atm> atms) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ATM);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ATM_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ATM_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Atm atm : atms) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(atm.getId());
                row.createCell(1).setCellValue(atm.getJenis());
                row.createCell(2).setCellValue(atm.getStatus());
                row.createCell(3).setCellValue(atm.getLokasi());
                row.createCell(4).setCellValue(atm.getUsaha());
                row.createCell(5).setCellValue(atm.getKeterangan());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream cabangPelaporToExcel(List<CabangPelapor> cabangPelapors) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CABANG_PELAPOR);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CABANG_PELAPOR_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CABANG_PELAPOR_HEADERs[col]);
            }

            int rowIdx = 1;
            for (CabangPelapor cabangPelapor : cabangPelapors) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(cabangPelapor.getStatusData());
                row.createCell(1).setCellValue(cabangPelapor.getKodeCabang());
                row.createCell(2).setCellValue(cabangPelapor.getKodeCabangUtama());
                row.createCell(3).setCellValue(cabangPelapor.getKodeCoa());
                row.createCell(4).setCellValue(cabangPelapor.getNamaCabang());
                row.createCell(5).setCellValue(cabangPelapor.getAliasCabang());
                row.createCell(6).setCellValue(cabangPelapor.getAliasWilayah());
                row.createCell(7).setCellValue(cabangPelapor.getKodeCabBi());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream kodeTransaksiLainnyaToExcel(List<KodeTransaksiLainnya> kodeTransaksiLainnyas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_KODE_TRANSAKSI_LAINNYA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < KODE_TRANSAKSI_LAINNYA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(KODE_TRANSAKSI_LAINNYA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (KodeTransaksiLainnya kodeTransaksiLainnya : kodeTransaksiLainnyas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(kodeTransaksiLainnya.getKodeTransaksi());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream liabilitasAntarKantorToExcel(List<LiabilitasAntarKantor> liabilitasAntarKantors) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_LIABILITAS_ANTAR_KANTOR);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < LIABILITAS_ANTAR_KANTOR_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(LIABILITAS_ANTAR_KANTOR_HEADERs[col]);
            }

            int rowIdx = 1;
            for (LiabilitasAntarKantor liabilitasAntarKantor : liabilitasAntarKantors) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(liabilitasAntarKantor.getKodeCoa());
                row.createCell(1).setCellValue(liabilitasAntarKantor.getDeskripsiCoa());
                row.createCell(2).setCellValue(liabilitasAntarKantor.getUsahaKantor());
                row.createCell(3).setCellValue(liabilitasAntarKantor.getStatusKantor());
                row.createCell(4).setCellValue(liabilitasAntarKantor.getNegaraKantor());
                row.createCell(5).setCellValue(liabilitasAntarKantor.getJenisLiabilitas());
                row.createCell(6).setCellValue(liabilitasAntarKantor.getPendBknPend());
                row.createCell(7).setCellValue(liabilitasAntarKantor.getSukuBungaRp().doubleValue());
                row.createCell(8).setCellValue(liabilitasAntarKantor.getSukuBungaVl().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream liabilitasLainnyaToExcel(List<LiabilitasLainnya> liabilitasLainnyas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_LIABILITAS_LAINNYA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < LIABILITAS_LAINNYA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(LIABILITAS_LAINNYA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (LiabilitasLainnya liabilitasLainnya : liabilitasLainnyas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(liabilitasLainnya.getKodeCoa());
                row.createCell(1).setCellValue(liabilitasLainnya.getKeterangan());
                row.createCell(2).setCellValue(liabilitasLainnya.getJenisLiabilitas());
                row.createCell(3).setCellValue(liabilitasLainnya.getGolonganKreditur());
                row.createCell(4).setCellValue(liabilitasLainnya.getHubunganKreditur());
                row.createCell(5).setCellValue(liabilitasLainnya.getNegaraKreditur());
                row.createCell(6).setCellValue(liabilitasLainnya.getPendBknPend());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream mdrToExcel(List<Mdr> mdrs) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_MDR);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < MDR_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(MDR_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Mdr mdr : mdrs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(mdr.getKodeMerchant());
                row.createCell(1).setCellValue(mdr.getRate().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream referensiAntasenaToExcel(List<ReferensiAntasena> referensiAntasenas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_REFERENSI_ANTASENA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < REFERENSI_ANTASENA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(REFERENSI_ANTASENA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (ReferensiAntasena referensiAntasena : referensiAntasenas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(referensiAntasena.getNo());
                row.createCell(1).setCellValue(referensiAntasena.getTabelReferensi());
                row.createCell(2).setCellValue(referensiAntasena.getSandiReferensi());
                row.createCell(3).setCellValue(referensiAntasena.getSandiExisting());
                row.createCell(4).setCellValue(referensiAntasena.getLabel());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream rekeningSlikToExcel(List<RekeningSlik> rekeningSliks) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_REKENING_SLIK);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < REKENING_SLIK_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(REKENING_SLIK_HEADERs[col]);
            }

            int rowIdx = 1;
            for (RekeningSlik rekeningSlik : rekeningSliks) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(rekeningSlik.getNoValid());
                row.createCell(1).setCellValue(rekeningSlik.getNoSlik());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream setoranJaminanToExcel(List<SetoranJaminan> setoranJaminans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_SETORAN_JAMINAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < SETORAN_JAMINAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(SETORAN_JAMINAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (SetoranJaminan setoranJaminan : setoranJaminans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(setoranJaminan.getKodeCoa());
                row.createCell(1).setCellValue(setoranJaminan.getDeskripsiCoa());
                row.createCell(2).setCellValue(setoranJaminan.getTujuanSetoranJaminan());
                row.createCell(3).setCellValue(setoranJaminan.getGolonganPemilik());
                row.createCell(4).setCellValue(setoranJaminan.getHubDgnPelapor());
                row.createCell(5).setCellValue(setoranJaminan.getPendBknPend());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream atiToExcel(List<Ati> atis) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ATI);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ASET_TETAP_INVENTARIS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ASET_TETAP_INVENTARIS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Ati ati : atis) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(ati.getNomorAset());
                row.createCell(1).setCellValue(ati.getJenisAset());
                row.createCell(2).setCellValue(ati.getTglPerolehan());
                row.createCell(3).setCellValue(ati.getJenisValuta());
                row.createCell(4).setCellValue(ati.getSumberPerolehan());
                row.createCell(5).setCellValue(ati.getMetodeUkur());
                row.createCell(6).setCellValue(ati.getStatusAset());
                row.createCell(7).setCellValue(ati.getJmlBlnLalu());
                row.createCell(8).setCellValue(ati.getJmlDebet());
                row.createCell(9).setCellValue(ati.getJmlKredit());
                row.createCell(10).setCellValue(ati.getJmlLainnya());
                row.createCell(11).setCellValue(ati.getJmlBlnLaporan());
                row.createCell(12).setCellValue(ati.getAkumulasiSusut());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream kursToExcel(List<Kurs> kurss) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_KURS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < KURS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(KURS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Kurs kurs : kurss) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(kurs.getEmbedKurs().getMataUang());
                row.createCell(1).setCellValue(kurs.getEmbedKurs().getTanggal());
                row.createCell(2).setCellValue(kurs.getKursJual().doubleValue());
                row.createCell(3).setCellValue(kurs.getKursBeli().doubleValue());
                row.createCell(4).setCellValue(kurs.getEmbedKurs().getJenis());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream golPemilikIndToExcel(List<GolonganPemilikIndividu> golonganPemilikIndividus) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_GOl_INDIVIDU);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < GOL_INDIVIDU_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(GOL_INDIVIDU_HEADERs[col]);
            }

            int rowIdx = 1;
            for (GolonganPemilikIndividu golonganPemilikIndividu : golonganPemilikIndividus) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(golonganPemilikIndividu.getTipeGolPemilik());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream cifDikecualikanToExcel(List<CifDikecualikan> cifDikecualikans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CIF_DIKECULIKAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CIF_DIKECUALIKAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CIF_DIKECUALIKAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (CifDikecualikan cifDikecualikan : cifDikecualikans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(cifDikecualikan.getNoCif());
                row.createCell(1).setCellValue(cifDikecualikan.getFlag());
                row.createCell(2).setCellValue(cifDikecualikan.getKeterangan());
                row.createCell(3).setCellValue(cifDikecualikan.getFlagDnLn());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream pihakTerkaitToExcel(List<PihakTerkait> pihakTerkaits) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PIHAK_TERKAIT);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PIHAK_TERKAIT_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PIHAK_TERKAIT_HEADERs[col]);
            }

            int rowIdx = 1;
            for (PihakTerkait pihakTerkait : pihakTerkaits) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pihakTerkait.getSandiAntasena());
                row.createCell(1).setCellValue(pihakTerkait.getDeskripsiSandi());
                row.createCell(2).setCellValue(pihakTerkait.getKodeGl());
                row.createCell(3).setCellValue(pihakTerkait.getDeskripsiGl());
                row.createCell(4).setCellValue(pihakTerkait.getMataUang());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream hapusBukuToExcel(List<HapusBuku> hapusBukus) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_HAPUS_BUKU);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HAPUS_BUKU_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HAPUS_BUKU_HEADERs[col]);
            }

            int rowIdx = 1;
            for (HapusBuku hapusBuku : hapusBukus) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(hapusBuku.getSandiAntasena());
                row.createCell(1).setCellValue(hapusBuku.getDeskripsiSandi());
                row.createCell(2).setCellValue(hapusBuku.getRupiah());
                row.createCell(3).setCellValue(hapusBuku.getValas());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream atbToExcel(List<Atb> atbs) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ATB);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ASET_TIDAK_BERWUJUD_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ASET_TIDAK_BERWUJUD_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Atb atb : atbs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(atb.getEmbedAtb().getJenisAset());
                row.createCell(1).setCellValue(atb.getEmbedAtb().getTglPerolehan());
                row.createCell(2).setCellValue(atb.getEmbedAtb().getTglMulai());
                row.createCell(3).setCellValue(atb.getJthTempo());
                row.createCell(4).setCellValue(atb.getJenisValuta());
                row.createCell(5).setCellValue(atb.getMetodeUkur());
                row.createCell(6).setCellValue(atb.getJmlBlnLalu());
                row.createCell(7).setCellValue(atb.getJmlDebet());
                row.createCell(8).setCellValue(atb.getJmlKredit());
                row.createCell(9).setCellValue(atb.getJmlLainnya());
                row.createCell(10).setCellValue(atb.getJmlBlnLaporan());
                row.createCell(11).setCellValue(atb.getAkumulasiAmortasi());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream mappingNcdToExcel(List<MappingNcd> mappingNcds) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CIF_NCD);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CIF_NCD_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CIF_NCD_HEADERs[col]);
            }

            int rowIdx = 1;
            for (MappingNcd mappingNcd : mappingNcds) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(mappingNcd.getNoDeposito());
                row.createCell(1).setCellValue(mappingNcd.getNoCif());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream rateSbkToExcel(List<CounterRateSbk> counterRateSbks) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_RATE_SBK);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < RATE_SBK_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(RATE_SBK_HEADERs[col]);
            }

            int rowIdx = 1;
            for (CounterRateSbk counterRateSbk : counterRateSbks) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(counterRateSbk.getEmbedRateSbk().getJenisPenggunaan());
                row.createCell(1).setCellValue(counterRateSbk.getEmbedRateSbk().getJenisValuta());
                row.createCell(2).setCellValue(counterRateSbk.getRateFlat().doubleValue());
                row.createCell(3).setCellValue(counterRateSbk.getRateEfektif().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream rateSbsToExcel(List<CounterRateSbs> counterRateSbss) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_RATE_SBS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < RATE_SBS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(RATE_SBS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (CounterRateSbs counterRateSbs : counterRateSbss) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(counterRateSbs.getEmbedRateSbs().getJenisInstrumen());
                row.createCell(1).setCellValue(counterRateSbs.getEmbedRateSbs().getJenisValuta());
                row.createCell(2).setCellValue(counterRateSbs.getEmbedRateSbs().getJangkaWaktu());
                row.createCell(3).setCellValue(counterRateSbs.getRateMin().doubleValue());
                row.createCell(4).setCellValue(counterRateSbs.getRateMax().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream pbiToExcel(List<Pbi> pbis) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PBI);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PBI_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PBI_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Pbi pbi : pbis) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pbi.getEmbedPbi().getKodeCoa());
                row.createCell(1).setCellValue(pbi.getDeskripsiCoa());
                row.createCell(2).setCellValue(pbi.getDalamLuarNegeri());
                row.createCell(3).setCellValue(pbi.getJenis());
                row.createCell(4).setCellValue(pbi.getSandiJenis());
                row.createCell(5).setCellValue(pbi.getBranch());
                row.createCell(6).setCellValue(pbi.getWilayah());
                row.createCell(7).setCellValue(pbi.getEmbedPbi().getMataUang());
                row.createCell(8).setCellValue(pbi.getHubDgnBank());
                row.createCell(9).setCellValue(pbi.getSandiBank());
                row.createCell(10).setCellValue(pbi.getNoRekening());
                row.createCell(11).setCellValue(pbi.getEmbedPbi().getMulai());
                row.createCell(12).setCellValue(pbi.getJatuhTempo());
                row.createCell(13).setCellValue(pbi.getKlasifikasiAset());
                row.createCell(14).setCellValue(pbi.getKualitasAset());
                row.createCell(15).setCellValue(pbi.getSukuBunga().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream pblToExcel(List<Pbl> pbls) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PBL);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PBL_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PBL_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Pbl pbl : pbls) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pbl.getEmbedPbl().getKodeCoa());
                row.createCell(1).setCellValue(pbl.getDeskripsiCoa());
                row.createCell(2).setCellValue(pbl.getDalamLuarNegeri());
                row.createCell(3).setCellValue(pbl.getJenis());
                row.createCell(4).setCellValue(pbl.getSandiJenis());
                row.createCell(5).setCellValue(pbl.getBranch());
                row.createCell(6).setCellValue(pbl.getWilayah());
                row.createCell(7).setCellValue(pbl.getEmbedPbl().getMataUang());
                row.createCell(8).setCellValue(pbl.getHubDgnBank());
                row.createCell(9).setCellValue(pbl.getSandiBank());
                row.createCell(10).setCellValue(pbl.getNoRekening());
                row.createCell(11).setCellValue(pbl.getEmbedPbl().getMulai());
                row.createCell(12).setCellValue(pbl.getJatuhTempo());
                row.createCell(13).setCellValue(pbl.getKlasifikasiAset());
                row.createCell(14).setCellValue(pbl.getKualitasAset());
                row.createCell(15).setCellValue(pbl.getPd().doubleValue());
                row.createCell(16).setCellValue(pbl.getLgd().doubleValue());
                row.createCell(17).setCellValue(pbl.getSukuBunga().doubleValue());
                row.createCell(18).setCellValue(pbl.getJnsSukuBunga());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream kasValasToExcel(List<KodeTransaksiKasValas> kasValass) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_KAS_VALAS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < KAS_VALAS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(KAS_VALAS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (KodeTransaksiKasValas kodeTransaksiKasValas : kasValass) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(kodeTransaksiKasValas.getKodeTransaksi());
                row.createCell(1).setCellValue(kodeTransaksiKasValas.getDeskripsi());
                row.createCell(2).setCellValue(kodeTransaksiKasValas.getFlagDebet());
                row.createCell(3).setCellValue(kodeTransaksiKasValas.getFlagKredit());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream kodeAgunanToExcel(List<KodeAgunan> kodeAgunans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_KODE_AGUNAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < KODE_AGUNAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(KODE_AGUNAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (KodeAgunan kodeAgunan : kodeAgunans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(kodeAgunan.getNo());
                row.createCell(1).setCellValue(kodeAgunan.getKodeAgunanCore());
                row.createCell(2).setCellValue(kodeAgunan.getKodeAgunanAntasena());
                row.createCell(3).setCellValue(kodeAgunan.getKodeAgunanLps());
                row.createCell(4).setCellValue(kodeAgunan.getKodeAgunanSlik());
                row.createCell(5).setCellValue(kodeAgunan.getKeterangan());
                row.createCell(6).setCellValue(kodeAgunan.getNilaiPersentase().doubleValue());
                row.createCell(7).setCellValue(kodeAgunan.getJw1().doubleValue());
                row.createCell(8).setCellValue(kodeAgunan.getPersen1().doubleValue());
                row.createCell(9).setCellValue(kodeAgunan.getJw2().doubleValue());
                row.createCell(10).setCellValue(kodeAgunan.getPersen2().doubleValue());
                row.createCell(11).setCellValue(kodeAgunan.getJw3().doubleValue());
                row.createCell(12).setCellValue(kodeAgunan.getPersen3().doubleValue());
                row.createCell(13).setCellValue(kodeAgunan.getJw4().doubleValue());
                row.createCell(14).setCellValue(kodeAgunan.getPersen4().doubleValue());
                row.createCell(15).setCellValue(kodeAgunan.getJw5().doubleValue());
                row.createCell(16).setCellValue(kodeAgunan.getPersen5().doubleValue());
                row.createCell(17).setCellValue(kodeAgunan.getJw6().doubleValue());
                row.createCell(18).setCellValue(kodeAgunan.getPersen6().doubleValue());
                row.createCell(19).setCellValue(kodeAgunan.getJw7().doubleValue());
                row.createCell(20).setCellValue(kodeAgunan.getPersen7().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream slikAgunanLamaToExcel(List<SlikAgunanLama> slikAgunanLamas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_SLIK_AGUNAN_LAMA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < SLIK_AGUNAN_LAMA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(SLIK_AGUNAN_LAMA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (SlikAgunanLama slikAgunanLama : slikAgunanLamas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(slikAgunanLama.getEmbedSlikAgunanLama().getNoSlikLama());
                row.createCell(1).setCellValue(slikAgunanLama.getEmbedSlikAgunanLama().getNoAgunanLama());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream slikAgunanBaruToExcel(List<SlikAgunanBaru> slikAgunanBarus) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_SLIK_AGUNAN_BARU);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < SLIK_AGUNAN_BARU_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(SLIK_AGUNAN_BARU_HEADERs[col]);
            }

            int rowIdx = 1;
            for (SlikAgunanBaru slikAgunanBaru : slikAgunanBarus) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(slikAgunanBaru.getEmbedSlikAgunanBaru().getNoValid());
                row.createCell(1).setCellValue(slikAgunanBaru.getEmbedSlikAgunanBaru().getNoAgunanBaru());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream noAgunanToExcel(List<NoAgunan> noAgunans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_NO_AGUNAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < NO_AGUNAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(NO_AGUNAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (NoAgunan noAgunan : noAgunans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(noAgunan.getEmbedNoAgunan().getNoAgunanBaru());
                row.createCell(1).setCellValue(noAgunan.getEmbedNoAgunan().getNoAgunanLama());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream bungaLpsToExcel(List<Lps> lpss) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_BUNGA_LPS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < BUNGA_LPS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(BUNGA_LPS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Lps lps : lpss) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(lps.getEmbedLps().getTglMulai());
                row.createCell(1).setCellValue(lps.getTglJt());
                row.createCell(2).setCellValue(lps.getEmbedLps().getJenisValuta());
                row.createCell(3).setCellValue(lps.getSukuBunga().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream ppaToExcel(List<Ppa> ppas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PPA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PPA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PPA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Ppa ppa : ppas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(ppa.getKodeCoa());
                row.createCell(1).setCellValue(ppa.getDeskripsiCoa());
                row.createCell(2).setCellValue(ppa.getJenis());
                row.createCell(3).setCellValue(ppa.getEmbedPpa().getSandiJenis());
                row.createCell(4).setCellValue(ppa.getEmbedPpa().getCabang());
                row.createCell(5).setCellValue(ppa.getEmbedPpa().getWilayah());
                row.createCell(6).setCellValue(ppa.getEmbedPpa().getMataUang());
                row.createCell(7).setCellValue(ppa.getEmbedPpa().getTglMulai());
                row.createCell(8).setCellValue(ppa.getTglJthTempo());
                row.createCell(9).setCellValue(ppa.getMetodeUkur());
                row.createCell(10).setCellValue(ppa.getStatusAset());
                row.createCell(11).setCellValue(ppa.getKualitasAset());
                row.createCell(12).setCellValue(ppa.getNomorAset());
                row.createCell(13).setCellValue(ppa.getHargaPerolehan().doubleValue());
                row.createCell(14).setCellValue(ppa.getAkumulasiSusut().doubleValue());
                row.createCell(15).setCellValue(ppa.getNilaiBuku().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream ptsToExcel(List<Pts> ptss) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PTS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PTS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PTS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Pts pts : ptss) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pts.getSandiAntasena());
                row.createCell(1).setCellValue(pts.getDeskripsiSandi());
                row.createCell(2).setCellValue(pts.getKodeGl());
                row.createCell(3).setCellValue(pts.getDeskripsiGl());
                row.createCell(4).setCellValue(pts.getMataUang());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream pengecualianKodeAgunanToExcel(List<PengecualianKodeAgunan> pengecualianKodeAgunans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PENGECUALIANKODEAGUNAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PENGECUALIANKODEAGUNAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PENGECUALIANKODEAGUNAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (PengecualianKodeAgunan pengecualianKodeAgunan : pengecualianKodeAgunans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pengecualianKodeAgunan.getKodeAgunanCore());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream golPihakLawanToExcel(List<GolPihakLawan> golPihakLawans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_GOL_PIHAK_LAWAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < GOL_PIHAK_LAWAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(GOL_PIHAK_LAWAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (GolPihakLawan golPihakLawan : golPihakLawans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(golPihakLawan.getGolonganPihakLawan());
                row.createCell(1).setCellValue(golPihakLawan.getGolDebiturKreditur());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream coaToExcel(List<Coa> coas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_COA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < COA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Coa coa : coas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(coa.getEmbedCoa().getCoaInduk());
                row.createCell(1).setCellValue(coa.getEmbedCoa().getCoaDetail());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream pihakLawanToExcel(List<PihakLawan> pihakLawans) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PIHAK_LAWAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PIHAK_LAWAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PIHAK_LAWAN_HEADERs[col]);
            }

            // create Date Cell
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (PihakLawan pihakLawan : pihakLawans) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pihakLawan.getEmbedPihakLawan().getKdCabang());
                row.createCell(1).setCellValue(pihakLawan.getEmbedPihakLawan().getIdPihakLawan());
                row.createCell(2).setCellValue(pihakLawan.getJenisIdentitas());
                row.createCell(3).setCellValue(pihakLawan.getNomorId());
                row.createCell(4).setCellValue(pihakLawan.getJenisKelamin());
                row.createCell(5).setCellValue(pihakLawan.getNamaLengkap());
                row.createCell(6).setCellValue(pihakLawan.getNpwp());
                row.createCell(7).setCellValue(pihakLawan.getKodeWn());
                row.createCell(8).setCellValue(pihakLawan.getKodeNegara());
                row.createCell(9).setCellValue(pihakLawan.getJenisKegUsaha());
                row.createCell(10).setCellValue(pihakLawan.getHubBank());
                row.createCell(11).setCellValue(pihakLawan.getGolDeb());
                row.createCell(12).setCellValue(pihakLawan.getKdLembaga());
                row.createCell(13).setCellValue(pihakLawan.getKdRating());

                Cell cell = row.createCell(14);
                cell.setCellValue(pihakLawan.getTglRating());
                cell.setCellStyle(cellStyle);

                row.createCell(15).setCellValue(pihakLawan.getTahun());
                row.createCell(16).setCellValue(pihakLawan.getKota());
                row.createCell(17).setCellValue(pihakLawan.getIdGroup());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream pemegangkuasaToExcel(List<Pemegangkuasa> pemegangkuasas) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_PEMEGANGKUASA);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < PEMEGANGKUASA_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(PEMEGANGKUASA_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Pemegangkuasa pemegangkuasa : pemegangkuasas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pemegangkuasa.getNoCif());
                row.createCell(1).setCellValue(pemegangkuasa.getJenisId());
                row.createCell(2).setCellValue(pemegangkuasa.getNomorId());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream fraudnasabahToExcel(List<Fraudnasabah> fraudnasabahs) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_FRAUDNASABAH);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < FRAUDNASABAH_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(FRAUDNASABAH_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Fraudnasabah fraudnasabah : fraudnasabahs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(fraudnasabah.getNoCif());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream nasabahjoinToExcel(List<Nasabahjoin> nasabahjoins) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_NASABAHJOIN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < NASABAHJOIN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(NASABAHJOIN_HEADERs[col]);
            }

            // create Date Cell
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (Nasabahjoin nasabahjoin : nasabahjoins) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(nasabahjoin.getCif());
                row.createCell(1).setCellValue(nasabahjoin.getCifJoin());
                row.createCell(2).setCellValue(nasabahjoin.getFlagNasabah());
                row.createCell(3).setCellValue(nasabahjoin.getFlagJoin());
                row.createCell(4).setCellValue(nasabahjoin.getTipeCif());
                row.createCell(5).setCellValue(nasabahjoin.getNmLnkpNsb());
                row.createCell(6).setCellValue(nasabahjoin.getNoNpwp());
                row.createCell(7).setCellValue(nasabahjoin.getJnsIdentitas());
                row.createCell(8).setCellValue(nasabahjoin.getNoIdentitas());
                row.createCell(9).setCellValue(nasabahjoin.getNmIbuKdg());
                row.createCell(10).setCellValue(nasabahjoin.getTmpLahir());

                Cell cell = row.createCell(11);
                cell.setCellValue(nasabahjoin.getTglLahir());
                cell.setCellStyle(cellStyle);

                row.createCell(12).setCellValue(nasabahjoin.getNoSiup());
                row.createCell(13).setCellValue(nasabahjoin.getNmLnkpPemegangKuasa());
                row.createCell(14).setCellValue(nasabahjoin.getJnsIdentitasPemegangKuasa());
                row.createCell(15).setCellValue(nasabahjoin.getNoIdentitasPemegangKuasa());
                row.createCell(16).setCellValue(nasabahjoin.getAlamat());
                row.createCell(17).setCellValue(nasabahjoin.getKabKota());
                row.createCell(18).setCellValue(nasabahjoin.getKewarnegaraan());
                row.createCell(19).setCellValue(nasabahjoin.getNoTelp());
                row.createCell(20).setCellValue(nasabahjoin.getFlagFraud());
                row.createCell(21).setCellValue(nasabahjoin.getHubDgnBank());
                row.createCell(22).setCellValue(nasabahjoin.getGolNsb());
                row.createCell(23).setCellValue(nasabahjoin.getKategoriUsaha());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream cifjoinqqToExcel(List<Cifjoinqq> cifjoinqqs) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CIFQQJOIN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CIFJOINQQ_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CIFJOINQQ_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Cifjoinqq cifjoinqq : cifjoinqqs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(cifjoinqq.getCifQq());
                row.createCell(1).setCellValue(cifjoinqq.getNamaLengkap());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream masterCifToExcel(List<MasterCif> masterCifs) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_MASTER_CIF);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < MASTER_CIF_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(MASTER_CIF_HEADERs[col]);
            }

            // create Date Cell
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (MasterCif masterCif : masterCifs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(masterCif.getCif());
                row.createCell(1).setCellValue(masterCif.getNmLnkpNsb());
                row.createCell(2).setCellValue(masterCif.getTipeNasabah());
                row.createCell(3).setCellValue(masterCif.getNoNpwp());
                row.createCell(4).setCellValue(masterCif.getJnsIdentitas());
                row.createCell(5).setCellValue(masterCif.getNoIdentitas());
                row.createCell(6).setCellValue(masterCif.getNmIbuKdg());
                row.createCell(7).setCellValue(masterCif.getTmpLahir());

                Cell cell = row.createCell(8);
                cell.setCellValue(masterCif.getTglLahir());
                cell.setCellStyle(cellStyle);

                row.createCell(9).setCellValue(masterCif.getNoSiup());
                row.createCell(10).setCellValue(masterCif.getNmLnkpPemegangKuasa());
                row.createCell(11).setCellValue(masterCif.getJnsIdentitasPemegangKuasa());
                row.createCell(12).setCellValue(masterCif.getNoIdentitasPemegangKuasa());
                row.createCell(13).setCellValue(masterCif.getAlamat());
                row.createCell(14).setCellValue(masterCif.getKabKota());
                row.createCell(15).setCellValue(masterCif.getKewarnegaraan());
                row.createCell(16).setCellValue(masterCif.getNoTelp());
                row.createCell(17).setCellValue(masterCif.getFlagFraud());
                row.createCell(18).setCellValue(masterCif.getHubDgnBank());
                row.createCell(19).setCellValue(masterCif.getGolNsb());
                row.createCell(20).setCellValue(masterCif.getKategoriUsaha());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream cifjoinToExcel(List<Cifjoin> cifjoins) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CIF_JOIN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CIF_JOIN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CIF_JOIN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Cifjoin cifjoin : cifjoins) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(cifjoin.getFlagNasabah());
                row.createCell(1).setCellValue(cifjoin.getTipeNasabah());
                row.createCell(2).setCellValue(cifjoin.getNoIdentitas());
                row.createCell(3).setCellValue(cifjoin.getEmbedCifjoin().getCif());
                row.createCell(4).setCellValue(cifjoin.getEmbedCifjoin().getCifJoin());
                row.createCell(5).setCellValue(cifjoin.getTipeJoin());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream validationLogToExcel(List<ValidationImportLog> validationImportLogs) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_VALIDATION_LOG);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < VALIDATION_LOG_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(VALIDATION_LOG_HEADERs[col]);
            }

            int rowIdx = 1;
            for (ValidationImportLog validationImportLog : validationImportLogs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(validationImportLog.getRowNumber());
                row.createCell(1).setCellValue(validationImportLog.getColumnName());
                row.createCell(2).setCellValue(validationImportLog.getDescription());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream duplicateCifToExcel(List<Duplicateid> duplicateids) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_DUPLICATE_CIF);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < DUPLICATE_CIF_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(DUPLICATE_CIF_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Duplicateid duplicateid : duplicateids) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(duplicateid.getCif());
                row.createCell(1).setCellValue(duplicateid.getNoIdentitas());
                row.createCell(2).setCellValue(duplicateid.getNamaLengkap());
                row.createCell(3).setCellValue(duplicateid.getSumber());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream cashbackToExcel(List<Cashback> cashbacks) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CASHBACK);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CASHBACK_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CASHBACK_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Cashback cashback : cashbacks) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(cashback.getNoRek());
                row.createCell(1).setCellValue(cashback.getJnsSimpanan());
                row.createCell(2).setCellValue(cashback.getPersentaseCashback().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream summaryAdjustmentToExcel(List<SummaryAdjustment> summaryAdjustments) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_SUMMARY_ADJUSTMENT);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < SUMMARY_ADJUSTMENT_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(SUMMARY_ADJUSTMENT_HEADERs[col]);
            }

            int rowIdx = 1;
            for (SummaryAdjustment summaryAdjustment : summaryAdjustments) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(summaryAdjustment.getAdjustmentNo());
                row.createCell(1).setCellValue(summaryAdjustment.getCoa());
                row.createCell(2).setCellValue(summaryAdjustment.getDescription());
                row.createCell(3).setCellValue(summaryAdjustment.getDebitCredit().doubleValue());
                row.createCell(4).setCellValue(summaryAdjustment.getNote());
                row.createCell(5).setCellValue(summaryAdjustment.getFlag());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream adjustmentThbToExcel(List<AdjustmentThb> adjustmentThbs) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_ADJUSTMENT_THB);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < ADJUSTMENT_THB_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(ADJUSTMENT_THB_HEADERs[col]);
            }

            int rowIdx = 1;
            for (AdjustmentThb adjustmentThb : adjustmentThbs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(adjustmentThb.getAdjustmentNo());
                row.createCell(1).setCellValue(adjustmentThb.getCoa());
                row.createCell(2).setCellValue(adjustmentThb.getDescription());
                row.createCell(3).setCellValue(adjustmentThb.getDebitCredit().doubleValue());
                row.createCell(4).setCellValue(adjustmentThb.getNote());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream coaBotToExcel(List<CoaBot> coaBots) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_COA_BOT);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < COA_BOT_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COA_BOT_HEADERs[col]);
            }

            int rowIdx = 1;
            for (CoaBot coaBot : coaBots) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(coaBot.getEmbedCoaBot().getGlCode());
                row.createCell(1).setCellValue(coaBot.getGlName());
                row.createCell(2).setCellValue(coaBot.getEmbedCoaBot().getCurrency());
                row.createCell(3).setCellValue(coaBot.getGlCodeKbank());
                row.createCell(4).setCellValue(coaBot.getAccountName());
                row.createCell(5).setCellValue(coaBot.getProductCode());
                row.createCell(6).setCellValue(coaBot.getReporting());
                row.createCell(7).setCellValue(coaBot.getBotReport());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream reverseRepoSupportsToExcel(List<ReverseRepoSupport> reverseRepoSupports) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_REVERSE_REPO_SUPPORT);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < REVERSE_REPO_SUPPORT_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(REVERSE_REPO_SUPPORT_HEADERs[col]);
            }

            // create Date Cell
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (ReverseRepoSupport reverseRepoSupport : reverseRepoSupports) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(reverseRepoSupport.getUnderlyingNo());
                
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(reverseRepoSupport.getPublicationDate());
                cell1.setCellStyle(dateCellStyle);
                
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(reverseRepoSupport.getDueDate());
                cell2.setCellStyle(dateCellStyle);
                
                row.createCell(3).setCellValue(reverseRepoSupport.getInterestRate().doubleValue());
                row.createCell(4).setCellValue(reverseRepoSupport.getCouponType());
                row.createCell(5).setCellValue(reverseRepoSupport.getInterestPaymentFrequency());
                row.createCell(6).setCellValue(reverseRepoSupport.getType());
                row.createCell(7).setCellValue(reverseRepoSupport.getAmount().doubleValue());
                row.createCell(8).setCellValue(reverseRepoSupport.getRounding().doubleValue());
                row.createCell(9).setCellValue(reverseRepoSupport.getSecondLeg().doubleValue());
                row.createCell(10).setCellValue(reverseRepoSupport.getCadanganPendapatan().doubleValue());
                row.createCell(11).setCellValue(reverseRepoSupport.getOutstanding().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream weselToExcel(List<Wesel> wesels) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_WESEL);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < WESEL_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(WESEL_HEADERs[col]);
            }

            // create Date Cell
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            int rowIdx = 1;
            for (Wesel wesel : wesels) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(wesel.getNo());
                row.createCell(1).setCellValue(wesel.getReference());
                row.createCell(2).setCellValue(wesel.getSkbdnType());
                row.createCell(3).setCellValue(wesel.getPurpose());
                
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(wesel.getIssuedDate());
                cell4.setCellStyle(dateCellStyle);
                
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(wesel.getExpiredDate());
                cell5.setCellStyle(dateCellStyle);
                
                row.createCell(6).setCellValue(wesel.getCurrency());
                row.createCell(7).setCellValue(wesel.getDocumentsAmount().doubleValue());
                row.createCell(8).setCellValue(wesel.getApplicantCustomerNo());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream kpmmToExcel(List<Kpmm> kpmms) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_KPMM);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < KPMM_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(KPMM_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Kpmm kpmm : kpmms) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(kpmm.getEmbedKpmm().getKodeForm());
                row.createCell(1).setCellValue(kpmm.getEmbedKpmm().getKodeKomponen());
                row.createCell(2).setCellValue(kpmm.getNamaKomponen());
                row.createCell(3).setCellValue(kpmm.getJumlah().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream bilyetToExcel(List<Bilyet> bilyets) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_BILYET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < BILYET_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(BILYET_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Bilyet bilyet : bilyets) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(bilyet.getCheckNo());
                row.createCell(1).setCellValue(bilyet.getCheckType());
                row.createCell(2).setCellValue(bilyet.getPurpose());
                row.createCell(3).setCellValue(bilyet.getIssuedDate());
                row.createCell(4).setCellValue(bilyet.getExpiredDate());
                row.createCell(5).setCellValue(bilyet.getCurrency());
                row.createCell(6).setCellValue(bilyet.getDocumentsAmount().doubleValue());
                row.createCell(7).setCellValue(bilyet.getApplicantCustomerNo());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream loanToExcel(List<Loan> loans) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_LOAN);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < LOAN_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(LOAN_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Loan loan : loans) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(loan.getEmbedLoan().getCustomerNo());
                row.createCell(1).setCellValue(loan.getEmbedLoan().getLoanNo());
                row.createCell(2).setCellValue(loan.getMovementType());
                row.createCell(3).setCellValue(loan.getOperationProgress());
                row.createCell(4).setCellValue(loan.getAssetClassReason());
                row.createCell(5).setCellValue(loan.getAssetClassReasonUnused());
                row.createCell(6).setCellValue(loan.getTdrType());
                row.createCell(7).setCellValue(loan.getTdrMethodType());
                row.createCell(8).setCellValue(loan.getLoanType());
                row.createCell(9).setCellValue(loan.getRevolving());
                row.createCell(10).setCellValue(loan.getCollateralDesc());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream irrevocableLcToExcel(List<IrrevocableLc> irrevocableLcs) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_IRREVOCABLE_LC);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < IRREVOCABLE_LC_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(IRREVOCABLE_LC_HEADERs[col]);
            }

            int rowIdx = 1;
            for (IrrevocableLc irrevocableLc : irrevocableLcs) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(irrevocableLc.getEmbedIrrevocableLc().getCustomerNo());
                row.createCell(1).setCellValue(irrevocableLc.getEmbedIrrevocableLc().getAccountNo());
                row.createCell(2).setCellValue(irrevocableLc.getAsetClassifitcationReason());
                row.createCell(3).setCellValue(irrevocableLc.getEconomicSector());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream bankGuaranteeToExcel(List<BankGuarantee> bankGuarantees) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_BANK_GUARANTEE);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < BANK_GUARANTEE_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(BANK_GUARANTEE_HEADERs[col]);
            }

            int rowIdx = 1;
            for (BankGuarantee bankGuarantee : bankGuarantees) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(bankGuarantee.getEmbedBankGuarantee().getCustomerNo());
                row.createCell(1).setCellValue(bankGuarantee.getEmbedBankGuarantee().getAccountNo());
                row.createCell(2).setCellValue(bankGuarantee.getAsetClassifitcationReason());
                row.createCell(3).setCellValue(bankGuarantee.getEconomicSector());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream securitiesToExcel(List<Securities> securitieses) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_SECURITIES);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < SECURITIES_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(SECURITIES_HEADERs[col]);
            }

            int rowIdx = 1;
            for (Securities securities : securitieses) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(securities.getUniqueId());
                row.createCell(1).setCellValue(securities.getFinalAmount().doubleValue());
                row.createCell(2).setCellValue(securities.getBuyAmount().doubleValue());
                row.createCell(3).setCellValue(securities.getMarketToMarket().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream intercompanyKbankToExcel(List<IntercompanyTransactionKbank> transactionKbanks) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_INTERCOMPANY_KBANK);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < INTERCOMPANY_KBANK_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(INTERCOMPANY_KBANK_HEADERs[col]);
            }

            int rowIdx = 1;
            for (IntercompanyTransactionKbank transactionKbank : transactionKbanks) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(transactionKbank.getEmbedIntercompanyTransactionKbank().getAccountType());
                row.createCell(1).setCellValue(transactionKbank.getEmbedIntercompanyTransactionKbank().getAccountNo());
                row.createCell(2).setCellValue(transactionKbank.getBranchCode());
                row.createCell(3).setCellValue(transactionKbank.getOglAccountNo());
                row.createCell(4).setCellValue(transactionKbank.getOglAmount().doubleValue());
                row.createCell(5).setCellValue(transactionKbank.getBankStatementAmount().doubleValue());
                row.createCell(6).setCellValue(transactionKbank.getRemainPeriod());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream intercompanyGroupToExcel(List<IntercompanyTransactionGroup> transactionGroups) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_INTERCOMPANY_GROUP);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < INTERCOMPANY_GROUP_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(INTERCOMPANY_GROUP_HEADERs[col]);
            }

            int rowIdx = 1;
            for (IntercompanyTransactionGroup transactionGroup : transactionGroups) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(transactionGroup.getEmbedIntercompanyTransactionGroup().getAccountType());
                row.createCell(1).setCellValue(transactionGroup.getEmbedIntercompanyTransactionGroup().getAccountNo());
                row.createCell(2).setCellValue(transactionGroup.getBranchCode());
                row.createCell(3).setCellValue(transactionGroup.getOglAccountNo());
                row.createCell(4).setCellValue(transactionGroup.getOglAmount().doubleValue());
                row.createCell(5).setCellValue(transactionGroup.getBankStatementAmount().doubleValue());
                row.createCell(6).setCellValue(transactionGroup.getRemainPeriod());
                row.createCell(7).setCellValue(transactionGroup.getIntercompanyCode());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream mapLoanTypeToExcel(List<MapLoanType> mapLoanTypes) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_MAP_LOAN_TYPE);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < MAP_LOAN_TYPE_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(MAP_LOAN_TYPE_HEADERs[col]);
            }

            int rowIdx = 1;
            for (MapLoanType mapLoanType : mapLoanTypes) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(mapLoanType.getEmbedMapLoanType().getLoanType());
                row.createCell(1).setCellValue(mapLoanType.getEmbedMapLoanType().getBmiProduct());
                row.createCell(2).setCellValue(mapLoanType.getKbankProduct());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream investmentNetToExcel(List<InvestmentNet> investmentNets) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_INVESTMENT_NET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < INVESTMENT_NET_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(INVESTMENT_NET_HEADERs[col]);
            }

            int rowIdx = 1;
            for (InvestmentNet investmentNet : investmentNets) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(investmentNet.getDescription());
                row.createCell(1).setCellValue(investmentNet.getRevaluationSurplus().doubleValue());
                row.createCell(2).setCellValue(investmentNet.getRevaluationDeficit().doubleValue());
                row.createCell(3).setCellValue(investmentNet.getExpectedCreditLoss().doubleValue());
                row.createCell(4).setCellValue(investmentNet.getHedgeInvestment().doubleValue());
                row.createCell(5).setCellValue(investmentNet.getDividendIncome().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream shortTermEmployeeBenefitsToExcel(List<ShortTermEmployeeBenefits> benefitses) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_SHORT_TERM_EMPLOYEE_BENEFITS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < SHORT_TERM_EMPLOYEE_BENEFITS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(SHORT_TERM_EMPLOYEE_BENEFITS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (ShortTermEmployeeBenefits benefits : benefitses) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(benefits.getDescription());
                row.createCell(1).setCellValue(benefits.getJanMar().doubleValue());
                row.createCell(2).setCellValue(benefits.getAprJun().doubleValue());
                row.createCell(3).setCellValue(benefits.getJulSep().doubleValue());
                row.createCell(4).setCellValue(benefits.getOctDec().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream postEmployeeBenefitsEmployeeIdToExcel(List<PostEmploymentBenefitsEmployeeId> benefitsEmployeeIds) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_POST_EMPLOYMENT_BENEFITS_EMPLOYEE_ID);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < POST_EMPLOYMENT_BENEFITS_EMPLOYEE_ID_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(POST_EMPLOYMENT_BENEFITS_EMPLOYEE_ID_HEADERs[col]);
            }

            int rowIdx = 1;
            for (PostEmploymentBenefitsEmployeeId benefitsEmployeeId : benefitsEmployeeIds) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(benefitsEmployeeId.getDescription());
                row.createCell(1).setCellValue(benefitsEmployeeId.getMar().doubleValue());
                row.createCell(2).setCellValue(benefitsEmployeeId.getJun().doubleValue());
                row.createCell(3).setCellValue(benefitsEmployeeId.getSep().doubleValue());
                row.createCell(4).setCellValue(benefitsEmployeeId.getDec().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream postEmployeeBenefitsToExcel(List<PostEmploymentBenefits> benefitses) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_POST_EMPLOYMENT_BENEFITS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < POST_EMPLOYMENT_BENEFITS_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(POST_EMPLOYMENT_BENEFITS_HEADERs[col]);
            }

            int rowIdx = 1;
            for (PostEmploymentBenefits benefits : benefitses) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(benefits.getDescription());
                row.createCell(1).setCellValue(benefits.getJanMar().doubleValue());
                row.createCell(2).setCellValue(benefits.getAprJun().doubleValue());
                row.createCell(3).setCellValue(benefits.getJulSep().doubleValue());
                row.createCell(4).setCellValue(benefits.getOctDec().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream otherBenefitsPaidToExcel(List<OtherBenefitsPaid> otherBenefitsPaids) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_OTHER_BENEFITS_PAID);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < OTHER_BENEFITS_PAID_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(OTHER_BENEFITS_PAID_HEADERs[col]);
            }

            int rowIdx = 1;
            for (OtherBenefitsPaid otherBenefitsPaid : otherBenefitsPaids) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(otherBenefitsPaid.getDescription());
                row.createCell(1).setCellValue(otherBenefitsPaid.getJanMar().doubleValue());
                row.createCell(2).setCellValue(otherBenefitsPaid.getAprJun().doubleValue());
                row.createCell(3).setCellValue(otherBenefitsPaid.getJulSep().doubleValue());
                row.createCell(4).setCellValue(otherBenefitsPaid.getOctDec().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream fundingConcentrationInterbankToExcel(List<FundingConcentrationInterbank> fundingConcentrationInterbanks) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_FUNDING_CONCENTRATION_INTERBANK);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < FUNDING_CONCENTRATION_INTERBANK_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(FUNDING_CONCENTRATION_INTERBANK_HEADERs[col]);
            }

            int rowIdx = 1;
            for (FundingConcentrationInterbank fundingConcentrationInterbank : fundingConcentrationInterbanks) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(fundingConcentrationInterbank.getCifNo());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream cifToFiToExcel(List<CifToFi> cifToFis) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_CIF_TO_FI);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < CIF_TO_FI_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(CIF_TO_FI_HEADERs[col]);
            }

            int rowIdx = 1;
            for (CifToFi cifToFi : cifToFis) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(cifToFi.getEmbedCifToFi().getCifNo());
                row.createCell(1).setCellValue(cifToFi.getEmbedCifToFi().getFlag());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
    public static ByteArrayInputStream deferredTaxToExcel(List<DeferredTax> deferredTaxs) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET_DEFERRED_TAX);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < DEFERRED_TAX_HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(DEFERRED_TAX_HEADERs[col]);
            }

            int rowIdx = 1;
            for (DeferredTax deferredTax : deferredTaxs) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(deferredTax.getEmbedDeferredTax().getCode());
                row.createCell(1).setCellValue(deferredTax.getEmbedDeferredTax().getGroupCode());
                row.createCell(2).setCellValue(deferredTax.getDescription());
                row.createCell(3).setCellValue(deferredTax.getDeferredTaxAssets().doubleValue());
                row.createCell(4).setCellValue(deferredTax.getDeferredTaxLiabilities().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to export data to Excel file: " + e.getMessage());
        }
    }
    
}
