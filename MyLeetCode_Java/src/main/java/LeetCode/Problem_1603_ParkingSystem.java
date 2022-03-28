package LeetCode;

public class Problem_1603_ParkingSystem {

    class ParkingSystem {

        private int[]size;

        public ParkingSystem(int b, int m, int s) {
            size = new int[4];
            size[1] = b;
            size[2] = m;
            size[3] = s;
        }

        public boolean addCar(int carType) {
            if(size[carType] >= 1) {
                size[carType]--;
                return true;
            } else {
                return false;
            }
        }
    }
}
