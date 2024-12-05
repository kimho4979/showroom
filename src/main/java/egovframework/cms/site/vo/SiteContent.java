package egovframework.cms.site.vo;

public class SiteContent
{
  private int contentId;
  private String siteId;
  private int menuId;
  private String content;
  private String registDttm;
  private String registId;
  private String updateDttm;
  private String updateId;

  public int getContentId()
  {
    return this.contentId;
  }

  public void setContentId(int contentId) {
    this.contentId = contentId;
  }

  public String getSiteId() {
    return this.siteId;
  }

  public void setSiteId(String siteId) {
    this.siteId = siteId;
  }

  public int getMenuId() {
    return this.menuId;
  }

  public void setMenuId(int menuId) {
    this.menuId = menuId;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRegistDttm() {
    return this.registDttm;
  }

  public void setRegistDttm(String registDttm) {
    this.registDttm = registDttm;
  }

  public String getRegistId() {
    return this.registId;
  }

  public void setRegistId(String registId) {
    this.registId = registId;
  }

  public String getUpdateDttm() {
    return this.updateDttm;
  }

  public void setUpdateDttm(String updateDttm) {
    this.updateDttm = updateDttm;
  }

  public String getUpdateId() {
    return this.updateId;
  }

  public void setUpdateId(String updateId) {
    this.updateId = updateId;
  }
}
