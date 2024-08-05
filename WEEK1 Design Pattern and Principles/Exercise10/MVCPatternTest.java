public class MVCPatternTest {
    public static void main(String[] args) {

        Student model = retrieveStudentFromDatabase();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        controller.updateView();

        controller.setStudentName("John Doe");
        controller.setStudentGrade("A");

        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        Student student = new Student();
        student.setName("Jane Doe");
        student.setId("001");
        student.setGrade("B");
        return student;
    }
}
