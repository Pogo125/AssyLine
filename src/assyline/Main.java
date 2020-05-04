package assyline;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		
		/**
		 *Counter for the amount of PLCs which are in run mode 
		 */				 		
		int pLCsInRunModeAmount = 0;

		/**
		 *Array List which contains all PLCs 
		 */
		List<Plc> plcList = new ArrayList<Plc>();
		
		//Fill array list with PLCs				
		for(int i=0; i<1100; i++) {			
			plcList.add(new Plc());			
		}
		
		//STEP 1: Place all PLCs in Run mode (first employee)		
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------- STEP 1: Place all PLCs in Run mode ---------");
		System.out.println("------------------------------------------------------------------");
		
		for(int plcNr=0; plcNr<1100; plcNr++) {			
			plcList.get(plcNr).setPlcState(PlcState.RUN);			
		}		
		outputPLCStates(plcList);
		
		//STEP 2: Place every second PLCs in program mode (second employee)		
		//Assumption: By "every second PLCs" it is meant to start off with the second PLC
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------- Place every second PLCs in program mode ---------");
		System.out.println("------------------------------------------------------------------");
		
		for(int plcNr=1; plcNr<1100; plcNr=plcNr+2) {			
			plcList.get(plcNr).setPlcState(PlcState.PROGRAM);			
		}	
		
		outputPLCStates(plcList);
				
		//STEPs 3ff: Switch state of remaining PLCs as requested by plant manager		
		//iterate through all employees
		System.out.println("------------------------------------------------------------------");
		System.out.println("Switch state of remaining PLCs as requested by plant manager");
		System.out.println("------------------------------------------------------------------");
		
		for(int employeeNr = 3; employeeNr <=1100; employeeNr++) {			
					
			System.out.println("----------------------------------------------------");
			System.out.println("EMPLOYEE NR: " + employeeNr);
			System.out.println("----------------------------------------------------");
			
			//iterate through all PLCs, starting with the one corresponding in number to the currently active employee
			for(int plcNr=(employeeNr-1); plcNr<1100; plcNr=plcNr+employeeNr) {				
				
				System.out.println("PLC No: " + plcNr + " STATE before: " + plcList.get(plcNr).getPlcState());
				
				if(plcList.get(plcNr).getPlcState()==PlcState.PROGRAM) {
					plcList.get(plcNr).setPlcState(PlcState.RUN);				
				} else {
					plcList.get(plcNr).setPlcState(PlcState.PROGRAM);
				}			
				
				System.out.println("PLC No: " + plcNr + " STATE after: " + plcList.get(plcNr).getPlcState());
				System.out.println("--------------------------");
			}			
		}
		
		//Check the state of each PLC, count the PLCs which are in RUN mode and output the result to the console
		for(int i=0; i<1100; i++) {			
			if(plcList.get(i).getPlcState()==PlcState.RUN) {
				pLCsInRunModeAmount = pLCsInRunModeAmount+1;				
			}			
		}
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("Output the final state of the PLCs");
		System.out.println("------------------------------------------------------------------");
		outputPLCStates(plcList);
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("Final result summary");
		System.out.println("------------------------------------------------------------------");		
		System.out.println(pLCsInRunModeAmount + " PLCs are in run mode at the end");
		
	
		
	}
	
	/**
	 * Method for outputting the plc states to the console
	 * @param plcList
	 */
	static void outputPLCStates(List<Plc> plcList) {			
		for(int i=0; i<1100; i++) {
			System.out.println("PLC No: " + i + " STATE: " + plcList.get(i).getPlcState());
		}
	}

}
