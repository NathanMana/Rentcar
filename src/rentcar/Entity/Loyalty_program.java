package rentcar.Entity;

import java.util.Date;

/**
 * 
 * Programme de fidélité
 *
 */
public class Loyalty_program {
	private long id_loyalty_program;
	private Integer duration;
	private String description;
	private Double price;
	private Double reduction_rate;
	private Date expiration_date;
	
	/**
	 * Pour le constructeur de service
	 * @param id_loyalty
	 * @param duration
	 * @param description
	 * @param price
	 * @param reduction_rate
	 * @param expiration_date
	 */
	public Loyalty_program(final long id_loyalty, final Integer duration, final String description, final Double price,
			final Double reduction_rate, final Date expiration_date) {
		this.id_loyalty_program = id_loyalty;
		this.duration = duration;
		this.description = description;
		this.price = price;
		this.reduction_rate = reduction_rate;
		this.expiration_date = expiration_date;
	}
	
	/**
	 * Constructeur classique
	 * @param duration
	 * @param description
	 * @param price
	 * @param reduction_rate
	 * @param expiration_date
	 */
	public Loyalty_program(final Integer duration, final String description, final Double price,
			final Double reduction_rate, final Date expiration_date) {
		this.duration = duration;
		this.description = description;
		this.price = price;
		this.reduction_rate = reduction_rate;
		this.expiration_date = expiration_date;
	}
	
	public Loyalty_program() {
		// TODO Auto-generated constructor stub
		this.duration = 0;
		this.description = "";
		this.price = 0.0;
		this.reduction_rate = 0.0;
		this.expiration_date = new Date(0);
	}

	public long getIdLoyaltyProgram() {
		return this.id_loyalty_program;
	}

	@Override
	public String toString() {
		return "Loyalty_program [id_loyalty_program=" + id_loyalty_program + ", duration=" + duration + ", description="
				+ description + ", price=" + price + ", reduction_rate=" + reduction_rate + ", expiration_date="
				+ expiration_date + "]";
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getReduction_rate() {
		return reduction_rate;
	}

	public void setReduction_rate(Double reduction_rate) {
		this.reduction_rate = reduction_rate;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((expiration_date == null) ? 0 : expiration_date.hashCode());
		result = prime * result + (int) (id_loyalty_program ^ (id_loyalty_program >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((reduction_rate == null) ? 0 : reduction_rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loyalty_program other = (Loyalty_program) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (expiration_date == null) {
			if (other.expiration_date != null)
				return false;
		} else if (!expiration_date.equals(other.expiration_date))
			return false;
		if (id_loyalty_program != other.id_loyalty_program)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (reduction_rate == null) {
			if (other.reduction_rate != null)
				return false;
		} else if (!reduction_rate.equals(other.reduction_rate))
			return false;
		return true;
	}

	
	
}
