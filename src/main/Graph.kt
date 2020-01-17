package main

import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.test.assertEquals

/**
 * Graph 자료구조 직접 코드로 구현해보기
 */
class Graph(size: Int) {
    var nodes: Array<Node> // 그래프의 노드들
    val visitedNodes: MutableList<Int> = ArrayList() // 방문한 그래프 저장할 리스트

    data class Node(
        val data: Int, // 노드의 데이터 값 ( 그냥 index )
        val adjacent: LinkedList<Node> = LinkedList(), // 해당 노드에 인접해있는 노드들을 가지고 있는 Link 형태의 리스트
        var marked: Boolean = false // 해당 노드에 방문했는지에 대한 플래그 값
    )

    init {
        nodes = Array(size) { i -> (Node(i))}
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
     * @note Stack 자료구조를 활용한 DFS(Depth First Search, 깊이 우선 탐색)
     * DFS 탐색특징: 엣지로 연결되어 있는 모든 노드를 반드시 순회할 수 있다.
     * DFS 활용: A정점에서 B정점에 도달 할 수 있는가? 혹은 연결되어 있는가? 를 확인할 때 ( 흔히 알고리즘 문제에서 이런식으로 많이 나오는 것 같습니다. )
     * DFS 단점: 스택 자료구조 활용 혹은 재귀적호출로 구현할 수 있는데, 결국 둘다 제한적인 스택 메모리를 활용해야 하므로 StackOverFlow 발생을 우려 할 수 있습니다.
     * , 또한 반드시 구한 해(결과 값) 가 최단거리라고 보장할 수 없습니다.
     *
     * DFS로 그래프를 순회하는 순서는 다음과 같습니다.
     * 어떤 한 정점에서부터 시작하여 왼쪽 인접 노드로부터 깊이 들어가여 더이상 인접 노드가 없을 때까지 들어갑니다. 그러다 인접노드가 존재하지 않으면
     * 다시 다른 뿌리에 인접한 노드가 있을 때까지의 노드로 거슬러올라가는 형태 ( 거슬러 올라가야 하기 때문에 Stack 자료구조 활용 )
     */
    fun DFS(i: Int) {
        val root = nodes[i]
        val stack = Stack<Node>()

        // 처음에 스택이 비어 있으므로, 루트를 먼저 스택에 push 해주고 마크
        stack.push(root);
        root.marked = true

        /**
         * DFS 순환 시작
        1. 스택에서 노드를 하나 꺼낸 뒤
        2. 꺼낸 노드에 인접한 노드가 있다면? -> 2.1
        2-1. 인접한 노드를 모두 스택에 넣는다. ( 한번 스택에 넣었던 노드라면 무시한다. )
        3. 꺼낸 노드를 방문(출력)
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
     * 재귀 호출을 이용한 DFS
     * 스택과 방문 순서가 조금 달라질 수 있는데, 그이유는 스택은 후입선출 구조이므로,
     * 인접 노드가 하나 이상인 경우 인접노드 리스트 중 마지막 노드 부터 출력되지만
     * 재귀는 정방향으로 방문 되기 때문에 호출 순서가 이접노드리스트에 있는 노드들이 순차적으로 먼저 방문되어집니다.
     * 재귀 호출 방식으로 구현되어지는 DFS에서는 먼저 방문 후 인접노드를 방문해야함
     */
    fun DFSRecursion(n: Node?) {
        // 재귀가 끝날 기저 조건
        if(n == null) return

        n.marked = true
        visit(n);
        n.adjacent.forEachIndexed() { index, _n -> if(!_n.marked) DFSRecursion(_n) }
    }


    /**
     * @note Queue 자료구조를 활용한 BFS
     * 그래프를 순회하는 순서는 다음과 같습니다.
     * 1. 큐에서 노드를 뺀다.
     * 2. 뺀 노드에 인접한 노드들이 있다면 모두 큐에 넣는다.
     * 3. 뺀 노드를 방문한다.
     *
     * BFS특징: 어떤 노드를 방문했었는지 반드시 마크해야 구현할 수 있음 그래프가 계층형구조라면 한계층을 내려가면서 차례대로 계층별 탐색이 가능합니다.
     * BFS활용: 너비를 우선시 하는 탐색이기 때문에 DFS와는 다르게 해가 여러개 있더라도 최단경로를 보장할 수 있습니다. (DFS는 찾아야할 노드가 좌측에 있을 수록 최단경로를 찾기에 유리, DFS는 좌측 노드 뿌리부터 파고들기 때문)
     * BFS단점: 너비를 우선시 하는 탐색이기 때문에 최단 경로를 찾기 전 방문이 굳이 필요없는 노드를 방문해야 할 수도 있습니다.
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
                    q.offer(_n)
                    _n.marked = true
                }
            }
            visit(n)
        }
    }

    private fun visit(n: Node) {
        visitedNodes.add(n.data)
        println("${n.data} 노드에 방문!")
    }

}

internal class GraphSearchTest {

    private val graph = Graph(11)

    @Before
    fun init() {
        /**
         * DFS 대상 그래프 형태
        ( 0 )
        |
        ( 1 )  —- ( 5 ) —- ( 6 )
        |   \    /         /
        ( 2 )  ( 4 )     ( 7 ) —- ( 8 )
        /                          /
        (3)                        (9)
         *
         */
//                graph.addEdge(0, 1)
//                graph.addEdge(1, 5)
//                graph.addEdge(1, 4)
//                graph.addEdge(1, 2)
//                graph.addEdge(2, 3)
//                graph.addEdge(4, 5)
//                graph.addEdge(5, 6)
//                graph.addEdge(6, 7)
//                graph.addEdge(7, 6)
//                graph.addEdge(7, 8)
//                graph.addEdge(8, 9)


        /**
         *   BFS 대상 그래프 형태
        (9)
        | |
        /   \
        /\    /\
        (7)(4) (0) (2)
        / \  \   |  \ \
        (3)(6)(5)(1)(10)(8)
         *
         */
        graph.addEdge(9, 7)
        graph.addEdge(9, 4)
        graph.addEdge(9, 0)
        graph.addEdge(9, 2)
        graph.addEdge(7, 3)
        graph.addEdge(7, 6)
        graph.addEdge(4, 5)
        graph.addEdge(0, 1)
        graph.addEdge(2, 10)
        graph.addEdge(2, 8)
    }

    @Test
    fun bfsTest() {
        // ROOT Node가 9번이므로 9번부터 출발
        graph.BFS(9)
        // 방문 순서 확인
        assertEquals(graph.visitedNodes.toString(), "[9, 7, 4, 0, 2, 3, 6, 5, 1, 10, 8]")
    }

    @Test
    fun dfsTest() {
        graph.DFS(0)
        // 방문 순서 확인
        assertEquals(graph.visitedNodes.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]")
    }

    @Test
    fun dfsRecursionTest() {
        graph.DFSRecursion(graph.nodes[0])
        // 방문 순서 확인
        assertEquals(graph.visitedNodes.toString(), "[0, 1, 5, 4, 6, 7, 8, 9, 2, 3]")
    }
}
