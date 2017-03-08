package org.cloudbus.cloudsim.power;

import java.util.List;
import java.util.Set;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.examples.power.Constants;

public class PowerVmAllocationPolicyMigrationPowerUsageEfficiency extends PowerVmAllocationPolicyMigrationAbstract {

	/** The utilization threshold. */
	private double utilizationThreshold = 0.9;
	
	/**
	 * Instantiates a new power vm allocation policy migration mad.
	 * 
	 * @param hostList the host list
	 * @param vmSelectionPolicy the vm selection policy
	 * @param utilizationThreshold the utilization threshold
	 */
	public PowerVmAllocationPolicyMigrationPowerUsageEfficiency(
			List<? extends Host> hostList,
			PowerVmSelectionPolicy vmSelectionPolicy,
			double utilizationThreshold) {
		super(hostList, vmSelectionPolicy);
		setUtilizationThreshold(utilizationThreshold);
	}

	@Override
	protected boolean isHostOverUtilized(PowerHost host) {
		addHistoryEntry(host, getUtilizationThreshold());
		double totalRequestedMips = 0;
		for (Vm vm : host.getVmList()) {
			totalRequestedMips += vm.getCurrentRequestedTotalMips();
		}
		double utilization = totalRequestedMips / host.getTotalMips();
		return utilization > getUtilizationThreshold();
	}
	
	/**
	 * Sets the utilization threshold.
	 * 
	 * @param utilizationThreshold the new utilization threshold
	 */
	protected void setUtilizationThreshold(double utilizationThreshold) {
		this.utilizationThreshold = utilizationThreshold;
	}

	/**
	 * Gets the utilization threshold.
	 * 
	 * @return the utilization threshold
	 */
	protected double getUtilizationThreshold() {
		return utilizationThreshold;
	}
	
	@Override
	public PowerHost findHostForVm(Vm vm) {
		return this.findHostForVm(vm, null);
	}
	
	@Override
	public PowerHost findHostForVm(Vm vm, Set<? extends Host> excludedHosts) {
		double min = Double.MAX_VALUE;
		PowerHost minHost = null;
		for(PowerHost host : this.<PowerHost> getHostList()){
			if (excludedHosts != null && excludedHosts.contains(host)) {
				continue;
			}
			
			double powerUsageEfficiency = host.getPowerUsageEfficiency();
			if (powerUsageEfficiency < min && host.isSuitableForVm(vm)) {
				min = powerUsageEfficiency;
				minHost = host;
			}
		}
		return minHost;
	}

}
