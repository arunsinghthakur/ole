package com.ole.dao;

import java.util.List;

import com.ole.exception.HistoryException;
import com.ole.model.Result;

public interface HistoryDAO {
	List<Result> getHistory(long userId) throws HistoryException;
}
