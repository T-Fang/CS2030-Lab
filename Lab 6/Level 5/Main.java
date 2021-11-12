import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfRecords = sc.nextInt();
		
		// initialize the roster
        Roster roster = new Roster("AY1920");

        for (int i = 0; i < numOfRecords; i++) {
            Student student = new Student(sc.next());
            Module module = new Module(sc.next());
            String assessmentName = sc.next();
            String grade = sc.next();
            Assessment assessment = new Assessment(assessmentName, grade);

			//check whether the student, module and assessment exist in the roster.
			
			roster.get(student.getKey()).ifPresentOrElse(s -> {
	            s.get(module.getKey()).ifPresentOrElse(m -> { m.put(assessment);}, () -> { 
					// if the module is not found
	                s.put(module);
	                module.put(assessment);
	            });
	        }, () -> { // if the student is not found
	            roster.put(student);
				student.put(module);
				module.put(assessment);
	        });

        }
		
		// initalize an ArrayList to store the output.
        ArrayList<String> output = new ArrayList<>();

		// take in queries
        while(sc.hasNext()) {
            String student = sc.next();
            String module = sc.next();
            String assessment = sc.next();

            try {
                String grade = roster.getGrade(student, module, assessment);
                output.add(grade);
            } catch (NoSuchRecordException e) {
                output.add(e.toString());
            }
        }

        for (String s: output) {
            System.out.println(s);
        }
		
		sc.close();

    }
}
