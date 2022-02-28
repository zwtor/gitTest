package com.lazy.common.utils;

import com.lazy.config.ResultCount;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 
 * @author zhanglun
 * @version 1.0
 */
public class SendMailUtil {

	public static Logger logger=Logger.getLogger(SendMailUtil.class);
    public static String username = AutoCommonUtil.readConfig("mailconfig.properties", "username"); // 服务邮箱(from邮箱)
    public static String password = AutoCommonUtil.readConfig("mailconfig.properties", "password"); // 邮箱密码
    public static String senderNick = AutoCommonUtil.readConfig("mailconfig.properties", "senderNick");   // 发件人昵称
    static int results[]= ResultCount.getResultCount();
    private Properties props; // 系统属性 
    private Session session; // 邮件会话对象 
    private MimeMessage mimeMsg; // MIME邮件对象 
    private Multipart mp;   // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象 

    private static SendMailUtil instance = null; 

    public SendMailUtil() {
        props = System.getProperties();
        props.put("mail.smtp.auth",AutoCommonUtil.readConfig("mailconfig.properties", "smtp.auth"));
        props.put("mail.transport.protocol", AutoCommonUtil.readConfig("mailconfig.properties", "transport.protocol"));
        props.put("mail.smtp.host", AutoCommonUtil.readConfig("mailconfig.properties", "smtp.host"));
        props.put("mail.smtp.port", AutoCommonUtil.readConfig("mailconfig.properties", "smtp.port"));
        props.put("username", username);
        props.put("password", password);
        // 建立会话
        session = Session.getDefaultInstance(props);
        session.setDebug(false);
    }
    public static SendMailUtil getInstance() {
        if (instance == null) {
            instance = new SendMailUtil();
        }
        return instance; 
    }

    /**
     * 发送邮件
     * @param from 发件人
     * @param to 收件人
     * @param copyto 抄送
     * @param subject 主题
     * @param content 内容
     * @param fileList 附件列表
     * @return
     */
    public boolean sendMail(String from, String[] to, String[] copyto, String subject, String content, String[] fileList) {
        boolean success = true;
        try {
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart(); 

            // 自定义发件人昵称
            String nick = "";
            try {
                nick = MimeUtility.encodeText(senderNick);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 设置发件人
//          mimeMsg.setFrom(new InternetAddress(from));
            mimeMsg.setFrom(new InternetAddress(from, nick));
            // 设置收件人
            if (to != null && to.length > 0) {
                String toListStr = getMailList(to);
                mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toListStr));
            }
            // 设置抄送人
            if (copyto != null && copyto.length > 0) {
                String ccListStr = getMailList(copyto);
                mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccListStr)); 
            }
            // 设置主题
            mimeMsg.setSubject(subject);
            // 设置正文
            BodyPart bp = new MimeBodyPart(); 
            bp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(bp);
            // 设置附件
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    bp = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileList[i]); 
                    bp.setDataHandler(new DataHandler(fds)); 
                    bp.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));
                    mp.addBodyPart(bp); 
                }
            }
            mimeMsg.setContent(mp); 
            mimeMsg.saveChanges(); 
            // 发送邮件
            if (props.get("mail.smtp.auth").equals("true")) {
                Transport transport = session.getTransport("smtp"); 
                transport.connect((String)props.get("mail.smtp.host"), (String)props.get("username"), (String)props.get("password")); 
                transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
                transport.close(); 
            } else {
                Transport.send(mimeMsg);
            }
            logger.info("Successfully send test report mail....." );
        } catch (MessagingException e) {
            e.printStackTrace();
            success = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            success = false;
        }
        
        return success;
    }


    public String getMailList(String[] mailArray) {
        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0]);
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i]);
                if (i != (length - 1)) {
                    toList.append(",");
                }
            }
        }
        return toList.toString();
    }
    
    public static void sendMail() {
        String from = username;
        String[] to = {"allenlor@163.com"};//多个收件人，以逗号间隔
        String[] copyto = {""};//抄送人
        String subject = "自动化测试报告";
        String content = getAutoMailTemplate();
        String[] fileList = new String[2];
        fileList[0] = FilePath.getTestOutPutDirectory()+"index.html";
        fileList[1] = FilePath.getLogDirectory()+"AutoTest.log";
        SendMailUtil.getInstance().sendMail(from, to, copyto, subject, content, fileList);
    }
    //邮件模板
    public static String getAutoMailTemplate() {
    	  ITestResult it = Reporter.getCurrentTestResult();
    	  
		  int successSize=it.getTestContext().getPassedTests().size();
		  int failSize=it.getTestContext().getFailedTests().size();
		  int skipSize=it.getTestContext().getSkippedTests().size();
		  
		 
		 String startTime=DateUtil.getRegularDate(Reporter.getCurrentTestResult().getTestContext().getStartDate());
		 String endTime=DateUtil.getRegularDate(Reporter.getCurrentTestResult().getTestContext().getEndDate());
		  String content = "<!DOCTYPE html >\r\n" + 
		  		"<html lang=\"zh-CN\" class=\"js_active vc_desktop vc_transform vc_transform hb-loaded\">\r\n" + 
		  		"<head>\r\n" + 
		  		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n" + 
		  		"<title>自动化测试</title>\r\n" + 
		  		"<style>\r\n" + 
		  		"	*{\r\n" + 
		  		"	margin:0;\r\n" + 
		  		"	padding:0;\r\n" + 
		  		"	text-decoration:none\r\n" + 
		  		"	}\r\n" + 
		  		"\r\n" + 
		  		"	img{\r\n" + 
		  		"	border:0;\r\n" + 
		  		"	width: 430px;\r\n" + 
		  		"	height:130px;\r\n" + 
		  		" 	}\r\n" + 
		  		"\r\n" + 
		  		"	.header{\r\n" + 
		  		"	height:130px;\r\n" + 
		  		"	width:100%;		\r\n" + 
		  		"	background: #fafef5 ;\r\n" + 
		  		"	position: relative;\r\n" + 
		  		"	border-bottom:solid 5px #002a00; \r\n" + 
		  		"	}  \r\n" + 
		  		"\r\n" + 
		  		"	.header_logo {\r\n" + 
		  		"	text-align:center;\r\n" + 
		  		"	}\r\n" + 
		  		"	.main{\r\n" + 
		  		"	width:80%;\r\n" + 
		  		"	margin:0 auto;\r\n" + 
		  		"	}\r\n" + 
		  		"	.a, .b{\r\n" + 
		  		"	margin-top:80px;\r\n" + 
		  		"	}\r\n" + 
		  		"\r\n" + 
		  		"	h2{	\r\n" + 
		  		"	font-size: 32px;\r\n" + 
		  		"    	font-weight: bold;\r\n" + 
		  		"    	color: #002a00;\r\n" + 
		  		"    	margin: 20px 0;	\r\n" + 
		  		"	}\r\n" + 
		  		"\r\n" + 
		  		"	.a p{\r\n" + 
		  		"	font-size: 22px;\r\n" + 
		  		"    	margin-left: 60px;\r\n" + 
		  		"    	color: black;\r\n" + 
		  		"	line-height:36px;\r\n" + 
		  		"	}\r\n" + 
		  		"\r\n" + 
		  		"	.b p{\r\n" + 
		  		"	font-size: 16px;\r\n" + 
		  		"    	color: black;\r\n" + 
		  		"	line-height:25px;\r\n" + 
		  		"	}\r\n" + 
		  		"\r\n" + 
		  		"	.foot{\r\n" + 
		  		"	height: 30px;	\r\n" + 
		  		"	line-height: 35px;	\r\n" + 
		  		"	position: fixed;\r\n" + 
		  		"	bottom: 0;	\r\n" + 
		  		"	width: 100%;	\r\n" + 
		  		"	text-align: center;	\r\n" + 
		  		"	background-color: darkslategrey;	\r\n" + 
		  		"	color: #fff;	font-family: Arial;	\r\n" + 
		  		"	font-size: 14px;	\r\n" + 
		  		"	letter-spacing: 1px;\r\n" + 
		  		"	}\r\n" + 
		  		"	a{\r\n" + 
		  		"	color:black;\r\n" + 
		  		"	}\r\n" + 
		  		"	a:hover{\r\n" + 
		  		"	text-decoration:none; \r\n" + 
		  		"	color:blue;\r\n" + 
		  		"	}\r\n" + 
		  		"</style>\r\n" + 
		  		"</head>\r\n" + 
		  		"<body>\r\n" + 
		  		"	<div class=\"header\">\r\n" + 
		  		"		<div class=\"header_logo\">\r\n" + 
		  		"			<a class=\"square\" href=\"http://www.baidu.com\" >\r\n" + 
		  		"			</a>\r\n" + 
		  		"		</div>	\r\n" + 
		  		"	</div>\r\n" + 
		  		"	<div class=\"main\">\r\n" + 
		  		"	        <div class=\"a\">\r\n" + 
		  		"		<h2>自动化测试执行完毕，具体如下：</h2>\r\n" + 
		  		" 		<p>执行开始时间：<span style=\"color:red;font-weight:bold\">"+startTime+"</span></p>\r\n" + 
		  		"  		<p>执行结束时间：<span style=\"color:red;font-weight:bold\">"+endTime+"</span></p>\r\n" + 
		  		"  		<p>执行成功个数：<span style=\"color:red;font-weight:bold\">"+successSize+"</span></p>\r\n" + 
		  		" 		<p>执行失败个数：<span style=\"color:red;font-weight:bold\">"+failSize+"</span></p>\r\n" + 
		  		"  		<p>执行skip个数：<span style=\"color:red;font-weight:bold\">"+skipSize+"</span></p>\r\n" + 
		  		"  		<p>测试报告请查看附件，如需详细内容请查看附件日志。</p>\r\n" + 
		  		"	         </div>\r\n" + 
		  		"	         <div class=\"b\">\r\n" + 
		  		"		<p >自动化测试组</p>\r\n" + 
		  		"		<p>自动化：基础平台组</p>\r\n" + 
		  		"		<p>联系人：张伦</p>\r\n" + 
		  		"		<p>电    话: <a href=\"tel://18220783868\">18220783868</a></p>\r\n" + 
		  		"		<p >Email：<a href =\"mailto:"+username+"\">"+username+"</a></p>\r\n" + 
		  		"		<p>地   址：上海市浦东新区国耀路209号鲁能国际中心C座7楼</p>\r\n" + 
		  		"		<p>   _   </p>\r\n" + 
		  		"	          </div>\r\n" + 
		  		"	</div>\r\n" + 
		  		"	<div class=\"foot\">\r\n" + 
		  		"		<p>提示：请不要对此邮件直接回复，系统看不懂您的回信^_^。如果您有建议或意见，请回复 <a href =\"mailto:allenlor@163.com?subject=Testing mailto&cc=1615008038@qq.com\" style=\"color:#fff;font-family: Arial;font-size: 14px;\">"+username+"</p>\r\n" + 
		  		"	</div>\r\n" + 
		  		" \r\n" + 
		  		"</body>\r\n" + 
		  		"</html>";
    	 
    	 
		return content;
    }
    
}
