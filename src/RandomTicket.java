// import java.security.SecureRandom;
public void main() {
    int Students = 12;
    int Questions = 13;
    int q1, q2;
    // Random r = new Random();
    SecureRandom r = new SecureRandom();
    for (int i = 1; i <= Students; i++) {
        q1 = r.nextInt(Questions) + 58;
        q2 = r.nextInt(Questions) + 58;
        println(i + ") " + q1 + ", " + q2);
    }
}
