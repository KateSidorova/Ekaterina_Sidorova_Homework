package Lesson_15;

import java.util.List;

public class Student {
    private String name;
    private String group;
    private int course;
    private List<Double> grades;

    public Student(String name, String group, int course, List<Double> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName() { return name; }
    public String getGroup() { return group; }
    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }
    public List<Double> getGrades() { return grades; }
    public double getAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
}