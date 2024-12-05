
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="egovframework.cms.site.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	public String generateTreemenu(List<SiteMenuVO> list, int parent)
	{
		String tree = "";
		for (int i = 0; i < list.size(); i++)
		{
			int menuId = list.get(i).getMenuId();
			if (list.get(i).getParntsMenuId() == parent)
			{
				tree += String.format("<li class=\"mjs-nestedSortable-branch mjs-nestedSortable-expanded\" id=\"menuItem_%d\"><div class=\"menuDiv\"><span><span data-id=\"%d\" class=\"itemTitle\">", menuId, menuId);
				tree += String.format("%s</span></span></div>", list.get(i).getMenuNm());
				tree += this.generateTreemenu(list, menuId);
				tree += "</li>";
			}
		}
		
		if (!"".equals(tree))
		{
			tree = "<ol>"+tree+"</ol>";
		}
		
		return tree;
	}
%>
<%
	int rootMenuId = (Integer)request.getAttribute("rootMenuId");
	List<SiteMenuVO> list = (List)request.getAttribute("siteMenuList");
	String sortableHtml = generateTreemenu(list, rootMenuId);
%>


<style type="text/css">
.placeholder {
	outline: 1px dashed #4183C4;
}

.mjs-nestedSortable-error {
	background: #fbe3e4;
	border-color: transparent;
}

#tree {
	margin: 0;
}

ol {
	padding-left: 40px;
}

ol.sortable,ol.sortable ol {
	list-style-type: none;
}

.sortable li div {
	border: 1px solid #d4d4d4;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	cursor: move;
	border-color: #D4D4D4 #D4D4D4 #BCBCBC;
	margin: 0;
	padding: 3px;
}

li.mjs-nestedSortable-collapsed.mjs-nestedSortable-hovering div {
	border-color: #999;
}

.disclose, .expandEditor {
	cursor: pointer;
	width: 20px;
	display: none;
}

.sortable li.mjs-nestedSortable-collapsed > ol {
	display: none;
}

.sortable li.mjs-nestedSortable-branch > div > .disclose {
	display: inline-block;
}

.sortable span.ui-icon {
	display: inline-block;
	margin: 0;
	padding: 0;
}

.menuDiv {
	background: #EBEBEB;
}

.menuEdit {
	background: #FFF;
}

.itemTitle {
	vertical-align: middle;
	cursor: pointer;
}

.deleteMenu {
	float: right;
	cursor: pointer;
}
	
p,ol,ul,pre,form {
	margin-top: 0;
	margin-bottom: 1em;
}
</style>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />
	<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.min.js"></script>
	<script type="text/javascript" src="https://ilikenwf.github.io/jquery.mjs.nestedSortable.js"></script>

<script type="text/javascript">
$().ready(function()
{
	$('#sortable_wrap').find('ol:first').addClass('sortable ui-sortable mjs-nestedSortable-branch mjs-nestedSortable-expanded');
	var ns = $('ol.sortable').nestedSortable({
		forcePlaceholderSize: true,
		handle: 'div',
		helper:	'clone',
		items: 'li',
		opacity: .6,
		placeholder: 'placeholder',
		revert: 250,
		tabSize: 25,
		tolerance: 'pointer',
		toleranceElement: '> div',
		maxLevels: 7,
		isTree: true,
		expandOnHover: 700,
		startCollapsed: false
	});
	
	$('#toArray').click(function(e)
	{
		arraied = $('ol.sortable').nestedSortable('toArray', {startDepthCount: 0});
		data = JSON.stringify(arraied);
		
		console.log(data);
		
		$('#jsonData').val(data);
		$('#moveSiteMenu').submit();
		
	});
});			
</script>

<div id="page-wrapper" class="gray-bg">
<jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
	<!-- Content Start -->
	<div class="wrapper wrapper-content" id="content">

		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5 class="text-navy">
							메뉴 등록 <small>아래의 내용을 입력해 주시기 바랍니다.</small>
						</h5>
					</div>
				</div>
				
				<section id="sortable_wrap">
					<form id="moveSiteMenu" action="${pageContext.request.contextPath}/admin/site/menuMoveProc.do" method="post">
						<input type="hidden" name="jsonData" id="jsonData" value="" />
					</form>
					<div style="height:600px; overflow-y:auto; margin-bottom:10px; padding:20px; border:1px solid #e0e0e0; ">
						<%=sortableHtml%>
					</div>
					<div class="text-center">
						<button type="button" id="toArray" class="btn btn-success">변경사항 적용</button>
					</div>
				</section>
			</div>
		</div>



		
	</div>
</div>
