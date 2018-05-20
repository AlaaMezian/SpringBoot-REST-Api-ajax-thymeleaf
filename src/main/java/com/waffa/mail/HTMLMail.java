package com.waffa.mail;

public class HTMLMail extends AbstractMail {

    public HTMLMail(String to) {
        super(to);
    }

    @Override
    public String getSubject() {
        return "New Password for wafa dying center application";
    }

    @Override
    public String getContent() {
        return "<html>" +
                    "<body>" +
                        "<p>Hello Dear </p>" +
                        "<p> please consider the following as your new password" +
                        "<p>This is <strong>your new password</strong> </p>" +
                    "</body>" +
                "</html>";
    }
}