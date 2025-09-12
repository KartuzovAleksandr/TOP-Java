// import java.util.Arrays;
void main() {
    Random r = new Random();
    Scanner sc = new Scanner(System.in);
    print("Введите размерность массива: ");
    int n = sc.nextInt();
    int[] m = new int[n];
    for (int i = 0; i < n; i++) {
        m[i] = r.nextInt(50);
    }
    println("Исходный массив = " + Arrays.toString(m));
    int[] mb = bubbleSort(m);
    println("После сортировки пузырьком = " + Arrays.toString(mb));
    int[] mq = quickSort(m, 0, n - 1, true);
    println("После сортировки быстрой = " + Arrays.toString(mq));
}
// сортировка пузырьком
int[] bubbleSort(int[] m)
{
    int temp;
    int n = m.length;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n - i - 1; j++)
        {
            if (m[j] > m[j + 1])
            {
                swap(m, j, j + 1);
                /*
                temp = m[j];
                m[j] = m[j + 1];
                m[j + 1] = temp;
                */
            }
        }
    }
    return m;
}
// сортировка быстрая
private static int[] quickSort(int[] array, int leftIndex, int rightIndex, boolean desc) {
    int i = leftIndex;
    int j = rightIndex;
    int pivot = array[leftIndex];

    while (i <= j) {
        if (!desc) {
            while (i < array.length && array[i] < pivot) {
                i++;
            }
            while (j >= 0 && array[j] > pivot) {
                j--;
            }
        } else {
            while (i < array.length && array[i] > pivot) {
                i++;
            }
            while (j >= 0 && array[j] < pivot) {
                j--;
            }
        }
        if (i <= j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    if (leftIndex < j)
        quickSort(array, leftIndex, j, desc);
    if (i < rightIndex)
        quickSort(array, i, rightIndex, desc);

    return array;
}
private static void swap(int[] numbers, int first, int second) {
    int temp = numbers[first];
    numbers[first] = numbers[second];
    numbers[second] = temp;
}