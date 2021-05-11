package T0207_course_schedule;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 把原数据转成有向图的形式
        // 深度优先遍历图的每个节点，看是否有环
        // 使用数组记录遍历过程中走过的点，判断是否有环
        public boolean canFinish1(int numCourses, int[][] prerequisites) {
            if(numCourses < 1) return false;
            if(numCourses == 1 || prerequisites == null || prerequisites.length == 0)
                return true;

            // 存储 有向图
            HashMap<Integer, List<Integer>> map = new HashMap<>();

            for(int i=0; i<numCourses; i++) map.put(i, new ArrayList<>());

            // 输入转换为有向图
            for(int[] pair : prerequisites) {
                map.get(pair[1]).add(pair[0]);
            }

            // 标记节点是否走过
            boolean[] isVisited = new boolean[numCourses];

            // 逐个遍历每个节点，查看当前节点开始的路径上是否有环
            for(int i=0; i<numCourses; i++) {
                // 如果遇到有环的路径，返回false
                if(process(map, i, isVisited)) return false;
            }
            // 所有节点有效后，返回true
            return true;
        }

        // 深度优先遍历
        // 返回cur开始的路径是否有环
        public boolean process(HashMap<Integer, List<Integer>> map, int cur, boolean[] isVisited) {
            if(isVisited[cur]) {
                return true;
            }

            // 逐个遍历cur的指向的节点
            List<Integer> list = map.get(cur);
            for(int j=0; j<list.size(); j++) {
                int v = list.get(j);
                isVisited[cur] = true;
                // 如果有一条路径有环，返回true
                if(process(map, v, isVisited)) return true;
                // 退递归时，重新标记为没有访问过
                isVisited[cur] = false;
            }
            // 所有路径尝试后，均没有环，返回false
            return false;
        }

        // 方法2
        // 方法1中判断某个点开始的路径中是否有环，存在重复判断的情况
        // 使用数组记录某个点是否已经判断过有环无环
        // 时间复杂度O(V+E),V是顶点的数量，E是边的数量，空间复杂度O(V+E)
        public boolean canFinish2(int numCourses, int[][] prerequisites) {
            if(numCourses < 1) return false;
            if(numCourses == 1 || prerequisites == null || prerequisites.length == 0)
                return true;

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

            // 逐个遍历每个节点，查看当前节点开始的路径上是否有环
            for(int i=0; i<numCourses; i++) {
                // 当前节点检查过且无环，检查下一个节点
                if(checked[i]) continue;
                // 如果遇到有环的路径，返回false
                if(process1(map, i, isVisited, checked)) return false;
            }
            // 所有节点有效后，返回true
            return true;
        }

        // 深度优先遍历
        // 返回cur开始的路径是否有环
        public boolean process1(HashMap<Integer, List<Integer>> map, int cur, boolean[] isVisited, boolean[] checked) {

            // 当前节点出现了环
            if(isVisited[cur]) return true;

            // 当前节点标记为走过
            isVisited[cur] = true;

            // 逐个遍历cur的指向的节点
            for(int v : map.get(cur)) {
                // 当前节点检查过，并且无环
                if(checked[v]) continue;
                // 如果有一条路径有环，返回true
                if(process1(map, v, isVisited, checked)) return true;
            }
            // 退递归时，重新标记为没有访问过
            isVisited[cur] = false;
            // cur的所有路径都遍历后，表示cur没有环，标记为true
            checked[cur] = true;
            // 所有路径尝试后，均没有环，返回false
            return false;
        }


        // 方法3
        // 图的拓扑排序
        // 找到顶点的入度
        // 先把入度为0的点加入队列，弹出一个点，去掉这个出发的边，更新剩下的点的入度和出度
        // 时间复杂度O(V+E),V是顶点的数量，E是边的数量，空间复杂度O(V+E)
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses < 1) return false;
            if(numCourses == 1 || prerequisites == null || prerequisites.length == 0)
                return true;

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
                cnt++;
            }

            // 查看已经弹出的入度为0的点是否是全部节点的数量
            return cnt == numCourses;
        }


    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int numCourses = 2;
        int[][] prerequisites = {{1,0}};

        app.canFinish(numCourses, prerequisites);

    }
}
