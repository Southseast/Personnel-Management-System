package cc.southseast.controller.verification.reset;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static cc.southseast.view.ui.base.StaticData.MAIL_ACCOUNT;
import static cc.southseast.view.ui.base.StaticData.MAIL_PASSWORD;

/**
 * @Author: Southseast
 * @Date: 2019/1/9 11:05 AM
 * @Version 1.0
 */
public class MailUtil implements Runnable {

    // 收件人邮箱
    private String email;
    // 激活码
    private String code;

    public MailUtil(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public static void main(String[] args) {
        MailUtil mailUtil = new MailUtil("314107086@qq.com", "吴明阳傻逼");
        mailUtil.run();
    }

    public void run() {
        // 指定发送邮件的主机
        String host = "smtp.qq.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 打开认证
        properties.setProperty("mail.smtp.auth", "true");

        try {

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);


            // 获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MAIL_ACCOUNT, MAIL_PASSWORD); // 发件人邮箱账号、授权码
                }
            });

            // 创建邮件对象
            Message message = new MimeMessage(session);
            // 设置发件人
            message.setFrom(new InternetAddress(MAIL_ACCOUNT));
            // 设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 设置邮件主题
            message.setSubject("密码重置");
            message.setSentDate(new Date());

            SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分ss秒");
            Calendar time = Calendar.getInstance();

            // 设置邮件内容
            String content = "<div id=\"qm_con_body\">\n" +
                    "    <div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\"> \n" +
                    "        <div style=\"\">\n" +
                    "            <div style=\" color:#666; font-size:14px; margin:0 auto; width:740px;\">\n" +
                    "                <div style=\" float:left;  background:#fff no-repeat left top; border:1px solid #e3e3e3; padding:85px 70px 54px 70px; height:auto; width:600px; position:relative;\">\n" +
                    "                    <div style=\" position:absolute; right:-52px; top:52px;\"></div>\n" +
                    "                        <h1 style=\" color:#4882ce; font-size:22px;\">您好！</h1>\n" +
                    "                    <div style=\" margin-top:35px;\">\n" +
                    "                        <h1 style=\"color:#333; font-size:16px;\">您重置密码的邮箱验证码为：<span style=\"border-bottom:1px dashed #ccc;z-index:1\" t=\"7\">" +
                    code +
                    "</span></h1>\n" +
                    "                        <p style=\"margin-top:25px;\">PS.  如果这不是你本人的操作，请忽略此封邮件。</p>\n" +
                    "                    </div>\n" +
                    "                    <div style=\" border-top:1px solid #ebedf1; padding-top:45px; margin:30px 0 20px 0;\">南溟<a href=\"https://southseast.cc\" title=\"南溟\" target=\"_blank\" style=\" color:#4882ce; margin:0 10px; text-decoration:none;\">southseast.cc</a><p><span style=\"border-bottom:1px dashed #ccc;\" t=\"5\" times=\"\">" +
                    yearMonthDay.format(time.getTime()) +
                    "</span></p></div>\n" +
                    "                </div>\t\t\n" +
                    "                <div style=\" clear:both; color:#757575; font-size:10px; padding:20px 0 80px; text-align:center;\">\n" +
                    "                    <p style=\"margin:0; padding:0;\">这是一封系统确认函，请不要回复此邮件！此邮件的地址无法接受您来信。</p>\n" +
                    "                    <p style=\"margin:0; padding:0;\"> © 2019 <a href=\"https://southseast.cc\" target=\"_blank\" style=\"color:#4882ce; text-decoration:none;\">southseast.cc</a>. All Rights Reserved.</p>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <style type=\"text/css\">.qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}</style>\n" +
                    "    </div>\n" +
                    "</div>";

            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}