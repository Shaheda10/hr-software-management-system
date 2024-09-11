/*
 * package com.hr.management.controller;
 * 
 * import java.util.List; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.hr.management.entity.Admin; import
 * com.hr.management.payload.AdminPayload; import
 * com.hr.management.service.AdminService;
 * 
 * @RestController // Specifies that this class is a controller and is capable
 * of handling web // requests
 * 
 * @RequestMapping("/api/admin") public class AdminController {
 * 
 * @Autowired // Autowires the AdminService bean into this controller private
 * AdminService adminService;
 * 
 * // Handles HTTP POST requests for creating an admin
 * 
 * @PostMapping("/create") public ResponseEntity<Object>
 * createAdmin(@RequestBody AdminPayload adminPayload) { return
 * adminService.createAdmin(adminPayload);
 * 
 * }
 * 
 * // Handles HTTP PUT requests for updating an admin by ID
 * 
 * @PutMapping("/{id}/update") public ResponseEntity<Object>
 * updateAdmin(@PathVariable String id, @RequestBody AdminPayload adminPayload)
 * { return adminService.updateAdmin(id, adminPayload);
 * 
 * }
 * 
 * // Handles HTTP GET requests for fetching all admins
 * 
 * @GetMapping("/admins") public ResponseEntity<List<Admin>> getAdmins() {
 * return adminService.getAdmins();
 * 
 * }
 * 
 * // Handles HTTP GET requests for fetching an admin by ID
 * 
 * @GetMapping("/{id}") public ResponseEntity<Object> getAdminById(@PathVariable
 * String id) { return adminService.getAdminById(id);
 * 
 * }
 * 
 * // Handles HTTP DELETE requests for deleting an admin by ID
 * 
 * @DeleteMapping("/{id}") public ResponseEntity<Object>
 * deleteAdmin(@PathVariable String id) { return adminService.deleteAdmin(id);
 * 
 * }
 * 
 * 
 * @PostMapping("/login-admin") public ResponseEntity<Object>
 * adminLogin(@RequestBody AdminPayload adminPayload) { return
 * adminService.adminLogin(adminPayload.getUsername(),adminPayload.getPassword()
 * );
 * 
 * }
 * 
 * 
 * }
 */