package com.wavemaker.file;

public class FileContent {
        private String content;
        private int lineCount;

        public FileContent(String content, int lineCount) {
            this.content = content;
            this.lineCount = lineCount;
        }
        public String getContent() {
            return content;
        }
        public int getLineCount() {
            return lineCount;
        }
}