package com.jwatprojectdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AuthController {
	@RequestMapping("/login/form")
	public String index(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập");
		return "login";
	}
	@RequestMapping("/login/success")
	public String success(Model model) {
		return "redirect:/admin";
	}
	@RequestMapping("/login/error")
	public String fail(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại");
		return "login";
	}
	@RequestMapping("/access-denied")
	public String denied(Model model) {
		model.addAttribute("message", "Không có quyền truy cập");
		return "login";
	}
	@RequestMapping("/logout/success")
	public String logout(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "login";
	}
}
