package com.zjh.ajax.jackson.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class User {
	private Integer id;
	private String username;

	// 在 getter 方法上添加 @JsonIgnore 可以忽略该属性
	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// json 的属性名是根据 getter 方法名而定的，与属性名无关
	public String getName() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return 12;
	}

	// 不仅可以操作对象，也可以操作集合
	public List getList() {
		return Arrays.asList("aaa", "bbb", "ccc");
	}

	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		// 构造 ObjectMapper 对象
		ObjectMapper mapper = new ObjectMapper();

		// 调用 ObjectMapper 对象的 writeValueAsString()方法，返回 String
		String jsonStr = mapper.writeValueAsString(new User());
		System.out.println(jsonStr);
	}

}
