package com.ole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ole.dao.HistoryDAO;
import com.ole.exception.HistoryException;
import com.ole.model.History;
import com.ole.model.HistoryResponse;
import com.ole.model.Result;
import com.ole.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryDAO historyDAO;
	
	@Override
	public HistoryResponse getHistory(long userId) throws HistoryException {
		List<Result> result = historyDAO.getHistory(userId);
		
		HistoryResponse response = new HistoryResponse();
		for(Result r : result) {
			History h  = new History();
			h.setExamName(r.getExam().getExamName());
			h.setCorrectAnswer(r.getCorrectAnswer());
			h.setExamResult(r.getExamResult());
			h.setPercentage(r.getPercentage());
			h.setQuestionAttempted(r.getQuestionAttempted());
			h.setTotalQuestion(r.getExam().getTotalQuestion());
			response.getHistory().add(h);
		}
		return response;
	}

}
