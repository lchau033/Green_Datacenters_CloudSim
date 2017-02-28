package org.cloudbus.cloudsim.power;

import java.util.List;
import java.util.Set;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.examples.power.Constants;

public class PowerVmAllocationPolicyMigrationPowerUsageEfficiency extends PowerVmAllocationPolicyMigrationAbstract {

	
	private double utilizationThreshold = 0.9;

	public PowerVmAllocationPolicyMigrationPowerUsageEfficiency(List<? extends Host> hostList,
			PowerVmSelectionPolicy vmSelectionPolicy) {
		super(hostList, vmSelectionPolicy);
		// TODO Auto-generated constructor stub
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
			
			double currentRequestedMips = host.getCurrentRequestedMips();
			double currentMips = host.getTotalMips();
			double serverLoadPercentage = currentRequestedMips/(currentMips+currentRequestedMips);
			
			double computePower = host.getPowerModel().getPower(serverLoadPercentage);
			double coolingPower = Constants.getCoolingPower(serverLoadPercentage, computePower);
			double powerUsageEfficiency = (coolingPower + computePower) / computePower;
			if( powerUsageEfficiency< min && host.isSuitableForVm(vm)){
				min = powerUsageEfficiency;
				minHost = host;
			}
		}
		return minHost;
	}

}
