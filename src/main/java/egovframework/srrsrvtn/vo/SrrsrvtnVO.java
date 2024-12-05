package egovframework.srrsrvtn.vo;

import java.io.Serializable;
import java.util.List;

public class SrrsrvtnVO  implements Serializable {
    private String evntNm;
    private String evntDesc;
    private String orgNm;
    private String comRgstNo;
    private String comRgstNoFileNm;
    private String rprsntrNm;
    private String mngrNm;
    private String bizType;
    private String bizType2;
    private String telNo;
    private String faxNo;
    private String address;
    private String email;
    private String evntDt;
    private String evntDtCd;
    private String isTemp;
    private String hallType;
    private String hallTypeCd;
    private Integer seq;
    private Integer rentPay;
    private Integer mngPay;
    private Integer eqpmntPay;
    private Integer totalPay;
    private String rsrvDtStart;
    private String status;
    private String statusCd;
    private String userId;
    private String inDt;
    private String modiDt;
    private String iscorrdr;
    private Integer wlsMic;
    private Integer wMic;
    private Integer bAmp;
    private Integer prjctr;
    private Integer tbl;
    private Integer chr;
    private Integer grbgPck;
    private Integer grpId;
    private String btchStyl;
    private String rsrvDtEnd;
    private String etc;
    private String applyDt;
    private String rsrvDt;
    private String rsrvDtFrom;
    private String rsrvDtTo;
    private String hallType1Yn;
    private String hallType2Yn;
    private String hallType3Yn;
    private String modiYn;
    private String addressDtl;
    private String stts;
    private String act;
    private String etc1;
    private Integer discount;
    
    
    public Integer getDiscount() {
        return discount;
    }
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    public String getEtc1() {
        return etc1;
    }
    public void setEtc1(String etc1) {
        this.etc1 = etc1;
    }
    // 장비 사용료
    private List<SreqpmntpayrfrncVO> sreqpmntpayrfrnc;
    // 전시실 임대료 & 관리비
    private List<SrprcrfrncVO> srprcrfrnc;

    
    
    public String getIscorrdr() {
        return iscorrdr;
    }
    public void setIscorrdr(String iscorrdr) {
        this.iscorrdr = iscorrdr;
    }
    public String getEvntDt() {
        return evntDt;
    }
    public void setEvntDt(String evntDt) {
        this.evntDt = evntDt;
    }
    public String getEvntDtCd() {
        return evntDtCd;
    }
    public void setEvntDtCd(String evntDtCd) {
        this.evntDtCd = evntDtCd;
    }
    @Override
    public String toString() {
        return "SrrsrvtnVO [address=" + address + ", applyDt=" + applyDt + ", bizType=" + bizType + ", bizType2="
                + bizType2 + ", comRgstNo=" + comRgstNo + ", email=" + email + ", eqpmntPay=" + eqpmntPay + ", comRgstNoFileNm=" + comRgstNoFileNm 
                + ", evntDesc=" + evntDesc + ", evntNm=" + evntNm + ", faxNo=" + faxNo + ", hallType=" + hallType + ", hallTypeNm="
                + hallTypeCd + ", isTemp=" + isTemp + ", mngPay=" + mngPay + ", mngrNm=" + mngrNm + ", orgNm=" + orgNm + ", rentPay="
                + rentPay + ", rprsntrNm=" + rprsntrNm + ", rsrvDt=" + rsrvDt + ", seq=" + seq + ", status=" + status + ", statusCd" + statusCd
                + ", telNo=" + telNo + ", totalPay=" + totalPay + ", userId=" + userId + ", inDt=" + inDt + ", modiDt="
                + modiDt + ", iscorrdr=" + iscorrdr + ", evntDt=" + evntDt + ", evntDtCd=" + evntDtCd
                + ", rsrvDtStart=" + rsrvDtStart + ", wlsMic=" + wlsMic + ", wMic=" + wMic + ", bAmp=" + bAmp
                + ", prjctr=" + prjctr + ", tbl=" + tbl + ", chr=" + chr + ", grbgPck=" + grbgPck
                + ", btchStyl=" + btchStyl + ", rsrvDtEnd=" + rsrvDtEnd + ", etc=" + etc
                + ", sreqpmntpayrfrnc=" + sreqpmntpayrfrnc + ", srprcrfrnc=" + srprcrfrnc + ", hallTypeCd=" + hallTypeCd
                + ", getIscorrdr()=" + getIscorrdr() + ", getEvntDt()=" + getEvntDt() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", getAddress()=" + getAddress()
                + ", getApplyDt()=" + getApplyDt() + ", getBizType()=" + getBizType() + ", getBizType2()="
                + getBizType2() + ", getComRgstNo()=" + getComRgstNo() + ", getComRgstNoFileNm()=" + getComRgstNoFileNm() + ", getEmail()=" + getEmail()
                + ", getEqpmntPay()=" + getEqpmntPay() + ", getEvntDesc()=" + getEvntDesc() + ", getEvntNm()="
                + getEvntNm() + ", getFaxNo()=" + getFaxNo() + ", getHallType()=" + getHallType() + ", getHallTypeCd()=" + getHallTypeCd()
                + ", getIsTemp()=" + getIsTemp() + ", getMngPay()=" + getMngPay() + ", getMngrNm()=" + getMngrNm() + ", getOrgNm()="
                + getOrgNm() + ", getRentPay()=" + getRentPay() + ", getRprsntrNm()=" + getRprsntrNm()
                + ", getRsrvDt()=" + getRsrvDt() + ", getSeq()=" + getSeq() + ", getStatus()=" + getStatus() + ", getStatusCd()=" + getStatusCd()
                + ", getTelNo()=" + getTelNo() + ", getTotalPay()=" + getTotalPay() + ", getUserId()=" + getUserId()
                + ", getRsrvDtStart()=" + getRsrvDtStart() + ", getWlsMic()=" + getWlsMic()
                + ", getwMic()=" + getwMic() + ", getBAmp()=" + getBAmp() + ", getPrjctr()=" + getPrjctr() + ", getTbl()=" + getTbl()
                + ", getChr()=" + getChr() + ", getGrbgPck()=" + getGrbgPck() + ", getModiYn()=" + getModiYn()
                + ", getBtchStyl()=" + getBtchStyl() + ", getRsrvDtEnd()=" + getRsrvDtEnd() + ", getAddressDtl()=" + getAddressDtl()
                + ", getSreqpmntpayrfrnc()=" + getSreqpmntpayrfrnc() + ", getSrprcrfrnc()=" + getSrprcrfrnc()
                + ", getHallTypeCd()=" + getHallTypeCd() + ", getEvntDtCd()=" + getEvntDtCd()
                + ", getInDt()=" + getInDt() + ", getModiDt()=" + getModiDt() + ", toString()=" + super.toString() + "]";
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getApplyDt() {
        return applyDt;
    }
    public void setApplyDt(String applyDt) {
        this.applyDt = applyDt;
    }
    public String getBizType() {
        return bizType;
    }
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
    public String getBizType2() {
        return bizType2;
    }
    public void setBizType2(String bizType2) {
        this.bizType2 = bizType2;
    }
    public String getComRgstNo() {
        return comRgstNo;
    }
    public void setComRgstNo(String comRgstNo) {
        this.comRgstNo = comRgstNo;
    }
    public String getComRgstNoFileNm() {
        return comRgstNoFileNm;
    }
    public void setComRgstNoFileNm(String comRgstNoFileNm) {
        this.comRgstNoFileNm = comRgstNoFileNm;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getEqpmntPay() {
        return eqpmntPay;
    }
    public void setEqpmntPay(Integer eqpmntPay) {
        this.eqpmntPay = eqpmntPay;
    }
    public String getEvntDesc() {
        return evntDesc;
    }
    public void setEvntDesc(String evntDesc) {
        this.evntDesc = evntDesc;
    }
    public String getEvntNm() {
        return evntNm;
    }
    public void setEvntNm(String evntNm) {
        this.evntNm = evntNm;
    }
    public String getFaxNo() {
        return faxNo;
    }
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }
    public String getHallType() {
        return hallType;
    }
    public void setHallType(String hallType) {
        this.hallType = hallType;
    }
    public String getHallTypeCd() {
        return hallTypeCd;
    }
    public void setHallTypeCd(String hallTypeCd) {
        this.hallTypeCd = hallTypeCd;
    }
    public String getIsTemp() {
        return isTemp;
    }
    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp;
    }
    public Integer getMngPay() {
        return mngPay;
    }
    public void setMngPay(Integer mngPay) {
        this.mngPay = mngPay;
    }
    public String getMngrNm() {
        return mngrNm;
    }
    public void setMngrNm(String mngrNm) {
        this.mngrNm = mngrNm;
    }
    public String getOrgNm() {
        return orgNm;
    }
    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }
    public Integer getRentPay() {
        return rentPay;
    }
    public void setRentPay(Integer rentPay) {
        this.rentPay = rentPay;
    }
    public String getRprsntrNm() {
        return rprsntrNm;
    }
    public void setRprsntrNm(String rprsntrNm) {
        this.rprsntrNm = rprsntrNm;
    }
    public String getRsrvDt() {
        return rsrvDt;
    }
    public void setRsrvDt(String rsrvDt) {
        this.rsrvDt = rsrvDt;
    }
    public String getRsrvDtFrom() {
        return rsrvDtFrom;
    }
    public void setRsrvDtFrom(String rsrvDtFrom) {
        this.rsrvDtFrom = rsrvDtFrom;
    }
    public String getRsrvDtTo() {
        return rsrvDtTo;
    }
    public void setRsrvDtTo(String rsrvDtTo) {
        this.rsrvDtTo = rsrvDtTo;
    }
    public Integer getSeq() {
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatusCd() {
        return statusCd;
    }
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }
    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
    public Integer getTotalPay() {
        return totalPay;
    }
    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getInDt() {
        return inDt;
    }
    public void setInDt(String inDt) {
        this.inDt = inDt;
    }
    public String getRsrvDtStart() {
        return rsrvDtStart;
    }
    public void setRsrvDtStart(String rsrvDtStart) {
        this.rsrvDtStart = rsrvDtStart;
    }
    public String getBtchStyl() {
        return btchStyl;
    }
    public void setBtchStyl(String btchStyl) {
        this.btchStyl = btchStyl;
    }
    public String getRsrvDtEnd() {
        return rsrvDtEnd;
    }
    public void setRsrvDtEnd(String rsrvDtEnd) {
        this.rsrvDtEnd = rsrvDtEnd;
    }
    public String getEtc() {
        return etc;
    }
    public void setEtc(String etc) {
        this.etc = etc;
    }
    public String getModiDt() {
        return modiDt;
    }
    public void setModiDt(String modiDt) {
        this.modiDt = modiDt;
    }
    public Integer getWlsMic() {
        return wlsMic;
    }
    public void setWlsMic(Integer wlsMic) {
        this.wlsMic = wlsMic;
    }
    public Integer getBAmp() {
        return bAmp;
    }
    public void setBAmp(Integer bAmp) {
        this.bAmp = bAmp;
    }
    public Integer getPrjctr() {
        return prjctr;
    }
    public void setPrjctr(Integer prjctr) {
        this.prjctr = prjctr;
    }
    public Integer getTbl() {
        return tbl;
    }
    public void setTbl(Integer tbl) {
        this.tbl = tbl;
    }
    public Integer getChr() {
        return chr;
    }
    public void setChr(Integer chr) {
        this.chr = chr;
    }
    public Integer getGrbgPck() {
        return grbgPck;
    }
    public void setGrbgPck(Integer grbgPck) {
        this.grbgPck = grbgPck;
    }
    
    
    // 장비 사용료
    public List<SreqpmntpayrfrncVO> getSreqpmntpayrfrnc() {
        return sreqpmntpayrfrnc;
    }
    public void setSreqpmntpayrfrnc(List<SreqpmntpayrfrncVO> sreqpmntpayrfrnc) {
        this.sreqpmntpayrfrnc = sreqpmntpayrfrnc;
    }
    // 전시실 임대료 & 관리비
    public List<SrprcrfrncVO> getSrprcrfrnc() {
        return srprcrfrnc;
    }
    public void setSrprcrfrnc(List<SrprcrfrncVO> srprcrfrnc) {
        this.srprcrfrnc = srprcrfrnc;
    }
	public Integer getwMic() {
		return wMic;
	}
	public void setwMic(Integer wMic) {
		this.wMic = wMic;
	}
	public Integer getbAmp() {
		return bAmp;
	}
	public void setbAmp(Integer bAmp) {
		this.bAmp = bAmp;
	}
	public String getHallType1Yn() {
		return hallType1Yn;
	}
	public void setHallType1Yn(String hallType1Yn) {
		this.hallType1Yn = hallType1Yn;
	}
	public String getHallType2Yn() {
		return hallType2Yn;
	}
	public void setHallType2Yn(String hallType2Yn) {
		this.hallType2Yn = hallType2Yn;
	}
	public String getHallType3Yn() {
		return hallType3Yn;
	}
	public void setHallType3Yn(String hallType3Yn) {
		this.hallType3Yn = hallType3Yn;
	}
    public String getModiYn() {
        return modiYn;
    }
    public void setModiYn(String modiYn) {
        this.modiYn = modiYn;
    }
    public String getAddressDtl() {
        return addressDtl;
    }
    public void setAddressDtl(String addressDtl) {
        this.addressDtl = addressDtl;
    }
	public String getStts() {
		return stts;
	}
	public void setStts(String stts) {
		this.stts = stts;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public Integer getGrpId() {
		return grpId;
	}
	public void setGrpId(Integer grpId) {
		this.grpId = grpId;
	}
    
}
