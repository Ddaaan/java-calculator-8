package calculator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

class AddCalculator {
    private static final String DEFAULT_SEPARATOR = ",:";
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("^[+-]?\\d+(?:\\.\\d+)?$");

    public BigDecimal add(String input) {
        if (input == null || input.isBlank()) { //입력이 없으면 0 return
            return BigDecimal.ZERO;
        }

        BigDecimal sum = BigDecimal.ZERO;
        String[] parts = input.split(DEFAULT_SEPARATOR); //구분자로 쪼개기

        for (int i=0; i<parts.length; i++) {
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
}
