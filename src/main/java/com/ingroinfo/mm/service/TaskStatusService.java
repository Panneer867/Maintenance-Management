package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.TaskStatusDto;

public interface TaskStatusService {

	//create
	TaskStatusDto saveTaskStatus(TaskStatusDto taskStatusDto);
	
	//get All Data
	List<TaskStatusDto> findAllTaskStatus();
	
	//Delete
	void deleteTaskStatus(Long taskstsId);
}
