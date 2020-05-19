package com.example.lib.statemachine;

import javax.sound.midi.MidiChannel;

/*
 * 1.如果这个对象的操作就一个，那其实就没有必要用状态机，反正不需要在很多个地方做分支判断。
 * 2.对象有很多个操作，并且在不同状态下所有操作行为表现基本都不相同，那就需要考虑用状态机模式。
 *
 */
public class StateMachine {

    public static void main(String[] args) {

    }


    enum State {
        SMALL, MIDDLE, BIG
    }

    static class Mario {
        int mScore;

        State mState = State.SMALL;

        void eatMushroom() {
            if (mState == State.SMALL) {
                mState = State.MIDDLE;
            } else if (mState == State.MIDDLE) {
                mState = State.BIG;
            }
        }

        void eatCoin() {
            if (mState == State.SMALL) {
                mScore += 100;
            } else if (mState == State.MIDDLE) {
                mScore += 200;
            } else {
                mScore += 300;
            }
        }

        @Override
        public String toString() {
            return "score " + mScore;
        }
    }

    /*
     * 状态机模式，采用数组实现，一维数组
     */
    static class MarioArrSM {
        State[] transitionTable = new State[]{
                State.MIDDLE,
                State.BIG,
                null
        };

        int[] scoreTable = new int[]{100, 200, 300};

        int mScore;
        State mState = State.SMALL;

        void eatMushroom() {
            mState = transitionTable[mState.ordinal()];
        }

        void eatCoin() {
            mScore += scoreTable[mState.ordinal()];
        }
    }

    static class MarioArrSM2{
        State[] transitionTable = new State[]{
                State.MIDDLE,
                State.BIG,
                null
        };

        int[] scoreTable = new int[]{100, 200, 300};

        int mScore;
        State mState = State.SMALL;

        void eatMushroom() {
            mState = transitionTable[mState.ordinal()];
        }

        void eatCoin() {
            mScore += scoreTable[mState.ordinal()];
        }
    }
}
