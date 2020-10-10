package com.fourfaith.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 短信接口，勿动！
 * @author Administrator
 * 2017年2月28日
 */
public class Huaxin_webjzjs {

	// 使用StringBuffer的append获得xml形式的字符串
	StringBuffer sub = new StringBuffer();
	BufferedReader br = null;
	URL url = null;
	HttpURLConnection con;
	String line;
	
	public static void main(String[] args) {
		System.out.println("执行开始......");
		xmlEntity xmlentity = new xmlEntity();
		String xml = null;
		Huaxin_webjzjs t = new Huaxin_webjzjs();
		String systemName = "甘州区";
		String deviceName = "沙井站";
		String deviceContact = "赵进鑫";
		String devicePhone = "18894261916";
		String phone = "18894261916";
		// 发送调用
		xml = t.SendMessage("", "szzd00477", "szzd00477ab", devicePhone, "【金志技术】现派遣到"+systemName+deviceName+"维修，负责人姓名："+deviceContact+"；电话："+phone+"；维修完后请致电公司反馈维修情况，谢谢。", "").toString();
		System.out.println(xml);
		xmlentity.setReturnstatus("returnstatus");
		xmlentity.setMessage("message");
		xmlentity.setRemainpoint("remainpoint");
		xmlentity.setTaskID("taskID");
		xmlentity.setSuccessCounts("successCounts");
		xmlentity = t.readStringXmlCommen(xmlentity, xml);
		System.out.println("状态" + xmlentity.getReturnstatus() + "返回信息"
				+ xmlentity.getMessage() + "成功条数"
				+ xmlentity.getSuccessCounts());
		System.out.println("执行成功......");
	}
	
	/**
	 * TODO :发送短信
	 * @param userid    企业ID，为空
	 * @param account   登陆名称
	 * @param password  接口密码，不是登陆密码
	 * @param mobile    发送人姓名
	 * @param content   发送内容
	 * @param sendTime  发送时间，为空
	 */
	@SuppressWarnings("finally")
	public StringBuffer SendMessage(String userid, String account,
			String password, String mobile, String content, String sendTime) {
		try {
			// 设置发送内容的编码方式
			String send_content = URLEncoder.encode(
					content.replaceAll("<br/>", " "), "UTF-8");// 发送内容

			url = new URL("http://sz.ipyy.net/sms.aspx?action=send&userid="
					+ userid + "&account=" + account + "&password=" + password
					+ "&mobile=" + mobile + "&content=" + send_content
					+ "&sendTime=" + sendTime + "");
			con = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			
			while ((line = br.readLine()) != null) {
				// 追加字符串获得XML形式的字符串
				sub.append(line + "");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return sub;
		}
	}

	// XML字符串解析通用方法
	public xmlEntity readStringXmlCommen(xmlEntity xmlentity, String xml) {
		xmlEntity xe = new xmlEntity();
		Document doc = null;
		try {
			// 将字符转化为XML
			doc = DocumentHelper.parseText(xml);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 拿到根节点的名称
			// System.out.println("根节点：" + rootElt.getName());

			// 获取根节点下的子节点的值
			if (xmlentity.getReturnstatus() != null) {
				xe.setReturnstatus(rootElt.elementText(
						xmlentity.getReturnstatus()).trim());
			}
			if (xmlentity.getMessage() != null) {
				xe.setMessage(rootElt.elementText(xmlentity.getMessage())
						.trim());
			}
			if (xmlentity.getRemainpoint() != null) {
				xe.setRemainpoint(rootElt.elementText(
						xmlentity.getRemainpoint()).trim());
			}
			if (xmlentity.getTaskID() != null) {
				xe.setTaskID(rootElt.elementText(xmlentity.getTaskID()).trim());
			}
			if (xmlentity.getSuccessCounts() != null) {
				xe.setSuccessCounts(rootElt.elementText(
						xmlentity.getSuccessCounts()).trim());
			}
			if (xmlentity.getPayinfo() != null) {
				xe.setPayinfo(rootElt.elementText(xmlentity.getPayinfo())
						.trim());
			}
			if (xmlentity.getOverage() != null) {
				xe.setOverage(rootElt.elementText(xmlentity.getOverage())
						.trim());
			}
			if (xmlentity.getSendTotal() != null) {
				xe.setSendTotal(rootElt.elementText(xmlentity.getSendTotal())
						.trim());
			}
			// 接收状态返回的报告
			if (rootElt.hasMixedContent() == false) {
				System.out.println("无返回状态！");
			} else {
				for (int i = 1; i <= rootElt.elements().size(); i++) {
					if (xmlentity.getStatusbox() != null) {
						System.out.println("状态" + i + ":");
						// 获取根节点下的子节点statusbox
						Iterator<?> iter = rootElt.elementIterator(xmlentity
								.getStatusbox());
						// 遍历statusbox节点
						while (iter.hasNext()) {
							Element recordEle = (Element) iter.next();
							xe.setMobile(recordEle.elementText("mobile").trim());
							xe.setTaskid(recordEle.elementText("taskid").trim());
							xe.setStatus(recordEle.elementText("status").trim());
							xe.setReceivetime(recordEle.elementText(
									"receivetime").trim());
							System.out.println("对应手机号：" + xe.getMobile());
							System.out.println("同一批任务ID：" + xe.getTaskid());
							System.out.println("状态报告----10：发送成功，20：发送失败："
									+ xe.getStatus());
							System.out.println("接收时间：" + xe.getReceivetime());
						}
					}

				}
			}
			// 错误返回的报告
			if (xmlentity.getErrorstatus() != null) {
				// 获取根节点下的子节点errorstatus
				Iterator<?> itererr = rootElt.elementIterator(xmlentity
						.getErrorstatus());
				// 遍历errorstatus节点
				while (itererr.hasNext()) {
					Element recordElerr = (Element) itererr.next();
					xe.setError(recordElerr.elementText("error").trim());
					xe.setRemark(recordElerr.elementText("remark").trim());
					System.out.println("错误代码：" + xe.getError());
					System.out.println("错误描述：" + xe.getRemark());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return xe;
	}
	
}