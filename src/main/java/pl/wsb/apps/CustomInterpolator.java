package pl.wsb.apps;

import javafx.animation.Interpolator;
public class CustomInterpolator {
    public static final Interpolator BOUNCING = new BouncingInterpolator();
    public static final Interpolator OVERSHOOT_1 = new OvershootInterpolator(OvershootInterpolator.T1);
    public static final Interpolator OVERSHOOT_2 = new OvershootInterpolator(OvershootInterpolator.T2);
    public static final Interpolator OVERSHOOT_3 = new OvershootInterpolator(OvershootInterpolator.T3);
    public static final Interpolator SPRING = new OvershootExpInterpolator();

    public static class OvershootExpInterpolator extends Interpolator{
        @Override
        protected double curve(double v) {
            return 1 + Math.exp(- 4 * v) * Math.sin(18 * v);
        }
    }

    public static class OvershootInterpolator extends Interpolator{
        private static double T1 = 0.5;
        private static double T2 = 2;
        private static double T3 = 4;
        private double T;

        private OvershootInterpolator(double T){
            this.T = T;
        }
        @Override
        protected double curve(double v) {
            return (T + 1) * (v -1)*(v -1)*(v -1) + T *(v -1) *(v -1) + 1;
        }
    }


    public static class BouncingInterpolator extends Interpolator {
        private static final double S1 = 0.31489;
        private static final double S2 = 0.65990;
        private static final double S3 = 0.85908;
        private static final double F1 = 1.1226;
        private static final double F2 = 0.54719;
        private static final double F3 = 0.8526;
        private static final double F4 = 1.0435;
        private static final double B1 = 0.7;
        private static final double B2 = 0.9;
        private static final double B3 = 0.95;

        private BouncingInterpolator(){}

        @Override
        protected double curve ( double v){
            return v < S1
                    ? 8 * F1 * v * F1 * v
                    : v < S2 ? 8 * (F1 * v - F2) * (F1 * v - F2) + B1
                    : v < S3 ? 8 * (F1 * v - F3) * (F1 * v - F3) + B2
                    : 8 * (F1 * v - F4) * (F1 * v - F4) + B3;
        }
    }
}
