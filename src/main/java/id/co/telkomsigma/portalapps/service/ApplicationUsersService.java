package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.ApplicationUsersRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.LoginRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationBranchResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationRolesResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationUsersResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.MenuCustomResponseDTO;
import id.co.telkomsigma.portalapps.model.ApplicationBranch;
import id.co.telkomsigma.portalapps.model.ApplicationRoles;
import id.co.telkomsigma.portalapps.model.ApplicationRolesMenus;
import id.co.telkomsigma.portalapps.model.ApplicationUsers;
import id.co.telkomsigma.portalapps.repository.ApplicationBranchRepository;
import id.co.telkomsigma.portalapps.repository.ApplicationRolesMenusRepository;
import id.co.telkomsigma.portalapps.repository.ApplicationRolesRepository;
import id.co.telkomsigma.portalapps.repository.ApplicationUsersRepository;
import id.co.telkomsigma.util.JwtBody;
import id.co.telkomsigma.util.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author satiya
 */
@Service
public class ApplicationUsersService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUsersService.class);

    private ApplicationUsersRepository applicationUsersRepository;
    private ApplicationRolesRepository applicationRolesRepository;
    private ApplicationBranchRepository applicationBranchRepository;
    private ApplicationRolesMenusRepository applicationRolesMenusRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret-key}")
    private String storedSecretKey;

    @Value("${jwt.expired.time}")
    private String expiredTime;


    @Autowired
    public ApplicationUsersService(
            ApplicationUsersRepository applicationUsersRepository,
            ApplicationRolesRepository applicationRolesRepository,
            ApplicationBranchRepository applicationBranchRepository,
            ApplicationRolesMenusRepository applicationRolesMenusRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.applicationUsersRepository = applicationUsersRepository;
        this.applicationRolesRepository = applicationRolesRepository;
        this.applicationBranchRepository = applicationBranchRepository;
        this.applicationRolesMenusRepository = applicationRolesMenusRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails findByEmail(String email, String requestUrl) {
        Optional<ApplicationUsers> applicationUsersOpt = applicationUsersRepository.findByEmail(email);
        if (applicationUsersOpt.isPresent()) {
            ApplicationUsers applicationUsers = applicationUsersOpt.get();

            // add roles
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

            ApplicationRoles applicationRoles = applicationUsers.getApplicationRoles();

            // List<String> permissions = extractPermission(applicationRoles);

            // tambahan sementara permission logic
            List<String> permissions = extractPermission(applicationRoles, requestUrl);

            for (String permission : permissions) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission));
            }

            return new org.springframework.security.core.userdetails.User(email, applicationUsers.getPassword(), true, true, true,
                    true, grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Email " + email + " not found!");
        }
    }


    public void save(ApplicationUsersRequestDTO applicationUsersRequestDTO) {
        ApplicationUsers applicationUsers = null;

        if (applicationUsersRequestDTO.isNew()) {
            // do insert
            applicationUsers = new ApplicationUsers(applicationUsersRequestDTO);
            applicationUsers.setPassword(passwordEncoder.encode(applicationUsersRequestDTO.getPassword()));
        } else {
            // do update
            applicationUsers = applicationUsersRepository.findByEmail(applicationUsersRequestDTO.getEmail()).get();
            applicationUsers.setFullname(applicationUsersRequestDTO.getFullname());
            applicationUsers.setPhone(applicationUsersRequestDTO.getPhone());
            applicationUsers.setPassword(passwordEncoder.encode(applicationUsersRequestDTO.getPassword()));
        }

        // load ApplicationRoles
        ApplicationRoles applicationRoles = applicationRolesRepository.findById(UUID.fromString(applicationUsersRequestDTO.getUuidRoles())).get();
        applicationUsers.setApplicationRoles(applicationRoles);


        if (applicationUsersRequestDTO.getBranchList() != null) {
            // load ApplicationBranch
            List<UUID> uuids = applicationUsersRequestDTO.getBranchList().stream().map(idStr -> UUID.fromString(idStr.getId())).collect(Collectors.toList());
            List<ApplicationBranch> applicationBranches = applicationBranchRepository.findAllById(uuids);

            applicationUsers.setBranches(new HashSet<>(applicationBranches));
        }

        ApplicationUsers saved = applicationUsersRepository.save(applicationUsers);
        LOGGER.info("Successfully save ApplicationUsers with uuid_users: " + saved.getUuidUsers());
    }

    public List<ApplicationUsersResponseDTO> getAll() {
        List<ApplicationUsersResponseDTO> applicationUsersResponseDTOs = null;
        List<ApplicationUsers> applicationUserss = applicationUsersRepository.findAll();

        if (!applicationUserss.isEmpty()) {
            applicationUsersResponseDTOs = new ArrayList<>();
            for (ApplicationUsers applicationUsers : applicationUserss) {
                // get and convert ApplicationBranch into ApplicationBranchResponseDTO
                List<ApplicationBranchResponseDTO> applicationBranchResponseDTOs = new ArrayList<>();
                for (ApplicationBranch applicationBranch : applicationUsers.getBranches()) {

                    ApplicationBranchResponseDTO applicationBranchResponseDTO = new ApplicationBranchResponseDTO(applicationBranch);

                    applicationBranchResponseDTOs.add(applicationBranchResponseDTO);
                }

                // get and convert ApplicationRoles into ApplicationRolesResponseDTO
                ApplicationRolesResponseDTO applicationRolesResponseDTO = new ApplicationRolesResponseDTO(applicationUsers.getApplicationRoles());

                // convert ApplicationUsers into ApplicationUsersResponseDTO
                ApplicationUsersResponseDTO applicationUsersResponseDTO = new ApplicationUsersResponseDTO(applicationUsers);
                applicationUsersResponseDTO.setRoles(applicationRolesResponseDTO); // set ApplicationRolesResponseDTO into ApplicationUsersResponseDTO
                applicationUsersResponseDTO.setBranchList(applicationBranchResponseDTOs);

                applicationUsersResponseDTOs.add(applicationUsersResponseDTO);
            }
        }

        return applicationUsersResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ApplicationUsersResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ApplicationUsers> dataTablesOutput = applicationUsersRepository.findAll(dataTablesInput);

        List<ApplicationUsersResponseDTO> applicationUsersResponseDTOs = new ArrayList<>();
        for (ApplicationUsers applicationUsers : dataTablesOutput.getData()) {
            // get and convert ApplicationBranch into ApplicationBranchResponseDTO
            List<ApplicationBranchResponseDTO> applicationBranchResponseDTOs = new ArrayList<>();
            for (ApplicationBranch applicationBranch : applicationUsers.getBranches()) {

                ApplicationBranchResponseDTO applicationBranchResponseDTO = new ApplicationBranchResponseDTO(applicationBranch);

                applicationBranchResponseDTOs.add(applicationBranchResponseDTO);
            }

//            // get and convert ApplicationPermission into ApplicationPermissionResponseDTO
//            List<ApplicationPermissionResponseDTO> applicationPermissionResponseDTOs = new ArrayList<>();
//            for (ApplicationPermission applicationPermission : applicationUsers.getApplicationRoles().getPermissions()) {
//
//                ApplicationPermissionResponseDTO applicationPermissionResponseDTO = new ApplicationPermissionResponseDTO(applicationPermission);
//
//                applicationPermissionResponseDTOs.add(applicationPermissionResponseDTO);
//            }

            // get and convert ApplicationRoles into ApplicationRolesResponseDTO
            ApplicationRolesResponseDTO applicationRolesResponseDTO = new ApplicationRolesResponseDTO(applicationUsers.getApplicationRoles());

            // convert ApplicationUsers into ApplicationUsersResponseDTO
            ApplicationUsersResponseDTO applicationUsersResponseDTO = new ApplicationUsersResponseDTO(applicationUsers);
            applicationUsersResponseDTO.setRoles(applicationRolesResponseDTO); // set ApplicationRolesResponseDTO into ApplicationUsersResponseDTO
            applicationUsersResponseDTO.setBranchList(applicationBranchResponseDTOs);

            applicationUsersResponseDTOs.add(applicationUsersResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(applicationUsersResponseDTOs);

        return result;
    }

    public ApplicationUsersResponseDTO findById(String uuidUsers) {
        ApplicationUsersResponseDTO applicationUsersResponseDTO = null;
        Optional<ApplicationUsers> applicationUsersOpt = applicationUsersRepository.findById(UUID.fromString(uuidUsers));
        if (applicationUsersOpt.isPresent()) {
            ApplicationUsers applicationUsers = applicationUsersOpt.get();

            // get and convert ApplicationBranch into ApplicationBranchResponseDTO
            List<ApplicationBranchResponseDTO> applicationBranchResponseDTOs = new ArrayList<>();
            for (ApplicationBranch applicationBranch : applicationUsers.getBranches()) {

                ApplicationBranchResponseDTO applicationBranchResponseDTO = new ApplicationBranchResponseDTO(applicationBranch);

                applicationBranchResponseDTOs.add(applicationBranchResponseDTO);
            }

            // get and convert ApplicationRoles into ApplicationRolesResponseDTO
            ApplicationRolesResponseDTO applicationRolesResponseDTO = new ApplicationRolesResponseDTO(applicationUsers.getApplicationRoles());
//            applicationRolesResponseDTO.setPermissionList(applicationPermissionResponseDTOs);

            applicationUsersResponseDTO = new ApplicationUsersResponseDTO(applicationUsers);
            applicationUsersResponseDTO.setRoles(applicationRolesResponseDTO); // set ApplicationRolesResponseDTO into ApplicationUsersResponseDTO
            applicationUsersResponseDTO.setBranchList(applicationBranchResponseDTOs);
        }

        return applicationUsersResponseDTO;
    }

    public ApplicationUsers getByEmail(String email) {
        ApplicationUsers applicationUsers = null;

        Optional<ApplicationUsers> applicationUsersOpt = applicationUsersRepository.findByEmail(email);

        if (applicationUsersOpt.isPresent()) {
            applicationUsers = applicationUsersOpt.get();
        }

        return applicationUsers;
    }

    public void deleteById(String uuidUsers) {
        Optional<ApplicationUsers> applicationUsersOpt = applicationUsersRepository.findById(UUID.fromString(uuidUsers));
        if (applicationUsersOpt.isPresent()) {
            applicationUsersRepository.deleteById(UUID.fromString(uuidUsers));
            LOGGER.info("Sucessfully delete ApplicationUsers with uuid_users: " + uuidUsers);
        } else {
            LOGGER.info("Cannot find ApplicationUsers with uuid_users: " + uuidUsers);
        }
    }

    public ResponseEntity<?> login(LoginRequestDTO loginRequestDTO) {
        try {
            Optional<ApplicationUsers> applicationUsersOpt = applicationUsersRepository.findByEmail(loginRequestDTO.getEmail());
            String secretKey = Base64.getEncoder().encodeToString(storedSecretKey.getBytes());
            if (applicationUsersOpt.isPresent()) {
                ApplicationUsers applicationUsers = applicationUsersOpt.get();
                if (passwordEncoder.matches(loginRequestDTO.getPassword(), applicationUsers.getPassword())) {
                    JwtBody body = new JwtBody(applicationUsers);
                    String jwt = Jwts.builder().setClaims(body.toMap()).setSubject(applicationUsers.getEmail())
                            .setExpiration(createExpiredDate()).signWith(SignatureAlgorithm.HS256, secretKey).compact();
                    Map<String, String> jwtMap = new HashMap<String, String>();
                    jwtMap.put("jwt", jwt);
                    jwtMap.put("email", loginRequestDTO.getEmail());

                    return new ResponseEntity<>(Response.success(jwtMap), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(Response.failed("Password Incorrect!"), HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(Response.failed("Username Incorrect!"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // menambahkan sementara parameter baru di method requestUrl
    public List<String> extractPermission(ApplicationRoles applicationRoles, String requestUrl) {
        // tambahan sementara logic permission
        String[] urls = requestUrl.split("/");
        String modul = urls[4];
        ApplicationRolesMenus applicationRolesMenus = null;
        applicationRolesMenus = applicationRolesMenusRepository.findByUrl(modul, applicationRoles.getUuidRoles().toString()).get();

        List<String> permissions = new ArrayList<>();

        if (applicationRolesMenus.isCanRead()) {
            if (!permissions.contains("READ_PRIVILEGE")) {
                permissions.add("READ_PRIVILEGE");
            }
        }

        if (applicationRolesMenus.isCanUpdate()) {
            if (!permissions.contains("UPDATE_PRIVILEGE")) {
                permissions.add("UPDATE_PRIVILEGE");
            }
        }

        if (applicationRolesMenus.isCanDownload()) {
            if (!permissions.contains("DOWNLOAD_PRIVILEGE")) {
                permissions.add("DOWNLOAD_PRIVILEGE");
            }
        }

        if (applicationRolesMenus.isCanUpload()) {
            if (!permissions.contains("UPLOAD_PRIVILEGE")) {
                permissions.add("UPLOAD_PRIVILEGE");
            }
        }

        if (applicationRolesMenus.isCanRegenerate()) {
            if (!permissions.contains("REGENERATE_PRIVILEGE")) {
                permissions.add("REGENERATE_PRIVILEGE");
            }
        }

        return permissions;


        // logic lama permission sementara dimatikan
//        for (ApplicationRolesMenus data : applicationRoles.getRoleMenus()) {
//
//
//            if (data.isCanRead()) {
//                if (!permissions.contains("READ_PRIVILEGE")) {
//                    permissions.add("READ_PRIVILEGE");
//                }
//            }
//
//            if (data.isCanUpdate()) {
//                if (!permissions.contains("UPDATE_PRIVILEGE")) {
//                    permissions.add("UPDATE_PRIVILEGE");
//                }
//            }
//
//            if (data.isCanDownload()) {
//                if (!permissions.contains("DOWNLOAD_PRIVILEGE")) {
//                    permissions.add("DOWNLOAD_PRIVILEGE");
//                }
//            }
//
//            if (data.isCanUpload()) {
//                if (!permissions.contains("UPLOAD_PRIVILEGE")) {
//                    permissions.add("UPLOAD_PRIVILEGE");
//                }
//            }
//
//            if (data.isCanRegenerate()) {
//                if (!permissions.contains("REGENERATE_PRIVILEGE")) {
//                    permissions.add("REGENERATE_PRIVILEGE");
//                }
//            }
//
//        }
    }

    protected Date createExpiredDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.valueOf(expiredTime));
        return calendar.getTime();
    }

    public JSONObject readJsonAn(String[] menuAntasena) throws IOException, ParseException {
        JSONObject objAn = new JSONObject();

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/menuPortal.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject an = (JSONObject) jsonObject.get("an");
        JSONArray anSubmenu = (JSONArray) an.get("submenu");

        JSONArray cleanSubmenu = new JSONArray();
        for (Object o : anSubmenu) {
            JSONObject anSubmenuSubmenu = (JSONObject) o;
            JSONArray anSubmenuSubmenuNext = (JSONArray) anSubmenuSubmenu.get("submenu");

            for (Object oNext : anSubmenuSubmenuNext) {
                JSONObject oNextObj = (JSONObject) oNext;


                boolean doesContain = Arrays.stream(menuAntasena).anyMatch(x -> x.equals(oNextObj.get("link")));

                if (doesContain) {
                    cleanSubmenu.add(oNextObj);
                }
            }
            break;
        }

        boolean containUpload = Arrays.stream(menuAntasena).anyMatch(x -> x.equals("/anuploadfiles"));

        JSONObject uploadObj = new JSONObject();
        if (containUpload) {
            uploadObj.put("text", "Upload Antasena");
            uploadObj.put("link", "/anuploadfiles");
        }


        JSONArray arrHardCodeAn = new JSONArray();
        JSONObject objHardCodeAn = new JSONObject();

        objHardCodeAn.put("text", "Entry Form");
        objHardCodeAn.put("submenu", cleanSubmenu);

        arrHardCodeAn.add(objHardCodeAn);
        arrHardCodeAn.add(uploadObj);

        objAn.put("text", "ANTASENA");
        objAn.put("submenu", arrHardCodeAn);

        return (JSONObject) objAn;
    }

    public JSONObject readJsonLps(String[] menuLps) throws IOException, ParseException {
        JSONObject objAn = new JSONObject();

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/menuPortal.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject an = (JSONObject) jsonObject.get("lps");
        JSONArray anSubmenu = (JSONArray) an.get("submenu");

        JSONArray cleanSubmenu = new JSONArray();
        for (Object o : anSubmenu) {
            JSONObject anSubmenuSubmenu = (JSONObject) o;
            JSONArray anSubmenuSubmenuNext = (JSONArray) anSubmenuSubmenu.get("submenu");

            for (Object oNext : anSubmenuSubmenuNext) {
                JSONObject oNextObj = (JSONObject) oNext;


                boolean doesContain = Arrays.stream(menuLps).anyMatch(x -> x.equals(oNextObj.get("link")));

                if (doesContain) {
                    cleanSubmenu.add(oNextObj);
                }
            }
            break;
        }

        boolean containUpload = Arrays.stream(menuLps).anyMatch(x -> x.equals("/anuploadlps"));
        boolean containValidation = Arrays.stream(menuLps).anyMatch(x -> x.equals("/an-validation-log"));

        JSONObject uploadObj = new JSONObject();
        if (containUpload) {
            uploadObj.put("text", "Upload Lps");
            uploadObj.put("link", "/anuploadlps");
        }

        JSONObject uploadValidation = new JSONObject();
        if (containValidation) {
            uploadValidation.put("text", "Validation Master Cif");
            uploadValidation.put("link", "/an-validation-log");
        }


        JSONArray arrHardCodeAn = new JSONArray();
        JSONObject objHardCodeAn = new JSONObject();

        objHardCodeAn.put("text", "Entry Form");
        objHardCodeAn.put("submenu", cleanSubmenu);

        arrHardCodeAn.add(objHardCodeAn);
        arrHardCodeAn.add(uploadObj);
        arrHardCodeAn.add(uploadValidation);

        objAn.put("text", "LPS");
        objAn.put("submenu", arrHardCodeAn);

        return (JSONObject) objAn;
    }

    public JSONObject readJsonParameter(String[] menuParameter) throws IOException, ParseException {
        JSONObject objAn = new JSONObject();

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/menuPortal.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject an = (JSONObject) jsonObject.get("par");
        JSONArray anSubmenu = (JSONArray) an.get("submenu");

        JSONArray cleanSubmenu = new JSONArray();
        for (Object o : anSubmenu) {
            JSONObject anSubmenuSubmenu = (JSONObject) o;
            JSONArray anSubmenuSubmenuNext = (JSONArray) anSubmenuSubmenu.get("submenu");

            for (Object oNext : anSubmenuSubmenuNext) {
                JSONObject oNextObj = (JSONObject) oNext;


                boolean doesContain = Arrays.stream(menuParameter).anyMatch(x -> x.equals(oNextObj.get("link")));

                if (doesContain) {
                    cleanSubmenu.add(oNextObj);
                }
            }
            break;
        }

        boolean containUpload = Arrays.stream(menuParameter).anyMatch(x -> x.equals("/anuploadparameter"));

        JSONObject uploadObj = new JSONObject();
        if (containUpload) {
            uploadObj.put("text", "Upload Parameter");
            uploadObj.put("link", "/anuploadparameter");
        }

        JSONArray arrHardCodeAn = new JSONArray();
        JSONObject objHardCodeAn = new JSONObject();

        objHardCodeAn.put("text", "Entry Form");
        objHardCodeAn.put("submenu", cleanSubmenu);

        arrHardCodeAn.add(objHardCodeAn);
        arrHardCodeAn.add(uploadObj);

        objAn.put("text", "PARAMETER");
        objAn.put("submenu", arrHardCodeAn);

        return (JSONObject) objAn;
    }

    public JSONObject readJsonSettings(String[] menuSettings) throws IOException, ParseException {
        JSONObject objAn = new JSONObject();

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/menuPortal.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject an = (JSONObject) jsonObject.get("user");
        JSONArray anSubmenu = (JSONArray) an.get("submenu");

        JSONArray cleanSubmenu = new JSONArray();
        for (Object o : anSubmenu) {
            JSONObject anSubmenuSubmenu = (JSONObject) o;

            boolean doesContain = Arrays.stream(menuSettings).anyMatch(x -> x.equals(anSubmenuSubmenu.get("link")));

            if (doesContain) {
                cleanSubmenu.add(anSubmenuSubmenu);
            }
        }

        boolean containRole = Arrays.stream(menuSettings).anyMatch(x -> x.equals("/an-application-roles"));

        if (containRole) {
            JSONObject objPrivileges = new JSONObject();
            objPrivileges.put("text", "Privileges");
            objPrivileges.put("link", "/an-privileges");

            cleanSubmenu.add(objPrivileges);
        }

        objAn.put("text", "Settings");
        objAn.put("submenu", cleanSubmenu);

        return (JSONObject) objAn;
    }
    
    public JSONObject readJsonBot(String[] menuBot) throws IOException, ParseException {
        JSONObject objAn = new JSONObject();

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/menuPortal.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject an = (JSONObject) jsonObject.get("bot");
        JSONArray anSubmenu = (JSONArray) an.get("submenu");

        JSONArray cleanSubmenu = new JSONArray();
        for (Object o : anSubmenu) {
            JSONObject anSubmenuSubmenu = (JSONObject) o;
            JSONArray anSubmenuSubmenuNext = (JSONArray) anSubmenuSubmenu.get("submenu");
            for (Object oNext : anSubmenuSubmenuNext) {
                JSONObject oNextObj = (JSONObject) oNext;
                boolean doesContain = Arrays.stream(menuBot).anyMatch(x -> x.equals(oNextObj.get("link")));
                if (doesContain) {
                    cleanSubmenu.add(oNextObj);
                }
            }
            break;
        }

        boolean containUpload = Arrays.stream(menuBot).anyMatch(x -> x.equals("/anuploadbot"));
        
        JSONObject uploadObj = new JSONObject();
        if (containUpload) {
            uploadObj.put("text", "Upload BOT");
            uploadObj.put("link", "/anuploadbot");
        }

        JSONArray arrHardCodeAn = new JSONArray();
        JSONObject objHardCodeAn = new JSONObject();

        
        JSONObject objKeyManagementParent = new JSONObject();
        JSONArray keyManagementSubmenu = new JSONArray();
        JSONObject keyManagementObj1 = new JSONObject();
        JSONObject keyManagementObj2 = new JSONObject();
        JSONObject keyManagementObj3 = new JSONObject();
        JSONObject keyManagementObj4 = new JSONObject();
        
        keyManagementObj1.put("text", "Short Term Employee Benefits");
        keyManagementObj1.put("link", "/an-short-term-employee-benefits");
        keyManagementObj2.put("text", "Post Employment Benefits Employee Id");
        keyManagementObj2.put("link", "/an-post-employment-benefits-employee-id");
        keyManagementObj3.put("text", "Post Employment Benefits");
        keyManagementObj3.put("link", "/an-post-employment-benefits");
        keyManagementObj4.put("text", "Other Benefits Paid");
        keyManagementObj4.put("link", "/an-other-benefits-paid");
        keyManagementSubmenu.add(keyManagementObj1);
        keyManagementSubmenu.add(keyManagementObj2);
        keyManagementSubmenu.add(keyManagementObj3);
        keyManagementSubmenu.add(keyManagementObj4);
        
        objKeyManagementParent.put("text", "Key Management Personal");
        objKeyManagementParent.put("submenu", keyManagementSubmenu);
        
        
        
        cleanSubmenu.add(objKeyManagementParent);
        objHardCodeAn.put("text", "Entry Form");
        objHardCodeAn.put("submenu", cleanSubmenu);
        
        

        arrHardCodeAn.add(objHardCodeAn);
        arrHardCodeAn.add(uploadObj);

        objAn.put("text", "BOT");
        objAn.put("submenu", arrHardCodeAn);

        return (JSONObject) objAn;
    }
    
    public JSONObject readJsonLog(String[] menuLog) throws IOException, ParseException {
        JSONObject objAn = new JSONObject();

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/menuPortal.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject an = (JSONObject) jsonObject.get("log");
        JSONArray anSubmenu = (JSONArray) an.get("submenu");

        JSONArray cleanSubmenuAntasena = new JSONArray();
        JSONArray cleanSubmenuBot = new JSONArray();
        for (Object o : anSubmenu) {
            JSONObject anSubmenuSubmenu = (JSONObject) o;
            String textLabel = (String) anSubmenuSubmenu.get("text");
            JSONArray anSubmenuSubmenuNext = (JSONArray) anSubmenuSubmenu.get("submenu");

            for (Object oNext : anSubmenuSubmenuNext) {
                JSONObject oNextObj = (JSONObject) oNext;
                JSONArray anSubmenuNext = (JSONArray) oNextObj.get("submenu");

                for (Object oNextNext : anSubmenuNext) {
                    JSONObject oDa = (JSONObject) oNextNext;

                    boolean doesContain = Arrays.stream(menuLog).anyMatch(x -> x.equals(oDa.get("link")));

                    if (doesContain) {
                        if("ANTASENA".equals(textLabel)) cleanSubmenuAntasena.add(oDa);
                        if("BOT".equals(textLabel)) cleanSubmenuBot.add(oDa);
                    }
                }
                break;
            }
        }

        boolean containRegenerate = Arrays.stream(menuLog).anyMatch(x -> x.equals("/anregeneratelog"));

        JSONArray arrHardCodeAn = new JSONArray();
        JSONObject objHardCodeAntasena = new JSONObject();
        JSONObject objHardCodeBot = new JSONObject();
        JSONObject arrProcessAntasena = new JSONObject();
        JSONObject arrProcessBot = new JSONObject();
        JSONArray arrCleanAntasena = new JSONArray();
        JSONArray arrCleanBot = new JSONArray();

        if (containRegenerate) {
            JSONObject objRegenerate = new JSONObject();
            objRegenerate.put("text", "Regenerate Log");
            objRegenerate.put("link", "/anregeneratelog");
            arrCleanAntasena.add(objRegenerate);
        }

        arrProcessAntasena.put("text", "Process");
        arrProcessAntasena.put("submenu", cleanSubmenuAntasena);
        arrCleanAntasena.add(arrProcessAntasena);
        
        arrProcessBot.put("text", "Process");
        arrProcessBot.put("submenu", cleanSubmenuBot);
        arrCleanBot.add(arrProcessBot);
        
        objHardCodeAntasena.put("text", "ANTASENA");
        objHardCodeAntasena.put("submenu", arrCleanAntasena);

        objHardCodeBot.put("text", "BOT");
        objHardCodeBot.put("submenu", arrCleanBot);

        arrHardCodeAn.add(objHardCodeAntasena);
        arrHardCodeAn.add(objHardCodeBot);

        objAn.put("text", "LOG & PROCESSING");
        objAn.put("submenu", arrHardCodeAn);

        return (JSONObject) objAn;
    }

    public MenuCustomResponseDTO menuCustom(String email) throws IOException, ParseException {
        Optional<ApplicationUsers> applicationUsersOpt = applicationUsersRepository.findByEmail(email);

        MenuCustomResponseDTO menuCustomResponseDTO = new MenuCustomResponseDTO();

        if (applicationUsersOpt.isPresent()) {
            ApplicationUsers applicationUsers = applicationUsersOpt.get();

            String[] menuAntasena = applicationRolesMenusRepository.findMenuLinkTabelAntasena(applicationUsers.getApplicationRoles().getUuidRoles().toString());
            String[] menuLps = applicationRolesMenusRepository.findMenuLinkTabelLps(applicationUsers.getApplicationRoles().getUuidRoles().toString());
            String[] menuParameter = applicationRolesMenusRepository.findMenuLinkTabelParameter(applicationUsers.getApplicationRoles().getUuidRoles().toString());
            String[] menuSettings = applicationRolesMenusRepository.findMenuLinkTabelSettings(applicationUsers.getApplicationRoles().getUuidRoles().toString());
            String[] menuLog = applicationRolesMenusRepository.findMenuLinkTabelLog(applicationUsers.getApplicationRoles().getUuidRoles().toString());
            String[] menuBot = applicationRolesMenusRepository.findMenuLinkTabelBot(applicationUsers.getApplicationRoles().getUuidRoles().toString());
 
            menuCustomResponseDTO.setAn(readJsonAn(menuAntasena));
            menuCustomResponseDTO.setLps(readJsonLps(menuLps));
            menuCustomResponseDTO.setPar(readJsonParameter(menuParameter));
            menuCustomResponseDTO.setUser(readJsonSettings(menuSettings));
            menuCustomResponseDTO.setLog(readJsonLog(menuLog));
            menuCustomResponseDTO.setBot(readJsonBot(menuBot));
        }

        return menuCustomResponseDTO;
    }

}
