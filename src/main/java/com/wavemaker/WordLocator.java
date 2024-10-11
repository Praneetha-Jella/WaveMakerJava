package com.wavemaker;

public class WordLocator {
    public int lineNumber;
    public int position;

    public WordLocator(int lineNumber,int position) {
        this.lineNumber = lineNumber;
        this.position = position;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPosition(){
        return position;
    }

    @Override
    public String toString() {
        return "Line: " + lineNumber + ", Position: " + position;
    }
}
