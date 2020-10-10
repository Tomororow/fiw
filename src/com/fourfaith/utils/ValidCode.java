package com.fourfaith.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成4位随机验证码
 */
@SuppressWarnings("unused")
public class ValidCode extends HttpServlet {

	private static final long serialVersionUID = 7984643570657748110L;

	private static final String generationStrategy = "1"; // 验证码生成策略，1生成4位纯数字，其他生成4位数字或字母

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageIO.write(getImage(request), "JPEG", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private BufferedImage getImage(HttpServletRequest request) {
		int width = 80, height = 39;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);

		// 取随机产生的认证码(4位数字)
		String rand = StringUtils.getRandomStr(4, true);
		// 将认证码显示到图象中
		g.setColor(Color.black);
		String numberStr = rand;
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
		String Str = numberStr.substring(0, 1);

		g.drawString(Str, 8, 27);

		Str = numberStr.substring(1, 2);
		g.drawString(Str, 25, 27);
		Str = numberStr.substring(2, 3);
		g.drawString(Str, 45, 27);

		Str = numberStr.substring(3, 4);
		g.drawString(Str, 65, 27);

		// 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到
		Random random = new Random();
		for (int i = 0; i < 150; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// 图象生效
		g.dispose();
		request.getSession().setAttribute("validCode", numberStr);
		return image;
	}

}