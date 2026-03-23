/**
 * Client class to test Pen LLD.
 */
public class Main {
    public static void main(String[] args) {
        AbstractPen ballPen = PenFactory.createBallPen("Reynolds", "Blue");
        ballPen.start();
        ballPen.write("Hello world");
        ballPen.close();

        System.out.println("--------------------------------");

        AbstractPen gelPen = PenFactory.createGelPen("Cello", "Black");
        gelPen.start();
        gelPen.write("solid principles");
        gelPen.refill();
        gelPen.write("design patterns");
        gelPen.close();

        System.out.println("--------------------------------");
        System.out.println(ballPen);
        System.out.println(gelPen);
    }
}