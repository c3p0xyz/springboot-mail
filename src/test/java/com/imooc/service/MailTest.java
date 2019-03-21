package com.imooc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
     MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    @Test
    public void test(){
        mailService.sendSimpleMail("13580465756@126.com","第一封邮件","这是一封通过springboot发送的邮件！");
    }

    @Test
    public void sendHtmlMail() throws Exception {
        String content = "<html><body><h2>这是一封springboot发送的html邮件！</h2></body></html>";
        mailService.sendHtmlMail("13580465756@126.com","第二封邮件",content);
    }

    @Test
    public void sendAttachmentMailTest() throws MessagingException {
        String filePath = "F:\\讲义\\day22--JDBC.docx";
        mailService.sendAttachmentMail("13580465756@126.com","第三封邮件","这是一封带附件的springboot发送的邮件！",filePath);

    }

    @Test
    public void sendInlinMail() throws MessagingException {
        String imgId = "007";
        String imgPath = "D:\\timg.jpg";
        String content = "<html><body> 这是带图片的邮件: <img src=\'cid:"+imgId+"\'></img></body></html>";
        mailService.sendInlinMail("13580465756@126.com","第四封邮件",content,imgPath,imgId);
    }

    @Test
    public void sendMailTemplateTest() throws MessagingException {

        Context context = new Context();
        context.setVariable("id","0_8");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("13580465756@126.com","第五封模板邮件",emailContent);
    }
}
