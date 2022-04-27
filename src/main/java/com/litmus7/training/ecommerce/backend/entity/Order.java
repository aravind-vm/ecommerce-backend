package com.litmus7.training.ecommerce.backend.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private Integer status;
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private Set<OrderItem> items;

	private Long deliveryAddressId;
	private BigDecimal totalprice;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser user;

	public void calculateTotalPrice() {
		if (!items.isEmpty()) {
			totalprice = BigDecimal.valueOf(0);
			for (OrderItem item : items) {

				BigDecimal price = (item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
				if (totalprice == BigDecimal.valueOf(0)) {
					totalprice = price;

				} else {
					totalprice = totalprice.add(price);
				}

			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public Long getDelivery() {
		return deliveryAddressId;
	}

	public void setDelivery(Long deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public Long getUser() {
		return user.getId();
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public void add(OrderItem item) {

		if (item != null) {
			if (items == null) {
				items = new HashSet<>();
			}

			items.add(item);
			item.setOrder(this);
		}
	}

	public Order() {
		super();

	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", dateCreated=" + dateCreated + ", lastUpdated="
				+ lastUpdated + ", items=" + items + ", deliveryAddressId=" + deliveryAddressId + ", totalprice="
				+ totalprice + ", user=" + user.getId() + "]";
	}

}
