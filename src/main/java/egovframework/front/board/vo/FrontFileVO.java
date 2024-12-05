package egovframework.front.board.vo;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FrontFileVO
  implements Serializable
{
  private static final long serialVersionUID = -287950405903719128L;
  public String atchFileId = "";

  public String creatDt = "";

  public String fileCn = "";

  public String fileExtsn = "";

  public String fileMg = "";

  public String fileSn = "";

  public String fileStreCours = "";

  public String orignlFileNm = "";

  public String streFileNm = "";

  public String sync = "";

  public String getAtchFileId()
  {
    return this.atchFileId;
  }

  public void setAtchFileId(String atchFileId)
  {
    this.atchFileId = atchFileId;
  }

  public String getCreatDt()
  {
    return this.creatDt;
  }

  public void setCreatDt(String creatDt)
  {
    this.creatDt = creatDt;
  }

  public String getFileCn()
  {
    return this.fileCn;
  }

  public void setFileCn(String fileCn)
  {
    this.fileCn = fileCn;
  }

  public String getFileExtsn()
  {
    return this.fileExtsn;
  }

  public void setFileExtsn(String fileExtsn)
  {
    this.fileExtsn = fileExtsn;
  }

  public String getFileMg()
  {
    return this.fileMg;
  }

  public void setFileMg(String fileMg)
  {
    this.fileMg = fileMg;
  }

  public String getFileSn()
  {
    return this.fileSn;
  }

  public void setFileSn(String fileSn)
  {
    this.fileSn = fileSn;
  }

  public String getFileStreCours()
  {
    return this.fileStreCours;
  }

  public void setFileStreCours(String fileStreCours)
  {
    this.fileStreCours = fileStreCours;
  }

  public String getOrignlFileNm()
  {
    return this.orignlFileNm;
  }

  public void setOrignlFileNm(String orignlFileNm)
  {
    this.orignlFileNm = orignlFileNm;
  }

  public String getStreFileNm()
  {
    return this.streFileNm;
  }

  public void setStreFileNm(String streFileNm)
  {
    this.streFileNm = streFileNm;
  }

  public String getSync() {
    return this.sync;
  }

  public void setSync(String sync) {
    this.sync = sync;
  }

  public String toString()
  {
    return ToStringBuilder.reflectionToString(this);
  }
}