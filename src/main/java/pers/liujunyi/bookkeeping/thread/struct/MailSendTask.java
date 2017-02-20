package pers.liujunyi.bookkeeping.thread.struct;



public class MailSendTask extends BaseMailSendHand {
	private String name;
	
	
	public MailSendTask(String name) {
		this.name = name;
	}

	@Override
	public void action() throws Exception {
		System.out.println("发送邮件:"+this.name);
	}
}
