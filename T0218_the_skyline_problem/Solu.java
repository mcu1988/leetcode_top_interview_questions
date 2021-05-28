package T0218_the_skyline_problem;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 将原始输入，转换为二元组，加入时的高度为负数，退出时的高度为正数
        // 对二元素进行排序
        // 按照横坐标进行排序，横坐标相等时，再按照高度进行排序，高度低的排在前面
        // 特殊情况
        // 1. 在同一坐标添加时，把高度大的排在前面，高度大的转为负数会，更小，会排在前面
        // 2. 在同一坐标退出时，把高度小的排在前面，高度小的，正数值小一些，排在前面
        // 3. 在同一坐标一个添加，一个退出时，把添加的排在前面，退出的排在后面，添加的高度是负数，退出的事正数，排在前面
        // 遍历排序后的二元组，使用最大堆，找到已经加入到堆中元素的最大值，加入时，如果高度时负数，转为正数后，添加到堆，如果高度是正数，从堆中直接删除
        // 堆中会存在重复的元素，每次添加和操作都会进行堆进行增删操作，加入数量过多时，堆的大小扩容也需要时间
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        public List<List<Integer>> getSkyline1(int[][] buildings) {

            if(buildings == null || buildings.length == 0 || buildings[0].length != 3)
                return new ArrayList<>();

            int N = buildings.length;
            int[][] arr = new int[2*N][2];
            int index = 0;
            // 把输入转换为二元组
            for(int[] building : buildings) {
                // 添加高度时，标记高度为负数
                arr[index][0] = building[0];
                arr[index++][1] = -1 * building[2];

                // 删除高度时，标记为正数
                arr[index][0] = building[1];
                arr[index++][1] = building[2];
            }

            // 按照坐标从小到大排序，坐标相等时，再按照高度从小到大排序
            Arrays.sort(arr, (a, b) -> {
                if(a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            });

            // 建立最大堆，初始加入高度0
            Queue<Integer> maxHeightHeap = new PriorityQueue<>((a, b) -> b - a);
            maxHeightHeap.add(0);
            int preHeight = 0;
            List<List<Integer>> result = new ArrayList<>();
            for(int[] pair : arr) {
                // 高度为负数，表示添加
                if(pair[1] < 0) maxHeightHeap.add(-1 * pair[1]);
                else maxHeightHeap.remove(pair[1]); // 高度为负数，表示删除

                // 操作后查看堆顶的最大高度是否有变化
                int curMaxHeight = maxHeightHeap.peek();
                if(curMaxHeight != preHeight) {
                    result.add(Arrays.asList(pair[0], curMaxHeight));
                    preHeight = curMaxHeight;
                }
            }

            return result;
        }


        // 方法2
        // 方法1中堆中会存在重复的元素，每次添加和操作都会进行堆进行增删操作，加入数量过多时，堆的大小扩容也需要时间
        // 自己实现堆，对堆中的重复元素，使用cnt进行计数，减少对堆的操作
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        public List<List<Integer>> getSkyline(int[][] buildings) {

            if(buildings == null || buildings.length == 0 || buildings[0].length != 3)
                return new ArrayList<>();

            int N = buildings.length;
            int[][] arr = new int[2*N][2];
            int index = 0;
            // 把输入转换为二元组
            for(int[] building : buildings) {
                // 添加高度时，标记高度为负数
                arr[index][0] = building[0];
                arr[index++][1] = -1 * building[2];

                // 删除高度时，标记为正数
                arr[index][0] = building[1];
                arr[index++][1] = building[2];
            }

            // 按照坐标从小到大排序，坐标相等时，再按照高度从小到大排序
            Arrays.sort(arr, (a, b) -> {
                if(a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            });

            // 建立最大堆，初始加入高度0
            MyHeap maxHeightHeap = new MyHeap(new MyComparator());
            maxHeightHeap.push(0);

            int preHeight = 0;
            List<List<Integer>> result = new ArrayList<>();
            for(int[] pair : arr) {
                // 高度为负数，表示添加
                if(pair[1] < 0) maxHeightHeap.push(-1 * pair[1]);
                else maxHeightHeap.delete(pair[1]); // 高度为负数，表示删除

                // 操作后查看堆顶的最大高度是否有变化
                int curMaxHeight = maxHeightHeap.peek().key;
                if(curMaxHeight != preHeight) {
                    result.add(Arrays.asList(pair[0], curMaxHeight));
                    preHeight = curMaxHeight;
                }
            }

            return result;
        }

        // 存储每个key对应的cnt
        class Info {
            public int key;
            public int cnt;
            Info(int key, int cnt) {
                this.key = key;
                this.cnt = cnt;
            }
        }

        public class MyComparator implements Comparator<Info> {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.key - o1.key;
            }
        }

        public class MyHeap {

            private ArrayList<Info> heap; // 堆的数组
            private HashMap<Integer, Integer> indexMap; // 存储每个元素对应的在堆数组中的位置
            private int heapSize;
            private Comparator<Info> comparator;

            public MyHeap(Comparator<Info> com) {
                heap = new ArrayList<>();
                indexMap = new HashMap<>();
                heapSize = 0;
                comparator = com;
            }

            public boolean isEmpty() {
                return heapSize == 0;
            }

            public int size() {
                return heapSize;
            }

            // 堆中是否包含某个元素，使用map查找
            public boolean contains(int key) {
                return indexMap.containsKey(key);
            }

            // 新元素插入到堆的末尾，进行heapInsert过程
            // 如果元素已经存在，重复计数，不需要调整堆
            public void push(int key) {
                if(!indexMap.containsKey(key)) {
                    Info newInfo = new Info(key, 1);
                    heap.add(newInfo);
                    indexMap.put(key, heapSize);
                    heapInsert(heapSize++);
                } else {
                    int index = indexMap.get(key);
                    heap.get(index).cnt++;
                }
            }

            // 返回堆顶元素的key
            public Info peek() {
                if(heapSize == 0) return null;
                return heap.get(0);
            }

            // 弹出堆顶元素，交换堆顶和堆尾元素，进行heapify过程
            // 如果堆顶元素的cnt>1, 这不需要调整cnt，不需要heapify过程
            public Info pop() {
                if(heapSize == 0) return null;
                Info ans = heap.get(0);
                if(ans.cnt > 1) {
                    heap.get(0).cnt--;
                } else {
                    int end = heapSize - 1;
                    swap(0, end);
                    heap.remove(end);
                    indexMap.remove(ans.key);
                    heapify(0, --heapSize);
                }
                return ans;
            }

            // 删除堆中的某个key，
            // 交换该元素和堆尾的元素，可能向上调整或者向下调整
            // 如果该值存在多次，只用调整cnt，不用删除
            // 如果删除的是最后一个元素，不需要调整
            public void delete(int key) {
                if(!indexMap.containsKey(key)) return;

                int index = indexMap.get(key);
                Info ans = heap.get(index);
                if(ans.cnt > 1) {
                    ans.cnt--;
                } else {
                    swap(index, heapSize-1);
                    heap.remove(heapSize-1);
                    indexMap.remove(key);
                    if(index == heapSize-1) {
                        heapSize--;
                        return; // 删除的是最后一个元素，不需要调整堆
                    }
                    heapInsert(index);
                    heapify(index, --heapSize);
                }
            }

            // 从index尾部向上调整
            private void heapInsert(int index) {
                while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                    swap(index, (index - 1) / 2);
                    index = (index - 1) / 2;
                }
            }

            // 从index位置向下调整
            private void heapify(int index, int heapSize) {
                int left = index * 2 + 1;
                while (left < heapSize) {
                    int largest = left + 1 < heapSize && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0)
                            ? left + 1
                            : left;
                    largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
                    if (largest == index) {
                        break;
                    }
                    swap(largest, index);
                    index = largest;
                    left = index * 2 + 1;
                }
            }

            private void swap(int i, int j) {
                Info o1 = heap.get(i);
                Info o2 = heap.get(j);
                heap.set(i, o2);
                heap.set(j, o1);
                indexMap.put(o1.key, j);
                indexMap.put(o2.key, i);
            }

        }


        // 方法3
        // 思路和方法1一致
        // 使用TreeMap代替堆进行高度排序，key是高度，value是次数，key是唯一的，重复的key使用value进行计数
        // TreeMap中不能包含重复元素，因此相同高度进行重复操作时，需要统计次数
        // 使用TreeMap的value进行计数
        // 由于使用了value进行计数，当对重复的高度进行操作时，只需要操作value即可，减少了直接对treeMap进行增删的操作
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        public List<List<Integer>> getSkyline3(int[][] buildings) {

            if(buildings == null || buildings.length == 0 || buildings[0].length != 3)
                return new ArrayList<>();

            int N = buildings.length;
            int[][] arr = new int[2*N][2];
            int index = 0;
            // 把输入转换为二元组
            for(int[] building : buildings) {
                // 添加高度时，标记高度为负数
                arr[index][0] = building[0];
                arr[index++][1] = -1 * building[2];

                // 删除高度时，标记为正数
                arr[index][0] = building[1];
                arr[index++][1] = building[2];
            }

            // 按照坐标从小到大排序，坐标相等时，再按照高度从小到大排序
            Arrays.sort(arr, (a, b) -> {
                if(a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            });

            // 建立有序表，key表示高度，value表示次数，初始加入高度0
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            treeMap.put(0, 1);
            int preHeight = 0;
            List<List<Integer>> result = new ArrayList<>();
            for(int[] pair : arr) {
                // 高度为负数，表示添加
                if(pair[1] < 0) {
                    // 添加时，需要累计计量添加的次数
                    int h = -1 * pair[1];
                    if(treeMap.containsKey(h)) {
                        treeMap.put(h, treeMap.get(h) + 1);
                    } else treeMap.put(h, 1);
                } else {
                    // 删除时，如果次数为1了，再删除
                    int cnt = treeMap.get(pair[1]);
                    if(cnt == 1) {
                        treeMap.remove(pair[1]);
                    } else treeMap.put(pair[1], cnt-1);
                }

                // 操作后查看堆顶的最大高度是否有变化
                int curMaxHeight = treeMap.lastKey();
                if(curMaxHeight != preHeight) {
                    result.add(Arrays.asList(pair[0], curMaxHeight));
                    preHeight = curMaxHeight;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};

        app.getSkyline(buildings);



        // treemap和堆的操作时间对比
        Queue<Integer> heap = new PriorityQueue<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int times = 1000000;
        int[] v = new int[times];
        for(int i=0; i<times; i++) {
            v[i] = (int)(Math.random() * 100);
        }
        long start = System.currentTimeMillis();
        for(int i=0; i<times; i++) {
            heap.add(v[i]);
            heap.remove(v[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("heap: " + (end - start));

        start = System.currentTimeMillis();
        for(int i=0; i<times; i++) {
            treeMap.put(v[i], 1);
            treeMap.remove(v[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap: " + (end - start));
    }
}
