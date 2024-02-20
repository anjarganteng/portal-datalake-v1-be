package id.co.telkomsigma.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author satiya
 */
public class Constant {

    public class Path {

        // general path
        public static final String SAVE = "/save";
        public static final String SAVE_ROLE = "save-role";
        public static final String SAVE_EXCEL = "/save-excel";
        public static final String DOWNLOAD_EXCEL = "/download";
        public static final String LOGIN = "/login";
        public static final String GET_ALL = "/get-all";

        public static final String GET_ALL_ONLY_MODUL = "/get-all-only-modul";

        public static final String GET_BY_NO_IDENTITAS = "/get-by-no-identitas";

        public static final String NO_IDENTITAS = "/no-identitas";

        public static final String APPROVAL = "/approval";
        public static final String FIND_ALL = "/find-all";
        public static final String FIND_ALL_BOT = "/find-all-bot";
        public static final String FIND_BY_ID = "/find-by-id";
        public static final String DELETE_BY_ID = "/delete-by-id";
        public static final String GET_ALL_SELECT2 = "/get-all-select2";

        public static final String GET_ALL_SELECT2_UPLOAD = "/get-all-select2-upload";
        public static final String GET_ALL_SELECT2_MENU = "/get-all-select2-menu";
        public static final String GET_ALL_SELECT2_ROLE = "/get-all-select2-role";

        public static final String REGENERATE_DATA = "/regenerate-data";
        public static final String PROCESS = "/process";
        public static final String PROCESS_BOT = "/process-bot";

        // base path
        public static final String REFERENSI_ANTASENA = "/antasena-service/referensi-antasena";
        public static final String MDR = "/antasena-service/mdr";
        public static final String INFRA_APMK = "/antasena-service/infra-apmk";
        public static final String KODE_TRANSAKSI_LAINNYA = "/antasena-service/kode-transaksi-lainnya";
        public static final String REKENING_DIKECUALIKAN = "/antasena-service/rekening-dikecualikan";
        public static final String ATM = "/antasena-service/atm";
        public static final String REKENING_SLIK = "/antasena-service/rekening-slik";
        public static final String ASET_ANTAR_KANTOR = "/antasena-service/aset-antar-kantor";
        public static final String ASET_LAINNYA = "/antasena-service/aset-lainnya";
        public static final String SETORAN_JAMINAN = "/antasena-service/setoran-jaminan";
        public static final String LIABILITAS_ANTAR_KANTOR = "/antasena-service/liabilitas-antar-kantor";
        public static final String LIABILITAS_LAINNYA = "/antasena-service/liabilitas-lainnya";
        public static final String CABANG_PELAPOR = "/antasena-service/cabang-pelapor";
        public static final String LOG = "/antasena-service/log";
        public static final String LIST_MENU = "/antasena-service/list-menu";
        public static final String PBI = "/antasena-service/pbi";
        public static final String PBL = "/antasena-service/pbl";
        public static final String CIF_DIKECUALIKAN = "/antasena-service/cif-dikecualikan";
        public static final String GOL_PEMILIK_INDIVIDU = "/antasena-service/gol-pemilik-individu";
        public static final String KODE_AGUNAN = "/antasena-service/kode-agunan";
        public static final String PIHAK_TERKAIT = "/antasena-service/pihak-terkait";
        public static final String HAPUS_BUKU = "/antasena-service/hapus-buku";
        public static final String MAPPING_NCD = "/antasena-service/mapping-ncd";
        public static final String KAS_VALAS = "/antasena-service/kas-valas";
        public static final String KURS = "/antasena-service/kurs";
        public static final String RATE_SBK = "/antasena-service/rate-sbk";
        public static final String RATE_SBS = "/antasena-service/rate-sbs";
        public static final String ATI = "/antasena-service/ati";
        public static final String ATB = "/antasena-service/atb";
        public static final String LPS = "/antasena-service/bunga-lps";
        public static final String SLIK_AGUNAN_LAMA = "/antasena-service/slik-agunan-lama";
        public static final String SLIK_AGUNAN_BARU = "/antasena-service/slik-agunan-baru";
        public static final String NO_AGUNAN = "/antasena-service/no-agunan";
        public static final String PPA = "/antasena-service/ppa";
        public static final String PTS = "/antasena-service/pts";
        public static final String PENGECUALIAN_KODE_AGUNAN = "/antasena-service/pengecualian-kode-agunan";
        public static final String GOL_PIHAK_LAWAN = "/antasena-service/gol-pihak-lawan";
        public static final String COA = "/antasena-service/coa";
        public static final String PIHAK_LAWAN = "/antasena-service/pihak-lawan";
        public static final String PEMEGANGKUASA = "/antasena-service/pemegangkuasa";
        public static final String FRAUDNASABAH = "/antasena-service/fraudnasabah";
        public static final String NASABAHJOIN = "/antasena-service/nasabahjoin";
        public static final String LPS_NASABAHJOIN_VALIDATION_LOG = "/antasena-service/validation-log";
        public static final String DUPLICATED_CIF = "/antasena-service/duplicated-cif";
        public static final String CIF_JOIN_QQ = "/antasena-service/cifjoin-qq";
        public static final String MASTER_CIF = "/antasena-service/master-cif";
        public static final String CIF_JOIN = "/antasena-service/cif-join";
        public static final String SANDI = "/antasena-service/sandi";
        public static final String CASHBACK = "/antasena-service/cashback";
        public static final String LIMIT_SIMPANAN = "/antasena-service/limit-simpanan";
        public static final String PARAM_ARUS_KAS = "/antasena-service/parameter-arus-kas";
        public static final String ARUS_KAS = "/antasena-service/arus-kas";
        public static final String ARUS_KAS_KBU = "/antasena-service/arus-kas-kbu";
        public static final String SUMMARY_ADJUSTMENT = "/antasena-service/summary-adjustment";
        public static final String ADJUSTMENT_THB = "/antasena-service/adjustment-thb";
        public static final String COA_BOT = "/antasena-service/coa-bot";
        public static final String REVERSE_REPO_SUPPORT = "/antasena-service/reverse-repo-support";
        public static final String WESEL = "/antasena-service/wesel";
        public static final String KPMM = "/antasena-service/kpmm";
        public static final String BILYET = "/antasena-service/bilyet";
        public static final String LOAN = "/antasena-service/loan";
        public static final String IRREVOCABLE_LC = "/antasena-service/irrevocable-lc";
        public static final String BANK_GUARANTEE = "/antasena-service/bank-guarantee";
        public static final String SECURITIES = "/antasena-service/securities";
        public static final String INTERCOMPANY_TRANSACTION_KBANK = "/antasena-service/intercompany-transaction-kbank";
        public static final String INTERCOMPANY_TRANSACTION_GROUP = "/antasena-service/intercompany-transaction-group";
        public static final String MAP_LOAN_TYPE = "/antasena-service/map-loan-type";
        public static final String INVESTMENT_NET = "/antasena-service/investment-net";
        public static final String SHORT_TERM_EMPLOYEE_BENEFITS = "/antasena-service/short-term-employee-benefits";
        public static final String POST_EMPLOYMENT_BENEFITS_EMPLOYEE_ID = "/antasena-service/post-employment-benefits-employee-id";
        public static final String POST_EMPLOYMENT_BENEFITS = "/antasena-service/post-employment-benefits";
        public static final String OTHER_BENEFITS_PAID = "/antasena-service/other-benefits-paid";
        public static final String FUNDING_CONCENTRATION_INTERBANK = "/antasena-service/funding-concentration-interbank";
        public static final String CIF_TO_FI = "/antasena-service/cif-to-fi";
        public static final String DEFERRED_TAX = "/antasena-service/deferred-tax";

        // user management path
        public static final String APPLICATION_ROLES = "/antasena-service/application-roles";
        public static final String APPLICATION_ROLES_MENUS = "/antasena-service/application-roles-menus";
        public static final String APPLICATION_BRANCH = "/antasena-service/application-branch";
        public static final String APPLICATION_USERS = "/antasena-service/application-users";
        public static final String APPLICATION_PERMISSION = "/antasena-service/application-permission";

        // upload path
        public static final String UPLOAD_FILE = "/antasena-service/upload-file";
        public static final String UPLOAD_LPS = "/antasena-service/upload-lps";
        public static final String UPLOAD_PARAMETER = "/antasena-service/upload-parameter";
        public static final String UPLOAD_BOT = "/antasena-service/upload-bot";

        // log path
        public static final String REGENERATE_LOG = "/antasena-service/regenerate-log";

        // process trigger manual
        public static final String TRIGGER_MANUAL = "/antasena-service/manual-trigger";
        public static final String PROSES_MANUAL = "/antasena-service/proses-manual";
        public static final String PROSES_DW_SB = "/antasena-service/proses-dw-sukubunga";
        public static final String PROSES_PORTAL_DATALAKE = "/antasena-service/proses-portal-datalake";

        // testing path
        public static final String TESTING = "/testings";

        public static final String GET_CUSTOM_MENU = "/get-custom-menu";
    }

    public class ResponseStatus {

        public static final String TRUE = "true";
        public static final String FALSE = "false";

    }

    public class ResponseMessage {

        public static final String SUCCESS = "Your request has been successfully submitted";
        public static final String FAILED = "Oops! Something went wrong";
        public static final String FAILED_DELETE_CONSTRAINT = "Delete failed because this item is used in another module";

    }

    public static class CheckMimeType {
        public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public static final String XLS = "application/vnd.ms-excel";

        public static final Set<String> SET_OF_MIMETYPES = Collections.unmodifiableSet(
                new HashSet<String>(Arrays.asList(
                        XLS,
                        XLSX
                )));
    }

}
