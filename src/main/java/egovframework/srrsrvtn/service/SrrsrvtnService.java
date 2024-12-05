package egovframework.srrsrvtn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import egovframework.srrsrvtn.vo.SrrsrvtnAttchVO;
import egovframework.srrsrvtn.vo.SrrsrvtnVO;

public interface SrrsrvtnService {
    public int selectRsrvSeq() throws Exception ;
    public List<Map<String,Object>> getEqpmntRfrnc() throws Exception;
    public List<Map<String,Object>> getPrcRfrnc() throws Exception;
    public List<SrrsrvtnVO> getAdminList(Map<String, Object> data) throws Exception;
    public List<SrrsrvtnVO> getAdminListExcel(Map<String, Object> data) throws Exception;
    public List<Map<String,Object>> getCmmnCode() throws Exception;
    public List<SrrsrvtnVO> getSrRsrvtnList(SrrsrvtnVO param) throws Exception;
    public List<SrrsrvtnVO> getRsrvYn(SrrsrvtnVO data) throws Exception;
    public SrrsrvtnVO getRsrvSessionInfo(SrrsrvtnVO param) throws Exception;
    public void sendMMS(Map<String,Object> param) throws Exception;
    public int updateRsrvList(Map<String, Object> data) throws Exception;
    public int insertSrrsrvtn(Map<String, Object> data) throws Exception;
    public int insertHistory(Map<String, Object> data) throws Exception;
    public void saveAgree(Map<String, Object> param) throws Exception;
    public int checkAgree(Map<String, Object> param) throws Exception;
    public SrrsrvtnAttchVO getLastAttchFile(String param) throws Exception;
    public HashMap<String, Object> multiRsrvtn(List<HashMap<String, Object>> srrsrvtnList, MultipartFile file, List<MultipartFile> fileList) throws Exception;
    public HashMap<String, Object> updateStatusList(Map<String, Object> data) throws Exception;
    public int deleteUserInfo(Map<String, Object> data) throws Exception;
    public SrrsrvtnVO getAdmin(Map<String, Object> data) throws Exception;
    public List<SrrsrvtnVO> getSrRsrvtnListGrp(SrrsrvtnVO data) throws Exception;
    public HashMap<String, Object> multiRsrvtn(Map<String, Object> data) throws Exception;
}
