package com.ole.model;

import java.util.ArrayList;
import java.util.List;

public class HistoryResponse extends BaseResponse{
	private List<History> history = new ArrayList<History>();

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}
	
}
