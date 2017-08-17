package com.primeton.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;


public class MyBolt implements IRichBolt {
	
	private static final long serialVersionUID = 1L;
	
	int num = 0;
	String valueString = "";
	OutputCollector collector;

	@Override
	public void cleanup() {
	}

	@Override
	public void execute(Tuple tuple) {
		try {
			valueString = tuple.getStringByField("log");
			if(valueString != null) {
				num ++;
				System.out.println("lines =" + num + "    session_id=" + valueString.split("\t")[1] + "    time=" + valueString.split("\t")[2]);
			}
			collector.ack(tuple);
		} catch (Exception e) {
			collector.fail(tuple);
			e.printStackTrace();
		}
	}

	@Override
	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(""));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
