package org.zpp.security.rbac.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.zpp.security.rbac.dto.AdminCondition;
import org.zpp.security.rbac.dto.AdminInfo;
import org.zpp.security.rbac.service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 获取当前登录的管理员信息
	 * @param user
	 * @return
	 */
	@GetMapping("/me")
	public AdminInfo me(@AuthenticationPrincipal UserDetails user) {
		AdminInfo info = new AdminInfo();
		info.setUsername(user.getUsername());
		return info;
	}

	/**
	 * 创建管理员
	 * @param adminInfo
	 * @return
	 */
	@PostMapping
	public AdminInfo create(@RequestBody AdminInfo adminInfo) {
		return adminService.create(adminInfo);
	}
	
	/**
	 * 修改管理员信息
	 * @param adminInfo
	 * @return
	 */
	@PutMapping("/{id}")
	public AdminInfo update(@RequestBody AdminInfo adminInfo) {
		return adminService.update(adminInfo);
	}
	
	/**
	 * 删除管理员
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		adminService.delete(id);
	}

	/**
	 * 获取管理员详情
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public AdminInfo getInfo(@PathVariable Long id) {
		return adminService.getInfo(id);
	}

	/**
	 * 分页查询管理员
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public Page<AdminInfo> query(AdminCondition condition, Pageable pageable) {
		return adminService.query(condition, pageable);
	}

}
