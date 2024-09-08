package org.prgrms.coffee_order_be.common.excepiton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
  private final int code;
  private final String message;
}
