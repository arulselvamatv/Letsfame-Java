package com.letsfame.Req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LetsFamePlanNoteReq {

	@JsonProperty("notes_key_1")
	private String notesKey1;

	@JsonProperty("notes_key_2")
	private String notesKey2;
}
