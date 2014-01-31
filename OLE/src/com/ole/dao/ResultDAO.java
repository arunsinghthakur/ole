package com.ole.dao;

import com.ole.exception.ResultException;
import com.ole.model.Result;

public interface ResultDAO {
	boolean saveResult(Result result) throws ResultException;
}
