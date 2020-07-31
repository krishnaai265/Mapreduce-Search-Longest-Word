package com.epam.searchlongestword;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SearchLongestWordReducer extends Reducer<IntWritable,Text,IntWritable, Text> {
    private final IntWritable outputKey = new IntWritable();
    private final StringBuilder outputValue = new StringBuilder();
    private final Set<String> longestWords = new TreeSet<String>();

    @Override
    public void reduce(IntWritable key, Iterable<Text> values, Context context
    ) throws IOException, InterruptedException {
        if (!isValidInput(key, values) && key.get() < outputKey.get()) {
            return;
        }
        for(Text value: values) {
            longestWords.add(value.toString());
        }
        outputKey.set(key.get());
    }

    @Override
    public void cleanup(Context context) throws IOException,
            InterruptedException {
        if (isValidInput(outputKey, longestWords)) {
            for (String value : longestWords) {
                if (outputValue.length() == 0) {
                    outputValue.append(value);
                } else {
                    outputValue.append(", " + value);
                }
            }
            context.write(outputKey, new Text(outputValue.toString()));
        }
    }

    private boolean isValidInput(IntWritable key, Iterable<Text> values) {
        if (key == null || key.get() <= 0 || values == null) {
            return false;
        }
        return true;
    }

    private boolean isValidInput(IntWritable key, Set<String> values) {
        if (key == null || key.get() <= 0 || values.isEmpty()) {
            return false;
        }
        return true;
    }
}
