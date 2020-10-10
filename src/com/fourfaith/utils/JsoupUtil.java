package com.fourfaith.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 抓取网上的数据
 * @author administrator
 */
@SuppressWarnings("unused")
public class JsoupUtil {
	
	static String url = "http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";

	/**
	 * 获取指定HTML 文档指定的body
	 * @throws IOException
	 */
	private static void bolgBody() throws IOException {
		// 直接从字符串中输入 HTML 文档
		String html = "<html><head><title> 开源中国社区 </title></head>"
				+ "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
		Document doc = Jsoup.parse(html);

		// 从 URL 直接加载 HTML 文档
		Document doc2 = Jsoup.connect(url).get();
		String title = doc2.body().toString();
	}

	/**
	 * 获取网页中超链接的标题和链接
	 */
	public static void article() {
		Document doc;
		try {
			// doc =
			// Jsoup.connect("http://news.baidu.com/ns?ct=0&rn=20&ie=utf-8&bs=%E6%88%90%E5%93%81%E6%B2%B9%E4%BB%B7&rsv_bp=1&sr=0&cl=2&f=8&prevct=no&tn=newstitle&word=%E6%88%90%E5%93%81%E6%B2%B9%E4%BB%B7").get();
			// Elements ListDiv =
			// doc.getElementsByAttributeValue("class","c-title");
			// 水质信息
			// doc =
			// Jsoup.connect("http://news.xiamenwater.com/SmallClass.asp?BName=%C6%F3%CE%F1%B9%AB%BF%AA&BType=0&SName=%CB%AE%D6%CA%D0%C5%CF%A2&displayTree=1&model=3").get();
			// 停水通知
			doc = Jsoup
					.connect(
							"http://news.xiamenwater.com/SmallClass.asp?BName=%C6%F3%CE%F1%B9%AB%BF%AA&BType=0&SName=%CD%A3%CB%AE%CD%A8%D6%AA&displayTree=1&model=3")
					.get();
			Elements ListDiv31 = doc.getElementsByAttributeValue("style",
					"TABLE-LAYOUT: fixed");
			;
			Elements ListDiv3 = ListDiv31.select("tr").select("table");
			for (Element lineInfo : ListDiv3) {
				Elements links = lineInfo.getElementsByTag("tr");
				for (Element element : links) {
					String linkHref = element.getElementsByTag("a")
							.attr("href");
					String linkText = element.getElementsByTag("a").text()
							.trim();
					String time = element.getElementsByTag("span").text()
							.trim();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取指定博客文章的内容
	 */
	public static void blog() {
		Document doc;
		try {
			doc = Jsoup.connect(
					"http://www.four-faith.com/html/yaocezhongduan/53.html")
					.get();
			// doc =
			// Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
			/*
			 * Elements ListDiv =
			 * doc.getElementsByAttributeValue("class","postBody");
			 */
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"property");

			for (Element element : ListDiv) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		article();
	}
	
}