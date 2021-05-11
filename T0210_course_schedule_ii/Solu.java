package T0210_course_schedule_ii;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 把原数据转成有向图的形式
        // 深度优先遍历图的每个节点，看是否有环
        // 使用数组记录遍历过程中走过的点，判断是否有环
        // 深度遍历时，无环则加入到结果集，返回上一层
        // 最终把结果集中的节点倒序输出
        // 时间复杂度O(V+E),V是顶点的数量，E是边的数量，空间复杂度O(V+E)
        public int[] findOrder1(int numCourses, int[][] prerequisites) {
            if(numCourses < 1) return new int[] {};

            // 初始顺序
            int[] result = new int[numCourses];
            for(int i=0; i<numCourses; i++) result[i] = i;

            if(numCourses == 1 || prerequisites == null || prerequisites.length == 0)
                return new int[] {0};

            // 存储 有向图
            HashMap<Integer, List<Integer>> map = new HashMap<>();

            for(int i=0; i<numCourses; i++) map.put(i, new ArrayList<>());

            // 输入转换为有向图
            for(int[] pair : prerequisites) {
                map.get(pair[1]).add(pair[0]);
            }

            // 标记节点是否走过
            boolean[] isVisited = new boolean[numCourses];
            // 当前节点是否检查过，把检查过的无环的节点标记为true
            boolean[] checked = new boolean[numCourses];

            List<Integer> resultList = new ArrayList<>();

            // 逐个遍历每个节点，查看当前节点开始的路径上是否有环
            for(int i=0; i<numCourses; i++) {
                // 当前节点检查过且无环，检查下一个节点
                if(checked[i]) continue;
                // 如果遇到有环的路径，返回false
                if(process(map, i, isVisited, checked, resultList)) return new int[] {};
            }

            // 把深度遍历的节点顺序倒过来
            for(int i=0; i<numCourses; i++) {
                result[i] = resultList.get(numCourses-1-i);
            }

            return result;
        }

        // 深度优先遍历
        // 返回cur开始的路径是否有环
        public boolean process(HashMap<Integer, List<Integer>> map, int cur, boolean[] isVisited, boolean[] checked, List<Integer> resultList) {

            // 当前节点出现了环
            if(isVisited[cur]) return true;

            // 当前节点标记为走过
            isVisited[cur] = true;

            // 逐个遍历cur的指向的节点
            for(int v : map.get(cur)) {
                // 当前节点检查过，并且无环
                if(checked[v]) continue;
                // 如果有一条路径有环，返回true
                if(process(map, v, isVisited, checked, resultList)) return true;
            }
            // 退递归时，重新标记为没有访问过
            isVisited[cur] = false;
            // cur的所有路径都遍历后，表示cur没有环，标记为true
            checked[cur] = true;
            // 当前节点出发的路径上，没有环，加入结果
            resultList.add(cur);
            // 所有路径尝试后，均没有环，返回false
            return false;
        }

        // 方法2
        // 图的拓扑排序
        // 找到顶点的入度
        // 先把入度为0的点加入队列，弹出一个点，加入到结果集，去掉这个出发的边，更新剩下的点的入度
        // 节点加入的书序就是上课的顺序
        // 时间复杂度O(V+E),V是顶点的数量，E是边的数量，空间复杂度O(V+E)
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if(numCourses < 1) return new int[] {};

            // 初始顺序
            int[] result = new int[numCourses];
            for(int i=0; i<numCourses; i++) result[i] = i;

            if(numCourses == 1 || prerequisites == null || prerequisites.length == 0)
                return result;

            // 存储 有向图
            HashMap<Integer, List<Integer>> map = new HashMap<>();

            for(int i=0; i<numCourses; i++) map.put(i, new ArrayList<>());

            // 输入转换为有向图
            for(int[] pair : prerequisites) {
                map.get(pair[1]).add(pair[0]);
            }

            // 顶点入度数组
            int[] in = new int[numCourses];

            for(int[] pair : prerequisites) {
                in[pair[0]]++;
            }

            // 使用队列存储入度为0的点
            Queue<Integer> queue = new LinkedList<>();
            for(int i=0; i<numCourses; i++) {
                if(in[i] == 0) queue.add(i);
            }

            int cnt = 0;
            while(!queue.isEmpty()) {
                // 队列中弹出一个节点，去掉该节点出发的边，更新剩下节点的入度
                int v = queue.poll();
                for(int to : map.get(v)) {
                    in[to]--;
                    // 入度为0的顶点重新加入队列
                    if(in[to] == 0) queue.add(to);
                }
                result[cnt++] = v;
            }

            // 查看已经弹出的入度为0的点是否是全部节点的数量
            return cnt == numCourses ? result : new int[] {};
        }

    }


    public static void main(String[] args) {

        Solution app = new Solution();



    }
}
