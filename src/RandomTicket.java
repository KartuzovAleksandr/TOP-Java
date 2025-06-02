import java.security.SecureRandom;
public void main() {
    int Students = 12;
    int Questions = 25;
    int q1, q2;
    SecureRandom r = new SecureRandom();
    for (int i = 1; i <= Students; i++) {
        q1 = r.nextInt(Questions) + 65;
        q2 = r.nextInt(Questions) + 65;
        println(i + ") " + q1 + ", " + q2);
    }
}
