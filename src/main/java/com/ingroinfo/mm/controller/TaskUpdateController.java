package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.TaskUpdateService;

@Controller
@RequestMapping("/task")
public class TaskUpdateController {

	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private AdminService adminService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());

	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "/pages/task_update/dashboard";
	}

	@GetMapping("/je")
	@PreAuthorize("hasAuthority('TASK_JE')")
	public String taskUpdateJE(Model model, Principal principal) {

		User user = this.adminService.getUserByUsername(principal.getName());

		model.addAttribute("title", "Task | JE | Maintenance Management");
		model.addAttribute("complDtls", new ComplaintDto());
		Long userId = user.getUbarmsUserId();
		try {
			List<ComplaintDto> jeComplaintDtos = this.taskUpdateService.getListOfJeComplaint(userId);
			if (jeComplaintDtos != null) {
				model.addAttribute("jeComplainList", jeComplaintDtos);
			} else {
				System.out.println("No Complain Found !!");
			}
		} catch (Exception e) {
			System.out.println("No Complain Found !!" + e.getMessage());
		}
		return "/pages/task_update/je_task";
	}

	@GetMapping("/aee")
	@PreAuthorize("hasAuthority('TASK_AEE')")
	public String taskUpdateAEE(Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		model.addAttribute("complDtls", new ComplaintDto());
		try {
			List<ComplaintDto> aeeComplaintDtos = this.taskUpdateService.getListOfAeeComplaint(userId);
			if (aeeComplaintDtos != null) {
				model.addAttribute("aeeComplainList", aeeComplaintDtos);
			} else {
				System.out.println("No Complain Found !!");
			}
		} catch (Exception e) {
			System.out.println("No Complain Found !!" + e.getMessage());
		}
		model.addAttribute("title", "Task | AEE | Maintenance Management");
		return "/pages/task_update/aee_task";
	}

	@GetMapping("/ee")
	@PreAuthorize("hasAuthority('TASK_EE')")
	public String taskUpdateEE(Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		model.addAttribute("complDtls", new ComplaintDto());
		try {
			List<ComplaintDto> eeComplaintDtos = this.taskUpdateService.getListOfEeComplaint(userId);
			if (eeComplaintDtos != null) {
				model.addAttribute("eeComplainList", eeComplaintDtos);
			} else {
				System.out.println("No Complain Found !!");
			}
		} catch (Exception e) {
			System.out.println("No Complain Found !!" + e.getMessage());
		}
		model.addAttribute("title", "Task | EE | Maintenance Management");
		return "/pages/task_update/ee_task";
	}

	@GetMapping("/commissioner")
	@PreAuthorize("hasAuthority('TASK_COMMISSIONER')")
	public String taskUpdateCommissioner(Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		model.addAttribute("complDtls", new ComplaintDto());
		try {
			List<ComplaintDto> comComplaintDtos = this.taskUpdateService
					.getListOfCommissionerComplaint(userId);
			if (comComplaintDtos != null) {
				model.addAttribute("comComplainList", comComplaintDtos);
			} else {
				System.out.println("No Complain Found !!");
			}
		} catch (Exception e) {
			System.out.println("No Complain Found !!" + e.getMessage());
		}
		model.addAttribute("title", "Task | Commissioner | Maintenance Management");
		return "/pages/task_update/commissioner";
	}

	// Handler For Fetching JE Complaints Details
	@GetMapping("/je/ComplDtls/{complNo}")
	public String getComplaintDataByComplNo(@PathVariable String complNo, Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		try {
			List<ComplaintDto> complaintDtos = this.taskUpdateService.getListOfJeComplaint(userId);
			model.addAttribute("jeComplainList", complaintDtos);
			ComplaintDto complaintDto = this.taskUpdateService.getComplaintDtlsByComplNo(complNo);
			if (complaintDto.getEsclatedFrom().equals("JE")) {
				complaintDto.setEsclatedTo("AEE");
			}
			model.addAttribute("complDtls", complaintDto);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return "redirect:/task/je";
		}
		model.addAttribute("title", "Task | JE | Maintenance Management");
		return "/pages/task_update/je_task";
	}

	// Handler For Fetching AEE Complaints Details
	@GetMapping("/aee/ComplDtls/{complNo}")
	public String getAeeComplaintDataByComplNo(@PathVariable String complNo, Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		try {
			List<ComplaintDto> aeeComplaintDtos = this.taskUpdateService.getListOfAeeComplaint(userId);
			model.addAttribute("aeeComplainList", aeeComplaintDtos);
			ComplaintDto complaintDto = this.taskUpdateService.getComplaintDtlsByComplNo(complNo);
			if (complaintDto.getEsclatedFrom().equals("AEE")) {
				complaintDto.setEsclatedTo("EE");
			}
			model.addAttribute("complDtls", complaintDto);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return "redirect:/task/aee";
		}
		model.addAttribute("title", "Task | AEE | Maintenance Management");
		return "/pages/task_update/aee_task";
	}

	// Handler For Fetching EE Complaints Details
	@GetMapping("/ee/ComplDtls/{complNo}")
	public String getEeComplaintDataByComplNo(@PathVariable String complNo, Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		try {
			List<ComplaintDto> eeComplaintDtos = this.taskUpdateService.getListOfEeComplaint(userId);
			model.addAttribute("eeComplainList", eeComplaintDtos);
			ComplaintDto complaintDto = this.taskUpdateService.getComplaintDtlsByComplNo(complNo);
			if (complaintDto.getEsclatedFrom().equals("EE")) {
				complaintDto.setEsclatedTo("COM");
			}
			model.addAttribute("complDtls", complaintDto);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return "redirect:/task/ee";
		}
		model.addAttribute("title", "Task | EE | Maintenance Management");
		return "/pages/task_update/ee_task";
	}

	// Handler For Fetching Commissioner Complaints Details
	@GetMapping("/commissioner/ComplDtls/{complNo}")
	public String getComissonComplaintDataByComplNo(@PathVariable String complNo, Model model, Principal principal) {
		User user = this.adminService.getUserByUsername(principal.getName());
		Long userId = user.getUbarmsUserId();
		try {
			List<ComplaintDto> comissComplaintDtos = this.taskUpdateService
					.getListOfCommissionerComplaint(userId);
			model.addAttribute("comComplainList", comissComplaintDtos);
			ComplaintDto complaintDto = this.taskUpdateService.getComplaintDtlsByComplNo(complNo);
			model.addAttribute("complDtls", complaintDto);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return "redirect:/task/commissioner";
		}
		model.addAttribute("title", "Task | Commissioner | Maintenance Management");
		return "/pages/task_update/commissioner";
	}

	// submit JE Investigations Data
	@PostMapping("/submitJeeInvest")
	public String submitJeInvestigationReport(@ModelAttribute ComplaintDto complaintDto, HttpSession session) {

		if (complaintDto.getJobDoneNotDone().equalsIgnoreCase("Y")) {
			complaintDto.setComplStatus("Completed");
			this.taskUpdateService.saveComplaint(complaintDto);
			try {
				this.taskUpdateService.submitInvestigations(complaintDto);
			} catch (Exception e) {
				System.out.println("Something Wrong " + e.getMessage());
			}
			session.setAttribute("message", new Message("JE Investigation Sucessfully Completed !!", "success"));
		} else if (complaintDto.getJobDoneNotDone().equalsIgnoreCase("N")) {
			try {
				complaintDto.setComplStatus("NeedMaterial");
				this.taskUpdateService.saveComplaint(complaintDto);
				this.taskUpdateService.submitInvestigations(complaintDto);
				if (complaintDto.getDepartment().equalsIgnoreCase("Pipe Dept")) {
					return "redirect:/pipe/maintenance-indent";
				} else if (complaintDto.getDepartment().equalsIgnoreCase("Pump Dept")) {
					return "redirect:/pump/maintenance/indent";
				}
			} catch (Exception e) {
				System.out.println("Something Wrong !!" + e.getMessage());
			}
		} else if (complaintDto.getJobDoneNotDone().equalsIgnoreCase("E")) {
			complaintDto.setComplStatus("Escalate");
			complaintDto.setEsclationType("Manual");
			complaintDto.setEsclationLavel("1");
			this.taskUpdateService.saveComplaint(complaintDto);

			try {
				this.taskUpdateService.submitEsclations(complaintDto);
			} catch (Exception e) {
				System.out.println("Something Wrong " + e.getMessage());
			}
			session.setAttribute("message", new Message("JE Investigation Esclated To AEE !!", "danger"));
		}

		return "redirect:/task/je";
	}

	// submit AEE Investigations Data
	@PostMapping("/submitAeeInvest")
	public String submitAeeInvestigationReport(@ModelAttribute ComplaintDto complaintDto, HttpSession session) {
		if (complaintDto.getEsclatedDate() != "") {
			complaintDto.setComplStatus("Escalate");
			complaintDto.setEsclationType("Manual");
			complaintDto.setEsclationLavel("2");
			this.taskUpdateService.saveComplaint(complaintDto);

			try {
				this.taskUpdateService.submitEsclations(complaintDto);
			} catch (Exception e) {
				System.out.println("Something Wrong " + e.getMessage());
			}
			session.setAttribute("message", new Message("AEE Investigation Esclated To EE !!", "danger"));
		} else {
			complaintDto.setComplStatus("Completed");
			complaintDto.setEsclationType("Manual");
			complaintDto.setEsclationLavel("2");
			this.taskUpdateService.saveComplaint(complaintDto);
			try {
				this.taskUpdateService.submitInvestigations(complaintDto);
			} catch (Exception e) {
				System.out.println("Something Wrong " + e.getMessage());
			}
			session.setAttribute("message", new Message("AEE Investigation Sucessfully Completed !!", "success"));
		}
		return "redirect:/task/aee";
	}

	// submit EE Investigations Data
	@PostMapping("/submitEeInvest")
	public String submitEeInvestigationReport(@ModelAttribute ComplaintDto complaintDto, HttpSession session) {

		if (complaintDto.getEsclatedDate() != "") {
			complaintDto.setComplStatus("Escalate");
			complaintDto.setEsclationType("Manual");
			complaintDto.setEsclationLavel("3");
			this.taskUpdateService.saveComplaint(complaintDto);

			try {
				this.taskUpdateService.submitEsclations(complaintDto);
			} catch (Exception e) {
				System.out.println("Something Wrong " + e.getMessage());
			}
			session.setAttribute("message", new Message("EE Investigation Esclated To Commissioner !!", "danger"));
		} else {
			complaintDto.setComplStatus("Completed");
			complaintDto.setEsclationType("Manual");
			complaintDto.setEsclationLavel("3");
			this.taskUpdateService.saveComplaint(complaintDto);
			try {
				this.taskUpdateService.submitInvestigations(complaintDto);
			} catch (Exception e) {
				System.out.println("Something Wrong " + e.getMessage());
			}
			session.setAttribute("message", new Message("EE Investigation Sucessfully Completed !!", "success"));
		}
		return "redirect:/task/ee";
	}

	// submit Commissioner Investigations Data
	@PostMapping("/submitComissInvest")
	public String submitComissInvestigationReport(@ModelAttribute ComplaintDto complaintDto, HttpSession session) {

		complaintDto.setComplStatus("Completed");
		complaintDto.setEsclationType("Manual");
		complaintDto.setEsclationLavel("4");
		this.taskUpdateService.saveComplaint(complaintDto);
		try {
			this.taskUpdateService.submitInvestigations(complaintDto);
		} catch (Exception e) {
			System.out.println("Something Wrong " + e.getMessage());
		}
		session.setAttribute("message", new Message("Commissner Investigation Sucessfully Completed !!", "success"));

		return "redirect:/task/commissioner";
	}

	// Getting Work Indent According Department In JE investigations
	@RequestMapping("/open-indent/{complNo}")
	public String openWorkOrderIndentByDepartment(@PathVariable("complNo") String complNo) {
		try {
			ComplaintDto complaintDto = taskUpdateService.getComplaintDtlsByComplNo(complNo);
			if (complaintDto.getDepartment().equalsIgnoreCase("Pipe Dept")) {
				return "redirect:/pipe/maintenance-indent";
			} else if (complaintDto.getDepartment().equalsIgnoreCase("Pump Dept")) {
				return "redirect:/pump/maintenance/indent";
			}
		} catch (Exception e) {
			System.out.println("Something Wrong !!" + e.getMessage());
		}

		return "redirect:/task/je";
	}

	@GetMapping("/work-complete")
	@PreAuthorize("hasAuthority('TASK_WORKCOMPLETE')")
	public String workComplete() {
		return "/pages/task_update/work_complete";
	}

	@GetMapping("/job-card")
	@PreAuthorize("hasAuthority('TASK_JOBCARD')")
	public String JobCard() {
		return "/pages/task_update/job_card";
	}

	@GetMapping("/complaint-history")
	@PreAuthorize("hasAuthority('TASK_COMPLAINTHISTORY')")
	public String ComplaintHistory() {
		return "/pages/task_update/complaint_history";
	}

}
