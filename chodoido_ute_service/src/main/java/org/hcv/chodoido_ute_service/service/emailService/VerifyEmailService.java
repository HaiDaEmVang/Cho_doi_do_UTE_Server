package org.hcv.chodoido_ute_service.service.emailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.service.RedisService.BaseRedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerifyEmailService {
    private final JavaMailSender mailSender;
    private final BaseRedisService baseRedisService;
    Long timeDelete = 5*60*100*60L;

    @Value("${spring.mail.username}")
    String email;

    public void sendCodeVerifyEmail(String toEmail){
        String code = getCode();
        baseRedisService.save(toEmail.trim(), code, timeDelete, TimeUnit.MILLISECONDS);
        sendSimpleEmail(toEmail, "Xác nhận email", getTextMailSend(toEmail, code));
    }

    public boolean verifyCode(String email, String code){
        if(baseRedisService.isExists(email.trim())){
            String value = (String) baseRedisService.get(email.trim());
            return value != null && value.equals(code);
        }
        return false;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(email);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new NoActionException("Gửi email thất bại: " + e.getMessage());
        }
    }

    public String getCode(){
        Random random = new Random();
        return String.format("%06d", random.nextInt(10000));
    }

    public String getTextMailSend(String email, String code){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #ffffff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            background-color: #007bff;\n" +
                "            color: #ffffff;\n" +
                "            padding: 10px 0;\n" +
                "            border-radius: 8px 8px 0 0;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "            color: #333333;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            margin-top: 20px;\n" +
                "            font-size: 12px;\n" +
                "            color: #666666;\n" +
                "        }\n" +
                "        a {\n" +
                "            color: #007bff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Welcome to Our Service!</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Hi <strong>"+email+"</strong>,</p>\n" +
                "            <p>Cảm ơn bạn đã đăng nhập hệ thống! Chúng tôi đã ghi nhận hệ thống. Để tiếp tục, vui lòng xác minh email bằng cách nhập mã code dưới đây vào ứng dụng: </p>\n" +
                "            <p style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #ffffff; border-radius: 5px;\">\n" +
                "                "+ code +"\n" +
                "            </p>\n" +
                "            <p>Nếu nút không hoạt động, vui lòng copy code và dán vào ứng dụng</p>\n" +

                "            <p>Nếu bạn không thể tạo tài khoản vui lòng trả lời lại email này.</p>\n" +
                "            <p>Thân gửi,<br>The Team HCV</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 hải very very đẹp trai nhoa.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

}
