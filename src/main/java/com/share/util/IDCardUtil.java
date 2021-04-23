package com.share.util;

import com.baidu.aip.util.Base64Util;
import com.share.exceptions.ShareException;
import com.share.ro.IDCardRo;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Setter
@Component
@ConfigurationProperties(prefix = "idcard")
public class IDCardUtil {
    private String url;
    private String clientId;
    private String clientSecret;

    /**
     * 来源：百度orc图片识别
     * */

    public IDCardRo idcard(MultipartFile file){
        try{
            byte[] imgData = FileUtil.readFileByBytes(file);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            //以身份证正面格式发起请求
            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            String accessToken = getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            JSONObject idCard = new JSONObject();
            JSONObject jsonObject = new JSONObject(result);
            JSONObject words_result = jsonObject.getJSONObject("words_result");

            IDCardRo idCardRo = new IDCardRo();
            idCardRo.setTrueName((String)(words_result.getJSONObject("姓名").get("words")));
//            idCardRo.setNation((String) words_result.getJSONObject("民族").get("words"));
//            idCardRo.setIDAddress((String) words_result.getJSONObject("住址").get("words"));
            idCardRo.setSex((String) words_result.getJSONObject("性别").get("words"));
//            idCardRo.setBirth("birth", words_result.getJSONObject("出生").get("words"));
            idCardRo.setIDNumber((String) words_result.getJSONObject("公民身份号码").get("words"));
            return idCardRo;
        }catch(Exception e){
            e.printStackTrace();
            throw new ShareException("认证功能出现异常，请重试!");
        }
    }

    public String getAuth() {
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw new ShareException("获取token失败,请重新尝试");
        }
    }

    /**
     * 通过输入的身份证号验证最后一位是否正确
     * @param idNum：用户输入的身份证号
     * @return 正确true，错误false
     * */
    public static boolean idNumberCheck(String idNum){
        char[] a = idNum.toCharArray();
        int sum = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int v = (int)((Math.pow(2, a.length - 1 - i)) % 11);
            //因为char在底层是以ASCII码，因此转化为整形要减去char的'0'
            int ai = a[i] - '0';
            sum+=v*ai;
        }
        int T = sum % 11;
        int R = (12 - T) % 11;
        if (R==10 && a[a.length-1] == 'X'){
            return true;
        }else if(R == a[a.length-1]-'0'){
            return true;
        }else {
            return false;
        }
    }
}