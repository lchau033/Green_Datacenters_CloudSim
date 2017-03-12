package org.cloudbus.cloudsim.examples.power.planetlab;

import java.io.IOException;

public class PueIqrRs {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		boolean enableOutput = true;
		boolean outputToFile = false;
		String inputFolder = NonPowerAware.class.getClassLoader().getResource("workload/planetlab").getPath();
		String outputFolder = "output";
		String workload = "20110303"; // PlanetLab workload
		String vmAllocationPolicy = "pueiqr"; // IQR Based PUE (PUE) VM allocation policy
		String vmSelectionPolicy = "rs"; // Random Selection (RS) VM selection policy
		String parameter = "1.5"; // the safety parameter of the IQR policy

		new PlanetLabRunner(enableOutput, outputToFile, inputFolder, outputFolder, workload, vmAllocationPolicy,
				vmSelectionPolicy, parameter);
	}
}
