package calculator;

class AddCalculator {
    private static final String DEFAULT_SEPARATOR = ",|:";

    public int add(String input) {
        int sum = 0;
        String[] parts = input.split(DEFAULT_SEPARATOR);

        for (String p : parts) {
            sum += Integer.parseInt(p);
        }
        return sum;
    }
}
