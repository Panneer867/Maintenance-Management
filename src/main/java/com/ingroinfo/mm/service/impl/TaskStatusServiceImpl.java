package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.TaskStatusRepository;
import com.ingroinfo.mm.dto.TaskStatusDto;
import com.ingroinfo.mm.entity.TaskStatus;
import com.ingroinfo.mm.service.TaskStatusService;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public TaskStatusDto saveTaskStatus(TaskStatusDto taskStatusDto) {
		TaskStatus convTaskStatus = this.modelMapper.map(taskStatusDto, TaskStatus.class);
		TaskStatus savedTaskStatus = this.taskStatusRepository.save(convTaskStatus);
		return this.modelMapper.map(savedTaskStatus, TaskStatusDto.class);
	}

	@Override
	public List<TaskStatusDto> findAllTaskStatus() {
		List<TaskStatus> taskStatuss = this.taskStatusRepository.findAll();
		List<TaskStatusDto> taskStatusDtos = taskStatuss.stream().map((taskSts) -> 
		this.modelMapper.map(taskSts, TaskStatusDto.class)).collect(Collectors.toList());
		return taskStatusDtos;
	}

	@Override
	public void deleteTaskStatus(Long taskstsId) {
		TaskStatus taskStatus  = this.taskStatusRepository.findById(taskstsId).get();
		this.taskStatusRepository.delete(taskStatus);
	}


}
