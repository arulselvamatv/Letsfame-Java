package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SubcriptionNoteCreateRequest {
	
	@JsonProperty("notes_key_1")
	private String notesKey1;

	@JsonProperty("notes_key_2")
	private String notesKey2;

}
