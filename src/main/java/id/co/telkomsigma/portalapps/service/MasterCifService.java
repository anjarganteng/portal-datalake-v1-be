package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.MasterCifRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.MasterCifResponseDTO;
import id.co.telkomsigma.portalapps.model.MasterCif;
import id.co.telkomsigma.portalapps.repository.MasterCifRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class MasterCifService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MasterCifService.class);

    private MasterCifRepository masterCifRepository;

    @Autowired
    public MasterCifService(
            MasterCifRepository masterCifRepository
    ) {
        this.masterCifRepository = masterCifRepository;
    }

    public void save(MasterCifRequestDTO masterCifRequestDTO) {
        MasterCif masterCif = null;
        Optional<MasterCif> masterCifOpt = masterCifRepository.findById(masterCifRequestDTO.getCif());

        if (masterCifRequestDTO.isNew()) {
            // do insert
            if (masterCifOpt.isPresent()) {
                throw new DuplicateDataException("CIF you entered already exist!");
            } else {
                if (validationInput(masterCifRequestDTO)) {

                    masterCif = new MasterCif(masterCifRequestDTO);

                    if (masterCifRequestDTO.getJnsIdentitas() != null) {
                        if (masterCifRequestDTO.getJnsIdentitas().equals("KTP")) {
                            System.out.println("KESINI");
                            if (this.validationKtp(masterCifRequestDTO.getNoIdentitas())) {
                                ;
                            } else {
                                throw new RuntimeException("Check your input KTP");
                            }
                        }
                    }
                }

            }
        } else {
            // do update
            masterCif = masterCifOpt.get();
            masterCif.setAlamat(masterCifRequestDTO.getAlamat());
            masterCif.setFlagFraud(masterCifRequestDTO.getFlagFraud());
            masterCif.setGolNsb(masterCifRequestDTO.getGolNsb());
            masterCif.setJnsIdentitas(masterCifRequestDTO.getJnsIdentitas());
            masterCif.setJnsIdentitasPemegangKuasa(masterCifRequestDTO.getJnsIdentitasPemegangKuasa());
            masterCif.setKabKota(masterCifRequestDTO.getKabKota());
            masterCif.setKategoriUsaha(masterCifRequestDTO.getKategoriUsaha());
            masterCif.setKewarnegaraan(masterCifRequestDTO.getKewarnegaraan());
            masterCif.setNoTelp(masterCifRequestDTO.getNoTelp());
            masterCif.setTglLahir(masterCifRequestDTO.getTglLahir());
            masterCif.setNoNpwp(masterCifRequestDTO.getNoNpwp());
            masterCif.setTmpLahir(masterCifRequestDTO.getTmpLahir());
            masterCif.setNmIbuKdg(masterCifRequestDTO.getNmIbuKdg());
            masterCif.setNmLnkpNsb(masterCifRequestDTO.getNmLnkpNsb());
            masterCif.setNmLnkpPemegangKuasa(masterCifRequestDTO.getNmLnkpPemegangKuasa());
            masterCif.setNoSiup(masterCifRequestDTO.getNoSiup());
            masterCif.setNoIdentitas(masterCifRequestDTO.getNoIdentitas());
            masterCif.setNoIdentitasPemegangKuasa(masterCifRequestDTO.getNoIdentitasPemegangKuasa());
            masterCif.setHubDgnBank(masterCifRequestDTO.getHubDgnBank());
            masterCif.setTipeNasabah(masterCifRequestDTO.getTipeNasabah());
        }

        masterCifRepository.save(masterCif);
        LOGGER.info("Successfully save application with id: " + masterCifRequestDTO.getCif());
    }

    public List<MasterCifResponseDTO> getAll() {
        List<MasterCifResponseDTO> masterCifResponseDTOs = null;
        List<MasterCif> masterCifs = masterCifRepository.findAll();

        if (!masterCifs.isEmpty()) {
            masterCifResponseDTOs = new ArrayList<>();
            for (MasterCif masterCif : masterCifs) {
                MasterCifResponseDTO masterCifResponseDTO = new MasterCifResponseDTO(masterCif);
                masterCifResponseDTOs.add(masterCifResponseDTO);
            }

        }

        return masterCifResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<MasterCifResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<MasterCif> dataTablesOutput = masterCifRepository.findAll(dataTablesInput);

        List<MasterCifResponseDTO> masterCifResponseDTOS = new ArrayList<>();
        for (MasterCif masterCif : dataTablesOutput.getData()) {
            MasterCifResponseDTO masterCifResponseDTO = new MasterCifResponseDTO(masterCif);

            masterCifResponseDTOS.add(masterCifResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(masterCifResponseDTOS);

        return result;
    }

    public MasterCifResponseDTO findById(String id) {
        MasterCifResponseDTO masterCifResponseDTO = null;
        Optional<MasterCif> masterCifOpt = masterCifRepository.findById(id);
        if (masterCifOpt.isPresent()) {
            MasterCif masterCif = masterCifOpt.get();

            masterCifResponseDTO = new MasterCifResponseDTO(masterCif);
        }

        return masterCifResponseDTO;
    }

    public MasterCifResponseDTO findByNoIdentitas(String noIdentitas) {
        MasterCifResponseDTO masterCifResponseDTO = null;
        Optional<MasterCif> masterCifOpt = masterCifRepository.findByNoIdentitas(noIdentitas);
        if (masterCifOpt.isPresent()) {
            MasterCif masterCif = masterCifOpt.get();

            masterCifResponseDTO = new MasterCifResponseDTO(masterCif);
        }

        return masterCifResponseDTO;
    }

    public void deleteById(String id) {
        Optional<MasterCif> masterCifOpt = masterCifRepository.findById(id);
        if (masterCifOpt.isPresent()) {
            masterCifRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<MasterCif> masterCifs = masterCifRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.masterCifToExcel(masterCifs);
        return in;
    }

    public boolean validationInput(MasterCifRequestDTO requestDTO) {
        MasterCifRequestDTO data = null;
        if (requestDTO.getTipeNasabah().equals("I")) {
            if (requestDTO.getNmLnkpNsb().equals("") || requestDTO.getNmLnkpNsb() == null) {
                throw new RuntimeException("NAMA LENGKAP NASABAH Column cannot be blank");
            }

            if (requestDTO.getJnsIdentitas().equals("") || requestDTO.getJnsIdentitas() == null) {
                throw new RuntimeException("JENIS IDENTITAS Column cannot be blank");
            }

            if (requestDTO.getNoIdentitas().equals("") || requestDTO.getNoIdentitas() == null) {
                throw new RuntimeException("NO IDENTITAS Column cannot be blank");
            }

            if (requestDTO.getNmIbuKdg().equals("") || requestDTO.getNmIbuKdg() == null) {
                throw new RuntimeException("NAMA IBU KANDUNG Column cannot be blank");
            }

            if (requestDTO.getTmpLahir().equals("") || requestDTO.getTmpLahir() == null) {
                throw new RuntimeException("TEMPAT LAHIR Column cannot be blank");
            }

//            if (requestDTO.getTglLahir().equals("")) {
//                throw new RuntimeException("TANGGAL LAHIR Column cannot be blank");
//            }

            if (requestDTO.getKabKota().equals("") || requestDTO.getKabKota() == null) {
                throw new RuntimeException("KAB KOTA Column cannot be blank");
            }

            if (requestDTO.getKewarnegaraan().equals("") || requestDTO.getKewarnegaraan() == null) {
                throw new RuntimeException("KEWARNEGARAAN Column cannot be blank");
            }

            if (requestDTO.getFlagFraud().equals("") || requestDTO.getFlagFraud() == null) {
                throw new RuntimeException("FLAG FRAUD Column cannot be blank");
            }

            if (requestDTO.getHubDgnBank().equals("") || requestDTO.getHubDgnBank() == null) {
                throw new RuntimeException("HUB DGN BANK Column cannot be blank");
            }

            if (requestDTO.getGolNsb().equals("") || requestDTO.getGolNsb() == null) {
                throw new RuntimeException("GOLONGAN NASABAH Column cannot be blank");
            }

            if (requestDTO.getKategoriUsaha().equals("") || requestDTO.getKategoriUsaha() == null) {
                throw new RuntimeException("KATEGORI USAHA Column cannot be blank");
            }

            if (requestDTO.getNmLnkpPemegangKuasa() == null) {
                ;
            }

            if (requestDTO.getJnsIdentitasPemegangKuasa() == null) {
                ;
            }

            if (requestDTO.getNoIdentitasPemegangKuasa() == null) {
                ;
            }

        } else {
            if (requestDTO.getNmLnkpNsb().equals("") || requestDTO.getNmLnkpNsb() == null) {
                throw new RuntimeException("NAMA LENGKAP NASABAH Column cannot be blank");
            }

            if (requestDTO.getJnsIdentitas() == null) {
                ;
            }

            if (requestDTO.getNoIdentitas() == null) {
                ;
            }

            if (requestDTO.getNmIbuKdg() == null) {
                ;
            }

            if (requestDTO.getTmpLahir() == null) {
                ;
            }

//            if (requestDTO.getTglLahir().equals("")) {
//                throw new RuntimeException("TANGGAL LAHIR Column cannot be blank");
//            }

            if (requestDTO.getKabKota().equals("") || requestDTO.getKabKota() == null) {
                throw new RuntimeException("KAB KOTA Column cannot be blank");
            }

            if (requestDTO.getKewarnegaraan() == null) {
                ;
            }

            if (requestDTO.getFlagFraud().equals("") || requestDTO.getFlagFraud() == null) {
                throw new RuntimeException("FLAG FRAUD Column cannot be blank");
            }

            if (requestDTO.getHubDgnBank().equals("") || requestDTO.getHubDgnBank() == null) {
                throw new RuntimeException("HUB DGN BANK Column cannot be blank");
            }

            if (requestDTO.getGolNsb().equals("") || requestDTO.getGolNsb() == null) {
                throw new RuntimeException("GOLONGAN NASABAH Column cannot be blank");
            }

            if (requestDTO.getKategoriUsaha().equals("") || requestDTO.getKategoriUsaha() == null) {
                throw new RuntimeException("KATEGORI USAHA Column cannot be blank");
            }

            if (requestDTO.getNmLnkpPemegangKuasa().equals("") || requestDTO.getNmLnkpPemegangKuasa() == null) {
                throw new RuntimeException("NAMA PEMEGANG KUASA Column cannot be blank");
            }

            if (requestDTO.getJnsIdentitasPemegangKuasa().equals("") || requestDTO.getJnsIdentitasPemegangKuasa() == null) {
                throw new RuntimeException("JENIS PEMEGANG KUASA Column cannot be blank");
            }

            if (requestDTO.getNoIdentitasPemegangKuasa().equals("") || requestDTO.getNoIdentitasPemegangKuasa() == null) {
                throw new RuntimeException("NO IDENTITAS PEMEGANG KUASA Column cannot be blank");
            }
        }
        return true;
    }

    public boolean validationKtp(String ktp) {
        boolean checkOnlyNumber = ktp.matches("[0-9]+");

        if (!checkOnlyNumber) {
            return false;
        }

        int length = ktp.length();

        if (length != 16) {
            return false;
        }

        return true;
    }

    public boolean checkKtpAlreadyExistorNot(String ktp) {
        Optional<MasterCif> dataOpt = masterCifRepository.findByNoIdentitas(ktp);

        if (dataOpt.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}

