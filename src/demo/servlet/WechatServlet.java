package demo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.process.WechatProcess;

/** 
 * ΢�ŷ�����շ���Ϣ�ӿ� 
 *  
 * @author pamchen-1 
 *  
 */  
public class WechatServlet extends HttpServlet {  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        /** ��ȡ���յ���xml��Ϣ */  
        StringBuffer sb = new StringBuffer();  
        InputStream is = request.getInputStream();  
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }  
        String xml = sb.toString(); //�μ�Ϊ���յ�΢�Ŷ˷��͹�����xml����  
  
        String result = "";  
        /** �ж��Ƿ���΢�Ž��뼤����֤��ֻ���״ν�����֤ʱ�Ż��յ�echostr��������ʱ��Ҫ����ֱ�ӷ��� */  
        String echostr = request.getParameter("echostr");  
        if (echostr != null && echostr.length() > 1) {  
            result = echostr;  
        } else {  
            //������΢�Ŵ�������  
            result = new WechatProcess().processWechatMag(xml);  
        }  
  
        try {  
            OutputStream os = response.getOutputStream();  
            os.write(result.getBytes("UTF-8"));  
            os.flush();  
            os.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}  