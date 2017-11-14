package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="VerifyCodeServlet",urlPatterns="/servlet/VerifyCodeServlet")//��̨�����·���������Ŀ��
public class VerifyCodeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//ֻ����session�Ͳ������ñ��뷽ʽ��
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		String text = vc.getText();
		req.getSession().setAttribute("vc", text);//ͼƬֱ��ͨ��res����ȥ���������ݱ��浽session�С�
		vc.output(image, res.getOutputStream());//VerifyCode����ķ���
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
}
