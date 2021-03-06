package com.bootx.common;

import com.bootx.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 消息
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public class Result {

	@JsonView({BaseEntity.BaseView.class,BaseEntity.ViewView.class, BaseEntity.ListView.class, BaseEntity.EditView.class, BaseEntity.BaseView.class, BaseEntity.PageView.class})
	private Integer code;

	@JsonView({BaseEntity.BaseView.class,BaseEntity.ViewView.class, BaseEntity.ListView.class, BaseEntity.EditView.class, BaseEntity.BaseView.class, BaseEntity.PageView.class})
	private String content;

	@JsonView({BaseEntity.BaseView.class,BaseEntity.ViewView.class, BaseEntity.ListView.class, BaseEntity.EditView.class, BaseEntity.BaseView.class, BaseEntity.PageView.class})
	private Object data;


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 构造方法
	 */
	public Result() {
	}

	public Result(Integer code, String content, Object data) {
		this.code = code;
		this.content = content;
		this.data = data;
	}

	public static Result success(String content, Object data) {
		return new Result(0, content, data);
	}

	public static Result ok (Integer code, String content, Object date) {
		return new Result(code,content,date);
	}
	public static Result ok (String content, Object date) {
		return new Result(200,content,date);
	}

	public static Result success(Object data) {
		return new Result(200,"请求成功", data);
	}


	public static Result error(String content) {
		return new Result(400,content, null);
	}
}