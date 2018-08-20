package bcm.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bcm.domain.BcmUser;
import bcm.service.AuthService;

@RestController
@RequestMapping("/Auth")
public class AuthController {
	
	@Autowired AuthService authService; 
	
	@PostMapping("SignUp")	
	public Object signUp(BcmUser user, Model model) {
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		int chkEmail = authService.identify(user.getEmail());
		
		System.out.println(chkEmail);
		
		if(chkEmail == 0) {
			
			System.out.println("���̵� �������� ����");
			
			int cnt = authService.add(user);
			
			if(cnt == 1) {
			
				resultMap.put("state", "success");
				
			}
			
		}else {
			
			resultMap.put("state", "fail");
			
		}
		
		return resultMap;
	
	}
	
	@PostMapping("SignIn")
	public Object SignIn(BcmUser user, HttpSession session) {
		
		HashMap<String,Object> resultMap = new HashMap<>();
		
		
		try {
			BcmUser loginUser = authService.getMember(user); 

			if(loginUser == null)throw new Exception("�����Դϴ�.");
		
			session.setAttribute("user", loginUser);
			resultMap.put("state","success");
		
		}catch(Exception e) {
			resultMap.put("state", "error");
			resultMap.put("message", e.getMessage());
		}
		
		
		return resultMap;
	}
	
	
	@RequestMapping("/chkEmail")
	public Object chkEmail(String email, Model model) {

		HashMap<String, Object> resultMap = new HashMap<>();
		System.out.println(email);
		int chkEmail = authService.identify(email);
		System.out.println(chkEmail);
		if(chkEmail == 0) {
			System.out.println("����");
			resultMap.put("state", "success");
		}else {
			resultMap.put("state", "fail");
		}
		
		return resultMap;
		
	}
}
