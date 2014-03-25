package com.pcache.main;

import java.util.ArrayList;

import com.pcache.DO.Timeseries;
import com.pcache.dataaccess.CacheEngine;
import com.pcache.exceptions.PCacheException;

public class Main {

	public static void main(String[] args) throws PCacheException {

		ArrayList<String> timestamps = new ArrayList<String>();
		ArrayList<String> dataPoints = new ArrayList<String>();

		timestamps.add("1");
		timestamps.add("2");
		timestamps.add("3");

		dataPoints.add("x");
		dataPoints.add("y");
		dataPoints.add("z");

		Timeseries ts = new Timeseries(timestamps, dataPoints);

		CacheEngine.addNewNamespace("sentinel");
		CacheEngine.renameNamespace("sentinel", "xentinel");

		CacheEngine.addNewStructure("xentinel", "filmstrip", "tid,sid");
		CacheEngine.renameStructure("xentinel", "filmstrip", "xilmstrip");

		CacheEngine.addNewStructureInstance("xentinel", "xilmstrip", "tid=1, sid=1", ts);

		Timeseries ts2 = CacheEngine.getTimeseries("xentinel","xilmstrip","tid=1,sid=1");

		System.out.println("Cache initialized | " + ts2.size());
	}

}
