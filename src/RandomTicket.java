import static java.lang.IO.println;

// import java.security.SecureRandom;
void main() {
    int Students = 6;
    int start = 18;
    int end = 111;
    int Questions = end - start + 1;
    int q1, q2, q3;
    // Random r = new Random();
    SecureRandom r = new SecureRandom();
    for (int i = 1; i <= Students; i++) {
        q1 = r.nextInt(Questions) + start;
        q2 = r.nextInt(Questions) + start;
        q3 = r.nextInt(Questions) + start;
        println(i + ") " + q1 + ", " + q2 + ", " + q3 );
    }
}