package main

import java.util.*

class Graph(size: Int) {
    private var nodes: Array<Node>

    data class Node(
        val data: Int,
        val adjacent: LinkedList<Node> = LinkedList(),
        var marked: Boolean = false
    )

    init {
        nodes = Array(size) { i -> (Node(i+1))}
    }

    /**
     * @note 인접한 노드 간선을 연결
     */
    fun addEdge(i1: Int, i2: Int){
        val n1 = nodes[i1]
        val n2 = nodes[i2]
        if(!n1.adjacent.contains(n2)) {
            n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)) {
            n2.adjacent.add(n1) ;
        }
    }


    /**
     * @note Stack 자료구조를 활용한 DFS 그래프 탐색
     *
     * 그래프를 순회하는 순서는 다음과 같습니다.
     * 루트에서부터 시작하여 왼쪽 자식 노드로부터 깊이 들어가여 더이상 자식이 없을 때까지 들어갑니다. 그러다 자식이 존재하지 않으면
     * 다시 다른 뿌리에 인접한 노드가 있을 때까지의 노드로 거슬러올라가는 형태 ( 거슬러 올라가야 하기 때문에 Stack 자료구조 활용 )
     */
    fun DFS(i: Int) {
        val root = nodes[i]
        val stack = Stack<Node>()

        // 처음에 스택이 비어 있으므로, 루트를 먼저 스택에 push 해주고 마크
        stack.push(root);
        root.marked = true

        /**
         * 스택에서 꺼낸 노드의 인접노드가 존재한다면 스택에 넣어줌
         * 꺼낸 노드는 출력
         * 한번이라도 스택에 들어 갔던 노드는 마킹한 후 이후에 다시 넣지 않음
         */
        while(!stack.isEmpty()) {
            val r = stack.pop()
            r.adjacent.forEachIndexed { index, _node ->
                val n = r.adjacent.get(index)
                if(!n.marked) {
                    n.marked = true;
                    stack.push(n)
                }
            }
            visit(r)
        }
    }

    /**
     * @note Queue 자료구조를 활용한 BFS
     * 그래프를 순회하는 순서는 다음과 같습니다.
     * 1. 큐에서 노드를 뺀다.
     * 2. 뺀 노드에 인접한 노드들이 있다면 모두 큐에 넣는다.
     * 3. 뺀 노드를 방문한다.
     */
    fun BFS(i: Int) {
        val root = nodes[i]
        val q: Queue<Node> = LinkedList()
        q.offer(root)
        root.marked = true

        while (!q.isEmpty()) {
            val n = q.poll()
            n.adjacent.forEach { _n ->
                if(!_n.marked) {
                    n.marked = true
                    q.offer(n)
                }
            }
            visit(n)
        }
    }

    private fun visit(n: Node) {
        println("${n.data} 노드에 방문!")
    }

}

