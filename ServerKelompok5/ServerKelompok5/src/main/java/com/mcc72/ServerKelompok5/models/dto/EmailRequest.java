package com.mcc72.ServerKelompok5.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

  private String to;
  private String subject;
  private String body;
}
