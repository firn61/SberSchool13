package edu.sber.lect8.cacheHolders;

import java.util.Arrays;

class ArgsHolder{
    private Object[] args;

    public ArgsHolder(Object[] args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArgsHolder that = (ArgsHolder) o;
        return Arrays.equals(args, that.args);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(args);
    }

    @Override
    public String toString() {
        return "ArgsHolder{" +
                "args=" + Arrays.toString(args) +
                '}';
    }
}