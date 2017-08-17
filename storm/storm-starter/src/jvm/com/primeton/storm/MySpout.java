package com.primeton.storm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;


public class MySpout implements IRichSpout {
	
	private static final long serialVersionUID = 1L;
	
	FileInputStream fis;
	InputStreamReader isr;
	BufferedReader br;
	String str = null;
	SpoutOutputCollector collector;

	@Override
	public void ack(Object msgId) {
		System.out.println("spout ack " + msgId.toString());
	}

	@Override
	public void activate() {
	}

	@Override
	public void close() {
		if(br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(isr != null) {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deactivate() {
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("spout ack " + msgId.toString());
	}

	@Override
	public void nextTuple() {
		try {
			while((str = this.br.readLine()) != null) {
				this.collector.emit(new Values(str));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		try {
			this.collector = collector;
			this.fis = new FileInputStream("track.log");
			this.isr = new InputStreamReader(fis,"UTF-8");
			this.br = new BufferedReader(isr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("log"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
