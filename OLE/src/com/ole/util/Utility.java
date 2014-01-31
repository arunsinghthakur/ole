package com.ole.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Utility {
	public static boolean  isEmpty(String input) {
		if(input == null || input.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void closeSession(Session session) {
		if(session != null) {
			session.close();
		}
	}
	
	public static void rollbackTransaction(Transaction tx) {
		if(tx != null) {
			tx.rollback();
		}
	}
	
	public static double calculatePercentage(long totalQuestion, long correctAnswer) {
		double percentage = (correctAnswer * 100)/totalQuestion;
		return percentage;
	}

}
