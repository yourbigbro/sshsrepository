package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="VerifyCodeServlet",urlPatterns="/servlet/VerifyCodeServlet")//后台的相对路径相对于项目名
public class VerifyCodeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//只设置session就不用设置编码方式了
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		String text = vc.getText();
		req.getSession().setAttribute("vc", text);//图片直接通过res传回去，文字内容保存到session中。
		vc.output(image, res.getOutputStream());//VerifyCode对象的方法
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
}
