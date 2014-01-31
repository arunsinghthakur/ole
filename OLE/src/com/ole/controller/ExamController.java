package com.ole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ole.constant.Constant;
import com.ole.constant.ResponseCode;
import com.ole.exception.AuthorizationException;
import com.ole.exception.ExamException;
import com.ole.exception.UserException;
import com.ole.model.BaseResponse;
import com.ole.model.Exam;
import com.ole.model.ExamResponse;
import com.ole.model.User;
import com.ole.service.ExamService;

@Controller
@RequestMapping("/{role}/exam")
public class ExamController extends BaseController {

	@Autowired
	private ExamService examService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String examView(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "admin/add-exam";
		} catch (UserException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		} catch (AuthorizationException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		}

		return viewType;
	}
	
	@RequestMapping(value = "/eview", method = RequestMethod.GET)
	public String examEditView(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "admin/edit-exam";
		} catch (UserException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		} catch (AuthorizationException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		}

		return viewType;
	}
	
	
	@RequestMapping(value = "/list/view", method = RequestMethod.GET)
	public String examListView(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "user/take-exam";
		} catch (UserException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		} catch (AuthorizationException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		}

		return viewType;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse addExam(@RequestBody Exam exam, @PathVariable("role") String role) {

		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			examService.addExam(exam);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.EXAM_ADDED);
		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_ADD_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ExamResponse getAllExam(@PathVariable("role") String role) {

		ExamResponse response = new ExamResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			boolean filter = true;
			if (role.equalsIgnoreCase(Constant.ROLE_ADMIN_KEY)) {
				filter = false;
			}
			List<Exam> exams = examService.getAllExam(filter);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.EXAM_LIST_FETCH);
			response.setExams(exams);

		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_FETCH_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ExamResponse getExam(@PathVariable("role") String role, @RequestParam("examId") long examId) {

		ExamResponse response = new ExamResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			Exam exam = examService.getExam(examId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.EXAM_LIST_FETCH);
			response.setExam(exam);

		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_FETCH_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/{examId}", method = RequestMethod.PUT)
	public BaseResponse editExam(@PathVariable("role") String role, @PathVariable("examId") Long examId, @RequestBody Exam exam) {
		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			examService.editExam(exam);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.EXAM_UPDATED);
		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_UPDATE_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	
	@ResponseBody
	@RequestMapping(value="/{examId}", method = RequestMethod.DELETE)
	public BaseResponse deleteExam(@PathVariable("examId") long examId, @PathVariable("role") String role) {

		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			examService.deleteExam(examId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.EXAM_DELETED);
		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_DELETE_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
}