This project invovlves a simulation using CloudSim a software that 
is available here (https://github.com/Cloudslab/cloudsim/releases). 
For this simulation as a team of two we tried to simulate several 
Green Datacenter algorithms to migrate virtual machines and save power. 
In order to do this we changed the following files:
----------------------------------------------------------------------------------------------------
---PowerVmAllocationPolicyMigrationIQRBasedPUE.java and 
   PowerVmAllocationPolicyMigrationStaticBasedPUE.java which respectively 
   contain the implementation of our Power Usage Efficiency (PUE) algorithm 
   using the Inter quartile Range's strategy to find overloadeed and underloaded 
   hosts and using the Static based strategy to find overloaded and underloaded 
   hosts. We created both of these because we were trying to find out what would 
   be the best way to find an overloaded and underloaded host. We found that the 
   IQR PUE algorithm gave us the best results and we will show these results in 
   our report.  
---PueIqrMc.java, PueIqrRs.java, PueStaticMc.java and PueStaticRs.java 
   which respectively will run our main provisioning algorithm with the IQR based 
   strategy to find overloaded and underloaded hosts using the Power Best Fit Decreasing 
   as a selection policy, the random selection policy, our PUE algorithm with the Static 
   Based strategy to find overloaded and underloaded hosts using the Power Best Fit 
   Decreasing as a selection policy and the random selection policy. 
---The runner abstract (RunnerAbstract.java) where we added our policies in the 
   else if because policies are decided based on strings.
---Constants.java where we added constants to convert server load into cooling with a 
   method. The file Host.jave where we just added a method for calculating the 
   currentTotalRequestedMips of a Host. 
---The helper, the power host and the abstract for the VM migration allocation policy, 
   which are respectively Helper.java and PowerHost.java and 
   PowerVmAllocationPolicyMigrationAbstract.java, where we added the implementation 
   to calculate the computing and cooling power history and the PUE history, in order 
   to get average computing and cooling power and PUE for each provisioning policy. 
   ------------------------------------------------------------------------------------------------------
   A report of our research is avaible on overleaf at https://www.overleaf.com/8634797pmmxxwhwsfwn#/30747965/.
