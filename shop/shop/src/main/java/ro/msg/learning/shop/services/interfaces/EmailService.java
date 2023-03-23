package ro.msg.learning.shop.services.interfaces;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface EmailService {

    void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException;

    void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel) throws MessagingException;

}
