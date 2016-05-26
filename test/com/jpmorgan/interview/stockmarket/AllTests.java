package com.jpmorgan.interview.stockmarket;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * AllTests runs all the tests in the Junit suite
 * 
 * @author Eugene Coelho
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@SuiteClasses({ TestShareIndex.class, TestStockRegistration.class, TestCommonStockDividendYield.class,
		TestPreferredStockDividendYield.class, TestStockPriceEarningRatio.class, TestStockTrade.class,
		TestVolumeWeightedStockPrice.class })
public class AllTests {

	@BeforeClass
	public static void setUpTestData() {

	}

	@AfterClass
	public static void tearDownTestData() {

	}

}
