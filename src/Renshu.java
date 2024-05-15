class Renshu {

    // xを2倍にして返す関数
    public int doubleValue(int x) {
        return x * 2;
    }

    // ここに続きを実装していく。
    public int sumUpToN(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public int sumFromPtoQ(int p, int q) {
        if (q < p) {
            return -1;
        }
        int sum = 0;
        for (int i = p; i <= q; i++) {
            sum += i;
        }
        return sum;
    }

    public int sumFromArrayIndex(int[] a, int index) {
        if (index < 0 || a.length - 1 < index) {
            return -1;
        }
        int sum = 0;
        for (int i = index; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    public int selectMaxValue(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        return max;
    }

    public int selectMaxIndex(int[] a) {
        int max = a[0];
        int index = 0;
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }

    public int selectMinValue(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        return min;
    }

    public int selectMinIndex(int[] a) {
        int min = a[0];
        int index = 0;
        for (int i = 1; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
                index = i;
            }
        }
        return index;
    }

    public void swapArrayElements(int[] p, int i, int j) {
        int value = p[i];
        p[i] = p[j];
        p[j] = value;
    }

    public boolean swapTwoArrays(int[] a, int[] b) {
        if(a.length!=b.length){
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            a[i] = b[i];
            b[i] = value;
        }
        return true;
    }
}