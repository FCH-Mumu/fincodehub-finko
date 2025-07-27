package com.fincodehub.finko.auth.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teautil.models.RuntimeOptions;
import com.finko.framework.common.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @title AliyunSmsHelper
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/13 11:00
 * @description 短信发送工具类
 */
@Component
@Slf4j
public class AliyunSmsHelper {

    @Resource private Client client;

    /**
     * 发送短信
     *
     * @param signName
     * @param templateCode
     * @param phone
     * @param templateParam
     * @return
     */
    public boolean sendMessage(
            String signName, String templateCode, String phone, String templateParam) {
        SendSmsRequest sendSmsRequest =
                new SendSmsRequest()
                        .setSignName(signName)
                        .setTemplateCode(templateCode)
                        .setPhoneNumbers(phone)
                        .setTemplateParam(templateParam);
        RuntimeOptions runtime = new RuntimeOptions();

        try {
            log.info(
                    "==> 开始短信发送, phone: {}, signName: {}, templateCode: {}, templateParam: {}",
                    phone,
                    signName,
                    templateCode,
                    templateParam);

            // 发送短信
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtime);

            log.info("==> 短信发送成功, response: {}", JsonUtils.toJsonString(response));
            return true;
        } catch (Exception error) {
            log.error("==> 短信发送错误: ", error);
            return false;
        }
    }
}