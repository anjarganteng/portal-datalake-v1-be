package id.co.telkomsigma.util;

import id.co.telkomsigma.util.embeddables.EmbedCoaBot;
import id.co.telkomsigma.exception.ErrorUploadException;
import id.co.telkomsigma.exception.PrimaryKeyNullException;
import id.co.telkomsigma.portalapps.model.*;
import id.co.telkomsigma.portalapps.repository.ValidationImportLogRepository;
import id.co.telkomsigma.portalapps.service.SandiService;
import id.co.telkomsigma.util.embeddables.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author satiya
 */
@Component
public class ExcelHelperUpload {

    public ValidationImportLogRepository validationImportLogRepository;
    public SandiService sandiService;

    @Autowired
    public ExcelHelperUpload(ValidationImportLogRepository validationImportLogRepository,
                             SandiService sandiService) {
        this.validationImportLogRepository = validationImportLogRepository;
        this.sandiService = sandiService;
    }

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<ArusKas> excelToArusKas(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<ArusKas> arusKass = new ArrayList<ArusKas>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                ArusKas arusKas = new ArusKas();

                // casting type data from file
                Cell uniqueId = cells.get(0);
                if (uniqueId != null) {
                    switch (uniqueId.getCellType()) {
                        case NUMERIC:
                            arusKas.setUniqueId(NumberToTextConverter.toText(uniqueId.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setUniqueId(uniqueId.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Unique ID can't be blank, Row: " + getRowKodeCoa);
                }

                Cell tanggalProyeksi = cells.get(1);
                if (tanggalProyeksi != null) {

                    String strDate = "f";

                    if (tanggalProyeksi.getCellType() == CellType.STRING) {

                        String datas = tanggalProyeksi.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];

                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(tanggalProyeksi.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(tanggalProyeksi);
                    }

                    switch (tanggalProyeksi.getCellType()) {
                        case STRING:
                            arusKas.setTanggalProyeksi(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            arusKas.setTanggalProyeksi(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }

                Cell sandiReferensi = cells.get(2);
                if (sandiReferensi != null) {
                    switch (sandiReferensi.getCellType()) {
                        case NUMERIC:
                            arusKas.setSandiReferensi(NumberToTextConverter.toText(sandiReferensi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setSandiReferensi(sandiReferensi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisArusKas = cells.get(3);
                if (jenisArusKas != null) {
                    switch (jenisArusKas.getCellType()) {
                        case NUMERIC:
                            arusKas.setJenisArusKas(NumberToTextConverter.toText(jenisArusKas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setJenisArusKas(jenisArusKas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisValuta = cells.get(4);
                if (jenisValuta != null) {
                    switch (jenisValuta.getCellType()) {
                        case NUMERIC:
                            arusKas.setJenisValuta(NumberToTextConverter.toText(jenisValuta.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setJenisValuta(jenisValuta.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell nominalProyeksi = cells.get(5);
                if (nominalProyeksi != null) {
                    switch (nominalProyeksi.getCellType()) {
                        case NUMERIC:
                            arusKas.setNominalProyeksi(NumberToTextConverter.toText(nominalProyeksi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setNominalProyeksi(nominalProyeksi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                arusKass.add(arusKas);
            }

            workbook.close();

            return arusKass;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<ArusKasKbu> excelToArusKasKbu(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<ArusKasKbu> arusKasKbus = new ArrayList<ArusKasKbu>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                ArusKasKbu arusKas = new ArusKasKbu();

                // casting type data from file
                Cell uniqueId = cells.get(0);
                if (uniqueId != null) {
                    switch (uniqueId.getCellType()) {
                        case NUMERIC:
                            arusKas.setUniqueId(NumberToTextConverter.toText(uniqueId.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setUniqueId(uniqueId.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Unique ID can't be blank, Row: " + getRowKodeCoa);
                }

                Cell tanggalProyeksi = cells.get(1);
                if (tanggalProyeksi != null) {

                    String strDate = "f";

                    if (tanggalProyeksi.getCellType() == CellType.STRING) {

                        String datas = tanggalProyeksi.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];

                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(tanggalProyeksi.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(tanggalProyeksi);
                    }

                    switch (tanggalProyeksi.getCellType()) {
                        case STRING:
                            arusKas.setTanggalProyeksi(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            arusKas.setTanggalProyeksi(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }

                Cell sandiReferensi = cells.get(2);
                if (sandiReferensi != null) {
                    switch (sandiReferensi.getCellType()) {
                        case NUMERIC:
                            arusKas.setSandiReferensi(NumberToTextConverter.toText(sandiReferensi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setSandiReferensi(sandiReferensi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisArusKas = cells.get(3);
                if (jenisArusKas != null) {
                    switch (jenisArusKas.getCellType()) {
                        case NUMERIC:
                            arusKas.setJenisArusKas(NumberToTextConverter.toText(jenisArusKas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setJenisArusKas(jenisArusKas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisValuta = cells.get(4);
                if (jenisValuta != null) {
                    switch (jenisValuta.getCellType()) {
                        case NUMERIC:
                            arusKas.setJenisValuta(NumberToTextConverter.toText(jenisValuta.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setJenisValuta(jenisValuta.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell nominalProyeksi = cells.get(5);
                if (nominalProyeksi != null) {
                    switch (nominalProyeksi.getCellType()) {
                        case NUMERIC:
                            arusKas.setNominalProyeksi(NumberToTextConverter.toText(nominalProyeksi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            arusKas.setNominalProyeksi(nominalProyeksi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                arusKasKbus.add(arusKas);
            }

            workbook.close();

            return arusKasKbus;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<ParamArusKas> excelToParamArusKas(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<ParamArusKas> paramArusKass = new ArrayList<ParamArusKas>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                ParamArusKas paramArusKas = new ParamArusKas();

                // casting type data from file
                Cell sandiReferensi = cells.get(0);
                if (sandiReferensi != null) {
                    switch (sandiReferensi.getCellType()) {
                        case NUMERIC:
                            paramArusKas.setSandiReferensi(NumberToTextConverter.toText(sandiReferensi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            paramArusKas.setSandiReferensi(sandiReferensi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Sandi Referensi can't be blank, Row: " + getRowKodeCoa);
                }

                Cell label = cells.get(1);
                if (label != null) {
                    switch (sandiReferensi.getCellType()) {
                        case NUMERIC:
                            paramArusKas.setLabel(NumberToTextConverter.toText(label.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            paramArusKas.setLabel(label.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                paramArusKass.add(paramArusKas);
            }

            workbook.close();

            return paramArusKass;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<AsetAntarKantor> excelToAsetAntarKantor(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<AsetAntarKantor> asetAntarKantors = new ArrayList<AsetAntarKantor>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 8);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                AsetAntarKantor asetAntarKantor = new AsetAntarKantor();

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            asetAntarKantor.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetAntarKantor.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode COA can't be blank, Row: " + getRowKodeCoa);
                }

                Cell deskripsiCoa = cells.get(1);
                if (deskripsiCoa != null) {
                    asetAntarKantor.setDeskripsiCoa(deskripsiCoa.getStringCellValue());
                }

                Cell usahaKantor = cells.get(2);
                if (usahaKantor != null) {
                    asetAntarKantor.setUsahaKantor(usahaKantor.getStringCellValue());
                }

                Cell statuskantor = cells.get(3);
                if (statuskantor != null) {
                    asetAntarKantor.setStatusKantor(statuskantor.getStringCellValue());
                }

                Cell negaraKantor = cells.get(4);
                if (negaraKantor != null) {
                    asetAntarKantor.setNegaraKantor(negaraKantor.getStringCellValue());
                }

                Cell jenisAset = cells.get(5);
                if (jenisAset != null) {
                    asetAntarKantor.setJenisAset(jenisAset.getStringCellValue());
                }

                Cell sukuBungaRp = cells.get(6);
                if (sukuBungaRp != null) {
                    switch (sukuBungaRp.getCellType()) {
                        case NUMERIC:
                            asetAntarKantor.setSukuBungaRp(BigDecimal.valueOf(sukuBungaRp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell sukuBungaVl = cells.get(7);
                if (sukuBungaVl != null) {
                    switch (sukuBungaVl.getCellType()) {
                        case NUMERIC:
                            asetAntarKantor.setSukuBungaVl(BigDecimal.valueOf(sukuBungaVl.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                asetAntarKantors.add(asetAntarKantor);
            }

            workbook.close();

            return asetAntarKantors;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<AsetLainnya> excelToAsetLainnya(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<AsetLainnya> asetLainnyas = new ArrayList<AsetLainnya>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                AsetLainnya asetLainnya = new AsetLainnya();

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            asetLainnya.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetLainnya.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode COA can't be blank, Row: " + getRowKodeCoa);
                }

                Cell keteranganJenisLainnya = cells.get(1);
                if (keteranganJenisLainnya != null) {
                    asetLainnya.setKeteranganJenisLainnya(keteranganJenisLainnya.getStringCellValue());
                }

                Cell jenisAset = cells.get(2);
                if (jenisAset != null) {
                    asetLainnya.setJenisAset(jenisAset.getStringCellValue());
                }

                Cell golonganDebitur = cells.get(3);
                if (golonganDebitur != null) {
                    asetLainnya.setGolonganDebitur(golonganDebitur.getStringCellValue());
                }

                Cell hubunganDebitur = cells.get(4);
                if (hubunganDebitur != null) {
                    asetLainnya.setHubunganDebitur(hubunganDebitur.getStringCellValue());
                }

                Cell pendBknPend = cells.get(5);
                if (pendBknPend != null) {
                    switch (pendBknPend.getCellType()) {
                        case NUMERIC:
                            asetLainnya.setPendBknPend(NumberToTextConverter.toText(pendBknPend.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetLainnya.setPendBknPend(pendBknPend.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                asetLainnyas.add(asetLainnya);
            }

            workbook.close();

            return asetLainnyas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Atm> excelToAtm(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Atm> atms = new ArrayList<Atm>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Atm atm = new Atm();

                // casting type data from file
                Cell id = cells.get(0);
                if (id != null) {
                    atm.setId(id.getStringCellValue());
                } else {
                    int getRowId = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column ID can't be blank, Row: " + getRowId);
                }

                Cell jenis = cells.get(1);
                if (jenis != null) {
                    atm.setJenis(jenis.getStringCellValue());
                }

                Cell status = cells.get(2);
                if (status != null) {
                    atm.setStatus(status.getStringCellValue());
                }

                Cell lokasi = cells.get(3);
                if (lokasi != null) {
                    switch (lokasi.getCellType()) {
                        case NUMERIC:
                            atm.setLokasi(NumberToTextConverter.toText(lokasi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atm.setLokasi(lokasi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell usaha = cells.get(4);
                if (usaha != null) {
                    atm.setUsaha(usaha.getStringCellValue());
                }

                Cell keterangan = cells.get(5);
                if (keterangan != null) {
                    atm.setKeterangan(keterangan.getStringCellValue());
                }

                atms.add(atm);
            }

            workbook.close();

            return atms;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<CabangPelapor> excelToCabangPelapor(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<CabangPelapor> cabangPelapors = new ArrayList<CabangPelapor>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 7);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                CabangPelapor cabangPelapor = new CabangPelapor();

                // casting type data from file
                Cell statusData = cells.get(0);
                if (statusData != null) {
                    switch (statusData.getCellType()) {
                        case NUMERIC:
                            cabangPelapor.setStatusData(NumberToTextConverter.toText(statusData.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cabangPelapor.setStatusData(statusData.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kodeCabang = cells.get(1);
                if (kodeCabang != null) {
                    switch (kodeCabang.getCellType()) {
                        case NUMERIC:
                            cabangPelapor.setKodeCabang(NumberToTextConverter.toText(kodeCabang.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cabangPelapor.setKodeCabang(kodeCabang.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCabang = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Cabang can't be blank, Row: " + getRowKodeCabang);
                }

                Cell kodeCabangUtama = cells.get(2);
                if (kodeCabangUtama != null) {
                    switch (kodeCabangUtama.getCellType()) {
                        case NUMERIC:
                            cabangPelapor.setKodeCabangUtama(NumberToTextConverter.toText(kodeCabangUtama.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cabangPelapor.setKodeCabangUtama(kodeCabangUtama.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kodeCoa = cells.get(3);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            cabangPelapor.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cabangPelapor.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell namaCabang = cells.get(4);
                if (namaCabang != null) {
                    cabangPelapor.setNamaCabang(namaCabang.getStringCellValue());
                }

                Cell aliasCabang = cells.get(5);
                if (aliasCabang != null) {
                    cabangPelapor.setAliasCabang(aliasCabang.getStringCellValue());
                }

                Cell aliasWilayah = cells.get(6);
                if (aliasWilayah != null) {
                    cabangPelapor.setAliasWilayah(aliasWilayah.getStringCellValue());
                }

                Cell kodeCabBi = cells.get(7);
                if (kodeCabBi != null) {
                    switch (kodeCabBi.getCellType()) {
                        case NUMERIC:
                            cabangPelapor.setKodeCabBi(NumberToTextConverter.toText(kodeCabBi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cabangPelapor.setKodeCabBi(kodeCabBi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                cabangPelapors.add(cabangPelapor);
            }

            workbook.close();

            return cabangPelapors;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<KodeTransaksiLainnya> excelToKodeTransaksiLainnya(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<KodeTransaksiLainnya> kodeTransaksiLainnyas = new ArrayList<KodeTransaksiLainnya>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 1);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                KodeTransaksiLainnya kodeTransaksiLainnya = new KodeTransaksiLainnya();

                // casting type data from file
                Cell kodeTransaksi = cells.get(0);
                if (kodeTransaksi != null) {
                    switch (kodeTransaksi.getCellType()) {
                        case NUMERIC:
                            kodeTransaksiLainnya.setKodeTransaksi(NumberToTextConverter.toText(kodeTransaksi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kodeTransaksiLainnya.setKodeTransaksi(kodeTransaksi.getStringCellValue());
                        default:
                            break;
                    }
                } else {
                    int getRowKodeTransaksi = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Transaksi can't be blank, Row: " + getRowKodeTransaksi);
                }

                kodeTransaksiLainnyas.add(kodeTransaksiLainnya);
            }

            workbook.close();

            return kodeTransaksiLainnyas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<GolonganPemilikIndividu> excelToGolPemilikInd(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<GolonganPemilikIndividu> golonganPemilikIndividus = new ArrayList<GolonganPemilikIndividu>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 1);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                GolonganPemilikIndividu golonganPemilikIndividu = new GolonganPemilikIndividu();

                // casting type data from file
                Cell tipeGolPemilik = cells.get(0);
                if (tipeGolPemilik != null) {
                    switch (tipeGolPemilik.getCellType()) {
                        case NUMERIC:
                            golonganPemilikIndividu.setTipeGolPemilik(NumberToTextConverter.toText(tipeGolPemilik.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            golonganPemilikIndividu.setTipeGolPemilik(tipeGolPemilik.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowTipeGolPemilik = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Tipe Gol Pemilik can't be blank, Row: " + getRowTipeGolPemilik);
                }

                golonganPemilikIndividus.add(golonganPemilikIndividu);
            }

            workbook.close();

            return golonganPemilikIndividus;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<CifDikecualikan> excelToCifDikecualikan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<CifDikecualikan> cifDikecualikans = new ArrayList<CifDikecualikan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                CifDikecualikan cifDikecualikan = new CifDikecualikan();

                // casting type data from file
                Cell noCif = cells.get(0);
                if (noCif != null) {
                    switch (noCif.getCellType()) {
                        case NUMERIC:
                            cifDikecualikan.setNoCif(NumberToTextConverter.toText(noCif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifDikecualikan.setNoCif(noCif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoCif = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Cif can't be blank, Row: " + getRowNoCif);
                }

                Cell flag = cells.get(1);
                if (flag != null) {
                    switch (flag.getCellType()) {
                        case NUMERIC:
                            cifDikecualikan.setFlag(NumberToTextConverter.toText(flag.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifDikecualikan.setFlag(flag.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell keterangan = cells.get(2);
                if (keterangan != null) {
                    switch (keterangan.getCellType()) {
                        case NUMERIC:
                            cifDikecualikan.setKeterangan(NumberToTextConverter.toText(keterangan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifDikecualikan.setKeterangan(keterangan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell flagDnLn = cells.get(3);
                if (flagDnLn != null) {
                    switch (flagDnLn.getCellType()) {
                        case NUMERIC:
                            cifDikecualikan.setFlagDnLn(NumberToTextConverter.toText(flagDnLn.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifDikecualikan.setFlagDnLn(flagDnLn.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                cifDikecualikans.add(cifDikecualikan);

            }

            workbook.close();

            return cifDikecualikans;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<PihakTerkait> excelToPihakTerkait(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<PihakTerkait> pihakTerkaits = new ArrayList<PihakTerkait>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                PihakTerkait pihakTerkait = new PihakTerkait();

                // casting type data from file
                Cell sandiAntasena = cells.get(0);
                if (sandiAntasena != null) {
                    switch (sandiAntasena.getCellType()) {
                        case NUMERIC:
                            pihakTerkait.setSandiAntasena(NumberToTextConverter.toText(sandiAntasena.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakTerkait.setSandiAntasena(sandiAntasena.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowSandiAntasena = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Sandi Antasena can't be blank, Row: " + getRowSandiAntasena);
                }

                Cell deskprisiSandi = cells.get(1);
                if (deskprisiSandi != null) {
                    pihakTerkait.setDeskripsiSandi(deskprisiSandi.getStringCellValue());
                }


                Cell kodeGl = cells.get(2);
                if (kodeGl != null) {
                    switch (kodeGl.getCellType()) {
                        case NUMERIC:
                            pihakTerkait.setKodeGl(NumberToTextConverter.toText(kodeGl.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakTerkait.setKodeGl(kodeGl.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell deskripsiGl = cells.get(3);
                if (deskripsiGl != null) {
                    pihakTerkait.setDeskripsiGl(deskripsiGl.getStringCellValue());
                }

                Cell mataUang = cells.get(4);
                if (mataUang != null) {
                    pihakTerkait.setMataUang(mataUang.getStringCellValue());
                }

                pihakTerkaits.add(pihakTerkait);

            }

            workbook.close();

            return pihakTerkaits;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<HapusBuku> excelToHapusBuku(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<HapusBuku> hapusBukus = new ArrayList<HapusBuku>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                HapusBuku hapusBuku = new HapusBuku();

                // casting type data from file
                Cell sandiAntasena = cells.get(0);
                if (sandiAntasena != null) {
                    switch (sandiAntasena.getCellType()) {
                        case NUMERIC:
                            hapusBuku.setSandiAntasena(NumberToTextConverter.toText(sandiAntasena.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            hapusBuku.setSandiAntasena(sandiAntasena.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowSandiAntasena = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Sandi Antasena can't be blank, Row: " + getRowSandiAntasena);
                }

                Cell deskprisiSandi = cells.get(1);
                if (deskprisiSandi != null) {
                    hapusBuku.setDeskripsiSandi(deskprisiSandi.getStringCellValue());
                }


                Cell rupiah = cells.get(2);
                if (rupiah != null) {
                    switch (rupiah.getCellType()) {
                        case NUMERIC:
                            hapusBuku.setRupiah(NumberToTextConverter.toText(rupiah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            hapusBuku.setRupiah(rupiah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell valas = cells.get(3);
                if (valas != null) {
                    switch (valas.getCellType()) {
                        case NUMERIC:
                            hapusBuku.setValas(NumberToTextConverter.toText(valas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            hapusBuku.setValas(valas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                hapusBukus.add(hapusBuku);

            }

            workbook.close();

            return hapusBukus;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<LiabilitasAntarKantor> excelToLiabilitasAntarKantor(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<LiabilitasAntarKantor> liabilitasAntarKantors = new ArrayList<LiabilitasAntarKantor>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 9);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                LiabilitasAntarKantor liabilitasAntarKantor = new LiabilitasAntarKantor();

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            liabilitasAntarKantor.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            liabilitasAntarKantor.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode COA can't be blank, Row: " + getRowKodeCoa);
                }

                Cell deskripsiCoa = cells.get(1);
                if (deskripsiCoa != null) {
                    liabilitasAntarKantor.setDeskripsiCoa(deskripsiCoa.getStringCellValue());
                }

                Cell kegiatanUsahaKantor = cells.get(2);
                if (kegiatanUsahaKantor != null) {
                    liabilitasAntarKantor.setUsahaKantor(kegiatanUsahaKantor.getStringCellValue());
                }

                Cell statuskantor = cells.get(3);
                if (statuskantor != null) {
                    liabilitasAntarKantor.setStatusKantor(statuskantor.getStringCellValue());
                }

                Cell negaraKantor = cells.get(4);
                if (negaraKantor != null) {
                    liabilitasAntarKantor.setNegaraKantor(negaraKantor.getStringCellValue());
                }

                Cell jenisLiabilitas = cells.get(5);
                if (jenisLiabilitas != null) {
                    liabilitasAntarKantor.setJenisLiabilitas(jenisLiabilitas.getStringCellValue());
                }

                Cell pendBknPend = cells.get(6);
                if (pendBknPend != null) {
                    switch (pendBknPend.getCellType()) {
                        case NUMERIC:
                            liabilitasAntarKantor.setPendBknPend(NumberToTextConverter.toText(pendBknPend.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            liabilitasAntarKantor.setPendBknPend(pendBknPend.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell sukuBungaRp = cells.get(7);
                if (sukuBungaRp != null) {
                    switch (sukuBungaRp.getCellType()) {
                        case NUMERIC:
                            liabilitasAntarKantor.setSukuBungaRp(BigDecimal.valueOf(sukuBungaRp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell sukuBungaVl = cells.get(8);
                if (sukuBungaVl != null) {
                    switch (sukuBungaVl.getCellType()) {
                        case NUMERIC:
                            liabilitasAntarKantor.setSukuBungaVl(BigDecimal.valueOf(sukuBungaVl.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                liabilitasAntarKantors.add(liabilitasAntarKantor);
            }

            workbook.close();

            return liabilitasAntarKantors;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<LiabilitasLainnya> excelToLiabilitasLainnya(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<LiabilitasLainnya> liabilitasLainnyas = new ArrayList<LiabilitasLainnya>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 7);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                LiabilitasLainnya liabilitasLainnya = new LiabilitasLainnya();

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            liabilitasLainnya.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            liabilitasLainnya.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode COA can't be blank, Row: " + getRowKodeCoa);
                }

                Cell keterangan = cells.get(1);
                if (keterangan != null) {
                    liabilitasLainnya.setKeterangan(keterangan.getStringCellValue());
                }

                Cell jenisLiabilitas = cells.get(2);
                if (jenisLiabilitas != null) {
                    liabilitasLainnya.setJenisLiabilitas(jenisLiabilitas.getStringCellValue());
                }

                Cell golKreditur = cells.get(3);
                if (golKreditur != null) {
                    liabilitasLainnya.setGolonganKreditur(golKreditur.getStringCellValue());
                }

                Cell hubunganKreditur = cells.get(4);
                if (hubunganKreditur != null) {
                    liabilitasLainnya.setHubunganKreditur(hubunganKreditur.getStringCellValue());
                }

                Cell negaraKreditur = cells.get(5);
                if (negaraKreditur != null) {
                    liabilitasLainnya.setNegaraKreditur(negaraKreditur.getStringCellValue());
                }

                liabilitasLainnyas.add(liabilitasLainnya);

                Cell pendBknPend = cells.get(6);
                if (pendBknPend != null) {
                    switch (pendBknPend.getCellType()) {
                        case NUMERIC:
                            liabilitasLainnya.setPendBknPend(NumberToTextConverter.toText(pendBknPend.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            liabilitasLainnya.setPendBknPend(pendBknPend.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
            }

            workbook.close();

            return liabilitasLainnyas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Mdr> excelToMdr(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Mdr> mdrs = new ArrayList<Mdr>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Mdr mdr = new Mdr();

                // casting type data from file
                Cell kodeMerchant = cells.get(0);
                if (kodeMerchant != null) {
                    switch (kodeMerchant.getCellType()) {
                        case NUMERIC:
                            mdr.setKodeMerchant(NumberToTextConverter.toText(kodeMerchant.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            mdr.setKodeMerchant(kodeMerchant.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeMerchant = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Merchant can't be blank, Row: " + getRowKodeMerchant);
                }

                Cell rate = cells.get(1);
                if (rate != null) {
                    switch (rate.getCellType()) {
                        case NUMERIC:
                            mdr.setRate(BigDecimal.valueOf(rate.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                mdrs.add(mdr);
            }

            workbook.close();

            return mdrs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Cashback> excelToCashback(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Cashback> cashbacks = new ArrayList<Cashback>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 3);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Cashback cashback = new Cashback();

                // casting type data from file
                Cell noRek = cells.get(0);
                if (noRek != null) {
                    switch (noRek.getCellType()) {
                        case NUMERIC:
                            cashback.setNoRek(NumberToTextConverter.toText(noRek.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cashback.setNoRek(noRek.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoRek = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Rek can't be blank, Row: " + getRowNoRek);
                }

                Cell jnsSimpanan = cells.get(1);
                if (jnsSimpanan != null) {
                    switch (jnsSimpanan.getCellType()) {
                        case NUMERIC:
                            cashback.setJnsSimpanan(NumberToTextConverter.toText(jnsSimpanan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cashback.setJnsSimpanan(jnsSimpanan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell persentaseCashback = cells.get(2);
                if (persentaseCashback != null) {
                    switch (persentaseCashback.getCellType()) {
                        case NUMERIC:
                            cashback.setPersentaseCashback(BigDecimal.valueOf(persentaseCashback.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                cashbacks.add(cashback);
            }

            workbook.close();

            return cashbacks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<ReferensiAntasena> excelToReferensiAntasena(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<ReferensiAntasena> referensiAntasenas = new ArrayList<ReferensiAntasena>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                ReferensiAntasena referensiAntasena = new ReferensiAntasena();

                // casting type data from file
                Cell no = cells.get(0);
                if (no != null) {
                    referensiAntasena.setNo((long) no.getNumericCellValue());
                } else {
                    int getRowNo = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No can't be blank, Row: " + getRowNo);
                }

                Cell tabelReferensi = cells.get(1);
                if (tabelReferensi != null) {
                    referensiAntasena.setTabelReferensi(tabelReferensi.getStringCellValue());
                }

                Cell sandiReferensi = cells.get(2);
                if (sandiReferensi != null) {
                    switch (sandiReferensi.getCellType()) {
                        case NUMERIC:
                            referensiAntasena.setSandiReferensi(NumberToTextConverter.toText(sandiReferensi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            referensiAntasena.setSandiReferensi(sandiReferensi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell sandiExisting = cells.get(3);
                if (sandiExisting != null) {
                    switch (sandiExisting.getCellType()) {
                        case NUMERIC:
                            referensiAntasena.setSandiExisting(NumberToTextConverter.toText(sandiExisting.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            referensiAntasena.setSandiExisting(sandiExisting.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell label = cells.get(4);
                if (label != null) {
                    referensiAntasena.setLabel(label.getStringCellValue());
                }

                referensiAntasenas.add(referensiAntasena);
            }

            workbook.close();

            return referensiAntasenas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<RekeningSlik> excelToRekeningSlik(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<RekeningSlik> rekeningSliks = new ArrayList<RekeningSlik>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                RekeningSlik rekeningSlik = new RekeningSlik();

                // casting type data from file
                Cell noValid = cells.get(0);
                if (noValid != null) {
                    rekeningSlik.setNoValid(noValid.getStringCellValue());
                } else {
                    int getRowNoValid = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No. Valid can't be blank, Row: " + getRowNoValid);
                }

                Cell noSlik = cells.get(1);
                if (noSlik != null) {
                    rekeningSlik.setNoSlik(noSlik.getStringCellValue());
                }

                rekeningSliks.add(rekeningSlik);
            }

            workbook.close();

            return rekeningSliks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<SetoranJaminan> excelToSetoranJaminan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<SetoranJaminan> setoranJaminans = new ArrayList<SetoranJaminan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                SetoranJaminan setoranJaminan = new SetoranJaminan();

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            setoranJaminan.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            setoranJaminan.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode COA can't be blank, Row: " + getRowKodeCoa);
                }

                Cell deskripsiCoa = cells.get(1);
                if (deskripsiCoa != null) {
                    setoranJaminan.setDeskripsiCoa(deskripsiCoa.getStringCellValue());
                }

                Cell tujuanSetoranJaminan = cells.get(2);
                if (tujuanSetoranJaminan != null) {
                    switch (tujuanSetoranJaminan.getCellType()) {
                        case NUMERIC:
                            setoranJaminan.setTujuanSetoranJaminan(NumberToTextConverter.toText(tujuanSetoranJaminan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            setoranJaminan.setTujuanSetoranJaminan(tujuanSetoranJaminan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell golonganPemilik = cells.get(3);
                if (golonganPemilik != null) {
                    setoranJaminan.setGolonganPemilik(golonganPemilik.getStringCellValue());
                }


                Cell hubDgnPelapor = cells.get(4);
                if (hubDgnPelapor != null) {
                    setoranJaminan.setHubDgnPelapor(hubDgnPelapor.getStringCellValue());
                }

                Cell pendBknPend = cells.get(5);
                if (pendBknPend != null) {
                    switch (pendBknPend.getCellType()) {
                        case NUMERIC:
                            setoranJaminan.setPendBknPend(NumberToTextConverter.toText(pendBknPend.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            setoranJaminan.setPendBknPend(pendBknPend.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                setoranJaminans.add(setoranJaminan);
            }

            workbook.close();

            return setoranJaminans;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<MappingNcd> excelToMappingNcd(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<MappingNcd> mappingNcds = new ArrayList<MappingNcd>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                MappingNcd mappingNcd = new MappingNcd();

                // casting type data from file
                Cell noDeposito = cells.get(0);
                if (noDeposito != null) {
                    switch (noDeposito.getCellType()) {
                        case NUMERIC:
                            mappingNcd.setNoDeposito(NumberToTextConverter.toText(noDeposito.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            mappingNcd.setNoDeposito(noDeposito.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoDeposito = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Deposito can't be blank, Row: " + getRowNoDeposito);
                }

                Cell noCif = cells.get(1);
                if (noCif != null) {
                    switch (noCif.getCellType()) {
                        case NUMERIC:
                            mappingNcd.setNoCif(NumberToTextConverter.toText(noCif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            mappingNcd.setNoCif(noCif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                mappingNcds.add(mappingNcd);
            }

            workbook.close();

            return mappingNcds;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<KodeTransaksiKasValas> excelToKasValas(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<KodeTransaksiKasValas> kasValass = new ArrayList<KodeTransaksiKasValas>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                KodeTransaksiKasValas kasValas = new KodeTransaksiKasValas();

                // casting type data from file
                Cell kodeTransaksi = cells.get(0);
                if (kodeTransaksi != null) {
                    switch (kodeTransaksi.getCellType()) {
                        case NUMERIC:
                            kasValas.setKodeTransaksi(NumberToTextConverter.toText(kodeTransaksi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kasValas.setKodeTransaksi(kodeTransaksi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeTransaksi = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Transaksi can't be blank, Row: " + getRowKodeTransaksi);
                }

                Cell deskripsi = cells.get(1);
                if (deskripsi != null) {
                    kasValas.setDeskripsi(deskripsi.getStringCellValue());
                }

                Cell flagDebet = cells.get(2);
                if (flagDebet != null) {
                    switch (flagDebet.getCellType()) {
                        case NUMERIC:
                            kasValas.setFlagDebet(NumberToTextConverter.toText(flagDebet.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kasValas.setFlagDebet(flagDebet.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell flagKredit = cells.get(3);
                if (flagKredit != null) {
                    switch (flagKredit.getCellType()) {
                        case NUMERIC:
                            kasValas.setFlagKredit(NumberToTextConverter.toText(flagKredit.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kasValas.setFlagKredit(flagKredit.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                kasValass.add(kasValas);
            }

            workbook.close();

            return kasValass;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<KodeAgunan> excelToKodeAgunan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<KodeAgunan> kodeAgunans = new ArrayList<KodeAgunan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 21);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                KodeAgunan kodeAgunan = new KodeAgunan();

                // casting type data from file
                Cell no = cells.get(0);
                if (no != null) {
                    switch (no.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setNo(NumberToTextConverter.toText(no.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kodeAgunan.setNo(no.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kodeAgunanCore = cells.get(1);
                if (kodeAgunanCore != null) {
                    switch (kodeAgunanCore.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setKodeAgunanCore(NumberToTextConverter.toText(kodeAgunanCore.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kodeAgunan.setKodeAgunanCore(kodeAgunanCore.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeAgunanCore = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Agunan Core can't be blank, Row: " + getRowKodeAgunanCore);
                }

                Cell kodeAgunanAntasena = cells.get(2);
                if (kodeAgunanAntasena != null) {
                    kodeAgunan.setKodeAgunanAntasena(kodeAgunanAntasena.getStringCellValue());
                }


                Cell kodeAgunanLps = cells.get(3);
                if (kodeAgunanLps != null) {
                    kodeAgunan.setKodeAgunanLps(kodeAgunanLps.getStringCellValue());
                }


                Cell kodeAgunanSlik = cells.get(4);
                if (kodeAgunanSlik != null) {
                    switch (kodeAgunanSlik.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setKodeAgunanSlik(NumberToTextConverter.toText(kodeAgunanSlik.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kodeAgunan.setKodeAgunanSlik(kodeAgunanSlik.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell keterangan = cells.get(5);
                if (keterangan != null) {
                    kodeAgunan.setKeterangan(keterangan.getStringCellValue());
                }

                Cell nilaiPersentase = cells.get(6);
                if (nilaiPersentase != null) {
                    switch (nilaiPersentase.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setNilaiPersentase(BigDecimal.valueOf(nilaiPersentase.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw1 = cells.get(7);
                if (jw1 != null) {
                    switch (jw1.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw1(BigDecimal.valueOf(jw1.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen1 = cells.get(8);
                if (persen1 != null) {
                    switch (persen1.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen1(BigDecimal.valueOf(persen1.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw2 = cells.get(9);
                if (jw2 != null) {
                    switch (jw2.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw2(BigDecimal.valueOf(jw2.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen2 = cells.get(10);
                if (persen2 != null) {
                    switch (persen2.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen2(BigDecimal.valueOf(persen2.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw3 = cells.get(11);
                if (jw3 != null) {
                    switch (jw3.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw3(BigDecimal.valueOf(jw3.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen3 = cells.get(12);
                if (persen3 != null) {
                    switch (persen3.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen3(BigDecimal.valueOf(persen3.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw4 = cells.get(13);
                if (jw4 != null) {
                    switch (jw4.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw4(BigDecimal.valueOf(jw4.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen4 = cells.get(14);
                if (persen4 != null) {
                    switch (persen4.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen4(BigDecimal.valueOf(persen4.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw5 = cells.get(15);
                if (jw5 != null) {
                    switch (jw5.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw5(BigDecimal.valueOf(jw5.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen5 = cells.get(16);
                if (persen5 != null) {
                    switch (persen5.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen5(BigDecimal.valueOf(persen5.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw6 = cells.get(17);
                if (jw6 != null) {
                    switch (jw6.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw6(BigDecimal.valueOf(jw6.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen6 = cells.get(18);
                if (persen6 != null) {
                    switch (persen6.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen6(BigDecimal.valueOf(persen6.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jw7 = cells.get(19);
                if (jw7 != null) {
                    switch (jw7.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setJw7(BigDecimal.valueOf(jw7.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell persen7 = cells.get(20);
                if (persen7 != null) {
                    switch (persen7.getCellType()) {
                        case NUMERIC:
                            kodeAgunan.setPersen7(BigDecimal.valueOf(persen7.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                kodeAgunans.add(kodeAgunan);

            }

            workbook.close();

            return kodeAgunans;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }


    public static List<Ati> excelToAti(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Ati> asetTetapInventarises = new ArrayList<Ati>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 13);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Ati asetTetapInventaris = new Ati();

                Cell nomorAset = cells.get(0);
                if (nomorAset != null) {
                    switch (nomorAset.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setNomorAset(NumberToTextConverter.toText(nomorAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setNomorAset(nomorAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNomorAset = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Nomor Aset can't be blank, Row: " + getRowNomorAset);
                }

                Cell jenisAset = cells.get(1);
                if (jenisAset != null) {
                    asetTetapInventaris.setJenisAset(jenisAset.getStringCellValue());
                }

                Cell tglPerolehan = cells.get(2);
                if (tglPerolehan != null) {
                    switch (tglPerolehan.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setTglPerolehan(NumberToTextConverter.toText(tglPerolehan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setTglPerolehan(tglPerolehan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisValuta = cells.get(3);
                if (jenisValuta != null) {
                    asetTetapInventaris.setJenisValuta(jenisValuta.getStringCellValue());
                }

                Cell sumberPerolehan = cells.get(4);
                if (sumberPerolehan != null) {
                    asetTetapInventaris.setSumberPerolehan(sumberPerolehan.getStringCellValue());
                }

                Cell metodeUkur = cells.get(5);
                if (metodeUkur != null) {
                    switch (metodeUkur.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setMetodeUkur(NumberToTextConverter.toText(metodeUkur.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setMetodeUkur(metodeUkur.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell statusAset = cells.get(6);
                if (statusAset != null) {
                    switch (statusAset.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setStatusAset(NumberToTextConverter.toText(statusAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setStatusAset(statusAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlBlnLalu = cells.get(7);
                if (jmlBlnLalu != null) {
                    switch (jmlBlnLalu.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setJmlBlnLalu(NumberToTextConverter.toText(jmlBlnLalu.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setJmlBlnLalu(jmlBlnLalu.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlDebet = cells.get(8);
                if (jmlDebet != null) {
                    switch (jmlDebet.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setJmlDebet(NumberToTextConverter.toText(jmlDebet.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setJmlDebet(jmlDebet.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlKredit = cells.get(9);
                if (jmlKredit != null) {
                    switch (jmlKredit.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setJmlKredit(NumberToTextConverter.toText(jmlKredit.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setJmlKredit(jmlKredit.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlLainnya = cells.get(10);
                if (jmlLainnya != null) {
                    switch (jmlLainnya.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setJmlLainnya(NumberToTextConverter.toText(jmlLainnya.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setJmlLainnya(jmlLainnya.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlBlnLaporan = cells.get(11);
                if (jmlBlnLaporan != null) {
                    switch (jmlBlnLaporan.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setJmlBlnLaporan(NumberToTextConverter.toText(jmlBlnLaporan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setJmlBlnLaporan(jmlBlnLaporan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell akumulasiSusut = cells.get(12);
                if (akumulasiSusut != null) {
                    switch (akumulasiSusut.getCellType()) {
                        case NUMERIC:
                            asetTetapInventaris.setAkumulasiSusut(NumberToTextConverter.toText(akumulasiSusut.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            asetTetapInventaris.setAkumulasiSusut(akumulasiSusut.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                asetTetapInventarises.add(asetTetapInventaris);

            }

            workbook.close();

            return asetTetapInventarises;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Atb> excelToAtb(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Atb> atbs = new ArrayList<Atb>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 12);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Atb atb = new Atb();
                atb.setEmbedAtb(new EmbedAtb());

                Cell jenisAset = cells.get(0);
                if (jenisAset != null) {
                    atb.getEmbedAtb().setJenisAset(jenisAset.getStringCellValue());
                } else {
                    int getRowJenisAset = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Jenis Aset can't be blank, Row: " + getRowJenisAset);
                }


                Cell tglPerolehan = cells.get(1);
                if (tglPerolehan != null) {
                    switch (tglPerolehan.getCellType()) {
                        case NUMERIC:
                            atb.getEmbedAtb().setTglPerolehan(NumberToTextConverter.toText(tglPerolehan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.getEmbedAtb().setTglPerolehan(tglPerolehan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell tglMulai = cells.get(2);
                if (tglMulai != null) {
                    switch (tglMulai.getCellType()) {
                        case NUMERIC:
                            atb.getEmbedAtb().setTglMulai(NumberToTextConverter.toText(tglMulai.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.getEmbedAtb().setTglMulai(tglMulai.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jthTempo = cells.get(3);
                if (jthTempo != null) {
                    switch (jthTempo.getCellType()) {
                        case NUMERIC:
                            atb.setJthTempo(NumberToTextConverter.toText(jthTempo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setJthTempo(jthTempo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisValuta = cells.get(4);
                if (jenisValuta != null) {
                    atb.setJenisValuta(jenisValuta.getStringCellValue());
                }

                Cell metodeUkur = cells.get(5);
                if (metodeUkur != null) {
                    switch (metodeUkur.getCellType()) {
                        case NUMERIC:
                            atb.setMetodeUkur(NumberToTextConverter.toText(metodeUkur.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setMetodeUkur(metodeUkur.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlBlnLalu = cells.get(6);
                if (jmlBlnLalu != null) {
                    switch (jmlBlnLalu.getCellType()) {
                        case NUMERIC:
                            atb.setJmlBlnLalu(NumberToTextConverter.toText(jmlBlnLalu.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setJmlBlnLalu(jmlBlnLalu.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlDebet = cells.get(7);
                if (jmlDebet != null) {
                    switch (jmlDebet.getCellType()) {
                        case NUMERIC:
                            atb.setJmlDebet(NumberToTextConverter.toText(jmlDebet.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setJmlDebet(jmlDebet.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlKredit = cells.get(8);
                if (jmlKredit != null) {
                    switch (jmlKredit.getCellType()) {
                        case NUMERIC:
                            atb.setJmlKredit(NumberToTextConverter.toText(jmlKredit.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setJmlKredit(jmlKredit.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlLainnya = cells.get(9);
                if (jmlLainnya != null) {
                    switch (jmlLainnya.getCellType()) {
                        case NUMERIC:
                            atb.setJmlLainnya(NumberToTextConverter.toText(jmlLainnya.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setJmlLainnya(jmlLainnya.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jmlBlnLaporan = cells.get(10);
                if (jmlBlnLaporan != null) {
                    switch (jmlBlnLaporan.getCellType()) {
                        case NUMERIC:
                            atb.setJmlBlnLaporan(NumberToTextConverter.toText(jmlBlnLaporan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setJmlBlnLaporan(jmlBlnLaporan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell akumulasiAmortisasi = cells.get(11);
                if (akumulasiAmortisasi != null) {
                    switch (akumulasiAmortisasi.getCellType()) {
                        case NUMERIC:
                            atb.setAkumulasiAmortasi(NumberToTextConverter.toText(akumulasiAmortisasi.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            atb.setAkumulasiAmortasi(akumulasiAmortisasi.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                atbs.add(atb);

            }

            workbook.close();

            return atbs;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Kurs> excelToKurs(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Kurs> kurss = new ArrayList<Kurs>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Kurs kurs = new Kurs();
                kurs.setEmbedKurs(new EmbedKurs());

                // casting type data from file
                Cell mataUang = cells.get(0);
                if (mataUang != null) {
                    kurs.getEmbedKurs().setMataUang(mataUang.getStringCellValue());
                } else {
                    break;
//                    int getRowMataUang = row.getRowNum() + 1;
//                    throw new PrimaryKeyNullException("Column Mata Uang can't be blank, Row: " + getRowMataUang);
                }

                Cell tanggal = cells.get(1);
                if (tanggal != null) {
                    switch (tanggal.getCellType()) {
                        case NUMERIC:
                            kurs.getEmbedKurs().setTanggal(NumberToTextConverter.toText(tanggal.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kurs.getEmbedKurs().setTanggal(tanggal.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowTanggal = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Tanggal can't be blank, Row: " + getRowTanggal);
                }

                Cell kursJual = cells.get(2);
                if (kursJual != null) {
                    switch (kursJual.getCellType()) {
                        case NUMERIC:
                            kurs.setKursJual(BigDecimal.valueOf(kursJual.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell kursBeli = cells.get(3);
                if (kursBeli != null) {
                    switch (kursBeli.getCellType()) {
                        case NUMERIC:
                            kurs.setKursBeli(BigDecimal.valueOf(kursBeli.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jenis = cells.get(4);
                if (jenis != null) {
                    kurs.getEmbedKurs().setJenis(jenis.getStringCellValue());
                }

                kurss.add(kurs);
            }

            workbook.close();

            return kurss;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<CounterRateSbk> excelToRateSbk(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<CounterRateSbk> counterRateSbks = new ArrayList<CounterRateSbk>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                CounterRateSbk counterRateSbk = new CounterRateSbk();
                counterRateSbk.setEmbedRateSbk(new EmbedRateSbk());

                // casting type data from file
                Cell jenisPenggunaan = cells.get(0);
                if (jenisPenggunaan != null) {
                    switch (jenisPenggunaan.getCellType()) {
                        case NUMERIC:
                            counterRateSbk.getEmbedRateSbk().setJenisPenggunaan(NumberToTextConverter.toText(jenisPenggunaan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            counterRateSbk.getEmbedRateSbk().setJenisPenggunaan(jenisPenggunaan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowJenisPenggunaan = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Jenis Penggunaan can't be blank, Row: " + getRowJenisPenggunaan);
                }

                Cell jenisValuta = cells.get(1);
                if (jenisValuta != null) {
                    counterRateSbk.getEmbedRateSbk().setJenisValuta(jenisValuta.getStringCellValue());
                }

                Cell rateFlat = cells.get(2);
                if (rateFlat != null) {
                    switch (rateFlat.getCellType()) {
                        case NUMERIC:
                            counterRateSbk.setRateFlat(BigDecimal.valueOf(rateFlat.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell rateEfektif = cells.get(3);
                if (rateEfektif != null) {
                    switch (rateEfektif.getCellType()) {
                        case NUMERIC:
                            counterRateSbk.setRateEfektif(BigDecimal.valueOf(rateEfektif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                counterRateSbks.add(counterRateSbk);
            }

            workbook.close();

            return counterRateSbks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<CounterRateSbs> excelToRateSbs(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<CounterRateSbs> counterRateSbss = new ArrayList<CounterRateSbs>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                CounterRateSbs counterRateSbs = new CounterRateSbs();
                counterRateSbs.setEmbedRateSbs(new EmbedRateSbs());

                // casting type data from file
                Cell jenisInstrumen = cells.get(0);
                if (jenisInstrumen != null) {
                    switch (jenisInstrumen.getCellType()) {
                        case NUMERIC:
                            counterRateSbs.getEmbedRateSbs().setJenisInstrumen(NumberToTextConverter.toText(jenisInstrumen.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            counterRateSbs.getEmbedRateSbs().setJenisInstrumen(jenisInstrumen.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowJenisInstrumen = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Jenis Instrumen can't be blank, Row: " + getRowJenisInstrumen);
                }

                Cell jenisValuta = cells.get(1);
                if (jenisValuta != null) {
                    counterRateSbs.getEmbedRateSbs().setJenisValuta(jenisValuta.getStringCellValue());
                }

                Cell jangkaWaktu = cells.get(2);
                if (jangkaWaktu != null) {
                    counterRateSbs.getEmbedRateSbs().setJangkaWaktu(jangkaWaktu.getStringCellValue());
                }

                Cell rateMin = cells.get(3);
                if (rateMin != null) {
                    switch (rateMin.getCellType()) {
                        case NUMERIC:
                            counterRateSbs.setRateMin(BigDecimal.valueOf(rateMin.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell rateMax = cells.get(4);
                if (rateMax != null) {
                    switch (rateMax.getCellType()) {
                        case NUMERIC:
                            counterRateSbs.setRateMax(BigDecimal.valueOf(rateMax.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                counterRateSbss.add(counterRateSbs);
            }

            workbook.close();

            return counterRateSbss;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Pbi> excelToPbi(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Pbi> pbis = new ArrayList<Pbi>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 16);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Pbi pbi = new Pbi();
                pbi.setEmbedPbi(new EmbedPbi());

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            pbi.getEmbedPbi().setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.getEmbedPbi().setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Coa can't be blank, Row: " + getRowKodeCoa);
                }

                Cell deskripsiCoa = cells.get(1);
                if (deskripsiCoa != null) {
                    pbi.setDeskripsiCoa(deskripsiCoa.getStringCellValue());
                }

                Cell dalamLuarNegeri = cells.get(2);
                if (dalamLuarNegeri != null) {
                    pbi.setDalamLuarNegeri(dalamLuarNegeri.getStringCellValue());
                }

                Cell jenis = cells.get(3);
                if (jenis != null) {
                    pbi.setJenis(jenis.getStringCellValue());
                }

                Cell sandiJenis = cells.get(4);
                if (sandiJenis != null) {
                    pbi.setSandiJenis(sandiJenis.getStringCellValue());
                }

                Cell branch = cells.get(5);
                if (branch != null) {
                    switch (branch.getCellType()) {
                        case NUMERIC:
                            pbi.setBranch(NumberToTextConverter.toText(branch.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setBranch(branch.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell wilayah = cells.get(6);
                if (wilayah != null) {
                    switch (wilayah.getCellType()) {
                        case NUMERIC:
                            pbi.setWilayah(NumberToTextConverter.toText(wilayah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setWilayah(wilayah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell mataUang = cells.get(7);
                if (mataUang != null) {
                    pbi.getEmbedPbi().setMataUang(mataUang.getStringCellValue());
                }

                Cell hubDgnBank = cells.get(8);
                if (hubDgnBank != null) {
                    pbi.setHubDgnBank(hubDgnBank.getStringCellValue());
                }

                Cell sandiBank = cells.get(9);
                if (sandiBank != null) {
                    switch (sandiBank.getCellType()) {
                        case NUMERIC:
                            pbi.setSandiBank(NumberToTextConverter.toText(sandiBank.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setSandiBank(sandiBank.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell noRekening = cells.get(10);
                if (noRekening != null) {
                    switch (noRekening.getCellType()) {
                        case NUMERIC:
                            pbi.setNoRekening(NumberToTextConverter.toText(noRekening.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setNoRekening(noRekening.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell mulai = cells.get(11);
                if (mulai != null) {
                    switch (mulai.getCellType()) {
                        case NUMERIC:
                            pbi.getEmbedPbi().setMulai(NumberToTextConverter.toText(mulai.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.getEmbedPbi().setMulai(mulai.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jatuhTempo = cells.get(12);
                if (jatuhTempo != null) {
                    switch (jatuhTempo.getCellType()) {
                        case NUMERIC:
                            pbi.setJatuhTempo(NumberToTextConverter.toText(jatuhTempo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setJatuhTempo(jatuhTempo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell klasifikasiAset = cells.get(13);
                if (klasifikasiAset != null) {
                    switch (klasifikasiAset.getCellType()) {
                        case NUMERIC:
                            pbi.setKlasifikasiAset(NumberToTextConverter.toText(klasifikasiAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setKlasifikasiAset(klasifikasiAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kualitasAset = cells.get(14);
                if (kualitasAset != null) {
                    switch (kualitasAset.getCellType()) {
                        case NUMERIC:
                            pbi.setKualitasAset(NumberToTextConverter.toText(kualitasAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbi.setKualitasAset(kualitasAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell sukuBunga = cells.get(15);
                if (sukuBunga != null) {
                    switch (sukuBunga.getCellType()) {
                        case NUMERIC:
                            pbi.setSukuBunga(BigDecimal.valueOf(sukuBunga.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                pbis.add(pbi);
            }

            workbook.close();

            return pbis;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Pbl> excelToPbl(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Pbl> pbls = new ArrayList<Pbl>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 19);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Pbl pbl = new Pbl();
                pbl.setEmbedPbl(new EmbedPbl());

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            pbl.getEmbedPbl().setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.getEmbedPbl().setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeCoa = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Coa can't be blank, Row: " + getRowKodeCoa);
                }

                Cell deskripsiCoa = cells.get(1);
                if (deskripsiCoa != null) {
                    pbl.setDeskripsiCoa(deskripsiCoa.getStringCellValue());
                }

                Cell dalamLuarNegeri = cells.get(2);
                if (dalamLuarNegeri != null) {
                    pbl.setDalamLuarNegeri(dalamLuarNegeri.getStringCellValue());
                }

                Cell jenis = cells.get(3);
                if (jenis != null) {
                    pbl.setJenis(jenis.getStringCellValue());
                }

                Cell sandiJenis = cells.get(4);
                if (sandiJenis != null) {
                    pbl.setSandiJenis(sandiJenis.getStringCellValue());
                }

                Cell branch = cells.get(5);
                if (branch != null) {
                    switch (branch.getCellType()) {
                        case NUMERIC:
                            pbl.setBranch(NumberToTextConverter.toText(branch.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setBranch(branch.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell wilayah = cells.get(6);
                if (wilayah != null) {
                    switch (wilayah.getCellType()) {
                        case NUMERIC:
                            pbl.setWilayah(NumberToTextConverter.toText(wilayah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setWilayah(wilayah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell mataUang = cells.get(7);
                if (mataUang != null) {
                    pbl.getEmbedPbl().setMataUang(mataUang.getStringCellValue());
                }

                Cell hubDgnBank = cells.get(8);
                if (hubDgnBank != null) {
                    pbl.setHubDgnBank(hubDgnBank.getStringCellValue());
                }

                Cell sandiBank = cells.get(9);
                if (sandiBank != null) {
                    switch (sandiBank.getCellType()) {
                        case NUMERIC:
                            pbl.setSandiBank(NumberToTextConverter.toText(sandiBank.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setSandiBank(sandiBank.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell noRekening = cells.get(10);
                if (noRekening != null) {
                    switch (noRekening.getCellType()) {
                        case NUMERIC:
                            pbl.setNoRekening(NumberToTextConverter.toText(noRekening.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setNoRekening(noRekening.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell mulai = cells.get(11);
                if (mulai != null) {
                    switch (mulai.getCellType()) {
                        case NUMERIC:
                            pbl.getEmbedPbl().setMulai(NumberToTextConverter.toText(mulai.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.getEmbedPbl().setMulai(mulai.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jatuhTempo = cells.get(12);
                if (jatuhTempo != null) {
                    switch (jatuhTempo.getCellType()) {
                        case NUMERIC:
                            pbl.setJatuhTempo(NumberToTextConverter.toText(jatuhTempo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setJatuhTempo(jatuhTempo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell klasifikasiAset = cells.get(13);
                if (klasifikasiAset != null) {
                    switch (klasifikasiAset.getCellType()) {
                        case NUMERIC:
                            pbl.setKlasifikasiAset(NumberToTextConverter.toText(klasifikasiAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setKlasifikasiAset(klasifikasiAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kualitasAset = cells.get(14);
                if (kualitasAset != null) {
                    switch (kualitasAset.getCellType()) {
                        case NUMERIC:
                            pbl.setKualitasAset(NumberToTextConverter.toText(kualitasAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setKualitasAset(kualitasAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell pd = cells.get(15);
                if (pd != null) {
                    switch (pd.getCellType()) {
                        case NUMERIC:
                            pbl.setPd(BigDecimal.valueOf(pd.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell lgd = cells.get(16);
                if (lgd != null) {
                    switch (lgd.getCellType()) {
                        case NUMERIC:
                            pbl.setLgd(BigDecimal.valueOf(lgd.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell sukuBunga = cells.get(17);
                if (sukuBunga != null) {
                    switch (sukuBunga.getCellType()) {
                        case NUMERIC:
                            pbl.setSukuBunga(BigDecimal.valueOf(sukuBunga.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell jnsSukuBunga = cells.get(18);
                if (jnsSukuBunga != null) {
                    switch (jnsSukuBunga.getCellType()) {
                        case NUMERIC:
                            pbl.setJnsSukuBunga(NumberToTextConverter.toText(jnsSukuBunga.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pbl.setJnsSukuBunga(jnsSukuBunga.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                pbls.add(pbl);
            }

            workbook.close();

            return pbls;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<SlikAgunanLama> excelToSlikAgunanLama(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<SlikAgunanLama> slikAgunanLamas = new ArrayList<SlikAgunanLama>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                SlikAgunanLama slikAgunanLama = new SlikAgunanLama();
                slikAgunanLama.setEmbedSlikAgunanLama(new EmbedSlikAgunanLama());

                // casting type data from file
                Cell noSlikLama = cells.get(0);
                if (noSlikLama != null) {
                    switch (noSlikLama.getCellType()) {
                        case NUMERIC:
                            slikAgunanLama.getEmbedSlikAgunanLama().setNoSlikLama(NumberToTextConverter.toText(noSlikLama.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            slikAgunanLama.getEmbedSlikAgunanLama().setNoSlikLama(noSlikLama.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoSlikLama = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Slik Lama can't be blank, Row: " + getRowNoSlikLama);
                }

                Cell noAgunanLama = cells.get(1);
                if (noAgunanLama != null) {
                    switch (noAgunanLama.getCellType()) {
                        case NUMERIC:
                            slikAgunanLama.getEmbedSlikAgunanLama().setNoAgunanLama(NumberToTextConverter.toText(noAgunanLama.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            slikAgunanLama.getEmbedSlikAgunanLama().setNoAgunanLama(noAgunanLama.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                slikAgunanLamas.add(slikAgunanLama);
            }

            workbook.close();

            return slikAgunanLamas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<SlikAgunanBaru> excelToSlikAgunanBaru(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<SlikAgunanBaru> slikAgunanBarus = new ArrayList<SlikAgunanBaru>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                SlikAgunanBaru slikAgunanBaru = new SlikAgunanBaru();
                slikAgunanBaru.setEmbedSlikAgunanBaru(new EmbedSlikAgunanBaru());

                // casting type data from file
                Cell noValid = cells.get(0);
                if (noValid != null) {
                    switch (noValid.getCellType()) {
                        case NUMERIC:
                            slikAgunanBaru.getEmbedSlikAgunanBaru().setNoValid(NumberToTextConverter.toText(noValid.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            slikAgunanBaru.getEmbedSlikAgunanBaru().setNoValid(noValid.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoValid = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Valid Lama can't be blank, Row: " + getRowNoValid);
                }

                Cell noAgunanBaru = cells.get(1);
                if (noAgunanBaru != null) {
                    switch (noAgunanBaru.getCellType()) {
                        case NUMERIC:
                            slikAgunanBaru.getEmbedSlikAgunanBaru().setNoAgunanBaru(NumberToTextConverter.toText(noAgunanBaru.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            slikAgunanBaru.getEmbedSlikAgunanBaru().setNoAgunanBaru(noAgunanBaru.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                slikAgunanBarus.add(slikAgunanBaru);
            }

            workbook.close();

            return slikAgunanBarus;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<NoAgunan> excelToNoAgunan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<NoAgunan> noAgunans = new ArrayList<NoAgunan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                NoAgunan noAgunan = new NoAgunan();
                noAgunan.setEmbedNoAgunan(new EmbedNoAgunan());

                // casting type data from file
                Cell noAgunanBaru = cells.get(0);
                if (noAgunanBaru != null) {
                    switch (noAgunanBaru.getCellType()) {
                        case NUMERIC:
                            noAgunan.getEmbedNoAgunan().setNoAgunanBaru(NumberToTextConverter.toText(noAgunanBaru.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            noAgunan.getEmbedNoAgunan().setNoAgunanBaru(noAgunanBaru.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoAgunanBaru = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Agunan Baru can't be blank, Row: " + getRowNoAgunanBaru);
                }

                Cell noAgunanLama = cells.get(1);
                if (noAgunanLama != null) {
                    switch (noAgunanLama.getCellType()) {
                        case NUMERIC:
                            noAgunan.getEmbedNoAgunan().setNoAgunanLama(NumberToTextConverter.toText(noAgunanLama.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            noAgunan.getEmbedNoAgunan().setNoAgunanLama(noAgunanLama.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                noAgunans.add(noAgunan);
            }

            workbook.close();

            return noAgunans;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Lps> excelToBungaLps(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Lps> lpss = new ArrayList<Lps>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Lps lps = new Lps();
                lps.setEmbedLps(new EmbedLps());

                // casting type data from file
                Cell tglMulai = cells.get(0);
                if (tglMulai != null) {
                    switch (tglMulai.getCellType()) {
                        case NUMERIC:
                            lps.getEmbedLps().setTglMulai(NumberToTextConverter.toText(tglMulai.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            lps.getEmbedLps().setTglMulai(tglMulai.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowTglMulai = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Tanggal Mulai can't be blank, Row: " + getRowTglMulai);
                }

                Cell tglJt = cells.get(1);
                if (tglJt != null) {
                    switch (tglJt.getCellType()) {
                        case NUMERIC:
                            lps.setTglJt(NumberToTextConverter.toText(tglJt.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            lps.setTglJt(tglJt.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisValuta = cells.get(2);
                if (jenisValuta != null) {
                    switch (jenisValuta.getCellType()) {
                        case NUMERIC:
                            lps.getEmbedLps().setJenisValuta(NumberToTextConverter.toText(jenisValuta.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            lps.getEmbedLps().setJenisValuta(jenisValuta.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowJenisValuta = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Jenis Valuta can't be blank, Row: " + getRowJenisValuta);
                }

                Cell sukuBunga = cells.get(3);
                if (sukuBunga != null) {
                    switch (sukuBunga.getCellType()) {
                        case NUMERIC:
                            lps.setSukuBunga(BigDecimal.valueOf(sukuBunga.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                lpss.add(lps);
            }

            workbook.close();

            return lpss;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Ppa> excelToPpa(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Ppa> ppas = new ArrayList<Ppa>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 16);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Ppa ppa = new Ppa();
                ppa.setEmbedPpa(new EmbedPpa());

                // casting type data from file
                Cell kodeCoa = cells.get(0);
                if (kodeCoa != null) {
                    switch (kodeCoa.getCellType()) {
                        case NUMERIC:
                            ppa.setKodeCoa(NumberToTextConverter.toText(kodeCoa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setKodeCoa(kodeCoa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell deskripsiCoa = cells.get(1);
                if (deskripsiCoa != null) {
                    ppa.setDeskripsiCoa(deskripsiCoa.getStringCellValue());
                }

                Cell jenis = cells.get(2);
                if (jenis != null) {
                    switch (jenis.getCellType()) {
                        case NUMERIC:
                            ppa.setJenis(NumberToTextConverter.toText(jenis.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setJenis(jenis.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowJenis = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Jenis can't be blank, Row: " + getRowJenis);
                }

                Cell sandiJenis = cells.get(3);
                if (sandiJenis != null) {
                    ppa.getEmbedPpa().setSandiJenis(sandiJenis.getStringCellValue());
                }

                Cell cabang = cells.get(4);
                if (cabang != null) {
                    switch (cabang.getCellType()) {
                        case NUMERIC:
                            ppa.getEmbedPpa().setCabang(NumberToTextConverter.toText(cabang.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.getEmbedPpa().setCabang(cabang.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell wilayah = cells.get(5);
                if (wilayah != null) {
                    switch (wilayah.getCellType()) {
                        case NUMERIC:
                            ppa.getEmbedPpa().setWilayah(NumberToTextConverter.toText(wilayah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.getEmbedPpa().setWilayah(wilayah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell mataUang = cells.get(6);
                if (mataUang != null) {
                    ppa.getEmbedPpa().setMataUang(mataUang.getStringCellValue());
                }

                Cell tglMulai = cells.get(7);
                if (tglMulai != null) {
                    switch (tglMulai.getCellType()) {
                        case NUMERIC:
                            ppa.getEmbedPpa().setTglMulai(NumberToTextConverter.toText(tglMulai.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.getEmbedPpa().setTglMulai(tglMulai.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowTglMulai = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Tanggal Mulai can't be blank, Row: " + getRowTglMulai);
                }

                Cell tglJthTempo = cells.get(8);
                if (tglJthTempo != null) {
                    switch (tglJthTempo.getCellType()) {
                        case NUMERIC:
                            ppa.setTglJthTempo(NumberToTextConverter.toText(tglJthTempo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setTglJthTempo(tglJthTempo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell metodeUkur = cells.get(9);
                if (metodeUkur != null) {
                    switch (metodeUkur.getCellType()) {
                        case NUMERIC:
                            ppa.setMetodeUkur(NumberToTextConverter.toText(metodeUkur.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setMetodeUkur(metodeUkur.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell statusAset = cells.get(10);
                if (statusAset != null) {
                    switch (statusAset.getCellType()) {
                        case NUMERIC:
                            ppa.setStatusAset(NumberToTextConverter.toText(statusAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setStatusAset(statusAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kualitasAset = cells.get(11);
                if (kualitasAset != null) {
                    switch (kualitasAset.getCellType()) {
                        case NUMERIC:
                            ppa.setKualitasAset(NumberToTextConverter.toText(kualitasAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setKualitasAset(kualitasAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell nomorAset = cells.get(12);
                if (nomorAset != null) {
                    switch (nomorAset.getCellType()) {
                        case NUMERIC:
                            ppa.setNomorAset(NumberToTextConverter.toText(nomorAset.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            ppa.setNomorAset(nomorAset.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell hargaPerolehan = cells.get(13);
                if (hargaPerolehan != null) {
                    switch (hargaPerolehan.getCellType()) {
                        case NUMERIC:
                            ppa.setHargaPerolehan(BigDecimal.valueOf(hargaPerolehan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell akumulasiSusut = cells.get(14);
                if (akumulasiSusut != null) {
                    switch (akumulasiSusut.getCellType()) {
                        case NUMERIC:
                            ppa.setAkumulasiSusut(BigDecimal.valueOf(akumulasiSusut.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                Cell nilaiBuku = cells.get(15);
                if (nilaiBuku != null) {
                    switch (nilaiBuku.getCellType()) {
                        case NUMERIC:
                            ppa.setNilaiBuku(BigDecimal.valueOf(nilaiBuku.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        default:
                            break;
                    }
                }

                ppas.add(ppa);
            }

            workbook.close();

            return ppas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Pts> excelToPts(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Pts> ptss = new ArrayList<Pts>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Pts pts = new Pts();

                // casting type data from file
                Cell sandiAntasena = cells.get(0);
                if (sandiAntasena != null) {
                    switch (sandiAntasena.getCellType()) {
                        case NUMERIC:
                            pts.setSandiAntasena(NumberToTextConverter.toText(sandiAntasena.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pts.setSandiAntasena(sandiAntasena.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowSandiAntasena = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Sandi Antasena can't be blank, Row: " + getRowSandiAntasena);
                }

                Cell deskripsiSandi = cells.get(1);
                if (deskripsiSandi != null) {
                    pts.setDeskripsiSandi(deskripsiSandi.getStringCellValue());
                }

                Cell kodeGl = cells.get(2);
                if (kodeGl != null) {
                    switch (kodeGl.getCellType()) {
                        case NUMERIC:
                            pts.setKodeGl(NumberToTextConverter.toText(kodeGl.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pts.setKodeGl(kodeGl.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell deskripsiGl = cells.get(3);
                if (deskripsiGl != null) {
                    pts.setDeskripsiGl(deskripsiGl.getStringCellValue());
                }

                Cell mataUang = cells.get(4);
                if (mataUang != null) {
                    pts.setMataUang(mataUang.getStringCellValue());
                }

                ptss.add(pts);
            }

            workbook.close();

            return ptss;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<PengecualianKodeAgunan> excelToPengecualianKodeAgunan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<PengecualianKodeAgunan> pengecualianKodeAgunans = new ArrayList<PengecualianKodeAgunan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 1);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                PengecualianKodeAgunan pengecualianKodeAgunan = new PengecualianKodeAgunan();

                // casting type data from file
                Cell kodeAgunanCore = cells.get(0);
                if (kodeAgunanCore != null) {
                    switch (kodeAgunanCore.getCellType()) {
                        case NUMERIC:
                            pengecualianKodeAgunan.setKodeAgunanCore(NumberToTextConverter.toText(kodeAgunanCore.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pengecualianKodeAgunan.setKodeAgunanCore(kodeAgunanCore.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKodeAgunanCore = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Agunan Core can't be blank, Row: " + getRowKodeAgunanCore);
                }

                pengecualianKodeAgunans.add(pengecualianKodeAgunan);
            }

            workbook.close();

            return pengecualianKodeAgunans;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<GolPihakLawan> excelToGolPihakLawan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<GolPihakLawan> golPihakLawans = new ArrayList<GolPihakLawan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                GolPihakLawan golPihakLawan = new GolPihakLawan();

                // casting type data from file
                Cell golonganPihakLawan = cells.get(0);
                if (golonganPihakLawan != null) {
                    golPihakLawan.setGolonganPihakLawan(golonganPihakLawan.getStringCellValue());
                } else {
                    int getRowGolonganPihakLawan = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Golongan Pihak Lawan can't be blank, Row: " + getRowGolonganPihakLawan);
                }

                Cell golDebiturKreditur = cells.get(1);
                if (golDebiturKreditur != null) {
                    golPihakLawan.setGolDebiturKreditur(golDebiturKreditur.getStringCellValue());
                }

                golPihakLawans.add(golPihakLawan);
            }

            workbook.close();

            return golPihakLawans;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Coa> excelToCoa(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Coa> coas = new ArrayList<Coa>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Coa coa = new Coa();
                coa.setEmbedCoa(new EmbedCoa());

                // casting type data from file
                Cell coaInduk = cells.get(0);
                if (coaInduk != null) {
                    switch (coaInduk.getCellType()) {
                        case NUMERIC:
                            coa.getEmbedCoa().setCoaInduk(NumberToTextConverter.toText(coaInduk.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coa.getEmbedCoa().setCoaInduk(coaInduk.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowCoaInduk = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Coa Induk can't be blank, Row: " + getRowCoaInduk);
                }

                Cell coaDetail = cells.get(1);
                if (coaDetail != null) {
                    switch (coaDetail.getCellType()) {
                        case NUMERIC:
                            coa.getEmbedCoa().setCoaDetail(NumberToTextConverter.toText(coaDetail.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coa.getEmbedCoa().setCoaDetail(coaDetail.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                coas.add(coa);
            }

            workbook.close();

            return coas;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<PihakLawan> excelToPihakLawan(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<PihakLawan> pihakLawans = new ArrayList<PihakLawan>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 18);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                PihakLawan pihakLawan = new PihakLawan();
                pihakLawan.setEmbedPihakLawan(new EmbedPihakLawan());

                // casting type data from file
                Cell kdCabang = cells.get(0);
                if (kdCabang != null) {
                    switch (kdCabang.getCellType()) {
                        case NUMERIC:
                            pihakLawan.getEmbedPihakLawan().setKdCabang(NumberToTextConverter.toText(kdCabang.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.getEmbedPihakLawan().setKdCabang(kdCabang.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowKdCabang = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kd Cabang can't be blank, Row: " + getRowKdCabang);
                }

                Cell idPihakLawan = cells.get(1);
                if (idPihakLawan != null) {
                    switch (idPihakLawan.getCellType()) {
                        case NUMERIC:
                            pihakLawan.getEmbedPihakLawan().setIdPihakLawan(NumberToTextConverter.toText(idPihakLawan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.getEmbedPihakLawan().setIdPihakLawan(idPihakLawan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowIdPihakLawan = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Id Pihak Lawan can't be blank, Row: " + getRowIdPihakLawan);
                }

                Cell jenisIdentitas = cells.get(2);
                if (jenisIdentitas != null) {
                    pihakLawan.setJenisIdentitas(jenisIdentitas.getStringCellValue());
                }

                Cell nomorId = cells.get(3);
                if (nomorId != null) {
                    switch (nomorId.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setNomorId(NumberToTextConverter.toText(nomorId.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setNomorId(nomorId.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisKelamin = cells.get(4);
                if (jenisKelamin != null) {
                    pihakLawan.setJenisKelamin(jenisKelamin.getStringCellValue());
                }

                Cell namaLengkap = cells.get(5);
                if (namaLengkap != null) {
                    pihakLawan.setNamaLengkap(namaLengkap.getStringCellValue());
                }

                Cell npwp = cells.get(6);
                if (npwp != null) {
                    switch (npwp.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setNpwp(NumberToTextConverter.toText(npwp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setNpwp(npwp.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kodeWn = cells.get(7);
                if (kodeWn != null) {
                    switch (kodeWn.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setKodeWn(NumberToTextConverter.toText(kodeWn.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setKodeWn(kodeWn.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kodeNegara = cells.get(8);
                if (kodeNegara != null) {
                    switch (kodeNegara.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setKodeNegara(NumberToTextConverter.toText(kodeNegara.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setKodeNegara(kodeNegara.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell jenisKegUsaha = cells.get(9);
                if (jenisKegUsaha != null) {
                    switch (jenisKegUsaha.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setJenisKegUsaha(NumberToTextConverter.toText(jenisKegUsaha.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setJenisKegUsaha(jenisKegUsaha.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell hubBank = cells.get(10);
                if (hubBank != null) {
                    switch (hubBank.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setHubBank(NumberToTextConverter.toText(hubBank.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setHubBank(hubBank.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell golDeb = cells.get(11);
                if (golDeb != null) {
                    switch (golDeb.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setGolDeb(NumberToTextConverter.toText(golDeb.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setGolDeb(golDeb.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kdLembaga = cells.get(12);
                if (kdLembaga != null) {
                    switch (kdLembaga.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setKdLembaga(NumberToTextConverter.toText(kdLembaga.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setKdLembaga(kdLembaga.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kdRating = cells.get(13);
                if (kdRating != null) {
                    switch (kdRating.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setKdRating(NumberToTextConverter.toText(kdRating.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setKdRating(kodeNegara.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell tglRating = cells.get(14);
                if (tglRating != null) {
                    // call convert
                    String data = convertDateString(tglRating);

                    switch (tglRating.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setTglRating(Date.valueOf(data));
                            break;
                        default:
                            break;
                    }
                }

                Cell tahun = cells.get(15);
                if (tahun != null) {
                    switch (tahun.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setTahun(NumberToTextConverter.toText(tahun.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setTahun(tahun.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell kota = cells.get(16);
                if (kota != null) {
                    switch (kota.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setKota(NumberToTextConverter.toText(kota.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setKota(kota.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell idGroup = cells.get(17);
                if (idGroup != null) {
                    switch (idGroup.getCellType()) {
                        case NUMERIC:
                            pihakLawan.setIdGroup(NumberToTextConverter.toText(idGroup.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pihakLawan.setIdGroup(idGroup.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                pihakLawans.add(pihakLawan);
            }

            workbook.close();

            return pihakLawans;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Pemegangkuasa> excelToPemegangkuasa(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Pemegangkuasa> pemegangkuasas = new ArrayList<Pemegangkuasa>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 3);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Pemegangkuasa pemegangkuasa = new Pemegangkuasa();

                // casting type data from file
                Cell noCif = cells.get(0);
                if (noCif != null) {
                    switch (noCif.getCellType()) {
                        case NUMERIC:
                            pemegangkuasa.setNoCif(NumberToTextConverter.toText(noCif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pemegangkuasa.setNoCif(noCif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoCif = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column NO CIF can't be blank, Row: " + getRowNoCif);
                }

                Cell jenisId = cells.get(1);
                if (jenisId != null) {
                    switch (jenisId.getCellType()) {
                        case NUMERIC:
                            pemegangkuasa.setJenisId(NumberToTextConverter.toText(jenisId.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pemegangkuasa.setJenisId(jenisId.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell nomorId = cells.get(2);
                if (nomorId != null) {
                    switch (nomorId.getCellType()) {
                        case NUMERIC:
                            pemegangkuasa.setNomorId(NumberToTextConverter.toText(nomorId.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            pemegangkuasa.setNomorId(nomorId.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                pemegangkuasas.add(pemegangkuasa);
            }

            workbook.close();

            return pemegangkuasas;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Fraudnasabah> excelToFraudnasabah(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Fraudnasabah> fraudnasabahs = new ArrayList<Fraudnasabah>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 1);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Fraudnasabah fraudnasabah = new Fraudnasabah();

                // casting type data from file
                Cell noCif = cells.get(0);
                if (noCif != null) {
                    switch (noCif.getCellType()) {
                        case NUMERIC:
                            fraudnasabah.setNoCif(NumberToTextConverter.toText(noCif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            fraudnasabah.setNoCif(noCif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowNoCif = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column NO CIF can't be blank, Row: " + getRowNoCif);
                }

                fraudnasabahs.add(fraudnasabah);
            }

            workbook.close();

            return fraudnasabahs;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Nasabahjoin> excelToNasabahjoin(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Nasabahjoin> nasabahjoins = new ArrayList<Nasabahjoin>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 24);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Nasabahjoin nasabahjoin = new Nasabahjoin();

                // mendapatkan value flagNasabah & tipeCif parameter method valiadationUpload()
                Cell valFlagNasabah = cells.get(2);
                String strValFlagNasabah = null;
                String strValtipeCif = null;


                if (valFlagNasabah.getCellType() == CellType.NUMERIC) {
                    strValFlagNasabah = NumberToTextConverter.toText(valFlagNasabah.getNumericCellValue());
                } else {
                    strValFlagNasabah = valFlagNasabah.getStringCellValue();
                }

                Cell valtipeCif = cells.get(4);
                if (valtipeCif == null) {
                    ;
                } else {
                    if (valtipeCif.getCellType() == CellType.NUMERIC) {
                        strValtipeCif = NumberToTextConverter.toText((valtipeCif.getNumericCellValue()));
                    } else {
                        strValtipeCif = valtipeCif.getStringCellValue();
                    }
                }

                // casting type data from file
                Cell cif = cells.get(0);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col0 = "CIF";
                int getRowcol0 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col0, cif, getRowcol0)) {
                    if (cif == null) {
                        ;
                    } else {
                        switch (cif.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setCif(NumberToTextConverter.toText(cif.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setCif(cif.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }

                }

                // casting type data from file
                Cell cifJoin = cells.get(1);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col1 = "CIF_JOIN";
                int getRowcol1 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col1, cifJoin, getRowcol1)) {
                    if (cifJoin == null) {
                        ;
                    } else {
                        switch (cifJoin.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setCifJoin(NumberToTextConverter.toText(cifJoin.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setCifJoin(cifJoin.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell flagNasabah = cells.get(2);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col2 = "FLAG_NASABAH";
                int getRowcol2 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col2, flagNasabah, getRowcol2)) {
                    if (flagNasabah == null) {
                        ;
                    } else {
                        switch (flagNasabah.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setFlagNasabah(NumberToTextConverter.toText(flagNasabah.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setFlagNasabah(flagNasabah.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell flagJoin = cells.get(3);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col3 = "FLAG_JOIN";
                int getRowcol3 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col3, flagJoin, getRowcol3)) {
                    if (flagJoin == null) {
                        ;
                    } else {
                        switch (flagJoin.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setFlagJoin(NumberToTextConverter.toText(flagJoin.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setFlagJoin(flagJoin.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell tipeCif = cells.get(4);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col4 = "TIPE_CIF";
                int getRowcol4 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col4, tipeCif, getRowcol4)) {
                    if (tipeCif == null) {
                        ;
                    } else {
                        switch (tipeCif.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setTipeCif(NumberToTextConverter.toText(tipeCif.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setTipeCif(tipeCif.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell nmLnkpNsb = cells.get(5);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col5 = "NM_LNKP_NSB";
                int getRowcol5 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col5, nmLnkpNsb, getRowcol5)) {
                    if (nmLnkpNsb == null) {
                        ;
                    } else {
                        switch (nmLnkpNsb.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNmLnkpNsb(NumberToTextConverter.toText(nmLnkpNsb.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNmLnkpNsb(nmLnkpNsb.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell noNpwp = cells.get(6);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col6 = "NO_NPWP";
                int getRowcol6 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col6, noNpwp, getRowcol6)) {
                    if (noNpwp == null) {
                        ;
                    } else {
                        switch (noNpwp.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNoNpwp(NumberToTextConverter.toText(noNpwp.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNoNpwp(noNpwp.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell jnsIdentitas = cells.get(7);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col7 = "JNS_IDENTITAS";
                int getRowcol7 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col7, jnsIdentitas, getRowcol7)) {
                    if (jnsIdentitas == null) {
                        ;
                    } else {
                        switch (jnsIdentitas.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setJnsIdentitas(NumberToTextConverter.toText(jnsIdentitas.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setJnsIdentitas(jnsIdentitas.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell noIdentitas = cells.get(8);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col8 = "NO_IDENTITAS";
                int getRowcol8 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col8, noIdentitas, getRowcol8)) {
                    if (noIdentitas == null) {
                        ;
                    } else {
                        switch (noIdentitas.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNoIdentitas(NumberToTextConverter.toText(noIdentitas.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNoIdentitas(noIdentitas.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell nmIbuKdg = cells.get(9);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col9 = "NM_IBU_KDG";
                int getRowcol9 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col9, nmIbuKdg, getRowcol9)) {
                    if (nmIbuKdg == null) {
                        ;
                    } else {
                        switch (nmIbuKdg.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNmIbuKdg(NumberToTextConverter.toText(nmIbuKdg.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNmIbuKdg(nmIbuKdg.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell tmpLahir = cells.get(10);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col10 = "TMP_LAHIR";
                int getRowcol10 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col10, tmpLahir, getRowcol10)) {
                    if (tmpLahir == null) {
                        ;
                    } else {
                        switch (tmpLahir.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setTmpLahir(NumberToTextConverter.toText(tmpLahir.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setTmpLahir(tmpLahir.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell tglLahir = cells.get(11);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col11 = "TGL_LAHIR";
                int getRowcol11 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col11, tglLahir, getRowcol11)) {
                    if (tglLahir == null) {
                        continue;
                    } else {
                        // call convert
                        String data = convertDateString(tglLahir);
                        switch (tglLahir.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setTglLahir(Date.valueOf(data));
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell noSiup = cells.get(12);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col12 = "NO_SIUP";
                int getRowcol12 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col12, noSiup, getRowcol12)) {
                    if (noSiup == null) {
                        ;
                    } else {
                        switch (noSiup.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNoSiup(NumberToTextConverter.toText(noSiup.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNoSiup(noSiup.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell nmLnkpPemegangKuasa = cells.get(13);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col13 = "NM_LNKP_PEMEGANG_KUASA";
                int getRowcol13 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col13, nmLnkpPemegangKuasa, getRowcol13)) {
                    if (nmLnkpPemegangKuasa == null) {
                        ;
                    } else {
                        switch (nmLnkpPemegangKuasa.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNmLnkpPemegangKuasa(NumberToTextConverter.toText(nmLnkpPemegangKuasa.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNmLnkpPemegangKuasa(nmLnkpPemegangKuasa.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell jnsIdentitasPemegangKuasa = cells.get(14);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col14 = "JNS_IDENTITAS_PEMEGANG_KUASA";
                int getRowcol14 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col14, jnsIdentitasPemegangKuasa, getRowcol14)) {
                    if (jnsIdentitasPemegangKuasa == null) {
                        ;
                    } else {
                        switch (jnsIdentitasPemegangKuasa.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setJnsIdentitasPemegangKuasa(NumberToTextConverter.toText(jnsIdentitasPemegangKuasa.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setJnsIdentitasPemegangKuasa(jnsIdentitasPemegangKuasa.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell noIdentitasPemegangKuasa = cells.get(15);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col15 = "NO_IDENTITAS_PEMEGANG_KUASA";
                int getRowcol15 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col15, noIdentitasPemegangKuasa, getRowcol15)) {
                    if (noIdentitasPemegangKuasa == null) {
                        ;
                    } else {
                        switch (noIdentitasPemegangKuasa.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNoIdentitasPemegangKuasa(NumberToTextConverter.toText(noIdentitasPemegangKuasa.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNoIdentitasPemegangKuasa(noIdentitasPemegangKuasa.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell alamat = cells.get(16);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col16 = "ALAMAT";
                int getRowcol16 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col16, alamat, getRowcol16)) {
                    if (alamat == null) {
                        ;
                    } else {
                        switch (alamat.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setAlamat(NumberToTextConverter.toText(alamat.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setAlamat(alamat.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell kabKota = cells.get(17);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col17 = "KAB_KOTA";
                int getRowcol17 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col17, kabKota, getRowcol17)) {
                    if (kabKota == null) {
                        ;
                    } else {
                        switch (kabKota.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setKabKota(NumberToTextConverter.toText(kabKota.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setKabKota(kabKota.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell kewarnegaraan = cells.get(18);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col18 = "KEWARGANEGARAAN";
                int getRowcol18 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col18, kewarnegaraan, getRowcol18)) {
                    if (kewarnegaraan == null) {
                        ;
                    } else {
                        switch (kewarnegaraan.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setKewarnegaraan(NumberToTextConverter.toText(kewarnegaraan.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setKewarnegaraan(kewarnegaraan.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell noTelp = cells.get(19);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col19 = "NO_TELP";
                int getRowcol19 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col19, noTelp, getRowcol19)) {
                    if (noTelp == null) {
                        ;
                    } else {
                        switch (noTelp.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setNoTelp(NumberToTextConverter.toText(noTelp.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setNoTelp(noTelp.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell flagFraud = cells.get(20);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col20 = "FLAG_FRAUD";
                int getRowcol20 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col20, flagFraud, getRowcol20)) {
                    if (flagFraud == null) {
                        ;
                    } else {
                        switch (flagFraud.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setFlagFraud(NumberToTextConverter.toText(flagFraud.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setFlagFraud(flagFraud.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell hubDgnBank = cells.get(21);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col21 = "HUB_DGN_BANK";
                int getRowcol21 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col21, hubDgnBank, getRowcol21)) {
                    if (hubDgnBank == null) {
                        ;
                    } else {
                        switch (hubDgnBank.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setHubDgnBank(NumberToTextConverter.toText(hubDgnBank.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setHubDgnBank(hubDgnBank.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell golNsb = cells.get(22);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col22 = "GOL_NSB";
                int getRowcol22 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col22, golNsb, getRowcol22)) {
                    if (golNsb == null) {
                        ;
                    } else {
                        switch (golNsb.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setGolNsb(NumberToTextConverter.toText(golNsb.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setGolNsb(golNsb.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                // casting type data from file
                Cell kategoriUsaha = cells.get(23);

                // keperluan untuk pengisian parameter method valiadationUpload()
                String col23 = "KATEGORI_USAHA";
                int getRowcol23 = row.getRowNum();

                if (validationUpload(strValFlagNasabah, strValtipeCif, col23, kategoriUsaha, getRowcol23)) {
                    if (kategoriUsaha == null) {
                        ;
                    } else {
                        switch (kategoriUsaha.getCellType()) {
                            case NUMERIC:
                                nasabahjoin.setKategoriUsaha(NumberToTextConverter.toText(kategoriUsaha.getNumericCellValue()));
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                nasabahjoin.setKategoriUsaha(kategoriUsaha.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                nasabahjoins.add(nasabahjoin);
            }

            workbook.close();

            return nasabahjoins;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public List<Nasabahjoin> excelToNasabahjoinCopy(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Nasabahjoin> nasabahjoins = new ArrayList<Nasabahjoin>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 24);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Nasabahjoin nasabahjoin = new Nasabahjoin();


                // casting type data from file
                Cell cif = cells.get(0);
                if (cif != null) {
                    switch (cif.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setCif(NumberToTextConverter.toText(cif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setCif(cif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell cifJoin = cells.get(1);
                if (cifJoin != null) {
                    switch (cifJoin.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setCifJoin(NumberToTextConverter.toText(cifJoin.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setCifJoin(cifJoin.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }


                // casting type data from file
                Cell flagNasabah = cells.get(2);
                if (flagNasabah != null) {
                    switch (flagNasabah.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setFlagNasabah(NumberToTextConverter.toText(flagNasabah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setFlagNasabah(flagNasabah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell flagJoin = cells.get(3);

                if (flagJoin != null) {
                    switch (flagJoin.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setFlagJoin(NumberToTextConverter.toText(flagJoin.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setFlagJoin(flagJoin.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell tipeCif = cells.get(4);
                if (tipeCif != null) {
                    switch (tipeCif.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setTipeCif(NumberToTextConverter.toText(tipeCif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setTipeCif(tipeCif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell nmLnkpNsb = cells.get(5);
                if (nmLnkpNsb != null) {
                    switch (nmLnkpNsb.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNmLnkpNsb(NumberToTextConverter.toText(nmLnkpNsb.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNmLnkpNsb(nmLnkpNsb.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noNpwp = cells.get(6);
                if (noNpwp != null) {
                    switch (noNpwp.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNoNpwp(NumberToTextConverter.toText(noNpwp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNoNpwp(noNpwp.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell jnsIdentitas = cells.get(7);
                if (jnsIdentitas != null) {
                    switch (jnsIdentitas.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setJnsIdentitas(NumberToTextConverter.toText(jnsIdentitas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setJnsIdentitas(jnsIdentitas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noIdentitas = cells.get(8);
                if (noIdentitas != null) {
                    switch (noIdentitas.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNoIdentitas(NumberToTextConverter.toText(noIdentitas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNoIdentitas(noIdentitas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell nmIbuKdg = cells.get(9);
                if (nmIbuKdg != null) {
                    switch (nmIbuKdg.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNmIbuKdg(NumberToTextConverter.toText(nmIbuKdg.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNmIbuKdg(nmIbuKdg.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell tmpLahir = cells.get(10);
                if (tmpLahir != null) {
                    switch (tmpLahir.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setTmpLahir(NumberToTextConverter.toText(tmpLahir.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setTmpLahir(tmpLahir.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell tglLahir = cells.get(11);
                if (tglLahir != null) {
                    String data = convertDateString(tglLahir);

                    switch (tglLahir.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setTglLahir(Date.valueOf(data));
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noSiup = cells.get(12);
                if (noSiup != null) {
                    switch (noSiup.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNoSiup(NumberToTextConverter.toText(noSiup.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNoSiup(noSiup.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell nmLnkpPemegangKuasa = cells.get(13);
                if (nmLnkpPemegangKuasa != null) {
                    switch (nmLnkpPemegangKuasa.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNmLnkpPemegangKuasa(NumberToTextConverter.toText(nmLnkpPemegangKuasa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNmLnkpPemegangKuasa(nmLnkpPemegangKuasa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell jnsIdentitasPemegangKuasa = cells.get(14);
                if (jnsIdentitasPemegangKuasa != null) {
                    switch (jnsIdentitasPemegangKuasa.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setJnsIdentitasPemegangKuasa(NumberToTextConverter.toText(jnsIdentitasPemegangKuasa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setJnsIdentitasPemegangKuasa(jnsIdentitasPemegangKuasa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noIdentitasPemegangKuasa = cells.get(15);
                if (noIdentitasPemegangKuasa != null) {
                    switch (noIdentitasPemegangKuasa.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNoIdentitasPemegangKuasa(NumberToTextConverter.toText(noIdentitasPemegangKuasa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNoIdentitasPemegangKuasa(noIdentitasPemegangKuasa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }


                // casting type data from file
                Cell alamat = cells.get(16);
                if (alamat != null) {
                    switch (alamat.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setAlamat(NumberToTextConverter.toText(alamat.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setAlamat(alamat.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell kabKota = cells.get(17);
                if (kabKota != null) {
                    switch (kabKota.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setKabKota(NumberToTextConverter.toText(kabKota.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setKabKota(kabKota.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell kewarnegaraan = cells.get(18);
                if (kewarnegaraan != null) {
                    switch (kewarnegaraan.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setKewarnegaraan(NumberToTextConverter.toText(kewarnegaraan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setKewarnegaraan(kewarnegaraan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noTelp = cells.get(19);
                if (noTelp != null) {
                    switch (noTelp.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setNoTelp(NumberToTextConverter.toText(noTelp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setNoTelp(noTelp.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell flagFraud = cells.get(20);
                if (flagFraud != null) {
                    switch (flagFraud.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setFlagFraud(NumberToTextConverter.toText(flagFraud.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setFlagFraud(flagFraud.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell hubDgnBank = cells.get(21);
                if (hubDgnBank != null) {
                    switch (hubDgnBank.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setHubDgnBank(NumberToTextConverter.toText(hubDgnBank.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setHubDgnBank(hubDgnBank.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell golNsb = cells.get(22);
                if (golNsb != null) {
                    switch (golNsb.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setGolNsb(NumberToTextConverter.toText(golNsb.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setGolNsb(golNsb.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell kategoriUsaha = cells.get(23);
                if (kategoriUsaha != null) {
                    switch (kategoriUsaha.getCellType()) {
                        case NUMERIC:
                            nasabahjoin.setKategoriUsaha(NumberToTextConverter.toText(kategoriUsaha.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            nasabahjoin.setKategoriUsaha(kategoriUsaha.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                nasabahjoins.add(nasabahjoin);
            }
            workbook.close();

            validationImportLogRepository.truncateTable();
            if (validationAneh(nasabahjoins)) {
                return nasabahjoins;
            } else {
                throw new ErrorUploadException("Please see the log for more details");
            }


        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Cifjoinqq> excelToCifjoinqq(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Cifjoinqq> cifjoinqqs = new ArrayList<Cifjoinqq>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Cifjoinqq cifjoinqq = new Cifjoinqq();

                // casting type data from file
                Cell cifQq = cells.get(0);
                if (cifQq != null) {
                    switch (cifQq.getCellType()) {
                        case NUMERIC:
                            cifjoinqq.setCifQq(NumberToTextConverter.toText(cifQq.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoinqq.setCifQq(cifQq.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowCifQq = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Cif QQ can't be blank, Row: " + getRowCifQq);
                }

                Cell namaLengkap = cells.get(1);
                if (namaLengkap != null) {
                    switch (namaLengkap.getCellType()) {
                        case NUMERIC:
                            cifjoinqq.setNamaLengkap(NumberToTextConverter.toText(namaLengkap.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoinqq.setNamaLengkap(namaLengkap.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                cifjoinqqs.add(cifjoinqq);
            }

            workbook.close();

            return cifjoinqqs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public List<MasterCif> excelToMasterCif(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<MasterCif> masterCifs = new ArrayList<MasterCif>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 21);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                MasterCif masterCif = new MasterCif();

                // casting type data from file
                Cell cif = cells.get(0);
                if (cif != null) {
                    switch (cif.getCellType()) {
                        case NUMERIC:
                            masterCif.setCif(NumberToTextConverter.toText(cif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setCif(cif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowCif = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Cif can't be blank, Row: " + getRowCif);
                }

                // casting type data from file
                Cell nmLnkpNsb = cells.get(1);
                if (nmLnkpNsb != null) {
                    switch (nmLnkpNsb.getCellType()) {
                        case NUMERIC:
                            masterCif.setNmLnkpNsb(NumberToTextConverter.toText(nmLnkpNsb.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNmLnkpNsb(nmLnkpNsb.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell tipeNasabah = cells.get(2);
                if (tipeNasabah != null) {
                    switch (tipeNasabah.getCellType()) {
                        case NUMERIC:
                            masterCif.setTipeNasabah(NumberToTextConverter.toText(tipeNasabah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setTipeNasabah(tipeNasabah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noNpwp = cells.get(3);
                if (noNpwp != null) {
                    switch (noNpwp.getCellType()) {
                        case NUMERIC:
                            masterCif.setNoNpwp(NumberToTextConverter.toText(noNpwp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNoNpwp(noNpwp.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell jnsIdentitas = cells.get(4);
                if (jnsIdentitas != null) {
                    switch (jnsIdentitas.getCellType()) {
                        case NUMERIC:
                            masterCif.setJnsIdentitas(NumberToTextConverter.toText(jnsIdentitas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setJnsIdentitas(jnsIdentitas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noIdentitas = cells.get(5);
                if (noIdentitas != null) {
                    switch (noIdentitas.getCellType()) {
                        case NUMERIC:
                            masterCif.setNoIdentitas(NumberToTextConverter.toText(noIdentitas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNoIdentitas(noIdentitas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell nmIbuKdg = cells.get(6);
                if (nmIbuKdg != null) {
                    switch (nmIbuKdg.getCellType()) {
                        case NUMERIC:
                            masterCif.setNmIbuKdg(NumberToTextConverter.toText(nmIbuKdg.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNmIbuKdg(nmIbuKdg.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell tmpLahir = cells.get(7);
                if (tmpLahir != null) {
                    switch (tmpLahir.getCellType()) {
                        case NUMERIC:
                            masterCif.setTmpLahir(NumberToTextConverter.toText(tmpLahir.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setTmpLahir(tmpLahir.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell tglLahir = cells.get(8);
                if (tglLahir != null) {

                    String data = convertDateString(tglLahir);
                    System.out.println(data);

                    switch (tglLahir.getCellType()) {
                        case NUMERIC:
                            masterCif.setTglLahir(Date.valueOf(data));
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noSiup = cells.get(9);
                if (noSiup != null) {
                    switch (noSiup.getCellType()) {
                        case NUMERIC:
                            masterCif.setNoSiup(NumberToTextConverter.toText(noSiup.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNoSiup(noSiup.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell nmLnkpPemegangKuasa = cells.get(10);
                if (nmLnkpPemegangKuasa != null) {
                    switch (nmLnkpPemegangKuasa.getCellType()) {
                        case NUMERIC:
                            masterCif.setNmLnkpPemegangKuasa(NumberToTextConverter.toText(nmLnkpPemegangKuasa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNmLnkpPemegangKuasa(nmLnkpPemegangKuasa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell jnsIdentitasPemegangKuasa = cells.get(11);
                if (jnsIdentitasPemegangKuasa != null) {
                    switch (jnsIdentitasPemegangKuasa.getCellType()) {
                        case NUMERIC:
                            masterCif.setJnsIdentitasPemegangKuasa(NumberToTextConverter.toText(jnsIdentitasPemegangKuasa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setJnsIdentitasPemegangKuasa(jnsIdentitasPemegangKuasa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noIdentitasPemegangKuasa = cells.get(12);
                if (noIdentitasPemegangKuasa != null) {
                    switch (noIdentitasPemegangKuasa.getCellType()) {
                        case NUMERIC:
                            masterCif.setNoIdentitasPemegangKuasa(NumberToTextConverter.toText(noIdentitasPemegangKuasa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNoIdentitasPemegangKuasa(noIdentitasPemegangKuasa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }


                // casting type data from file
                Cell alamat = cells.get(13);
                if (alamat != null) {
                    switch (alamat.getCellType()) {
                        case NUMERIC:
                            masterCif.setAlamat(NumberToTextConverter.toText(alamat.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setAlamat(alamat.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell kabKota = cells.get(14);
                if (kabKota != null) {
                    switch (kabKota.getCellType()) {
                        case NUMERIC:
                            masterCif.setKabKota(NumberToTextConverter.toText(kabKota.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setKabKota(kabKota.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell kewarnegaraan = cells.get(15);
                if (kewarnegaraan != null) {
                    switch (kewarnegaraan.getCellType()) {
                        case NUMERIC:
                            masterCif.setKewarnegaraan(NumberToTextConverter.toText(kewarnegaraan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setKewarnegaraan(kewarnegaraan.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell noTelp = cells.get(16);
                if (noTelp != null) {
                    switch (noTelp.getCellType()) {
                        case NUMERIC:
                            masterCif.setNoTelp(NumberToTextConverter.toText(noTelp.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setNoTelp(noTelp.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell flagFraud = cells.get(17);
                if (flagFraud != null) {
                    switch (flagFraud.getCellType()) {
                        case NUMERIC:
                            masterCif.setFlagFraud(NumberToTextConverter.toText(flagFraud.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setFlagFraud(flagFraud.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell hubDgnBank = cells.get(18);
                if (hubDgnBank != null) {
                    switch (hubDgnBank.getCellType()) {
                        case NUMERIC:
                            masterCif.setHubDgnBank(NumberToTextConverter.toText(hubDgnBank.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setHubDgnBank(hubDgnBank.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell golNsb = cells.get(19);
                if (golNsb != null) {
                    switch (golNsb.getCellType()) {
                        case NUMERIC:
                            masterCif.setGolNsb(NumberToTextConverter.toText(golNsb.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setGolNsb(golNsb.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                // casting type data from file
                Cell kategoriUsaha = cells.get(20);
                if (kategoriUsaha != null) {
                    switch (kategoriUsaha.getCellType()) {
                        case NUMERIC:
                            masterCif.setKategoriUsaha(NumberToTextConverter.toText(kategoriUsaha.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            masterCif.setKategoriUsaha(kategoriUsaha.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                masterCifs.add(masterCif);
            }

            workbook.close();

            validationImportLogRepository.truncateTable();
            if (validationAnehCopy(masterCifs)) {
                return masterCifs;
            } else {
                throw new ErrorUploadException("Please see the log for more details");
            }
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException(err.getMessage());
        }
    }

    public static List<Cifjoin> excelToCifjoin(InputStream is) {
        try {

            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Cifjoin> cifjoins = new ArrayList<Cifjoin>();

            // skip header
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Cifjoin cifjoin = new Cifjoin();
                cifjoin.setEmbedCifjoin(new EmbedCifjoin());

                // casting type data from file
                Cell flagNasabah = cells.get(0);
                if (flagNasabah != null) {
                    switch (flagNasabah.getCellType()) {
                        case NUMERIC:
                            cifjoin.setFlagNasabah(NumberToTextConverter.toText(flagNasabah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoin.setFlagNasabah(flagNasabah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell tipeNasabah = cells.get(1);
                if (tipeNasabah != null) {
                    switch (tipeNasabah.getCellType()) {
                        case NUMERIC:
                            cifjoin.setTipeNasabah(NumberToTextConverter.toText(tipeNasabah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoin.setTipeNasabah(tipeNasabah.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell noIdentitas = cells.get(2);
                if (noIdentitas != null) {
                    switch (noIdentitas.getCellType()) {
                        case NUMERIC:
                            cifjoin.setNoIdentitas(NumberToTextConverter.toText(noIdentitas.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoin.setNoIdentitas(noIdentitas.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell cif = cells.get(3);
                if (cif != null) {
                    switch (cif.getCellType()) {
                        case NUMERIC:
                            cifjoin.getEmbedCifjoin().setCif(NumberToTextConverter.toText(cif.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoin.getEmbedCifjoin().setCif(cif.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowCif = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Cif can't be blank, Row: " + getRowCif);
                }

                Cell cifJoin = cells.get(4);
                if (cifJoin != null) {
                    switch (cifJoin.getCellType()) {
                        case NUMERIC:
                            cifjoin.getEmbedCifjoin().setCifJoin(NumberToTextConverter.toText(cifJoin.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoin.getEmbedCifjoin().setCifJoin(cifJoin.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    int getRowCifJoin = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Cif Join can't be blank, Row: " + getRowCifJoin);
                }

                Cell tipeJoin = cells.get(5);
                if (tipeJoin != null) {
                    switch (tipeJoin.getCellType()) {
                        case NUMERIC:
                            cifjoin.setTipeJoin(NumberToTextConverter.toText(tipeJoin.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            cifjoin.setTipeJoin(tipeJoin.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                cifjoins.add(cifjoin);
            }

            workbook.close();

            return cifjoins;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<SummaryAdjustment> excelToSummaryAdjustment(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<SummaryAdjustment> summaryAdjustments = new ArrayList<SummaryAdjustment>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                SummaryAdjustment summaryAdjustment = new SummaryAdjustment();

                // casting type data from file
                Cell adjustmentNo = cells.get(0);
                if (adjustmentNo != null) {
                    switch (adjustmentNo.getCellType()) {
                        case NUMERIC:
                            summaryAdjustment.setAdjustmentNo(NumberToTextConverter.toText(adjustmentNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            summaryAdjustment.setAdjustmentNo(adjustmentNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }  else {
                    throw new PrimaryKeyNullException("Column Adjustment No can't be blank, Row: " + rowCount);
                }
                
                // casting type data from file
                Cell coa = cells.get(1);
                if (coa != null) {
                    switch (coa.getCellType()) {
                        case NUMERIC:
                            summaryAdjustment.setCoa(NumberToTextConverter.toText(coa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            summaryAdjustment.setCoa(coa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new PrimaryKeyNullException("Column COA can't be blank, Row: " + rowCount);
                }

                Cell description = cells.get(2);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            summaryAdjustment.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            summaryAdjustment.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell debitCredit = cells.get(3);
                if (debitCredit != null) {
                    switch (debitCredit.getCellType()) {
                        case NUMERIC:
                            summaryAdjustment.setDebitCredit(new BigDecimal(debitCredit.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            summaryAdjustment.setDebitCredit(new BigDecimal(debitCredit.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }

                Cell note = cells.get(4);
                if (note != null) {
                    switch (note.getCellType()) {
                        case NUMERIC:
                            summaryAdjustment.setNote(NumberToTextConverter.toText(note.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            summaryAdjustment.setNote(note.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                Cell flag = cells.get(5);
                if (flag != null) {
                    switch (flag.getCellType()) {
                        case NUMERIC:
                            summaryAdjustment.setFlag(NumberToTextConverter.toText(flag.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            summaryAdjustment.setFlag(flag.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                summaryAdjustments.add(summaryAdjustment);
                rowCount++;
            }

            workbook.close();

            return summaryAdjustments;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<AdjustmentThb> excelToAdjustmentThb(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<AdjustmentThb> adjustmentThbs = new ArrayList<AdjustmentThb>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                AdjustmentThb adjustmentThb = new AdjustmentThb();
                
                // casting type data from file
                Cell adjustmentNo = cells.get(0);
                if (adjustmentNo != null) {
                    switch (adjustmentNo.getCellType()) {
                        case NUMERIC:
                            adjustmentThb.setAdjustmentNo(NumberToTextConverter.toText(adjustmentNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            adjustmentThb.setAdjustmentNo(adjustmentNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }  else {
                    throw new PrimaryKeyNullException("Column Adjustment No can't be blank, Row: " + rowCount);
                }
                
                // casting type data from file
                Cell coa = cells.get(1);
                if (coa != null) {
                    switch (coa.getCellType()) {
                        case NUMERIC:
                            adjustmentThb.setCoa(NumberToTextConverter.toText(coa.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            adjustmentThb.setCoa(coa.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }  else {
                    throw new PrimaryKeyNullException("Column COA can't be blank, Row: " + rowCount);
                }

                Cell description = cells.get(2);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            adjustmentThb.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            adjustmentThb.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell debitCredit = cells.get(3);
                if (debitCredit != null) {
                    switch (debitCredit.getCellType()) {
                        case NUMERIC:
                            adjustmentThb.setDebitCredit(new BigDecimal(debitCredit.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            adjustmentThb.setDebitCredit(new BigDecimal(debitCredit.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }

                Cell note = cells.get(4);
                if (note != null) {
                    switch (note.getCellType()) {
                        case NUMERIC:
                            adjustmentThb.setNote(NumberToTextConverter.toText(note.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            adjustmentThb.setNote(note.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                adjustmentThbs.add(adjustmentThb);
                rowCount++;
            }

            workbook.close();

            return adjustmentThbs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<CoaBot> excelToCoaBot(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<CoaBot> coaBots = new ArrayList<CoaBot>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 8);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                CoaBot coaBot = new CoaBot();
                EmbedCoaBot coaBotPK = new EmbedCoaBot();

                // casting type data from file
                Cell glCode = cells.get(0);
                if (glCode != null) {
                    switch (glCode.getCellType()) {
                        case NUMERIC:
                            coaBotPK.setGlCode(NumberToTextConverter.toText(glCode.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBotPK.setGlCode(glCode.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new PrimaryKeyNullException("Column GL Code can't be blank, Row: " + rowCount);
//                    continue;
                }
                
                // casting type data from file
                Cell glName = cells.get(1);
                if (glName != null) {
                    switch (glName.getCellType()) {
                        case NUMERIC:
                            coaBot.setGlName(NumberToTextConverter.toText(glName.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBot.setGlName(glName.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                // casting type data from file
                Cell currency = cells.get(2);
                if (currency != null) {
                    switch (currency.getCellType()) {
                        case NUMERIC:
                            coaBotPK.setCurrency(NumberToTextConverter.toText(currency.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBotPK.setCurrency(currency.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    //int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Currency can't be blank, Row: " + rowCount);
                }

                Cell glCodeKbank = cells.get(3);
                if (glCodeKbank != null) {
                    switch (glCodeKbank.getCellType()) {
                        case NUMERIC:
                            coaBot.setGlCodeKbank(NumberToTextConverter.toText(glCodeKbank.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBot.setGlCodeKbank(glCodeKbank.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell accountName = cells.get(4);
                if (accountName != null) {
                    switch (accountName.getCellType()) {
                        case NUMERIC:
                            coaBot.setAccountName(NumberToTextConverter.toText(accountName.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBot.setAccountName(accountName.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell productCode = cells.get(5);
                if (productCode != null) {
                    switch (productCode.getCellType()) {
                        case NUMERIC:
                            coaBot.setProductCode(NumberToTextConverter.toText(productCode.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBot.setProductCode(productCode.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell reporting = cells.get(6);
                if (reporting != null) {
                    switch (reporting.getCellType()) {
                        case NUMERIC:
                            coaBot.setReporting(NumberToTextConverter.toText(reporting.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBot.setReporting(reporting.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell botReport = cells.get(7);
                if (botReport != null) {
                    switch (botReport.getCellType()) {
                        case NUMERIC:
                            coaBot.setBotReport(NumberToTextConverter.toText(botReport.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            coaBot.setBotReport(botReport.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }

                coaBot.setEmbedCoaBot(coaBotPK);
                coaBots.add(coaBot);
                rowCount++;
            }

            workbook.close();

            return coaBots;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<ReverseRepoSupport> excelToReverseRepoSupport(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<ReverseRepoSupport> reverseRepoSupports = new ArrayList<ReverseRepoSupport>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 12);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                ReverseRepoSupport reverseRepoSupport = new ReverseRepoSupport();

                // casting type data from file
                Cell underlyingNo = cells.get(0);
                if (underlyingNo != null) {
                    switch (underlyingNo.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setUnderlyingNo(NumberToTextConverter.toText(underlyingNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setUnderlyingNo(underlyingNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new PrimaryKeyNullException("Column Underlying No can't be blank, Row: " + rowCount);
                }
                
                Cell publicationDate = cells.get(1);
                if (publicationDate != null) {
                    String strDate = "f";
                    if (publicationDate.getCellType() == CellType.STRING) {
                        String datas = publicationDate.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];
                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(publicationDate.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(publicationDate);
                    }

                    switch (publicationDate.getCellType()) {
                        case STRING:
                            reverseRepoSupport.setPublicationDate(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            reverseRepoSupport.setPublicationDate(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }

                Cell dueDate = cells.get(2);
                if (dueDate != null) {
                    String strDate = "f";
                    if (dueDate.getCellType() == CellType.STRING) {
                        String datas = dueDate.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];
                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(dueDate.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(dueDate);
                    }

                    switch (dueDate.getCellType()) {
                        case STRING:
                            reverseRepoSupport.setDueDate(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            reverseRepoSupport.setDueDate(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell interestRate = cells.get(3);
                if (interestRate != null) {
                    switch (interestRate.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setInterestRate(new BigDecimal(interestRate.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setInterestRate(new BigDecimal(interestRate.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell couponType = cells.get(4);
                if (couponType != null) {
                    switch (couponType.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setCouponType(NumberToTextConverter.toText(couponType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setCouponType(couponType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell interestPaymentFrequency = cells.get(5);
                if (interestPaymentFrequency != null) {
                    switch (couponType.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setInterestPaymentFrequency(NumberToTextConverter.toText(interestPaymentFrequency.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setInterestPaymentFrequency(interestPaymentFrequency.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell type = cells.get(6);
                if (type != null) {
                    switch (type.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setType(NumberToTextConverter.toText(type.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setType(type.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell amount = cells.get(7);
                if (amount != null) {
                    switch (amount.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setAmount(new BigDecimal(amount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setAmount(new BigDecimal(amount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell rounding = cells.get(8);
                if (rounding != null) {
                    switch (rounding.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setRounding(new BigDecimal(rounding.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setRounding(new BigDecimal(rounding.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell secondLeg = cells.get(9);
                if (secondLeg != null) {
                    switch (secondLeg.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setSecondLeg(new BigDecimal(secondLeg.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setSecondLeg(new BigDecimal(secondLeg.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell cadanganPendapatan = cells.get(10);
                if (cadanganPendapatan != null) {
                    switch (cadanganPendapatan.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setCadanganPendapatan(new BigDecimal(cadanganPendapatan.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setCadanganPendapatan(new BigDecimal(cadanganPendapatan.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell outstanding = cells.get(11);
                if (outstanding != null) {
                    switch (secondLeg.getCellType()) {
                        case NUMERIC:
                            reverseRepoSupport.setOutstanding(new BigDecimal(outstanding.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            reverseRepoSupport.setOutstanding(new BigDecimal(outstanding.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                reverseRepoSupports.add(reverseRepoSupport);
                rowCount++;
            }

            workbook.close();

            return reverseRepoSupports;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<Wesel> excelToWesel(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Wesel> wesels = new ArrayList<Wesel>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 9);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Wesel wesel = new Wesel();

                // casting type data from file
                Cell no = cells.get(0);
                if (no != null) {
                    switch (no.getCellType()) {
                        case NUMERIC:
                            Double d = new Double(no.getNumericCellValue());
                            wesel.setNo(d.intValue());
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setNo(Integer.parseInt(no.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                } 
                
                Cell reference = cells.get(1);
                if (reference != null) {
                    switch (reference.getCellType()) {
                        case NUMERIC:
                            wesel.setReference(NumberToTextConverter.toText(reference.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setReference(reference.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell skbdnType = cells.get(2);
                if (skbdnType != null) {
                    switch (skbdnType.getCellType()) {
                        case NUMERIC:
                            wesel.setSkbdnType(NumberToTextConverter.toText(skbdnType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setSkbdnType(skbdnType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell purpose = cells.get(3);
                if (purpose != null) {
                    switch (purpose.getCellType()) {
                        case NUMERIC:
                            wesel.setPurpose(NumberToTextConverter.toText(purpose.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setPurpose(purpose.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell issuedDate = cells.get(4);
                if (issuedDate != null) {
                    String strDate = "f";
                    if (issuedDate.getCellType() == CellType.STRING) {
                        String datas = issuedDate.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];
                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(issuedDate.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(issuedDate);
                    }

                    switch (issuedDate.getCellType()) {
                        case STRING:
                            wesel.setIssuedDate(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            wesel.setIssuedDate(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell expiredDate = cells.get(5);
                if (expiredDate != null) {
                    String strDate = "f";
                    if (expiredDate.getCellType() == CellType.STRING) {
                        String datas = expiredDate.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];
                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(expiredDate.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(expiredDate);
                    }

                    switch (expiredDate.getCellType()) {
                        case STRING:
                            wesel.setExpiredDate(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            wesel.setExpiredDate(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell currency = cells.get(6);
                if (currency != null) {
                    switch (currency.getCellType()) {
                        case NUMERIC:
                            wesel.setCurrency(NumberToTextConverter.toText(currency.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setCurrency(currency.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell documentsAmount = cells.get(7);
                if (documentsAmount != null) {
                    switch (documentsAmount.getCellType()) {
                        case NUMERIC:
                            wesel.setDocumentsAmount(new BigDecimal(documentsAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setDocumentsAmount(new BigDecimal(documentsAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell applicantCustomerNo = cells.get(8);
                if (applicantCustomerNo != null) {
                    switch (applicantCustomerNo.getCellType()) {
                        case NUMERIC:
                            wesel.setApplicantCustomerNo(NumberToTextConverter.toText(applicantCustomerNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            wesel.setApplicantCustomerNo(applicantCustomerNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new PrimaryKeyNullException("Column No Nasabah Pemohon can't be blank, Row: " + rowCount);
                }
                
                wesels.add(wesel);
                rowCount++;
            }

            workbook.close();

            return wesels;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    } 
    
    public static List<Bilyet> excelToBilyet(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Bilyet> bilyets = new ArrayList<Bilyet>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 8);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Bilyet bilyet = new Bilyet();
                
                Cell checkNo = cells.get(0);
                if (checkNo != null) {
                    switch (checkNo.getCellType()) {
                        case NUMERIC:
                            bilyet.setCheckNo(NumberToTextConverter.toText(checkNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bilyet.setCheckNo(checkNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell checkType = cells.get(1);
                if (checkType != null) {
                    switch (checkType.getCellType()) {
                        case NUMERIC:
                            bilyet.setCheckType(NumberToTextConverter.toText(checkType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bilyet.setCheckType(checkType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell purpose = cells.get(2);
                if (purpose != null) {
                    switch (purpose.getCellType()) {
                        case NUMERIC:
                            bilyet.setPurpose(NumberToTextConverter.toText(purpose.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bilyet.setPurpose(purpose.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell issuedDate = cells.get(3);
                if (issuedDate != null) {
                    String strDate = "f";
                    if (issuedDate.getCellType() == CellType.STRING) {
                        String datas = issuedDate.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];
                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(issuedDate.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(issuedDate);
                    }
                    switch (issuedDate.getCellType()) {
                        case STRING:
                            bilyet.setIssuedDate(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            bilyet.setIssuedDate(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell expiredDate = cells.get(4);
                if (expiredDate != null) {
                    String strDate = "f";
                    if (expiredDate.getCellType() == CellType.STRING) {
                        String datas = expiredDate.getStringCellValue();
                        String[] parts = datas.split("-");
                        String part1 = parts[0];
                        if (part1.length() > 2) {
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateValue = input.parse(expiredDate.getStringCellValue());

                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                            strDate = output.format(dateValue);
                        }
                    } else {
                        strDate = convertDateString(expiredDate);
                    }
                    switch (expiredDate.getCellType()) {
                        case STRING:
                            bilyet.setExpiredDate(Date.valueOf(strDate));
                            break;
                        case NUMERIC:
                            bilyet.setExpiredDate(Date.valueOf(strDate));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell currency = cells.get(5);
                if (currency != null) {
                    switch (currency.getCellType()) {
                        case NUMERIC:
                            bilyet.setCurrency(NumberToTextConverter.toText(currency.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bilyet.setCurrency(currency.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell documentsAmount = cells.get(6);
                if (documentsAmount != null) {
                    switch (documentsAmount.getCellType()) {
                        case NUMERIC:
                            bilyet.setDocumentsAmount(new BigDecimal(documentsAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bilyet.setDocumentsAmount(new BigDecimal(documentsAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell applicantCustomerNo = cells.get(7);
                if (applicantCustomerNo != null) {
                    switch (applicantCustomerNo.getCellType()) {
                        case NUMERIC:
                            bilyet.setApplicantCustomerNo(NumberToTextConverter.toText(applicantCustomerNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bilyet.setApplicantCustomerNo(applicantCustomerNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new PrimaryKeyNullException("Column No Nasabah Pemohon can't be blank, Row: " + rowCount);
                }
                
                bilyets.add(bilyet);
                rowCount++;
            }

            workbook.close();

            return bilyets;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<Loan> excelToLoan(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Loan> loans = new ArrayList<Loan>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 11);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Loan loan = new Loan();
                EmbedLoan embedLoan = new EmbedLoan();
                
                Cell customerNo = cells.get(0);
                if (customerNo != null) {
                    switch (customerNo.getCellType()) {
                        case NUMERIC:
                            embedLoan.setCustomerNo(NumberToTextConverter.toText(customerNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedLoan.setCustomerNo(customerNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Customer can't be blank, Row: " + rowCount);
                }
                
                Cell loanNo = cells.get(1);
                if (loanNo != null) {
                    switch (loanNo.getCellType()) {
                        case NUMERIC:
                            embedLoan.setLoanNo(NumberToTextConverter.toText(loanNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedLoan.setLoanNo(loanNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Rekening can't be blank, Row: " + rowCount);
                }
                
                Cell movementType = cells.get(2);
                if (movementType != null) {
                    switch (movementType.getCellType()) {
                        case NUMERIC:
                            loan.setMovementType(NumberToTextConverter.toText(movementType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setMovementType(movementType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell operationProgress = cells.get(3);
                if (operationProgress != null) {
                    switch (operationProgress.getCellType()) {
                        case NUMERIC:
                            loan.setOperationProgress(NumberToTextConverter.toText(operationProgress.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setOperationProgress(operationProgress.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell assetClassReason = cells.get(4);
                if (assetClassReason != null) {
                    switch (assetClassReason.getCellType()) {
                        case NUMERIC:
                            loan.setAssetClassReason(NumberToTextConverter.toText(assetClassReason.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setAssetClassReason(assetClassReason.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell assetClassReasonUnused = cells.get(5);
                if (assetClassReasonUnused != null) {
                    switch (assetClassReasonUnused.getCellType()) {
                        case NUMERIC:
                            loan.setAssetClassReasonUnused(NumberToTextConverter.toText(assetClassReasonUnused.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setAssetClassReasonUnused(assetClassReasonUnused.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell tdrType = cells.get(6);
                if (tdrType != null) {
                    switch (tdrType.getCellType()) {
                        case NUMERIC:
                            loan.setTdrType(NumberToTextConverter.toText(tdrType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setTdrType(tdrType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell tdrMethodType = cells.get(7);
                if (tdrMethodType != null) {
                    switch (tdrMethodType.getCellType()) {
                        case NUMERIC:
                            loan.setTdrMethodType(NumberToTextConverter.toText(tdrMethodType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setTdrMethodType(tdrMethodType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell loanType = cells.get(8);
                if (loanType != null) {
                    switch (loanType.getCellType()) {
                        case NUMERIC:
                            loan.setLoanType(NumberToTextConverter.toText(loanType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setLoanType(loanType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell revolving = cells.get(9);
                if (revolving != null) {
                    switch (revolving.getCellType()) {
                        case NUMERIC:
                            loan.setRevolving(NumberToTextConverter.toText(revolving.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setRevolving(revolving.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell collateralDesc = cells.get(10);
                if (collateralDesc != null) {
                    switch (collateralDesc.getCellType()) {
                        case NUMERIC:
                            loan.setCollateralDesc(NumberToTextConverter.toText(collateralDesc.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            loan.setCollateralDesc(collateralDesc.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                loan.setEmbedLoan(embedLoan);
                loans.add(loan);
                rowCount++;
            }

            workbook.close();

            return loans;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<IrrevocableLc> excelToIrrevocableLc(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<IrrevocableLc> irrevocableLcs = new ArrayList<IrrevocableLc>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                IrrevocableLc irrevocableLc = new IrrevocableLc();
                EmbedIrrevocableLc embedIrrevocableLc = new EmbedIrrevocableLc();
                
                Cell customerNo = cells.get(0);
                if (customerNo != null) {
                    switch (customerNo.getCellType()) {
                        case NUMERIC:
                            embedIrrevocableLc.setCustomerNo(NumberToTextConverter.toText(customerNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedIrrevocableLc.setCustomerNo(customerNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Nasabah can't be blank, Row: " + rowCount);
                }
                
                Cell loanNo = cells.get(1);
                if (loanNo != null) {
                    switch (loanNo.getCellType()) {
                        case NUMERIC:
                            embedIrrevocableLc.setAccountNo(NumberToTextConverter.toText(loanNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedIrrevocableLc.setAccountNo(loanNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Rekening can't be blank, Row: " + rowCount);
                }
                
                Cell asetClassifitcationReason = cells.get(2);
                if (asetClassifitcationReason != null) {
                    switch (asetClassifitcationReason.getCellType()) {
                        case NUMERIC:
                            irrevocableLc.setAsetClassifitcationReason(NumberToTextConverter.toText(asetClassifitcationReason.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            irrevocableLc.setAsetClassifitcationReason(asetClassifitcationReason.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell economicSector = cells.get(3);
                if (economicSector != null) {
                    switch (economicSector.getCellType()) {
                        case NUMERIC:
                            irrevocableLc.setEconomicSector(NumberToTextConverter.toText(economicSector.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            irrevocableLc.setEconomicSector(economicSector.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                irrevocableLc.setEmbedIrrevocableLc(embedIrrevocableLc);
                irrevocableLcs.add(irrevocableLc);
                rowCount++;
            }

            workbook.close();

            return irrevocableLcs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<BankGuarantee> excelToBankGuarantee(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<BankGuarantee> bankGuarantees = new ArrayList<BankGuarantee>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                BankGuarantee bankGuarantee = new BankGuarantee();
                EmbedBankGuarantee embedBankGuarantee = new EmbedBankGuarantee();
                
                Cell customerNo = cells.get(0);
                if (customerNo != null) {
                    switch (customerNo.getCellType()) {
                        case NUMERIC:
                            embedBankGuarantee.setCustomerNo(NumberToTextConverter.toText(customerNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedBankGuarantee.setCustomerNo(customerNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Nasabah can't be blank, Row: " + rowCount);
                }
                
                
                Cell accountNo = cells.get(1);
                if (accountNo != null) {
                    switch (accountNo.getCellType()) {
                        case NUMERIC:
                            embedBankGuarantee.setAccountNo(NumberToTextConverter.toText(accountNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedBankGuarantee.setAccountNo(accountNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column No Rekening can't be blank, Row: " + rowCount);
                }
                
                Cell asetClassifitcationReason = cells.get(2);
                if (asetClassifitcationReason != null) {
                    switch (asetClassifitcationReason.getCellType()) {
                        case NUMERIC:
                            bankGuarantee.setAsetClassifitcationReason(NumberToTextConverter.toText(asetClassifitcationReason.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bankGuarantee.setAsetClassifitcationReason(asetClassifitcationReason.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell economicSector = cells.get(3);
                if (economicSector != null) {
                    switch (economicSector.getCellType()) {
                        case NUMERIC:
                            bankGuarantee.setEconomicSector(NumberToTextConverter.toText(economicSector.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            bankGuarantee.setEconomicSector(economicSector.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                bankGuarantee.setEmbedBankGuarantee(embedBankGuarantee);
                bankGuarantees.add(bankGuarantee);
                rowCount++;
            }

            workbook.close();

            return bankGuarantees;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<Kpmm> excelToKpmm(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Kpmm> kpmms = new ArrayList<Kpmm>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Kpmm kpmm = new Kpmm();
                EmbedKpmm embedKpmm = new EmbedKpmm();
                
                Cell kodeForm = cells.get(0);
                if (kodeForm != null) {
                    switch (kodeForm.getCellType()) {
                        case NUMERIC:
                            embedKpmm.setKodeForm(NumberToTextConverter.toText(kodeForm.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedKpmm.setKodeForm(kodeForm.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Form can't be blank, Row: " + rowCount);
                }
                
                Cell kodeKomponen = cells.get(1);
                if (kodeKomponen != null) {
                    switch (kodeKomponen.getCellType()) {
                        case NUMERIC:
                            embedKpmm.setKodeKomponen(NumberToTextConverter.toText(kodeKomponen.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedKpmm.setKodeKomponen(kodeKomponen.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Kode Komponen can't be blank, Row: " + rowCount);
                }
                
                Cell namaKomponen = cells.get(2);
                if (namaKomponen != null) {
                    switch (namaKomponen.getCellType()) {
                        case NUMERIC:
                            kpmm.setNamaKomponen(NumberToTextConverter.toText(namaKomponen.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kpmm.setNamaKomponen(namaKomponen.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell jumlah = cells.get(3);
                if (jumlah != null) {
                    switch (jumlah.getCellType()) {
                        case NUMERIC:
                            kpmm.setJumlah(new BigDecimal(jumlah.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            kpmm.setJumlah(new BigDecimal(jumlah.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                kpmm.setEmbedKpmm(embedKpmm);
                kpmms.add(kpmm);
                rowCount++;
            }

            workbook.close();

            return kpmms;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<Securities> excelToSecurities(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<Securities> securitieses = new ArrayList<Securities>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 4);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                Securities securities = new Securities();
                
                Cell uniqueId = cells.get(0);
                if (uniqueId != null) {
                    switch (uniqueId.getCellType()) {
                        case NUMERIC:
                            securities.setUniqueId(NumberToTextConverter.toText(uniqueId.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            securities.setUniqueId(uniqueId.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Unique Id can't be blank, Row: " + rowCount);
                }
                
                Cell finalAmount = cells.get(1);
                if (finalAmount != null) {
                    switch (finalAmount.getCellType()) {
                        case NUMERIC:
                            securities.setFinalAmount(new BigDecimal(finalAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            securities.setFinalAmount(new BigDecimal(finalAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell buyAmount = cells.get(2);
                if (buyAmount != null) {
                    switch (buyAmount.getCellType()) {
                        case NUMERIC:
                            securities.setBuyAmount(new BigDecimal(buyAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            securities.setBuyAmount(new BigDecimal(buyAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell marketToMarket = cells.get(3);
                if (marketToMarket != null) {
                    switch (marketToMarket.getCellType()) {
                        case NUMERIC:
                            securities.setMarketToMarket(new BigDecimal(marketToMarket.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            securities.setMarketToMarket(new BigDecimal(marketToMarket.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                securitieses.add(securities);
                rowCount++;
            }

            workbook.close();

            return securitieses;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<IntercompanyTransactionKbank> excelToIntercompanyTransactionKbank(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<IntercompanyTransactionKbank> intercompanyTransactions = new ArrayList<IntercompanyTransactionKbank>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 7);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                IntercompanyTransactionKbank intercompanyTransaction = new IntercompanyTransactionKbank();
                EmbedIntercompanyTransactionKbank embedIntercompanyTransaction = new EmbedIntercompanyTransactionKbank();
                
                Cell accountType = cells.get(0);
                if (accountType != null) {
                    switch (accountType.getCellType()) {
                        case NUMERIC:
                            embedIntercompanyTransaction.setAccountType(NumberToTextConverter.toText(accountType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedIntercompanyTransaction.setAccountType(accountType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Type Of Account can't be blank, Row: " + rowCount);
                }
                
                Cell accountNo = cells.get(1);
                if (accountNo != null) {
                    switch (accountNo.getCellType()) {
                        case NUMERIC:
                            embedIntercompanyTransaction.setAccountNo(NumberToTextConverter.toText(accountNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedIntercompanyTransaction.setAccountNo(accountNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Type Of Account can't be blank, Row: " + rowCount);
                }
                
                Cell branch = cells.get(2);
                if (branch != null) {
                    switch (branch.getCellType()) {
                        case NUMERIC:
                            intercompanyTransaction.setBranchCode(NumberToTextConverter.toText(branch.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransaction.setBranchCode(branch.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell oglAccountNo = cells.get(3);
                if (oglAccountNo != null) {
                    switch (oglAccountNo.getCellType()) {
                        case NUMERIC:
                            intercompanyTransaction.setOglAccountNo(NumberToTextConverter.toText(oglAccountNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransaction.setOglAccountNo(oglAccountNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell oglAmount = cells.get(4);
                if (oglAmount != null) {
                    switch (oglAmount.getCellType()) {
                        case NUMERIC:
                            intercompanyTransaction.setOglAmount(new BigDecimal(oglAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransaction.setOglAmount(new BigDecimal(oglAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell bankStatementAmount = cells.get(5);
                if (bankStatementAmount != null) {
                    switch (bankStatementAmount.getCellType()) {
                        case NUMERIC:
                            intercompanyTransaction.setBankStatementAmount(new BigDecimal(bankStatementAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransaction.setBankStatementAmount(new BigDecimal(bankStatementAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell remainPeriod = cells.get(6);
                if (remainPeriod != null) {
                    switch (remainPeriod.getCellType()) {
                        case NUMERIC:
                            intercompanyTransaction.setRemainPeriod(NumberToTextConverter.toText(remainPeriod.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransaction.setRemainPeriod(remainPeriod.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                intercompanyTransaction.setEmbedIntercompanyTransactionKbank(embedIntercompanyTransaction);
                intercompanyTransactions.add(intercompanyTransaction);
                rowCount++;
            }

            workbook.close();

            return intercompanyTransactions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<IntercompanyTransactionGroup> excelToIntercompanyTransactionGroup(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<IntercompanyTransactionGroup> intercompanyTransactionsGroups = new ArrayList<IntercompanyTransactionGroup>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 8);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }

                IntercompanyTransactionGroup intercompanyTransactionGroup = new IntercompanyTransactionGroup();
                EmbedIntercompanyTransactionGroup embedIntercompanyTransactionGroup = new EmbedIntercompanyTransactionGroup();
                
                Cell accountType = cells.get(0);
                if (accountType != null) {
                    switch (accountType.getCellType()) {
                        case NUMERIC:
                            embedIntercompanyTransactionGroup.setAccountType(NumberToTextConverter.toText(accountType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedIntercompanyTransactionGroup.setAccountType(accountType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Type Of Account can't be blank, Row: " + rowCount);
                }
                
                Cell accountNo = cells.get(1);
                if (accountNo != null) {
                    switch (accountNo.getCellType()) {
                        case NUMERIC:
                            embedIntercompanyTransactionGroup.setAccountNo(NumberToTextConverter.toText(accountNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedIntercompanyTransactionGroup.setAccountNo(accountNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Type Of Account can't be blank, Row: " + rowCount);
                }
                
                Cell branch = cells.get(2);
                if (branch != null) {
                    switch (branch.getCellType()) {
                        case NUMERIC:
                            intercompanyTransactionGroup.setBranchCode(NumberToTextConverter.toText(branch.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransactionGroup.setBranchCode(branch.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell oglAccountNo = cells.get(3);
                if (oglAccountNo != null) {
                    switch (oglAccountNo.getCellType()) {
                        case NUMERIC:
                            intercompanyTransactionGroup.setOglAccountNo(NumberToTextConverter.toText(oglAccountNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransactionGroup.setOglAccountNo(oglAccountNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell oglAmount = cells.get(4);
                if (oglAmount != null) {
                    switch (oglAmount.getCellType()) {
                        case NUMERIC:
                            intercompanyTransactionGroup.setOglAmount(new BigDecimal(oglAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransactionGroup.setOglAmount(new BigDecimal(oglAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell bankStatementAmount = cells.get(5);
                if (bankStatementAmount != null) {
                    switch (bankStatementAmount.getCellType()) {
                        case NUMERIC:
                            intercompanyTransactionGroup.setBankStatementAmount(new BigDecimal(bankStatementAmount.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransactionGroup.setBankStatementAmount(new BigDecimal(bankStatementAmount.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell remainPeriod = cells.get(6);
                if (remainPeriod != null) {
                    switch (remainPeriod.getCellType()) {
                        case NUMERIC:
                            intercompanyTransactionGroup.setRemainPeriod(NumberToTextConverter.toText(remainPeriod.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransactionGroup.setRemainPeriod(remainPeriod.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell intercompanyCode = cells.get(7);
                if (intercompanyCode != null) {
                    switch (intercompanyCode.getCellType()) {
                        case NUMERIC:
                            intercompanyTransactionGroup.setIntercompanyCode(NumberToTextConverter.toText(intercompanyCode.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            intercompanyTransactionGroup.setIntercompanyCode(intercompanyCode.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                intercompanyTransactionGroup.setEmbedIntercompanyTransactionGroup(embedIntercompanyTransactionGroup);
                intercompanyTransactionsGroups.add(intercompanyTransactionGroup);
                rowCount++;
            }

            workbook.close();

            return intercompanyTransactionsGroups;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<MapLoanType> excelToMapLoanType(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<MapLoanType> mapLoanTypes = new ArrayList<MapLoanType>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 3);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                MapLoanType mapLoanType = new MapLoanType();
                EmbedMapLoanType embedMapLoanType = new EmbedMapLoanType();
                
                Cell loanType = cells.get(0);
                if (loanType != null) {
                    switch (loanType.getCellType()) {
                        case NUMERIC:
                            embedMapLoanType.setLoanType(NumberToTextConverter.toText(loanType.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedMapLoanType.setLoanType(loanType.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Type Loan can't be blank, Row: " + rowCount);
                }
                
                Cell bmiProduct = cells.get(1);
                if (bmiProduct != null) {
                    switch (bmiProduct.getCellType()) {
                        case NUMERIC:
                            embedMapLoanType.setBmiProduct(NumberToTextConverter.toText(bmiProduct.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedMapLoanType.setBmiProduct(bmiProduct.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column BMI Product can't be blank, Row: " + rowCount);
                }
                
                Cell kbankProduct = cells.get(2);
                if (kbankProduct != null) {
                    switch (kbankProduct.getCellType()) {
                        case NUMERIC:
                            mapLoanType.setKbankProduct(NumberToTextConverter.toText(kbankProduct.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            mapLoanType.setKbankProduct(kbankProduct.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                mapLoanType.setEmbedMapLoanType(embedMapLoanType);
                mapLoanTypes.add(mapLoanType);
                rowCount++;
            }

            workbook.close();

            return mapLoanTypes;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<InvestmentNet> excelToInvestmentNet(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<InvestmentNet> investmentNets = new ArrayList<InvestmentNet>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 6);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                InvestmentNet investmentNet = new InvestmentNet();
                
                Cell description = cells.get(0);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            investmentNet.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            investmentNet.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Description can't be blank, Row: " + rowCount);
                }
                
                Cell revaluationSurplus = cells.get(1);
                if (revaluationSurplus != null) {
                    switch (revaluationSurplus.getCellType()) {
                        case NUMERIC:
                            investmentNet.setRevaluationSurplus(new BigDecimal(revaluationSurplus.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            investmentNet.setRevaluationSurplus(new BigDecimal(revaluationSurplus.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell revaluationDeficit = cells.get(2);
                if (revaluationDeficit != null) {
                    switch (revaluationDeficit.getCellType()) {
                        case NUMERIC:
                            investmentNet.setRevaluationDeficit(new BigDecimal(revaluationDeficit.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            investmentNet.setRevaluationDeficit(new BigDecimal(revaluationDeficit.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell expectedCreditLoss = cells.get(3);
                if (expectedCreditLoss != null) {
                    switch (expectedCreditLoss.getCellType()) {
                        case NUMERIC:
                            investmentNet.setExpectedCreditLoss(new BigDecimal(expectedCreditLoss.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            investmentNet.setExpectedCreditLoss(new BigDecimal(expectedCreditLoss.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell hedgeInvestment = cells.get(4);
                if (hedgeInvestment != null) {
                    switch (hedgeInvestment.getCellType()) {
                        case NUMERIC:
                            investmentNet.setHedgeInvestment(new BigDecimal(hedgeInvestment.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            investmentNet.setHedgeInvestment(new BigDecimal(hedgeInvestment.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell dividendIncome = cells.get(5);
                if (dividendIncome != null) {
                    switch (dividendIncome.getCellType()) {
                        case NUMERIC:
                            investmentNet.setDividendIncome(new BigDecimal(dividendIncome.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            investmentNet.setDividendIncome(new BigDecimal(dividendIncome.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                System.out.println("investmentNet.getDescription = "+investmentNet.getDescription());
                investmentNets.add(investmentNet);
                rowCount++;
            }

            workbook.close();

            return investmentNets;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<ShortTermEmployeeBenefits> excelToShortTermEmployeeBenefits(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<ShortTermEmployeeBenefits> employeeBenefitses = new ArrayList<ShortTermEmployeeBenefits>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                ShortTermEmployeeBenefits employeeBenefits = new ShortTermEmployeeBenefits();
                
                Cell description = cells.get(0);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            employeeBenefits.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            employeeBenefits.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Description can't be blank, Row: " + rowCount);
                }
                
                Cell janMar = cells.get(1);
                if (janMar != null) {
                    switch (janMar.getCellType()) {
                        case NUMERIC:
                            employeeBenefits.setJanMar(new BigDecimal(janMar.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            employeeBenefits.setJanMar(new BigDecimal(janMar.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell aprJun = cells.get(2);
                if (aprJun != null) {
                    switch (aprJun.getCellType()) {
                        case NUMERIC:
                            employeeBenefits.setAprJun(new BigDecimal(aprJun.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            employeeBenefits.setAprJun(new BigDecimal(aprJun.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell julSep = cells.get(3);
                if (julSep != null) {
                    switch (julSep.getCellType()) {
                        case NUMERIC:
                            employeeBenefits.setJulSep(new BigDecimal(julSep.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            employeeBenefits.setJulSep(new BigDecimal(julSep.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell octDec = cells.get(4);
                if (octDec != null) {
                    switch (octDec.getCellType()) {
                        case NUMERIC:
                            employeeBenefits.setOctDec(new BigDecimal(octDec.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            employeeBenefits.setOctDec(new BigDecimal(octDec.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                employeeBenefitses.add(employeeBenefits);
                rowCount++;
            }

            workbook.close();

            return employeeBenefitses;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<PostEmploymentBenefitsEmployeeId> excelToPostEmploymentBenefitsEmployeeId(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<PostEmploymentBenefitsEmployeeId> benefitsEmployeeIds = new ArrayList<PostEmploymentBenefitsEmployeeId>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                PostEmploymentBenefitsEmployeeId benefitsEmployeeId = new PostEmploymentBenefitsEmployeeId();
                
                Cell description = cells.get(0);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            benefitsEmployeeId.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefitsEmployeeId.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Description can't be blank, Row: " + rowCount);
                }
                
                Cell mar = cells.get(1);
                if (mar != null) {
                    switch (mar.getCellType()) {
                        case NUMERIC:
                            benefitsEmployeeId.setMar(new BigDecimal(mar.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefitsEmployeeId.setMar(new BigDecimal(mar.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell jun = cells.get(2);
                if (jun != null) {
                    switch (jun.getCellType()) {
                        case NUMERIC:
                            benefitsEmployeeId.setJun(new BigDecimal(jun.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefitsEmployeeId.setJun(new BigDecimal(jun.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell sep = cells.get(3);
                if (sep != null) {
                    switch (sep.getCellType()) {
                        case NUMERIC:
                            benefitsEmployeeId.setSep(new BigDecimal(sep.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefitsEmployeeId.setSep(new BigDecimal(sep.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell dec = cells.get(4);
                if (dec != null) {
                    switch (dec.getCellType()) {
                        case NUMERIC:
                            benefitsEmployeeId.setDec(new BigDecimal(dec.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefitsEmployeeId.setDec(new BigDecimal(dec.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                benefitsEmployeeIds.add(benefitsEmployeeId);
                rowCount++;
            }

            workbook.close();

            return benefitsEmployeeIds;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<PostEmploymentBenefits> excelToPostEmploymentBenefits(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<PostEmploymentBenefits> benefitses = new ArrayList<PostEmploymentBenefits>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                PostEmploymentBenefits benefits = new PostEmploymentBenefits();
                
                Cell description = cells.get(0);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            benefits.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefits.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Description can't be blank, Row: " + rowCount);
                }
                
                Cell janMar = cells.get(1);
                if (janMar != null) {
                    switch (janMar.getCellType()) {
                        case NUMERIC:
                            benefits.setJanMar(new BigDecimal(janMar.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefits.setJanMar(new BigDecimal(janMar.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell aprJun = cells.get(2);
                if (aprJun != null) {
                    switch (aprJun.getCellType()) {
                        case NUMERIC:
                            benefits.setAprJun(new BigDecimal(aprJun.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefits.setAprJun(new BigDecimal(aprJun.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell julSep = cells.get(3);
                if (julSep != null) {
                    switch (julSep.getCellType()) {
                        case NUMERIC:
                            benefits.setJulSep(new BigDecimal(julSep.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefits.setJulSep(new BigDecimal(julSep.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell octDec = cells.get(4);
                if (octDec != null) {
                    switch (octDec.getCellType()) {
                        case NUMERIC:
                            benefits.setOctDec(new BigDecimal(octDec.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            benefits.setOctDec(new BigDecimal(octDec.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                benefitses.add(benefits);
                rowCount++;
            }

            workbook.close();

            return benefitses;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<OtherBenefitsPaid> excelToOtherBenefitsPaid(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<OtherBenefitsPaid> otherBenefitsPaids = new ArrayList<OtherBenefitsPaid>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                OtherBenefitsPaid otherBenefitsPaid = new OtherBenefitsPaid();
                
                Cell description = cells.get(0);
                if (description != null) {
                    switch (description.getCellType()) {
                        case NUMERIC:
                            otherBenefitsPaid.setDescription(NumberToTextConverter.toText(description.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            otherBenefitsPaid.setDescription(description.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Description can't be blank, Row: " + rowCount);
                }
                
                Cell janMar = cells.get(1);
                if (janMar != null) {
                    switch (janMar.getCellType()) {
                        case NUMERIC:
                            otherBenefitsPaid.setJanMar(new BigDecimal(janMar.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            otherBenefitsPaid.setJanMar(new BigDecimal(janMar.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell aprJun = cells.get(2);
                if (aprJun != null) {
                    switch (aprJun.getCellType()) {
                        case NUMERIC:
                            otherBenefitsPaid.setAprJun(new BigDecimal(aprJun.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            otherBenefitsPaid.setAprJun(new BigDecimal(aprJun.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell julSep = cells.get(3);
                if (julSep != null) {
                    switch (julSep.getCellType()) {
                        case NUMERIC:
                            otherBenefitsPaid.setJulSep(new BigDecimal(julSep.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            otherBenefitsPaid.setJulSep(new BigDecimal(julSep.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell octDec = cells.get(4);
                if (octDec != null) {
                    switch (octDec.getCellType()) {
                        case NUMERIC:
                            otherBenefitsPaid.setOctDec(new BigDecimal(octDec.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            otherBenefitsPaid.setOctDec(new BigDecimal(octDec.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                otherBenefitsPaids.add(otherBenefitsPaid);
                rowCount++;
            }

            workbook.close();

            return otherBenefitsPaids;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<FundingConcentrationInterbank> excelToFundingConcentrationInterbank(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<FundingConcentrationInterbank> fundingConcentrationInterbanks = new ArrayList<FundingConcentrationInterbank>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 1);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                FundingConcentrationInterbank fundingConcentrationInterbank = new FundingConcentrationInterbank();
                
                Cell cifNo = cells.get(0);
                if (cifNo != null) {
                    switch (cifNo.getCellType()) {
                        case NUMERIC:
                            fundingConcentrationInterbank.setCifNo(NumberToTextConverter.toText(cifNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            fundingConcentrationInterbank.setCifNo(cifNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Cif No can't be blank, Row: " + rowCount);
                }
                
                fundingConcentrationInterbanks.add(fundingConcentrationInterbank);
                rowCount++;
            }

            workbook.close();

            return fundingConcentrationInterbanks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<CifToFi> excelToCifToFi(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<CifToFi> cifToFis = new ArrayList<CifToFi>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 2);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                CifToFi cifToFi = new CifToFi();
                EmbedCifToFi embedCifToFi = new EmbedCifToFi();
                
                Cell cifNo = cells.get(0);
                if (cifNo != null) {
                    switch (cifNo.getCellType()) {
                        case NUMERIC:
                            embedCifToFi.setCifNo(NumberToTextConverter.toText(cifNo.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedCifToFi.setCifNo(cifNo.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
//                    int getRow = row.getRowNum() + 1;
                    throw new PrimaryKeyNullException("Column Cif No can't be blank, Row: " + rowCount);
                }
                
                Cell flag = cells.get(1);
                if (flag != null) {
                    switch (flag.getCellType()) {
                        case NUMERIC:
                            embedCifToFi.setFlag(NumberToTextConverter.toText(flag.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedCifToFi.setFlag(flag.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                cifToFi.setEmbedCifToFi(embedCifToFi);
                cifToFis.add(cifToFi);
                rowCount++;
            }

            workbook.close();

            return cifToFis;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        
    }
    
    public static List<DeferredTax> excelToDeferredTax(InputStream is) {
        try {
            // open the file
            Workbook workbook = new XSSFWorkbook(is);

            // get sheet name
            Sheet sheet = workbook.getSheetAt(0);

            List<DeferredTax> deferredTaxs = new ArrayList<DeferredTax>();

            // skip header
            boolean skipHeader = true;
            int rowCount = 1;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // read last column the file
                int lastColumn = Math.max(row.getLastCellNum(), 5);

                // collect element the file
                List<Cell> cells = new ArrayList<Cell>();

                // logic for handle blank or empty cell
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                
                DeferredTax deferredTax = new DeferredTax();
                EmbedDeferredTax embedDeferredTax = new EmbedDeferredTax();
                
                Cell code = cells.get(0);
                if (code != null) {
                    switch (code.getCellType()) {
                        case NUMERIC:
                            embedDeferredTax.setCode(NumberToTextConverter.toText(code.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedDeferredTax.setCode(code.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new PrimaryKeyNullException("Column Code can't be blank, Row: " + rowCount);
                }
                
                Cell gropCode = cells.get(1);
                if (gropCode != null) {
                    switch (gropCode.getCellType()) {
                        case NUMERIC:
                            embedDeferredTax.setGroupCode(NumberToTextConverter.toText(gropCode.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            embedDeferredTax.setGroupCode(gropCode.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell desc = cells.get(2);
                if (desc != null) {
                    switch (desc.getCellType()) {
                        case NUMERIC:
                            deferredTax.setDescription(NumberToTextConverter.toText(desc.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            deferredTax.setDescription(desc.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                
                Cell dta = cells.get(3);
                if (dta != null) {
                    switch (dta.getCellType()) {
                        case NUMERIC:
                            deferredTax.setDeferredTaxAssets(new BigDecimal(dta.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            deferredTax.setDeferredTaxAssets(new BigDecimal(dta.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                Cell dtl = cells.get(4);
                if (dtl != null) {
                    switch (dtl.getCellType()) {
                        case NUMERIC:
                            deferredTax.setDeferredTaxLiabilities(new BigDecimal(dtl.getNumericCellValue()));
                            break;
                        case BLANK:
                            break;
                        case STRING:
                            deferredTax.setDeferredTaxLiabilities(new BigDecimal(dtl.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                
                deferredTax.setEmbedDeferredTax(embedDeferredTax);
                deferredTaxs.add(deferredTax);
                rowCount++;
            }

            workbook.close();

            return deferredTaxs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        
    }



    
    
    
    

    public boolean validationAneh(List<Nasabahjoin> dataList) {

        Integer counter = 2;
        for (Nasabahjoin data : dataList) {
            if (data.getFlagNasabah().equals("0")) {
                if (data.getTipeCif().equals("I") || data.getTipeCif().equals("i")) {

                    Map<String, Object> mandatory = new HashMap<String, Object>();
                    mandatory.put("cif", data.getCif());
                    mandatory.put("cif_join", data.getCifJoin());
                    mandatory.put("flag_nasabah", data.getFlagNasabah());
                    mandatory.put("flag_join", data.getFlagJoin());
                    mandatory.put("tipe_cif", data.getTipeCif());
                    mandatory.put("nm_lnkp_nsb", data.getNmLnkpNsb());
                    mandatory.put("jns_indentitas", data.getJnsIdentitas());
                    mandatory.put("no_identitas", data.getNoIdentitas());
                    mandatory.put("ibu_kdg", data.getNmIbuKdg());
                    mandatory.put("tempat_lahir", data.getTmpLahir());
                    mandatory.put("tanggal_lahir", data.getTglLahir());
                    mandatory.put("kab_kota", data.getKabKota());
                    mandatory.put("kewarnegaraan", data.getKewarnegaraan());
                    mandatory.put("flag_fraud", data.getFlagFraud());
                    mandatory.put("hub_dgn_bank", data.getHubDgnBank());
                    mandatory.put("gol_nsb", data.getGolNsb());
                    mandatory.put("kategori_usaha", data.getKategoriUsaha());

                    Map<String, Object> empty = new HashMap<String, Object>();
                    empty.put("nm_pemegang_kuasa", data.getNmLnkpPemegangKuasa());
                    empty.put("jns_pemegang_kuasa", data.getJnsIdentitasPemegangKuasa());
                    empty.put("no_pemegang_kuasa", data.getNoIdentitasPemegangKuasa());

                    Map<String, Object> optional = new HashMap<String, Object>();
                    optional.put("npwp", data.getNoNpwp());
                    optional.put("no_siup", data.getNoSiup());
                    optional.put("alamat", data.getAlamat());
                    optional.put("no_telp", data.getNoTelp());


                    for (Map.Entry<String, Object> entryMandatory : mandatory.entrySet()) {
                        if (entryMandatory.getValue() == null) {
                            ValidationImportLog savedLog = new ValidationImportLog();
                            savedLog.setRowNumber(String.valueOf(counter));
                            savedLog.setColumnName(entryMandatory.getKey());
                            savedLog.setDescription("Column cannot be blank");
                            validationImportLogRepository.save(savedLog);
                        }
                    }

                    for (Map.Entry<String, Object> entryEmpty : empty.entrySet()) {
                        if (entryEmpty.getValue() != null) {
                            ValidationImportLog savedLog = new ValidationImportLog();
                            savedLog.setRowNumber(String.valueOf(counter));
                            savedLog.setColumnName(entryEmpty.getKey());
                            savedLog.setDescription("Column must be empty");
                            validationImportLogRepository.save(savedLog);
                        }
                    }
                } else if (data.getTipeCif().equals("C") || data.getTipeCif().equals("c")) {

                    Map<String, Object> mandatory = new HashMap<String, Object>();
                    mandatory.put("cif", data.getCif());
                    mandatory.put("cif_join", data.getCifJoin());
                    mandatory.put("flag_nasabah", data.getFlagNasabah());
                    mandatory.put("flag_join", data.getFlagJoin());
                    mandatory.put("tipe_cif", data.getTipeCif());
                    mandatory.put("nm_lnkp_nsb", data.getNmLnkpNsb());
                    mandatory.put("kab_kota", data.getKabKota());
                    mandatory.put("flag_fraud", data.getFlagFraud());
                    mandatory.put("hub_dgn_bank", data.getHubDgnBank());
                    mandatory.put("gol_nsb", data.getGolNsb());
                    mandatory.put("kategori_usaha", data.getKategoriUsaha());
                    mandatory.put("nm_pemegang_kuasa", data.getNmLnkpPemegangKuasa());
                    mandatory.put("jns_pemegang_kuasa", data.getJnsIdentitasPemegangKuasa());
                    mandatory.put("no_pemegang_kuasa", data.getNoIdentitasPemegangKuasa());

                    Map<String, Object> empty = new HashMap<String, Object>();
                    empty.put("jns_indentitas", data.getJnsIdentitas());
                    empty.put("no_identitas", data.getNoIdentitas());
                    empty.put("ibu_kdg", data.getNmIbuKdg());
                    empty.put("tempat_lahir", data.getTmpLahir());
                    empty.put("tanggal_lahir", data.getTglLahir());
                    empty.put("kewarnegaraan", data.getKewarnegaraan());

                    Map<String, Object> optional = new HashMap<String, Object>();
                    optional.put("npwp", data.getNoNpwp());
                    optional.put("no_siup", data.getNoSiup());
                    optional.put("alamat", data.getAlamat());
                    optional.put("no_telp", data.getNoTelp());


                    for (Map.Entry<String, Object> entryMandatory : mandatory.entrySet()) {
                        if (entryMandatory.getValue() == null) {
                            ValidationImportLog savedLog = new ValidationImportLog();
                            savedLog.setRowNumber(String.valueOf(counter));
                            savedLog.setColumnName(entryMandatory.getKey());
                            savedLog.setDescription("Column cannot be blank");
                            validationImportLogRepository.save(savedLog);
                        }
                    }

                    for (Map.Entry<String, Object> entryEmpty : empty.entrySet()) {
                        if (entryEmpty.getValue() != null) {
                            ValidationImportLog savedLog = new ValidationImportLog();
                            savedLog.setRowNumber(String.valueOf(counter));
                            savedLog.setColumnName(entryEmpty.getKey());
                            savedLog.setDescription("Column must be empty");
                            validationImportLogRepository.save(savedLog);
                        }
                    }
                } else {
                    return false;
                }
            } else {
                Map<String, Object> mandatory = new HashMap<String, Object>();
                mandatory.put("cif", data.getCif());
                mandatory.put("cif_join", data.getCifJoin());
                mandatory.put("flag_nasabah", data.getFlagNasabah());
                mandatory.put("flag_join", data.getFlagJoin());

                Map<String, Object> empty = new HashMap<String, Object>();
                empty.put("nm_pemegang_kuasa", data.getNmLnkpPemegangKuasa());
                empty.put("jns_pemegang_kuasa", data.getJnsIdentitasPemegangKuasa());
                empty.put("no_pemegang_kuasa", data.getNoIdentitasPemegangKuasa());
                empty.put("tipe_cif", data.getTipeCif());
                empty.put("nm_lnkp_nsb", data.getNmLnkpNsb());
                empty.put("jns_indentitas", data.getJnsIdentitas());
                empty.put("no_identitas", data.getNoIdentitas());
                empty.put("ibu_kdg", data.getNmIbuKdg());
                empty.put("tempat_lahir", data.getTmpLahir());
                empty.put("tanggal_lahir", data.getTglLahir());
                empty.put("kab_kota", data.getKabKota());
                empty.put("kewarnegaraan", data.getKewarnegaraan());
                empty.put("flag_fraud", data.getFlagFraud());
                empty.put("hub_dgn_bank", data.getHubDgnBank());
                empty.put("gol_nsb", data.getGolNsb());
                empty.put("kategori_usaha", data.getKategoriUsaha());

                Map<String, Object> optional = new HashMap<String, Object>();
                optional.put("npwp", data.getNoNpwp());
                optional.put("no_siup", data.getNoSiup());
                optional.put("alamat", data.getAlamat());
                optional.put("no_telp", data.getNoTelp());


                for (Map.Entry<String, Object> entryMandatory : mandatory.entrySet()) {
                    if (entryMandatory.getValue() == null) {
                        ValidationImportLog savedLog = new ValidationImportLog();
                        savedLog.setRowNumber(String.valueOf(counter));
                        savedLog.setColumnName(entryMandatory.getKey());
                        savedLog.setDescription("Column cannot be blank");
                        validationImportLogRepository.save(savedLog);
                    }
                }

                for (Map.Entry<String, Object> entryEmpty : empty.entrySet()) {
                    if (entryEmpty.getValue() != null) {
                        ValidationImportLog savedLog = new ValidationImportLog();
                        savedLog.setRowNumber(String.valueOf(counter));
                        savedLog.setColumnName(entryEmpty.getKey());
                        savedLog.setDescription("Column must be empty");
                        validationImportLogRepository.save(savedLog);
                    }
                }
            }
            counter++;
        }

        long count = validationImportLogRepository.count();

        if (count != 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validationAnehCopy(List<MasterCif> dataList) {

        Integer counter = 2;
        for (MasterCif data : dataList) {
            if (data.getTipeNasabah().equals("I") || data.getTipeNasabah().equals("i")) {

                Map<String, Object> mandatory = new HashMap<String, Object>();
                mandatory.put("CIF", data.getCif());
                mandatory.put("TIPE NASABAH", data.getTipeNasabah());
                mandatory.put("NAMA LENGKAP NASABAH", data.getNmLnkpNsb());
                mandatory.put("JENIS IDENTITAS", data.getJnsIdentitas());
                mandatory.put("NO IDENTITAS", data.getNoIdentitas());
                mandatory.put("NAMA IBU KANDUNG", data.getNmIbuKdg());
                mandatory.put("TEMPAT LAHIR", data.getTmpLahir());
                mandatory.put("TANGGAL LAHIR", data.getTglLahir());
                mandatory.put("KAB KOTA", data.getKabKota());
                mandatory.put("KEWARNEGARAAN", data.getKewarnegaraan());
                mandatory.put("FLAG FRAUD", data.getFlagFraud());
                mandatory.put("HUB DGN BANK", data.getHubDgnBank());
                mandatory.put("GOLONGAN NASABAH", data.getGolNsb());
                mandatory.put("KATEGORI USAHA", data.getKategoriUsaha());

                Map<String, Object> empty = new HashMap<String, Object>();
                empty.put("NAMA PEMEGANG KUASA", data.getNmLnkpPemegangKuasa());
                empty.put("JENIS PEMEGANG KUASA", data.getJnsIdentitasPemegangKuasa());
                empty.put("NO IDENTITAS PEMEGANG KUASA", data.getNoIdentitasPemegangKuasa());

                Map<String, Object> optional = new HashMap<String, Object>();
                optional.put("NPWP", data.getNoNpwp());
                optional.put("NO SIUP", data.getNoSiup());
                optional.put("ALAMAT", data.getAlamat());
                optional.put("NO TELP", data.getNoTelp());

                for (Map.Entry<String, Object> entryMandatoryVa : mandatory.entrySet()) {
                    if (entryMandatoryVa.getValue() != null) {
                        ValidationImportLog savedLog = new ValidationImportLog();

                        if (entryMandatoryVa.getKey().equals("KAB KOTA")) {
                            String kabKota = (String) entryMandatoryVa.getValue();

                            if (sandiService.getSandi(kabKota)) {
                                ;
                            } else {
                                savedLog.setRowNumber(String.valueOf(counter));
                                savedLog.setColumnName(entryMandatoryVa.getKey());
                                savedLog.setDescription("Data KAB KOTA not found");
                                validationImportLogRepository.save(savedLog);
                            }
                        }

                        if (entryMandatoryVa.getKey().equals("TEMPAT LAHIR")) {
                            String tempatLahir = (String) entryMandatoryVa.getValue();

                            if (sandiService.getSandi(tempatLahir)) {
                                ;
                            } else {
                                savedLog.setRowNumber(String.valueOf(counter));
                                savedLog.setColumnName(entryMandatoryVa.getKey());
                                savedLog.setDescription("Data TEMPAT LAHIR not found");
                                validationImportLogRepository.save(savedLog);
                            }
                        }

                        if (entryMandatoryVa.getKey().equals("GOLONGAN NASABAH")) {
                            String golonganNasabah = (String) entryMandatoryVa.getValue();

                            if (sandiService.getSandi(golonganNasabah)) {
                                ;
                            } else {
                                savedLog.setRowNumber(String.valueOf(counter));
                                savedLog.setColumnName(entryMandatoryVa.getKey());
                                savedLog.setDescription("Data GOLONGAN NASABAH not found");
                                validationImportLogRepository.save(savedLog);
                            }
                        }
                    }
                }

                for (Map.Entry<String, Object> entryMandatory : mandatory.entrySet()) {
                    if (entryMandatory.getValue() == null) {
                        ValidationImportLog savedLog = new ValidationImportLog();
                        savedLog.setRowNumber(String.valueOf(counter));
                        savedLog.setColumnName(entryMandatory.getKey());
                        savedLog.setDescription("Column cannot be blank");
                        validationImportLogRepository.save(savedLog);
                    }
                }

                for (Map.Entry<String, Object> entryEmpty : empty.entrySet()) {
                    if (entryEmpty.getValue() != null) {
                        ValidationImportLog savedLog = new ValidationImportLog();
                        savedLog.setRowNumber(String.valueOf(counter));
                        savedLog.setColumnName(entryEmpty.getKey());
                        savedLog.setDescription("Column must be empty");
                        validationImportLogRepository.save(savedLog);
                    }
                }
            } else if (data.getTipeNasabah().equals("C") || data.getTipeNasabah().equals("c")) {

                Map<String, Object> mandatory = new HashMap<String, Object>();
                mandatory.put("CIF", data.getCif());
                mandatory.put("TIPE NASABAH", data.getTipeNasabah());
                mandatory.put("NAMA LENGKAP NASABAH", data.getNmLnkpNsb());
                mandatory.put("KAB KOTA", data.getKabKota());
                mandatory.put("FLAG FRAUD", data.getFlagFraud());
                mandatory.put("HUB DGN BANK", data.getHubDgnBank());
                mandatory.put("GOLONGAN NASABAH", data.getGolNsb());
                mandatory.put("KATEGORI USAHA", data.getKategoriUsaha());
                mandatory.put("NAMA PEMEGANG KUASA", data.getNmLnkpPemegangKuasa());
                mandatory.put("JENIS PEMEGANG KUASA", data.getJnsIdentitasPemegangKuasa());
                mandatory.put("NO IDENTITAS PEMEGANG KUASA", data.getNoIdentitasPemegangKuasa());

                Map<String, Object> empty = new HashMap<String, Object>();
                empty.put("JENIS IDENTITAS", data.getJnsIdentitas());
                empty.put("NO IDENTITAS", data.getNoIdentitas());
                empty.put("NAMA IBU KANDUNG", data.getNmIbuKdg());
                empty.put("TEMPAT LAHIR", data.getTmpLahir());
                empty.put("TANGGAL LAHIR", data.getTglLahir());
                empty.put("KEWARNEGARAAN", data.getKewarnegaraan());

                Map<String, Object> optional = new HashMap<String, Object>();
                optional.put("NPWP", data.getNoNpwp());
                optional.put("NO SIUP", data.getNoSiup());
                optional.put("ALAMAT", data.getAlamat());
                optional.put("NO TELP", data.getNoTelp());

                for (Map.Entry<String, Object> entryMandatoryVa : mandatory.entrySet()) {
                    if (entryMandatoryVa.getValue() != null) {
                        ValidationImportLog savedLog = new ValidationImportLog();

                        if (entryMandatoryVa.getKey().equals("KAB KOTA")) {
                            String kabKota = (String) entryMandatoryVa.getValue();

                            if (sandiService.getSandi(kabKota)) {
                                ;
                            } else {
                                savedLog.setRowNumber(String.valueOf(counter));
                                savedLog.setColumnName(entryMandatoryVa.getKey());
                                savedLog.setDescription("Data KAB KOTA not found");
                                validationImportLogRepository.save(savedLog);
                            }
                        }

                        if (entryMandatoryVa.getKey().equals("GOLONGAN NASABAH")) {
                            String golonganNasabah = (String) entryMandatoryVa.getValue();

                            if (sandiService.getSandi(golonganNasabah)) {
                                ;
                            } else {
                                savedLog.setRowNumber(String.valueOf(counter));
                                savedLog.setColumnName(entryMandatoryVa.getKey());
                                savedLog.setDescription("Data GOLONGAN NASABAH not found");
                                validationImportLogRepository.save(savedLog);
                            }
                        }
                    }
                }

                for (Map.Entry<String, Object> entryMandatory : mandatory.entrySet()) {
                    if (entryMandatory.getValue() == null) {
                        ValidationImportLog savedLog = new ValidationImportLog();
                        savedLog.setRowNumber(String.valueOf(counter));
                        savedLog.setColumnName(entryMandatory.getKey());
                        savedLog.setDescription("Column cannot be blank");
                        validationImportLogRepository.save(savedLog);
                    }
                }

                for (Map.Entry<String, Object> entryEmpty : empty.entrySet()) {
                    if (entryEmpty.getValue() != null) {
                        ValidationImportLog savedLog = new ValidationImportLog();
                        savedLog.setRowNumber(String.valueOf(counter));
                        savedLog.setColumnName(entryEmpty.getKey());
                        savedLog.setDescription("Column must be empty");
                        validationImportLogRepository.save(savedLog);
                    }
                }
            } else {
                return false;
            }

            counter++;
        }

        long count = validationImportLogRepository.count();

        if (count != 0) {
            return false;
        } else {
            return true;
        }
    }

    public static String convertDateString(Cell data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(data.getDateCellValue());

        return date;
    }

    public static boolean validationUpload(String flagNasabah, String tipeCif, String col, Cell colValue, Integer rowNum) {
        if (flagNasabah.equals("1")) {
            // do method 1
            checkFlagOne(col, colValue, rowNum);
        } else {
            // do method 0
            if (tipeCif.equals("I")) {
                checkFlagZero(tipeCif, col, colValue, rowNum);
            } else {
                checkFlagZeroCorporate(tipeCif, col, colValue, rowNum);
            }

        }
        return true;
    }

    public static boolean checkFlagZero(String tipeCif, String col, Cell colValue, Integer rowNum) {
        String[] mandatoryCol = {"CIF", "CIF_JOIN", "FLAG_NASABAH", "FLAG_JOIN",
                "TIPE_CIF", "NM_LNKP_NSB", "JNS_IDENTITAS", "NO_IDENTITAS", "NM_IBU_KDG",
                "TMP_LAHIR", "TGL_LAHIR", "KAB_KOTA", "KEWARGANEGARAAN", "FLAG_FRAUD", "HUB_DGN_BANK",
                "GOL_NSB", "KATEGORI_USAHA"};

        String[] emptyCol = {"NM_LNKP_PEMEGANG_KUASA", "JNS_IDENTITAS_PEMEGANG_KUASA",
                "NO_IDENTITAS_PEMEGANG_KUASA"};

        String[] optCol = {"NO_NPWP", "NO_SIUP", "ALAMAT", "NO_TELP"};

        String typeCol = null;
        for (String kolomMandatory : mandatoryCol) {
            if (kolomMandatory.equals(col)) {
                typeCol = "mandatory";
            }
        }

        for (String kolomEmpty : emptyCol) {
            if (kolomEmpty.equals(col)) {
                typeCol = "empty";
            }
        }

        for (String kolomOpt : optCol) {
            if (kolomOpt.equals(col)) {
                typeCol = "opt";
            }
        }

        if (tipeCif.equals("I")) {
            if (typeCol.equals("mandatory")) {
                if (colValue == null) {
                    throw new ErrorUploadException(String.format("Column %s row data %d can't be blank", col, rowNum));
                } else {
                    return true;
                }
            } else if (typeCol.equals("empty")) {
                if (colValue != null) {
                    throw new ErrorUploadException(String.format("Column %s row data %d must be empty", col, rowNum));
                } else {
                    return true;
                }
            } else {
                if (colValue == null) {
                    return true;
                }
            }
        }
        return true;
    }

    public static boolean checkFlagZeroCorporate(String tipeCif, String col, Cell colValue, Integer rowNum) {
        String[] mandatoryCol = {"CIF", "CIF_JOIN", "FLAG_NASABAH", "FLAG_JOIN",
                "TIPE_CIF", "NM_LNKP_NSB", "KAB_KOTA", "FLAG_FRAUD", "HUB_DGN_BANK",
                "GOL_NSB", "KATEGORI_USAHA", "NM_LNKP_PEMEGANG_KUASA", "JNS_IDENTITAS_PEMEGANG_KUASA",
                "NO_IDENTITAS_PEMEGANG_KUASA"};

        String[] emptyCol = {"JNS_IDENTITAS", "NO_IDENTITAS", "NM_IBU_KDG", "TMP_LAHIR", "TGL_LAHIR", "KEWARGANEGARAAN"};

        String[] optCol = {"NO_NPWP", "NO_SIUP", "ALAMAT", "NO_TELP"};

        String typeCol = null;
        for (String kolomMandatory : mandatoryCol) {
            if (kolomMandatory.equals(col)) {
                typeCol = "mandatory";
            }
        }

        for (String kolomEmpty : emptyCol) {
            if (kolomEmpty.equals(col)) {
                typeCol = "empty";
            }
        }

        for (String kolomOpt : optCol) {
            if (kolomOpt.equals(col)) {
                typeCol = "opt";
            }
        }

        if (tipeCif.equals("C")) {
            if (typeCol.equals("mandatory")) {
                if (colValue == null) {
                    throw new ErrorUploadException(String.format("Column %s row data %d can't be blank", col, rowNum));
                } else {
                    return true;
                }
            } else if (typeCol.equals("empty")) {
                if (colValue != null) {
                    throw new ErrorUploadException(String.format("Column %s row data %d must be empty", col, rowNum));
                } else {
                    return true;
                }
            } else {
                if (colValue == null) {
                    return true;
                }
            }
        }
        return true;
    }

    public static boolean checkFlagOne(String col, Cell colValue, Integer rowNum) {
        String[] mandatoryCol = {"CIF", "CIF_JOIN", "FLAG_NASABAH", "FLAG_JOIN"};

        String[] emptyCol = {"JNS_IDENTITAS", "NO_IDENTITAS", "NM_IBU_KDG", "TMP_LAHIR",
                "TGL_LAHIR", "NM_LNKP_NSB", "KAB_KOTA", "FLAG_FRAUD", "HUB_DGN_BANK",
                "GOL_NSB", "KATEGORI_USAHA", "NM_LNKP_PEMEGANG_KUASA", "JNS_IDENTITAS_PEMEGANG_KUASA",
                "NO_IDENTITAS_PEMEGANG_KUASA", "NO_NPWP", "NO_SIUP", "ALAMAT", "NO_TELP", "KEWARGANEGARAAN", "TIPE_CIF"};

        String[] optCol = {"nothing"};

        String typeCol = null;
        for (String kolomMandatory : mandatoryCol) {
            if (kolomMandatory.equals(col)) {
                typeCol = "mandatory";
            }
        }

        for (String kolomEmpty : emptyCol) {
            if (kolomEmpty.equals(col)) {
                typeCol = "empty";
            }
        }

        for (String kolomOpt : optCol) {
            if (kolomOpt.equals(col)) {
                typeCol = "opt";
            }
        }

        if (typeCol.equals("mandatory")) {
            if (colValue == null) {
                throw new ErrorUploadException(String.format("Column %s row data %d can't be blank", col, rowNum));
            } else {
                return true;
            }
        } else if (typeCol.equals("empty")) {
            if (colValue != null) {
                throw new ErrorUploadException(String.format("Column %s row data %d must be empty", col, rowNum));
            } else {
                return true;
            }
        } else {
            if (colValue == null) {
                return true;
            }
        }

        return true;
    }

    public static boolean checkFlagOneCorporate(String col, Cell colValue, Integer rowNum) {
        String[] mandatoryCol = {"CIF", "CIF_JOIN", "FLAG_NASABAH", "FLAG_JOIN"};

        String[] emptyCol = {"JNS_IDENTITAS", "NO_IDENTITAS", "NM_IBU_KDG", "TMP_LAHIR",
                "TGL_LAHIR", "NM_LNKP_NSB", "KAB_KOTA", "FLAG_FRAUD", "HUB_DGN_BANK",
                "GOL_NSB", "KATEGORI_USAHA", "NM_LNKP_PEMEGANG_KUASA", "JNS_IDENTITAS_PEMEGANG_KUASA",
                "NO_IDENTITAS_PEMEGANG_KUASA", "NO_NPWP", "NO_SIUP", "ALAMAT", "NO_TELP", "KEWARGANEGARAAN", "TIPE_CIF"};

        String[] optCol = {"nothing"};

        String typeCol = null;
        for (String kolomMandatory : mandatoryCol) {
            if (kolomMandatory.equals(col)) {
                typeCol = "mandatory";
            }
        }

        for (String kolomEmpty : emptyCol) {
            if (kolomEmpty.equals(col)) {
                typeCol = "empty";
            }
        }

        for (String kolomOpt : optCol) {
            if (kolomOpt.equals(col)) {
                typeCol = "opt";
            }
        }

        if (typeCol.equals("mandatory")) {
            if (colValue == null) {
                throw new ErrorUploadException(String.format("Column %s row %d can't be blank", col, rowNum));
            } else {
                return true;
            }
        } else if (typeCol.equals("empty")) {
            if (colValue != null) {
                throw new ErrorUploadException(String.format("Column %s row %d must be empty", col, rowNum));
            } else {
                return true;
            }
        } else {
            if (colValue == null) {
                return true;
            }
        }

        return true;
    }
}
