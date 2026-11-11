// Jacqueline Salazar - ITSE 2321

public class CourseLab {

    @FunctionalInterface
    interface ScoreRule {
        boolean accepts(int score);
    }

    static class PassingRule implements ScoreRule {
        @Override
        public boolean accepts(int score) {
            return score >= 70;
        }
    }

    static int countMatching(int[] scores, ScoreRule rule) {
        int count = 0;
        for (int score : scores) {
            if (rule.accepts(score)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] scores = {62, 70, 88, 95};
        int[] boundaryScores = {69, 70, 89, 90};
        int[] emptyScores = {};

        // Part 2: Create courses and their reports
        Course javaCourse = new Course("Java", 24);
        Course cppCourse = new Course("C++", 18);

        Course.RosterReport javaReport = javaCourse.new RosterReport();
        Course.RosterReport cppReport = cppCourse.new RosterReport();

        javaReport.print();
        cppReport.print();

        javaCourse.setEnrollment(25);
        javaReport.print();

        System.out.println("Java report enrollment: " + javaReport.getEnrollment());
        System.out.println("C++ report enrollment: " + cppReport.getEnrollment());
        System.out.println();

        // Part 3: Create and print a catalog entry
        Catalog.Entry entry = new Catalog.Entry("ITSE 2321");
        entry.print();
        System.out.println();

        // Part 4: Create the required score rules
        ScoreRule passing = new PassingRule();

        ScoreRule honors = new ScoreRule() {
            @Override
            public boolean accepts(int score) {
                return score >= 90;
            }
        };

        ScoreRule belowPassing = score -> score < 70;

        // Part 5: Run and label all nine required tests
        System.out.println("Scores, passing count: " + countMatching(scores, passing));
        System.out.println("Scores, honors count: " + countMatching(scores, honors));
        System.out.println("Scores, belowPassing count: " + countMatching(scores, belowPassing));

        System.out.println("Boundary scores, passing count: " + countMatching(boundaryScores, passing));
        System.out.println("Boundary scores, honors count: " + countMatching(boundaryScores, honors));
        System.out.println("Boundary scores, belowPassing count: " + countMatching(boundaryScores, belowPassing));

        System.out.println("Empty scores, passing count: " + countMatching(emptyScores, passing));
        System.out.println("Empty scores, honors count: " + countMatching(emptyScores, honors));
        System.out.println("Empty scores, belowPassing count: " + countMatching(emptyScores, belowPassing));
        System.out.println();

        // Part 6A: Replace Anonymous Implementation with a Lambda
        ScoreRule honorsLambda = score -> score >= 90;
        System.out.println("Scores, honorsLambda count: " + countMatching(scores, honorsLambda));

        // Part 6B: Capture a Local Variable
        int threshold = 80;
        ScoreRule meetsThreshold = score -> score >= threshold;
        System.out.println("Scores, meetsThreshold (80) count: " + countMatching(scores, meetsThreshold));
        System.out.println();

        // Part 7: Diagnose and Repair a Lambda
        // Invalid declaration kept as a comment:
        // ScoreRule broken = score -> { score >= 70; };

        ScoreRule fixedExpression = score -> score >= 70;
        ScoreRule fixedBlock = score -> { return score >= 70; };

        System.out.println("Scores, fixedExpression count: " + countMatching(scores, fixedExpression));
        System.out.println("Scores, fixedBlock count: " + countMatching(scores, fixedBlock));
    }
}

class Course {
    private String title;
    private int enrollment;

    public Course(String title, int enrollment) {
        this.title = title;
        this.enrollment = enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public class RosterReport {
        public void print() {
            System.out.println(title + ": " + enrollment + " students");
        }

        public int getEnrollment() {
            return enrollment;
        }
    }
}

class Catalog {
    static class Entry {
        private String code;

        Entry(String code) {
            this.code = code;
        }

        void print() {
            System.out.println(code);
        }
    }
}
