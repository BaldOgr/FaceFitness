package kz.baldogre.learn.common;

public final class RunOnBackground {
    private RunOnBackground() {

    }

    public static void runOnBackground(Runnable runnable) {
        new Thread(runnable).start();
    }
}
