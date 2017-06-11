package com.zjh.ajax.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/validateUsername")
public class ValidateUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> usernames = Arrays.asList("AAA", "BBB", "CCC");
		String username = request.getParameter("username");
		String result = null;
		if (usernames.contains(username)) {
			result = "<font color='red'>用户名已存在</font>";
		} else {
			result = "<font color='green'>用户名可以使用</font>";
		}

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(result);
	}

}
