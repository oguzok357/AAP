import java.util.Arrays;
import java.util.Random;

public class SortingTest {

    // 1. Пузырьковая сортировка
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 2. Быстрая сортировка
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // 3. Сортировка расчёской
    public static void combSort(int[] arr) {
        int gap = arr.length;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            gap = (int) (gap / 1.3);
            if (gap < 1) gap = 1;

            swapped = false;
            for (int i = 0; i < arr.length - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }


    // Копирование массива
    public static int[] copy(int[] a) {
        return Arrays.copyOf(a, a.length);
    }

    // Генерация случайного массива
    public static int[] randomArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    // Частично отсортированный (75% на месте)
    public static int[] partiallySorted75(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Random r = new Random();
        int toSwap = n / 4;
        for (int i = 0; i < toSwap; i++) {
            int i1 = r.nextInt(n);
            int i2 = r.nextInt(n);
            int t = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = t;
        }
        return arr;
    }

    // Почти отсортированный (90% на месте)
    public static int[] almostSorted90(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Random r = new Random();
        int toSwap = n / 10;
        for (int i = 0; i < toSwap; i++) {
            int i1 = r.nextInt(n);
            int i2 = r.nextInt(n);
            int t = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = t;
        }
        return arr;
    }

    // Обратно отсортированный
    public static int[] reversedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    // Много дубликатов (10% уникальных)
    public static int[] arrayWithDuplicates(int n) {
        Random r = new Random();
        int uniqueCount = Math.max(1, n / 10);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(uniqueCount);
        }
        return arr;
    }

    // Замер времени: 5 запусков, среднее значение
    public static long measureTime(Runnable sort, int[] original) {
        long total = 0;
        for (int run = 0; run < 5; run++) {
            int[] arr = copy(original);
            long start = System.nanoTime();
            sort.run();
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / 5;
    }

    // Основная программа
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000};

        Object[][] testCases = {
                {"Случайный", (java.util.function.IntFunction<int[]>) SortingTest::randomArray},
                {"Частично отсортированный (75%)", (java.util.function.IntFunction<int[]>) SortingTest::partiallySorted75},
                {"Почти отсортированный (90%)", (java.util.function.IntFunction<int[]>) SortingTest::almostSorted90},
                {"Обратный", (java.util.function.IntFunction<int[]>) SortingTest::reversedArray},
                {"Много дубликатов", (java.util.function.IntFunction<int[]>) SortingTest::arrayWithDuplicates}
        };

        for (int size : sizes) {
            System.out.println("\n=== Размер массива: " + size + " ===");
            for (Object[] testCase : testCases) {
                String name = (String) testCase[0];
                java.util.function.IntFunction<int[]> generator = (java.util.function.IntFunction<int[]>) testCase[1];
                int[] arr = generator.apply(size);

                long timeBubble = measureTime(() -> bubbleSort(arr), arr);
                long timeQuick = measureTime(() -> quickSort(arr), arr);
                long timeComb = measureTime(() -> combSort(arr), arr);

                System.out.printf("%-30s | Пузырьковая: %8d мкс | Быстрая: %8d мкс | Расчёска: %8d мкс%n",
                        name,
                        timeBubble / 1000,
                        timeQuick / 1000,
                        timeComb / 1000);
            }
        }
    }

}
