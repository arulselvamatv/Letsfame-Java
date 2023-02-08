package com.letsfame.Req;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import com.letsfame.bean.PlanFeatures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Entity
//@DynamicInsert
//@DynamicUpdate
@Builder

public class PlanFeaturesReq {
	@Id
	private String id;

	private ArrayList<PlanFeatures> planFeatures;

}
