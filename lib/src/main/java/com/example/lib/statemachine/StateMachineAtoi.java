package com.example.lib.statemachine;


/*
 *  正负数溢出之后，溢出实际上是循环到了对面范围内。
 *  int plus = Integer.MAX_VALUE;
    int minus = Integer.MIN_VALUE;
    System.out.println("plus " + (plus));
    System.out.println("plus overflow 1 " + (plus + 1));
    System.out.println("plus overflow 2 " + (plus + 2));
    System.out.println("plus overflow 3 " + (plus + 3));
    System.out.println("minus " + (minus ));
    System.out.println("minus overflow 1 " + (minus - 1));
    System.out.println("minus overflow 2 " + (minus - 2));
    System.out.println("minus overflow 3 " + (minus - 3));
    plus 2147483647
    plus overflow 1 -2147483648
    plus overflow 2 -2147483647
    plus overflow 3 -2147483646
    minus -2147483648
    minus overflow 1 2147483647
    minus overflow 2 2147483646
    minus overflow 3 2147483645
 */

public class StateMachineAtoi {
    public static void main(String[] args) {


//        String source = " 212 sf -0-123 sfs";
//        int ret = convert(source);
//        System.out.println("ret=" + ret);
    }

    private static int convert1(String source) {
        if (source == null || source.trim().length() == 0) {
            return 0;
        }
        source = source.trim();

        boolean minus = false;
        int ret = 0;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (i == 0) {
                if (c == '-') {
                    minus = true;
                } else if (c >= '0' && c <= '9') {
                    int single = c - '0';
                    if (!overflow(ret, single)) {
                        ret = ret * 10 + single;
                    } else {
                        return 0;
                    }
                } else if (c != '+') {
                    return ret;
                }
            } else {
                if (c >= '0' && c <= '9') {
                    ret = ret * 10 + (c - '0');
                } else {
                    return ret;
                }
            }
        }

        return minus ? -ret : ret;
    }

    enum State {
        START, SIGNED, IN_NUMBER, END
    }


    State[][] states = new State[][]{
            //           " "             "-,+"         "number"     "other"
            /*start*/  {State.START, State.SIGNED, State.IN_NUMBER, State.END},
            /*sign */  {State.END, State.END, State.IN_NUMBER, State.END},
            /*num  */  {State.END, State.END, State.IN_NUMBER, State.END},
            /*other*/  {State.END, State.END, State.END, State.END},
    };

    private static int convert2(String source) {
        State state = State.START;
        int number = 0;
        for (int i = 0; i < source.length(); i++) {
            int col = getCol(source.charAt(i));
            int row = getRow(state);

        }
        return 0;
    }

    private static int getRow(State state) {
        switch (state) {
            case START:
                return 0;
            case SIGNED:
                return 1;
            case IN_NUMBER:
                return 2;
            case END:
                return 3;
        }
    }

    static int getCol(char c) {
        if (c == ' ') {
            return 0;
        } else if (c == '-' || c == '+') {
            return 1;
        } else if (c > '0' && c < '9') {
            return 2;
        } else {
            return 3;
        }
    }

    private static boolean overflow(int ret, int single) {
        return false;
    }

}
