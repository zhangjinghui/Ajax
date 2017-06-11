package com.zjh.ajax.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.zjh.ajax.entities.Cart;

@WebServlet("/addToCart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addToCart(name, price);

		// 如果 json 对象是从服务器生成的，那么 属性的键必须使用双引号，而不能是单引号
		// StringBuilder sb = new StringBuilder();
		// sb.append("{")
		// .append("\"name\":\""+ name+"\",")
		// .append("\"totalNumber\":"+ cart.getTotalNumber() +",")
		// .append("\"totalPrice\":" + cart.getTotalPrice())
		// .append("}");

		// 使用 jackson jar生成 jsonStr
		ObjectMapper mapper = new ObjectMapper();
		String sb = mapper.writeValueAsString(cart);
		System.out.println(sb);

		response.setContentType("text/javascript;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(sb);
	}

}
