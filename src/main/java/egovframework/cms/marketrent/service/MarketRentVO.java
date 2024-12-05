/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.cms.marketrent.service;

/**
 * @Class Name : SampleVO.java
 * @Description : SampleVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class MarketRentVO  {


	private String contentId;

	private String title;

	private String content;
	
	private String registDttm;
	
	private String registId;
	
	private String updateDttm;
	
	private String updateId;

	

	public String getContentId() {
		return contentId;
	}



	public void setContentId(String contentId) {
		this.contentId = contentId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getRegistDttm() {
		return registDttm;
	}



	public void setRegistDttm(String registDttm) {
		this.registDttm = registDttm;
	}



	public String getRegistId() {
		return registId;
	}



	public void setRegistId(String registId) {
		this.registId = registId;
	}



	public String getUpdateDttm() {
		return updateDttm;
	}



	public void setUpdateDttm(String updateDttm) {
		this.updateDttm = updateDttm;
	}



	public String getUpdateId() {
		return updateId;
	}



	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}


	

	

	
}
