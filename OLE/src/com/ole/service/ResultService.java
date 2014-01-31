package com.ole.service;

import com.ole.exception.AnswerException;
import com.ole.exception.ExamException;
import com.ole.exception.ResultException;
import com.ole.model.ResultResponse;

public interface ResultService {
	ResultResponse calculateResult(long userId, long examId, boolean isInternalRequest) throws ResultException, AnswerException, ExamException;
}
