package wtw;

import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Text;

public class WTWClient {
	private HttpClient client = new HttpClient();
	
	private boolean isConnection = false;
	
	private String rootPath;
	
	private String currentPath;
	
	private String parentPath;
	
	public static void main(String[] args) {
		new WTWClient();
	}
	
	public String getHtmlContent(String url) {
		if(!url.startsWith("http://")) return "请求的地址无效";
		if(isConnection) return "之前的连接未结束，请不要频繁请求";
		isConnection = true;
		HttpGet request = null;
		String content = "";
		try {
			request = new HttpGet(url);
			HttpResponse response;
			long time = System.currentTimeMillis();
			response = client.execute(request);
			System.out.println(System.currentTimeMillis() - time);
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				System.out.println(entity.getContentType());
				Header contentType = entity.getContentType();
				if(contentType.getValue().contains("text/vnd.wap.wml")) {
					time = System.currentTimeMillis();
					content = EntityUtils.toString(entity);
					System.out.println(System.currentTimeMillis() - time);
					int index = url.indexOf("/", 7);
					if(index != -1) {
						rootPath = url.substring(0, index);
						currentPath = url.substring(0, url.lastIndexOf("/"));
						parentPath = currentPath.substring(0, url.lastIndexOf("/"));
					} else {
						rootPath = url;
						currentPath = url;
						parentPath = url;
					}
					System.out.println("rootPath:" + rootPath);
					System.out.println("currentPath:" + currentPath);
					System.out.println("parentPath:" + parentPath);
					time = System.currentTimeMillis();
					content = content
							.replace("﻿<?xml version='1.0' encoding='utf-8'?>", "")
							.replace("﻿<?xml version=\"1.0\" encoding=\"utf-8\"?>", "")
							.replace("﻿<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "")
							.replace(
									"<!DOCTYPE wml PUBLIC \"-//WAPFORUM//DTD WML 1.3//EN\" \"http://www.wapforum.org/DTD/wml13.dtd\">",
									"")
							.replace(
									"<!DOCTYPE wml PUBLIC \"-//WAPFORUM//DTD WML 1.1//EN\" \"http://www.wapforum.org/DTD/wml_1.1.xml\">",
									"")
							.replace(
									"<!DOCTYPE wml PUBLIC '-//WAPFORUM//DTD WML 1.1//EN' 'http://www.wapforum.org/DTD/wml_1.1.xml'>",
									"").replace("&nbsp;", " ").trim();
					System.out.println(System.currentTimeMillis() - time);
					System.out.println(content);
					time = System.currentTimeMillis();
					content = wml2Html(content).replace(
							"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n", "");
					System.out.println(System.currentTimeMillis() - time);
					//					System.out.println(content);
				} else {
					content = "请求的不是wap页面";
					System.out.println(EntityUtils.toString(entity));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			content = "连接服务器时出现意外错误";
		}
		if(request != null) request.abort();
		isConnection = false;
		return content;
	}
	
	/**
	 * 将xml转换成对应的html，格式如下 <div> <div>转换后的html内容</div> </div>
	 * @param wml
	 * @return Document
	 */
	public String wml2Html(String wml) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("div");
		try {
			Element wmlRoot = DocumentHelper.parseText(wml).getRootElement();
			iteratorNode(root, wmlRoot);
		} catch(DocumentException e) {
			root.addElement("div").setText("无法解析该wap页面");
			e.printStackTrace();
		}
		return doc.asXML();
	}
	
	/**
	 * 迭代sourceElement里面的节点，将WAP节点转换成WEB节点
	 * @param rootElement sourceElement内的节点转换后将添加到rootElement节点内，如果rootElement为null则创建div节点
	 * @param sourceElement 要解析的所有节点的父节点
	 * @return 返回处理后的rootElement
	 */
	private Element iteratorNode(Element rootElement, Element sourceElement) {
		if(rootElement == null) {
			rootElement = DocumentHelper.createElement("div");
		}
		for(Iterator iter = sourceElement.nodeIterator(); iter.hasNext();) {
			Object node = iter.next();
			if(node instanceof Element) {
				Element element = (Element)node;
				String elementName = element.getName();
				if(elementName.equals("br")) {
					rootElement.addElement("br");
				} else if(elementName.equals("img")) {
					Element imgElement = rootElement.addElement("img");
					String src = element.attributeValue("src");
					if(src != null) {
						imgElement.addAttribute("src", analysisUrl(src));
					}
					String alt = element.attributeValue("alt");
					if(alt != null) {
						imgElement.addAttribute("title", alt);
						imgElement.addAttribute("alt", alt);
					}
				} else if(elementName.equals("a")) {
					Element aElement = rootElement.addElement("a");
					String href = element.attributeValue("href");
					if(href != null) {
						if(href.startsWith("#")) {
							aElement.addAttribute("href", href);
						} else {
							aElement.addAttribute("href", analysisUrl(href));
						}
					}
					if(element.isTextOnly()) {
						aElement.addText(element.getText());
					} else {
						iteratorNode(aElement, element);
					}
				} else if(elementName.equals("input")) {
					Element inputElement = rootElement.addElement("input");
					String type = element.attributeValue("type");
					if(type != null) {
						inputElement.addAttribute("type", type);
					}
					String name = element.attributeValue("name");
					if(name != null) {
						inputElement.addAttribute("id", name);
						inputElement.addAttribute("name", name);
					}
					String value = element.attributeValue("value");
					if(value != null) {
						inputElement.addAttribute("value", value);
					}
					String size = element.attributeValue("size");
					if(size != null) {
						inputElement.addAttribute("size", size);
					}
				} else if(elementName.equals("anchor")) {
					Element formElement = rootElement.addElement("form");
					Element goElement = element.element("go");
					if(goElement == null) {
						formElement.addText(element.getText());
					} else {
						String href = goElement.attributeValue("href");
						if(href != null) {
							formElement.addAttribute("action", analysisUrl(href));
						}
						String method = goElement.attributeValue("method");
						if(method == null) method = "get";
						formElement.addAttribute("method", method);
						iteratorNode(formElement, goElement);
						String text = element.getTextTrim();
						if(text == null) {
							Element imgElement = formElement.element("img");
							if(imgElement != null) {
								formElement.remove(imgElement);
								Element buttonElement = formElement.addElement("button");
								buttonElement.addAttribute("type", "submit");
								buttonElement.add(imgElement);
							}
						} else {
							Element inputElement = formElement.addElement("input");
							inputElement.addAttribute("type", "submit");
							inputElement.addAttribute("value", text);
						}
					}
				} else if(elementName.equals("select")) {
					Element selectElement = rootElement.addElement("select");
					String name = element.attributeValue("name");
					if(name != null) {
						selectElement.addAttribute("id", name);
						selectElement.addAttribute("name", name);
					}
					iteratorNode(selectElement, element);
				} else if(elementName.equals("option")) {
					Element optionElement = rootElement.addElement("option");
					String value = element.attributeValue("value");
					if(value != null) {
						optionElement.addAttribute("value", value);
					}
					optionElement.addText(element.getText());
				} else if(elementName.equals("postfield")) {
					addHiddenElement(rootElement, element.attributeValue("name"), element
							.attributeValue("value"));
				} else if(elementName.equals("p")) {
					iteratorNode(rootElement.addElement("p"), element);
				} else if(elementName.equals("strong")) {
					iteratorNode(rootElement.addElement("strong"), element);
				} else if(elementName.equals("b")) {
					iteratorNode(rootElement.addElement("b"), element);
				} else if(elementName.equals("do")) {
					Element buttonElement = rootElement.addElement("input");
					buttonElement.addAttribute("type", "button");
					buttonElement.addAttribute("value", element.attributeValue("label"));
					List<Element> list = element.elements("prev");
					if(list.size() == 1) {
						buttonElement.addAttribute("onclick", "history.back();");
					} else {
						buttonElement.addAttribute("onclick", "alert('未知事件');");
					}
				} else if(elementName.equals("card")) {
					Element cardElement = rootElement.addElement("div");
					cardElement.addAttribute("class", "card");
					String id = element.attributeValue("id");
					if(id != null) cardElement.addAttribute("id", id);
					String title = element.attributeValue("title");
					if(title != null) {
						cardElement.addElement("h1").addText(title);
						//						addHiddenElement(cardElement,"captiondata",title);
					}
					String ontimer = element.attributeValue("ontimer");
					if(ontimer != null) {
						addHiddenElement(cardElement, "ontimer", analysisUrl(ontimer));
					}
					Element bodyElement = cardElement.addElement("div");
					bodyElement.addAttribute("class", "innercard");
					iteratorNode(bodyElement, element);
				} else if(elementName.equals("timer")) {
					String timerValue = element.attributeValue("value");
					if(timerValue == null) {
						timerValue = "1";
					}
					addHiddenElement(rootElement, "timer", timerValue);
				} else if(elementName.equals("head")) {
					//head不处理
					//					iteratorNode(rootElement.addElement("head"), element);
				} else {
					System.out.println(elementName + "是未处理的节点,内容是:" + element.getText());
					rootElement.add(element.createCopy());
				}
			} else if(node instanceof Text) {
				Text text = (Text)node;
				rootElement.addText(text.getText());
			} else if(node instanceof Comment) {
				//注释不处理
			} else if(node instanceof Entity) {
				Entity entity = (Entity)node;
				rootElement.addText(entity.getText());
			} else {
				System.out.println(node + "无法解析");
			}
		}
		return rootElement;
	}
	
	private String analysisUrl(String path) {
		String url = null;
		if(path.startsWith("http://")) {
			url = path;
		} else {
			if(path.startsWith("/")) {
				url = rootPath + path;
			} else if(path.startsWith("../")) {
				url = parentPath + path.substring(2);
			} else {
				url = currentPath + "/" + path.replace("./", "");
			}
		}
		return url;
	}
	
	private Element addHiddenElement(Element parentElement, String id, String value) {
		Element hiddenElement = parentElement.addElement("input");
		hiddenElement.addAttribute("id", id);
		hiddenElement.addAttribute("name", id);
		hiddenElement.addAttribute("value", value);
		hiddenElement.addAttribute("type", "hidden");
		return hiddenElement;
	}
	
	@Override
	protected void finalize() throws Throwable {
		client.getConnectionManager().shutdown();
	}
}
