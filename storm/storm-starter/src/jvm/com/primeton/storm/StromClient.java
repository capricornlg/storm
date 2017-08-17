package com.primeton.storm;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;


public class StromClient {

	public static void main(String[] args) {

		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("spout", new MySpout(), 1);

		builder.setBolt("bolt", new MyBolt(), 1).shuffleGrouping("spout");

		Map conf = new HashMap();

		conf.put(Config.TOPOLOGY_WORKERS, 4);

		if (args.length > 0) {
			try {
				StormSubmitter.submitTopology(args[0], conf,
						builder.createTopology());
			} catch (AlreadyAliveException e) {
				e.printStackTrace();
			} catch (InvalidTopologyException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			LocalCluster localCluster = new LocalCluster();

			localCluster.submitTopology("myTopology", conf,
					builder.createTopology());
		}
	}

}
