package com.share.util;

import com.zhenzi.sms.ZhenziSmsClient;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Setter
@Component
@ConfigurationProperties(prefix = "phonecode")
public class PhoneCodeUtil {

    private String apiUrl;
    private String appId;
    private String appSecret;
    private String templateId;

    public String send(String number, String code, String time) throws Exception {
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", number);
        params.put("templateId", templateId);
        String[] templateParams = new String[2];
        templateParams[0] = code;
        templateParams[1] = time;
        params.put("templateParams", templateParams);
        String result = client.send(params);
        System.out.println(result);
        return result;
    }
}
