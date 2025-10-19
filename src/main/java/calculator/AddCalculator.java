package calculator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

class AddCalculator {
  private static final String DEFAULT_SEPARATOR = ",:";
  private static final Pattern DECIMAL_PATTERN = Pattern.compile("^[+-]?\\d+(?:\\.\\d+)?$");

  public BigDecimal add(String input) {
    if (input == null || input.isBlank()) { // 입력이 없으면 0 return
      return BigDecimal.ZERO;
    }

    String number = input; // 사용자지정구분자가 있을 때 계산할 숫자 문자열
    String customSeparator = null; // 사용자지정구분자

    if (input.startsWith("//")) {
      int newline = input.indexOf('\n');
      if (newline < 0) {
        throw new IllegalArgumentException("커스텀 구분자 지정 후 줄바꿈이 필요합니다.");
      }
      customSeparator = input.substring(2, newline); // 커스텀 구분자 집합
      number = input.substring(newline + 1); // 계산할 숫자들의 문자열
    }

    String splitRegex =
        buildCharClassRegex(DEFAULT_SEPARATOR + (customSeparator != null ? customSeparator : ""));
    String[] parts = number.split(splitRegex); // 구분자로 쪼개기

    BigDecimal sum = BigDecimal.ZERO;

    for (int i = 0; i < parts.length; i++) {
      String token = parts[i].trim();

      if (token.isEmpty()) {
        throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
      }
      if (!DECIMAL_PATTERN.matcher(token).matches()) {
        throw new IllegalArgumentException("숫자만 입력이 가능합니다 : '" + token + "'");
      }

      BigDecimal value = new BigDecimal(token);
      if (value.signum() < 0) {
        throw new IllegalArgumentException("음수는 허용되지 않습니다 : '" + token + "'");
      }

      sum = sum.add(value);
    }
    return sum;
  }

  private static boolean needsEscape(char delimiterChar, boolean isFirst) {
    return delimiterChar == '\\'
        || delimiterChar == '-'
        || delimiterChar == ']'
        || (isFirst && delimiterChar == '^');
  }

  private String buildCharClassRegex(String delimiter) {
    StringBuilder charClassBuilder = new StringBuilder("[");
    for (int i = 0; i < delimiter.length(); i++) {
      char delimiterChar = delimiter.charAt(i);
      if (needsEscape(delimiterChar, i == 0)) {
        charClassBuilder.append('\\');
      }
      charClassBuilder.append(delimiterChar);
    }
    charClassBuilder.append(']');
    return charClassBuilder.toString();
  }
}
