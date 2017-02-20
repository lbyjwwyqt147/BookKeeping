package pers.liujunyi.bookkeeping.thread.struct;

import pers.liujunyi.bookkeeping.thread.ICommon;



public abstract class BaseMailSendHand implements ICommon, Runnable {

	@Override
	public void run() {
		try {
			this.action();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
