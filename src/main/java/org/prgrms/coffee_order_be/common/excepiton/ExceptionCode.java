package org.prgrms.coffee_order_be.common.excepiton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  // ADMIN ERROR
  DUPLICATED_PRODUCT(400, "이미 존재하는 제품입니다.");

  private final int code;
  private final String message;
}
