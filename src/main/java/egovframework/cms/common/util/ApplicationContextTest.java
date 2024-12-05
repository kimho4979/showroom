package egovframework.cms.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import egovframework.srrsrvtn.service.impl.SrrsrvtnServiceImpl;

public class ApplicationContextTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = null;
        context = new ClassPathXmlApplicationContext("egovframework/spring/context-*.xml");
        String[] beans = context.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println(bean);
        }
        SrrsrvtnServiceImpl service = (SrrsrvtnServiceImpl)context.getBean("srrsrvtnServiceImpl");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("hallType", "1");
        map.put("evntDt", "AM");
        map.put("rsrvDtStart", "20240708");
        map.put("rsrvDtEnd", "20240708");
        map.put("orgNm", "파인");
        map.put("mngrNm", "채희진");
        map.put("telNo", "00000000000");
        map.put("seq", "1");

        service.sendMMS(map);
        System.exit(0);
    }
}
