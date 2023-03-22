package com.letsfame.dto;

import lombok.Data;

@Data
public class PaginationDto {
	private Long totalElements;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer offset;
	private Boolean sorted;
	private Object data;

}
