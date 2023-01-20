package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.TeamCodeDto;

public interface TeamCodeService {

	//save
	TeamCodeDto saveTeamCode(TeamCodeDto teamCodeDto);
	
	//find All
	List<TeamCodeDto> getAllTeamCode();
	
	//Delete
	void deleteTeamcode(Long teamCodeId);
}
