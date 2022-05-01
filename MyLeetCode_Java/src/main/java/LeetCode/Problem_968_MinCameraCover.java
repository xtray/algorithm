package LeetCode;

public class Problem_968_MinCameraCover {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // NOTE: 数据类型使用long, 否则会越界
    // 二叉树递归套路
    public static class Info {
        public long uncover; // 没有覆盖, 没有相机
        public long coverNoCam; // 覆盖, 没有相机
        public long coverWithCam; //覆盖, 有相机

        public Info(long un, long no, long yes) {
            uncover = un;
            coverNoCam = no;
            coverWithCam = yes;
        }
    }

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return (int) Math.min(info.uncover + 1, Math.min(info.coverNoCam, info.coverWithCam));
    }

    private Info process(TreeNode root) {
        if (root == null) {
            // 认为是覆盖了, 没有相机的状态
            // 虽然给了三个解, 但是因为要抓系统最小, 所以只会抓到0的解
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        long uncover = left.coverNoCam + right.coverNoCam;
        long coverNoCam = Math.min(left.coverWithCam + right.coverWithCam,
                Math.min(left.coverNoCam + right.coverWithCam,
                        left.coverWithCam + right.coverNoCam));
        long coverWithCam = Math.min(left.uncover, Math.min(left.coverNoCam, left.coverWithCam)) +
                Math.min(right.uncover, Math.min(right.coverNoCam, right.coverWithCam)) + 1;

        return new Info(uncover, coverNoCam, coverWithCam);
    }

    // 贪心的解
    // NOTE: 贪心解可以不使用long类型了!!
    public static enum Status {
        UNCOVERED, // 没覆盖,没相机
        COVER_NO_CAM, // 覆盖, 没相机
        COVER_WITH_CAM // 覆盖, 有相机
    }

    public static class Data {
        public Status status;
        public int camCnts; // 相机数,

        public Data(Status s, int c) {
            status = s;
            camCnts = c;
        }
    }

    public int minCameraCover2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Data data = process2(root);
        return data.camCnts + (data.status == Status.UNCOVERED ? 1 : 0);
    }

    private Data process2(TreeNode root) {
        if(root == null) {
            return new Data(Status.COVER_NO_CAM, 0);
        }
        Data left = process2(root.left);
        Data right = process2(root.right);
        int camCnts = left.camCnts + right.camCnts;
        // 左、或右，哪怕有一个没覆盖
        if(left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(Status.COVER_WITH_CAM, camCnts + 1);
        }
        // 左右孩子，不存在没被覆盖的情况
        if(left.status == Status.COVER_WITH_CAM || right.status == Status.COVER_WITH_CAM) {
            return new Data(Status.COVER_NO_CAM, camCnts);
        }
        // 左右孩子，不存在没被覆盖的情况，也都没有相机
        return new Data(Status.UNCOVERED, camCnts);
    }

}
