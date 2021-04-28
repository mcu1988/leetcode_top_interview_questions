package T0146_LRU_cache;

import java.util.HashMap;

class Solu {

    static class Solution {

        static class LRUCache {

            MyLRUCache myLRUCache;

            public LRUCache(int capacity) {
                myLRUCache = new MyLRUCache(capacity);
            }

            public int get(int key) {
                return myLRUCache.get(key);
            }

            public void put(int key, int value) {
                myLRUCache.put(key, value);
            }

            // 双向链表的节点
            class Node {
                int key;
                int val;
                Node pre;
                Node next;
                Node(int key, int val) {
                    this.key = key;
                    this.val = val;
                    pre = null;
                    next = null;
                }
            }

            // 构建双向链表类
            class DoubleList {
                Node head;
                Node tail;

                DoubleList() {
                    head = null;
                    tail = null;
                }

                // 新来的节点添加到头结点，因为头结点是最新活跃的，尾节点是最不活跃的
                public void addToHead(Node node) {
                    if(node == null) return;

                    if(head == null) { // 加入时，需要判断头结点是否为空
                        head = node;
                        tail = node;
                    } else {
                        node.next = head;
                        head.pre = node;
                        head = node;
                    }
                }

                // 把最新操作的节点移动到头结点
                public void moveToHead(Node node) {
                    if(node == null) return;

                    if(node != head) { // 如果已经是头结点，不用移动
                        if(node == tail) { // 如果把尾结点移动到头节点，先把尾结点拆出来，再更新尾结点
                            node.pre.next = null;
                            tail = node.pre;
                        } else { // 把中间节点移动到头结点，先把中间节点从链表中拆解出来
                            node.pre.next = node.next;
                            node.next.pre = node.pre;
                        }

                        // 把拆解出来的节点移动到头结点
                        node.next = head;
                        node.pre = null;
                        head.pre = node;
                        head = node;
                    }
                }

                // 移除最不经常使用的尾结点
                public void removeTail() {
                    if(head == tail) { // 如果队列只有一个节点
                        head = null;
                        tail = null;
                    } else { // 队列有多个节点
                        Node pre = tail.pre;
                        pre.next = null;
                        tail.pre = null;
                        tail = pre;
                    }
                }
            }

            public class MyLRUCache {
                int capacity;
                HashMap<Integer, Node> map;
                DoubleList doubleList;

                MyLRUCache(int capacity) {
                    this.capacity = capacity;
                    map = new HashMap<>();
                    doubleList = new DoubleList();
                }

                // 从LRU中取key
                public int get(int key) {
                    int result = -1; // 默认值-1
                    if(map.containsKey(key)) {
                        Node node = map.get(key);
                        result = node.val;
                        // 把当前key移动到头结点
                        doubleList.moveToHead(node);
                    }
                    return result;
                }

                // 把key， val放入到LRU
                public void put(int key, int val) {
                    if(map.containsKey(key)) { // 已经包含key，更新val
                        Node node = map.get(key);
                        node.val = val;
                        // 把key移动到头结点
                        doubleList.moveToHead(node);
                    } else {
                        // 如果队列满了，先移除尾结点
                        if(map.size() >= capacity) {
                            map.remove(doubleList.tail.key);
                            doubleList.removeTail();
                        }

                        // 新建Node，添加到头结点
                        Node node = new Node(key, val);
                        map.put(key, node);
                        doubleList.addToHead(node);
                    }
                }
            }

        }

        public void printList(LRUCache.DoubleList doubleList) {
            LRUCache.Node head = doubleList.head;

            System.out.print("cur list is: ");

            while(head != null) {
                System.out.print("(" + head.key + "," + head.val + ")");
                head = head.next;
            }
            System.out.println();
            System.out.println();
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();

        Solution.LRUCache lruCache = new Solution.LRUCache(2);

        lruCache.put(1, 1);
        System.out.println("put (1,1)");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.put(2, 2);
        System.out.println("put (2,2)");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.get(1);
        System.out.println("get 1");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.put(3, 3);
        System.out.println("put (3,3)");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.get(2);
        System.out.println("get 2");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.put(4, 4);
        System.out.println("put (4,4)");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.get(1);
        System.out.println("get 1");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.get(3);
        System.out.println("get 3");
        app.printList(lruCache.myLRUCache.doubleList);

        lruCache.get(4);
        System.out.println("get 4");
        app.printList(lruCache.myLRUCache.doubleList);

    }
}
