// import java.security.SecureRandom;
public void main() {
    int Students = 13;
    int Questions = 21;
    int q1, q2;
    // Random r = new Random();
    SecureRandom r = new SecureRandom();
    for (int i = 1; i <= Students; i++) {
        q1 = r.nextInt(Questions) + 9;
        q2 = r.nextInt(Questions) + 9;
        println(i + ") " + q1 + ", " + q2);
    }
}
