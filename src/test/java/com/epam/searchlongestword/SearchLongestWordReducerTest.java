package com.epam.searchlongestword;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchLongestWordReducerTest {
    ReduceDriver<IntWritable, Text, IntWritable, Text> reduceDriver;

    @Before
    public void setUp() {
        SearchLongestWordReducer mapper = new SearchLongestWordReducer();
        reduceDriver = ReduceDriver.newReduceDriver(mapper);
    }

    @Test
    public void testFindLongestWords() throws IOException {
        List<Text> values = new ArrayList<Text>();
        values.add(new Text("million"));
        values.add(new Text("billion"));
        reduceDriver.withInput(new IntWritable(7), values);
        List<Text> values1 = new ArrayList<Text>();
        values1.add(new Text("touched"));
        values1.add(new Text("billion"));
        reduceDriver.withInput(new IntWritable(7), values1);
        reduceDriver.withOutput(new IntWritable(7), new Text("billion, million, touched"));
        reduceDriver.runTest();
    }

    @Test
    public void testReduceEmptyKeyValue() throws IOException {
        reduceDriver.withInput(new IntWritable(), new ArrayList<Text>());
        reduceDriver.runTest();
    }

    @Test
    public void testReduceEmptyKey() throws IOException {
        List<Text> values = new ArrayList<Text>();
        values.add(new Text("Krishna"));
        reduceDriver.withInput(new IntWritable(), values);
        reduceDriver.runTest();
    }

    @Test
    public void testReduceEmptyValues() throws IOException {
        reduceDriver.withInput(new IntWritable(8), new ArrayList<Text>());
        reduceDriver.runTest();
    }
}
