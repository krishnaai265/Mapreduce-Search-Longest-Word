package com.epam.searchlongestword;

import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SearchLongestWordMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
    private IntWritable outputKey;
    private Text outputValue = new Text();
    private static final String WORD_PATTERN = "[a-z'-]+";
    private static final String PUNCT_PATTERN = "[!\\\"#$%&()*+,./:;<=>?@\\[\\]^_`{|}~]";

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (isValidInput(value)) {
            StringTokenizer stringTokenizer = new StringTokenizer(value.toString());
            Set<String> longestWords = new TreeSet<String>();
            int maxWordlength = 0;
            while (stringTokenizer.hasMoreTokens()) {
                String element = stringTokenizer.nextToken().toLowerCase();
                element = element.replaceAll(PUNCT_PATTERN, "");
                if(!element.matches(WORD_PATTERN)) {
                    continue;
                }
                int currentWordLength = element.length();
                if(currentWordLength == maxWordlength) {
                    longestWords.add(element);
                }
                if(currentWordLength > maxWordlength) {
                    maxWordlength = currentWordLength;
                    longestWords.clear();
                    longestWords.add(element);
                }
            }
            outputKey = new IntWritable(maxWordlength);
            for(String word: longestWords) {
                outputValue.set(word);
                context.write(outputKey, outputValue);
            }
        }
    }

    private boolean isValidInput(Text value) {
        if (value == null || value.getLength() <= 0) {
            return false;
        }
        return true;
    }
}
