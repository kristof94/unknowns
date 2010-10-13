import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class Test {
	public strictfp static void main(String[] args) throws Exception {
//		testCalculator();
//		testPost();
//		testClient();
//		bb();
		bd();
	}
	
	public static void bd() throws Exception {
		String path = "C:/Documents and Settings/Administrator/桌面/Civilization_T320X480";
		ClassPool cp = ClassPool.getDefault();
		cp.insertClassPath(path);
		CtClass cc = cp.get("bd");
		cc.addField(CtField.make("private long crack = System.currentTimeMillis();", cc));
		for(CtMethod cm:cc.getDeclaredMethods()) {
			String str = "{System.out.println(\"crack:"+Modifier.toString(cm.getModifiers())+ " "  + cm.getName() + cm.getMethodInfo().getDescriptor()+":\"+System.currentTimeMillis());}";
			cm.insertBefore(str);
			System.out.println(str);
			String methodInfo = cm.getMethodInfo().toString();
			System.out.println(methodInfo);
			if("a (Lcf;)Z".equals(methodInfo)) {
				StringBuilder sb = new StringBuilder();
				sb.append("long time = 2000 - System.currentTimeMillis() + crack;");
				sb.append("System.out.println(\"crack:\"+time);");
				sb.append("if(time>0) Thread.sleep(time);");
				sb.append("crack = System.currentTimeMillis();");
				cm.insertBefore(sb.toString());
				System.out.println(sb.toString());
			}
//			cm.insertBefore(str);
		}
//		cm.getMethodInfo().rebuildStackMap(cp);
		cc.writeFile();
	}
	
	
	public static void bb() throws Exception {
		String path = "C:/Documents and Settings/Administrator/桌面/Civilization_T320X480";
		ClassPool cp = ClassPool.getDefault();
		cp.insertClassPath(path);
		CtClass cc = cp.get("bb");
		cc.addField(CtField.make("private long crack = System.currentTimeMillis();", cc));
		for(CtMethod cm:cc.getDeclaredMethods()) {
			String str = "{System.out.println(\"crack:"+Modifier.toString(cm.getModifiers())+ " "  + cm.getName() + cm.getMethodInfo().getDescriptor()+":\"+System.currentTimeMillis());}";
			cm.insertBefore(str);
			System.out.println(str);
			String methodInfo = cm.getMethodInfo().toString();
			System.out.println(methodInfo);
//			if("a ()Z".equals(methodInfo) || "a (Z)Ljava/io/DataOutputStream;".equals(methodInfo)) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("long time = 1000 - System.currentTimeMillis() + crack;");
//				sb.append("System.out.println(\"crack:\"+time);");
//				sb.append("if(time>0) Thread.sleep(time);");
//				sb.append("crack = System.currentTimeMillis();");
//				cm.insertBefore(sb.toString());
//				System.out.println(sb.toString());
//			}
//			cm.insertBefore(str);
		}
//		cm.getMethodInfo().rebuildStackMap(cp);
		cc.writeFile();
	}
	
	public static void testCalculator() {
		Calculator c = new Calculator();
		c.appendNumber(3);
		c.inverse();
		c.operator('+');
		c.appendNumber(2);
		System.out.println(c.equal());
	}
	
	public static void testClient() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://cnota.cn/g.jsp?18500_895");
		HttpResponse response = client.execute(request);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if(entity != null) {
			System.out.println(EntityUtils.toString(entity));
		}
		request.abort();
		client.getConnectionManager().shutdown();
	}
	
	public static void testPost() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://192.168.1.139:8080/activate_notify/log?gid=1&mt=3");
		HttpEntity entity = new StringEntity("900");
		request.setEntity(entity);
		client.execute(request);
	}
}
