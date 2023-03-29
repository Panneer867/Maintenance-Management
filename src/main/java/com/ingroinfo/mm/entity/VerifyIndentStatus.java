package com.ingroinfo.mm.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "VERIFY_INDENT_STATUS")
@Data
public class VerifyIndentStatus {

	@Id
    @Column(name = "INDENT_NO")
    private String indentNo;

    @Column(name = "COMPL_NO")
    private String complNo;

    @Column(name = "DEPARTMENT_NAME")
    private String department;
    
    @Column(name = "ITEM_APPROVED")
    private String itemApproved;

    @Column(name = "LABOR_APPROVED")
    private String laborApproved;

    @Column(name = "VEHICLE_APPROVED")
    private String vehicleApproved;
}
