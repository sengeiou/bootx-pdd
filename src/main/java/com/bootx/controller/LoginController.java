
package com.bootx.controller;

import com.bootx.controller.admin.BaseController;
import com.bootx.entity.Member;
import com.bootx.security.UserAuthenticationToken;
import com.bootx.service.MemberService;
import com.bootx.service.UserService;
import com.bootx.util.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller - 会员登录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@RestController("memberLoginController")
@RequestMapping("/pdd/login")
@CrossOrigin
public class LoginController extends BaseController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private UserService userService;

	/**
	 * 登录页面
	 */
	@PostMapping
	public Map<String,Object> index(String type,String username, String password, HttpServletRequest request) {
		Map<String,Object> data = new HashMap<>();
		data.put("type",type);
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			data.put("status","error");
			data.put("content","请输入用户名或密码");
			return data;
		}
		Member member = memberService.findByUsername(username);
		if(member==null){
			data.put("status","error");
			data.put("content","用户名或密码输入错误");
			return data;
		}
		if(!member.isValidCredentials(password)){
			data.put("status","error");
			data.put("content","用户名或密码输入错误");
			return data;
		}
		data.put("status","ok");
		data.put("content","登陆成功");
		Map<String,Object> user = new HashMap<>();
		user.put("id",member.getId());
		user.put("username",member.getUsername());
		data.put("user",user);
		data.put("token", JWTUtils.create(member.getId()+"",user));
		userService.login(new UserAuthenticationToken(Member.class, username, password, false, request.getRemoteAddr()));
		return data;
	}

}