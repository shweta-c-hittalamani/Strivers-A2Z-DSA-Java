
class Solution {
    public int myAtoi(String input) {
        // Your code goes here
        int i = 0;
        int sign = 1;
        int sum = 0;

        while (i < input.length() && input.charAt(i) == ' ') {
            i++;
        }

        if (i < input.length() && input.charAt(i) == '+') {
            i++;
        } else if (i < input.length() && input.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        while (i < input.length()) {
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                int digit = input.charAt(i) - '0';

                if (sum > Integer.MAX_VALUE / 10 ||
                    (sum == Integer.MAX_VALUE / 10 && digit > 7)) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                sum = sum * 10 + digit;
                i++;
            } else {
                break;
            }
        }

        return sign * sum;
    }
}
