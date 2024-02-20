package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.model.*;
import id.co.telkomsigma.portalapps.repository.*;
import id.co.telkomsigma.util.ExcelHelperUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @author satiya
 */
@Service
public class ExcelService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExcelService.class);

    private SetoranJaminanRepository setoranJaminanRepository;
    private AtmRepository atmRepository;
    private MdrRepository mdrRepository;
    private RekeningSlikRepository rekeningSlikRepository;
    private CabangPelaporRepository cabangPelaporRepository;
    private ReferensiAntasenaRepository referensiAntasenaRepository;
    private AsetAntarKantorRepository asetAntarKantorRepository;
    private AsetLainnyaRepository asetLainnyaRepository;
    private LiabilitasAntarKantorRepository liabilitasAntarKantorRepository;
    private LiabilitasLainnyaRepository liabilitasLainnyaRepository;
    private KodeTransaksiLainnyaRepository kodeTransaksiLainnyaRepository;
    private GolonganPemilikIndividuRepository golonganPemilikIndividuRepository;
    private CifDikecualikanRepository cifDikecualikanRepository;
    private PihakTerkaitRepository pihakTerkaitRepository;
    private HapusBukuRepository hapusBukuRepository;
    private MappingNcdRepository mappingNcdRepository;
    private KodeTransaksiKasValasRepository kodeTransaksiKasValasRepository;
    private KodeAgunanRepository kodeAgunanRepository;
    private AtiRepository atiRepository;
    private KursRepository kursRepository;
    private AtbRepository atbRepository;
    private CounterRateSbkRepository counterRateSbkRepository;
    private CounterRateSbsRepository counterRateSbsRepository;
    private PbiRepository pbiRepository;
    private PblRepository pblRepository;
    private SlikAgunanLamaRepository slikAgunanLamaRepository;
    private SlikAgunanBaruRepository slikAgunanBaruRepository;
    private NoAgunanRepository noAgunanRepository;
    private LpsRepository lpsRepository;
    private PpaRepository ppaRepository;
    private PtsRepository ptsRepository;
    private PengecualianKodeAgunanRepository pengecualianKodeAgunanRepository;
    private GolPihakLawanRepository golPihakLawanRepository;
    private CoaRepository coaRepository;
    private PihakLawanRepository pihakLawanRepository;
    private PemegangkuasaRepository pemegangkuasaRepository;
    private FraudnasabahRepository fraudnasabahRepository;
    private NasabahjoinRepository nasabahjoinRepository;
    private ImportExcelLogRepository importExcelLogRepository;
    private ExcelHelperUpload excelHelperUpload;
    private CifjoinqqRepository cifjoinqqRepository;
    private MasterCifRepository masterCifRepository;
    private CifjoinRepository cifjoinRepository;
    private CashbackRepository cashbackRepository;
    private ParamArusKasRepository paramArusKasRepository;
    private ArusKasRepository arusKasRepository;
    private ArusKasKbuRepository arusKasKbuRepository;
    private SummaryAdjustmentRepository summaryAdjustmentRepository;
    private AdjustmentThbRepository adjustmentThbRepository;
    private CoaBotRepository coaBotRepository;
    private ReverseRepoSupportRepository reverseRepoSupportRepository;
    private WeselRepository weselRepository;
    private BilyetRepository bilyetRepository;
    private LoanRepository loanRepository;
    private IrrevocableLcRepository irrevocableLcRepository;
    private BankGuaranteeRepository bankGuaranteeRepository;
    private KpmmRepository kpmmRepository;
    private SecuritiesRepository securitiesRepository;
    private IntercompanyTransactionKbankRepository intercompanyTransactionKbankRepository;
    private IntercompanyTransactionGroupRepository intercompanyTransactionGroupRepository;
    private MapLoanTypeRepository mapLoanTypeRepository;
    private InvestmentNetRepository investmentNetRepository;
    private ShortTermEmployeeBenefitsRepository employeeBenefitsRepository;
    private PostEmploymentBenefitsEmployeeIdRepository employeeIdRepository;
    private PostEmploymentBenefitsRepository benefitsRepository;
    private OtherBenefitsPaidRepository otherBenefitsPaidRepository;
    private FundingConcentrationInterbankRepository fundingConcentrationInterbankRepository;
    private CifToFiRepository cifToFiRepository;
    private DeferredTaxRepository deferredTaxRepository;

    @Autowired
    public ExcelService(
            SetoranJaminanRepository setoranJaminanRepository,
            AtmRepository atmRepository,
            MdrRepository mdrRepository,
            RekeningSlikRepository rekeningSlikRepository,
            CabangPelaporRepository cabangPelaporRepository,
            ReferensiAntasenaRepository referensiAntasenaRepository,
            AsetAntarKantorRepository asetAntarKantorRepository,
            AsetLainnyaRepository asetLainnyaRepository,
            LiabilitasAntarKantorRepository liabilitasAntarKantorRepository,
            LiabilitasLainnyaRepository liabilitasLainnyaRepository,
            KodeTransaksiLainnyaRepository kodeTransaksiLainnyaRepository,
            GolonganPemilikIndividuRepository golonganPemilikIndividuRepository,
            CifDikecualikanRepository cifDikecualikanRepository,
            PihakTerkaitRepository pihakTerkaitRepository,
            HapusBukuRepository hapusBukuRepository,
            MappingNcdRepository mappingNcdRepository,
            KodeTransaksiKasValasRepository kodeTransaksiKasValasRepository,
            KodeAgunanRepository kodeAgunanRepository,
            AtiRepository atiRepository,
            KursRepository kursRepository,
            AtbRepository atbRepository,
            CounterRateSbkRepository counterRateSbkRepository,
            CounterRateSbsRepository counterRateSbsRepository,
            PbiRepository pbiRepository,
            PblRepository pblRepository,
            SlikAgunanLamaRepository slikAgunanLamaRepository,
            SlikAgunanBaruRepository slikAgunanBaruRepository,
            NoAgunanRepository noAgunanRepository,
            LpsRepository lpsRepository,
            PpaRepository ppaRepository,
            PtsRepository ptsRepository,
            PengecualianKodeAgunanRepository pengecualianKodeAgunanRepository,
            GolPihakLawanRepository golPihakLawanRepository,
            CoaRepository coaRepository,
            PihakLawanRepository pihakLawanRepository,
            PemegangkuasaRepository pemegangkuasaRepository,
            FraudnasabahRepository fraudnasabahRepository,
            NasabahjoinRepository nasabahjoinRepository,
            ImportExcelLogRepository importExcelLogRepository,
            ExcelHelperUpload excelHelperUpload,
            CifjoinqqRepository cifjoinqqRepository,
            MasterCifRepository masterCifRepository,
            CifjoinRepository cifjoinRepository,
            CashbackRepository cashbackRepository,
            ParamArusKasRepository paramArusKasRepository,
            ArusKasRepository arusKasRepository,
            ArusKasKbuRepository arusKasKbuRepository,
            SummaryAdjustmentRepository summaryAdjustmentRepository,
            AdjustmentThbRepository adjustmentThbRepository,
            CoaBotRepository coaBotRepository,
            ReverseRepoSupportRepository reverseRepoSupportRepository,
            WeselRepository weselRepository,
            BilyetRepository bilyetRepository,
            LoanRepository loanRepository,
            IrrevocableLcRepository irrevocableLcRepository,
            BankGuaranteeRepository bankGuaranteeRepository,
            KpmmRepository kpmmRepository,
            SecuritiesRepository securitiesRepository,
            IntercompanyTransactionKbankRepository intercompanyTransactionKbankRepository,
            IntercompanyTransactionGroupRepository intercompanyTransactionGroupRepository,
            MapLoanTypeRepository mapLoanTypeRepository,
            InvestmentNetRepository investmentNetRepository,
            ShortTermEmployeeBenefitsRepository employeeBenefitsRepository,
            PostEmploymentBenefitsEmployeeIdRepository employeeIdRepository,
            PostEmploymentBenefitsRepository benefitsRepository,
            OtherBenefitsPaidRepository otherBenefitsPaidRepository,
            FundingConcentrationInterbankRepository fundingConcentrationInterbankRepository,
            CifToFiRepository cifToFiRepository,
            DeferredTaxRepository deferredTaxRepository
    ) {
        this.setoranJaminanRepository = setoranJaminanRepository;
        this.atmRepository = atmRepository;
        this.mdrRepository = mdrRepository;
        this.rekeningSlikRepository = rekeningSlikRepository;
        this.cabangPelaporRepository = cabangPelaporRepository;
        this.referensiAntasenaRepository = referensiAntasenaRepository;
        this.asetAntarKantorRepository = asetAntarKantorRepository;
        this.asetLainnyaRepository = asetLainnyaRepository;
        this.liabilitasAntarKantorRepository = liabilitasAntarKantorRepository;
        this.liabilitasLainnyaRepository = liabilitasLainnyaRepository;
        this.kodeTransaksiLainnyaRepository = kodeTransaksiLainnyaRepository;
        this.golonganPemilikIndividuRepository = golonganPemilikIndividuRepository;
        this.cifDikecualikanRepository = cifDikecualikanRepository;
        this.pihakTerkaitRepository = pihakTerkaitRepository;
        this.hapusBukuRepository = hapusBukuRepository;
        this.mappingNcdRepository = mappingNcdRepository;
        this.kodeTransaksiKasValasRepository = kodeTransaksiKasValasRepository;
        this.kodeAgunanRepository = kodeAgunanRepository;
        this.atiRepository = atiRepository;
        this.kursRepository = kursRepository;
        this.atbRepository = atbRepository;
        this.counterRateSbkRepository = counterRateSbkRepository;
        this.counterRateSbsRepository = counterRateSbsRepository;
        this.importExcelLogRepository = importExcelLogRepository;
        this.pbiRepository = pbiRepository;
        this.slikAgunanLamaRepository = slikAgunanLamaRepository;
        this.slikAgunanBaruRepository = slikAgunanBaruRepository;
        this.noAgunanRepository = noAgunanRepository;
        this.lpsRepository = lpsRepository;
        this.ppaRepository = ppaRepository;
        this.ptsRepository = ptsRepository;
        this.pengecualianKodeAgunanRepository = pengecualianKodeAgunanRepository;
        this.golPihakLawanRepository = golPihakLawanRepository;
        this.coaRepository = coaRepository;
        this.pihakLawanRepository = pihakLawanRepository;
        this.pemegangkuasaRepository = pemegangkuasaRepository;
        this.fraudnasabahRepository = fraudnasabahRepository;
        this.nasabahjoinRepository = nasabahjoinRepository;
        this.pblRepository = pblRepository;
        this.excelHelperUpload = excelHelperUpload;
        this.cifjoinqqRepository = cifjoinqqRepository;
        this.masterCifRepository = masterCifRepository;
        this.cifjoinRepository = cifjoinRepository;
        this.cashbackRepository = cashbackRepository;
        this.paramArusKasRepository = paramArusKasRepository;
        this.arusKasRepository = arusKasRepository;
        this.arusKasKbuRepository = arusKasKbuRepository;
        this.summaryAdjustmentRepository = summaryAdjustmentRepository;
        this.adjustmentThbRepository = adjustmentThbRepository;
        this.coaBotRepository = coaBotRepository;
        this.reverseRepoSupportRepository = reverseRepoSupportRepository;
        this.weselRepository = weselRepository;
        this.bilyetRepository = bilyetRepository;
        this.loanRepository = loanRepository;
        this.irrevocableLcRepository = irrevocableLcRepository;
        this.bankGuaranteeRepository = bankGuaranteeRepository;
        this.kpmmRepository = kpmmRepository;
        this.securitiesRepository = securitiesRepository;
        this.intercompanyTransactionKbankRepository = intercompanyTransactionKbankRepository;
        this.intercompanyTransactionGroupRepository = intercompanyTransactionGroupRepository;
        this.mapLoanTypeRepository = mapLoanTypeRepository;
        this.investmentNetRepository = investmentNetRepository;
        this.employeeBenefitsRepository = employeeBenefitsRepository;
        this.employeeIdRepository = employeeIdRepository;
        this.benefitsRepository = benefitsRepository;
        this.otherBenefitsPaidRepository = otherBenefitsPaidRepository;
        this.fundingConcentrationInterbankRepository = fundingConcentrationInterbankRepository;
        this.cifToFiRepository = cifToFiRepository;
        this.deferredTaxRepository = deferredTaxRepository;
    }

    public void save(MultipartFile file, String obj) {
        try {
            switch (obj) {
                case "setoranjaminan":
                    List<SetoranJaminan> setoranJaminans = ExcelHelperUpload.excelToSetoranJaminan(file.getInputStream());
                    setoranJaminanRepository.deleteAll();
                    setoranJaminanRepository.saveAll(setoranJaminans);
                 break;
                case "atm":
                    List<Atm> atms = ExcelHelperUpload.excelToAtm(file.getInputStream());
                    atmRepository.deleteAll();
                    atmRepository.saveAll(atms);
                    break;
                case "mdr":
                    List<Mdr> mdrs = ExcelHelperUpload.excelToMdr(file.getInputStream());
                    mdrRepository.deleteAll();
                    mdrRepository.saveAll(mdrs);
                    break;
                case "rekeningslik":
                    List<RekeningSlik> rekeningSliks = ExcelHelperUpload.excelToRekeningSlik(file.getInputStream());
                    rekeningSlikRepository.deleteAll();
                    rekeningSlikRepository.saveAll(rekeningSliks);
                    break;
                case "cabangpelapor":
                    List<CabangPelapor> cabangPelapors = ExcelHelperUpload.excelToCabangPelapor(file.getInputStream());
                    cabangPelaporRepository.deleteAll();
                    cabangPelaporRepository.saveAll(cabangPelapors);
                    break;
                case "referensiantasena":
                    List<ReferensiAntasena> referensiAntasenas = ExcelHelperUpload.excelToReferensiAntasena(file.getInputStream());
                    referensiAntasenaRepository.saveAll(referensiAntasenas);
                    break;
                case "asetantarkantor":
                    List<AsetAntarKantor> asetAntarKantors = ExcelHelperUpload.excelToAsetAntarKantor(file.getInputStream());
                    asetAntarKantorRepository.deleteAll();
                    asetAntarKantorRepository.saveAll(asetAntarKantors);
                    break;
                case "asetlainnya":
                    List<AsetLainnya> asetLainnyas = ExcelHelperUpload.excelToAsetLainnya(file.getInputStream());
                    asetLainnyaRepository.deleteAll();
                    asetLainnyaRepository.saveAll(asetLainnyas);
                    break;
                case "liabilitasantarkantor":
                    List<LiabilitasAntarKantor> liabilitasAntarKantors = ExcelHelperUpload.excelToLiabilitasAntarKantor(file.getInputStream());
                    liabilitasAntarKantorRepository.deleteAll();
                    liabilitasAntarKantorRepository.saveAll(liabilitasAntarKantors);
                    break;
                case "liabilitaslainnya":
                    List<LiabilitasLainnya> liabilitasLainnyas = ExcelHelperUpload.excelToLiabilitasLainnya(file.getInputStream());
                    liabilitasLainnyaRepository.deleteAll();
                    liabilitasLainnyaRepository.saveAll(liabilitasLainnyas);
                    break;
                case "kodetransaksilainnya":
                    List<KodeTransaksiLainnya> kodeTransaksiLainnyas = ExcelHelperUpload.excelToKodeTransaksiLainnya(file.getInputStream());
                    kodeTransaksiLainnyaRepository.deleteAll();
                    kodeTransaksiLainnyaRepository.saveAll(kodeTransaksiLainnyas);
                    break;
                case "golpemilikindividu":
                    List<GolonganPemilikIndividu> golonganPemilikIndividus = ExcelHelperUpload.excelToGolPemilikInd(file.getInputStream());
                    golonganPemilikIndividuRepository.deleteAll();
                    golonganPemilikIndividuRepository.saveAll(golonganPemilikIndividus);
                    break;
                case "cifdikecualikan":
                    List<CifDikecualikan> cifDikecualikans = ExcelHelperUpload.excelToCifDikecualikan(file.getInputStream());
                    cifDikecualikanRepository.deleteAll();
                    cifDikecualikanRepository.saveAll(cifDikecualikans);
                    break;
                case "pihakterkait":
                    List<PihakTerkait> pihakTerkaits = ExcelHelperUpload.excelToPihakTerkait(file.getInputStream());
                    pihakTerkaitRepository.deleteAll();
                    pihakTerkaitRepository.saveAll(pihakTerkaits);
                    break;
                case "hapusbuku":
                    List<HapusBuku> hapusBukus = ExcelHelperUpload.excelToHapusBuku(file.getInputStream());
                    hapusBukuRepository.deleteAll();
                    hapusBukuRepository.saveAll(hapusBukus);
                    break;
                case "mappingcifncd":
                    List<MappingNcd> mappingNcds = ExcelHelperUpload.excelToMappingNcd(file.getInputStream());
                    mappingNcdRepository.deleteAll();
                    mappingNcdRepository.saveAll(mappingNcds);
                    break;
                case "kodetransaksikasvalas":
                    List<KodeTransaksiKasValas> kasValass = ExcelHelperUpload.excelToKasValas(file.getInputStream());
                    kodeTransaksiKasValasRepository.deleteAll();
                    kodeTransaksiKasValasRepository.saveAll(kasValass);
                    break;
                case "tipecollateralpersentase":
                    List<KodeAgunan> kodeAgunans = ExcelHelperUpload.excelToKodeAgunan(file.getInputStream());
                    kodeAgunanRepository.deleteAll();
                    kodeAgunanRepository.saveAll(kodeAgunans);
                    break;
                case "asettetapinventaris":
                    List<Ati> atis = ExcelHelperUpload.excelToAti(file.getInputStream());
                    atiRepository.deleteAll();
                    atiRepository.saveAll(atis);
                    break;
                case "kurs":
                    List<Kurs> kurss = ExcelHelperUpload.excelToKurs(file.getInputStream());
                    kursRepository.deleteAll();
                    kursRepository.saveAll(kurss);
                    break;
                case "asettidakberwujud":
                    List<Atb> atbs = ExcelHelperUpload.excelToAtb(file.getInputStream());
                    atbRepository.deleteAll();
                    atbRepository.saveAll(atbs);
                    break;
                case "counterratesbk":
                    List<CounterRateSbk> counterRateSbks = ExcelHelperUpload.excelToRateSbk(file.getInputStream());
                    counterRateSbkRepository.deleteAll();
                    counterRateSbkRepository.saveAll(counterRateSbks);
                    break;
                case "counterratesbs":
                    List<CounterRateSbs> counterRateSbss = ExcelHelperUpload.excelToRateSbs(file.getInputStream());
                    counterRateSbsRepository.deleteAll();
                    counterRateSbsRepository.saveAll(counterRateSbss);
                    break;
                case "penempatanpadabankindonesia":
                    List<Pbi> pbis = ExcelHelperUpload.excelToPbi(file.getInputStream());
                    pbiRepository.deleteAll();
                    pbiRepository.saveAll(pbis);
                    break;
                case "penempatanpadabanklain":
                    List<Pbl> pbls = ExcelHelperUpload.excelToPbl(file.getInputStream());
                    pblRepository.deleteAll();
                    pblRepository.saveAll(pbls);
                    break;
                case "slikagunanlama":
                    List<SlikAgunanLama> slikAgunanLamas = ExcelHelperUpload.excelToSlikAgunanLama(file.getInputStream());
                    slikAgunanLamaRepository.deleteAll();
                    slikAgunanLamaRepository.saveAll(slikAgunanLamas);
                    break;
                case "slikagunanbaru":
                    List<SlikAgunanBaru> slikAgunanBarus = ExcelHelperUpload.excelToSlikAgunanBaru(file.getInputStream());
                    slikAgunanBaruRepository.deleteAll();
                    slikAgunanBaruRepository.saveAll(slikAgunanBarus);
                    break;
                case "noagunan":
                    List<NoAgunan> noAgunans = ExcelHelperUpload.excelToNoAgunan(file.getInputStream());
                    noAgunanRepository.deleteAll();
                    noAgunanRepository.saveAll(noAgunans);
                    break;
                case "bungalps":
                    List<Lps> lpss = ExcelHelperUpload.excelToBungaLps(file.getInputStream());
                    lpsRepository.deleteAll();
                    lpsRepository.saveAll(lpss);
                    break;
                case "ppa":
                    List<Ppa> ppas = ExcelHelperUpload.excelToPpa(file.getInputStream());
                    ppaRepository.deleteAll();
                    ppaRepository.saveAll(ppas);
                    break;
                case "pts":
                    List<Pts> ptss = ExcelHelperUpload.excelToPts(file.getInputStream());
                    ptsRepository.deleteAll();
                    ptsRepository.saveAll(ptss);
                    break;
                case "pengecualiankodeagunan":
                    List<PengecualianKodeAgunan> pengecualianKodeAgunans = ExcelHelperUpload.excelToPengecualianKodeAgunan(file.getInputStream());
                    pengecualianKodeAgunanRepository.deleteAll();
                    pengecualianKodeAgunanRepository.saveAll(pengecualianKodeAgunans);
                    break;
                case "golpihaklawan":
                    List<GolPihakLawan> golPihakLawans = ExcelHelperUpload.excelToGolPihakLawan(file.getInputStream());
                    golPihakLawanRepository.deleteAll();
                    golPihakLawanRepository.saveAll(golPihakLawans);
                    break;
                case "coa":
                    List<Coa> coas = ExcelHelperUpload.excelToCoa(file.getInputStream());
                    coaRepository.deleteAll();
                    coaRepository.saveAll(coas);
                    break;
                case "pihaklawan":
                    List<PihakLawan> pihakLawans = ExcelHelperUpload.excelToPihakLawan(file.getInputStream());
                    pihakLawanRepository.deleteAll();
                    pihakLawanRepository.saveAll(pihakLawans);
                    break;
                case "pemegangkuasa":
                    List<Pemegangkuasa> pemegangkuasas = ExcelHelperUpload.excelToPemegangkuasa(file.getInputStream());
                    pemegangkuasaRepository.deleteAll();
                    pemegangkuasaRepository.saveAll(pemegangkuasas);
                    break;
                case "fraudnasabah":
                    List<Fraudnasabah> fraudnasabahs = ExcelHelperUpload.excelToFraudnasabah(file.getInputStream());
                    fraudnasabahRepository.deleteAll();
                    fraudnasabahRepository.saveAll(fraudnasabahs);
                    break;
                case "nasabahjoin":
                    List<Nasabahjoin> nasabahjoins = excelHelperUpload.excelToNasabahjoinCopy(file.getInputStream());
                    nasabahjoinRepository.deleteAll();
                    nasabahjoinRepository.saveAll(nasabahjoins);
                    break;
                case "cifjoinqq":
                    List<Cifjoinqq> cifjoinqqs = ExcelHelperUpload.excelToCifjoinqq(file.getInputStream());
                    cifjoinqqRepository.deleteAll();
                    cifjoinqqRepository.saveAll(cifjoinqqs);
                    break;
                case "mastercif":
                    List<MasterCif> masterCifs = excelHelperUpload.excelToMasterCif(file.getInputStream());
                    masterCifRepository.deleteAll();
                    masterCifRepository.saveAll(masterCifs);
                    break;
                case "cifjoin":
                    List<Cifjoin> cifjoins = ExcelHelperUpload.excelToCifjoin(file.getInputStream());
                    cifjoinRepository.deleteAll();
                    cifjoinRepository.saveAll(cifjoins);
                    break;
                case "cashback":
                    List<Cashback> cashbacks = ExcelHelperUpload.excelToCashback(file.getInputStream());
                    cashbackRepository.deleteAll();
                    cashbackRepository.saveAll(cashbacks);
                    break;
                case "paramaruskas":
                    List<ParamArusKas> paramArusKass = ExcelHelperUpload.excelToParamArusKas(file.getInputStream());
                    paramArusKasRepository.deleteAll();
                    paramArusKasRepository.saveAll(paramArusKass);
                    break;
                case "aruskas":
                    List<ArusKas> arusKass = ExcelHelperUpload.excelToArusKas(file.getInputStream());
                    arusKasRepository.deleteAll();
                    arusKasRepository.saveAll(arusKass);
                    break;
                case "aruskaskbu":
                    List<ArusKasKbu> arusKasKbus = ExcelHelperUpload.excelToArusKasKbu(file.getInputStream());
                    arusKasKbuRepository.deleteAll();
                    arusKasKbuRepository.saveAll(arusKasKbus);
                    break;
                case "summaryadjustment":
                    List<SummaryAdjustment> summaryAdjustments = ExcelHelperUpload.excelToSummaryAdjustment(file.getInputStream());
                    summaryAdjustmentRepository.deleteAll();
                    summaryAdjustmentRepository.saveAll(summaryAdjustments);
                    break;
                case "adjustmentthb":
                    List<AdjustmentThb> adjustmentThbs = ExcelHelperUpload.excelToAdjustmentThb(file.getInputStream());
                    adjustmentThbRepository.deleteAll();
                    adjustmentThbRepository.saveAll(adjustmentThbs);
                    break;
                case "coabot":
                    List<CoaBot> coaBots = ExcelHelperUpload.excelToCoaBot(file.getInputStream());
                    coaBotRepository.deleteAll();
                    coaBotRepository.saveAll(coaBots);
                    break;
                case "reversereposupport":
                    List<ReverseRepoSupport> reverseRepoSupports = ExcelHelperUpload.excelToReverseRepoSupport(file.getInputStream());
                    reverseRepoSupportRepository.deleteAll();
                    reverseRepoSupportRepository.saveAll(reverseRepoSupports);
                    break;
                case "wesel":
                    List<Wesel> wesels = ExcelHelperUpload.excelToWesel(file.getInputStream());
                    weselRepository.deleteAll();
                    weselRepository.saveAll(wesels);
                    break;
                case "bilyet":
                    List<Bilyet> bilyets = ExcelHelperUpload.excelToBilyet(file.getInputStream());
                    bilyetRepository.deleteAll();
                    bilyetRepository.saveAll(bilyets);
                    break;
                case "loan":
                    List<Loan> loans = ExcelHelperUpload.excelToLoan(file.getInputStream());
                    loanRepository.deleteAll();
                    loanRepository.saveAll(loans);
                    break;
                case "irrevocablelc":
                    List<IrrevocableLc> irrevocableLcs = ExcelHelperUpload.excelToIrrevocableLc(file.getInputStream());
                    irrevocableLcRepository.deleteAll();
                    irrevocableLcRepository.saveAll(irrevocableLcs);
                    break;
                case "bankguarantee":
                    List<BankGuarantee> bankGuarantees = ExcelHelperUpload.excelToBankGuarantee(file.getInputStream());
                    bankGuaranteeRepository.deleteAll();
                    bankGuaranteeRepository.saveAll(bankGuarantees);
                    break;
                case "kpmm":
                    System.out.println("case kpmm");
                    List<Kpmm> kpmms = ExcelHelperUpload.excelToKpmm(file.getInputStream());
                    System.out.println("kpmms.size() : "+kpmms.size());
                    kpmmRepository.deleteAll();
                    kpmmRepository.saveAll(kpmms);
                    break;
                case "securities":
                    List<Securities> securitieses = ExcelHelperUpload.excelToSecurities(file.getInputStream());
                    securitiesRepository.deleteAll();
                    securitiesRepository.saveAll(securitieses);
                    break;
                case "intercompanytransactionkbank":
                    List<IntercompanyTransactionKbank> intercompanyTransactionsKbank = ExcelHelperUpload.excelToIntercompanyTransactionKbank(file.getInputStream());
                    intercompanyTransactionKbankRepository.deleteAll();
                    intercompanyTransactionKbankRepository.saveAll(intercompanyTransactionsKbank);
                    break;
                case "intercompanytransactiongroup":
                    List<IntercompanyTransactionGroup> intercompanyTransactionsGroups = ExcelHelperUpload.excelToIntercompanyTransactionGroup(file.getInputStream());
                    intercompanyTransactionGroupRepository.deleteAll();
                    intercompanyTransactionGroupRepository.saveAll(intercompanyTransactionsGroups);
                    break;
                case "maploantype":
                    List<MapLoanType> mapLoanTypes = ExcelHelperUpload.excelToMapLoanType(file.getInputStream());
                    mapLoanTypeRepository.deleteAll();
                    mapLoanTypeRepository.saveAll(mapLoanTypes);
                    break;
                case "investmentnet":
                    System.out.println("CASE investmentnet");
                    List<InvestmentNet> investmentNets = ExcelHelperUpload.excelToInvestmentNet(file.getInputStream());
                    System.out.println("investmentNets.size = "+investmentNets.size());
                    investmentNetRepository.deleteAll();
                    investmentNetRepository.saveAll(investmentNets);
                    break;
                case "shorttermemployeebenefits":
                    List<ShortTermEmployeeBenefits> employeeBenefitses = ExcelHelperUpload.excelToShortTermEmployeeBenefits(file.getInputStream());
                    employeeBenefitsRepository.deleteAll();
                    employeeBenefitsRepository.saveAll(employeeBenefitses);
                    break;
                case "postemploymentbenefitsemployeeid":
                    List<PostEmploymentBenefitsEmployeeId> benefitsEmployeeIds = ExcelHelperUpload.excelToPostEmploymentBenefitsEmployeeId(file.getInputStream());
                    employeeIdRepository.deleteAll();
                    employeeIdRepository.saveAll(benefitsEmployeeIds);
                    break;
                case "postemploymentbenefits":
                    List<PostEmploymentBenefits> benefitses = ExcelHelperUpload.excelToPostEmploymentBenefits(file.getInputStream());
                    benefitsRepository.deleteAll();
                    benefitsRepository.saveAll(benefitses);
                    break;
                case "otherbenefitspaid":
                    List<OtherBenefitsPaid> otherBenefitsPaids = ExcelHelperUpload.excelToOtherBenefitsPaid(file.getInputStream());
                    otherBenefitsPaidRepository.deleteAll();
                    otherBenefitsPaidRepository.saveAll(otherBenefitsPaids);
                    break;
                case "fundingconcentrationinterbank":
                    List<FundingConcentrationInterbank> fundingConcentrationInterbanks = ExcelHelperUpload.excelToFundingConcentrationInterbank(file.getInputStream());
                    fundingConcentrationInterbankRepository.deleteAll();
                    fundingConcentrationInterbankRepository.saveAll(fundingConcentrationInterbanks);
                    break;
                case "ciftofi":
                    List<CifToFi> cifToFis = ExcelHelperUpload.excelToCifToFi(file.getInputStream());
                    cifToFiRepository.deleteAll();
                    cifToFiRepository.saveAll(cifToFis);
                    break;
                case "deferredtax":
                    List<DeferredTax> deferredTaxs = ExcelHelperUpload.excelToDeferredTax(file.getInputStream());
                    deferredTaxRepository.deleteAll();
                    deferredTaxRepository.saveAll(deferredTaxs);
                    break;
                default:
                    throw new RuntimeException("Data is not correct");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        } catch (DataIntegrityViolationException cve) {
            throw new RuntimeException(cve.getRootCause());
        }
    }

    public void saveLog(String status, MultipartFile file, String obj, String form) {
        ImportExcelLog savedLog = new ImportExcelLog();
        long getDate = System.currentTimeMillis();

        savedLog.setInputDate(new Date(getDate));
        savedLog.setInputTime(new Time(getDate));
        savedLog.setFileName(file.getOriginalFilename());
        savedLog.setTableName("tabel" + obj);
        savedLog.setUser("admin");
        savedLog.setStatus(status);
        savedLog.setForm(form);
        importExcelLogRepository.save(savedLog);
    }
}
