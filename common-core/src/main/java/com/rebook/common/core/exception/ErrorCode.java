package com.rebook.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  // ========================================================================
  // VALIDATION - 입력 검증
  // ========================================================================
  EMAIL_FORMAT_INVALID(400, "VAL_001", "이메일 형식이 올바르지 않습니다."),
  PASSWORD_TOO_SHORT(400, "VAL_002", "비밀번호는 최소 8자 이상이어야 합니다."),
  PASSWORD_TOO_WEAK(400, "VAL_003", "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다."),
  NAME_TOO_LONG(400, "VAL_004", "이름은 50자 이내로 입력해야 합니다."),
  TERMS_AGREEMENT_REQUIRED(400, "VAL_005", "이용약관 동의는 필수 항목입니다."),
  PRIVACY_AGREEMENT_REQUIRED(400, "VAL_006", "개인정보 처리방침 동의는 필수 항목입니다."),
  WITHDRAW_REASON_REQUIRED(400, "VAL_007", "탈퇴 사유는 필수 항목입니다."),
  PAGE_SIZE_EXCEEDED(400, "VAL_008", "페이지 크기는 최대 100까지 가능합니다."),
  PAGE_NUMBER_INVALID(400, "VAL_009", "페이지 번호는 0 이상이어야 합니다."),
  TAG_FORMAT_INVALID(400, "VAL_010", "태그 형식이 올바르지 않습니다."),
  SORT_TYPE_INVALID(400, "VAL_011", "정렬 방식이 올바르지 않습니다."),
  FILE_UPLOAD_FAILED(400, "VAL_012", "파일 업로드에 실패했습니다."),
  REQUEST_BODY_MISSING(400, "VAL_013", "요청 본문이 누락되었습니다."),
  PATH_VARIABLE_MISSING(400, "VAL_014", "필수 경로 변수가 누락되었습니다."),
  QUERY_PARAMETER_MISSING(400, "VAL_015", "필수 쿼리 파라미터가 누락되었습니다."),
  DATE_FORMAT_INVALID(400, "VAL_016", "날짜 형식이 올바르지 않습니다. (yyyy-MM-dd)"),
  DATE_TIME_FORMAT_INVALID(400, "VAL_017", "날짜시간 형식이 올바르지 않습니다. (yyyy-MM-dd'T'HH:mm:ss)"),
  VALIDATION_FAILED(400, "VAL_018", "요청 값 검증에 실패했습니다."),
  TYPE_MISMATCH(400, "VAL_019", "파라미터 타입이 올바르지 않습니다."),

  // ========================================================================
  // SYSTEM - 시스템 관련
  // ========================================================================
  INTERNAL_SERVER_ERROR(500, "SYS_001", "서버 내부 오류가 발생했습니다."),
  DATABASE_ERROR(500, "SYS_002", "데이터베이스 오류가 발생했습니다."),
  EXTERNAL_API_ERROR(502, "SYS_003", "외부 API 호출에 실패했습니다."),
  FILE_STORAGE_ERROR(500, "SYS_004", "파일 저장에 실패했습니다."),
  FILE_STORAGE_ACCESS_DENIED(503, "FILE_001", "파일 저장소 접근이 거부되었습니다."),
  FILE_STORAGE_BUCKET_NOT_FOUND(503, "FILE_002", "S3 버킷을 찾을 수 없습니다."),
  FILE_READ_ERROR(500, "FILE_003", "파일 읽기에 실패했습니다."),
  FILE_EMPTY(400, "FILE_004", "파일이 비어있습니다."),
  FILE_SIZE_EXCEEDED(413, "FILE_005", "파일 크기가 10MB를 초과했습니다."),
  FILE_INVALID_TYPE(400, "FILE_006", "지원하지 않는 파일 형식입니다."),
  RATE_LIMIT_EXCEEDED(429, "SYS_005", "요청 한도를 초과했습니다. 잠시 후 다시 시도해주세요."),
  SERVICE_UNAVAILABLE(503, "SYS_006", "서비스를 일시적으로 사용할 수 없습니다."),



  // ========================================================================
  // 미처리 오류
  // ========================================================================
  UNKNOWN_ERROR(500, "UNKNOWN", "알 수 없는 오류가 발생했습니다.");
  private final int status;
  private final String code;
  private final String message;
}
