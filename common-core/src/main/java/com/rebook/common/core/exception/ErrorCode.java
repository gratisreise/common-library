package com.rebook.common.core.exception;

public interface ErrorCode {
  int getStatus();
  String getCode();
  String getMessage();
}
