import java.util.regex.Pattern;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Mail {
	private static Pattern pattern = Pattern.compile(".*=\\?.*\\?=.*");
	private static String host = "mail.3guu.com";
	private static int port = 110;
	private static String username = "xxx@3guu.com";
	private static String password = "";
	
	public static void main(String[] args) throws Exception {
//		String s = "\"=?gb2312?B?uti6o7fl?=\" <hehf@3guu.com>";
//		System.out.println(s.matches(".*=\\?.*\\?=.*"));
//		System.out.println(MimeUtility.decodeText(s));
//		getCharset(s);
//		System.out.println(MimeUtility.decodeText(MimeUtility.unfold("=?gb2312?Q?zhilinzhai@homail.com =28E-mail Address Not Verified=29=20=CF=EB=B8=FA=C4=FA=BD=BB=CC=B8=A3=A1?=")));
		getMessage();
	}
	
	private static void getMessage() throws Exception {
		URLName url = new URLName("pop3", host, port, null, username, password);
		Session session = Session.getInstance(System.getProperties());
//		session.setDebug(true);
		Store store = session.getStore(url);
		store.connect();
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		byte[] b = new byte[1024*1024];
		for(Message message:inbox.getMessages()) {
//			if(!message.getContentType().contains("text")) {
//				continue;
//			}
//			Enumeration<Header> e = message.getAllHeaders();
//			while(e.hasMoreElements()) {
//				Header header = e.nextElement();
//				System.out.println(header.getName()+":"+header.getValue());
//			}
//			BufferedInputStream in = new BufferedInputStream(message.getInputStream());
//			int l;
//			StringBuilder sb = new StringBuilder();
//			while((l=in.read(b))>-1) {
//				if(l!=0) sb.append(new String(b));
//			}
//			in.close();
//			System.out.println("Body:"+sb.toString());
//			System.out.println("Content Type:"+message.getContentType());
//			System.out.println("Body:"+new String(message.getContent().toString().getBytes("ISO-8859-1"), charset));
//			if(true) {
//				System.out.println("===========================");
//				continue;
//			}
			//获得邮件的部分信息，如头部信息，送信人的署名，送信人的邮件地址
			System.out.println("Content Type："+message.getContentType());
			String charset = getCharset(message.getContentType());
			String[] str = message.getHeader("From");
			if(str!=null) {
//				InternetAddress from = (InternetAddress)message.getFrom()[0];
//				System.out.println("From：" + from.getPersonal());
//				System.out.println("Address：" + from.getAddress());
//				System.out.println("From：" + Arrays.toString(str));
				for(String s:str) {
					if(charset==null && s.startsWith("=?")) {
						int index = s.indexOf('?', 3);
						if(index>-1) charset = s.substring(2, index);
					}
					System.out.println("From："+getText(s, charset));
				}
			}
			str = message.getHeader("Subject");
			if(str!=null) {
				for(String s:str) {
					if(charset==null && s.startsWith("=?")) {
						int index = s.indexOf('?', 3);
						if(index>-1) charset = s.substring(2, index);
					}
					System.out.println("Subject："+getText(s, charset));
				}
			}
			if(charset==null) charset = "UTF-8";
			//以下代码用来获取邮件的主体信息
			Object content = message.getContent();
//			System.out.println("Subject："+message.getSubject());
			if(content instanceof MimeMultipart) {
				int count = ((MimeMultipart)content).getCount();
				for(int i = 0; i < count; i++) {
					BodyPart part = ((MimeMultipart)content).getBodyPart(i);
					String disposition = part.getDisposition();
					if((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
						//以下代码将获得的附件保存到当前目录下，以part.getFileName()为文件名，也既是附件的名称。
//						File filename = new File(MimeUtility.decodeText(part.getFileName()));
//						for(int k = 0; filename.exists(); k++) {
//							filename = new File(part.getFileName() + k);
//						}
//						FileOutputStream myFileoutputstream = new FileOutputStream(filename);
//						int chunk = part.getSize();//获得附件的大小，不一定很准确。
//						byte[] buffer = new byte[chunk];
//						InputStream instream = part.getInputStream();
//						instream.read(buffer, 0, chunk);
//						myFileoutputstream.write(buffer, 0, chunk);
//						instream.close();
//						myFileoutputstream.close();
						System.out.println("File Name:"+getText(part.getFileName(), charset));
					} else {
						Object partContent = part.getContent();
						if(partContent instanceof String) {
//							System.out.println("Content："+partContent);
						} else {
							System.out.println(partContent+" is unknown type!");
						}
					}
				}
			} else if(content instanceof String) {
//				System.out.println("Content："+new String(content.toString().getBytes("ISO-8859-1"), charset));
			} else {
				System.out.println(content+" is unknown type!");
			}
			System.out.println("------------------------------------------------------------ ");
		}
		inbox.close(false);
		store.close();
	}

	private static String getCharset(String str) {
		int i1 = str.indexOf("charset=");
		if(i1>-1) {
			i1 += 8;
			if(str.charAt(i1)=='"') {
				i1++;
				int i2 = str.indexOf('"', i1);
				return str.substring(i1, i2);
			} else {
				int i2 = str.indexOf(' ', i1);
				if(i2>-1) return str.substring(i1, i2);
				else return str.substring(i1);
			}
		}
		return null;
	}
	
	private static String getText(String str, String charset) throws Exception {
		if(pattern.matcher(str).matches())
			return MimeUtility.decodeText(str);
		else
			return charset==null?str:new String(str.getBytes("ISO-8859-1"), charset);
	}
}
