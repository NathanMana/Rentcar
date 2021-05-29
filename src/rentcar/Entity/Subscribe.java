package rentcar.Entity;

import java.util.Date;

public class Subscribe {

	private long id_client;
	private long id_loyalty_program;
	private Date joining_date;
	
	/**
	 * 
	 * @param id_client
	 * @param id_loyalty_program
	 * @param joining_date
	 */
	public Subscribe(long id_client, long id_loyalty_program, Date joining_date) {
		this.id_client = id_client;
		this.id_loyalty_program = id_loyalty_program;
		this.joining_date = joining_date;
	}

	public long getId_client() {
		return id_client;
	}

	public long getId_loyalty_program() {
		return id_loyalty_program;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}
	
	
	
}
