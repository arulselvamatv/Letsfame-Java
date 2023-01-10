package com.letsfame.bean;

import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "subscriptionrequest")
public class SubscriptionRequest {

	@JsonProperty("total_count")
	private Double totalCount;
	@JsonProperty("plan_id")
	private String planId;
	@JsonProperty("quantity")
	private String quantity;
	@JsonProperty("customer_notify")
	private String customerNotify;
	@JsonProperty("start_at")
	private Date startAt;
	@JsonProperty("expire_by")
	private Date expireBy;
	@JsonProperty("addons")
	private List<AddOn> addons;

	public Double getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Double totalCount) {
		this.totalCount = totalCount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCustomerNotify() {
		return customerNotify;
	}

	public void setCustomerNotify(String customerNotify) {
		this.customerNotify = customerNotify;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getExpireBy() {
		return expireBy;
	}

	public void setExpireBy(Date expireBy) {
		this.expireBy = expireBy;
	}

	public List<AddOn> getAddons() {
		return addons;
	}

	public void setAddons(List<AddOn> addons) {
		this.addons = addons;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "SubscriptionRequest [totalCount=" + totalCount + ", planId=" + planId + ", quantity=" + quantity
				+ ", customerNotify=" + customerNotify + ", startAt=" + startAt + ", expireBy=" + expireBy + ", addons="
				+ addons + "]";
	}

	public JSONObject toJsonObject() {
		JSONObject subscriptionRequest = new JSONObject();

		subscriptionRequest.put("total_count", this.totalCount);

		subscriptionRequest.put("quantity", this.quantity);

		subscriptionRequest.put("customer_notify", Integer.parseInt(this.customerNotify));

		subscriptionRequest.put("start_at", this.startAt);

		subscriptionRequest.put("expire_by", this.expireBy);

		subscriptionRequest.put("plan_id", this.planId);

		if (addons != null) {

			JSONArray jsonarray = new JSONArray();

			for (AddOn addOn : addons) {
//				jsonarray.add(item.toJsonObject());
				JSONObject itemObject = new JSONObject();
				itemObject.put("item", addOn.toJsonObject());

				jsonarray.put(itemObject);

			}
			subscriptionRequest.put("addons", jsonarray);
		}

		return subscriptionRequest;
	}
}
