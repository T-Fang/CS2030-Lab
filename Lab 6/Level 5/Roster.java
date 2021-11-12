import java.util.NoSuchElementException;
public class Roster extends KeyableMap<String, String, Student> {
    public Roster(String string) {
        super(string);
    }

    @Override
    public Roster put(Student student) {
        super.put(student);
        return this;
    }
	
	// edited
    public String getGrade(String student, String module, String assessment) throws NoSuchRecordException {
        try {
            return super.get(student).orElseThrow().get(module).orElseThrow().get(assessment).orElseThrow().getGrade();
        } catch (NoSuchElementException e) {
            throw new NoSuchRecordException(String.format("No such record: %s %s %s", student, module, assessment));
        }
    }
}
