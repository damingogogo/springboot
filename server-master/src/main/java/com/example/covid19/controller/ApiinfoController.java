package com.example.covid19.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.covid19.common.dto.ImageUploadDto;
import com.example.covid19.common.dto.PaichaDto;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.LearnCount;
import com.example.covid19.entity.Paicha;
import com.example.covid19.service.LearnCountService;
import com.example.covid19.service.LoginUserInfoService;
import com.example.covid19.service.PaichaService;
import com.example.covid19.util.JwtUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BaiZhengChun
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/apiinfo")
public class ApiinfoController {

    @Autowired
    private LearnCountService learnCountService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private LoginUserInfoService loginUserInfoService;

    @Autowired
    private PaichaService paichaService;

    @GetMapping("/getInfo")
    public Result getCOVIDNews() {
        String httpUrl = "http://api.tianapi.com/ncov/index?key=9b4f31f597100cc9ec8133ee4de15196";
        String jsonResult = request(httpUrl);
        JSONObject json = JSONUtil.parseObj(jsonResult);
        String str = json.getStr("newslist");
        JSONArray array = JSONUtil.parseArray(str);
        return Result.success(array);
    }

    public static String request(String httpUrl) {
        BufferedReader reader;
        String result = null;
        StringBuilder sbf = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @GetMapping("/sina")
    public Result sina(){
        String url = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";
        String jsonResult = request(url);
        JSONObject json = JSONUtil.parseObj(jsonResult);
        String str = json.getStr("data");
        JSONObject array = JSONUtil.parseObj(str);
        return Result.success(array);
    }


    public int learn(String base64) {
        byte[] buff = Base64.getDecoder().decode(base64);
        String url = "https://api-cn.faceplusplus.com/imagepp/v2/generalocr";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "puaPEqTKRAATMQu35U8y3SwQGiLJduX0");
        map.put("api_secret", "vXTB_SEI0P3sZg3UPcJym7bxb6MrAW8p");
        map.put("return_landmark", "1");
        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
//            str = StringEscapeUtils.unescapeJava(str);
            return testFindChar(str);
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String learnl(String base64) {
        byte[] buff = Base64.getDecoder().decode(base64);
        String url = "https://api-cn.faceplusplus.com/imagepp/v2/generalocr";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "puaPEqTKRAATMQu35U8y3SwQGiLJduX0");
        map.put("api_secret", "vXTB_SEI0P3sZg3UPcJym7bxb6MrAW8p");
        map.put("return_landmark", "1");
        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
//            str = StringEscapeUtils.unescapeJava(str);
            return testFind(str);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static final String boundaryString = getBoundary();
    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            String key = ((Map.Entry<String, String>) (Map.Entry) stringStringEntry).getKey();
            String value = ((Map.Entry<String, String>) (Map.Entry) stringStringEntry).getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            for (Map.Entry<String, byte[]> fileEntry : fileMap.entrySet()) {
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode() + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins;
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }
    private static String encode() throws Exception{
        return URLEncoder.encode(" ", "UTF-8");
    }

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException ignored) {
        }
        return null;
    }
    //java中判断一个字符出现的次数
    //在下面字符串中查找有几个啊
    public int testFindChar(String str){
        //存放每个字符的数组
        String [] strs = new String[str.length()];
        //计数该字符出现了多少次
        int count = 0;
        //先把字符串转换成数组
        for(int i = 0;i<strs.length;i++){
            strs[i] = str.substring(i,i+1);
        }
        //挨个字符进行查找，查找到之后count++
        for (String s : strs) {
            if (s.equals("*")) {
                count++;
            }
        }

        return count;
    }
    public String testFind(String str){
        //存放每个字符的数组
        String [] strs = new String[str.length()];
        //计数该字符出现了多少次
        String flag = "true";
        //先把字符串转换成数组
        for(int i = 0;i<strs.length;i++){
            strs[i] = str.substring(i);
        }
        //挨个字符进行查找，查找到之后count++
        for (String s : strs) {
            if (s.equals("红")||s.equals("黄")) {
                flag = "flase";
            }
            else {
                flag = "true";
            }

        }

        return flag;
    }

    @RequiresAuthentication
    @PostMapping("/from")
    public Result from(@Validated @RequestBody PaichaDto paichaDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            return Result.fail(444, "Token过期", null);
        }
        String id = claims.getSubject();
        Paicha paicha = new Paicha();
        paicha.setId(Integer.valueOf(id));
        paicha.setLxbGao(paichaDto.getLxb_Gao());
        paicha.setLxbJing(paichaDto.getLxb_Jing());
        paicha.setLxbJuji(paichaDto.getLxb_JuJi());
        paicha.setLxbMijie(paichaDto.getLxb_MiJie());
        paicha.setLxbMjgao(paichaDto.getLxb_MJGao());
        String color = learnl(paichaDto.getBase65());
        paicha.setColor(color);
        if (Objects.equals(color, "flase")) {
            sendMessage();
        }
        int count = learn(paichaDto.getBase64());
        System.out.println(count);
        paicha.setCount(count);
        if (count > 4) {
            sendMessage();
        }
        return Result.success(paicha);
    }

    public void sendMessage() {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential("AKID73mGVVFk00kFVVFl24qMcdOt9AvQggRw", "HJ5buxlJbdV8WeW9WdU6SYOzKQHvLVv6");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"19824758412"};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId("1400527069");
            req.setSignName("辽东学院");
            req.setTemplateId("972395");
            // 验证码，附加文字，但是不能超过12个字符
            String[] templateParamSet1 = {"114514"};
            req.setTemplateParamSet(templateParamSet1);
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

    }

}
