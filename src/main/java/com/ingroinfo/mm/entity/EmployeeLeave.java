package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Data
@Table(name = "MM_EMPLOYEE_LEAVE")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empLeaveId;
	@Column(name="EMPLOYEE_CODE",length = 20)
	private String employeeCode;
	@Column(name = "EMPLOYEE_NAME",length = 150)
	private String empName;
	@Column(name = "DEPARTMENT",length = 150)
	private String department;
	@Column(name = "ANNUAL_SL", length=20)
	private String sickLeave;
	@Column(name = "ANNUAL_CL", length=20)
	private String causalLeave;
	@Column(name="ANNUAL_LWP",length=20)
	private String annualLwp;
	@Column(name = "SAN_SL",length=20)
	private Integer sacnSickLeave;
	@Column(name = "SAN_CL",length=20)
	private Integer sacnCausalLeave;
	@Column(name = "SAN_LWP",length=20)
	private Integer sacnLwp;
	@Column(name = "DEPT_HEAD",length=20)
	private String deptHead;
	@Column(name = "REMARK",length=150)
	private String remark;
	@Column(name = "LEAVE_DATE",length=20)
	private String leaveDate;
	@Column(name = "NO_OF_LEAVE",length=20)
	private Integer noOfLeave;
	@Column(name = "LEAVE_TYPE",length=20)
	private String leaveType;
	@Column(name = "HR_APPROVAL",length =20)
	private String hrApproval;
	@Column(name = "HR_DATE",length=20) 
	private String hrDate;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
    private EmployeeMaster employeeMaster;
	
	@Column(name = "CREATE_DATE")	
	private Date createDate;
	@Column(name ="UPADATED_DATE")
	@UpdateTimestamp
	private Date lastUpdateDate;	
	
	
  
	
}
