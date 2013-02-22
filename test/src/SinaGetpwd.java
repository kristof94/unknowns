import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientParamBean;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class SinaGetpwd extends JFrame {
	private static final String[] KEY_LIB_C = new String[]{"hld", "huald", "hualide", "hualider", "HuaLide", "HuaLider"};
	
	private static int indexA = 0;
	
	private static final String[] KEY_LIB_B = new String[]{"0763", "5703", "1881", "188197210"};
	
	private static int indexB = 0;
	
	private static final String[] KEY_LIB_A = new String[]{"暂时没想到", "话你得", "橘子", "橘仔"};
	
	private static int indexC = 0;
	//	hld1881
	private HttpClient client;
	
	private HttpContext context;
	
	private JLabel label;
	
	private JTextField textField;
	
	public SinaGetpwd() {
		HttpParams params = new BasicHttpParams();
		ClientParamBean clientParam = new ClientParamBean(params);
		clientParam.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8,text/vnd.wap.wml;q=0.6"));
		clientParam.setDefaultHeaders(headers);
		HttpProtocolParamBean httpProtocolParam = new HttpProtocolParamBean(params);
		httpProtocolParam.setContentCharset(HTTP.UTF_8);
		httpProtocolParam.setHttpElementCharset(HTTP.UTF_8);
		httpProtocolParam.setUserAgent("Mozilla/5.0 (Windows NT 5.1; rv:10.0.2) Gecko/20100101 Firefox/10.0.2");
		httpProtocolParam.setUseExpectContinue(true);
		client = new DefaultHttpClient(params);
		context = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		label = new JLabel();
		textField = new JTextField(5);
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getpwd(textField.getText());
			}
		});
		button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), JComponent.WHEN_IN_FOCUSED_WINDOW);
		button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), JComponent.WHEN_IN_FOCUSED_WINDOW);
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(textField);
		panel.add(button);
		add(panel);
		setTitle("找回账号");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void getpwd(String captcha) {
		String name = KEY_LIB_A[indexA] + KEY_LIB_B[indexB] + KEY_LIB_C[indexC];
		String result = submit(name, captcha);
		System.out.println(name + ":" + result);
		if(!"验证码输入错误或过期！".equals(result)) indexA++;
		if(result==null) System.err.println("貌似"+name+"是有效账号！");
		if(indexA == KEY_LIB_A.length) {
			indexA = 0;
			indexB++;
		}
		if(indexB == KEY_LIB_B.length) {
			indexB = 0;
			indexC++;
		}
		if(indexC == KEY_LIB_C.length) {
			System.out.println("匹配结束");
			System.exit(0);
			return;
		}
		setCaptcha();
	}
	
	private void setCaptcha() {
		String url = "http://login.sina.com.cn/cgi/pin.php?r=" + new Random().nextInt();
//		System.out.println("setCaptcha:" + url);
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request, context);
			HttpEntity entity = response.getEntity();
			label.setIcon(new ImageIcon(EntityUtils.toByteArray(entity)));
			textField.setText("");
			textField.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
			label.setText("无法加载验证码：" + e);
		} finally {
			request.abort();
		}
		pack();
	}
	
	private String submit(String loginname, String door) {
		String url = "http://login.sina.com.cn/member/getpwd/getpwd1.php";
		HttpPost request = new HttpPost(url);
//		System.out.println("submit:" + loginname + ":" + door);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("entry", "sso"));
		list.add(new BasicNameValuePair("mode", ""));
		list.add(new BasicNameValuePair("appgroup", ""));
		list.add(new BasicNameValuePair("loginname", loginname));
		list.add(new BasicNameValuePair("door", door));
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(list, "gb2312");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entity.setContentType("application/x-www-form-urlencoded");
		request.setEntity(entity);
		try {
			HttpResponse response = client.execute(request, context);
			String result = EntityUtils.toString(response.getEntity(), "gb2312");
			//			System.out.println(result);
			int i = result.indexOf("<td colspan=\"4\" class=\"txta\">");
			String s = null;
			if(i > -1) {
				s = result.substring(i + "<td colspan=\"4\" class=\"txta\">".length(), result.indexOf("</td>", i));
			}
//			System.out.println(s);
			return s;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.abort();
		}
		return null;
	}
	
	public static void main(String[] args) {
		SinaGetpwd sinaGetpwd = new SinaGetpwd();
		sinaGetpwd.setCaptcha();
	}
}
