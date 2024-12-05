package egovframework.cms.cmm;

import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

public class PaginationRendererDefault extends AbstractPaginationRenderer
  implements ServletContextAware
{
  private ServletContext servletContext;

  public void initVariables()
  {
    firstPageLabel = "";
    previousPageLabel = "<a href=\"?{0}{1}={2}\" class=\"btn_page_prev\">이전</a>";
    currentPageLabel = "<a href=\"#\" class=\"btn_page on\"><strong>{0}</strong></a>";
    otherPageLabel = "<a href=\"?{0}{1}={2}\" class=\"btn_page\">{3}</a>";
    nextPageLabel = "<a href=\"?{0}{1}={2}\" class=\"btn_page_next\">다음</a>";
    lastPageLabel = "";
    
    /*
    <a href="#!" class="btn_page_prev">이전</a>
	<a href="#!" class="btn_page on">1</a>
	<a href="#!" class="btn_page">2</a>
	<a href="#!" class="btn_page">3</a>
	<a href="#!" class="btn_page">4</a>
	<a href="#!" class="btn_page">5</a>
	<a href="#!" class="btn_page_next">다음</a>
    */
    
  }

  public void setServletContext(ServletContext servletContext)
  {
    this.servletContext = servletContext;
    initVariables();
  }
}
