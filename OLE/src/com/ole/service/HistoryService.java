package com.ole.service;

import com.ole.exception.HistoryException;
import com.ole.model.HistoryResponse;

public interface HistoryService {
	HistoryResponse getHistory(long userId) throws HistoryException;
}
