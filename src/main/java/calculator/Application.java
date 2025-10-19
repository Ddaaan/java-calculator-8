package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Application {
  public static void main(String[] args) {
    System.out.println("덧셈할 문자열을 입력해주세요.");
    String input = Console.readLine();

    AddCalculator calculator = new AddCalculator();
    BigDecimal result = calculator.add(input);

    result = result.setScale(2, RoundingMode.HALF_UP);

    System.out.println("결과 : " + result);
    Console.close();
  }
}
