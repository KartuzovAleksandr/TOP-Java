import static java.lang.IO.println;

// import java.security.SecureRandom;
public void main() {
    int Students = 13;
    int start = 65;
    int end = 111;
    int Questions = end - start + 1;
    int q1, q2;
    // Random r = new Random();
    SecureRandom r = new SecureRandom();
    for (int i = 1; i <= Students; i++) {
        q1 = r.nextInt(Questions) + start;
        q2 = r.nextInt(Questions) + start;
        println(i + ") " + q1 + ", " + q2);
    }
}