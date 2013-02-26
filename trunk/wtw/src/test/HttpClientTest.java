package test;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class HttpClientTest {
	public static void main(String[] args) {
		try {
			//			test();
			Map<String, String> brandMap = new LinkedHashMap<String, String>();
			Map<String, List<String>> modelMap = new LinkedHashMap<String, List<String>>();
			Map<String, Map<String, List<String>>> resolutionMap = new LinkedHashMap<String, Map<String, List<String>>>();
			getModel("/spg/index/resolution/", brandMap, modelMap, resolutionMap);
			getModel("/spg/index/resolution-2", brandMap, modelMap, resolutionMap);
			System.out.println("brandMap:" + brandMap);
			System.out.println("modelMap:" + modelMap);
			System.out.println("resolutionMap:" + resolutionMap);
			Document brandXML = DocumentHelper.createDocument();
			Document modelXML = DocumentHelper.createDocument();
			Document resolutionXML = DocumentHelper.createDocument();
			Element brandElement = brandXML.addElement("brands");
			Iterator<Entry<String, String>> i = brandMap.entrySet().iterator();
			while(i.hasNext()) {
				Entry<String, String> e = i.next();
				Element element = brandElement.addElement("brand");
				element.addElement("key").addText(e.getKey());
				element.addElement("value").addText(e.getValue());
			}
			Element modelElement = modelXML.addElement("models");
			Iterator<Entry<String, List<String>>> ii = modelMap.entrySet().iterator();
			while(ii.hasNext()) {
				Entry<String, List<String>> e = ii.next();
				Element element = modelElement.addElement("model");
				element.addElement("key").addText(e.getKey());
				element = element.addElement("list");
				for(String v:e.getValue()) {
					element.addElement("value").addText(v);
				}
			}
			Element resolutionElement = resolutionXML.addElement("resolutions");
			Iterator<Entry<String, Map<String, List<String>>>> iii = resolutionMap.entrySet()
					.iterator();
			while(iii.hasNext()) {
				Entry<String, Map<String, List<String>>> e = iii.next();
				Element element = resolutionElement.addElement("resolution");
				element.addElement("key").addText(e.getKey());
				element = element.addElement("map");
				Map<String, List<String>> m = e.getValue();
				Iterator<Entry<String, List<String>>> mi = m.entrySet().iterator();
				while(mi.hasNext()) {
					Entry<String, List<String>> me = mi.next();
					element.addElement("mkey").addText(me.getKey());
					Element temp = element.addElement("list");
					for(String v:me.getValue()) {
						temp.addElement("value").addText(v);
					}
				}
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setLineSeparator("\r\n");
			Writer out = new PrintWriter("c://brand.xml");
			XMLWriter w = new XMLWriter(out, format);
			w.write(brandXML);
			w.close();
			out.close();
			out = new PrintWriter("c://model.xml");
			w = new XMLWriter(out, format);
			w.write(modelXML);
			w.close();
			out.close();
			out = new PrintWriter("c://resolution.xml");
			w = new XMLWriter(out, format);
			w.write(resolutionXML);
			w.close();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test() throws Exception {
		String url;
		//		url = "http://down.d.cn/spg/index/resolution/";
		url = "http://down.d.cn/spg/index/resolution?keyword=6";
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if(entity != null) {
			System.out.println(EntityUtils.toString(entity));
		}
		request.abort();
		client.getConnectionManager().shutdown();
	}
	
	public static void getModel(String url, Map<String, String> brandMap,
			Map<String, List<String>> modelMap, Map<String, Map<String, List<String>>> resolutionMap)
			throws Exception {
		String str = "http://down.d.cn";
		HttpClient client = new DefaultHttpClient();
		String content = getContent(client, str + url);
		//		System.out.println(content);
		Document doc = DocumentHelper.parseText(content);
		for(Element a:getA(null, doc.getRootElement())) {
			String href = a.attributeValue("href");
			if(href != null && href.contains("resolution?")) {
				Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
				System.out.println(href);
				content = getContent(client, str + href);
				//				System.out.println(content);
				doc = DocumentHelper.parseText(content);
				List<Element> alist = getA(null, doc.getRootElement());
				if(alist.isEmpty()) {
					String ontimer = doc.getRootElement().element("card").attributeValue("ontimer");
					if(ontimer != null) {
						String[] s = ontimer.split("/");
						String brand = s[3];
						List<String> modelList = modelMap.get(brand);
						if(modelList == null) modelList = new LinkedList<String>();
						String model = s[4].replace(".wml", "").replace("+", " ").trim();
						//						System.out.println(brand+":"+model);
						modelList.add(model);
						map.put(brand, Arrays.asList(model));
					} else {
						throw new NullPointerException();
					}
				} else {
					List<String> list = null;
					for(Element m:alist) {
						href = m.attributeValue("href");
						if(href.startsWith("/spg/index/")) {
							String[] s = href.split("/");
							String brand = s[3];
							//							System.out.println(href);
							if(href.endsWith(".wml")) {
								List<String> modelList = modelMap.get(brand);
								if(modelList == null) {
									modelList = new LinkedList<String>();
									modelMap.put(brand, modelList);
								}
								String model = m.getTextTrim();
								//								System.out.println(model);
								modelList.add(model);
								list.add(model);
							} else {
								//								System.out.println(brand);
								if(!brandMap.containsKey(brand))
									brandMap.put(brand, m.getTextTrim());
								list = new LinkedList<String>();
								map.put(brand, list);
							}
						}
					}
				}
				resolutionMap.put(a.getTextTrim(), map);
			}
		}
		client.getConnectionManager().shutdown();
	}
	
	public static String getContent(HttpClient client, String uri) throws Exception {
		HttpGet request = new HttpGet(uri);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String content = null;
		if(entity != null) {
			content = EntityUtils.toString(entity);
		} else {
			content = "";
		}
		request.abort();
		return content;
	}
	
	public static List<Element> getA(List<Element> alist, Element element) throws Exception {
		if(alist == null) alist = new LinkedList<Element>();
		for(Iterator iterator = element.elementIterator(); iterator.hasNext();) {
			Node node = (Node)iterator.next();
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element)node;
				if("a".equals(e.getName())) {
					alist.add(e);
				} else {
					getA(alist, e);
				}
			}
		}
		return alist;
	}
}
