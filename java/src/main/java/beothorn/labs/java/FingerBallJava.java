package beothorn.labs.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import beothorn.fingerball.FingerBallGame;

public class FingerBallJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("beothorn/labs/resources");
    PlayN.run(new FingerBallGame());
  }
}
