package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.ErrorUploadException;
import id.co.telkomsigma.util.Constant;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * @author satiya
 */
@Deprecated
@Service
public class UploadService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public Environment environment;

    @Autowired
    public UploadService(
            Environment environment
    ) {
        this.environment = environment;
    }

    public void save(MultipartFile file) throws IOException {
        FTPClient con = null;

        con = new FTPClient();
        con.connect(environment.getProperty("FTP_ADDRESS"), Integer.parseInt(Objects.requireNonNull(environment.getProperty("PORT"))));

        if (con.login(environment.getProperty("LOGIN"), environment.getProperty("PSW"))) {

            for (String s : con.getReplyStrings())
                LOGGER.info(s);

            MediaType mediaType = MediaType.parseMediaType(Objects.requireNonNull(file.getContentType()));

            if (Constant.CheckMimeType.SET_OF_MIMETYPES.contains(mediaType.toString())) {
                con.enterLocalPassiveMode();
                con.setFileType(FTP.BINARY_FILE_TYPE);
                con.changeWorkingDirectory(environment.getProperty("DIRECTORY"));

                boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
                con.logout();
                con.disconnect();

                LOGGER.info("Document uploaded successfully.");

            } else {
                LOGGER.error("You can only upload xls, xlsx, files.");
                throw new ErrorUploadException("GAGAL UPLOAD");
            }
        } else {
            throw new ErrorUploadException("GAGAL LOGIN");
        }
    }
}
