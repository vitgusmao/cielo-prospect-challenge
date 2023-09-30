package com.adacielochallenge.prospect.dto;

import com.adacielochallenge.prospect.model.ProspectStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProspectUpdateDTO {
    private ProspectStatus status;
}