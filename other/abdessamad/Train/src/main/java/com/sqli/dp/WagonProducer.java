package com.sqli.dp;

import com.sqli.train.Head;
import com.sqli.train.Wagon;

/* WagonProduer is an helper to create wagon object */
public class WagonProducer {

	public Wagon create(char c) {
		String absuluteName = "com.sqli.dp.Factory" + c;
		IWagonFactory factory;
		Wagon wagon = null;
		try {
			/* using java reflection to create object dynamically
			 * and without the "new" operator
			 */
			factory = (IWagonFactory) Class.forName(absuluteName).newInstance();
			wagon = factory.getInstance();
		} catch (Exception e) {
		}
		/* if we get exception "null" will be returned */
		return wagon;
	}
	
	public static boolean isHead(Wagon wagon){
		return wagon instanceof Head;
	}

}
