package com.cogni.apartment.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
@Component
public class TransactionAspect extends TransactionSynchronizationAdapter {

	static {
		System.out.println("STATIC BLOCK");
	}

	@Before("@annotation(org.springframework.transaction.annotation.Transactional)")
	public void registerTransactionSyncrhonization(JoinPoint joinPoint) {
		TransactionSynchronizationManager.registerSynchronization(this);

	}

	@Override
	public void afterCompletion(int status) {
		System.out.println("CCCCC");
	}

	@Override
	public void beforeCommit(boolean readOnly) {
		System.out.println("BEFORE COMMIT____");
	}
	
	
}
