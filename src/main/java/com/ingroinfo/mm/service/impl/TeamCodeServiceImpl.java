package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.TeamCodeRepository;
import com.ingroinfo.mm.dto.TeamCodeDto;
import com.ingroinfo.mm.entity.TeamCode;
import com.ingroinfo.mm.service.TeamCodeService;

@Service
public class TeamCodeServiceImpl implements TeamCodeService {

	@Autowired
	private TeamCodeRepository teamCodeRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public TeamCodeDto saveTeamCode(TeamCodeDto teamCodeDto) {
		TeamCode convTeamCode = this.modelMapper.map(teamCodeDto, TeamCode.class);
		TeamCode savedTeamCode = this.teamCodeRepo.save(convTeamCode);
		return this.modelMapper.map(savedTeamCode, TeamCodeDto.class);
	}

	@Override
	public List<TeamCodeDto> getAllTeamCode() {
		List<TeamCode> teamCodes = this.teamCodeRepo.findAll();
		List<TeamCodeDto> teamCodeDtos = teamCodes.stream().map((teamCode) -> 
		this.modelMapper.map(teamCode, TeamCodeDto.class)).collect(Collectors.toList());
		return teamCodeDtos;
	}

	@Override
	public void deleteTeamcode(Long teamCodeId) {
		 TeamCode teamCodem = this.teamCodeRepo.findById(teamCodeId).get();
		 this.teamCodeRepo.delete(teamCodem);
	}

}
