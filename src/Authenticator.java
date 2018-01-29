public class Authenticator {
    public Authenticator() {

    }
    public boolean authenticate(Integer[] signupDiff, Integer[] signupDurations, Integer[] loginDiff, Integer[] loginDurations) {
        boolean auth = false;
        int threshold = 30;
        float marks = 0;
        float full_marks = loginDiff.length + loginDurations.length;

        for(int i=0; i<loginDiff.length; i++) {
            if(Math.abs(loginDiff[i]-signupDiff[i])<=threshold) {
                marks++;
            }
        }
        for (int i=0; i<loginDurations.length; i++) {
            if(Math.abs(loginDurations[i]-signupDurations[i])<=threshold) {
                marks++;
            }
        }
        if (marks/full_marks>=0.8) {
            auth = true;
        } else {
            auth = false;
        }
        System.out.println(marks/full_marks);
        return auth;
    }
}
